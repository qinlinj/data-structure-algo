package org.qinlinj.algoframework._100_core_framework._130_sliding_window._3_find_all_anagrams;

import java.util.*;

// @formatter:off
public class FindAnagrams {
    /**
     * Finds all starting indices of anagrams (permutations) of string t in string s.
     *
     * This solution uses the sliding window technique to efficiently find all occurrences
     * where an anagram of t appears in s.
     *
     * Visual example:
     * For s = "cbaebabacd", t = "abc"
     *
     * Initial state:
     * "cbaebabacd"
     *  l
     *  r
     * window: {} valid: 0/3
     *
     * After window reaches t.length():
     * "cbaebabacd"
     *  l   r
     * window: {c:1, b:1, a:1} valid: 3/3
     * Found anagram at index 0
     *
     * After shrinking and expanding:
     * "cbaebabacd"
     *    l   r
     * window: {a:1, e:1, b:1} valid: 1/3
     *
     * Eventually:
     * "cbaebabacd"
     *       l   r
     * window: {a:1, b:1, c:1} valid: 3/3
     * Found anagram at index 6
     *
     * Time Complexity: O(n) where n is the length of string s
     * Space Complexity: O(k) where k is the size of the character set
     *
     * @param s The source string to search in
     * @param t The pattern string (looking for anagrams of this)
     * @return List of starting indices where anagrams of t appear in s
     */
    public List<Integer> findAnagrams(String s, String t) {
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

        // Result list to store starting indices of anagrams
        List<Integer> res = new ArrayList<>();

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
                if (window.get(c).equals(need.get(c))) {
                    valid++;
                }
            }

            // Shrink window when its size equals or exceeds t's length
            while (right - left >= t.length()) {
                // If we found a valid anagram, add its starting index to result
                if (valid == need.size())
                    res.add(left);

                // Character to be removed from the window
                char d = s.charAt(left);
                // Shrink window
                left++;

                // Update window data
                if (need.containsKey(d)) {
                    // If removing this character breaks the valid count, decrease valid
                    if (window.get(d).equals(need.get(d))) {
                        valid--;
                    }
                    // Decrement the count of this character in the window
                    window.put(d, window.get(d) - 1);
                }
            }
        }

        return res;
    }
}
