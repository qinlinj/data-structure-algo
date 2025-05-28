package org.qinlinj.algoframework._800_dynamic_programming_algo._840_dp_games_I._842_magic_tower_game;

import java.util.*;

/**
 * COMPLETE DUNGEON GAME SOLUTION WITH COMPREHENSIVE ANALYSIS
 * <p>
 * Problem Summary:
 * - Knight must travel from top-left to bottom-right to rescue princess
 * - Can only move right or down
 * - Health must remain > 0 throughout the journey
 * - Find minimum initial health required
 * <p>
 * Solution Approach:
 * 1. Use reverse DP: work backwards from destination
 * 2. dp(i,j) = minimum health needed at (i,j) to reach the end successfully
 * 3. State transition: dp(i,j) = max(1, min(dp(i+1,j), dp(i,j+1)) - grid[i][j])
 * <p>
 * Time Complexity: O(m*n) where m,n are grid dimensions
 * Space Complexity: O(m*n) for memoization
 * <p>
 * Key Insights:
 * - Forward DP fails due to insufficient state information
 * - Reverse DP provides complete information for optimal decisions
 * - Health constraint (>0) is critical for correctness
 */

public class _842_e_CompleteSolutionAnalysis {
    /**
     * Main solution class for the Dungeon Game problem
     */
    public static class DungeonGameSolver {
        private int[][] memo;

        /**
         * Calculates minimum initial health needed for knight to rescue princess
         *
         * @param dungeon m x n grid representing the dungeon
         * @return minimum initial health points required
         */
        public int calculateMinimumHP(int[][] dungeon) {
            if (dungeon == null || dungeon.length == 0) return 1;

            int m = dungeon.length;
            int n = dungeon[0].length;

            // Initialize memoization table
            memo = new int[m][n];
            for (int[] row : memo) {
                Arrays.fill(row, -1);
            }

            return dp(dungeon, 0, 0);
        }
    }

    /**
     * Alternative iterative solution for comparison
     */
    public int calculateMinimumHPIterative(int[][] dungeon) {
        int m = dungeon.length;
        int n = dungeon[0].length;

        // Create DP table with extra row/column for boundary handling
        int[][] dp = new int[m + 1][n + 1];

        // Initialize boundary values to max (invalid paths)
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }

        // Set base cases: virtual cells adjacent to destination
        dp[m][n - 1] = dp[m - 1][n] = 1;

        // Fill DP table from bottom-right to top-left
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                int minHealthNeeded = Math.min(dp[i + 1][j], dp[i][j + 1]);
                dp[i][j] = Math.max(1, minHealthNeeded - dungeon[i][j]);
            }
        }

        return dp[0][0];
    }
}
}
