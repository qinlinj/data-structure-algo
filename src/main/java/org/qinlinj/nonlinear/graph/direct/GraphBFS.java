package org.qinlinj.nonlinear.graph.direct;

import org.qinlinj.nonlinear.graph.Graph;

import java.util.*;

// @formatter:off

/**
 * Implementation of Breadth-First Search for graph traversal
 * Handles both connected and disconnected graphs
 */
public class GraphBFS {
    private Graph g;                 // Graph to traverse
    private boolean[] visited;       // Tracks visited vertices
    private List<Integer> res;       // Stores traversal result

    /**
     * Constructs a BFS traversal of the entire graph
     * Time Complexity: O(V + E)
     */
    public GraphBFS(Graph g) {
        this.g = g;

        this.visited = new boolean[g.getV()];
        this.res = new ArrayList<>();

        // Iterate through all vertices to handle disconnected components
        for (int v = 0; v < g.getV(); v++) {
            if (!visited[v]) bfs(v);
        }
    }

    public static void main(String[] args) {
        Graph g = new GraphImpl("data/directedgraph-dfs.txt", true);
        GraphBFS graphBFS = new GraphBFS(g);
        System.out.println(graphBFS.getRes());
    }

    /**
     * BFS traversal from vertex v
     * Time Complexity: O(V + E) for the component
     */
    private void bfs(int v) {
        if (g == null) return;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(v);
        visited[v] = true;

        while (!queue.isEmpty()) {
            int curr = queue.poll();
            res.add(curr);

            // Add all unvisited neighbors to queue
            for (int w : g.adj(curr)) {
                if (!visited[w]) {
                    queue.add(w);
                    visited[w] = true;
                }
            }
        }
    }

    /**
     * Returns vertices in BFS traversal order
     */
    public List<Integer> getRes() {
        return res;
    }
}
