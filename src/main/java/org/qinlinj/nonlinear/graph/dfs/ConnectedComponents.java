package org.qinlinj.nonlinear.graph.dfs;

import org.qinlinj.nonlinear.graph.AdjSet;
import org.qinlinj.nonlinear.graph.Graph;

/**
 * ConnectedComponents - A class to find connected components in an undirected graph using DFS.
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
 * - For undirected graphs: we can use DFS or BFS to find connected components
 * - The algorithm marks all nodes reachable from a given node as belonging to the same component
 * - We start with an unvisited vertex and perform DFS, marking all reachable vertices
 * - Once DFS completes, we've found one connected component
 * - We repeat this for all unvisited vertices until all vertices are visited
 * <p>
 * Time Complexity: O(V + E) where V is number of vertices and E is number of edges
 * Space Complexity: O(V) for the visited array and recursion stack
 */
public class ConnectedComponents {
    private Graph g;

    private boolean[] visited;

    private int ccCount = 0;

    public ConnectedComponents(Graph g) {
        this.g = g;

        if (g == null) return;

        this.visited = new boolean[g.getV()];
        for (int v = 0; v < g.getV(); v++) {
            if (!visited[v]) {
                ccCount++;
                dfs(v);
            }
        }
    }

    public static void main(String[] args) {
        Graph g = new AdjSet("data/graph-dfs.txt");
        ConnectedComponents graphDFS = new ConnectedComponents(g);
        System.out.println(graphDFS.getCcCount());
    }

    private void dfs(int v) {
        visited[v] = true;
        for (int w : g.adj(v)) {
            if (!visited[w]) {
                dfs(w);
            }
        }
    }

    public int getCcCount() {
        return ccCount;
    }
}
