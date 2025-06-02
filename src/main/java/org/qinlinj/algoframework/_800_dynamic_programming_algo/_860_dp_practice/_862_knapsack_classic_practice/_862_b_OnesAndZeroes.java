package org.qinlinj.algoframework._800_dynamic_programming_algo._860_dp_practice._862_knapsack_classic_practice;

// ==================== Problem 2: Ones and Zeroes ====================
class _862_b_OnesAndZeroes {
    public static void main(String[] args) {
        _862_b_OnesAndZeroes solution = new _862_b_OnesAndZeroes();

        // Test case 1: strs = ["10","0001","111001","1","0"], m = 5, n = 3 -> Expected: 4
        String[] strs1 = {"10", "0001", "111001", "1", "0"};
        System.out.println("Test 1 - Expected: 4, Got: " + solution.findMaxForm(strs1, 5, 3));
        System.out.println("Test 1 Optimized - Expected: 4, Got: " + solution.findMaxFormOptimized(strs1, 5, 3));

        // Test case 2: strs = ["10","0","1"], m = 1, n = 1 -> Expected: 2
        String[] strs2 = {"10", "0", "1"};
        System.out.println("Test 2 - Expected: 2, Got: " + solution.findMaxForm(strs2, 1, 1));
        System.out.println("Test 2 Optimized - Expected: 2, Got: " + solution.findMaxFormOptimized(strs2, 1, 1));

        // Test case 3: Edge case
        String[] strs3 = {"0", "1"};
        System.out.println("Test 3 - Expected: 2, Got: " + solution.findMaxForm(strs3, 1, 1));
    }

    /**
     * LeetCode 474: Ones and Zeroes
     * <p>
     * Problem: Given array of binary strings and integers m, n.
     * Find maximum subset length with at most m zeros and n ones.
     * <p>
     * Key Insight: 2D Knapsack Problem
     * - Standard knapsack has 1 constraint (weight/capacity)
     * - This problem has 2 constraints (count of 0s and 1s)
     * - Each string is an "item" with "weight" = (zeros_count, ones_count) and "value" = 1
     * <p>
     * DP Definition: dp[i][j][k] = maximum strings using first i strings
     * with at most j zeros and k ones
     * <p>
     * State Transition:
     * - If current string has zeroCount zeros and oneCount ones:
     * - If j >= zeroCount && k >= oneCount: can include current string
     * dp[i][j][k] = max(dp[i-1][j][k], dp[i-1][j-zeroCount][k-oneCount] + 1)
     * - Else: cannot include
     * dp[i][j][k] = dp[i-1][j][k]
     */

    public int findMaxForm(String[] strs, int m, int n) {
        // dp[i][j][k] = max strings using first i strings with ≤j zeros and ≤k ones
        int[][][] dp = new int[strs.length + 1][m + 1][n + 1];

        // Base case: dp[0][j][k] = 0 (no strings available)

        for (int i = 1; i <= strs.length; i++) {
            String currentStr = strs[i - 1]; // Convert to 0-based index

            // Count zeros and ones in current string
            int zeroCount = 0, oneCount = 0;
            for (char c : currentStr.toCharArray()) {
                if (c == '0') zeroCount++;
                else oneCount++;
            }

            for (int j = 0; j <= m; j++) {
                for (int k = 0; k <= n; k++) {
                    if (j >= zeroCount && k >= oneCount) {
                        // Can include current string
                        dp[i][j][k] = Math.max(
                                dp[i - 1][j][k],                                    // Don't include
                                dp[i - 1][j - zeroCount][k - oneCount] + 1         // Include
                        );
                    } else {
                        // Cannot include current string
                        dp[i][j][k] = dp[i - 1][j][k];
                    }
                }
            }
        }

        return dp[strs.length][m][n];
    }

    // Space-optimized version using 2D array (rolling array technique)
    public int findMaxFormOptimized(String[] strs, int m, int n) {
        // dp[j][k] = max strings with ≤j zeros and ≤k ones
        int[][] dp = new int[m + 1][n + 1];

        for (String str : strs) {
            int zeroCount = 0, oneCount = 0;
            for (char c : str.toCharArray()) {
                if (c == '0') zeroCount++;
                else oneCount++;
            }

            // Traverse backwards to avoid using updated values
            for (int j = m; j >= zeroCount; j--) {
                for (int k = n; k >= oneCount; k--) {
                    dp[j][k] = Math.max(dp[j][k], dp[j - zeroCount][k - oneCount] + 1);
                }
            }
        }

        return dp[m][n];
    }
}