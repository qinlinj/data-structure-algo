package org.qinlinj.nonlinear.graph.weighted;

import java.util.*;

/**
 * Optimized Dijkstra's Algorithm Implementation (Dijkstra1)
 * <p>
 * Concept and Principles:
 * This is an optimized version of Dijkstra's algorithm that uses a priority queue to efficiently
 * find the vertex with the minimum distance at each step. Like the basic version, it computes
 * the shortest path from a source vertex to all other vertices in a weighted graph with non-negative edges.
 * <p>
 * Key Optimizations Compared to Basic Dijkstra:
 * 1. Priority Queue: Uses a min-heap (PriorityQueue) to efficiently extract the vertex with minimum distance
 * - Eliminates the need to scan all vertices in each iteration to find minimum distance vertex
 * - Reduces time complexity from O(V²) to O((V+E) log V) or effectively O(E log V)
 * 2. Lazy Deletion: Simply skips vertices that have already been processed
 * - Avoids the overhead of removing outdated entries from the priority queue
 * 3. On-demand Distance Updates: Only adds a vertex to the priority queue when its distance is updated
 * - Reduces the total number of operations on the priority queue
 * <p>
 * Algorithm Steps:
 * 1. Initialize distances from source to all vertices as infinite, and distance to source as 0
 * 2. Create a priority queue and add the source vertex with distance 0
 * 3. While the priority queue is not empty:
 * a. Extract the vertex with minimum distance from priority queue
 * b. If vertex already processed, skip (lazy deletion)
 * c. Mark the vertex as visited
 * d. Update distances to all adjacent unvisited vertices if a shorter path is found
 * e. For each updated vertex, add to priority queue with its new distance
 * <p>
 * Detailed Example Visualization:
 * Using the same graph as before:
 * <p>
 * A --- 4 --- B
 * |           |
 * 2           3
 * |           |
 * C --- 1 --- D --- 2 --- E
 * |                       |
 * 3                       1
 * |                       |
 * F ---------- 5 --------- G
 * <p>
 * Step-by-step execution:
 * <p>
 * 1. Initialize:
 * - All distances = ∞, except distance[A] = 0
 * - Priority Queue (PQ): [(A,0)]  // Format: (vertex, distance)
 * - Visited: {}
 * <p>
 * 2. Extract minimum from PQ: (A,0)
 * - Mark A as visited
 * - Update neighbors:
 * * B: distance[B] = min(∞, 0+4) = 4, add (B,4) to PQ
 * * C: distance[C] = min(∞, 0+2) = 2, add (C,2) to PQ
 * - PQ: [(C,2), (B,4)]
 * - Visited: {A}
 * - Current distances: [A:0, B:4, C:2, D:∞, E:∞, F:∞, G:∞]
 * <p>
 * 3. Extract minimum from PQ: (C,2)
 * - Mark C as visited
 * - Update neighbors:
 * * D: distance[D] = min(∞, 2+1) = 3, add (D,3) to PQ
 * * F: distance[F] = min(∞, 2+3) = 5, add (F,5) to PQ
 * - PQ: [(D,3), (B,4), (F,5)]
 * - Visited: {A, C}
 * - Current distances: [A:0, B:4, C:2, D:3, E:∞, F:5, G:∞]
 * <p>
 * 4. Extract minimum from PQ: (D,3)
 * - Mark D as visited
 * - Update neighbors:
 * * B: distance[B] = min(4, 3+3) = 4, no change
 * * E: distance[E] = min(∞, 3+2) = 5, add (E,5) to PQ
 * - PQ: [(B,4), (F,5), (E,5)]
 * - Visited: {A, C, D}
 * - Current distances: [A:0, B:4, C:2, D:3, E:5, F:5, G:∞]
 * <p>
 * 5. Extract minimum from PQ: (B,4)
 * - Mark B as visited
 * - All neighbors already visited or no improvement
 * - PQ: [(F,5), (E,5)]
 * - Visited: {A, C, D, B}
 * - Current distances: [A:0, B:4, C:2, D:3, E:5, F:5, G:∞]
 * <p>
 * 6. Extract minimum from PQ: (Either F or E, depending on PQ implementation)
 * - Let's say (E,5) is extracted
 * - Mark E as visited
 * - Update neighbors:
 * * G: distance[G] = min(∞, 5+1) = 6, add (G,6) to PQ
 * - PQ: [(F,5), (G,6)]
 * - Visited: {A, C, D, B, E}
 * - Current distances: [A:0, B:4, C:2, D:3, E:5, F:5, G:6]
 * <p>
 * 7. Extract minimum from PQ: (F,5)
 * - Mark F as visited
 * - Update neighbors:
 * * G: distance[G] = min(6, 5+5) = 6, no change
 * - PQ: [(G,6)]
 * - Visited: {A, C, D, B, E, F}
 * - Current distances: [A:0, B:4, C:2, D:3, E:5, F:5, G:6]
 * <p>
 * 8. Extract minimum from PQ: (G,6)
 * - Mark G as visited
 * - No neighbors to update
 * - PQ: []
 * - Visited: {A, C, D, B, E, F, G}
 * - Final distances: [A:0, B:4, C:2, D:3, E:5, F:5, G:6]
 * <p>
 * Result: Same shortest distances as before, but computed more efficiently
 * <p>
 * Time Complexity: O((V+E) log V)
 * - Each vertex is extracted from the PQ once: O(V log V)
 * - Each edge can cause at most one vertex insertion into the PQ: O(E log V)
 * - Combined: O((V+E) log V), which is effectively O(E log V) for connected graphs (where E ≥ V-1)
 * <p>
 * Space Complexity: O(V) for the distance and visited arrays, plus O(V) for the priority queue
 */
public class Dijkstra1 {
    private WeightedAdjSet g;
    private int source;

    private int[] distance;
    private boolean[] visited;

    public Dijkstra1(WeightedAdjSet g, int source) {
        this.g = g;
        this.source = source;

        distance = new int[g.getV()];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[source] = 0;

        visited = new boolean[g.getV()];

        PriorityQueue<Pair> pq = new PriorityQueue<>();

        pq.add(new Pair(source, 0));

        while (!pq.isEmpty()) { // O(V)
            int curr = pq.poll().v; // O(logV)
            if (visited[curr]) continue;

            visited[curr] = true;

            for (int w : g.adj(curr)) { // O(E)
                if (!visited[w]) {
                    if (distance[curr] + g.getWeight(curr, w) < distance[w]) {
                        distance[w] = distance[curr] + g.getWeight(curr, w);
                        pq.add(new Pair(w, distance[w]));
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        WeightedAdjSet g = new WeightedAdjSet("data/dijkstra.txt");
        Dijkstra1 dijkstra = new Dijkstra1(g, 0);
        System.out.println(dijkstra.minDistanceTo(1));
    }

    public int minDistanceTo(int v) {
        validateVertex(v);
        return distance[v];
    }

    public void validateVertex(int v) {
        if (v < 0 || v >= g.getV()) {
            throw new IllegalArgumentException(String.format("Vertex %d invalid", v));
        }
    }

    private class Pair implements Comparable<Pair> {
        int v;
        int dis;

        public Pair(int v, int dis) {
            this.v = v;
            this.dis = dis;
        }

        @Override
        public int compareTo(Pair o) {
            return dis - o.dis;
        }
    }
}
