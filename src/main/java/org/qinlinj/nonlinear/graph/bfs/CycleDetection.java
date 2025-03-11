package org.qinlinj.nonlinear.graph.bfs;

import org.qinlinj.nonlinear.graph.AdjSet;
import org.qinlinj.nonlinear.graph.Graph;

import java.util.*;

// @formatter:off
/**
 * CycleDetection - An implementation to detect cycles in an undirected graph using BFS
 * <p>
 * Concept and Principles:
 * - A cycle in a graph is a path that starts and ends at the same vertex
 * - This algorithm uses Breadth-First Search to traverse the graph and identify cycles
 * - If during BFS traversal, we encounter an already visited vertex that is not the parent of the current vertex, a cycle exists
 * - For disconnected graphs, we need to run BFS from each unvisited vertex to ensure complete coverage
 * <p>
 * Advantages:
 * - Time Complexity: O(V + E) where V is the number of vertices and E is the number of edges
 * - Handles disconnected graphs: Runs BFS from each unvisited vertex
 * - Early termination: Stops as soon as a cycle is detected
 * - Simple implementation: Uses standard BFS with minor modifications
 * <p>
 * Visual Example:
 * Consider a graph with cycle:
 *    a --- b
 *    |     |
 *    c --- d
 * <p>
 * BFS traversal starting from 'a' would detect the cycle because when exploring neighbors of 'd',
 * we would discover 'a' has already been visited and is not the direct parent of 'd'.
 */
public class CycleDetection {
    private Graph g;               // The graph to be analyzed
    private boolean[] visited;     // Track visited vertices
    private int[] prevs;           // Store the previous vertex for each vertex

    private boolean hasCycle = false;  // Flag indicating if cycle was detected

    /**
     * Constructor runs the cycle detection algorithm on the graph
     * <p>
     * Time Complexity: O(V + E) where V is number of vertices and E is number of edges
     *
     * @param g The graph to analyze for cycles
     */
    public CycleDetection(Graph g) {
        this.g = g;
        this.visited = new boolean[g.getV()];
        this.prevs = new int[g.getV()];
        Arrays.fill(this.prevs, -1);  // Initialize prevs with -1 (no predecessor)

        // For disconnected graphs, run BFS from each unvisited vertex
        for (int v = 0; v < g.getV(); v++) {
            if (!visited[v]) {
                if (bfs(v)) {
                    hasCycle = true;
                    break;
                }
            }
        }
    }

    /**
     * Main method to demonstrate the cycle detection algorithm
     * <p>
     * Example output:
     * - hasCycle() might output true if the graph contains a cycle
     */
    public static void main(String[] args) {
        Graph g = new AdjSet("data/graph-bfs.txt");
        CycleDetection graphBFS = new CycleDetection(g);
        System.out.println(graphBFS.hasCycle());
    }

    /**
     * Modified BFS implementation that detects cycles
     * <p>
     * Time Complexity: O(V + E) where V is number of vertices and E is number of edges
     * <p>
     * Visual Example for cycle detection using BFS:
     * Consider this small graph:
     *    a --- b
     *     \   /
     *       c
     * <p>
     * Initial state (starting BFS from vertex 'a'):
     * - visited = [T,F,F] (assuming a=0, b=1, c=2)
     * - prevs = [0,-1,-1] (a is its own predecessor)
     * - queue = [a]
     * <p>
     * After processing vertex 'a':
     * - Neighbors 'b' and 'c' are added to queue
     * - visited = [T,T,T]
     * - prevs = [0,0,0] (both b and c have a as predecessor)
     * - queue = [b,c]
     * <p>
     * Processing vertex 'b':
     * - Looking at neighbor 'a': already visited but it's prevs[b], so no cycle
     * - Looking at neighbor 'c': already visited and NOT prevs[b], so cycle detected!
     * This is because we've found a path a->b->c and also a->c
     *
     * @param v The source vertex to start BFS from
     * @return true if a cycle is detected, false otherwise
     */
    private boolean bfs(int v) {
        if (g == null) return false;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(v);
        visited[v] = true;
        // Set the starting vertex as its own predecessor
        prevs[v] = v;

        while (!queue.isEmpty()) {
            int curr = queue.poll();

            for (int w : g.adj(curr)) {
                if (!visited[w]) {
                    // Standard BFS processing for unvisited vertices
                    queue.add(w);
                    visited[w] = true;
                    // Record the previous vertex
                    prevs[w] = curr;
                } else {
                    // Cycle detection logic: if we reach a visited vertex that is not the parent
                    // of the current vertex, we have found a cycle
                    /**
                     * Visual explanation of cycle detection:
                     *
                     *    a --- b
                     *     \   /
                     *       c
                     *
                     * Assume we first visit vertex 'a' (setting visited[a] = true),
                     * then neighbors 'b' and 'c' are both added to the queue
                     * and we set prevs[b] = a and prevs[c] = a, with visited[b] = true and visited[c] = true
                     *
                     * Later, when we process vertex 'b', its neighbors are 'a' and 'c':
                     *   - For neighbor 'a': curr = b, w = a, and visited[w] = true, but w = prevs[curr]
                     *     This is our parent, so we ignore it (not a cycle)
                     *   - For neighbor 'c': curr = b, w = c, and visited[w] = true, but w != prevs[curr]
                     *     This means we've found a vertex that was already visited through another path
                     *     This indicates a cycle!
                     *
                     * The key insight: in BFS, if we can reach the same vertex through two different paths,
                     * then there must be a cycle in the graph.
                     * In this case, vertex 'c' can be reached both via a->c and via a->b->c
                     */
                    if (w != prevs[curr]) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Returns whether the graph contains a cycle
     * <p>
     * Time Complexity: O(1) - constant time lookup of pre-computed result
     *
     * @return true if the graph contains at least one cycle, false otherwise
     */
    public boolean hasCycle() {
        return hasCycle;
    }
}