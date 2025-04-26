package org.qinlinj.algoframework._300_classical_data_structure_algo._330_array_algo_II._331_sliding_window_template;

/**
 * Minimum Window Substring (LeetCode 76)
 * <p>
 * Problem Description:
 * Given two strings s and t, find the minimum window in s that contains all characters in t.
 * If there is no such window in s that covers all characters in t, return an empty string.
 * If there are multiple answers, return the one with the minimum length.
 * <p>
 * Key Concepts:
 * 1. Use two pointers (left and right) to maintain a sliding window
 * 2. Use HashMap/Counter to track characters needed and characters in current window
 * 3. Expand window until all characters in t are covered
 * 4. Contract window to find the minimum valid substring
 * 5. Time complexity: O(N), where N is the length of string s
 * <p>
 * Example:
 * Input: s = "ADOBECODEBANC", t = "ABC"
 * Output: "BANC"
 */
public class MinimumWindowSubstring {
}
