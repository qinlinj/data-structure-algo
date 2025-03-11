package org.qinlinj.nonlinear.graph.dfs;

import org.qinlinj.nonlinear.graph.AdjSet;
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
        for (int v = 0; v < g.getV(); v++) {
            if (!visited[v]) {
                dfs(v);
            }
        }
    }

    public static void main(String[] args) {
        Graph g = new AdjSet("data/graph-dfs.txt");
        GraphDFSR graphDFS = new GraphDFSR(g);
        System.out.println(graphDFS.getRes());
    }

    private void dfs(int v) {
        res.add(v);
        visited[v] = true;
        for (int w : g.adj(v)) {
            if (!visited[w]) {
                dfs(w);
            }
        }
    }

    public List<Integer> getRes() {
        return res;
    }
}
