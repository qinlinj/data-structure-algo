package org.qinlinj.nonlinear.graph.bfs;

import org.qinlinj.nonlinear.graph.AdjSet;
import org.qinlinj.nonlinear.graph.Graph;

import java.util.*;

// @formatter:off
/**
 * TargetedSingleSourcePath - An optimized implementation to find a path between specific source and target vertices
 * <p>
 * Concept and Principles:
 * - This algorithm uses a modified Breadth-First Search that terminates early when the target is found
 * - Unlike the general SingleSourcePath, this implementation is optimized for finding a path to a specific target
 * - The BFS traversal stops immediately when the target vertex is encountered, avoiding unnecessary exploration
 * <p>
 * Advantages:
 * - Time Efficiency: Can be significantly faster than regular BFS when the target is found early
 * - Early Termination: Stops processing once the target is found, saving computational resources
 * - Memory Efficiency: Only processes vertices necessary to reach the target
 * - Optimized for Single Target: Perfect for when only one specific destination is needed, not all possible paths
 * <p>
 * Key Differences from SingleSourcePath:
 * - Target vertex is specified at initialization
 * - BFS terminates immediately when target is found
 * - Path and connectivity methods don't require target parameter (already stored in class)
 * <p>
 * Visual Example:
 * Consider a graph with 7 vertices (0-6) connected as follows:
 *    0 -- 1 -- 3 -- 5
 *    |    |    |
 *    2 -- 4 -- 6
 * <p>
 * Running targeted BFS from source=0 to target=6:
 * - First visit vertex 0
 * - Then visit vertices 1,2 (neighbors of 0)
 * - Then visit vertices 3,4 (neighbors of 1,2)
 * - Find vertex 6 (neighbor of 3) and terminate immediately
 * - Vertex 5 would never be explored, saving computational work
 */
public class TargetedSingleSourcePath {
    private Graph g;               // The graph to be traversed
    private boolean[] visited;     // Track visited vertices
    private int[] prevs;           // Store the previous vertex in the path

    private int source;            // Source vertex for path finding
    private int target;            // Target vertex for path finding

    /**
     * Constructor initializes the algorithm with specific source and target, then runs targeted BFS
     * <p>
     * Time Complexity: O(V + E) worst case, but usually better due to early termination
     *
     * @param g      The graph to traverse
     * @param source The source vertex for finding path
     * @param target The target vertex to find path to
     */
    public TargetedSingleSourcePath(Graph g, int source, int target) {
        this.g = g;
        this.source = source;
        this.target = target;
        this.visited = new boolean[g.getV()];
        this.prevs = new int[g.getV()];
        Arrays.fill(this.prevs, -1);  // Initialize prevs with -1 (no predecessor)

        bfs(source);  // Run targeted BFS from the source vertex
    }

    /**
     * Main method to demonstrate the algorithm
     * <p>
     * Example output:
     * - path() might output [0, 1, 3, 6] showing the path from 0 to 6
     */
    public static void main(String[] args) {
        Graph g = new AdjSet("data/graph-bfs.txt");
        TargetedSingleSourcePath graphBFS = new TargetedSingleSourcePath(g, 0, 6);
        System.out.println(graphBFS.path());
    }

    /**
     * Targeted Breadth-First Search implementation that stops when target is found
     * <p>
     * Time Complexity: O(V + E) in worst case, but typically much better
     * <p>
     * Visual Example for targeted BFS from source=0 to target=6:
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
     * After processing vertices 1 and 2:
     * - visited = [T,T,T,F,F,F,F]
     * - prevs = [0,0,0,-1,-1,-1,-1]
     * - queue = [3,4]  (neighbors of 1,2)
     * <p>
     * While processing vertex 3, we find 6 in its adjacency list:
     * - Mark 6 as visited, set prevs[6] = 3
     * - Then terminate BFS immediately
     * - vertices 4, 5 may never be processed
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

            // Early termination: If current vertex is the target, stop BFS
            if (curr == target) {
                return;
            }

            for (int w : g.adj(curr)) {
                if (!visited[w]) {
                    queue.add(w);
                    visited[w] = true;
                    // Record the previous vertex in the path
                    prevs[w] = curr;

                    /* Visual Example of Optimization:
                     * If target = 6 and we just processed w = 6:
                     * - Mark 6 as visited
                     * - Set prevs[6] = curr
                     * - On next iteration, curr = 6 will match target
                     * - BFS will terminate early
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
     * @return true if target is reachable from source, false otherwise
     */
    public boolean isConnected() {
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
     * Reconstructs the path from source to the pre-specified target
     * <p>
     * Time Complexity: O(V) in worst case (path could include all vertices)
     * <p>
     * Visual Example:
     * For a path from 0 to 6 in our example graph:
     * - Start with target = 6
     * - prevs[6] = 3, so add 6 to path and set tmp = 3
     * - prevs[3] = 1, so add 3 to path and set tmp = 1
     * - prevs[1] = 0, so add 1 to path and set tmp = 0
     * - tmp == source, so add 0 to path
     * - After reversing: path = [0,1,3,6]
     *
     * @return List of vertices forming the path from source to target, or empty if no path exists
     */
    public List<Integer> path() {
        List<Integer> res = new ArrayList<>();
        // 1. If there's no path from source to target, return empty list
        if (!isConnected()) {
            return res;
        }
        // 2. Reconstruct the path by following prevs backward from target
        int tmp = target;
        while (tmp != source) {
            res.add(tmp);
            tmp = prevs[tmp];
        }
        res.add(source);

        // 3. Reverse the path to get correct order from source to target
        Collections.reverse(res);

        return res;
    }
}