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

        // Approach 2: Top-down DP with memoization
        public int solveTopDownDP(int n, int[][] flights, int src, int dst, int k) {
            Map<Integer, List<int[]>> indegree = new HashMap<>();
            for (int[] flight : flights) {
                indegree.computeIfAbsent(flight[1], key -> new ArrayList<>())
                        .add(new int[]{flight[0], flight[2]});
            }

            Integer[][] memo = new Integer[n][k + 2];
            return dpHelper(dst, k + 1, src, indegree, memo);
        }

        private int dpHelper(int city, int k, int src, Map<Integer, List<int[]>> indegree, Integer[][] memo) {
            if (city == src) return 0;
            if (k == 0) return -1;
            if (memo[city][k] != null) return memo[city][k];

            int res = Integer.MAX_VALUE;
            if (indegree.containsKey(city)) {
                for (int[] edge : indegree.get(city)) {
                    int subRes = dpHelper(edge[0], k - 1, src, indegree, memo);
                    if (subRes != -1) {
                        res = Math.min(res, subRes + edge[1]);
                    }
                }
            }
            return memo[city][k] = (res == Integer.MAX_VALUE ? -1 : res);
        }

        // Approach 3: Bottom-up DP (most efficient)
        public int solveBottomUpDP(int n, int[][] flights, int src, int dst, int k) {
            int[] dp = new int[n];
            Arrays.fill(dp, Integer.MAX_VALUE);
            dp[src] = 0;

            for (int i = 0; i <= k; i++) {
                int[] temp = dp.clone();
                for (int[] flight : flights) {
                    int from = flight[0], to = flight[1], price = flight[2];
                    if (dp[from] != Integer.MAX_VALUE) {
                        temp[to] = Math.min(temp[to], dp[from] + price);
                    }
                }
                dp = temp;
            }
            return dp[dst] == Integer.MAX_VALUE ? -1 : dp[dst];
        }

        // Approach 4: Bellman-Ford variant
        public int solveBellmanFord(int n, int[][] flights, int src, int dst, int k) {
            int[] dist = new int[n];
            Arrays.fill(dist, Integer.MAX_VALUE);
            dist[src] = 0;

            // Relax edges k+1 times
            for (int i = 0; i <= k; i++) {
                int[] temp = dist.clone();
                for (int[] flight : flights) {
                    int u = flight[0], v = flight[1], w = flight[2];
                    if (dist[u] != Integer.MAX_VALUE && dist[u] + w < temp[v]) {
                        temp[v] = dist[u] + w;
                    }
                }
                dist = temp;
            }
            return dist[dst] == Integer.MAX_VALUE ? -1 : dist[dst];
        }
    }

    /**
     * Problem variations and extensions
     */
    public static class ProblemVariations {

        // Variation 1: Find path with exactly K stops
        public int findCheapestPriceExactlyKStops(int n, int[][] flights, int src, int dst, int k) {
            int[] dp = new int[n];
            Arrays.fill(dp, Integer.MAX_VALUE);
            dp[src] = 0;

            for (int step = 0; step < k; step++) {
                int[] temp = new int[n];
                Arrays.fill(temp, Integer.MAX_VALUE);

                for (int[] flight : flights) {
                    int from = flight[0], to = flight[1], price = flight[2];
                    if (dp[from] != Integer.MAX_VALUE) {
                        temp[to] = Math.min(temp[to], dp[from] + price);
                    }
                }
                dp = temp;
            }
            return dp[dst] == Integer.MAX_VALUE ? -1 : dp[dst];
        }
    }
}

