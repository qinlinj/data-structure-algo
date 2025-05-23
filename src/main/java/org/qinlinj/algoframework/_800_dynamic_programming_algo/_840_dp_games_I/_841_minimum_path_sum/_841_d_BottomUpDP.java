package org.qinlinj.algoframework._800_dynamic_programming_algo._840_dp_games_I._841_minimum_path_sum;

public class _841_d_BottomUpDP {
    /**
     * Bottom-up DP solution with 2D table
     * <p>
     * Time Complexity: O(m*n) - visit each cell once
     * Space Complexity: O(m*n) - DP table storage
     */
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        // Create DP table
        int[][] dp = new int[m][n];

        // === BASE CASE INITIALIZATION ===

        // Initialize starting position
        dp[0][0] = grid[0][0];

        // Initialize first column (can only come from above)
        for (int i = 1; i < m; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }

        // Initialize first row (can only come from left)
        for (int j = 1; j < n; j++) {
            dp[0][j] = dp[0][j - 1] + grid[0][j];
        }

        // === STATE TRANSITION ===

        // Fill the rest of the DP table
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.min(
                        dp[i - 1][j],   // came from above
                        dp[i][j - 1]    // came from left
                ) + grid[i][j];
            }
        }

        return dp[m - 1][n - 1];
    }
}
