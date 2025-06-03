package org.qinlinj.algoframework._800_dynamic_programming_algo._860_dp_practice._863_dp_classic_practice_I;

// @formatter:off
import java.util.*; /**
 * LeetCode 120. Triangle Minimum Path Sum - Dynamic Programming Solution
 *
 * PROBLEM SUMMARY:
 * Given a triangle array, return the minimum path sum from top to bottom.
 * Each step you may move to adjacent numbers on the row below.
 *
 * KEY CONCEPTS:
 * 1. 2D Dynamic Programming with triangle structure
 * 2. State Definition: dp[i][j] = minimum path sum from top to triangle[i][j]
 * 3. State Transition: dp[i][j] = min(dp[i-1][j], dp[i-1][j-1]) + triangle[i][j]
 * 4. Adjacent constraint: from position j, you can move to j or j+1 in next row
 * 5. Boundary handling for leftmost and rightmost positions
 *
 * TIME COMPLEXITY: O(n²) where n is the number of rows
 * SPACE COMPLEXITY: O(n²), can be optimized to O(n)
 *
 * EXAMPLE:
 * Triangle: [[2],[3,4],[6,5,7],[4,1,8,3]]
 * Visualization:
 *    2
 *   3 4
 *  6 5 7
 * 4 1 8 3
 * Minimum path: 2 + 3 + 5 + 1 = 11
 */

public class _863_d_TriangleMinimumPath {
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();

        // dp[i][j] = minimum path sum to reach triangle[i][j]
        int[][] dp = new int[n][n];

        // Initialize with maximum values (since we want minimum)
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }

        // Base case: starting point
        dp[0][0] = triangle.get(0).get(0);

        // Fill the DP table
        for (int i = 1; i < n; i++) {
            List<Integer> row = triangle.get(i);
            for (int j = 0; j < row.size(); j++) {
                int current = row.get(j);

                // State transition: can come from dp[i-1][j] or dp[i-1][j-1]
                if (j > 0 && dp[i-1][j-1] != Integer.MAX_VALUE) {
                    dp[i][j] = Math.min(dp[i][j], dp[i-1][j-1] + current);
                }
                if (j < i && dp[i-1][j] != Integer.MAX_VALUE) {
                    dp[i][j] = Math.min(dp[i][j], dp[i-1][j] + current);
                }
            }
        }

        // Find minimum value in the last row
        int result = Integer.MAX_VALUE;
        for (int j = 0; j < n; j++) {
            result = Math.min(result, dp[n-1][j]);
        }

        return result;
    }

    // Space-optimized version using O(n) space
    public int minimumTotalOptimized(List<List<Integer>> triangle) {
        int n = triangle.size();

        // Use only one array to store the minimum sums
        int[] dp = new int[n];

        // Initialize with the first row
        dp[0] = triangle.get(0).get(0);

        // Process each row
        for (int i = 1; i < n; i++) {
            List<Integer> row = triangle.get(i);

            // Process from right to left to avoid overwriting needed values
            for (int j = i; j >= 0; j--) {
                int current = row.get(j);

                if (j == i) {
                    // Rightmost position: can only come from dp[j-1]
                    dp[j] = dp[j-1] + current;
                } else if (j == 0) {
                    // Leftmost position: can only come from dp[j]
                    dp[j] = dp[j] + current;
                } else {
                    // Middle positions: can come from dp[j] or dp[j-1]
                    dp[j] = Math.min(dp[j], dp[j-1]) + current;
                }
            }
        }

        // Find minimum in the last processed values
        int result = Integer.MAX_VALUE;
        for (int j = 0; j < n; j++) {
            result = Math.min(result, dp[j]);
        }

        return result;
    }
}
