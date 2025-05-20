package org.qinlinj.algoframework._800_dynamic_programming_algo._820_subsequence_problems._823_longest_common_subsequence;

import java.util.*;

/**
 * Longest Common Subsequence (LCS) - LeetCode Problem 1143
 * <p>
 * Problem Description:
 * Given two strings s1 and s2, find the length of their longest common subsequence (LCS).
 * A subsequence is a sequence that can be derived from another sequence by deleting some or no elements
 * without changing the order of the remaining elements.
 * <p>
 * Example:
 * Input: s1 = "zabcde", s2 = "acez"
 * Output: 3
 * Explanation: The LCS is "ace"
 * <p>
 * Key Insights:
 * 1. Two-pointer approach: Use pointers i and j to traverse s1 and s2
 * 2. For each character pair, we have two cases:
 * - If s1[i] == s2[j]: This character is part of the LCS
 * - If s1[i] != s2[j]: At least one character is not in the LCS
 * 3. When characters don't match, we need to consider skipping either character
 * 4. Overlapping subproblems make this perfect for dynamic programming
 * <p>
 * Two implementation approaches are demonstrated:
 * 1. Top-down (recursive with memoization)
 * 2. Bottom-up (iterative with tabulation)
 */
public class _823_b_LongestCommonSubsequence {

    /**
     * Main method to demonstrate LCS algorithms
     */
    public static void main(String[] args) {
        _823_b_LongestCommonSubsequence lcs = new _823_b_LongestCommonSubsequence();

        String s1 = "zabcde";
        String s2 = "acez";

        System.out.println("Example strings:");
        System.out.println("s1 = \"" + s1 + "\"");
        System.out.println("s2 = \"" + s2 + "\"");

        // Using top-down approach
        int lcsLength1 = lcs.longestCommonSubsequenceTopDown(s1, s2);
        System.out.println("\nTop-Down DP result: " + lcsLength1);

        // Using bottom-up approach
        int lcsLength2 = lcs.longestCommonSubsequenceBottomUp(s1, s2);
        System.out.println("Bottom-Up DP result: " + lcsLength2);

        // Visual explanation with a smaller example
        System.out.println("\n--- Detailed Explanation with Example ---");
        lcs.explainWithExample("abc", "ac");
    }

    /**
     * Top-down dynamic programming approach (recursive with memoization)
     */
    public int longestCommonSubsequenceTopDown(String s1, String s2) {
        int m = s1.length(), n = s2.length();
        // Memoization table to store already computed results
        int[][] memo = new int[m][n];
        // Initialize with -1 to indicate uncalculated states
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }

        return dpTopDown(s1, 0, s2, 0, memo);
    }

    /**
     * Helper recursive function for top-down approach
     * Defines: dp(i,j) = length of LCS of s1[i..] and s2[j..]
     */
    private int dpTopDown(String s1, int i, String s2, int j, int[][] memo) {
        // Base case: if either string reaches the end
        if (i == s1.length() || j == s2.length()) {
            return 0;
        }

        // If already calculated, return the cached result
        if (memo[i][j] != -1) {
            return memo[i][j];
        }

        // If current characters match
        if (s1.charAt(i) == s2.charAt(j)) {
            // Current character is part of LCS, add 1 and move both pointers
            memo[i][j] = 1 + dpTopDown(s1, i + 1, s2, j + 1, memo);
        } else {
            // Current characters don't match, try skipping each character
            // and take the maximum result
            memo[i][j] = Math.max(
                    // Skip character in s1
                    dpTopDown(s1, i + 1, s2, j, memo),
                    // Skip character in s2
                    dpTopDown(s1, i, s2, j + 1, memo)
            );
        }

        return memo[i][j];
    }

    /**
     * Bottom-up dynamic programming approach (iterative with tabulation)
     */
    public int longestCommonSubsequenceBottomUp(String s1, String s2) {
        int m = s1.length(), n = s2.length();

        // Define dp[i][j] = length of LCS for s1[0...i-1] and s2[0...j-1]
        int[][] dp = new int[m + 1][n + 1];

        // Fill the dp table bottom-up
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    // If characters match, extend the LCS
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    // If characters don't match, take the best result from skipping
                    // either character
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        // The final answer is in the bottom-right cell
        return dp[m][n];
    }

    /**
     * Visual explanation of the bottom-up DP approach for a simple example
     */
    public void explainWithExample(String s1, String s2) {
        int m = s1.length(), n = s2.length();
        int[][] dp = new int[m + 1][n + 1];

        System.out.println("Finding LCS of \"" + s1 + "\" and \"" + s2 + "\"");
        System.out.println("\nDP Table Construction:");

        // Print column headers
        System.out.print("    ");
        System.out.print("  "); // For empty string
        for (int j = 0; j < n; j++) {
            System.out.print(s2.charAt(j) + " ");
        }
        System.out.println();

        // Fill and print the DP table
        for (int i = 0; i <= m; i++) {
            if (i == 0) {
                System.out.print("  "); // For empty string
            } else {
                System.out.print(s1.charAt(i - 1) + " ");
            }

            for (int j = 0; j <= n; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 0; // Base case
                } else if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                    System.out.print(dp[i][j] + "* "); // Mark matches with an asterisk
                    continue;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println("\nFinal LCS length: " + dp[m][n]);

        // Backtrack to find the actual LCS
        StringBuilder lcs = new StringBuilder();
        int i = m, j = n;
        while (i > 0 && j > 0) {
            if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                lcs.append(s1.charAt(i - 1));
                i--;
                j--;
            } else if (dp[i - 1][j] > dp[i][j - 1]) {
                i--;
            } else {
                j--;
            }
        }

        System.out.println("The LCS is: " + lcs.reverse().toString());
    }

    // Ensure you import this at the top of your file
    // import java.util.Arrays;
}