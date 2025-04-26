package org.qinlinj.algoframework._300_classical_data_structure_algo._330_array_algo_II._331_sliding_window_template;

import java.util.*;

/**
 * Longest Substring Without Repeating Characters (LeetCode 3)
 * <p>
 * Problem Description:
 * Given a string s, find the length of the longest substring without repeating characters.
 * <p>
 * Key Concepts:
 * 1. Use sliding window approach with dynamic window size
 * 2. Use HashMap/Counter to track character occurrences in the window
 * 3. When a duplicate is found, shrink the window from left until the duplicate is removed
 * 4. Update the maximum length after window adjustments
 * 5. Time complexity: O(N), where N is the length of string s
 * <p>
 * Example:
 * Input: s = "abcabcbb"
 * Output: 3 (The longest substring without repeating characters is "abc")
 */
public class _331_e_LongestSubstringWithoutRepeatingChars {
    public static void main(String[] args) {
        _331_e_LongestSubstringWithoutRepeatingChars solution = new _331_e_LongestSubstringWithoutRepeatingChars();

        // Test cases
        String s1 = "abcabcbb";
        System.out.println("Input: s = \"" + s1 + "\"");
        System.out.println("Output: " + solution.lengthOfLongestSubstring(s1)); // Expected: 3

        String s2 = "bbbbb";
        System.out.println("Input: s = \"" + s2 + "\"");
        System.out.println("Output: " + solution.lengthOfLongestSubstring(s2)); // Expected: 1

        String s3 = "pwwkew";
        System.out.println("Input: s = \"" + s3 + "\"");
        System.out.println("Output: " + solution.lengthOfLongestSubstring(s3)); // Expected: 3

        // Test optimized version
        System.out.println("\nTesting optimized version:");
        System.out.println("Input: s = \"" + s1 + "\"");
        System.out.println("Output: " + solution.lengthOfLongestSubstringOptimized(s1)); // Expected: 3
    }

    public int lengthOfLongestSubstring(String s) {
        // Map to track character frequencies in current window
        Map<Character, Integer> window = new HashMap<>();

        // Initialize pointers and result
        int left = 0, right = 0;
        int maxLength = 0;

        // Begin sliding window
        while (right < s.length()) {
            // Get character to add to window
            char c = s.charAt(right);
            // Expand window
            right++;

            // Update window data - increment character count
            window.put(c, window.getOrDefault(c, 0) + 1);

            // Contract window when we have repeating characters
            while (window.get(c) > 1) {
                // Get character to remove from window
                char d = s.charAt(left);
                // Contract window
                left++;

                // Update window data - decrement character count
                window.put(d, window.get(d) - 1);
            }

            // After adjusting window, update the maximum length
            // At this point, the window contains no repeating characters
            maxLength = Math.max(maxLength, right - left);
        }

        return maxLength;
    }

    // Alternative implementation using an array instead of HashMap for better performance
    public int lengthOfLongestSubstringOptimized(String s) {
        // Use array for faster lookups (assuming ASCII characters)
        int[] window = new int[128];

        int left = 0, right = 0;
        int maxLength = 0;

        while (right < s.length()) {
            char c = s.charAt(right);
            window[c]++;
            right++;

            while (window[c] > 1) {
                char d = s.charAt(left);
                window[d]--;
                left++;
            }

            maxLength = Math.max(maxLength, right - left);
        }

        return maxLength;
    }
}
