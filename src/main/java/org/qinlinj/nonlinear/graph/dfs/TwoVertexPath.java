package org.qinlinj.nonlinear.graph.dfs;

import org.qinlinj.nonlinear.graph.AdjSet;
import org.qinlinj.nonlinear.graph.Graph;

import java.util.*;

// @formatter:off
/**
 * TwoVertexPath - A class to find a path between two specific vertices in a graph using DFS.
 * <p>
 * Concept and Principles:
 * - This class finds a path between a specified source vertex and a target vertex
 * - It uses a modified DFS approach that stops early once the target is found
 * - For each visited vertex, we maintain information about its parent/previous vertex
 * - After finding the target, we reconstruct the path using parent pointers
 * <p>
 * Advantages:
 * - More efficient than single-source path when only one specific path is needed
 * - Early termination once target is found, saving computation time
 * - Avoids exploring unnecessary parts of the graph
 * - Uses DFS with a boolean return value to signal when target is found
 * <p>
 * Key differences from SingleSourcePath:
 * - Only explores the graph until target is found, then stops
 * - Returns a boolean from DFS to indicate if target was found
 * - Pre-computes and stores the path in constructor
 * <p>
 * Time Complexity: O(V + E) in worst case, but can be better if target is found early
 * Space Complexity: O(V) for the visited array, prevs array, and recursion stack
 */
public class TwoVertexPath {
    private Graph g;

    private int source;
    private int target;

    private boolean[] visited;
    private int[] prevs;

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

        for (int i = 0; i < g.getV(); i++) {
            prevs[i] = -1;
        }

        dfs(source, source);

        path();
    }

    public static void main(String[] args) {
        Graph g = new AdjSet("data/graph-dfs.txt");
        TwoVertexPath graphDFS = new TwoVertexPath(g, 0, 6);
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
        prevs[v] = prev;
        if (v == target) return true;
        for (int w : g.adj(v)) {
            if (!visited[w]) {
                if (dfs(w, v)) return true;
            }
        }
        return false;
    }

    public boolean isConnected() {
        validateVertex(target);
        return visited[target];
    }

    private void validateVertex(int v) {
        if (v < 0 && v >= g.getV()) {
            throw new IllegalArgumentException("error");
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
        if (!isConnected()) {
            return res;
        }

        int tmp = target;
        while (tmp != source) {
            res.add(tmp);
            tmp = prevs[tmp];
        }
        res.add(source);

        Collections.reverse(res);

        return res;
    }

    public List<Integer> getRes() {
        return res;
    }
}
