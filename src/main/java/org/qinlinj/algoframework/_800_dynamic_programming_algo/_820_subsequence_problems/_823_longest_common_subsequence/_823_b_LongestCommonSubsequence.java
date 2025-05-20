package org.qinlinj.algoframework._800_dynamic_programming_algo._820_subsequence_problems._823_longest_common_subsequence;

import java.util.*;

public class _823_b_LongestCommonSubsequence {
    /**
     * Provide practical tips for solving subsequence problems
     */
    public static void practicalTips() {
        System.out.println("Practical Tips for Solving Subsequence Problems:");
        System.out.println("---------------------------------------------");
        System.out.println("1. Start by defining what your dp state represents precisely");
        System.out.println("2. Think about the decision at each step (e.g., include or skip a character)");
        System.out.println("3. Draw small examples and trace through the algorithm manually");
        System.out.println("4. Pay special attention to base cases and boundary conditions");
        System.out.println("5. Consider space optimization if needed (usually only keeping the last row/two rows)");
        System.out.println("6. For complex problems, start with the recursive solution, then add memoization");
        System.out.println("7. Convert to bottom-up only after understanding the solution completely");
        System.out.println();
    }

    /**
     * Demonstrate a simple application of the pattern to another problem
     */
    public static int longestPalindromicSubsequence(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];

        // Base case: single characters are palindromes of length 1
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }

        // Fill the dp table for substrings of length 2 and more
        for (int len = 2; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) {
                int j = i + len - 1;
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = 2 + (len > 2 ? dp[i + 1][j - 1] : 0);
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[0][n - 1];
    }

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
