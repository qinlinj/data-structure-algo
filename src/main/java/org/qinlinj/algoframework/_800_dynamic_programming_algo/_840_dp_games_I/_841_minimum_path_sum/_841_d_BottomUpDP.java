package org.qinlinj.algoframework._800_dynamic_programming_algo._840_dp_games_I._841_minimum_path_sum;

/**
 * BOTTOM-UP DYNAMIC PROGRAMMING (ITERATIVE APPROACH)
 * <p>
 * KNOWLEDGE POINTS:
 * 1. Bottom-Up vs Top-Down: Build solution from smaller subproblems upward
 * 2. Iterative Implementation: Eliminates recursion overhead and stack space
 * 3. Base Case Initialization: Properly initialize first row and column
 * 4. State Filling Order: Fill DP table in correct dependency order
 * 5. Space Efficiency: Can be optimized to O(min(m,n)) space complexity
 * <p>
 * BOTTOM-UP ADVANTAGES:
 * - No recursion overhead or stack overflow risk
 * - Better space efficiency potential
 * - Clearer iteration pattern
 * - Often faster in practice due to better cache locality
 * <p>
 * KEY INSIGHT:
 * Build the solution by computing dp[i][j] for all positions (i,j)
 * starting from (0,0) and proceeding row by row, ensuring all
 * dependencies are computed before current cell.
 * <p>
 * DP TABLE DEFINITION:
 * dp[i][j] = minimum path sum from (0,0) to (i,j)
 */
public class _841_d_BottomUpDP {

    // Test method
    public static void main(String[] args) {
        _841_d_BottomUpDP solution = new _841_d_BottomUpDP();

        // Interactive learning with small example
        solution.interactiveLearning();

        System.out.println("\n" + "=".repeat(60));

        // Test case 1 - detailed visualization
        int[][] grid1 = {{1, 3, 1}, {1, 5, 1}, {4, 2, 1}};
        System.out.println("=== TEST CASE 1 - DETAILED VISUALIZATION ===");
        int result1 = solution.minPathSumWithVisualization(grid1);
        System.out.println("Final Result: " + result1);

        System.out.println("\n" + "=".repeat(60));

        // Test case 2 - space optimization
        int[][] grid2 = {{1, 2, 3}, {4, 5, 6}};
        System.out.println("=== TEST CASE 2 - SPACE OPTIMIZATION ===");
        System.out.println("Original Grid:");
        for (int[] row : grid2) {
            for (int val : row) {
                System.out.print(val + " ");
            }
            System.out.println();
        }

        int result2 = solution.minPathSumSpaceOptimizedWithLogging(grid2);
        System.out.println("Space-Optimized Result: " + result2);

        System.out.println("\n" + "=".repeat(60));

        // Educational sections
        solution.explainBaseCase();
        System.out.println("\n" + "=".repeat(30));
        solution.demonstrateFillOrder();
        System.out.println("\n" + "=".repeat(30));
        solution.compareApproaches(grid1);
    }

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

    /**
     * Bottom-up solution with detailed step-by-step visualization
     */
    public int minPathSumWithVisualization(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];

        System.out.println("=== BOTTOM-UP DP VISUALIZATION ===");
        System.out.println("Original Grid:");
        printGrid(grid);

        // Base case: starting position
        dp[0][0] = grid[0][0];
        System.out.println("\nStep 1: Initialize dp[0][0] = " + dp[0][0]);
        printDPTable(dp, 0, 0);

        // Initialize first column
        System.out.println("\nStep 2: Initialize first column");
        for (int i = 1; i < m; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
            System.out.println("dp[" + i + "][0] = dp[" + (i - 1) + "][0] + grid[" + i + "][0] = " +
                    dp[i - 1][0] + " + " + grid[i][0] + " = " + dp[i][0]);
        }
        printDPTable(dp, m - 1, 0);

        // Initialize first row
        System.out.println("\nStep 3: Initialize first row");
        for (int j = 1; j < n; j++) {
            dp[0][j] = dp[0][j - 1] + grid[0][j];
            System.out.println("dp[0][" + j + "] = dp[0][" + (j - 1) + "] + grid[0][" + j + "] = " +
                    dp[0][j - 1] + " + " + grid[0][j] + " = " + dp[0][j]);
        }
        printDPTable(dp, 0, n - 1);

        // Fill the rest
        System.out.println("\nStep 4: Fill remaining cells");
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
                System.out.println("dp[" + i + "][" + j + "] = min(dp[" + (i - 1) + "][" + j + "], dp[" + i + "][" + (j - 1) + "]) + grid[" + i + "][" + j + "]");
                System.out.println("         = min(" + dp[i - 1][j] + ", " + dp[i][j - 1] + ") + " + grid[i][j] + " = " + dp[i][j]);
                printDPTable(dp, i, j);
                System.out.println();
            }
        }

        System.out.println("Final DP Table:");
        printDPTable(dp, m - 1, n - 1);

        return dp[m - 1][n - 1];
    }

    /**
     * Space-optimized version using only O(n) space
     * <p>
     * Time Complexity: O(m*n)
     * Space Complexity: O(n) - only store one row at a time
     */
    public int minPathSumSpaceOptimized(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        // Use 1D array to represent current row
        int[] dp = new int[n];

        // Initialize first row
        dp[0] = grid[0][0];
        for (int j = 1; j < n; j++) {
            dp[j] = dp[j - 1] + grid[0][j];
        }

        // Process each row
        for (int i = 1; i < m; i++) {
            // Update first column of current row
            dp[0] += grid[i][0];

            // Update rest of current row
            for (int j = 1; j < n; j++) {
                dp[j] = Math.min(dp[j], dp[j - 1]) + grid[i][j];
                //      dp[j] represents dp[i-1][j] (from above)
                //      dp[j-1] represents dp[i][j-1] (from left)
            }
        }

        return dp[n - 1];
    }

    /**
     * Demonstrates the space optimization technique with detailed logging
     */
    public int minPathSumSpaceOptimizedWithLogging(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[] dp = new int[n];

        System.out.println("=== SPACE-OPTIMIZED DP ===");
        System.out.println("Using 1D array of size " + n + " instead of 2D array of size " + m + "x" + n);

        // Initialize first row
        dp[0] = grid[0][0];
        for (int j = 1; j < n; j++) {
            dp[j] = dp[j - 1] + grid[0][j];
        }
        System.out.println("Initial row: " + java.util.Arrays.toString(dp));

        // Process each row
        for (int i = 1; i < m; i++) {
            System.out.println("\nProcessing row " + i + ":");

            // Update first column
            dp[0] += grid[i][0];
            System.out.println("  dp[0] = " + dp[0] + " (can only come from above)");

            // Update rest of row
            for (int j = 1; j < n; j++) {
                int fromAbove = dp[j];      // This is dp[i-1][j]
                int fromLeft = dp[j - 1];   // This is dp[i][j-1]
                dp[j] = Math.min(fromAbove, fromLeft) + grid[i][j];
                System.out.println("  dp[" + j + "] = min(" + fromAbove + ", " + fromLeft + ") + " + grid[i][j] + " = " + dp[j]);
            }
            System.out.println("  Row " + i + " result: " + java.util.Arrays.toString(dp));
        }

        return dp[n - 1];
    }

    /**
     * Alternative space optimization - process column by column
     * Useful when n > m (more columns than rows)
     */
    public int minPathSumSpaceOptimizedByColumns(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        // Use 1D array to represent current column
        int[] dp = new int[m];

        // Initialize first column
        dp[0] = grid[0][0];
        for (int i = 1; i < m; i++) {
            dp[i] = dp[i - 1] + grid[i][0];
        }

        // Process each column
        for (int j = 1; j < n; j++) {
            // Update first row of current column
            dp[0] += grid[0][j];

            // Update rest of current column
            for (int i = 1; i < m; i++) {
                dp[i] = Math.min(dp[i], dp[i - 1]) + grid[i][j];
                //      dp[i] represents dp[i][j-1] (from left)
                //      dp[i-1] represents dp[i-1][j] (from above)
            }
        }

        return dp[m - 1];
    }

    /**
     * Optimal space complexity solution - choose better dimension
     */
    public int minPathSumOptimalSpace(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        // Choose the approach that uses less space
        if (m <= n) {
            return minPathSumSpaceOptimizedByColumns(grid);  // Use O(m) space
        } else {
            return minPathSumSpaceOptimized(grid);           // Use O(n) space
        }
    }

    /**
     * Helper method to print grid
     */
    private void printGrid(int[][] grid) {
        for (int[] row : grid) {
            for (int val : row) {
                System.out.printf("%3d ", val);
            }
            System.out.println();
        }
    }

    /**
     * Helper method to print DP table with current position highlighted
     */
    private void printDPTable(int[][] dp, int currentI, int currentJ) {
        System.out.println("DP Table (current position: [" + currentI + "," + currentJ + "]):");
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                if (i == currentI && j == currentJ) {
                    System.out.printf("[%2d] ", dp[i][j]);
                } else if (dp[i][j] == 0) {
                    System.out.print("  -  ");
                } else {
                    System.out.printf("%3d  ", dp[i][j]);
                }
            }
            System.out.println();
        }
    }

    /**
     * Compares all three approaches: recursive, memoized, and bottom-up
     */
    public void compareApproaches(int[][] grid) {
        System.out.println("=== APPROACH COMPARISON ===");

        // Bottom-up DP
        long start = System.nanoTime();
        int bottomUpResult = minPathSum(grid);
        long bottomUpTime = System.nanoTime() - start;

        // Memoized DP
        _841_c_MemoizationSolution memoSolution = new _841_c_MemoizationSolution();
        start = System.nanoTime();
        int memoResult = memoSolution.minPathSum(grid);
        long memoTime = System.nanoTime() - start;

        // Space-optimized DP
        start = System.nanoTime();
        int spaceOptResult = minPathSumSpaceOptimized(grid);
        long spaceOptTime = System.nanoTime() - start;

        // Optimal space DP
        start = System.nanoTime();
        int optimalSpaceResult = minPathSumOptimalSpace(grid);
        long optimalSpaceTime = System.nanoTime() - start;

        System.out.println("Results (should all be equal):");
        System.out.println("Bottom-up 2D:     " + bottomUpResult + " (Time: " + bottomUpTime + " ns)");
        System.out.println("Memoized DP:      " + memoResult + " (Time: " + memoTime + " ns)");
        System.out.println("Space-Optimized:  " + spaceOptResult + " (Time: " + spaceOptTime + " ns)");
        System.out.println("Optimal Space:    " + optimalSpaceResult + " (Time: " + optimalSpaceTime + " ns)");

        System.out.println("\nSpace Complexity:");
        System.out.println("Bottom-up 2D:     O(m*n) - full 2D table");
        System.out.println("Memoized DP:      O(m*n) - memo table + O(m+n) recursion stack");
        System.out.println("Space-Optimized:  O(n) - only one row stored");
        System.out.println("Optimal Space:    O(min(m,n)) - choose better dimension");
    }

    /**
     * Explains why base case initialization is necessary
     */
    public void explainBaseCase() {
        System.out.println("=== BASE CASE EXPLANATION ===");
        System.out.println("Why do we need special initialization for first row and column?");
        System.out.println();

        System.out.println("General transition: dp[i][j] = min(dp[i-1][j], dp[i][j-1]) + grid[i][j]");
        System.out.println();

        System.out.println("Problem cases:");
        System.out.println("1. First row (i=0, j>0): dp[i-1][j] would access dp[-1][j] (invalid!)");
        System.out.println("   Solution: dp[0][j] = dp[0][j-1] + grid[0][j] (only from left)");
        System.out.println();

        System.out.println("2. First column (i>0, j=0): dp[i][j-1] would access dp[i][-1] (invalid!)");
        System.out.println("   Solution: dp[i][0] = dp[i-1][0] + grid[i][0] (only from above)");
        System.out.println();

        System.out.println("3. Starting point (0,0): No previous cells");
        System.out.println("   Solution: dp[0][0] = grid[0][0] (base case)");

        System.out.println("\nThis gives us the foundation to apply the general formula for all other cells.");
    }

    /**
     * Demonstrates the filling order importance
     */
    public void demonstrateFillOrder() {
        System.out.println("=== FILLING ORDER DEMONSTRATION ===");
        System.out.println("DP table must be filled in dependency order:");
        System.out.println();

        System.out.println("For dp[i][j], we need:");
        System.out.println("- dp[i-1][j] (cell above)");
        System.out.println("- dp[i][j-1] (cell to the left)");
        System.out.println();

        System.out.println("Valid filling patterns:");
        System.out.println("1. Row by row (left to right, top to bottom)");
        System.out.println("2. Column by column (top to bottom, left to right)");
        System.out.println("3. Diagonal by diagonal");
        System.out.println();

        System.out.println("Invalid patterns:");
        System.out.println("❌ Bottom to top (dependencies not ready)");
        System.out.println("❌ Right to left in rows (left dependency missing)");
        System.out.println("❌ Random order (may access uncomputed values)");
    }

    /**
     * Interactive learning method - step through small example
     */
    public void interactiveLearning() {
        System.out.println("=== INTERACTIVE LEARNING ===");
        int[][] smallGrid = {{1, 2}, {3, 4}};

        System.out.println("Let's solve this 2x2 grid step by step:");
        printGrid(smallGrid);

        int[][] dp = new int[2][2];

        System.out.println("\nStep 1: Initialize dp[0][0]");
        dp[0][0] = smallGrid[0][0];
        System.out.println("dp[0][0] = grid[0][0] = " + dp[0][0]);
        printDPTable(dp, 0, 0);

        System.out.println("\nStep 2: Initialize first row");
        dp[0][1] = dp[0][0] + smallGrid[0][1];
        System.out.println("dp[0][1] = dp[0][0] + grid[0][1] = " + dp[0][0] + " + " + smallGrid[0][1] + " = " + dp[0][1]);
        printDPTable(dp, 0, 1);

        System.out.println("\nStep 3: Initialize first column");
        dp[1][0] = dp[0][0] + smallGrid[1][0];
        System.out.println("dp[1][0] = dp[0][0] + grid[1][0] = " + dp[0][0] + " + " + smallGrid[1][0] + " = " + dp[1][0]);
        printDPTable(dp, 1, 0);

        System.out.println("\nStep 4: Fill remaining cell");
        dp[1][1] = Math.min(dp[0][1], dp[1][0]) + smallGrid[1][1];
        System.out.println("dp[1][1] = min(dp[0][1], dp[1][0]) + grid[1][1]");
        System.out.println("         = min(" + dp[0][1] + ", " + dp[1][0] + ") + " + smallGrid[1][1]);
        System.out.println("         = " + Math.min(dp[0][1], dp[1][0]) + " + " + smallGrid[1][1] + " = " + dp[1][1]);
        printDPTable(dp, 1, 1);

        System.out.println("\nFinal answer: " + dp[1][1]);
        System.out.println("Optimal path: 1 → 2 → 4 (sum = 7)");
    }
}