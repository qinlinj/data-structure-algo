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

    /**
     * Visualizes the process of finding minimum insertions required
     */
    public static void visualizeMinInsertions(String s) {
        int n = s.length();

        // Calculate LPS first
        int[][] lpsDP = new int[n][n];

        // Base case for LPS
        for (int i = 0; i < n; i++) {
            lpsDP[i][i] = 1;
        }

        // Fill the LPS dp array
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    lpsDP[i][j] = lpsDP[i + 1][j - 1] + 2;
                } else {
                    lpsDP[i][j] = Math.max(lpsDP[i + 1][j], lpsDP[i][j - 1]);
                }
            }
        }

        // Calculate min insertions directly
        int[][] insertDP = new int[n][n];

        // Fill the insertions dp array
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    insertDP[i][j] = insertDP[i + 1][j - 1];
                } else {
                    insertDP[i][j] = Math.min(insertDP[i + 1][j], insertDP[i][j - 1]) + 1;
                }
            }
        }

        System.out.println("String: " + s);

        // Show LPS calculation
        System.out.println("\nLongest Palindromic Subsequence (LPS) Matrix:");
        printMatrix(lpsDP, s);
        System.out.println("LPS Length: " + lpsDP[0][n - 1]);

        // Show min insertions calculation
        System.out.println("\nMinimum Insertions Matrix:");
        printMatrix(insertDP, s);
        System.out.println("Minimum Insertions: " + insertDP[0][n - 1]);

        // Verify the relationship
        System.out.println("\nVerifying relationship:");
        System.out.println("String length: " + n);
        System.out.println("LPS length: " + lpsDP[0][n - 1]);
        System.out.println("Min insertions: " + insertDP[0][n - 1]);
        System.out.println("String length - LPS length = " + (n - lpsDP[0][n - 1]));

        if (insertDP[0][n - 1] == n - lpsDP[0][n - 1]) {
            System.out.println("Relationship verified! Min insertions = String length - LPS length");
        }

        // Reconstruct a possible result
        StringBuilder result = new StringBuilder(s);
        int insertionsMade = 0;

        // Find one possible way to make the string a palindrome
        System.out.println("\nOne way to make the string a palindrome:");
        reconstructPalindrome(s, insertDP, 0, n - 1, result, insertionsMade);
        System.out.println("Original: " + s);
        System.out.println("Palindrome: " + result.toString());
    }

    /**
     * Helper method to print a matrix
     */
    private static void printMatrix(int[][] matrix, String s) {
        int n = s.length();

        // Print column headers
        System.out.print("    ");
        for (int j = 0; j < n; j++) {
            System.out.print(s.charAt(j) + " ");
        }
        System.out.println();

        // Print the matrix
        for (int i = 0; i < n; i++) {
            System.out.print(s.charAt(i) + "   ");
            for (int j = 0; j < n; j++) {
                if (j < i) {
                    System.out.print("- "); // Lower triangle is not used
                } else {
                    System.out.print(matrix[i][j] + " ");
                }
            }
            System.out.println();
        }
    }
}
