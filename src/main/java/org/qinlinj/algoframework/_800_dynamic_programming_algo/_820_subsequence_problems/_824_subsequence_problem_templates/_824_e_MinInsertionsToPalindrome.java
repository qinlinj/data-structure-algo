package org.qinlinj.algoframework._800_dynamic_programming_algo._820_subsequence_problems._824_subsequence_problem_templates;

/**
 * Minimum Insertions to Make a String Palindrome - LeetCode Problem 1312
 * <p>
 * Key Concepts:
 * 1. This problem extends the Longest Palindromic Subsequence (LPS) concept
 * to find the minimum number of characters to insert to make a string palindromic.
 * 2. The key insight is that the characters NOT in the LPS must be inserted.
 * 3. Specifically, the minimum number of insertions equals (length of string - length of LPS).
 * <p>
 * DP State Definition:
 * - dp[i][j] = minimum number of insertions needed to make substring s[i...j] a palindrome
 * <p>
 * State Transitions:
 * 1. If s[i] == s[j]:
 * No need to insert characters between these matching characters
 * dp[i][j] = dp[i+1][j-1]
 * 2. If s[i] != s[j]:
 * We need to insert at least one character
 * dp[i][j] = min(dp[i+1][j], dp[i][j-1]) + 1
 * <p>
 * Alternative (simpler) solution:
 * minInsertions = length of string - length of LPS
 * <p>
 * Time and Space Complexity: O(n²)
 */
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

    /**
     * Recursively reconstructs one possible palindrome with minimum insertions
     */
    private static void reconstructPalindrome(String s, int[][] dp, int i, int j, StringBuilder result, int offset) {
        // Base case: if only one character or no character
        if (i >= j) {
            return;
        }

        // If characters match, no insertion needed
        if (s.charAt(i) == s.charAt(j)) {
            reconstructPalindrome(s, dp, i + 1, j - 1, result, offset + 1);
        }
        // If inserting at the end is optimal
        else if (dp[i][j] == dp[i][j - 1] + 1) {
            // Insert the character at position i at the end
            result.insert(j + offset, s.charAt(i));
            reconstructPalindrome(s, dp, i + 1, j, result, offset + 1);
        }
        // Otherwise, inserting at the beginning is optimal
        else {
            // Insert the character at position j at the beginning
            result.insert(i + offset, s.charAt(j));
            reconstructPalindrome(s, dp, i, j - 1, result, offset + 1);
        }
    }

    /**
     * Main method to demonstrate the Minimum Insertions algorithm
     */
    public static void main(String[] args) {
        System.out.println("=== MINIMUM INSERTIONS TO MAKE A STRING PALINDROME ===\n");

        System.out.println("Problem: Given a string, find the minimum number of characters");
        System.out.println("that need to be inserted to make it a palindrome.\n");

        // Example 1
        String s1 = "abcea";
        int min1 = minInsertions(s1);
        int min1DP = minInsertionsDP(s1);

        System.out.println("Example 1:");
        System.out.println("Input: \"" + s1 + "\"");
        System.out.println("Output (using LPS): " + min1);
        System.out.println("Output (using direct DP): " + min1DP);
        System.out.println("Possible palindromes after insertion: \"abeceba\" or \"aebcbea\"");

        // Example 2
        String s2 = "aba";
        int min2 = minInsertions(s2);
        int min2DP = minInsertionsDP(s2);

        System.out.println("\nExample 2:");
        System.out.println("Input: \"" + s2 + "\"");
        System.out.println("Output (using LPS): " + min2);
        System.out.println("Output (using direct DP): " + min2DP);
        System.out.println("The string is already a palindrome, so no insertions needed");

        // Visualization for a small example
        System.out.println("\n=== VISUALIZATION ===");
        visualizeMinInsertions("abcea");

        System.out.println("\nKey Insights:");
        System.out.println("1. Every character not in the LPS must be inserted somewhere");
        System.out.println("2. The minimum number of insertions = length of string - length of LPS");
        System.out.println("3. This problem builds directly on the LPS problem, showing how");
        System.out.println("   understanding one DP problem can help solve related problems");
    }
}