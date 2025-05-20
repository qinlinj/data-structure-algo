package org.qinlinj.algoframework._800_dynamic_programming_algo._820_subsequence_problems._824_subsequence_problem_templates;

public class _824_e_MinInsertionsToPalindrome {
    /**
     * Direct approach: Minimum insertions equals length of string minus length of LPS
     */
    public static int minInsertions(String s) {
        return s.length() - longestPalindromeSubseq(s);
    }
}
