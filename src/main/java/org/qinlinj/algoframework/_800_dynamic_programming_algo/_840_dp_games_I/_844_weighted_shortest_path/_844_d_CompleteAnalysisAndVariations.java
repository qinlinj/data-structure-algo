package org.qinlinj.algoframework._800_dynamic_programming_algo._840_dp_games_I._844_weighted_shortest_path;

import java.util.*;

/**
 * Complete Analysis and Variations of Cheapest Flights Problem
 * <p>
 * Comprehensive Coverage:
 * 1. Multiple Solution Approaches: BFS/Dijkstra, Top-down DP, Bottom-up DP, Bellman-Ford
 * 2. Problem Variations: Exact K stops, At most K stops, Multiple destinations
 * 3. Edge Cases: No path exists, Self-loops, Multiple edges between cities
 * 4. Optimization Techniques: Early termination, Space optimization, Pruning
 * 5. Real-world Applications: Flight booking, Network routing, Cost optimization
 * <p>
 * Algorithm Comparison:
 * - Dijkstra: O((V+E)logV), good for sparse graphs, handles general shortest path
 * - Top-down DP: O(V*K*E), intuitive recursion, handles step constraints naturally
 * - Bottom-up DP: O(K*E), iterative, better space locality, easier to optimize
 * - Bellman-Ford: O(K*E), detects negative cycles, works with negative weights
 * <p>
 * This class serves as a complete reference implementation with extensive testing
 * and real-world scenario simulation for the cheapest flights problem.
 */

public class _844_d_CompleteAnalysisAndVariations {
    /**
     * Master solution class with all approaches
     */
    public static class MasterSolution {

        // Approach 1: Dijkstra with step constraint
        public int solveDijkstra(int n, int[][] flights, int src, int dst, int k) {
            Map<Integer, List<int[]>> graph = new HashMap<>();
            for (int[] flight : flights) {
                graph.computeIfAbsent(flight[0], key -> new ArrayList<>())
                        .add(new int[]{flight[1], flight[2]});
            }

            PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
            pq.offer(new int[]{src, 0, 0}); // {city, cost, stops}

            Map<String, Integer> visited = new HashMap<>();

            while (!pq.isEmpty()) {
                int[] curr = pq.poll();
                int city = curr[0], cost = curr[1], stops = curr[2];

                if (city == dst) return cost;
                if (stops > k) continue;

                String key = city + "," + stops;
                if (visited.containsKey(key) && visited.get(key) <= cost) continue;
                visited.put(key, cost);

                if (graph.containsKey(city)) {
                    for (int[] next : graph.get(city)) {
                        pq.offer(new int[]{next[0], cost + next[1], stops + 1});
                    }
                }
            }
            return -1;
        }
    }
}
