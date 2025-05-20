package org.qinlinj.algoframework._800_dynamic_programming_algo._820_subsequence_problems._823_longest_common_subsequence;

public class _823_c_DeleteOperationForTwoStrings {
    /**
     * Calculate the minimum number of deletions required
     */
    public int minDistance(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();

        // Find the length of the longest common subsequence
        int lcsLength = longestCommonSubsequence(s1, s2);

        // Calculate deletions:
        // (characters to remove from s1) + (characters to remove from s2)
        return (m - lcsLength) + (n - lcsLength);
    }

    /**
     * Calculate the length of the longest common subsequence (bottom-up DP approach)
     */
    private int longestCommonSubsequence(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();

        // Define dp[i][j] = length of LCS for s1[0...i-1] and s2[0...j-1]
        int[][] dp = new int[m + 1][n + 1];

        // Fill the dp table
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[m][n];
    }
}
