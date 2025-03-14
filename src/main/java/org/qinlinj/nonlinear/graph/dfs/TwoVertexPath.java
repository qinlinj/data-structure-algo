package org.qinlinj.nonlinear.graph.dfs;

import org.qinlinj.nonlinear.graph.AdjSet;
import org.qinlinj.nonlinear.graph.Graph;

import java.util.*;

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
