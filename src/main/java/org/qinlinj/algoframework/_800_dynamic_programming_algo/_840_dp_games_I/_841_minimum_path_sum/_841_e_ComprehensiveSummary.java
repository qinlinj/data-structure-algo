package org.qinlinj.algoframework._800_dynamic_programming_algo._840_dp_games_I._841_minimum_path_sum;

/**
 * COMPREHENSIVE SUMMARY AND ADVANCED TECHNIQUES
 * <p>
 * KNOWLEDGE POINTS SUMMARY:
 * 1. Problem Recognition: 2D grid optimization → Dynamic Programming
 * 2. State Definition: dp[i][j] = minimum path sum to reach position (i,j)
 * 3. State Transition: dp[i][j] = min(dp[i-1][j], dp[i][j-1]) + grid[i][j]
 * 4. Three Implementation Approaches:
 * - Recursive (exponential time, simple logic)
 * - Top-down with memoization (polynomial time, intuitive)
 * - Bottom-up iterative (polynomial time, space-efficient)
 * 5. Space Optimization: Reduce from O(m*n) to O(min(m,n))
 * <p>
 * PROBLEM-SOLVING FRAMEWORK:
 * 1. Identify if it's a DP problem (optimization + overlapping subproblems)
 * 2. Define the state clearly
 * 3. Find the recurrence relation
 * 4. Handle base cases and boundaries
 * 5. Choose implementation approach based on constraints
 * 6. Optimize space if needed
 * <p>
 * COMPLEXITY ANALYSIS:
 * - Recursive: Time O(2^(m+n)), Space O(m+n)
 * - Memoized: Time O(m*n), Space O(m*n)
 * - Bottom-up: Time O(m*n), Space O(m*n) or O(min(m,n))
 */
public class _841_e_ComprehensiveSummary {

    // Test method
    public static void main(String[] args) {
        _841_e_ComprehensiveSummary solution = new _841_e_ComprehensiveSummary();

        // Test cases
        int[][] grid1 = {{1, 3, 1}, {1, 5, 1}, {4, 2, 1}};
        int[][] grid2 = {{1, 2, 3}, {4, 5, 6}};

        System.out.println("=== FINAL OPTIMIZED SOLUTIONS ===");
        System.out.println("Test Case 1 Result: " + solution.minPathSum(grid1));
        System.out.println("Test Case 2 Result: " + solution.minPathSum(grid2));

        System.out.println("\n" + "=".repeat(60));
        solution.discussVariations();

        System.out.println("\n" + "=".repeat(60));
        solution.printDecisionTree();

        System.out.println("\n" + "=".repeat(60));
        solution.problemSolvingChecklist();

        System.out.println("\n" + "=".repeat(60));
        solution.benchmarkAllApproaches(grid1);
    }

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

    /**
     * PROBLEM VARIATIONS AND EXTENSIONS
     */
    public void discussVariations() {
        System.out.println("=== PROBLEM VARIATIONS ===");

        System.out.println("1. MAXIMUM PATH SUM:");
        System.out.println("   - Change min() to max() in transition");
        System.out.println("   - Same structure, different operation");

        System.out.println("\n2. PATH WITH OBSTACLES:");
        System.out.println("   - Mark obstacles as unreachable (e.g., Integer.MAX_VALUE)");
        System.out.println("   - Add boundary checks for obstacle cells");

        System.out.println("\n3. COUNT ALL PATHS:");
        System.out.println("   - dp[i][j] = count of paths to (i,j)");
        System.out.println("   - Transition: dp[i][j] = dp[i-1][j] + dp[i][j-1]");

        System.out.println("\n4. PATH WITH K STEPS:");
        System.out.println("   - Add third dimension: dp[i][j][k] = optimal value with exactly k steps");
        System.out.println("   - More complex but same principles");

        System.out.println("\n5. MULTIPLE STARTING/ENDING POINTS:");
        System.out.println("   - Initialize multiple base cases");
        System.out.println("   - Take optimal among all endpoints");
    }

    /**
     * DECISION TREE: When to use which approach
     */
    public void printDecisionTree() {
        System.out.println("=== APPROACH SELECTION GUIDE ===");
        System.out.println();
        System.out.println("Choose your approach based on:");
        System.out.println();
        System.out.println("RECURSIVE (Educational/Small inputs):");
        System.out.println("✓ When: Learning DP concepts, very small grids");
        System.out.println("✗ Avoid: Production code, large inputs");
        System.out.println("⚠ Complexity: O(2^(m+n)) time, O(m+n) space");
        System.out.println();

        System.out.println("MEMOIZED (Balance of intuition and efficiency):");
        System.out.println("✓ When: Intuitive thinking, medium-sized inputs");
        System.out.println("✓ When: Easy to modify for constraint changes");
        System.out.println("⚠ Complexity: O(m*n) time, O(m*n) space + recursion stack");
        System.out.println();

        System.out.println("BOTTOM-UP (Production-ready):");
        System.out.println("✓ When: Large inputs, memory-constrained environments");
        System.out.println("✓ When: Need predictable performance");
        System.out.println("⚠ Complexity: O(m*n) time, O(m*n) space");
        System.out.println();

        System.out.println("SPACE-OPTIMIZED (Memory-critical applications):");
        System.out.println("✓ When: Very large grids, embedded systems");
        System.out.println("✓ When: Memory usage is primary concern");
        System.out.println("⚠ Complexity: O(m*n) time, O(min(m,n)) space");
    }

    /**
     * COMPLETE PROBLEM-SOLVING CHECKLIST
     */
    public void problemSolvingChecklist() {
        System.out.println("=== DP PROBLEM-SOLVING CHECKLIST ===");
        System.out.println();
        System.out.println("□ 1. IDENTIFY DP PROBLEM:");
        System.out.println("  □ Is it an optimization problem? (min/max/count)");
        System.out.println("  □ Can it be broken into subproblems?");
        System.out.println("  □ Do subproblems overlap?");
        System.out.println("  □ Does optimal solution contain optimal subproblems?");
        System.out.println();

        System.out.println("□ 2. DEFINE STATE:");
        System.out.println("  □ What does dp[i][j] represent?");
        System.out.println("  □ What are the dimensions of the state space?");
        System.out.println("  □ What is the final answer in terms of dp?");
        System.out.println();

        System.out.println("□ 3. FIND RECURRENCE:");
        System.out.println("  □ How does dp[i][j] relate to previous states?");
        System.out.println("  □ What are all possible transitions to dp[i][j]?");
        System.out.println("  □ Which transition gives optimal value?");
        System.out.println();

        System.out.println("□ 4. HANDLE BASE CASES:");
        System.out.println("  □ What are the smallest subproblems?");
        System.out.println("  □ How to handle boundary conditions?");
        System.out.println("  □ Are there any special initialization requirements?");
        System.out.println();

        System.out.println("□ 5. IMPLEMENT AND OPTIMIZE:");
        System.out.println("  □ Choose appropriate implementation approach");
        System.out.println("  □ Consider space optimization opportunities");
        System.out.println("  □ Verify with test cases");
        System.out.println("  □ Analyze time and space complexity");
    }

    /**
     * Performance benchmarking across all approaches
     */
    public void benchmarkAllApproaches(int[][] grid) {
        System.out.println("=== PERFORMANCE BENCHMARK ===");
        System.out.println("Grid size: " + grid.length + "x" + grid[0].length);

        // Approach 1: Bottom-up DP
        long start = System.nanoTime();
        _841_d_BottomUpDP bottomUpSolution = new _841_d_BottomUpDP();
        int result1 = bottomUpSolution.minPathSum(grid);
        long time1 = System.nanoTime() - start;

        // Approach 2: Memoized DP
        start = System.nanoTime();
        _841_c_MemoizationSolution memoSolution = new _841_c_MemoizationSolution();
        int result2 = memoSolution.minPathSum(grid);
        long time2 = System.nanoTime() - start;

        // Approach 3: Space-optimized
        start = System.nanoTime();
        int result3 = minPathSum(grid);
        long time3 = System.nanoTime() - start;

        System.out.println("Results (all should be equal): " + result1 + ", " + result2 + ", " + result3);
        System.out.println();
        System.out.printf("Bottom-up DP:    %8d ns\n", time1);
        System.out.printf("Memoized DP:     %8d ns\n", time2);
        System.out.printf("Space-optimized: %8d ns\n", time3);

        // Memory usage comparison
        Runtime runtime = Runtime.getRuntime();
        long memoryBefore = runtime.totalMemory() - runtime.freeMemory();

        // Simulate memory usage for different approaches
        System.out.println("\nMemory Usage Comparison:");
        System.out.println("Bottom-up DP:    O(" + grid.length + " × " + grid[0].length + ") = O(" + (grid.length * grid[0].length) + ")");
        System.out.println("Memoized DP:     O(" + grid.length + " × " + grid[0].length + ") + recursion stack");
        System.out.println("Space-optimized: O(" + Math.min(grid.length, grid[0].length) + ")");
    }

    /**
     * COMPREHENSIVE SOLUTION TEMPLATE
     * This template can be adapted for similar 2D DP problems
     */
    public class TwoDimensionalDPTemplate {

        /**
         * Template for 2D grid DP problems
         *
         * @param grid           input grid
         * @param isMinimization true for minimization, false for maximization
         * @return optimal value
         */
        public int solve2DGridDP(int[][] grid, boolean isMinimization) {
            if (grid == null || grid.length == 0) return 0;

            int m = grid.length;
            int n = grid[0].length;
            int[][] dp = new int[m][n];

            // Step 1: Initialize base case
            dp[0][0] = grid[0][0];

            // Step 2: Initialize first row and column
            for (int i = 1; i < m; i++) {
                dp[i][0] = dp[i - 1][0] + grid[i][0]; // Only one way to reach
            }
            for (int j = 1; j < n; j++) {
                dp[0][j] = dp[0][j - 1] + grid[0][j]; // Only one way to reach
            }

            // Step 3: Fill the DP table
            for (int i = 1; i < m; i++) {
                for (int j = 1; j < n; j++) {
                    if (isMinimization) {
                        dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
                    } else {
                        dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
                    }
                }
            }

            return dp[m - 1][n - 1];
        }
    }
}