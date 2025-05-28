package org.qinlinj.algoframework._800_dynamic_programming_algo._840_dp_games_I._842_magic_tower_game;

/**
 * WHY FORWARD DP DEFINITION FAILS
 * <p>
 * Key Learning Points:
 * 1. Wrong DP definition: "minimum health needed to reach (i,j) from start"
 * 2. Problem: Insufficient information for state transitions
 * 3. We know minimum health to REACH a cell, but not health REMAINING at cell
 * 4. Cannot make optimal decisions without knowing remaining health
 * <p>
 * Critical Insight:
 * - Forward thinking lacks necessary information for decision making
 * - Need to know not just "how to get there" but "what state we're in when we arrive"
 * - This motivates the reverse DP approach
 * <p>
 * Example Problem:
 * Two paths reach the same cell with same minimum initial health requirement,
 * but different remaining health - algorithm cannot distinguish between them.
 */

public class _842_c_WhyForwardDPFails {
    /**
     * Demonstrates why forward DP definition fails
     * <p>
     * Wrong definition: dp(i,j) = minimum initial health to reach cell (i,j)
     * Problem: This doesn't tell us the health remaining when we reach (i,j)
     * <p>
     * In the example below, both paths to reach (2,2) require initial health = 1,
     * but they leave us with different remaining health values.
     */

    private static final int[][] PROBLEM_GRID = {
            {-3, 5},
            {1, -4}
    };

    private static void printGrid(int[][] grid) {
        for (int[] row : grid) {
            for (int val : row) {
                System.out.printf("%4d ", val);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        demonstrateForwardDPProblem();
    }

    /**
     * Attempted forward DP implementation (will fail for complex cases)
     * This shows why the approach is fundamentally flawed
     */
    public static class FailedForwardDP {
        public int calculateMinimumHP(int[][] dungeon) {
            int m = dungeon.length;
            int n = dungeon[0].length;

            // This approach will fail because it lacks necessary information
            int[][] dp = new int[m][n];

            // Base case - but this is already problematic
            dp[0][0] = dungeon[0][0] >= 0 ? 1 : -dungeon[0][0] + 1;

            // Fill first row and column - seems reasonable
            for (int j = 1; j < n; j++) {
                int needed = dp[0][j - 1] - dungeon[0][j];
                dp[0][j] = needed <= 0 ? 1 : needed;
            }

            for (int i = 1; i < m; i++) {
                int needed = dp[i - 1][0] - dungeon[i][0];
                dp[i][0] = needed <= 0 ? 1 : needed;
            }

            // Fill the rest - THIS IS WHERE IT BREAKS DOWN
            for (int i = 1; i < m; i++) {
                for (int j = 1; j < n; j++) {
                    // Problem: We don't know the remaining health when arriving
                    // from dp[i-1][j] vs dp[i][j-1], so we can't make optimal choice
                    int fromTop = dp[i - 1][j] - dungeon[i][j];
                    int fromLeft = dp[i][j - 1] - dungeon[i][j];

                    // This min choice is incorrect without knowing remaining health
                    int needed = Math.min(fromTop, fromLeft);
                    dp[i][j] = needed <= 0 ? 1 : needed;
                }
            }

            return dp[m - 1][n - 1];
        }
    }
}
