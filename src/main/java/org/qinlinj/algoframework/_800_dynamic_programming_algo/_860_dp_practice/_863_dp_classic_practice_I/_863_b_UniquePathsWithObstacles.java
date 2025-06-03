package org.qinlinj.algoframework._800_dynamic_programming_algo._860_dp_practice._863_dp_classic_practice_I;

/**
 * LeetCode 63. Unique Paths II - Dynamic Programming Solution
 * <p>
 * PROBLEM SUMMARY:
 * A robot is located at the top-left corner of an m x n grid. The robot can only
 * move either down or right at any point in time. Find the number of possible
 * unique paths to reach the bottom-right corner, considering obstacles.
 * <p>
 * KEY CONCEPTS:
 * 1. 2D Dynamic Programming with obstacle handling
 * 2. State Definition: dp[i][j] = number of paths to reach grid[i][j]
 * 3. State Transition: dp[i][j] = dp[i-1][j] + dp[i][j-1] (if no obstacle)
 * 4. Three optimization approaches:
 * - Top-down with memoization
 * - Bottom-up 2D DP
 * - Space-optimized 1D DP
 * <p>
 * TIME COMPLEXITY: O(m × n)
 * SPACE COMPLEXITY: O(m × n) for 2D DP, O(n) for 1D optimized version
 */

import java.util.*;

public class _863_b_UniquePathsWithObstacles {

    // Approach 1: Top-down recursion with memoization
    private int[][] memo;

    public static void main(String[] args) {
        _863_b_UniquePathsWithObstacles solution = new _863_b_UniquePathsWithObstacles();

        System.out.println("=== Unique Paths with Obstacles Tests ===");

        // Test case 1
        int[][] grid1 = {
                {0, 0, 0},
                {0, 1, 0},
                {0, 0, 0}
        };

        System.out.println("Test Case 1:");
        System.out.println("Grid:");
        printGrid(grid1);

        int result1_memo = solution.uniquePathsWithObstaclesMemo(grid1);
        int result1_2d = solution.uniquePathsWithObstacles2D(grid1);
        int result1_1d = solution.uniquePathsWithObstacles1D(grid1);

        System.out.printf("Result (Memoization): %d\n", result1_memo);
        System.out.printf("Result (2D DP): %d\n", result1_2d);
        System.out.printf("Result (1D DP): %d\n", result1_1d);
        System.out.println("Expected: 2 (paths: right→right→down→down or down→down→right→right)\n");

        // Test case 2
        int[][] grid2 = {
                {0, 1},
                {0, 0}
        };

        System.out.println("Test Case 2:");
        System.out.println("Grid:");
        printGrid(grid2);

        int result2 = solution.uniquePathsWithObstacles2D(grid2);
        System.out.printf("Result: %d\n", result2);
        System.out.println("Expected: 1 (only path: down→right)\n");

        // Demonstration of DP approach
        System.out.println("=== DP State Transition Visualization ===");
        System.out.println("For grid1, the DP table progression:");
        System.out.println("Initial: dp[1][1] = 1 (starting point)");
        System.out.println("Row 1: [0, 1, 1, 1] (can reach all cells in first row)");
        System.out.println("Row 2: [0, 1, 0, 1] (middle cell blocked by obstacle)");
        System.out.println("Row 3: [0, 1, 1, 2] (final answer: 2 paths)");
    }

    private static void printGrid(int[][] grid) {
        for (int[] row : grid) {
            for (int cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }

    public int uniquePathsWithObstaclesMemo(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        memo = new int[m][n];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }
        return dp(obstacleGrid, m - 1, n - 1);
    }

    // Definition: dp(grid, i, j) returns number of paths from (0,0) to (i,j)
    private int dp(int[][] grid, int i, int j) {
        int m = grid.length, n = grid[0].length;

        // Base cases
        if (i < 0 || i >= m || j < 0 || j >= n || grid[i][j] == 1) {
            return 0; // Out of bounds or obstacle
        }
        if (i == 0 && j == 0) {
            return 1; // Starting point
        }

        // Check memoization
        if (memo[i][j] != -1) {
            return memo[i][j];
        }

        // State transition
        int fromLeft = dp(grid, i, j - 1);
        int fromUp = dp(grid, i - 1, j);
        int result = fromLeft + fromUp;

        memo[i][j] = result;
        return result;
    }

    // Approach 2: Bottom-up 2D DP
    public int uniquePathsWithObstacles2D(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;

        // dp[i][j] represents paths to reach obstacleGrid[i-1][j-1]
        int[][] dp = new int[m + 1][n + 1];

        // Base case: if starting point has no obstacle
        dp[1][1] = obstacleGrid[0][0] == 1 ? 0 : 1;

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == 1 && j == 1) continue; // Skip base case

                if (obstacleGrid[i - 1][j - 1] == 1) {
                    dp[i][j] = 0; // Obstacle, no path
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }

        return dp[m][n];
    }

    // Approach 3: Space-optimized 1D DP
    public int uniquePathsWithObstacles1D(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;

        // Use 1D array to save space
        int[] dp = new int[n + 1];
        dp[1] = obstacleGrid[0][0] == 1 ? 0 : 1;

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == 1 && j == 1) continue; // Skip base case

                if (obstacleGrid[i - 1][j - 1] == 1) {
                    dp[j] = 0; // Obstacle
                } else {
                    dp[j] = dp[j] + dp[j - 1]; // dp[j] (from above) + dp[j-1] (from left)
                }
            }
        }

        return dp[n];
    }
}