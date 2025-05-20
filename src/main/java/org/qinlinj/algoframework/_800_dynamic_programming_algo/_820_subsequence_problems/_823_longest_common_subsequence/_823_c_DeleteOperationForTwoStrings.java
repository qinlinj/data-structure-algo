package org.qinlinj.algoframework._800_dynamic_programming_algo._820_subsequence_problems._823_longest_common_subsequence;

public class _823_c_DeleteOperationForTwoStrings {
    /**
     * Calculate the minimum number of deletions required
     */
    public int minDistance(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();

        // Find the length of the longest common subsequence
        int lcsLength = longestCommonSubsequence(s1, s2);

        // Calculate deletions:
        // (characters to remove from s1) + (characters to remove from s2)
        return (m - lcsLength) + (n - lcsLength);
    }
}
