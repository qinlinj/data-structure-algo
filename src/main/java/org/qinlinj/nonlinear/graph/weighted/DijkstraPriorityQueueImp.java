package org.qinlinj.nonlinear.graph.weighted;

import java.util.*;

// @formatter:off
/**
 * Optimized Dijkstra's Algorithm Implementation (Dijkstra1)
 *
 * Concept and Principles:
 * This is an optimized version of Dijkstra's algorithm that uses a priority queue to efficiently
 * find the vertex with the minimum distance at each step. Like the basic version, it computes
 * the shortest path from a source vertex to all other vertices in a weighted graph with non-negative edges.
 *
 * Key Optimizations Compared to Basic Dijkstra:
 * 1. Priority Queue: Uses a min-heap (PriorityQueue) to efficiently extract the vertex with minimum distance
 *    - Eliminates the need to scan all vertices in each iteration to find minimum distance vertex
 *    - Reduces time complexity from O(V²) to O((V+E) log V) or effectively O(E log V)
 * 2. Lazy Deletion: Simply skips vertices that have already been processed
 *    - Avoids the overhead of removing outdated entries from the priority queue
 * 3. On-demand Distance Updates: Only adds a vertex to the priority queue when its distance is updated
 *    - Reduces the total number of operations on the priority queue
 *
 * Algorithm Steps:
 * 1. Initialize distances from source to all vertices as infinite, and distance to source as 0
 * 2. Create a priority queue and add the source vertex with distance 0
 * 3. While the priority queue is not empty:
 *    a. Extract the vertex with minimum distance from priority queue
 *    b. If vertex already processed, skip (lazy deletion)
 *    c. Mark the vertex as visited
 *    d. Update distances to all adjacent unvisited vertices if a shorter path is found
 *    e. For each updated vertex, add to priority queue with its new distance
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
 * Step-by-step execution:
 *
 * 1. Initialize:
 *    - All distances = ∞, except distance[A] = 0
 *    - Priority Queue (PQ): [(A,0)]  // Format: (vertex, distance)
 *    - Visited: {}
 *
 * 2. Extract minimum from PQ: (A,0)
 *    - Mark A as visited
 *    - Update neighbors:
 *      * B: distance[B] = min(∞, 0+4) = 4, add (B,4) to PQ
 *      * C: distance[C] = min(∞, 0+2) = 2, add (C,2) to PQ
 *    - PQ: [(C,2), (B,4)]
 *    - Visited: {A}
 *    - Current distances: [A:0, B:4, C:2, D:∞, E:∞, F:∞, G:∞]
 *
 * 3. Extract minimum from PQ: (C,2)
 *    - Mark C as visited
 *    - Update neighbors:
 *      * D: distance[D] = min(∞, 2+1) = 3, add (D,3) to PQ
 *      * F: distance[F] = min(∞, 2+3) = 5, add (F,5) to PQ
 *    - PQ: [(D,3), (B,4), (F,5)]
 *    - Visited: {A, C}
 *    - Current distances: [A:0, B:4, C:2, D:3, E:∞, F:5, G:∞]
 *
 * 4. Extract minimum from PQ: (D,3)
 *    - Mark D as visited
 *    - Update neighbors:
 *      * B: distance[B] = min(4, 3+3) = 4, no change
 *      * E: distance[E] = min(∞, 3+2) = 5, add (E,5) to PQ
 *    - PQ: [(B,4), (F,5), (E,5)]
 *    - Visited: {A, C, D}
 *    - Current distances: [A:0, B:4, C:2, D:3, E:5, F:5, G:∞]
 *
 * 5. Extract minimum from PQ: (B,4)
 *    - Mark B as visited
 *    - All neighbors already visited or no improvement
 *    - PQ: [(F,5), (E,5)]
 *    - Visited: {A, C, D, B}
 *    - Current distances: [A:0, B:4, C:2, D:3, E:5, F:5, G:∞]
 *
 * 6. Extract minimum from PQ: (Either F or E, depending on PQ implementation)
 *    - Let's say (E,5) is extracted
 *    - Mark E as visited
 *    - Update neighbors:
 *      * G: distance[G] = min(∞, 5+1) = 6, add (G,6) to PQ
 *    - PQ: [(F,5), (G,6)]
 *    - Visited: {A, C, D, B, E}
 *    - Current distances: [A:0, B:4, C:2, D:3, E:5, F:5, G:6]
 *
 * 7. Extract minimum from PQ: (F,5)
 *    - Mark F as visited
 *    - Update neighbors:
 *      * G: distance[G] = min(6, 5+5) = 6, no change
 *    - PQ: [(G,6)]
 *    - Visited: {A, C, D, B, E, F}
 *    - Current distances: [A:0, B:4, C:2, D:3, E:5, F:5, G:6]
 *
 * 8. Extract minimum from PQ: (G,6)
 *    - Mark G as visited
 *    - No neighbors to update
 *    - PQ: []
 *    - Visited: {A, C, D, B, E, F, G}
 *    - Final distances: [A:0, B:4, C:2, D:3, E:5, F:5, G:6]
 *
 * Result: Same shortest distances as before, but computed more efficiently
 *
 * Time Complexity: O((V+E) log V)
 * - Each vertex is extracted from the PQ once: O(V log V)
 * - Each edge can cause at most one vertex insertion into the PQ: O(E log V)
 * - Combined: O((V+E) log V), which is effectively O(E log V) for connected graphs (where E ≥ V-1)
 *
 * Space Complexity: O(V) for the distance and visited arrays, plus O(V) for the priority queue
 */
public class DijkstraPriorityQueueImp {
    private WeightedAdjSet g;        // The weighted graph
    private int source;              // Source vertex for shortest paths
    private int[] distance;          // Array to store shortest distance from source to each vertex
    private boolean[] visited;       // Array to track visited vertices

    /**
     * Constructs a Dijkstra shortest path calculator using a priority queue for optimization.
     *
     * @param g The weighted graph
     * @param source The source vertex from which to compute shortest paths
     *
     * Time Complexity: O((V+E) log V) where V is the number of vertices and E is the number of edges
     *   - Each vertex is extracted from the priority queue once: O(V log V)
     *   - Each edge can lead to at most one vertex insertion: O(E log V)
     *   - Total: O((V+E) log V), which is much better than O(V²) for sparse graphs
     *
     * Space Complexity: O(V) for the distance and visited arrays, plus O(V) for the priority queue
     */
    public DijkstraPriorityQueueImp(WeightedAdjSet g, int source) {
        this.g = g;
        this.source = source;

        // Initialize distance array with infinity for all vertices except source
        distance = new int[g.getV()];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[source] = 0;  // Distance from source to itself is 0

        // Initialize visited array to track processed vertices
        visited = new boolean[g.getV()];

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
                    }
                }
            }
        }
    }

    /**
     * Main method to demonstrate the optimized Dijkstra's algorithm.
     *
     * @param args Command line arguments
     *
     * Example Output (depending on the graph in dijkstra.txt):
     * The shortest distance from vertex 0 to vertex 1 is: X
     */
    public static void main(String[] args) {
        WeightedAdjSet g = new WeightedAdjSet("data/dijkstra.txt");
        DijkstraPriorityQueueImp dijkstra = new DijkstraPriorityQueueImp(g, 0);
        System.out.println(dijkstra.minDistanceTo(1));
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