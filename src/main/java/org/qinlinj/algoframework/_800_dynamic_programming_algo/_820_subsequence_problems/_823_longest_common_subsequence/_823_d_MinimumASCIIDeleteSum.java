package org.qinlinj.algoframework._800_dynamic_programming_algo._820_subsequence_problems._823_longest_common_subsequence;

import java.util.*;

public class _823_d_MinimumASCIIDeleteSum {
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

}
