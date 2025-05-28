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
     * Comprehensive test suite and analysis
     */
    public static class TestSuite {
        private DungeonGameSolver solver = new DungeonGameSolver();

        public void runAllTests() {
            System.out.println("=== DUNGEON GAME COMPLETE SOLUTION ANALYSIS ===\n");

            testBasicExample();
            testEdgeCases();
            testPerformanceComparison();
            analyzeAlgorithmComplexity();
        }

        private void testBasicExample() {
            System.out.println("1. BASIC EXAMPLE TEST");
            System.out.println("-------------------");

            int[][] dungeon = {
                    {-2, -3, 3},
                    {-5, -10, 1},
                    {10, 30, -5}
            };

            System.out.println("Grid:");
            printGrid(dungeon);

            int result1 = solver.calculateMinimumHP(dungeon);
            int result2 = solver.calculateMinimumHPIterative(dungeon);

            System.out.println("Recursive solution: " + result1);
            System.out.println("Iterative solution: " + result2);
            System.out.println("Expected: 7");
            System.out.println("Test passed: " + (result1 == 7 && result2 == 7));

            // Trace the optimal path
            traceOptimalPath(dungeon, result1);
            System.out.println();
        }

        private void testEdgeCases() {
            System.out.println("2. EDGE CASES TEST");
            System.out.println("------------------");

            // Single cell with positive value
            testCase("Single positive cell", new int[][]{{5}}, 1);

            // Single cell with negative value
            testCase("Single negative cell", new int[][]{{-3}}, 4);

            // All positive values
            testCase("All positive", new int[][]{{1, 2}, {3, 4}}, 1);

            // All negative values
            testCase("All negative", new int[][]{{-1, -2}, {-3, -4}}, 7);

            // Mixed with zeros
            testCase("Mixed with zeros", new int[][]{{0, -3}, {1, 0}}, 4);

            System.out.println();
        }
    }

}
