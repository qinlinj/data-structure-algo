package org.qinlinj.algoframework._300_classical_data_structure_algo._330_array_algo_II._332_sliding_window_practice;

/**
 * LeetCode 424: Longest Repeating Character Replacement
 * <p>
 * Problem Description:
 * Given a string s and an integer k, you can choose any character of the string and
 * change it to any other uppercase English character. You can perform this operation
 * at most k times. Return the length of the longest substring containing the same letter
 * you can get after performing the above operations.
 * <p>
 * Key Insight:
 * We need to find a substring where we can make at most k character replacements to have
 * all characters the same. The optimal strategy is to replace characters that are not the
 * most frequent in the current window.
 * <p>
 * Sliding Window Approach:
 * 1. For a window of size windowSize, if we want all characters to be the same:
 * - We need to replace (windowSize - maxFrequency) characters
 * - If (windowSize - maxFrequency) <= k, the window is valid
 * 2. When a window becomes invalid, we shrink it from the left
 * 3. The maximum valid window size is our answer
 * <p>
 * Time Complexity: O(n) where n is the length of the string
 * Space Complexity: O(1) as we only use a fixed-size array for character counts
 */

public class _332_d_LongestRepeatingCharacterReplacement {
}
