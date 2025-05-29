package org.qinlinj.algoframework._800_dynamic_programming_algo._840_dp_games_I._844_weighted_shortest_path;

import java.util.*;

public class _844_c_CompleteDPImplementation {
    /**
     * Optimized DP solution with memoization
     */
    public static class OptimizedDPSolution {
        private static final int UNCOMPUTED = -888;
        private Map<Integer, List<int[]>> indegree;
        private int source, destination;
        private int[][] memo;

        public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
            // Convert stops to edges (k stops = k+1 edges)
            k++;

            this.source = src;
            this.destination = dst;

            // Initialize memoization table
            memo = new int[n][k + 1];
            for (int[] row : memo) {
                Arrays.fill(row, UNCOMPUTED);
            }

            // Build indegree graph
            buildIndegreeGraph(flights);

            return dp(dst, k);
        }


        /**
         * Builds indegree representation of the graph
         * For each city, stores all cities that can reach it and the cost
         */
        private void buildIndegreeGraph(int[][] flights) {
            indegree = new HashMap<>();
            for (int[] flight : flights) {
                int from = flight[0];
                int to = flight[1];
                int price = flight[2];

                indegree.computeIfAbsent(to, key -> new ArrayList<>())
                        .add(new int[]{from, price});
            }
        }

        /**
         * DP function with memoization
         *
         * @param city  target city to reach
         * @param steps maximum steps allowed
         * @return minimum cost to reach city from source in steps, or -1 if impossible
         */
        private int dp(int city, int steps) {
            // Base case: reached source city
            if (city == source) {
                return 0;
            }

            // Base case: no steps remaining
            if (steps == 0) {
                return -1;
            }

            // Check memoization table
            if (memo[city][steps] != UNCOMPUTED) {
                return memo[city][steps];
            }

            int minCost = Integer.MAX_VALUE;

            // Try all possible previous cities (incoming edges)
            if (indegree.containsKey(city)) {
                for (int[] incomingEdge : indegree.get(city)) {
                    int fromCity = incomingEdge[0];
                    int edgePrice = incomingEdge[1];

                    // Recursive call for subproblem
                    int subproblemCost = dp(fromCity, steps - 1);

                    // Update minimum cost if valid path found
                    if (subproblemCost != -1) {
                        minCost = Math.min(minCost, subproblemCost + edgePrice);
                    }
                }
            }

            // Store result in memo table
            memo[city][steps] = (minCost == Integer.MAX_VALUE) ? -1 : minCost;
            return memo[city][steps];
        }
        
    }
}
