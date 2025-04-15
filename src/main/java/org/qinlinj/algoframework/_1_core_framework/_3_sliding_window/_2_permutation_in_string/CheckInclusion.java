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
        return false;
    }
}
