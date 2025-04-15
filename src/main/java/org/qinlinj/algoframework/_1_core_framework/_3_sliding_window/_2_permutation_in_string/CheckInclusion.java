package org.qinlinj.algoframework._1_core_framework._3_sliding_window._2_permutation_in_string;

import java.util.*;

public class CheckInclusion {
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
            
        }
        return false;
    }
}
