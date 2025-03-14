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
