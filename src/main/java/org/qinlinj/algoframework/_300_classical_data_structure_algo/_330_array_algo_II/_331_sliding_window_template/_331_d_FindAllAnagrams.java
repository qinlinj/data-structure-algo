package org.qinlinj.algoframework._300_classical_data_structure_algo._330_array_algo_II._331_sliding_window_template;

import java.util.*;

/**
 * Find All Anagrams in a String (LeetCode 438)
 * <p>
 * Problem Description:
 * Given two strings s and p, find all the start indices of p's anagrams in s.
 * Return these indices in any order.
 * An anagram is a string formed by rearranging the letters of a different string,
 * using all the original letters exactly once.
 * <p>
 * Key Concepts:
 * 1. This is a fixed-length sliding window problem
 * 2. Window size must equal the length of p
 * 3. Use counter/HashMap to track character frequencies
 * 4. Add the start index to result list whenever a valid anagram is found
 * 5. Time complexity: O(N), where N is the length of s
 * <p>
 * Example:
 * Input: s = "cbaebabacd", p = "abc"
 * Output: [0, 6] (Starting indices of "cba" and "bac")
 */
public class _331_d_FindAllAnagrams {
    public static void main(String[] args) {
        _331_d_FindAllAnagrams solution = new _331_d_FindAllAnagrams();

        // Test cases
        String s1 = "cbaebabacd";
        String p1 = "abc";
        System.out.println("Input: s = \"" + s1 + "\", p = \"" + p1 + "\"");
        System.out.println("Output: " + solution.findAnagrams(s1, p1)); // Expected: [0, 6]

        String s2 = "abab";
        String p2 = "ab";
        System.out.println("Input: s = \"" + s2 + "\", p = \"" + p2 + "\"");
        System.out.println("Output: " + solution.findAnagrams(s2, p2)); // Expected: [0, 1, 2]
    }

    public List<Integer> findAnagrams(String s, String p) {
        // Initialize result list
        List<Integer> result = new ArrayList<>();

        // Edge case: s is shorter than p
        if (s.length() < p.length()) {
            return result;
        }

        // Hash maps to store character frequencies
        Map<Character, Integer> need = new HashMap<>();
        Map<Character, Integer> window = new HashMap<>();

        // Populate the need hash map with characters in p
        for (char c : p.toCharArray()) {
            need.put(c, need.getOrDefault(c, 0) + 1);
        }

        // Initialize pointers and variables
        int left = 0, right = 0;
        int valid = 0; // Count of characters with sufficient frequency

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

            // Fixed-length window - contract when window size equals p length
            while (right - left >= p.length()) {
                // If all characters match in frequency, add left index to result
                if (valid == need.size()) {
                    result.add(left);
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

        return result;
    }
}
