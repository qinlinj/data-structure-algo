package org.qinlinj.nonlinear.graph.direct;

import org.qinlinj.nonlinear.graph.Graph;

// @formatter:off
/**
 * Cycle Detection Algorithm for Directed Graphs
 *
 * Concept and Principles:
 * - A cycle in a directed graph is a path that starts and ends at the same vertex.
 * - This algorithm uses a depth-first search (DFS) traversal to detect cycles in a directed graph.
 * - We maintain two boolean arrays:
 *   1. 'visited' - tracks vertices that have been visited during the entire traversal
 *   2. 'isOnPath' - tracks vertices that are on the current DFS path (recursion stack)
 *
 * Advantages:
 * - Efficient: O(V+E) time complexity where V is the number of vertices and E is the number of edges
 * - Space efficient: Uses only O(V) extra space
 * - Simple implementation: Uses a straightforward modification of DFS
 * - Early termination: Stops as soon as a cycle is detected
 *
 * Visualization Example:
 * Consider a directed graph with 4 vertices:
 *
 *    0 --> 1
 *    |     |
 *    v     v
 *    2 --> 3
 *    ^     |
 *    |     |
 *    +-----+
 *
 * DFS starts at vertex 0:
 * 1. Mark 0 as visited and on current path (visited=[T,F,F,F], isOnPath=[T,F,F,F])
 * 2. Explore neighbor 1:
 *    - Mark 1 as visited and on current path (visited=[T,T,F,F], isOnPath=[T,T,F,F])
 *    - Explore neighbor 3:
 *      - Mark 3 as visited and on current path (visited=[T,T,F,T], isOnPath=[T,T,F,T])
 *      - Explore neighbor 2:
 *        - Mark 2 as visited and on current path (visited=[T,T,T,T], isOnPath=[T,T,T,T])
 *        - Explore neighbor 0:
 *          - Vertex 0 is already visited AND on current path (isOnPath[0]=true)
 *          - Cycle detected! Return true
 *
 * The cycle in this example is: 0 -> 1 -> 3 -> 2 -> 0
 */
public class CycleDetection {
    // The graph to be analyzed
    private Graph g;
    // Flag indicating if the graph contains a cycle
    private boolean hasCycle = false;

    // Track visited vertices across all DFS traversals
    private boolean[] visited;
    // Track vertices on the current DFS path (recursion stack)
    private boolean[] isOnPath;

    /**
     * Constructor that initializes and runs the cycle detection algorithm.
     *
     * Time Complexity: O(V+E) where V is number of vertices and E is number of edges
     * Space Complexity: O(V) for the visited and isOnPath arrays plus recursion stack
     *
     * @param g The directed graph to check for cycles
     */
    public CycleDetection(Graph g) {
        this.g = g;

        // Handle null graph case
        if (g == null) return;

        // Initialize tracking arrays
        this.visited = new boolean[g.getV()];
        this.isOnPath = new boolean[g.getV()];

        // Start DFS from each unvisited vertex to handle disconnected graphs
        for (int v = 0; v < g.getV(); v++) {
            if (!visited[v]) {
                if (dfs(v)) {
                    hasCycle = true;
                    break; // Early termination once a cycle is found
                }
            }
        }
    }

    /**
     * Main method to demonstrate the cycle detection algorithm.
     *
     * Time Complexity: Depends on graph size, same as constructor
     * Space Complexity: Depends on graph size, same as constructor
     *
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        // Create a graph from a file
        Graph g = new GraphImpl("data/directedgraph-dfs.txt", true);
        // Run cycle detection algorithm
        CycleDetection graphDFS = new CycleDetection(g);
        // Output whether the graph has a cycle
        System.out.println(graphDFS.isHasCycle());
    }

    /**
     * Depth-first search implementation for cycle detection.
     *
     * Time Complexity: O(V+E) where V is number of vertices and E is number of edges
     * Space Complexity: O(V) for the recursion stack in worst case (linear graph)
     *
     * Visualization example (continuing from class description):
     * When exploring from vertex 0:
     * - We mark 0 as visited and on current path
     * - We explore each neighbor (1 and 2 in our example)
     * - For each neighbor, we recursively call DFS
     * - If we encounter a vertex that is already on our current path, we've found a cycle
     * - After exploring all neighbors of a vertex, we remove it from the current path (isOnPath[v] = false)
     *
     * @param v The current vertex being visited
     * @return true if a cycle is detected, false otherwise
     */
    private boolean dfs(int v) {
        // Mark the current vertex as visited and on the current path
        visited[v] = true;
        isOnPath[v] = true;

        // Explore all neighbors of the current vertex
        for (int w : g.adj(v)) {
            if (!visited[w]) {
                // If neighbor hasn't been visited, recursively visit it
                if (dfs(w)) return true; // Propagate cycle detection upward
            } else {
                // If neighbor is already visited AND on the current path, we found a cycle
                if (isOnPath[w]) return true;
            }
        }

        // Backtrack: remove the current vertex from the current path
        isOnPath[v] = false;
        return false; // No cycle found in this path
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
}