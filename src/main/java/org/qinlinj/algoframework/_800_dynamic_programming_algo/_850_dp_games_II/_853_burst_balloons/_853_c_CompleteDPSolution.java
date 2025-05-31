package org.qinlinj.algoframework._800_dynamic_programming_algo._850_dp_games_II._853_burst_balloons;

import java.util.*;

/**
 * BURST BALLOONS PROBLEM - COMPLETE DYNAMIC PROGRAMMING SOLUTION
 * <p>
 * Final Implementation Details:
 * <p>
 * Algorithm Steps:
 * 1. Transform input by adding virtual balloons with value 1 at both ends
 * 2. Initialize DP table: dp[i][j] = max coins from range (i,j) exclusive
 * 3. Fill DP table using bottom-up approach with correct traversal order
 * 4. For each range (i,j), try all possible k as the last balloon to burst
 * 5. Return dp[0][n+1] as the final answer
 * <p>
 * Complexity Analysis:
 * - Time: O(n³) - three nested loops
 * - Space: O(n²) - 2D DP table
 * <p>
 * Key Implementation Details:
 * - Traversal order: i from n to 0, j from i+1 to n+1
 * - State transition: dp[i][j] = max(dp[i][k] + dp[k][j] + points[i]*points[k]*points[j])
 * - Base cases are automatically handled (dp[i][j] = 0 when no balloons between i and j)
 * <p>
 * Example Trace:
 * Input: [3,1,5,8] → Enhanced: [1,3,1,5,8,1]
 * Final answer: dp[0][5] = 167
 */

public class _853_c_CompleteDPSolution {
    /**
     * Main DP solution for burst balloons problem
     *
     * @param nums Original balloon array
     * @return Maximum coins obtainable
     */
    public int maxCoins(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        int n = nums.length;

        // Step 1: Add virtual balloons at both ends
        int[] points = new int[n + 2];
        points[0] = points[n + 1] = 1;
        for (int i = 1; i <= n; i++) {
            points[i] = nums[i - 1];
        }

        // Step 2: Initialize DP table
        // dp[i][j] represents max coins from bursting balloons between i and j (exclusive)
        int[][] dp = new int[n + 2][n + 2];
        // Base cases are already 0 (no balloons to burst)

        // Step 3: Fill DP table with correct traversal order
        // i goes from bottom to top (n to 0)
        for (int i = n; i >= 0; i--) {
            // j goes from left to right (i+1 to n+1)
            for (int j = i + 1; j < n + 2; j++) {
                // Try each k as the last balloon to burst in range (i,j)
                for (int k = i + 1; k < j; k++) {
                    // State transition equation
                    int coins = dp[i][k] + dp[k][j] + points[i] * points[k] * points[j];
                    dp[i][j] = Math.max(dp[i][j], coins);
                }
            }
        }

        return dp[0][n + 1];
    }

    /**
     * Enhanced version with detailed tracing for educational purposes
     */
    public int maxCoinsWithTrace(int[] nums) {
        System.out.println("=== DP Solution with Detailed Trace ===");
        System.out.println("Input: " + Arrays.toString(nums));

        int n = nums.length;

        // Step 1: Transform array
        int[] points = new int[n + 2];
        points[0] = points[n + 1] = 1;
        for (int i = 1; i <= n; i++) {
            points[i] = nums[i - 1];
        }
        System.out.println("Enhanced array: " + Arrays.toString(points));

        // Step 2: Initialize DP table
        int[][] dp = new int[n + 2][n + 2];

        System.out.println("\n=== DP Table Fill Process ===");

        // Step 3: Fill DP table
        for (int i = n; i >= 0; i--) {
            for (int j = i + 1; j < n + 2; j++) {
                if (j <= i + 1) continue; // Skip trivial cases

                System.out.println("\nComputing dp[" + i + "][" + j + "] (range (" + i + "," + j + ")):");

                for (int k = i + 1; k < j; k++) {
                    int leftPart = dp[i][k];
                    int rightPart = dp[k][j];
                    int burstCoin = points[i] * points[k] * points[j];
                    int totalCoins = leftPart + rightPart + burstCoin;

                    System.out.println("  Try k=" + k + " (balloon " + points[k] + "): " +
                            leftPart + " + " + rightPart + " + " +
                            points[i] + "*" + points[k] + "*" + points[j] +
                            " = " + totalCoins);

                    dp[i][j] = Math.max(dp[i][j], totalCoins);
                }

                System.out.println("  → dp[" + i + "][" + j + "] = " + dp[i][j]);
            }
        }

        System.out.println("\n=== Final DP Table ===");
        printDPTable(dp, n + 2);

        return dp[0][n + 1];
    }

    /**
     * Print DP table in a readable format
     */
    private void printDPTable(int[][] dp, int size) {
        System.out.print("    ");
        for (int j = 0; j < size; j++) {
            System.out.printf("%4d", j);
        }
        System.out.println();

        for (int i = 0; i < size; i++) {
            System.out.printf("%2d: ", i);
            for (int j = 0; j < size; j++) {
                if (j > i + 1) {
                    System.out.printf("%4d", dp[i][j]);
                } else {
                    System.out.print("   -");
                }
            }
            System.out.println();
        }
    }

    /**
     * Validate solution by comparing with expected results
     */
    public void validateSolution() {
        System.out.println("=== Solution Validation ===");

        // Test case 1: [3,1,5,8] → Expected: 167
        int[] test1 = {3, 1, 5, 8};
        int result1 = maxCoins(test1);
        System.out.println("Test 1: " + Arrays.toString(test1) + " → " + result1 +
                (result1 == 167 ? " ✓" : " ✗ (expected 167)"));

        // Test case 2: [1,5] → Expected: 10
        int[] test2 = {1, 5};
        int result2 = maxCoins(test2);
        System.out.println("Test 2: " + Arrays.toString(test2) + " → " + result2 +
                (result2 == 10 ? " ✓" : " ✗ (expected 10)"));

        // Test case 3: Single balloon
        int[] test3 = {5};
        int result3 = maxCoins(test3);
        System.out.println("Test 3: " + Arrays.toString(test3) + " → " + result3 +
                (result3 == 5 ? " ✓" : " ✗ (expected 5)"));

        // Test case 4: Two identical balloons
        int[] test4 = {3, 3};
        int result4 = maxCoins(test4);
        System.out.println("Test 4: " + Arrays.toString(test4) + " → " + result4 +
                (result4 == 12 ? " ✓" : " ✗ (expected 12)"));
    }
}
