package org.qinlinj.algoframework._100_algo_core_framework._130_sliding_window._133_permutation_in_string;

import java.util.*;

// @formatter:off
public class CheckInclusion {
    /**
     * Determines if string s contains any permutation of string t.
     *
     * This solution uses the sliding window technique to efficiently check if a permutation
     * (anagram) of string t exists as a substring in string s.
     *
     * Visual example:
     * For s = "eidbaooo", t = "ab"
     *
     * Initial state:
     * "eidbaooo"
     *  l
     *  r
     * window: {} valid: 0/2
     *
     * After window reaches t.length():
     * "eidbaooo"
     *  l  r
     * window: {e:1, i:1} valid: 0/2
     * Window size = 2, start shrinking (not a match)
     *
     * After several iterations:
     * "eidbaooo"
     *    l  r
     * window: {d:1, b:1} valid: 1/2 (b matches)
     *
     * Eventually:
     * "eidbaooo"
     *     l  r
     * window: {b:1, a:1} valid: 2/2 (both a and b match)
     * Return true
     *
     * Time Complexity: O(n) where n is the length of string s
     * Space Complexity: O(k) where k is the size of the character set
     *
     * @param t The pattern string (looking for permutations of this)
     * @param s The source string to search in
     * @return true if any permutation of t exists in s, false otherwise
     */
    public boolean checkInclusion(String t, String s) {
        // Maps to track character frequencies
        Map<Character, Integer> need = new HashMap<>(); // Characters needed from t
        Map<Character, Integer> window = new HashMap<>(); // Current window characters

        // Count character frequencies in string t
        for (char c : t.toCharArray()) {
            need.put(c, need.getOrDefault(c, 0) + 1);
        }

        // Initialize pointers and counter
        int left = 0, right = 0;
        int valid = 0; // Count of characters with satisfied frequency requirements

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
                // Note: Using intValue() to avoid potential auto-boxing comparison issues
                if (window.get(c).intValue() == need.get(c).intValue())
                    valid++;
            }

            // Shrink window when its size equals or exceeds t's length
            while (right - left >= t.length()) {
                // Check if we've found a valid permutation
                if (valid == need.size())
                    return true;

                // Character to be removed from the window
                char d = s.charAt(left);
                // Shrink window
                left++;

                // Update window data
                if (need.containsKey(d)) {
                    // If removing this character breaks the valid count, decrease valid
                    if (window.get(d).intValue() == need.get(d).intValue())
                        valid--;
                    // Decrement the count of this character in the window
                    window.put(d, window.get(d) - 1);
                }
            }
        }

        // No permutation of t found in s
        return false;
    }
}
