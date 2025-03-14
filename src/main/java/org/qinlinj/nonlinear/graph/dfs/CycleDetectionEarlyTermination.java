package org.qinlinj.nonlinear.graph.dfs;

import org.qinlinj.nonlinear.graph.AdjSet;
import org.qinlinj.nonlinear.graph.Graph;

/**
 * CycleDetection1 - An improved class to detect cycles in an undirected graph using DFS.
 * <p>
 * Cycle detection identifies whether a graph contains any cycles (closed paths).
 * A cycle exists when there is a path that starts and ends at the same vertex,
 * with at least three vertices involved.
 * <p>
 * Key Differences from the Previous Implementation:
 * 1. Early Termination: This implementation returns as soon as a cycle is detected
 * rather than continuing to search the entire graph
 * 2. DFS Return Value: The dfs() method returns a boolean indicating whether a cycle
 * was found, allowing for immediate propagation up the call stack
 * 3. Efficiency: This approach is more efficient for large graphs where cycles are common,
 * as it avoids unnecessary traversal once a cycle is found
 * <p>
 * Advantages of This Approach:
 * 1. Better Performance: Stops as soon as a cycle is found, saving computation time
 * 2. Cleaner Implementation: The boolean return value makes the code's intent clearer
 * 3. Memory Efficiency: Reduces the depth of the recursion stack by returning early
 * <p>
 * Time Complexity: O(V + E) worst case, but can be much better if cycles are found early
 * Space Complexity: O(V) for the visited array and recursion stack
 */
public class CycleDetectionEarlyTermination {
    // The graph to be processed
    private Graph g;

    // Array to track visited vertices
    private boolean[] visited;

    // Flag to indicate whether the graph has a cycle
    private boolean hasCycle = false;

    /**
     * Constructor that initializes the improved cycle detection algorithm and computes the result.
     * <p>
     * Visual Example with Early Termination:
     * Consider a graph with 6 vertices:
     * 0 -- 1 -- 2
     * |         |
     * 5 -- 4 -- 3
     * <p>
     * With the previous implementation, even after finding a cycle (e.g., 0-1-2-3-4-5-0),
     * the algorithm would continue checking other unvisited vertices.
     * <p>
     * With this implementation:
     * 1. Start DFS from vertex 0
     * 2. As soon as the cycle is detected (when examining vertex 5's neighbor 0),
     * the true value is returned up the call stack
     * 3. The main loop breaks immediately, saving computation time
     *
     * @param g The input graph
     *          <p>
     *          Time Complexity: O(V + E) worst case, better if cycles are found early
     *          Space Complexity: O(V) for the visited array and recursion stack
     */
    public CycleDetectionEarlyTermination(Graph g) {
        this.g = g;

        this.visited = new boolean[g.getV()];

        // Iterate through all vertices
        for (int v = 0; v < g.getV(); v++) {
            // Only start DFS from unvisited vertices
            if (!visited[v]) {
                // If a cycle is detected, set flag and break the loop immediately
                if (dfs(v, v)) {
                    hasCycle = true;
                    break;  // Early termination - no need to check other components
                }
            }
        }
    }

    /**
     * Main method to demonstrate the improved CycleDetection usage.
     *
     * @param args Command line arguments (not used)
     *             <p>
     *             Time Complexity: Depends on the size of the input graph and cycle presence
     *             Space Complexity: Depends on the size of the input graph
     */
    public static void main(String[] args) {
        // Create a graph from file
        Graph g = new AdjSet("data/graph-dfs.txt");

        // Detect cycles in the graph using the improved algorithm
        CycleDetectionEarlyTermination graphDFS = new CycleDetectionEarlyTermination(g);

        // Output whether the graph contains cycles
        System.out.println(graphDFS.hasCycle());
    }

    /**
     * Improved Depth-First Search to detect cycles in the graph with early termination.
     * <p>
     * Visual Example for dfs(0, 0):
     * Graph:
     * 0 -- 1
     * |    |
     * 2 -- 3
     * <p>
     * Steps showing the return value propagation:
     * 1. dfs(0, 0) calls dfs(1, 0)
     * 2. dfs(1, 0) calls dfs(3, 1)
     * 3. dfs(3, 1) calls dfs(2, 3)
     * 4. dfs(2, 3) finds 0 is already visited and not the previous vertex
     * 5. dfs(2, 3) returns true (cycle found)
     * 6. dfs(3, 1) returns true
     * 7. dfs(1, 0) returns true
     * 8. dfs(0, 0) returns true
     * 9. The constructor sets hasCycle to true and breaks the loop
     *
     * @param v    Current vertex being processed
     * @param prev Previous vertex in the DFS traversal
     * @return true if a cycle is detected, false otherwise
     * <p>
     * Time Complexity: O(V + E) worst case
     * Space Complexity: O(V) due to recursion stack in worst case
     */
    private boolean dfs(int v, int prev) {
        visited[v] = true;  // Mark current vertex as visited

        // Explore all adjacent vertices
        for (int w : g.adj(v)) {
            if (!visited[w]) {
                // If a cycle is detected in the subtree, return true immediately
                if (dfs(w, v)) return true;
            } else {  // Neighbor is already visited
                // If the visited neighbor is not the previous vertex, a cycle is detected
                if (w != prev) {
                    return true;  // Return immediately when a cycle is found
                }
            }
        }

        // No cycle found in this DFS branch
        return false;
    }

    /**
     * Returns whether the graph contains any cycles.
     *
     * @return true if the graph has at least one cycle, false otherwise
     * <p>
     * Time Complexity: O(1) - Constant time operation
     * Space Complexity: O(1) - No additional space used
     */
    public boolean hasCycle() {
        return hasCycle;
    }
}