package org.qinlinj.algoframework._300_classical_data_structure_algo._330_array_algo_II._331_sliding_window_template;

import java.util.*;

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
public class _331_b_MinimumWindowSubstring {

    public static void main(String[] args) {
        _331_b_MinimumWindowSubstring solution = new _331_b_MinimumWindowSubstring();

        // Test cases
        String s1 = "ADOBECODEBANC";
        String t1 = "ABC";
        System.out.println("Input: s = \"" + s1 + "\", t = \"" + t1 + "\"");
        System.out.println("Output: \"" + solution.minWindow(s1, t1) + "\""); // Expected: "BANC"

        String s2 = "a";
        String t2 = "a";
        System.out.println("Input: s = \"" + s2 + "\", t = \"" + t2 + "\"");
        System.out.println("Output: \"" + solution.minWindow(s2, t2) + "\""); // Expected: "a"

        String s3 = "a";
        String t3 = "aa";
        System.out.println("Input: s = \"" + s3 + "\", t = \"" + t3 + "\"");
        System.out.println("Output: \"" + solution.minWindow(s3, t3) + "\""); // Expected: ""
    }

    public String minWindow(String s, String t) {
        // Hash maps to store character frequencies
        Map<Character, Integer> need = new HashMap<>();
        Map<Character, Integer> window = new HashMap<>();

        // Populate the need hash map with characters in t
        for (char c : t.toCharArray()) {
            need.put(c, need.getOrDefault(c, 0) + 1);
        }

        // Initialize pointers and variables
        int left = 0, right = 0;
        int valid = 0;  // Count of characters with sufficient frequency

        // Variables to track the minimum window
        int start = 0;
        int len = Integer.MAX_VALUE;

        // Begin sliding window
        while (right < s.length()) {
            // Get character to add to window
            char c = s.charAt(right);
            // Expand window
            right++;

            // Update window data
            if (need.containsKey(c)) {
                window.put(c, window.getOrDefault(c, 0) + 1);
                // If we have exact count needed for this character, increment valid
                if (window.get(c).equals(need.get(c))) {
                    valid++;
                }
            }

            // Contract window when all characters are found
            while (valid == need.size()) {
                // Update minimum window if current is smaller
                if (right - left < len) {
                    start = left;
                    len = right - left;
                }

                // Get character to remove from window
                char d = s.charAt(left);
                // Contract window
                left++;

                // Update window data
                if (need.containsKey(d)) {
                    // If removing this character will break the valid count, decrement valid
                    if (window.get(d).equals(need.get(d))) {
                        valid--;
                    }
                    window.put(d, window.get(d) - 1);
                }
            }
        }

        // Return the minimum window substring or empty string if not found
        return len == Integer.MAX_VALUE ? "" : s.substring(start, start + len);
    }
}
