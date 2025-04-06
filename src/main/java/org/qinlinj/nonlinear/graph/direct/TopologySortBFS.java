package org.qinlinj.nonlinear.graph.direct;

import java.util.*;

// @formatter:off
/**
 * Topological Sort using Breadth-First Search (Kahn's Algorithm)
 *
 * Concept and Principles:
 * - Topological sorting is an ordering of vertices in a directed acyclic graph (DAG)
 *   such that for every directed edge (u,v), vertex u comes before vertex v in the ordering.
 * - This implementation uses Kahn's algorithm which is based on BFS:
 *   1. Calculate in-degree (number of incoming edges) for each vertex
 *   2. Enqueue all vertices with in-degree of 0 (no dependencies)
 *   3. While queue is not empty:
 *      a. Dequeue a vertex and add it to result
 *      b. Reduce in-degree of all its neighbors by 1
 *      c. If any neighbor's in-degree becomes 0, enqueue it
 *   4. If the result doesn't include all vertices, the graph has a cycle
 *
 * Advantages:
 * - Efficient: O(V+E) time complexity where V is number of vertices and E is number of edges
 * - Intuitive: Process vertices with no dependencies first, then remove their outgoing edges
 * - Cycle detection: Can detect if the graph contains cycles (topological sort only works on DAGs)
 * - Flexible: Can handle disconnected graphs naturally
 *
 * Visualization Example:
 * Consider a directed graph representing course prerequisites:
 *
 *    0 --> 1 --> 3
 *    |     |
 *    v     v
 *    2 --> 4
 *
 * Initial in-degrees: [0,1,1,1,2]
 * Initial queue: [0] (vertices with in-degree 0)
 *
 * Steps:
 * 1. Dequeue 0, add to result: res=[0]
 *    - Reduce in-degree of neighbors 1 and 2: [0,0,0,1,2]
 *    - Add 1, 2 to queue: queue=[1,2]
 * 2. Dequeue 1, add to result: res=[0,1]
 *    - Reduce in-degree of neighbors 3 and 4: [0,0,0,0,1]
 *    - Add 3 to queue: queue=[2,3]
 * 3. Dequeue 2, add to result: res=[0,1,2]
 *    - Reduce in-degree of neighbor 4: [0,0,0,0,0]
 *    - Add 4 to queue: queue=[3,4]
 * 4. Dequeue 3, add to result: res=[0,1,2,3]
 *    - No neighbors to process
 * 5. Dequeue 4, add to result: res=[0,1,2,3,4]
 *    - No neighbors to process
 *
 * Final result: [0,1,2,3,4] - A valid topological ordering
 */
public class TopologySortBFS {
    // The directed graph to be topologically sorted
    private GraphImpl g;

    // Array to store the topological ordering
    private int[] res;

    // Flag indicating if the graph contains a cycle (if true, no valid topological sort exists)
    private boolean hasCycle = false;

    /**
     * Constructor that initializes and runs the topological sort algorithm.
     *
     * Time Complexity: O(V+E) where V is number of vertices and E is number of edges
     * Space Complexity: O(V) for the result array, in-degree array, and queue
     *
     * @param g The directed graph to be topologically sorted
     * @throws IllegalArgumentException if the input graph is not directed
     */
    public TopologySortBFS(GraphImpl g) {
        // Topological sort is only defined for directed graphs
        if (!g.isDirected()) {
            throw new IllegalArgumentException("Topological sort can only be applied to directed graphs");
        }
        this.g = g;

        // Calculate in-degree for each vertex (number of incoming edges)
        int[] indegrees = new int[g.getV()];
        for (int v = 0; v < g.getV(); v++) {
            indegrees[v] = g.indegree(v);
        }

        // Initialize queue with all vertices that have no dependencies (in-degree = 0)
        Queue<Integer> queue = new LinkedList<>();
        for (int v = 0; v < g.getV(); v++) {
            if (indegrees[v] == 0) {
                queue.add(v);
            }
        }

        // Initialize result array to store the topological ordering
        this.res = new int[g.getV()];
        int index = 0;

        // Process vertices in topological order
        while (!queue.isEmpty()) {
            // Remove a vertex with no dependencies
            int v = queue.remove();

            // Add it to the result
            res[index++] = v;

            // For each neighbor, reduce its in-degree by 1 (removing the edge from v to w)
            for (int w : g.adj(v)) {
                indegrees[w]--;

                // If the neighbor now has no dependencies, add it to the queue
                if (indegrees[w] == 0) {
                    queue.add(w);
                }
            }
        }

        // If we couldn't process all vertices, the graph must have a cycle
        if (index != g.getV()) {
            hasCycle = true;
        }
    }

    /**
     * Main method to demonstrate the topological sort algorithm.
     *
     * Time Complexity: Depends on graph size, same as constructor
     * Space Complexity: Depends on graph size, same as constructor
     *
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        // Create a graph from a file
        GraphImpl g = new GraphImpl("data/directedgraph-dfs.txt", true);

        // Run topological sort algorithm
        TopologySortBFS bfs = new TopologySortBFS(g);

        // Output whether the graph has a cycle (if true, topological sort is not possible)
        System.out.println("Has cycle: " + bfs.isHasCycle());

        // Output the topological ordering (if no cycle exists)
        System.out.println("Topological order: " + Arrays.toString(bfs.getRes()));
    }

    /**
     * Returns whether the graph contains a cycle.
     *
     * Time Complexity: O(1) - constant time lookup
     * Space Complexity: O(1) - no additional space used
     *
     * @return true if the graph contains a cycle, false otherwise
     */
    public boolean isHasCycle() {
        return hasCycle;
    }

    /**
     * Returns the topological ordering of the graph vertices.
     * Note: If the graph has a cycle, the returned array will be incomplete.
     *
     * Time Complexity: O(1) - constant time lookup
     * Space Complexity: O(1) - no additional space used
     *
     * @return array containing vertices in topological order
     */
    public int[] getRes() {
        return res;
    }
}
