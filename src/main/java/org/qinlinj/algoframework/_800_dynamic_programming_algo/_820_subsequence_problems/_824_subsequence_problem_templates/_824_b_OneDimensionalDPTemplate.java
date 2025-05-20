package org.qinlinj.algoframework._800_dynamic_programming_algo._820_subsequence_problems._824_subsequence_problem_templates;

public class _824_b_OneDimensionalDPTemplate {
    /**
     * Two-Dimensional DP Template for Subsequence Problems
     * <p>
     * Key Concepts:
     * 1. The two-dimensional DP template uses a matrix where each cell represents
     * the optimal solution for a specific range or pair of indices.
     * 2. This approach is typically used for:
     * - Problems involving two sequences (like Longest Common Subsequence)
     * - Problems involving ranges in a single sequence (like Longest Palindromic Subsequence)
     * 3. For two sequences, dp[i][j] typically represents the solution for the prefixes
     * ending at indices i and j of the respective sequences.
     * 4. For ranges in a single sequence, dp[i][j] typically represents the solution
     * for the subsequence from index i to j.
     * 5. Time and space complexity are generally O(n²).
     * <p>
     * This class introduces the two-dimensional DP template with examples.
     */
    public class _824_c_TwoDimensionalDPTemplate {

        /**
         * General template explanation for the two-dimensional DP approach
         */
        public static void explainTemplate() {
            System.out.println("The two-dimensional DP template follows this pattern:");
            System.out.println("1. Define a 2D array dp[i][j] that represents:");
            System.out.println("   - For two sequences: Solution for prefixes s1[0...i] and s2[0...j]");
            System.out.println("   - For a single sequence: Solution for the subsequence s[i...j]");
            System.out.println("2. Initialize base cases (like empty strings or single characters)");
            System.out.println("3. Fill the dp array, typically using nested loops");
            System.out.println("4. For single sequence range problems, often use a specific traversal order:");
            System.out.println("   - Either from shorter ranges to longer ranges");
            System.out.println("   - Or from bottom-right to top-left of the dp matrix");
            System.out.println("5. The final answer is typically found at dp[n][m] or dp[0][n-1]");
        }

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
    }
}
