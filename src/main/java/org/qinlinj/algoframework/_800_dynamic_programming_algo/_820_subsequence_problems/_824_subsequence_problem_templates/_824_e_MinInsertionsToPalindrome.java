package org.qinlinj.algoframework._800_dynamic_programming_algo._820_subsequence_problems._824_subsequence_problem_templates;

public class _824_e_MinInsertionsToPalindrome {
    /**
     * Direct approach: Minimum insertions equals length of string minus length of LPS
     */
    public static int minInsertions(String s) {
        return s.length() - longestPalindromeSubseq(s);
    }

    /**
     * Finds the length of the longest palindromic subsequence
     * (reused from the previous problem)
     */
    public static int longestPalindromeSubseq(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];

        // Base case: single characters are palindromes
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }

        // Fill the dp array
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[0][n - 1];
    }

    /**
     * Alternative approach: directly calculate minimum insertions with DP
     */
    public static int minInsertionsDP(String s) {
        int n = s.length();

        // dp[i][j] = min insertions to make s[i...j] a palindrome
        int[][] dp = new int[n][n];

        // Fill the dp array
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i + 1][j], dp[i][j - 1]) + 1;
                }
            }
        }

        return dp[0][n - 1];
    }
}
