package org.qinlinj.algoframework._100_algo_core_framework._130_sliding_window._135_longest_substring_without_repeating;

import java.util.*;

// @formatter:off
public class LengthOfLongestSubstring {
    /**
     * Finds the length of the longest substring without repeating characters.
     *
     * This solution uses the sliding window technique to efficiently track
     * substrings with unique characters and find the maximum length.
     *
     * Visual example:
     * For string: "abcabcbb"
     *
     * Initial state:
     * "abcabcbb"
     *  l
     *  r
     * window: {} res: 0
     *
     * After adding "abc":
     * "abcabcbb"
     *  l   r
     * window: {a:1, b:1, c:1} res: 3
     *
     * When adding second 'a':
     * "abcabcbb"
     *  l    r
     * window: {a:2, b:1, c:1}
     *
     * After shrinking:
     * "abcabcbb"
     *    l  r
     * window: {a:1, b:1, c:1} res: 3
     *
     * Final result: 3 (the substring "abc" or "cab")
     *
     * Time Complexity: O(n) where n is the length of string s
     * Space Complexity: O(min(m,n)) where m is the size of the character set
     *
     * @param s The input string
     * @return The length of the longest substring without repeating characters
     */
    public int lengthOfLongestSubstring(String s) {
        // Map to track character frequencies in current window
        Map<Character, Integer> window = new HashMap<>();

        // Initialize pointers and result
        int left = 0, right = 0;
        int res = 0; // Length of longest substring found

        // Expand the window by moving right pointer
        while (right < s.length()) {
            // Character to be added to the window
            char c = s.charAt(right);
            // Expand window
            right++;

            // Update window data - increment count of current character
            window.put(c, window.getOrDefault(c, 0) + 1);

            // Shrink window when a duplicate character is found
            while (window.get(c) > 1) {
                // Character to be removed from the window
                char d = s.charAt(left);
                // Shrink window
                left++;

                // Update window data - decrement count of removed character
                window.put(d, window.get(d) - 1);
            }

            // Update maximum length after window adjustment
            // At this point, the window contains only unique characters
            res = Math.max(res, right - left);
        }

        return res;
    }
}
