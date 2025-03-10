package org.qinlinj.nonlinear.graph.bfs;

import org.qinlinj.nonlinear.graph.AdjSet;
import org.qinlinj.nonlinear.graph.Graph;

import java.util.*;


/**
 * Connected Components (CC) Implementation
 * <p>
 * CONCEPT AND PRINCIPLES:
 * A connected component is a subgraph in which any two vertices are connected by paths.
 * This class finds all connected components in an undirected graph using Breadth-First Search (BFS).
 * <p>
 * Key features:
 * 1. Identifies separate connected components in a graph
 * 2. Maintains component information for each vertex
 * 3. Provides query methods to check if vertices are connected
 * 4. Returns all vertices grouped by their component
 * <p>
 * Example graph representation:
 * <p>
 * 0 -- 1    4 -- 5
 * |    |    |
 * 2 -- 3    6
 * |
 * 7
 * <p>
 * In this example, there are 2 connected components:
 * - Component 1: Vertices 0, 1, 2, 3, 7
 * - Component 2: Vertices 4, 5, 6
 */
public class CC {
    private Graph g;
    private int[] visited;  // Stores component id for each vertex
    private int ccCount;    // Number of connected components

    /**
     * Constructor that finds all connected components in the graph
     * Time Complexity: O(V + E) where V is the number of vertices and E is the number of edges
     *
     * @param g the input graph
     */
    public CC(Graph g) {
        this.g = g;

        this.visited = new int[g.getV()];
        Arrays.fill(visited, -1);

        for (int v = 0; v < g.getV(); v++) {
            if (visited[v] == -1) {
                ccCount++;
                bfs(v, ccCount);
            }
        }
    }

    public static void main(String[] args) {
        Graph g = new AdjSet("data/graph-bfs.txt");
        CC graphBFS = new CC(g);
        System.out.println(graphBFS.getCcCount());
    }

    /**
     * Performs BFS traversal to mark all vertices in a connected component
     * Time Complexity: O(V + E) for vertices and edges in the component
     *
     * @param v    starting vertex for BFS
     * @param ccId the id to assign to this connected component
     */
    private void bfs(int v, int ccId) {
        if (g == null) return;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(v);
        visited[v] = ccId;

        while (!queue.isEmpty()) {
            int curr = queue.poll();

            for (int w : g.adj(curr)) {
                if (visited[w] == -1) {
                    queue.add(w);
                    visited[w] = ccId;
                }
            }
        }
        /*
         * BFS Example:
         * For the graph (starting BFS from vertex 0):
         *    0 -- 1    4 -- 5
         *    |    |    |
         *    2 -- 3    6
         *         |
         *         7
         *
         * Initial state: queue = [0], visited = [-1, -1, -1, -1, -1, -1, -1, -1]
         *
         * Step 1: Dequeue 0, visited = [1, -1, -1, -1, -1, -1, -1, -1]
         *         Enqueue neighbors 1, 2. queue = [1, 2]
         *
         * Step 2: Dequeue 1, visited = [1, 1, -1, -1, -1, -1, -1, -1]
         *         Enqueue neighbor 3. queue = [2, 3]
         *
         * Step 3: Dequeue 2, visited = [1, 1, 1, -1, -1, -1, -1, -1]
         *         Enqueue neighbor 3 (already in queue, skip)
         *
         * Step 4: Dequeue 3, visited = [1, 1, 1, 1, -1, -1, -1, -1]
         *         Enqueue neighbor 7. queue = [7]
         *
         * Step 5: Dequeue 7, visited = [1, 1, 1, 1, -1, -1, -1, 1]
         *         No unvisited neighbors.
         *
         * Final state: queue is empty, and component 1 is fully identified.
         */
    }

    public int getCcCount() {
        return ccCount;
    }

    public List<Integer>[] components() {
        List<Integer>[] res = new ArrayList[ccCount];

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
