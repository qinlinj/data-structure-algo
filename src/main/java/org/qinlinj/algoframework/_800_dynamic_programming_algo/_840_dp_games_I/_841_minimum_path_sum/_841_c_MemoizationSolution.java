package org.qinlinj.algoframework._800_dynamic_programming_algo._840_dp_games_I._841_minimum_path_sum;

/**
 * TOP-DOWN DYNAMIC PROGRAMMING WITH MEMOIZATION
 * <p>
 * KNOWLEDGE POINTS:
 * 1. Memoization Technique: Cache results to avoid redundant calculations
 * 2. Time Complexity Optimization: From O(2^(m+n)) to O(m*n)
 * 3. Space-Time Tradeoff: Use O(m*n) extra space to save exponential time
 * 4. Cache Management: Initialize memo table and check before computing
 * 5. Overlapping Subproblems: Same subproblems solved multiple times without memo
 * <p>
 * MEMOIZATION BENEFITS:
 * - Transforms exponential recursive solution to polynomial time
 * - Maintains the intuitive recursive structure
 * - Easy to implement by adding cache checks
 * - Optimal for problems with overlapping subproblems
 * <p>
 * IMPLEMENTATION STRATEGY:
 * 1. Create memo table to store computed results
 * 2. Check memo before computation
 * 3. Store result in memo after computation
 * 4. Use sentinel value (-1) to indicate uncomputed states
 */
public class _841_c_MemoizationSolution {

    // Memoization table to store computed results
    private int[][] memo;

    // Test method
    public static void main(String[] args) {
        _841_c_MemoizationSolution solution = new _841_c_MemoizationSolution();

        // Test cases
        int[][] grid1 = {{1, 3, 1}, {1, 5, 1}, {4, 2, 1}};
        int[][] grid2 = {{1, 2, 3}, {4, 5, 6}};

        System.out.println("Test Case 1:");
        System.out.println("Grid:");
        for (int[] row : grid1) {
            for (int val : row) {
                System.out.print(val + " ");
            }
            System.out.println();
        }

        int result1 = solution.minPathSumWithMemoLogging(grid1);
        System.out.println("Result: " + result1);

        System.out.println("\n" + "=".repeat(50));

        System.out.println("Test Case 2:");
        System.out.println("Grid:");
        for (int[] row : grid2) {
            for (int val : row) {
                System.out.print(val + " ");
            }
            System.out.println();
        }

        int result2 = solution.minPathSum(grid2);
        System.out.println("Result: " + result2);

        // Demonstrate memoization impact
        solution.demonstrateMemoizationImpact();
        solution.performanceComparison(grid1);
    }

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
     * Version with detailed memoization logging
     */
    public int minPathSumWithMemoLogging(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        memo = new int[m][n];
        for (int[] row : memo) {
            java.util.Arrays.fill(row, -1);
        }

        System.out.println("=== MEMOIZATION TRACE ===");
        int result = dpWithMemoLogging(grid, m - 1, n - 1, 0);

        System.out.println("\n=== FINAL MEMO TABLE ===");
        printMemoTable();

        return result;
    }

    private int dpWithMemoLogging(int[][] grid, int i, int j, int depth) {
        String indent = "  ".repeat(depth);
        System.out.println(indent + "dp(" + i + "," + j + ")");

        // Base case
        if (i == 0 && j == 0) {
            System.out.println(indent + "  Base case: " + grid[0][0]);
            return grid[0][0];
        }

        // Boundary condition
        if (i < 0 || j < 0) {
            System.out.println(indent + "  Out of bounds");
            return Integer.MAX_VALUE;
        }

        // Check memoization
        if (memo[i][j] != -1) {
            System.out.println(indent + "  Cache hit! Returning " + memo[i][j]);
            return memo[i][j];
        }

        System.out.println(indent + "  Computing new result...");

        // Recursive computation
        int fromTop = dpWithMemoLogging(grid, i - 1, j, depth + 1);
        int fromLeft = dpWithMemoLogging(grid, i, j - 1, depth + 1);

        memo[i][j] = Math.min(fromTop, fromLeft) + grid[i][j];
        System.out.println(indent + "  Stored memo[" + i + "][" + j + "] = " + memo[i][j]);

        return memo[i][j];
    }

    /**
     * Helper method to print the memoization table
     */
    private void printMemoTable() {
        if (memo == null) return;

        for (int i = 0; i < memo.length; i++) {
            for (int j = 0; j < memo[0].length; j++) {
                if (memo[i][j] == -1) {
                    System.out.print("  -  ");
                } else {
                    System.out.printf("%3d  ", memo[i][j]);
                }
            }
            System.out.println();
        }
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

    /**
     * Demonstrates the impact of memoization
     */
    public void demonstrateMemoizationImpact() {
        System.out.println("=== MEMOIZATION IMPACT ===");
        System.out.println("Without memoization:");
        System.out.println("- Time Complexity: O(2^(m+n)) - exponential");
        System.out.println("- Each subproblem solved multiple times");
        System.out.println("- Recursive tree has exponential nodes");

        System.out.println("\nWith memoization:");
        System.out.println("- Time Complexity: O(m*n) - polynomial");
        System.out.println("- Each subproblem solved exactly once");
        System.out.println("- Cached results reused");
        System.out.println("- Space-time tradeoff: O(m*n) space for O(2^(m+n)) time savings");
    }
}