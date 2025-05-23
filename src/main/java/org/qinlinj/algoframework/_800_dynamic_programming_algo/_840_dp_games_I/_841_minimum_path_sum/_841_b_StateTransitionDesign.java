package org.qinlinj.algoframework._800_dynamic_programming_algo._840_dp_games_I._841_minimum_path_sum;

public class _841_b_StateTransitionDesign {
    /**
     * Basic recursive solution (without memoization)
     * This demonstrates the core logic and state transition
     * <p>
     * Time Complexity: O(2^(m+n)) - exponential due to overlapping subproblems
     * Space Complexity: O(m+n) - recursion stack depth
     */
    public int minPathSumRecursive(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        // Calculate minimum path sum from top-left to bottom-right
        return dp(grid, m - 1, n - 1);
    }

    /**
     * Core DP function definition:
     * dp(grid, i, j) = minimum path sum from (0,0) to (i,j)
     */
    private int dp(int[][] grid, int i, int j) {
        // Base case: reached the starting point
        if (i == 0 && j == 0) {
            return grid[0][0];
        }

        // Boundary condition: if indices are out of bounds,
        // return a very large value to ensure it won't be chosen in min()
        if (i < 0 || j < 0) {
            return Integer.MAX_VALUE;
        }

        // State transition: choose the minimum path from top or left
        // and add current cell value
        return Math.min(
                dp(grid, i - 1, j),  // came from above
                dp(grid, i, j - 1)   // came from left
        ) + grid[i][j];
    }

    /**
     * Demonstrates the state transition with detailed logging
     */
    public int minPathSumWithLogging(int[][] grid) {
        System.out.println("=== TRACING STATE TRANSITIONS ===");
        return dpWithLogging(grid, grid.length - 1, grid[0].length - 1, 0);
    }

    private int dpWithLogging(int[][] grid, int i, int j, int depth) {
        // Indentation for visualization
        String indent = "  ".repeat(depth);
        System.out.println(indent + "dp(" + i + "," + j + ")");

        // Base case
        if (i == 0 && j == 0) {
            System.out.println(indent + "  Base case: grid[0][0] = " + grid[0][0]);
            return grid[0][0];
        }

        // Boundary condition
        if (i < 0 || j < 0) {
            System.out.println(indent + "  Out of bounds: returning MAX_VALUE");
            return Integer.MAX_VALUE;
        }

        // Recursive calls
        System.out.println(indent + "  Exploring paths from (" + i + "," + j + "):");
        int fromTop = dpWithLogging(grid, i - 1, j, depth + 1);
        int fromLeft = dpWithLogging(grid, i, j - 1, depth + 1);

        int result = Math.min(fromTop, fromLeft) + grid[i][j];
        System.out.println(indent + "  Result for (" + i + "," + j + "): " +
                "min(" + fromTop + "," + fromLeft + ") + " + grid[i][j] + " = " + result);

        return result;
    }
}
