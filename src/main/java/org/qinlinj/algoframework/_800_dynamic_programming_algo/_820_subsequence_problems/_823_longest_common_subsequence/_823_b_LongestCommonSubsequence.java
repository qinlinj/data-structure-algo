package org.qinlinj.algoframework._800_dynamic_programming_algo._820_subsequence_problems._823_longest_common_subsequence;

import java.util.*;

public class _823_b_LongestCommonSubsequence {
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

}
