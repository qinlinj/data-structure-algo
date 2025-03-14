package org.qinlinj.nonlinear.graph.dfs;

import org.qinlinj.nonlinear.graph.AdjSet;
import org.qinlinj.nonlinear.graph.Graph;

import java.util.*;

// @formatter:off
/**
 * SingleSourcePath - A class to find paths from a single source vertex to any other vertex using DFS.
 *
 * Concept and Principles:
 * - Single-source path finding determines paths from one source vertex to all other vertices in a graph
 * - This implementation uses DFS (Depth-First Search) traversal from the source vertex
 * - For each visited vertex, we maintain information about its parent/previous vertex
 * - This creates a "parent pointer tree" that can be used to reconstruct any path from source to destination
 *
 * Advantages:
 * - Simple implementation using DFS
 * - Memory efficient - only needs to store one previous vertex for each node
 * - Provides connectivity information (whether a path exists) as well as the actual path
 * - Can work well for sparse graphs or when you need only specific paths
 *
 * Limitations of DFS for path finding:
 * - Unlike BFS, DFS does not guarantee shortest paths in unweighted graphs
 * - DFS-based paths might be longer than necessary
 * - For shortest path finding in unweighted graphs, BFS is preferred
 *
 * Time Complexity: O(V + E) where V is number of vertices and E is number of edges
 * Space Complexity: O(V) for the visited array, prevs array, and recursion stack
 */
public class SingleSourcePath {
    private Graph g;

    private int source;

    private boolean[] visited;
    private int[] prevs;

    public SingleSourcePath(Graph g, int source) {
        this.g = g;
        this.source = source;

        this.visited = new boolean[g.getV()];
        prevs = new int[g.getV()];
        for (int i = 0; i < g.getV(); i++) {
            prevs[i] = -1;
        }
        dfs(source, source);
    }

    public static void main(String[] args) {
        Graph g = new AdjSet("data/graph-bfs.txt");
        SingleSourcePath graphDFS = new SingleSourcePath(g, 0);
        System.out.println(graphDFS.path(6));
    }

    private void dfs(int v, int prev) {
        visited[v] = true;
        prevs[v] = prev;
        for (int w : g.adj(v)) {
            if (!visited[w]) {
                dfs(w, v);
            }
        }
    }

    public boolean isConnected(int target) {
        validateVertex(target);
        return visited[target];
    }

    private void validateVertex(int v) {
        if (v < 0 && v >= g.getV()) {
            throw new IllegalArgumentException("error");
        }
    }

    public List<Integer> path(int target) {
        List<Integer> res = new ArrayList<>();
        if (!isConnected(target)) {
            return res;
        }
        while (target != source) {
            res.add(target);
            target = prevs[target];
        }
        res.add(source);

        Collections.reverse(res);

        return res;
    }
}
