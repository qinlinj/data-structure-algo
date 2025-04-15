package org.qinlinj.algoframework._1_core_framework._3_sliding_window._1_minimum_window_substring;

import java.util.*;

public class MinWindow {
    /**
     * Finds the minimum window substring that contains all characters from string t.
     * <p>
     * This solution implements the sliding window algorithm to efficiently find the
     * smallest substring in s that contains all characters from t, including duplicates.
     * <p>
     * Visual example:
     * For s = "ADOBECODEBANC", t = "ABC"
     * <p>
     * Initial state:
     * "ADOBECODEBANC"
     * l
     * r
     * window: {} valid: 0/3
     * <p>
     * After expanding window to include all necessary characters:
     * "ADOBECODEBANC"
     * l            r
     * window: {A:1, B:1, C:1} valid: 3/3
     * <p>
     * After first minimum found and shrinking:
     * "ADOBECODEBANC"
     * l  r
     * window: {A:1, B:1, C:1} valid: 3/3
     * Minimum window: "BANC"
     * <p>
     * Time Complexity: O(n) where n is the length of string s
     * Space Complexity: O(k) where k is the size of the character set (in this case, at most 52)
     *
     * @param s The source string
     * @param t The target string containing characters to be found
     * @return The minimum window substring containing all characters from t
     */
    public String minWindow(String s, String t) {
        // Maps to track character frequencies
        Map<Character, Integer> need = new HashMap<>(); // Characters needed from t
        Map<Character, Integer> window = new HashMap<>(); // Current window characters

        // Count character frequencies in string t
        for (char c : t.toCharArray()) {
            need.put(c, need.getOrDefault(c, 0) + 1);
        }

        // Initialize pointers and counters
        int left = 0, right = 0;
        int valid = 0; // Count of characters with satisfied frequency requirements

        // Variables to track the minimum window substring
        int start = 0, len = Integer.MAX_VALUE;

        // Expand the window by moving right pointer
        while (right < s.length()) {
            // Character to be added to the window
            char c = s.charAt(right);
            // Expand window
            right++;

            // Update window data
            if (need.containsKey(c)) {
                // Increment the count of this character in the window
                window.put(c, window.getOrDefault(c, 0) + 1);
                // If we have exactly the required count of this character, increase valid count
                if (window.get(c).equals(need.get(c)))
                    valid++;
            }

            // Try to shrink the window when all required characters are found
            while (valid == need.size()) {
                // Update the minimum window substring if current is smaller
                if (right - left < len) {
                    start = left;
                    len = right - left;
                }

                // Character to be removed from the window
                char d = s.charAt(left);
                // Shrink window
                left++;

                // Update window data
                if (need.containsKey(d)) {
                    // If removing this character breaks the valid count, decrease valid
                    if (window.get(d).equals(need.get(d)))
                        valid--;
                    // Decrement the count of this character in the window
                    window.put(d, window.get(d) - 1);
                }
            }
        }

        // Return the minimum window substring if found, otherwise empty string
        return len == Integer.MAX_VALUE ? "" : s.substring(start, start + len);
    }
}
