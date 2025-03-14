package org.qinlinj.nonlinear.graph.dfs;

import org.qinlinj.nonlinear.graph.AdjSet;
import org.qinlinj.nonlinear.graph.Graph;

/**
 * CycleDetection - A class to detect cycles in an undirected graph using DFS.
 * <p>
 * Cycle detection identifies whether a graph contains any cycles (closed paths).
 * A cycle exists when there is a path that starts and ends at the same vertex,
 * with at least three vertices involved.
 * <p>
 * Advantages and Applications:
 * 1. Circuit Analysis: Detecting feedback loops in electrical circuits
 * 2. Dependency Resolution: Finding circular dependencies in software packages
 * 3. Deadlock Detection: Identifying potential deadlocks in resource allocation
 * 4. Network Topology: Ensuring redundant paths in network design
 * 5. Constraint Satisfaction: Validating that constraints don't have circular dependencies
 * <p>
 * Principles and Algorithm:
 * - For undirected graphs, a cycle exists if, during DFS traversal, we find an already
 * visited vertex that is not the parent/previous vertex in our traversal
 * - We track both visited vertices and the previous vertex in our traversal
 * - When we encounter an already visited vertex, we check if it's the previous vertex:
 * - If it IS the previous vertex, this is just backtracking (not a cycle)
 * - If it is NOT the previous vertex, we've found a cycle
 * <p>
 * Time Complexity: O(V + E) where V is the number of vertices and E is the number of edges
 * Space Complexity: O(V) for the visited array and recursion stack
 */
public class CycleDetection {
    // The graph to be processed
    private Graph g;

    // Array to track visited vertices
    private boolean[] visited;

    // Flag to indicate whether the graph has a cycle
    private boolean hasCycle = false;

    /**
     * Constructor that initializes the cycle detection algorithm and computes the result.
     * <p>
     * Visual Example:
     * Consider a graph with 4 vertices:
     * 0 -- 1
     * |    |
     * 2 -- 3
     * <p>
     * This graph contains a cycle: 0-1-3-2-0
     * <p>
     * Algorithm steps:
     * 1. Start DFS from vertex 0, marking it as visited, with previous as 0 (itself initially)
     * 2. Visit neighbor 1, marking it as visited, with previous as 0
     * 3. Visit neighbor 3, marking it as visited, with previous as 1
     * 4. Visit neighbor 2, marking it as visited, with previous as 3
     * 5. When at vertex 2, its neighbor 0 is already visited and is not the previous vertex (3)
     * This indicates a cycle, so hasCycle is set to true
     *
     * @param g The input graph
     *          <p>
     *          Time Complexity: O(V + E) - We visit each vertex once and process all edges
     *          Space Complexity: O(V) - For the visited array and recursion stack
     */
    public CycleDetection(Graph g) {
        this.g = g;

        this.visited = new boolean[g.getV()];

        // Iterate through all vertices
        for (int v = 0; v < g.getV(); v++) {
            // Only start DFS from unvisited vertices
            if (!visited[v]) {
                dfs(v, v);  // Initially, previous vertex is set to the starting vertex itself
            }
        }
    }

    /**
     * Main method to demonstrate the CycleDetection usage.
     *
     * @param args Command line arguments (not used)
     *             <p>
     *             Time Complexity: Depends on the size of the input graph
     *             Space Complexity: Depends on the size of the input graph
     */
    public static void main(String[] args) {
        // Create a graph from file
        Graph g = new AdjSet("data/graph-dfs.txt");

        // Detect cycles in the graph
        CycleDetection graphDFS = new CycleDetection(g);

        // Output whether the graph contains cycles
        System.out.println(graphDFS.hasCycle());
    }

    /**
     * Depth-First Search to detect cycles in the graph.
     * <p>
     * Visual Example for dfs(0, 0):
     * Graph:
     * 0 -- 1
     * |    |
     * 2 -- 3
     * <p>
     * Steps:
     * 1. Mark vertex 0 as visited
     * 2. For neighbors of 0 (1 and 2):
     * a. If not visited, call dfs(neighbor, 0)
     * b. For neighbor 1: dfs(1, 0)
     * - Mark 1 as visited
     * - For neighbors of 1 (0 and 3):
     * * 0 is visited but it's the previous vertex (not a cycle)
     * * 3 is not visited, call dfs(3, 1)
     * ~ Mark 3 as visited
     * ~ For neighbors of 3 (1 and 2):
     * # 1 is visited but it's the previous vertex (not a cycle)
     * # 2 is not visited, call dfs(2, 3)
     * > Mark 2 as visited
     * > For neighbors of 2 (0 and 3):
     * + 0 is visited and NOT the previous vertex (cycle detected!)
     * + hasCycle is set to true
     *
     * @param v    Current vertex being processed
     * @param prev Previous vertex in the DFS traversal
     *             <p>
     *             Time Complexity: O(V + E) - Each vertex and edge is processed once
     *             Space Complexity: O(V) - Due to recursion stack in worst case
     */
    private void dfs(int v, int prev) {
        visited[v] = true;  // Mark current vertex as visited

        // Explore all adjacent vertices
        for (int w : g.adj(v)) {
            if (!visited[w]) {
                // If neighbor is not visited, continue DFS with current vertex as previous
                dfs(w, v);
            } else {  // Neighbor is already visited
                // If the visited neighbor is not the previous vertex, a cycle is detected
                if (w != prev) {
                    hasCycle = true;
                }
            }
        }
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
