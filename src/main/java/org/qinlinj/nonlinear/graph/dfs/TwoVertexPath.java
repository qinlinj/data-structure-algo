package org.qinlinj.nonlinear.graph.dfs;

import org.qinlinj.nonlinear.graph.AdjSet;
import org.qinlinj.nonlinear.graph.Graph;

import java.util.*;

// @formatter:off
/**
 * TwoVertexPath - A class to find a path between two specific vertices in a graph using DFS.
 *
 * Concept and Principles:
 * - This class finds a path between a specified source vertex and a target vertex
 * - It uses a modified DFS approach that stops early once the target is found
 * - For each visited vertex, we maintain information about its parent/previous vertex
 * - After finding the target, we reconstruct the path using parent pointers
 *
 * Advantages:
 * - More efficient than single-source path when only one specific path is needed
 * - Early termination once target is found, saving computation time
 * - Avoids exploring unnecessary parts of the graph
 * - Uses DFS with a boolean return value to signal when target is found
 *
 * Key differences from SingleSourcePath:
 * - Only explores the graph until target is found, then stops
 * - Returns a boolean from DFS to indicate if target was found
 * - Pre-computes and stores the path in constructor
 *
 * Time Complexity: O(V + E) in worst case, but can be better if target is found early
 * Space Complexity: O(V) for the visited array, prevs array, and recursion stack
 */
public class TwoVertexPath {
    // The graph to be processed
    private Graph g;

    // Source and target vertices
    private int source;
    private int target;

    // Track visited vertices
    private boolean[] visited;

    // Store the previous vertex in the path for each vertex
    private int[] prevs;

    // Store the computed path from source to target
    private List<Integer> res;

    /**
     * Constructor that initializes the two-vertex path finding algorithm and computes the path.
     *
     * Visual Example:
     * Consider this graph with source=0 and target=6:
     *    0 --- 1 --- 3
     *    |     |     |
     *    2 --- 4 --- 5
     *           \
     *            6
     *
     * DFS will proceed from 0 until it reaches 6, then stop.
     * The path is computed once and stored in the res list.
     *
     * @param g The input graph
     * @param source The source vertex to find path from
     * @param target The target vertex to find path to
     *
     * Time Complexity: O(V + E) in worst case, but typically better due to early termination
     * Space Complexity: O(V) for the visited and prevs arrays, plus recursion stack
     */
    public TwoVertexPath(Graph g, int source, int target) {
        this.g = g;
        this.source = source;
        this.target = target;

        this.res = new ArrayList<>();

        this.visited = new boolean[g.getV()];
        prevs = new int[g.getV()];

        // Initialize all parent pointers to -1 (no parent yet)
        for (int i = 0; i < g.getV(); i++) {
            prevs[i] = -1;
        }

        // Start DFS from the source vertex
        // Source vertex's parent is set to itself
        dfs(source, source);

        // Compute the path once and store it
        path();
    }

    /**
     * Main method to demonstrate the TwoVertexPath algorithm.
     *
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        // Create a graph from file
        Graph g = new AdjSet("data/graph-dfs.txt");

        // Find path from vertex 0 to vertex 6
        TwoVertexPath graphDFS = new TwoVertexPath(g, 0, 6);

        // Output the path
        System.out.println(graphDFS.getRes());
    }

    /**
     * Modified Depth-First Search traversal that stops once the target is found.
     *
     * Visual Example for dfs(0, 0) with target=6:
     *    0 --- 1 --- 3
     *    |     |     |
     *    2 --- 4 --- 5
     *           \
     *            6
     *
     * DFS execution:
     * 1. Start at vertex 0, mark as visited, set prevs[0] = 0
     * 2. Visit neighbor 1:
     *    - Mark 1 as visited, set prevs[1] = 0
     *    - Visit neighbor 4:
     *      - Mark 4 as visited, set prevs[4] = 1
     *      - Visit neighbor 6 (target):
     *        - Mark 6 as visited, set prevs[6] = 4
     *        - Since v == target, return true
     *      - dfs(6, 4) returns true, so dfs(4, 1) returns true
     *    - dfs(4, 1) returns true, so dfs(1, 0) returns true
     * 3. dfs(1, 0) returns true, so dfs(0, 0) returns true
     * 4. Early termination: no need to explore other paths
     *
     * @param v Current vertex being processed
     * @param prev Previous vertex (parent) in the DFS traversal
     * @return true if target is found in this branch, false otherwise
     *
     * Time Complexity: O(V + E) in worst case, but typically better
     * Space Complexity: O(V) due to recursion stack in worst case
     */
    private boolean dfs(int v, int prev) {
        visited[v] = true;

        // Set the parent/previous vertex for v
        prevs[v] = prev;

        // If target is found, return true immediately
        if (v == target) return true;

        // Explore all neighbors of v
        for (int w : g.adj(v)) {
            if (!visited[w]) {
                // For unvisited neighbors, continue DFS with v as their parent
                // If target is found in this branch, return true immediately
                if (dfs(w, v)) return true;
            }
        }

        // Target not found in this branch
        return false;
    }

    /**
     * Checks if there is a path from the source vertex to the target vertex.
     *
     * @return true if there is a path from source to target, false otherwise
     *
     * Time Complexity: O(1) - constant time check of the visited array
     * Space Complexity: O(1) - no additional space used
     */
    public boolean isConnected() {
        validateVertex(target);
        return visited[target];
    }

    /**
     * Validates if a vertex is within the valid range for the graph.
     *
     * @param v The vertex to validate
     * @throws IllegalArgumentException if vertex is outside valid range
     *
     * Time Complexity: O(1) - constant time operation
     * Space Complexity: O(1) - no additional space used
     */
    private void validateVertex(int v) {
        if (v < 0 || v >= g.getV()) {  // Fixed logical condition from '&&' to '||'
            throw new IllegalArgumentException("Vertex is invalid, out of range");
        }
    }

    /**
     * Computes the path from source to target using the parent pointers.
     * This method is called once in the constructor and the result is stored.
     *
     * Visual Example:
     * For source=0 and target=6, with prevs=[0,0,0,1,1,4,4]:
     *
     * Step 1: Check if connected (visited[6] = true)
     * Step 2: Reconstruct path by following parent pointers backward
     *   - Start with tmp = 6
     *   - Add 6 to path
     *   - tmp = prevs[6] = 4
     *   - Add 4 to path
     *   - tmp = prevs[4] = 1
     *   - Add 1 to path
     *   - tmp = prevs[1] = 0
     *   - Add 0 to path
     *   - Now tmp == source, so stop
     * Step 3: Reverse path (currently [6, 4, 1, 0]) to get [0, 1, 4, 6]
     *
     * @return List of vertices representing the path from source to target,
     *         or an empty list if no path exists
     *
     * Time Complexity: O(V) in worst case (if path includes all vertices)
     * Space Complexity: O(V) for storing the path
     */
    private List<Integer> path() {
        // 1. If there's no path from source to target, return empty list
        if (!isConnected()) {
            return res;
        }

        // 2. Reconstruct path by following parent pointers backward from target
        int tmp = target;
        while (tmp != source) {
            res.add(tmp);
            tmp = prevs[tmp];  // Move to the parent vertex
        }
        res.add(source);  // Add the source vertex

        // 3. Reverse the path to get correct order (source to target)
        Collections.reverse(res);

        return res;
    }

    /**
     * Returns the pre-computed path from source to target.
     *
     * @return List of vertices representing the path from source to target,
     *         or an empty list if no path exists
     *
     * Time Complexity: O(1) - constant time access to stored result
     * Space Complexity: O(1) - no additional space used
     */
    public List<Integer> getRes() {
        return res;
    }
}