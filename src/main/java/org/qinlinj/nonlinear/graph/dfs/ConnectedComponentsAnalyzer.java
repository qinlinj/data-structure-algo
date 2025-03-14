package org.qinlinj.nonlinear.graph.dfs;

import org.qinlinj.nonlinear.graph.AdjSet;
import org.qinlinj.nonlinear.graph.Graph;

import java.util.*;

/**
 * ConnectedComponentsAnalyzer - A class to identify and analyze connected components in an undirected graph using DFS.
 * <p>
 * What are Connected Components (CC)?
 * A connected component is a subgraph in which any two vertices are connected to each other by paths,
 * and which is connected to no additional vertices in the supergraph.
 * <p>
 * Advantages and Applications:
 * 1. Network Analysis: Identify isolated networks or communities within a larger graph
 * 2. Image Processing: Connected components are used to identify distinct objects in images
 * 3. Social Network Analysis: Finding communities of connected individuals
 * 4. Circuit Design: Identifying connected elements in electronic circuits
 * <p>
 * Principles and Concepts:
 * - Uses DFS to identify all vertices in the same connected component
 * - Each component is assigned a unique ID number (starting from 1)
 * - The algorithm tracks not just whether a vertex is visited, but also which component it belongs to
 * - This allows for more advanced queries like checking if two vertices are connected
 * <p>
 * Differences from basic CC implementation:
 * 1. Uses integer array instead of boolean array to track components IDs, not just visited status
 * 2. Provides additional utility methods to:
 * - Retrieve all vertices in each component
 * - Check if two vertices are in the same component (connected)
 * 3. Includes validation for vertex parameters
 * <p>
 * Time Complexity: O(V + E) where V is number of vertices and E is number of edges
 * Space Complexity: O(V) for the visited array, recursion stack, and component lists
 */
public class ConnectedComponentsAnalyzer {
    private Graph g;

    private int[] visited;

    private int ccCount = 0;

    public ConnectedComponentsAnalyzer(Graph g) {
        this.g = g;

        if (g == null) return;

        this.visited = new int[g.getV()];
        Arrays.fill(visited, -1);
        for (int v = 0; v < g.getV(); v++) {
            if (visited[v] == -1) {
                ccCount++;
                dfs(v, ccCount);
            }
        }
    }

    public static void main(String[] args) {
        Graph g = new AdjSet("data/graph-dfs.txt");
        ConnectedComponentsAnalyzer graphDFS = new ConnectedComponentsAnalyzer(g);
        System.out.println(Arrays.toString(graphDFS.components()));

        System.out.println(graphDFS.isConnected(0, 6));
    }

    private void dfs(int v, int ccId) {
        visited[v] = ccId;
        for (int w : g.adj(v)) {
            if (visited[w] == -1) {
                dfs(w, ccId);
            }
        }
    }

    public int getCcCount() {
        return ccCount;
    }

    public List<Integer>[] components() {
        List<Integer>[] res = new ArrayList[ccCount];
        // Arrays.fill(res, new ArrayList<>());
        for (int i = 0; i < ccCount; i++) {
            res[i] = new ArrayList<>();
        }
        for (int v = 0; v < g.getV(); v++) {
            int cc = visited[v];
            res[cc - 1].add(v);
        }

        return res;
    }

    public boolean isConnected(int v, int w) {
        validateVertex(v);
        validateVertex(w);

        return visited[v] == visited[w];
    }

    private void validateVertex(int v) {
        if (v < 0 && v >= g.getV()) {
            throw new IllegalArgumentException("error");
        }
    }
}
