package org.qinlinj.algoframework._800_dynamic_programming_algo._840_dp_games_I._841_minimum_path_sum;

public class _841_c_MemoizationSolution {
    // Memoization table to store computed results
    private int[][] memo;

    /**
     * Main function using top-down DP with memoization
     * <p>
     * Time Complexity: O(m*n) - each cell computed at most once
     * Space Complexity: O(m*n) - memo table + O(m+n) recursion stack
     */
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        // Initialize memoization table with -1 (uncomputed)
        memo = new int[m][n];
        for (int[] row : memo) {
            java.util.Arrays.fill(row, -1);
        }

        return dp(grid, m - 1, n - 1);
    }
}
