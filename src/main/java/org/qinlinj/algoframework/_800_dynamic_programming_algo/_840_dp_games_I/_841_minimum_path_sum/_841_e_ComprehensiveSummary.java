package org.qinlinj.algoframework._800_dynamic_programming_algo._840_dp_games_I._841_minimum_path_sum;

public class _841_e_ComprehensiveSummary {
    /**
     * FINAL OPTIMIZED SOLUTION
     * This is the most practical implementation combining efficiency and readability
     */
    public int minPathSum(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int m = grid.length;
        int n = grid[0].length;

        // Space optimization: use the smaller dimension for the DP array
        if (m < n) {
            return minPathSumOptimized(grid, true);  // process by rows
        } else {
            return minPathSumOptimized(rotateGrid(grid), false); // process by columns
        }
    }

    /**
     * Space-optimized implementation with O(min(m,n)) space
     */
    private int minPathSumOptimized(int[][] grid, boolean byRows) {
        int m = grid.length;
        int n = grid[0].length;

        if (byRows) {
            // Process row by row, maintain only one row in memory
            int[] dp = new int[n];

            // Initialize first row
            dp[0] = grid[0][0];
            for (int j = 1; j < n; j++) {
                dp[j] = dp[j - 1] + grid[0][j];
            }

            // Process remaining rows
            for (int i = 1; i < m; i++) {
                dp[0] += grid[i][0]; // First column: only from above
                for (int j = 1; j < n; j++) {
                    dp[j] = Math.min(dp[j], dp[j - 1]) + grid[i][j];
                }
            }

            return dp[n - 1];
        } else {
            // Process column by column (when m > n)
            int[] dp = new int[m];

            // Initialize first column
            dp[0] = grid[0][0];
            for (int i = 1; i < m; i++) {
                dp[i] = dp[i - 1] + grid[i][0];
            }

            // Process remaining columns
            for (int j = 1; j < n; j++) {
                dp[0] += grid[0][j]; // First row: only from left
                for (int i = 1; i < m; i++) {
                    dp[i] = Math.min(dp[i], dp[i - 1]) + grid[i][j];
                }
            }

            return dp[m - 1];
        }
    }

    /**
     * Helper method to rotate grid for column-wise processing
     */
    private int[][] rotateGrid(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] rotated = new int[n][m];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                rotated[j][i] = grid[i][j];
            }
        }

        return rotated;
    }
}
