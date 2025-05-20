package org.qinlinj.algoframework._800_dynamic_programming_algo._820_subsequence_problems._823_longest_common_subsequence;

import java.util.*;

public class _823_b_LongestCommonSubsequence {
    /**
     * Top-down approach with memoization
     */
    public int minimumDeleteSum(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();

        // Initialize memoization table with -1 (uncalculated)
        int[][] memo = new int[m][n];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }

        return dp(s1, 0, s2, 0, memo);
    }

    /**
     * Recursive helper function with memoization
     * dp(i,j) = minimum ASCII sum of deleted characters to make s1[i..] and s2[j..] equal
     */
    private int dp(String s1, int i, String s2, int j, int[][] memo) {
        // Base cases: if one string is exhausted, delete all remaining characters from the other
        if (i == s1.length()) {
            int asciiSum = 0;
            for (int k = j; k < s2.length(); k++) {
                asciiSum += s2.charAt(k);
            }
            return asciiSum;
        }

        if (j == s2.length()) {
            int asciiSum = 0;
            for (int k = i; k < s1.length(); k++) {
                asciiSum += s1.charAt(k);
            }
            return asciiSum;
        }

        // Check memo table
        if (memo[i][j] != -1) {
            return memo[i][j];
        }

        // If current characters match, no need to delete either
        if (s1.charAt(i) == s2.charAt(j)) {
            memo[i][j] = dp(s1, i + 1, s2, j + 1, memo);
        } else {
            // Try deleting current character from s1
            int deleteFromS1 = s1.charAt(i) + dp(s1, i + 1, s2, j, memo);

            // Try deleting current character from s2
            int deleteFromS2 = s2.charAt(j) + dp(s1, i, s2, j + 1, memo);

            // Choose the minimum ASCII sum
            memo[i][j] = Math.min(deleteFromS1, deleteFromS2);
        }

        return memo[i][j];
    }

    /**
     * Bottom-up approach using tabulation
     */
    public int minimumDeleteSumBottomUp(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();

        // dp[i][j] = minimum ASCII sum of deleted chars to make s1[0...i-1] and s2[0...j-1] equal
        int[][] dp = new int[m + 1][n + 1];

        // Base case: delete all characters from s1
        for (int i = 1; i <= m; i++) {
            dp[i][0] = dp[i - 1][0] + s1.charAt(i - 1);
        }

        // Base case: delete all characters from s2
        for (int j = 1; j <= n; j++) {
            dp[0][j] = dp[0][j - 1] + s2.charAt(j - 1);
        }

        // Fill the DP table
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    // Characters match, no need to delete
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    // Choose the minimum between deleting from s1 or s2
                    dp[i][j] = Math.min(
                            s1.charAt(i - 1) + dp[i - 1][j],  // Delete from s1
                            s2.charAt(j - 1) + dp[i][j - 1]   // Delete from s2
                    );
                }
            }
        }

        return dp[m][n];
    }

    /**
     * Helper method to calculate ASCII sum of a string
     */
    private int calcASCIISum(String s) {
        int sum = 0;
        for (char c : s.toCharArray()) {
            sum += c;
        }
        return sum;
    }
}
