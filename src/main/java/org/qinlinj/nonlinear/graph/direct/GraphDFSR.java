package org.qinlinj.nonlinear.graph.direct;

import org.qinlinj.nonlinear.graph.Graph;

import java.util.*;

// @formatter:off
/**
 * GraphDFSR - An implementation of recursive Depth-First Search for graph traversal
 *
 * Concept and Principles:
 * - Depth-First Search (DFS) is an algorithm for traversing or searching tree or graph data structures
 * - It explores as far as possible along each branch before backtracking
 * - This implementation uses recursion, which naturally implements the DFS stack through the call stack
 * - For disconnected graphs, the algorithm ensures all vertices are visited by initiating DFS from each unvisited vertex
 *
 * Advantages:
 * - Time Complexity: O(V + E) where V is the number of vertices and E is the number of edges
 * - Space Complexity: O(V) for storing visited status and the implicit recursion stack
 * - Elegant implementation: Recursive approach is more concise and elegant
 * - Complete: Guaranteed to visit all vertices reachable from the starting vertices
 *
 * Visual Example:
 * Consider a graph:
 *    1 --- 2 --- 3
 *    |     |
 *    4 --- 5     6
 *
 * Recursive DFS traversal starting from vertex 1 might proceed as:
 * - Visit 1, mark as visited, add to result
 * - Recursively visit neighbor 2
 *   - Visit 2, mark as visited, add to result
 *   - Recursively visit neighbor 3
 *     - Visit 3, mark as visited, add to result
 *     - No unvisited neighbors, return from recursion
 *   - Return to vertex 2, no more unvisited neighbors, return from recursion
 * - Return to vertex 1, recursively visit neighbor 4
 *   - Visit 4, mark as visited, add to result
 *   - Recursively visit neighbor 5
 *     - Visit 5, mark as visited, add to result
 *     - No unvisited neighbors, return from recursion
 *   - Return to vertex 4, no more unvisited neighbors, return from recursion
 * - Return to vertex 1, no more unvisited neighbors, return from recursion
 * - Since vertex 6 is unvisited and disconnected, start a new DFS from 6
 *
 * Result: [1, 2, 3, 4, 5, 6]
 */
public class GraphDFSR {
    private Graph g;               // The graph to be traversed
    private List<Integer> res;     // Stores the DFS traversal result
    private boolean[] visited;     // Tracks visited vertices to prevent cycles

    /**
     * Constructor performs DFS traversal on the entire graph
     *
     * Time Complexity: O(V + E) where V is number of vertices and E is number of edges
     *
     * @param g The graph to traverse
     */
    public GraphDFSR(Graph g) {
        this.g = g;

        if (g == null) return;

        this.res = new ArrayList<>();
        this.visited = new boolean[g.getV()];

        // Iterate through all vertices to handle disconnected components
        for (int v = 0; v < g.getV(); v++) {
            // Only initiate DFS from unvisited vertices
            if (!visited[v]) {
                dfs(v);
            }
        }
    }

    /**
     * Main method to demonstrate recursive DFS traversal
     *
     * Example output:
     * - getRes() might return [0, 1, 3, 5, 2, 4, 6] depending on the graph structure
     */
    public static void main(String[] args) {
        Graph g = new GraphImpl("data/directedgraph-dfs.txt", true);
        GraphDFSR graphDFS = new GraphDFSR(g);
        System.out.println(graphDFS.getRes());
    }

    /**
     * Recursive implementation of DFS
     *
     * Time Complexity: O(V + E) for the component
     * Space Complexity: O(V) for the recursion stack in worst case
     *
     * Visual Example of recursive DFS execution:
     * For a small graph with vertices [0,1,2,3] where 0 connects to 1 and 2, and 2 connects to 3:
     *
     * Call stack and operations:
     * 1. dfs(0)
     *    - Add 0 to result: res = [0]
     *    - Mark 0 as visited: visited = [T,F,F,F]
     *    - For neighbors 1,2 of vertex 0:
     *      - Call dfs(1)
     *        - Add 1 to result: res = [0,1]
     *        - Mark 1 as visited: visited = [T,T,F,F]
     *        - No unvisited neighbors, return to dfs(0)
     *      - Call dfs(2)
     *        - Add 2 to result: res = [0,1,2]
     *        - Mark 2 as visited: visited = [T,T,T,F]
     *        - For neighbor 3 of vertex 2:
     *          - Call dfs(3)
     *            - Add 3 to result: res = [0,1,2,3]
     *            - Mark 3 as visited: visited = [T,T,T,T]
     *            - No unvisited neighbors, return to dfs(2)
     *        - No more unvisited neighbors, return to dfs(0)
     *    - No more unvisited neighbors, return
     *
     * Final result: [0,1,2,3]
     *
     * @param v The current vertex being visited
     */
    private void dfs(int v) {
        // Add the current vertex to result and mark as visited
        res.add(v);
        visited[v] = true;

        // Recursively visit all unvisited neighbors
        for (int w : g.adj(v)) {
            if (!visited[w]) {
                dfs(w);

                /* Visual representation of recursive calls:
                 * If v = 2 with neighbors [0,3]:
                 * - 0 is already visited, so skip
                 * - For neighbor 3, make recursive call dfs(3)
                 * - This pushes the current state onto the call stack
                 * - After dfs(3) completes, execution returns here
                 */
            }
        }

        // When we reach this point, all neighbors have been visited,
        // and we implicitly backtrack by returning to the previous call frame
    }

    /**
     * Returns vertices in DFS traversal order
     *
     * Time Complexity: O(1) - constant time return of pre-computed result
     *
     * @return List of vertices in DFS traversal order
     */
    public List<Integer> getRes() {
        return res;
    }
}
