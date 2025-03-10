package org.qinlinj.nonlinear.graph.bfs;

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
public class ConnectedComponents {
    private Graph g;
    private int[] visited;  // Stores component id for each vertex
    private int ccCount;    // Number of connected components

    /**
     * Constructor that finds all connected components in the graph
     * Time Complexity: O(V + E) where V is the number of vertices and E is the number of edges
     *
     * @param g the input graph
     */
    public ConnectedComponents(Graph g) {
        this.g = g;

        this.visited = new int[g.getV()];
        Arrays.fill(visited, -1);  // Initialize all vertices as unvisited

        // Find connected components
        for (int v = 0; v < g.getV(); v++) {
            if (visited[v] == -1) {
                ccCount++;
                bfs(v, ccCount);
            }
        }

        /*
         * Example process:
         * For the graph:
         *    0 -- 1    4 -- 5
         *    |    |    |
         *    2 -- 3    6
         *         |
         *         7
         *
         * Initial visited array: [-1, -1, -1, -1, -1, -1, -1, -1]
         *
         * Start with vertex 0 (unvisited):
         * - ccCount = 1
         * - BFS from vertex 0
         * - After BFS, visited = [1, 1, 1, 1, -1, -1, -1, 1]
         *   (vertices 0, 1, 2, 3, 7 are marked as component 1)
         *
         * Next, vertex 4 (unvisited):
         * - ccCount = 2
         * - BFS from vertex 4
         * - After BFS, visited = [1, 1, 1, 1, 2, 2, 2, 1]
         *   (vertices 4, 5, 6 are marked as component 2)
         *
         * Final ccCount = 2 (two connected components found)
         */
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
        visited[v] = ccId;  // Mark the starting vertex with component id

        while (!queue.isEmpty()) {
            int curr = queue.poll();

            for (int w : g.adj(curr)) {
                if (visited[w] == -1) {
                    queue.add(w);
                    visited[w] = ccId;  // Mark the neighbor with component id
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

    /**
     * Returns the number of connected components in the graph
     * Time Complexity: O(1)
     *
     * @return the number of connected components
     */
    public int getComponentCount() {
        return ccCount;
    }

    /**
     * Returns all vertices grouped by their component
     * Time Complexity: O(V) where V is the number of vertices
     *
     * @return array of lists, where each list contains vertices in one component
     */
    public List<Integer>[] components() {
        @SuppressWarnings("unchecked")
        List<Integer>[] result = new ArrayList[ccCount];

        for (int i = 0; i < ccCount; i++) {
            result[i] = new ArrayList<>();
        }

        for (int v = 0; v < g.getV(); v++) {
            int cc = visited[v];
            result[cc - 1].add(v);
        }

        return result;

        /*
         * Example result for the graph:
         *    0 -- 1    4 -- 5
         *    |    |    |
         *    2 -- 3    6
         *         |
         *         7
         *
         * Visited array: [1, 1, 1, 1, 2, 2, 2, 1]
         *
         * After processing:
         * result[0] = [0, 1, 2, 3, 7] (component 1)
         * result[1] = [4, 5, 6] (component 2)
         */
    }

    /**
     * Checks if two vertices are in the same connected component
     * Time Complexity: O(1)
     *
     * @param v first vertex
     * @param w second vertex
     * @return true if vertices are connected, false otherwise
     */
    public boolean isConnected(int v, int w) {
        validateVertex(v);
        validateVertex(w);

        return visited[v] == visited[w];

        /*
         * Example:
         * For the graph:
         *    0 -- 1    4 -- 5
         *    |    |    |
         *    2 -- 3    6
         *         |
         *         7
         *
         * isConnected(0, 3) returns true (both in component 1)
         * isConnected(0, 4) returns false (in different components)
         * isConnected(4, 6) returns true (both in component 2)
         */
    }

    /**
     * Validates if a vertex is within the valid range
     * Time Complexity: O(1)
     *
     * @param v the vertex to validate
     * @throws IllegalArgumentException if vertex is invalid
     */
    private void validateVertex(int v) {
        if (v < 0 || v >= g.getV()) {
            throw new IllegalArgumentException("Invalid vertex: out of range");
        }
    }
}
