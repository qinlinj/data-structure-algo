package org.qinlinj.algoframework._800_dynamic_programming_algo._820_subsequence_problems._823_longest_common_subsequence;

import java.util.*;

/**
 * Minimum ASCII Delete Sum for Two Strings - LeetCode Problem 712
 * <p>
 * Problem Description:
 * Given two strings s1 and s2, return the lowest ASCII sum of deleted characters to make two strings equal.
 * <p>
 * Example:
 * Input: s1 = "sea", s2 = "eat"
 * Output: 231
 * Explanation: Delete 's' from "sea" and 't' from "eat" => "ea" == "ea"
 * ASCII sum = s(115) + t(116) = 231
 * <p>
 * Key Insights:
 * 1. This problem is a variation of the Longest Common Subsequence (LCS) problem
 * 2. Instead of counting deletions, we need to sum the ASCII values of deleted characters
 * 3. The approach remains similar, but with modified state transitions
 * 4. We can use either top-down (recursive with memoization) or bottom-up (iterative) DP
 * 5. The goal is to minimize the sum of ASCII values of deleted characters
 * <p>
 * This class demonstrates the top-down approach with detailed explanations.
 */
public class _823_d_MinimumASCIIDeleteSum {

    /**
     * Main method to demonstrate the algorithm
     */
    public static void main(String[] args) {
        _823_d_MinimumASCIIDeleteSum solution = new _823_d_MinimumASCIIDeleteSum();

        // Example from LeetCode
        String s1 = "sea";
        String s2 = "eat";

        // Using top-down approach
        int resultTopDown = solution.minimumDeleteSum(s1, s2);
        System.out.println("Minimum ASCII Delete Sum (Top-Down): " + resultTopDown);

        // Using bottom-up approach
        int resultBottomUp = solution.minimumDeleteSumBottomUp(s1, s2);
        System.out.println("Minimum ASCII Delete Sum (Bottom-Up): " + resultBottomUp);

        // Detailed explanation
        System.out.println("\n--- Detailed Explanation ---");
        solution.explainWithExample(s1, s2);

        // Another example
        System.out.println("\n\n--- Another Example ---");
        s1 = "delete";
        s2 = "leet";
        resultBottomUp = solution.minimumDeleteSumBottomUp(s1, s2);
        System.out.println("Minimum ASCII Delete Sum: " + resultBottomUp);
        solution.explainWithExample(s1, s2);
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

    /**
     * Visual explanation of the solution process
     */
    public void explainWithExample(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();

        // Initialize dp table
        int[][] dp = new int[m + 1][n + 1];

        // Base cases
        for (int i = 1; i <= m; i++) {
            dp[i][0] = dp[i - 1][0] + s1.charAt(i - 1);
        }

        for (int j = 1; j <= n; j++) {
            dp[0][j] = dp[0][j - 1] + s2.charAt(j - 1);
        }

        // Fill the dp table
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(
                            s1.charAt(i - 1) + dp[i - 1][j],
                            s2.charAt(j - 1) + dp[i][j - 1]
                    );
                }
            }
        }

        // Display explanation
        System.out.println("Given strings:");
        System.out.println("s1 = \"" + s1 + "\"");
        System.out.println("s2 = \"" + s2 + "\"");

        System.out.println("\nDP Table (Minimum ASCII Sum of Deleted Characters):");

        // Print column headers
        System.out.print("     ");
        System.out.print("\"\"  "); // Empty string
        for (int j = 0; j < n; j++) {
            System.out.printf("%c(%3d) ", s2.charAt(j), (int) s2.charAt(j));
        }
        System.out.println();

        // Print the table with ASCII values
        for (int i = 0; i <= m; i++) {
            if (i == 0) {
                System.out.print("\"\"   ");
            } else {
                System.out.printf("%c(%3d) ", s1.charAt(i - 1), (int) s1.charAt(i - 1));
            }

            for (int j = 0; j <= n; j++) {
                System.out.printf("%5d ", dp[i][j]);
            }
            System.out.println();
        }

        // Backtrack to find the common subsequence and the deleted characters
        StringBuilder common = new StringBuilder();
        StringBuilder deletedFromS1 = new StringBuilder();
        StringBuilder deletedFromS2 = new StringBuilder();

        int i = m, j = n;
        while (i > 0 && j > 0) {
            if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                // Characters match
                common.append(s1.charAt(i - 1));
                i--;
                j--;
            } else if (dp[i][j] == s1.charAt(i - 1) + dp[i - 1][j]) {
                // Delete from s1
                deletedFromS1.append(s1.charAt(i - 1));
                i--;
            } else {
                // Delete from s2
                deletedFromS2.append(s2.charAt(j - 1));
                j--;
            }
        }

        // Add any remaining characters to deleted lists
        while (i > 0) {
            deletedFromS1.append(s1.charAt(i - 1));
            i--;
        }

        while (j > 0) {
            deletedFromS2.append(s2.charAt(j - 1));
            j--;
        }

        // Reverse the results (since we built them backward)
        common.reverse();
        deletedFromS1.reverse();
        deletedFromS2.reverse();

        // Calculate ASCII sums
        int deletedS1Sum = calcASCIISum(deletedFromS1.toString());
        int deletedS2Sum = calcASCIISum(deletedFromS2.toString());
        int totalSum = deletedS1Sum + deletedS2Sum;

        System.out.println("\nCommon subsequence: \"" + common + "\"");
        System.out.println("Deleted from s1: \"" + deletedFromS1 + "\" (ASCII sum: " + deletedS1Sum + ")");
        System.out.println("Deleted from s2: \"" + deletedFromS2 + "\" (ASCII sum: " + deletedS2Sum + ")");
        System.out.println("Total ASCII sum of deleted characters: " + totalSum);

        // Display ASCII values of each deleted character
        System.out.println("\nASCII values of deleted characters from s1:");
        for (char c : deletedFromS1.toString().toCharArray()) {
            System.out.println(c + ": " + (int) c);
        }

        System.out.println("\nASCII values of deleted characters from s2:");
        for (char c : deletedFromS2.toString().toCharArray()) {
            System.out.println(c + ": " + (int) c);
        }
    }

    // Ensure you import this at the top of your file
    // import java.util.Arrays;
}