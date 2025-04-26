package org.qinlinj.algoframework._300_classical_data_structure_algo._330_array_algo_II._332_sliding_window_practice;

/**
 * LeetCode 395: Longest Substring with At Least K Repeating Characters
 * <p>
 * Problem Description:
 * Given a string s and an integer k, return the length of the longest substring of s
 * such that the frequency of each character in this substring is greater than or equal to k.
 * <p>
 * Key Insight:
 * This problem requires a modified sliding window approach with an additional constraint:
 * 1. Standard sliding window techniques cannot easily determine when to shrink the window
 * 2. We add an additional constraint: the number of unique characters in the window
 * 3. By trying all possible counts of unique characters (max 26 for lowercase letters),
 * we can use the sliding window technique for each specific count
 * <p>
 * Time Complexity: O(26 * n) = O(n) where n is the length of the string
 * Space Complexity: O(1) as we only use fixed-size arrays
 */

public class _332_h_LongestSubstringWithKRepeatingChars {
}
