package org.qinlinj.algoframework._800_dynamic_programming_algo._820_subsequence_problems._824_subsequence_problem_templates;

public class _824_c_TwoDimensionalDPTemplate {
    /**
     * Two-sequence example: Longest Common Subsequence (LCS)
     */
    public static int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();

        // dp[i][j] represents the length of LCS for text1[0...i-1] and text2[0...j-1]
        int[][] dp = new int[m + 1][n + 1];

        // Fill the dp table
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    // If current characters match, extend the LCS
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    // Otherwise, take the best from skipping one character from either string
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[m][n];
    }

    /**
     * Visualize the LCS algorithm to demonstrate the 2D DP template
     */
    public static void visualizeLCS(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();
        int[][] dp = new int[m + 1][n + 1];

        System.out.println("String 1: " + text1);
        System.out.println("String 2: " + text2);
        System.out.println("\nBuilding DP table for Longest Common Subsequence:");

        // Print column headers
        System.out.print("    ");
        System.out.print("  "); // For empty string
        for (int j = 0; j < n; j++) {
            System.out.print(text2.charAt(j) + " ");
        }
        System.out.println();

        // Fill and print the DP table
        for (int i = 0; i <= m; i++) {
            if (i == 0) {
                System.out.print("  "); // For empty string
            } else {
                System.out.print(text1.charAt(i - 1) + " ");
            }

            for (int j = 0; j <= n; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 0; // Base case
                } else if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    System.out.print(dp[i][j] + "* "); // Mark matches with an asterisk
                    continue;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }

        // Find the LCS by backtracking
        StringBuilder lcs = new StringBuilder();
        int i = m, j = n;
        while (i > 0 && j > 0) {
            if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                lcs.append(text1.charAt(i - 1));
                i--;
                j--;
            } else if (dp[i - 1][j] > dp[i][j - 1]) {
                i--;
            } else {
                j--;
            }
        }

        System.out.println("\nLongest Common Subsequence: " + lcs.reverse().toString());
        System.out.println("Length: " + dp[m][n]);
    }

}
