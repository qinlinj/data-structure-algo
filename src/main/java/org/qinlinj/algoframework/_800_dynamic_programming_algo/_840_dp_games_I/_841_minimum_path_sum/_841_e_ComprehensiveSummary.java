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
}
