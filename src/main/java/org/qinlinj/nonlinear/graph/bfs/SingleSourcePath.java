package org.qinlinj.nonlinear.graph.bfs;

import org.qinlinj.nonlinear.graph.AdjSet;
import org.qinlinj.nonlinear.graph.Graph;

import java.util.*;

/**
 * SingleSourcePath - An implementation to find a path from a source vertex to any other vertex in a graph using BFS
 * <p>
 * Concept and Principles:
 * - This algorithm uses Breadth-First Search to find a path between two vertices in an unweighted graph
 * - Unlike SingleSourceShortestPath, this class only focuses on finding a valid path, not necessarily the shortest
 * - However, since BFS is used, the found path will be one of the shortest possible paths in an unweighted graph
 * <p>
 * Advantages:
 * - Time Complexity: O(V + E) where V is the number of vertices and E is the number of edges
 * - Memory Efficiency: Only stores the path information without distance tracking
 * - Simple Implementation: Uses a queue-based BFS traversal for intuitive path finding
 * - Complete: Guaranteed to find a path if one exists
 * <p>
 * Visual Example:
 * Consider a graph with 7 vertices (0-6) connected as follows:
 * 0 -- 1 -- 3 -- 5
 * |    |    |
 * 2 -- 4 -- 6
 * <p>
 * Running BFS from source=0:
 * - First visit vertex 0
 * - Then visit vertices 1,2 (neighbors of 0)
 * - Then visit vertices 3,4 (neighbors of 1,2)
 * - Finally visit vertices 5,6 (neighbors of 3,4)
 * <p>
 * For a path from 0 to 6, we would discover: 0 -> 1 -> 3 -> 6 or 0 -> 2 -> 4 -> 6
 * With the prevs array tracking the discovery: prevs[6] = 3, prevs[3] = 1, prevs[1] = 0
 */
public class SingleSourcePath {
    private Graph g;               // The graph to be traversed
    private boolean[] visited;     // Track visited vertices
    private int[] prevs;           // Store the previous vertex in the path

    private int source;            // Source vertex for path finding

    /**
     * Constructor initializes the algorithm and runs BFS
     * <p>
     * Time Complexity: O(V + E) - dominated by the BFS algorithm
     *
     * @param g      The graph to traverse
     * @param source The source vertex for finding paths
     */
    public SingleSourcePath(Graph g, int source) {
        this.g = g;
        this.source = source;
        this.visited = new boolean[g.getV()];
        this.prevs = new int[g.getV()];
        Arrays.fill(this.prevs, -1);  // Initialize prevs with -1 (no predecessor)

        bfs(source);  // Run BFS from the source vertex
    }

    /**
     * Main method to demonstrate the algorithm
     * <p>
     * Example output:
     * - path(6) might output [0, 1, 3, 6] showing a path from 0 to 6
     */
    public static void main(String[] args) {
        Graph g = new AdjSet("data/graph-bfs.txt");
        SingleSourcePath graphBFS = new SingleSourcePath(g, 0);
        System.out.println(graphBFS.path(6));
    }

    /**
     * Breadth-First Search implementation to find paths
     * <p>
     * Time Complexity: O(V + E) where V is number of vertices and E is number of edges
     * <p>
     * Visual Example for BFS on a graph with source=0:
     * <p>
     * Initial state:
     * - visited = [F,F,F,F,F,F,F]
     * - prevs = [-1,-1,-1,-1,-1,-1,-1]
     * - queue = [0]
     * <p>
     * After processing vertex 0:
     * - visited = [T,F,F,F,F,F,F]
     * - prevs = [0,-1,-1,-1,-1,-1,-1]
     * - queue = [1,2]  (neighbors of 0)
     * <p>
     * After processing vertex 1:
     * - visited = [T,T,F,F,F,F,F]
     * - prevs = [0,0,-1,-1,-1,-1,-1]
     * - queue = [2,3,4]  (added neighbors of 1)
     * <p>
     * And so on until all reachable vertices are visited
     *
     * @param v The source vertex to start BFS from
     */
    private void bfs(int v) {
        if (g == null) return;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(v);
        visited[v] = true;
        // Set source as its own predecessor
        prevs[v] = v;

        while (!queue.isEmpty()) {
            int curr = queue.poll();

            for (int w : g.adj(curr)) {
                if (!visited[w]) {
                    queue.add(w);
                    visited[w] = true;
                    // Record the previous vertex in the path
                    prevs[w] = curr;

                    /* Visual Example of BFS Step:
                     * If curr = 1 and w = 3:
                     * - Mark 3 as visited
                     * - Set prevs[3] = 1 (showing the path from source to 3 goes through 1)
                     * - Queue now contains vertex 3 for future exploration
                     */
                }
            }
        }
    }

    /**
     * Checks if there is a path from the source to the target vertex
     * <p>
     * Time Complexity: O(1) - constant time lookup
     *
     * @param target The vertex to check connectivity to
     * @return true if target is reachable from source, false otherwise
     */
    public boolean isConnected(int target) {
        validateVertex(target);
        return visited[target];
    }

    /**
     * Validates that a vertex is within the graph's bounds
     * <p>
     * Time Complexity: O(1) - constant time operation
     *
     * @param v The vertex to validate
     * @throws IllegalArgumentException if vertex is out of bounds
     */
    private void validateVertex(int v) {
        if (v < 0 || v >= g.getV()) {  // Fixed the condition from && to ||
            throw new IllegalArgumentException("Vertex is invalid, out of range");
        }
    }

    /**
     * Finds a path from source to target
     * <p>
     * Time Complexity: O(V) in worst case (path could include all vertices)
     * <p>
     * Visual Example:
     * For a path from 0 to 6 in our example graph:
     * - Start with target = 6
     * - prevs[6] might be 3, so add 6 to path and set target = 3
     * - prevs[3] might be 1, so add 3 to path and set target = 1
     * - prevs[1] = 0, so add 1 to path and set target = 0
     * - target == source, so add 0 to path
     * - After reversing: path = [0,1,3,6]
     *
     * @param target The destination vertex
     * @return List of vertices forming a path from source to target, or empty if no path exists
     */
    public List<Integer> path(int target) {
        List<Integer> res = new ArrayList<>();
        // 1. If there's no path from source to target, return empty list
        if (!isConnected(target)) {
            return res;
        }
        // 2. Reconstruct the path by following prevs backward from target
        while (target != source) {
            res.add(target);
            target = prevs[target];
        }
        res.add(source);

        // 3. Reverse the path to get correct order from source to target
        Collections.reverse(res);

        return res;
    }
}

