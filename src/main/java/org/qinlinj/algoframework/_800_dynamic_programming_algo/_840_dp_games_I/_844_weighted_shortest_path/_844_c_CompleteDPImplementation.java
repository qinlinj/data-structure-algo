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
    }
}
