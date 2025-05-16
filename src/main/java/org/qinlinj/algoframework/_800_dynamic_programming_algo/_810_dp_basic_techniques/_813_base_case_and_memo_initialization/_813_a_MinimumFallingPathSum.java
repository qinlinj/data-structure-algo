package org.qinlinj.algoframework._800_dynamic_programming_algo._810_dp_basic_techniques._813_base_case_and_memo_initialization;

/**
 * Minimum Falling Path Sum - Dynamic Programming Approach
 * ======================================================
 * <p>
 * This class implements the solution to LeetCode problem 931: Minimum Falling Path Sum.
 * The problem asks to find the minimum sum path when falling from the first row to the last row
 * of a matrix, where each step can move down, down-left, or down-right.
 * <p>
 * Key Concepts:
 * <p>
 * 1. Top-down Dynamic Programming with Memoization:
 * - Recursive approach with memoization to avoid redundant calculations
 * - Uses a state transition based on the three possible moves from each position
 * <p>
 * 2. Function Definition:
 * - dp(matrix, i, j) = minimum path sum when falling to position (i, j) starting from top row
 * - This clear definition guides our base case and state transitions
 * <p>
 * 3. Recurrence Relation:
 * - dp(i, j) = matrix[i][j] + min(dp(i-1, j), dp(i-1, j-1), dp(i-1, j+1))
 * - For each position, we consider the minimum cost path from the three possible previous positions
 * <p>
 * Time Complexity: O(n²) where n is the side length of the matrix
 * Space Complexity: O(n²) for the memoization table
 */

import java.util.*;

public class _813_a_MinimumFallingPathSum {

    // Memoization table to store computed results
    private int[][] memo;

    public static void main(String[] args) {
        _813_a_MinimumFallingPathSum solution = new _813_a_MinimumFallingPathSum();

        // Example 1: Expected output is 13
        int[][] matrix1 = {
                {2, 1, 3},
                {6, 5, 4},
                {7, 8, 9}
        };

        // Example 2: Expected output is -59
        int[][] matrix2 = {
                {-19, 57},
                {-40, -5}
        };

        // Test both examples with both implementation approaches
        System.out.println("Example 1 (Top-down): " + solution.minFallingPathSum(matrix1));
        System.out.println("Example 1 (Bottom-up): " + solution.minFallingPathSumBottomUp(matrix1));
        System.out.println("Example 2 (Top-down): " + solution.minFallingPathSum(matrix2));
        System.out.println("Example 2 (Bottom-up): " + solution.minFallingPathSumBottomUp(matrix2));

        // Visualization for a simple example
        System.out.println("\nVisualization of falling path for Example 1:");
        visualizeFallingPath(matrix1);
    }

    /**
     * Helper method to visualize the falling path for better understanding.
     */
    private static void visualizeFallingPath(int[][] matrix) {
        _813_a_MinimumFallingPathSum solution = new _813_a_MinimumFallingPathSum();
        int n = matrix.length;

        // Find the path
        int[][] dp = new int[n][n];

        // Copy first row
        for (int j = 0; j < n; j++) {
            dp[0][j] = matrix[0][j];
        }

        // Fill dp table
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int prevLeft = (j > 0) ? dp[i - 1][j - 1] : Integer.MAX_VALUE;
                int prevMiddle = dp[i - 1][j];
                int prevRight = (j < n - 1) ? dp[i - 1][j + 1] : Integer.MAX_VALUE;

                dp[i][j] = matrix[i][j] + Math.min(prevLeft, Math.min(prevMiddle, prevRight));
            }
        }

        // Find minimum in last row
        int minVal = dp[n - 1][0];
        int minIdx = 0;
        for (int j = 1; j < n; j++) {
            if (dp[n - 1][j] < minVal) {
                minVal = dp[n - 1][j];
                minIdx = j;
            }
        }

        // Trace back the path
        int[] path = new int[n];
        path[n - 1] = minIdx;

        for (int i = n - 1; i > 0; i--) {
            int j = path[i];
            int val = dp[i][j] - matrix[i][j];

            if (j > 0 && dp[i - 1][j - 1] == val) {
                path[i - 1] = j - 1;
            } else if (dp[i - 1][j] == val) {
                path[i - 1] = j;
            } else {
                path[i - 1] = j + 1;
            }
        }

        // Print the matrix with path
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (j == path[i]) {
                    System.out.print("[" + matrix[i][j] + "] ");
                } else {
                    System.out.print(" " + matrix[i][j] + "  ");
                }
            }
            System.out.println();
        }

        // Print the sum
        System.out.println("Minimum path sum: " + minVal);
    }

    /**
     * Finds the minimum sum of any falling path through the matrix.
     *
     * @param matrix the input n x n square matrix
     * @return the minimum falling path sum
     */
    public int minFallingPathSum(int[][] matrix) {
        int n = matrix.length;
        int result = Integer.MAX_VALUE;

        // Initialize memoization table with a special value (explained in detail in _813_b_DPDetailsExplained)
        memo = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(memo[i], 66666);
        }

        // The path can end at any column in the last row, so we check all possibilities
        for (int j = 0; j < n; j++) {
            result = Math.min(result, dp(matrix, n - 1, j));
        }

        return result;
    }

    /**
     * Recursive helper function with memoization.
     * dp(matrix, i, j) = minimum sum of falling path ending at position (i, j)
     *
     * @param matrix the input matrix
     * @param i row index
     * @param j column index
     * @return minimum path sum to position (i, j)
     */
    private int dp(int[][] matrix, int i, int j) {
        // 1. Check for invalid indices
        if (i < 0 || j < 0 || i >= matrix.length || j >= matrix[0].length) {
            // Return a value that will never be chosen in the min operation
            return 99999;
        }

        // 2. Base case: when we reach the first row
        if (i == 0) {
            return matrix[0][j];
        }

        // 3. Check memoization table to avoid redundant calculations
        if (memo[i][j] != 66666) {
            return memo[i][j];
        }

        // 4. State transition: calculate minimum path sum by considering all three possible moves
        memo[i][j] = matrix[i][j] + min(
                dp(matrix, i - 1, j),     // coming from directly above
                dp(matrix, i - 1, j - 1), // coming from above-left
                dp(matrix, i - 1, j + 1)  // coming from above-right
        );

        return memo[i][j];
    }

    /**
     * Helper method to find minimum of three values.
     */
    private int min(int a, int b, int c) {
        return Math.min(a, Math.min(b, c));
    }

    /**
     * Alternative implementation using bottom-up dynamic programming.
     * Included to show the contrast with the top-down approach.
     */
    public int minFallingPathSumBottomUp(int[][] matrix) {
        int n = matrix.length;
        int[][] dp = new int[n][n];

        // Initialize first row with the matrix values (base case)
        for (int j = 0; j < n; j++) {
            dp[0][j] = matrix[0][j];
        }

        // Fill the dp table row by row
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // Get values from previous row, handling edge cases
                int prevLeft = (j > 0) ? dp[i - 1][j - 1] : Integer.MAX_VALUE;
                int prevMiddle = dp[i - 1][j];
                int prevRight = (j < n - 1) ? dp[i - 1][j + 1] : Integer.MAX_VALUE;

                // Update current cell with matrix value plus minimum from previous row
                dp[i][j] = matrix[i][j] + Math.min(prevLeft, Math.min(prevMiddle, prevRight));
            }
        }

        // Find minimum value in the last row
        int result = Integer.MAX_VALUE;
        for (int j = 0; j < n; j++) {
            result = Math.min(result, dp[n - 1][j]);
        }

        return result;
    }
}
