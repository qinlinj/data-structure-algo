package org.qinlinj.nonlinear.graph.direct;

import java.util.*;
import org.qinlinj.nonlinear.graph.Graph;

// @formatter:off
/**
 * Topological Sort using Depth-First Search
 *
 * Concept and Principles:
 * - Topological sorting is an ordering of vertices in a directed acyclic graph (DAG)
 *   such that for every directed edge (u,v), vertex u comes before vertex v in the ordering.
 * - This implementation uses DFS-based approach:
 *   1. Perform DFS traversal of the graph
 *   2. When backtracking from a vertex (after exploring all its neighbors),
 *      add the vertex to the result
 *   3. Reverse the result to get the topological ordering
 *   4. Detect cycles during DFS to ensure the graph is a DAG
 *
 * Advantages:
 * - Efficient: O(V+E) time complexity where V is number of vertices and E is number of edges
 * - Intuitive: Uses the natural recursion of DFS to determine dependencies
 * - Memory efficient: Uses O(V) extra space for tracking visited vertices
 * - Integrated cycle detection: Can detect if the graph contains cycles (topological sort only works on DAGs)
 *
 * Visualization Example:
 * Consider a directed graph representing build dependencies:
 *
 *    0 --> 1 --> 3
 *    |     |
 *    v     v
 *    2 --> 4
 *
 * DFS execution:
 * 1. Start DFS at vertex 0:
 *    - Mark 0 as visited and on current path
 *    - Explore neighbor 1:
 *      - Mark 1 as visited and on current path
 *      - Explore neighbor 3:
 *        - Mark 3 as visited and on current path
 *        - No neighbors to explore
 *        - Mark 3 as not on current path
 *        - Add 3 to result: res=[3]
 *      - Explore neighbor 4:
 *        - Mark 4 as visited and on current path
 *        - No neighbors to explore
 *        - Mark 4 as not on current path
 *        - Add 4 to result: res=[3,4]
 *      - Mark 1 as not on current path
 *      - Add 1 to result: res=[3,4,1]
 *    - Explore neighbor 2:
 *      - Mark 2 as visited and on current path
 *      - Explore neighbor 4 (already visited, not on current path)
 *      - Mark 2 as not on current path
 *      - Add 2 to result: res=[3,4,1,2]
 *    - Mark 0 as not on current path
 *    - Add 0 to result: res=[3,4,1,2,0]
 *
 * 2. Final result (after reversing): [0,2,1,4,3] - A valid topological ordering
 *
 * In this implementation, the result is built in reverse order during DFS,
 * so no explicit reversal step is needed.
 */
public class TopologySortDFS {
    // The directed graph to be topologically sorted
    private Graph g;

    // Flag indicating if the graph contains a cycle (if true, no valid topological sort exists)
    private boolean hasCycle = false;

    // Track visited vertices across all DFS traversals
    private boolean[] visited;

    // Track vertices on the current DFS path (used for cycle detection)
    private boolean[] isOnPath;

    // Array to store the topological ordering
    private int[] res;

    // Index pointer for filling the result array (starts from the end for post-order traversal)
    private int index;

    /**
     * Constructor that initializes and runs the topological sort algorithm.
     *
     * Time Complexity: O(V+E) where V is number of vertices and E is number of edges
     * Space Complexity: O(V) for the result array, visited array, isOnPath array, plus recursion stack
     *
     * @param g The directed graph to be topologically sorted
     */
    public TopologySortDFS(Graph g) {
        this.g = g;

        // Handle null graph case
        if (g == null) return;

        // Initialize tracking arrays
        this.visited = new boolean[g.getV()];
        this.isOnPath = new boolean[g.getV()];

        // Initialize result array and index pointer
        // We fill the array from the end to naturally capture the reversed post-order
        this.res = new int[g.getV()];
        this.index = this.res.length - 1;

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
     * Main method to demonstrate the topological sort algorithm.
     *
     * Time Complexity: Depends on graph size, same as constructor
     * Space Complexity: Depends on graph size, same as constructor
     *
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        // Create a graph from a file
        Graph g = new GraphImpl("data/directedgraph-dfs.txt", true);

        // Run topological sort algorithm
        TopologySortDFS graphDFS = new TopologySortDFS(g);

        // Output whether the graph has a cycle (if true, topological sort is not possible)
        System.out.println("Has cycle: " + graphDFS.isHasCycle());

        // Output the topological ordering (if no cycle exists)
        System.out.println("Topological order: " + Arrays.toString(graphDFS.getRes()));
    }

    /**
     * Depth-first search implementation for topological sorting.
     *
     * The key insight is that the reverse post-order of DFS gives a valid topological sort.
     * We also check for cycles during the traversal, as topological sort is only defined for DAGs.
     *
     * Time Complexity: O(V+E) where V is number of vertices and E is number of edges
     * Space Complexity: O(V) for the recursion stack in worst case (linear graph)
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

        // Post-order processing: add vertex to result after exploring all its descendants
        // This is the key to topological sorting - a vertex is added only after all its
        // dependencies (outgoing edges) have been processed
        res[index--] = v;

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

    /**
     * Returns the topological ordering of the graph vertices.
     * Note: If the graph has a cycle, the returned array will not be a valid topological sort.
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