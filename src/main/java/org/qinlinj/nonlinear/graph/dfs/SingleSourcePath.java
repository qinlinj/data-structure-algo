package org.qinlinj.nonlinear.graph.dfs;

import org.qinlinj.nonlinear.graph.AdjSet;
import org.qinlinj.nonlinear.graph.Graph;

import java.util.*;

// @formatter:off
/**
 * SingleSourcePath - A class to find paths from a single source vertex to any other vertex using DFS.
 *
 * Concept and Principles:
 * - Single-source path finding determines paths from one source vertex to all other vertices in a graph
 * - This implementation uses DFS (Depth-First Search) traversal from the source vertex
 * - For each visited vertex, we maintain information about its parent/previous vertex
 * - This creates a "parent pointer tree" that can be used to reconstruct any path from source to destination
 *
 * Advantages:
 * - Simple implementation using DFS
 * - Memory efficient - only needs to store one previous vertex for each node
 * - Provides connectivity information (whether a path exists) as well as the actual path
 * - Can work well for sparse graphs or when you need only specific paths
 *
 * Limitations of DFS for path finding:
 * - Unlike BFS, DFS does not guarantee shortest paths in unweighted graphs
 * - DFS-based paths might be longer than necessary
 * - For shortest path finding in unweighted graphs, BFS is preferred
 *
 * Time Complexity: O(V + E) where V is number of vertices and E is number of edges
 * Space Complexity: O(V) for the visited array, prevs array, and recursion stack
 */
public class SingleSourcePath {
    // The graph to be processed
    private Graph g;

    // The source vertex from which paths will be computed
    private int source;

    // Track visited vertices
    private boolean[] visited;

    // Store the previous vertex in the path for each vertex
    private int[] prevs;

    /**
     * Constructor that initializes the path finding algorithm and computes paths.
     *
     * Visual Example:
     * Consider this graph with source vertex 0:
     *    0 --- 1 --- 3
     *    |     |     |
     *    2 --- 4 --- 5
     *           \
     *            6
     *
     * After DFS traversal:
     * - visited array = [T, T, T, T, T, T, T] (all vertices are visited)
     * - prevs array = [0, 0, 0, 1, 1, 4, 4] (showing parent pointers)
     * - This means:
     *   * 0's parent is 0 (itself)
     *   * 1's parent is 0
     *   * 2's parent is 0
     *   * 3's parent is 1
     *   * 4's parent is 1
     *   * 5's parent is 4
     *   * 6's parent is 4
     *
     * Path from 0 to 6 would be: 0 -> 1 -> 4 -> 6
     * (Reconstructed by following parent pointers backward)
     *
     * @param g The input graph
     * @param source The source vertex to find paths from
     *
     * Time Complexity: O(V + E) for the DFS traversal
     * Space Complexity: O(V) for the visited and prevs arrays, plus recursion stack
     */
    public SingleSourcePath(Graph g, int source) {
        this.g = g;
        this.source = source;

        this.visited = new boolean[g.getV()];

        // Initialize the prevs array to store each vertex's parent in the path
        prevs = new int[g.getV()];

        // Initialize all parent pointers to -1 (no parent yet)
        for (int i = 0; i < g.getV(); i++) {
            prevs[i] = -1;
        }

        // Start DFS from the source vertex
        // The source vertex's parent is set to itself
        dfs(source, source);
    }

    public static void main(String[] args) {
        Graph g = new AdjSet("data/graph-bfs.txt");
        SingleSourcePath graphDFS = new SingleSourcePath(g, 0);
        System.out.println(graphDFS.path(6));
    }

    /**
     * Depth-First Search traversal to build the parent pointer tree.
     *
     * Visual Example for dfs(0, 0) with the graph:
     *    0 --- 1 --- 3
     *    |     |     |
     *    2 --- 4 --- 5
     *           \
     *            6
     *
     * DFS execution:
     * 1. Mark vertex 0 as visited, set prevs[0] = 0
     * 2. Visit neighbors of 0 (1 and 2):
     *    - For neighbor 1:
     *      - Mark 1 as visited, set prevs[1] = 0
     *      - Visit neighbors of 1 (0, 3, 4):
     *        - 0 is already visited, skip it
     *        - For neighbor 3:
     *          - Mark 3 as visited, set prevs[3] = 1
     *          - Visit neighbors of 3 (1, 5):
     *            - 1 is already visited, skip it
     *            - For neighbor 5: mark as visited, set prevs[5] = 3
     *              - (... continues with 5's neighbors)
     *        - For neighbor 4:
     *          - Mark 4 as visited, set prevs[4] = 1
     *          - (... continues with 4's neighbors)
     *    - For neighbor 2:
     *      - (... similarly process vertex 2)
     *
     * @param v Current vertex being processed
     * @param prev Previous vertex (parent) in the DFS traversal
     *
     * Time Complexity: O(V + E) for vertices and edges in the traversal
     * Space Complexity: O(V) due to recursion stack in worst case
     */
    private void dfs(int v, int prev) {
        visited[v] = true;

        // Set the parent/previous vertex for v
        prevs[v] = prev;

        // Explore all neighbors of v
        for (int w : g.adj(v)) {
            if (!visited[w]) {
                // For unvisited neighbors, continue DFS with v as their parent
                dfs(w, v);
            }
        }
    }

    public boolean isConnected(int target) {
        validateVertex(target);
        return visited[target];
    }

    private void validateVertex(int v) {
        if (v < 0 && v >= g.getV()) {
            throw new IllegalArgumentException("error");
        }
    }

    public List<Integer> path(int target) {
        List<Integer> res = new ArrayList<>();
        if (!isConnected(target)) {
            return res;
        }
        while (target != source) {
            res.add(target);
            target = prevs[target];
        }
        res.add(source);

        Collections.reverse(res);

        return res;
    }
}
