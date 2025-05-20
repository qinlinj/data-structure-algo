package org.qinlinj.algoframework._800_dynamic_programming_algo._820_subsequence_problems._824_subsequence_problem_templates;

/**
 * Longest Palindromic Subsequence - LeetCode Problem 516
 * <p>
 * Key Concepts:
 * 1. This problem demonstrates using the 2D DP template for a single sequence
 * where dp[i][j] represents the solution for a specific range (i to j).
 * 2. A palindromic subsequence reads the same forward and backward.
 * 3. The problem asks for the length of the longest palindromic subsequence in a string.
 * <p>
 * DP State Definition:
 * - dp[i][j] = length of the longest palindromic subsequence in the substring s[i...j]
 * <p>
 * State Transitions:
 * 1. If s[i] == s[j]:
 * The characters at both ends match, so they can be part of the palindrome
 * dp[i][j] = dp[i+1][j-1] + 2
 * 2. If s[i] != s[j]:
 * The characters don't match, so either skip the left or right character
 * dp[i][j] = max(dp[i+1][j], dp[i][j-1])
 * <p>
 * Base Case:
 * - dp[i][i] = 1 (single character is a palindrome of length 1)
 * <p>
 * Time and Space Complexity: O(n²)
 */
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

    /**
     * Visualizes the DP process for finding the longest palindromic subsequence
     */
    public static void visualizeLPS(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];

        System.out.println("String: " + s);
        System.out.println("\nStep-by-step DP calculation:");
        System.out.println("----------------------------");

        // Initialize the base case
        System.out.println("Base case: Single characters are palindromes of length 1");
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }

        System.out.println("\nInitial DP matrix:");
        printDPMatrix(dp, s);

        // Fill the DP array
        for (int len = 2; len <= n; len++) {
            System.out.println("\nProcessing substrings of length " + len + ":");

            for (int i = 0; i <= n - len; i++) {
                int j = i + len - 1;

                System.out.println("  Substring s[" + i + "..." + j + "] = \"" + s.substring(i, j + 1) + "\"");

                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                    System.out.println("    s[" + i + "] = s[" + j + "] = '" + s.charAt(i) + "', adding 2 to inner substring");
                    System.out.println("    dp[" + i + "][" + j + "] = dp[" + (i + 1) + "][" + (j - 1) + "] + 2 = " + dp[i + 1][j - 1] + " + 2 = " + dp[i][j]);
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                    System.out.println("    s[" + i + "] = '" + s.charAt(i) + "' != s[" + j + "] = '" + s.charAt(j) + "', taking max");
                    System.out.println("    dp[" + i + "][" + j + "] = max(dp[" + (i + 1) + "][" + j + "], dp[" + i + "][" + (j - 1) + "]) = max(" + dp[i + 1][j] + ", " + dp[i][j - 1] + ") = " + dp[i][j]);
                }
            }

            System.out.println("\n  DP matrix after processing length " + len + ":");
            printDPMatrix(dp, s);
        }

        System.out.println("\nFinal result: dp[0][" + (n - 1) + "] = " + dp[0][n - 1]);
        System.out.println("The longest palindromic subsequence has length " + dp[0][n - 1]);

        // Reconstruct the LPS
        StringBuilder lps = new StringBuilder();
        reconstructLPS(dp, s, 0, n - 1, lps);
        System.out.println("One possible longest palindromic subsequence: " + lps.toString());
    }

    /**
     * Helper method to print the DP matrix
     */
    private static void printDPMatrix(int[][] dp, String s) {
        int n = s.length();

        // Print column headers
        System.out.print("    ");
        for (int j = 0; j < n; j++) {
            System.out.print(s.charAt(j) + " ");
        }
        System.out.println();

        // Print the dp matrix
        for (int i = 0; i < n; i++) {
            System.out.print(s.charAt(i) + "   ");
            for (int j = 0; j < n; j++) {
                if (j < i) {
                    System.out.print("- "); // Lower triangle is not used
                } else {
                    System.out.print(dp[i][j] + " ");
                }
            }
            System.out.println();
        }
    }

    /**
     * Reconstructs the longest palindromic subsequence from the DP matrix
     */
    private static void reconstructLPS(int[][] dp, String s, int i, int j, StringBuilder result) {
        // Base case: if only one character
        if (i == j) {
            result.append(s.charAt(i));
            return;
        }

        // Base case: if only two characters and they are equal
        if (i + 1 == j && s.charAt(i) == s.charAt(j)) {
            result.append(s.charAt(i));
            result.append(s.charAt(j));
            return;
        }

        // If the first and last characters are the same
        if (s.charAt(i) == s.charAt(j)) {
            result.append(s.charAt(i));
            reconstructLPS(dp, s, i + 1, j - 1, result);
            result.append(s.charAt(j));
        }
        // If the first and last characters are different
        else if (dp[i][j] == dp[i + 1][j]) {
            reconstructLPS(dp, s, i + 1, j, result);
        } else {
            reconstructLPS(dp, s, i, j - 1, result);
        }
    }

    /**
     * Main method to demonstrate the Longest Palindromic Subsequence algorithm
     */
    public static void main(String[] args) {
        System.out.println("=== LONGEST PALINDROMIC SUBSEQUENCE ===\n");

        System.out.println("Problem: Given a string, find the length of its longest palindromic subsequence");
        System.out.println("A palindromic subsequence is a subsequence that reads the same backward as forward");

        // Example 1
        String s1 = "bbbab";
        int length1 = longestPalindromeSubseq(s1);
        System.out.println("\nExample 1:");
        System.out.println("Input: \"" + s1 + "\"");
        System.out.println("Output: " + length1);
        System.out.println("Explanation: The LPS is \"bbbb\", with length " + length1);

        // Example 2
        String s2 = "cbbd";
        int length2 = longestPalindromeSubseq(s2);
        System.out.println("\nExample 2:");
        System.out.println("Input: \"" + s2 + "\"");
        System.out.println("Output: " + length2);
        System.out.println("Explanation: The LPS is \"bb\", with length " + length2);

        // Visualization for a small example
        System.out.println("\n=== VISUALIZATION ===");
        visualizeLPS("aecda");

        System.out.println("\nKey Insight:");
        System.out.println("- This problem demonstrates how to use a 2D DP array for a single sequence");
        System.out.println("- The dp[i][j] represents the LPS length in the substring s[i...j]");
        System.out.println("- We process the matrix either diagonally or from bottom to top");
        System.out.println("- The traversal order is crucial to ensure dependencies are calculated first");
    }
}