package org.qinlinj.algoframework._800_dynamic_programming_algo._860_dp_practice._864_dp_classic_practice_II;

/**
 * LeetCode 97. Interleaving String - Dynamic Programming Solution
 * <p>
 * PROBLEM SUMMARY:
 * Given three strings s1, s2, and s3, determine if s3 is formed by interleaving s1 and s2.
 * An interleaving of two strings maintains the relative order of characters from each string.
 * <p>
 * KEY CONCEPTS:
 * 1. Two-pointer technique with exhaustive search (not greedy)
 * 2. When both s1[i] and s2[j] can match s3[k], we need to try both possibilities
 * 3. Memoization to handle overlapping subproblems
 * 4. State Definition: dp(i, j) = whether s1[i..] and s2[j..] can form s3[i+j..]
 * 5. Base Case: When we've processed all of s3 (k == s3.length)
 * <p>
 * TIME COMPLEXITY: O(m × n) where m = s1.length, n = s2.length
 * SPACE COMPLEXITY: O(m × n) for memoization
 * <p>
 * EXAMPLE:
 * s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
 * Output: true (one valid interleaving: a[a]d[bb]c[b]c[a]c where [] = from s2)
 */

import java.util.*;

public class _864_a_InterleavingString {
    private int[][] memo;

    public static void main(String[] args) {
        _864_a_InterleavingString solution = new _864_a_InterleavingString();

        System.out.println("=== Interleaving String Tests ===");

        // Test case 1: Valid interleaving
        String s1_1 = "aabcc";
        String s2_1 = "dbbca";
        String s3_1 = "aadbbcbcac";

        System.out.println("Test Case 1:");
        System.out.printf("s1 = \"%s\", s2 = \"%s\", s3 = \"%s\"\n", s1_1, s2_1, s3_1);

        boolean result1_memo = solution.isInterleave(s1_1, s2_1, s3_1);
        boolean result1_dp = solution.isInterleaveBottomUp(s1_1, s2_1, s3_1);

        System.out.printf("Result (Memoization): %b\n", result1_memo);
        System.out.printf("Result (Bottom-up DP): %b\n", result1_dp);
        System.out.println("Explanation: Valid interleaving - a[a]d[bb]c[b]c[a]c ([] from s2)\n");

        // Test case 2: Invalid interleaving
        String s1_2 = "aabcc";
        String s2_2 = "dbbca";
        String s3_2 = "aadbbbaccc";

        System.out.println("Test Case 2:");
        System.out.printf("s1 = \"%s\", s2 = \"%s\", s3 = \"%s\"\n", s1_2, s2_2, s3_2);

        boolean result2 = solution.isInterleave(s1_2, s2_2, s3_2);
        System.out.printf("Result: %b\n", result2);
        System.out.println("Explanation: Cannot maintain relative order of characters\n");

        // Test case 3: Empty strings
        String s1_3 = "";
        String s2_3 = "";
        String s3_3 = "";

        System.out.println("Test Case 3:");
        System.out.printf("s1 = \"%s\", s2 = \"%s\", s3 = \"%s\"\n", s1_3, s2_3, s3_3);

        boolean result3 = solution.isInterleave(s1_3, s2_3, s3_3);
        System.out.printf("Result: %b\n", result3);
        System.out.println("Explanation: Empty strings can form empty string\n");

        // Demonstration of algorithm thinking process
        System.out.println("=== Algorithm Analysis ===");
        System.out.println("Why we need exhaustive search (not greedy):");
        System.out.println("Consider s1='ab', s2='ac', s3='aabc'");
        System.out.println("- At position 0: both s1[0]='a' and s2[0]='a' match s3[0]='a'");
        System.out.println("- Greedy choice might pick wrong option");
        System.out.println("- Need to try both: use s1[0] OR use s2[0]");
        System.out.println("- Memoization prevents recalculating same subproblems");
        System.out.println();
        System.out.println("State transition logic:");
        System.out.println("dp(i,j) = (s1[i]==s3[i+j] && dp(i+1,j)) || (s2[j]==s3[i+j] && dp(i,j+1))");
    }

    public boolean isInterleave(String s1, String s2, String s3) {
        int m = s1.length(), n = s2.length();

        // Quick check: if lengths don't match, impossible
        if (m + n != s3.length()) {
            return false;
        }

        // Initialize memoization: -1 = uncomputed, 0 = false, 1 = true
        memo = new int[m + 1][n + 1];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }

        return dp(s1, 0, s2, 0, s3);
    }

    /**
     * Definition: Check if s1[i..] and s2[j..] can form s3[i+j..]
     *
     * @param s1 First string
     * @param i  Current index in s1
     * @param s2 Second string
     * @param j  Current index in s2
     * @param s3 Target string
     * @return true if interleaving is possible
     */
    private boolean dp(String s1, int i, String s2, int j, String s3) {
        int k = i + j; // Current position in s3

        // Base case: we've successfully formed all of s3
        if (k == s3.length()) {
            return true;
        }

        // Check memoization
        if (memo[i][j] != -1) {
            return memo[i][j] == 1;
        }

        boolean result = false;

        // Option 1: Use character from s1 if it matches s3[k]
        if (i < s1.length() && s1.charAt(i) == s3.charAt(k)) {
            result = dp(s1, i + 1, s2, j, s3);
        }

        // Option 2: Use character from s2 if it matches s3[k]
        // Note: We use OR (||) because if either option works, the result is true
        if (j < s2.length() && s2.charAt(j) == s3.charAt(k)) {
            result = result || dp(s1, i, s2, j + 1, s3);
        }

        // Store result in memo and return
        memo[i][j] = result ? 1 : 0;
        return result;
    }

    // Alternative bottom-up DP approach for comparison
    public boolean isInterleaveBottomUp(String s1, String s2, String s3) {
        int m = s1.length(), n = s2.length();

        if (m + n != s3.length()) {
            return false;
        }

        // dp[i][j] = whether s1[0..i-1] and s2[0..j-1] can form s3[0..i+j-1]
        boolean[][] dp = new boolean[m + 1][n + 1];

        // Base case: empty strings can form empty string
        dp[0][0] = true;

        // Fill first row: using only s2
        for (int j = 1; j <= n; j++) {
            dp[0][j] = dp[0][j - 1] && s2.charAt(j - 1) == s3.charAt(j - 1);
        }

        // Fill first column: using only s1
        for (int i = 1; i <= m; i++) {
            dp[i][0] = dp[i - 1][0] && s1.charAt(i - 1) == s3.charAt(i - 1);
        }

        // Fill the rest of the table
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                int k = i + j - 1; // Current position in s3
                dp[i][j] = (dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(k)) ||
                        (dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(k));
            }
        }

        return dp[m][n];
    }
}