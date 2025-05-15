package org.qinlinj.algoframework._700_brute_force_search_algo._740_dfs_backtracking_practice._742_backtracking_classic_practice_II;

import java.util.*;

/**
 * 1593. Split a String Into the Max Number of Unique Substrings
 * <p>
 * Problem Summary:
 * Given a string s, split it into some number of non-empty substrings such that:
 * - Each substring is unique
 * - The original string can be reconstructed by concatenating all substrings
 * Return the maximum number of unique substrings possible.
 * <p>
 * Key Insights:
 * - This is a classic backtracking problem
 * - Two possible approaches based on the "box-ball" model:
 * 1. Character perspective: For each character, decide whether to split or not
 * 2. Substring perspective: For each position, try different substring lengths
 * - Need to track already used substrings to maintain uniqueness
 * - The constraint of the string length (max 16) suggests backtracking is efficient
 * <p>
 * Time Complexity: O(2^n) in worst case, where n is the string length
 * Space Complexity: O(n) for recursion stack and tracking unique substrings
 */
class _742_c_MaxUniqueSplitString {

    Set<String> set = new HashSet<>();
    int res = 0;

    public int maxUniqueSplit(String s) {
        backtrack(s, 0);
        return res;
    }

    void backtrack(String s, int index) {
        if (index == s.length()) {
            // Reached the end of the string, update the maximum count
            res = Math.max(res, set.size());
            return;
        }

        // Choice 1: Do not split at the current index
        backtrack(s, index + 1);

        // Choice 2: Split at the current index
        String sub = s.substring(0, index + 1);
        // Only proceed if the substring is unique
        if (!set.contains(sub)) {
            // Make choice
            set.add(sub);
            // Process the remaining characters
            backtrack(s.substring(index + 1), 0);
            // Undo choice
            set.remove(sub);
        }
    }

    // Alternative implementation using the substring perspective
    void backtrackAlt(String s, int start) {
        if (start == s.length()) {
            res = Math.max(res, set.size());
            return;
        }

        for (int i = start; i < s.length(); i++) {
            String sub = s.substring(start, i + 1);
            if (!set.contains(sub)) {
                set.add(sub);
                backtrackAlt(s, i + 1);
                set.remove(sub);
            }
        }
    }
}