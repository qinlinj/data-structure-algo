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

    /**
     * Optimized DP function with memoization
     */
    private int dp(int[][] grid, int i, int j) {
        // Base case: starting position
        if (i == 0 && j == 0) {
            return grid[0][0];
        }

        // Boundary condition: out of bounds
        if (i < 0 || j < 0) {
            return Integer.MAX_VALUE;
        }

        // Check if already computed (memoization check)
        if (memo[i][j] != -1) {
            return memo[i][j];
        }

        // Compute and store result in memo table
        memo[i][j] = Math.min(
                dp(grid, i - 1, j),     // path from above
                dp(grid, i, j - 1)      // path from left
        ) + grid[i][j];

        return memo[i][j];
    }

    /**
     * Performance comparison between recursive and memoized versions
     */
    public void performanceComparison(int[][] grid) {
        System.out.println("=== PERFORMANCE COMPARISON ===");

        // Test recursive version (warning: slow for large grids)
        if (grid.length <= 3 && grid[0].length <= 3) {
            long start = System.nanoTime();
            _841_b_StateTransitionDesign recursive = new _841_b_StateTransitionDesign();
            int recursiveResult = recursive.minPathSumRecursive(grid);
            long recursiveTime = System.nanoTime() - start;

            System.out.println("Recursive result: " + recursiveResult);
            System.out.println("Recursive time: " + recursiveTime + " ns");
        }

        // Test memoized version
        long start = System.nanoTime();
        int memoResult = minPathSum(grid);
        long memoTime = System.nanoTime() - start;

        System.out.println("Memoized result: " + memoResult);
        System.out.println("Memoized time: " + memoTime + " ns");

        if (grid.length <= 3 && grid[0].length <= 3) {
            System.out.println("Speedup: " +
                    (double) (System.nanoTime() - start - memoTime) / memoTime + "x");
        }
    }
}
