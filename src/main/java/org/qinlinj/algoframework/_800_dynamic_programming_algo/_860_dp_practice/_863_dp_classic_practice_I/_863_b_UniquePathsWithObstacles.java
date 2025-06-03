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
}
