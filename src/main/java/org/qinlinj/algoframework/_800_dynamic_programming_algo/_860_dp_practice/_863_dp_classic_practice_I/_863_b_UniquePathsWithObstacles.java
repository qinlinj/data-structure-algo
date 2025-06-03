package org.qinlinj.algoframework._800_dynamic_programming_algo._860_dp_practice._863_dp_classic_practice_I;

import java.util.*;

public class _863_b_UniquePathsWithObstacles {
    // Approach 1: Top-down recursion with memoization
    private int[][] memo;

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


