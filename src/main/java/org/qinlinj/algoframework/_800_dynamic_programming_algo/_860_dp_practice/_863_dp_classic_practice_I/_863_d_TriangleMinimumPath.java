package org.qinlinj.algoframework._800_dynamic_programming_algo._860_dp_practice._863_dp_classic_practice_I;

// @formatter:off
import java.util.*; 

/**
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
    public static void main(String[] args) {
        _863_d_TriangleMinimumPath solution = new _863_d_TriangleMinimumPath();

        System.out.println("=== Triangle Minimum Path Sum Tests ===");

        // Test case 1
        List<List<Integer>> triangle1 = Arrays.asList(
                Arrays.asList(2),
                Arrays.asList(3, 4),
                Arrays.asList(6, 5, 7),
                Arrays.asList(4, 1, 8, 3)
        );

        System.out.println("Test Case 1:");
        System.out.println("Triangle:");
        printTriangle(triangle1);

        int result1 = solution.minimumTotal(triangle1);
        int result1_opt = solution.minimumTotalOptimized(triangle1);

        System.out.printf("Result (2D DP): %d\n", result1);
        System.out.printf("Result (Optimized): %d\n", result1_opt);
        System.out.println("Explanation: Path 2→3→5→1 gives sum = 11\n");

        // Test case 2
        List<List<Integer>> triangle2 = Arrays.asList(
                Arrays.asList(-10)
        );

        System.out.println("Test Case 2:");
        System.out.println("Triangle:");
        printTriangle(triangle2);

        int result2 = solution.minimumTotal(triangle2);
        System.out.printf("Result: %d\n", result2);
        System.out.println("Explanation: Only one element, so minimum sum is -10\n");

        // Test case 3 - demonstrating DP transitions
        List<List<Integer>> triangle3 = Arrays.asList(
                Arrays.asList(1),
                Arrays.asList(2, 3),
                Arrays.asList(4, 5, 6)
        );

        System.out.println("Test Case 3 (DP Visualization):");
        System.out.println("Triangle:");
        printTriangle(triangle3);

        int result3 = solution.minimumTotal(triangle3);
        System.out.printf("Result: %d\n", result3);

        System.out.println("\n=== DP State Transition Visualization ===");
        System.out.println("DP Table progression:");
        System.out.println("Row 0: [1]");
        System.out.println("Row 1: [3, 4] (1+2=3, 1+3=4)");
        System.out.println("Row 2: [7, 8, 10] (3+4=7, min(3,4)+5=8, 4+6=10)");
        System.out.println("Minimum path sum: 7 (path: 1→2→4)");
    }

    private static void printTriangle(List<List<Integer>> triangle) {
        int n = triangle.size();
        for (int i = 0; i < n; i++) {
            // Add spaces for triangle shape
            for (int k = 0; k < n - i - 1; k++) {
                System.out.print(" ");
            }

            List<Integer> row = triangle.get(i);
            for (int j = 0; j < row.size(); j++) {
                System.out.print(row.get(j));
                if (j < row.size() - 1) {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }

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
