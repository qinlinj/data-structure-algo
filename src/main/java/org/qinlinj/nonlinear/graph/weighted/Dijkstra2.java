package org.qinlinj.nonlinear.graph.weighted;

import java.util.*;

// @formatter:off
/**
 * Dijkstra's Algorithm with Path Reconstruction Implementation (Dijkstra2)
 *
 * Concept and Principles:
 * This is an enhanced version of the optimized Dijkstra algorithm that not only calculates
 * the shortest distances but also tracks the shortest path tree, allowing for path reconstruction.
 * It maintains a "predecessor" array that stores the previous vertex in the shortest path for each vertex.
 *
 * Key Enhancements Compared to Dijkstra1:
 * 1. Path Tracking: Maintains a predecessors array (prevs) to record the shortest path tree
 *    - For each vertex, stores which vertex comes before it on the shortest path from source
 *    - Enables reconstruction of the complete shortest path from source to any reachable vertex
 * 2. Path Reconstruction: Provides a method to retrieve the actual path (sequence of vertices)
 *    - Traverses the predecessor array backwards from target to source
 *    - Returns the sequence of vertices that forms the shortest path
 * 3. Connectivity Check: Allows checking if a vertex is reachable from the source
 *    - Uses the visited array to determine if a path exists
 *
 * Other Optimizations (inherited from Dijkstra1):
 * 1. Priority Queue for efficient minimum distance vertex extraction
 * 2. Lazy deletion approach for already processed vertices
 * 3. On-demand distance updates
 *
 * Algorithm Steps:
 * 1. Initialize distances to infinity (except source), predecessors to -1, and all vertices as unvisited
 * 2. Add source to priority queue with distance 0
 * 3. While priority queue is not empty:
 *    a. Extract vertex with minimum distance
 *    b. Skip if already processed (lazy deletion)
 *    c. Mark vertex as visited
 *    d. For each adjacent unvisited vertex:
 *       i. If a shorter path is found, update distance
 *       ii. Set predecessor to current vertex
 *       iii. Add to priority queue with new distance
 * 4. Path reconstruction: Follow predecessors array backwards from target to source
 *
 * Detailed Example Visualization:
 * Using the same graph as before:
 *
 *     A --- 4 --- B
 *     |           |
 *     2           3
 *     |           |
 *     C --- 1 --- D --- 2 --- E
 *     |                       |
 *     3                       1
 *     |                       |
 *     F ---------- 5 --------- G
 *
 * Path reconstruction example: Finding path from A to G
 *
 * After Dijkstra's algorithm completes, we have:
 * - distance array: [A:0, B:4, C:2, D:3, E:5, F:5, G:6]
 * - prevs array:    [A:-, B:A, C:A, D:C, E:D, F:C, G:E]
 *   (where prevs[v] indicates the predecessor of vertex v in the shortest path)
 *
 * To reconstruct the path from A to G:
 * 1. Start at target G, add G to path
 * 2. prevs[G] = E, add E to path
 * 3. prevs[E] = D, add D to path
 * 4. prevs[D] = C, add C to path
 * 5. prevs[C] = A, add A to path
 * 6. A is the source, stop
 * 7. Reverse the path: [A, C, D, E, G]
 *
 * This gives us the complete shortest path from A to G: A → C → D → E → G
 *
 * The corresponding weights are:
 * - A to C: 2
 * - C to D: 1
 * - D to E: 2
 * - E to G: 1
 * Total: 6 (matches the distance[G] value)
 *
 * Time Complexity: O((V+E) log V)
 * - Same as Dijkstra1 since the path reconstruction overhead is minimal
 * - Path reconstruction for a specific target is O(P) where P is the path length (at most V)
 *
 * Space Complexity: O(V)
 * - Additional O(V) space for the predecessors array
 */
public class Dijkstra2 {
    private WeightedAdjSet g;        // The weighted graph
    private int source;              // Source vertex for shortest paths
    private int[] distance;          // Array to store shortest distance from source to each vertex
    private boolean[] visited;       // Array to track visited vertices
    private int[] prevs;             // Array to store predecessor vertices (for path reconstruction)

    /**
     * Constructs a Dijkstra shortest path calculator with path reconstruction capability.
     *
     * @param g The weighted graph
     * @param source The source vertex from which to compute shortest paths
     *
     * Time Complexity: O((V+E) log V) where V is the number of vertices and E is the number of edges
     *   - Same as Dijkstra1, with minimal overhead for tracking predecessors
     *
     * Space Complexity: O(V) for the distance, visited, and predecessors arrays, plus O(V) for the priority queue
     */
    public Dijkstra2(WeightedAdjSet g, int source) {
        this.g = g;
        this.source = source;

        // Initialize distance array with infinity for all vertices except source
        distance = new int[g.getV()];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[source] = 0;  // Distance from source to itself is 0

        // Initialize visited array to track processed vertices
        visited = new boolean[g.getV()];

        // Initialize predecessors array to store the previous vertex in the shortest path
        prevs = new int[g.getV()];
        Arrays.fill(prevs, -1);  // -1 indicates no predecessor (unreachable or source)

        // Priority queue to efficiently get the vertex with minimum distance
        PriorityQueue<Pair> pq = new PriorityQueue<>();

        // Add the source vertex to the priority queue with distance 0
        pq.add(new Pair(source, 0));

        // Main Dijkstra algorithm loop with priority queue optimization
        while (!pq.isEmpty()) {  // O(V) iterations total
            // Extract vertex with minimum distance from priority queue
            int curr = pq.poll().v;  // O(log V)

            // Skip if already processed (lazy deletion approach)
            if (visited[curr]) continue;

            // Mark the current vertex as visited
            visited[curr] = true;

            // Relaxation step: Update distances to adjacent vertices
            for (int w : g.adj(curr)) {  // O(E) total across all iterations
                if (!visited[w]) {
                    // If a shorter path is found, update the distance
                    if (distance[curr] + g.getWeight(curr, w) < distance[w]) {
                        distance[w] = distance[curr] + g.getWeight(curr, w);
                        // Add the updated vertex to the priority queue
                        pq.add(new Pair(w, distance[w]));
                        // Record the predecessor for path reconstruction
                        prevs[w] = curr;
                    }
                }
            }
        }
    }

    /**
     * Main method to demonstrate Dijkstra's algorithm with path reconstruction.
     *
     * @param args Command line arguments
     *
     * Example Output (depending on the graph in dijkstra.txt):
     * The shortest distance from vertex 0 to vertex 1 is: X
     * The shortest path from vertex 0 to vertex 1 is: [0, ..., 1]
     */
    public static void main(String[] args) {
        WeightedAdjSet g = new WeightedAdjSet("data/dijkstra.txt");
        Dijkstra2 dijkstra = new Dijkstra2(g, 0);
        System.out.println(dijkstra.minDistanceTo(1));
        System.out.println(dijkstra.path(1));
    }

    /**
     * Returns the minimum distance from the source vertex to the specified vertex.
     *
     * @param v The destination vertex
     * @return The shortest distance from source to v, or Integer.MAX_VALUE if no path exists
     *
     * Time Complexity: O(1) - constant time lookup in the distance array
     */
    public int minDistanceTo(int v) {
        validateVertex(v);
        return distance[v];
    }

    /**
     * Validates that the given vertex exists in the graph.
     *
     * @param v Vertex to validate
     * @throws IllegalArgumentException if vertex is invalid
     *
     * Time Complexity: O(1) - constant time operation
     */
    public void validateVertex(int v) {
        if (v < 0 || v >= g.getV()) {
            throw new IllegalArgumentException(String.format("Vertex %d invalid", v));
        }
    }

    /**
     * Checks if there is a path from the source vertex to the specified vertex.
     *
     * @param v The destination vertex
     * @return true if v is reachable from the source, false otherwise
     *
     * Time Complexity: O(1) - constant time lookup in the visited array
     *
     * Example:
     * Dijkstra2 dijkstra = new Dijkstra2(graph, 0);
     * boolean canReach = dijkstra.isConnected(5);
     * // Returns true if there's a path from vertex 0 to vertex 5
     */
    public boolean isConnected(int v) {
        validateVertex(v);
        return visited[v];
    }

    /**
     * Reconstructs the shortest path from the source vertex to the target vertex.
     *
     * @param target The destination vertex
     * @return Collection of vertices representing the shortest path from source to target,
     *         or an empty list if no path exists
     *
     * Time Complexity: O(P) where P is the path length (at most V)
     *
     * Example:
     * Dijkstra2 dijkstra = new Dijkstra2(graph, 0);
     * Collection<Integer> shortestPath = dijkstra.path(5);
     * // Returns the sequence of vertices in the shortest path from 0 to 5, e.g., [0, 2, 3, 5]
     */
    public Collection<Integer> path(int target) {
        List<Integer> res = new ArrayList<>();

        // If target is not reachable from source, return empty path
        if (!isConnected(target)) {
            return res;
        }

        // Reconstruct the path by walking backwards from target to source
        while (target != source) {
            res.add(target);
            target = prevs[target];  // Move to the predecessor
        }
        res.add(source);  // Add the source vertex

        // Reverse to get the path from source to target
        Collections.reverse(res);

        return res;
    }

    /**
     * Inner class that represents a vertex-distance pair for the priority queue.
     * Implements Comparable to allow the priority queue to order by distance.
     */
    private class Pair implements Comparable<Pair> {
        int v;    // Vertex
        int dis;  // Distance from source to this vertex

        /**
         * Constructs a vertex-distance pair.
         *
         * @param v The vertex
         * @param dis The distance to this vertex from the source
         */
        public Pair(int v, int dis) {
            this.v = v;
            this.dis = dis;
        }

        /**
         * Compares this pair with another pair based on distance.
         *
         * @param o The other pair to compare with
         * @return A negative value if this pair's distance is less, zero if equal,
         *         or a positive value if greater
         *
         * Note: This implementation may cause integer overflow for very large distances.
         * A more robust implementation would be: return Integer.compare(dis, o.dis);
         */
        @Override
        public int compareTo(Pair o) {
            return dis - o.dis;
        }
    }
}