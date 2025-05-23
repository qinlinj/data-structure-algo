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
}
