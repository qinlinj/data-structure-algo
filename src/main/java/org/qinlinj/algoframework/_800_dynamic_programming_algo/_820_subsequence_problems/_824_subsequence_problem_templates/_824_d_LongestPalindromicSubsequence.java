package org.qinlinj.algoframework._800_dynamic_programming_algo._820_subsequence_problems._824_subsequence_problem_templates;

public class _824_d_LongestPalindromicSubsequence {
    /**
     * Finds the length of the longest palindromic subsequence in a string
     * using dynamic programming
     *
     * @param s the input string
     * @return the length of the longest palindromic subsequence
     */
    public static int longestPalindromeSubseq(String s) {
        int n = s.length();

        // dp[i][j] = length of the longest palindromic subsequence in s[i...j]
        int[][] dp = new int[n][n];

        // Base case: single characters are palindromes of length 1
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }

        // Fill the dp array
        // We need to process diagonally (shorter substrings to longer ones)
        // Alternatively, we can process row by row from bottom to top
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    // If characters match, add 2 to the result from the inner substring
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    // If characters don't match, take the best of skipping either character
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }

        // The answer is in dp[0][n-1], representing the entire string
        return dp[0][n - 1];
    }
}
