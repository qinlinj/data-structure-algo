package org.qinlinj.algoframework._1_core_framework._3_sliding_window._3_find_all_anagrams;

import java.util.*;

// @formatter:off
public class FindAnagrams {
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
        return null;
    }
}
