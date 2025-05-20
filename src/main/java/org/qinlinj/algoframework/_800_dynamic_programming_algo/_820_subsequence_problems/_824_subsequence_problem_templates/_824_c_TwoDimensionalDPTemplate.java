package org.qinlinj.algoframework._800_dynamic_programming_algo._820_subsequence_problems._824_subsequence_problem_templates;

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

    /**
     * Print the dp array for visualization
     */
    private static void printDP(int[][] dp, String s) {
        int n = s.length();

        // Print column headers
        System.out.print("    ");
        for (int j = 0; j < n; j++) {
            System.out.print(s.charAt(j) + " ");
        }
        System.out.println();

        // Print the dp array
        for (int i = 0; i < n; i++) {
            System.out.print(s.charAt(i) + " ");
            for (int j = 0; j < n; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * Main method to demonstrate the two-dimensional DP template
     */
    public static void main(String[] args) {
        System.out.println("=== TWO-DIMENSIONAL DP TEMPLATE FOR SUBSEQUENCE PROBLEMS ===\n");

        // Explain the template
        explainTemplate();

        // Example: Longest Common Subsequence
        System.out.println("\nExample: Longest Common Subsequence");
        System.out.println("----------------------------------");
        String text1 = "abcde";
        String text2 = "ace";
        int lcsLength = longestCommonSubsequence(text1, text2);
        System.out.println("LCS length: " + lcsLength);

        // Visualize LCS
        System.out.println("\nDetailed visualization:");
        visualizeLCS(text1, text2);

        System.out.println("\nThis problem demonstrates the two-dimensional DP template for two sequences.");
        System.out.println("The next problem will show how to use this template for ranges in a single sequence.");
    }
}