package org.qinlinj.algoframework._300_classical_data_structure_algo._330_array_algo_II._331_sliding_window_template;

import java.util.*;

/**
 * Permutation in String (LeetCode 567)
 * <p>
 * Problem Description:
 * Given two strings s1 and s2, return true if s2 contains a permutation of s1,
 * or false otherwise. In other words, return true if one of s1's permutations
 * is the substring of s2.
 * <p>
 * Key Concepts:
 * 1. This is a fixed-length sliding window problem
 * 2. Window size must equal the length of s1
 * 3. Use counter/HashMap to track character frequencies
 * 4. Time complexity: O(N), where N is the length of s2
 * <p>
 * Example:
 * Input: s1 = "ab", s2 = "eidbaooo"
 * Output: true (s2 contains "ba", which is a permutation of s1)
 */
public class PermutationInString {
    public static void main(String[] args) {
        PermutationInString solution = new PermutationInString();

        // Test cases
        String s1_1 = "ab";
        String s2_1 = "eidbaooo";
        System.out.println("Input: s1 = \"" + s1_1 + "\", s2 = \"" + s2_1 + "\"");
        System.out.println("Output: " + solution.checkInclusion(s1_1, s2_1)); // Expected: true

        String s1_2 = "ab";
        String s2_2 = "eidboaoo";
        System.out.println("Input: s1 = \"" + s1_2 + "\", s2 = \"" + s2_2 + "\"");
        System.out.println("Output: " + solution.checkInclusion(s1_2, s2_2)); // Expected: false
    }

    public boolean checkInclusion(String s1, String s2) {
        // Hash maps to store character frequencies
        Map<Character, Integer> need = new HashMap<>();
        Map<Character, Integer> window = new HashMap<>();

        // Populate the need hash map with characters in s1
        for (char c : s1.toCharArray()) {
            need.put(c, need.getOrDefault(c, 0) + 1);
        }

        // Initialize pointers and variables
        int left = 0, right = 0;
        int valid = 0; // Count of characters with sufficient frequency

        // Begin sliding window
        while (right < s2.length()) {
            // Get character to add to window
            char c = s2.charAt(right);
            // Expand window
            right++;

            // Update window data
            if (need.containsKey(c)) {
                window.put(c, window.getOrDefault(c, 0) + 1);
                // If we have exact count needed for this character, increment valid
                if (window.get(c).intValue() == need.get(c).intValue()) {
                    valid++;
                }
            }

            // Fixed-length window - contract when window size equals s1 length
            // Note: This could be changed to "if" instead of "while" since we only move one character at a time
            while (right - left >= s1.length()) {
                // If all characters match in frequency, return true
                if (valid == need.size()) {
                    return true;
                }

                // Get character to remove from window
                char d = s2.charAt(left);
                // Contract window
                left++;

                // Update window data
                if (need.containsKey(d)) {
                    // If removing this character will break the valid count, decrement valid
                    if (window.get(d).intValue() == need.get(d).intValue()) {
                        valid--;
                    }
                    window.put(d, window.get(d) - 1);
                }
            }
        }

        // No permutation found
        return false;
    }
}
