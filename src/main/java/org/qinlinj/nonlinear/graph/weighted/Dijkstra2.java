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
    private WeightedAdjSet g;
    private int source;

    private int[] distance;
    private boolean[] visited;

    private int[] prevs;

    public Dijkstra2(WeightedAdjSet g, int source) {
        this.g = g;
        this.source = source;

        distance = new int[g.getV()];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[source] = 0;

        visited = new boolean[g.getV()];
        prevs = new int[g.getV()];
        Arrays.fill(prevs, -1);

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
                        prevs[w] = curr;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        WeightedAdjSet g = new WeightedAdjSet("data/dijkstra.txt");
        Dijkstra2 dijkstra = new Dijkstra2(g, 0);
        System.out.println(dijkstra.minDistanceTo(1));
        System.out.println(dijkstra.path(1));
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

    public boolean isConnected(int v) {
        validateVertex(v);
        return visited[v];
    }

    public Collection<Integer> path(int target) {
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
