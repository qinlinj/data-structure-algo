package org.qinlinj.algoframework._700_brute_force_search_algo._740_dfs_backtracking_practice._742_backtracking_classic_practice_II;

import java.util.*;

/**
 * 1849. Splitting a String Into Descending Consecutive Values
 * <p>
 * Problem Summary:
 * Given a string of digits, determine if it can be split into two or more non-empty substrings such that:
 * - The numerical values of the substrings are in strictly descending order
 * - The difference between any two adjacent values is exactly 1
 * <p>
 * Key Insights:
 * - This is a classic backtracking problem with two possible implementation perspectives:
 * 1. Substring perspective: For each substring, try different lengths
 * 2. Character perspective: For each character position, decide whether to split or not
 * - Need to handle potential numeric overflow issues since the string can be up to 20 characters
 * - Can use pruning to avoid calculating values that would be too large
 * - Special consideration needed for leading zeros
 * <p>
 * Time Complexity: O(2^n) in worst case, where n is the string length
 * Space Complexity: O(n) for recursion stack and tracking
 */
class _742_b_SplittingStringIntoDescendingValues {

    LinkedList<String> track = new LinkedList<>();
    boolean found = false;

    // Approach 1: Character perspective - decide whether to split or not at each position
    public boolean splitString(String s) {
        backtrack(s, 0, 0);
        return found;
    }

    void backtrack(String s, int start, int index) {
        if (found) {
            // Pruning: once solution found, no need to continue backtracking
            return;
        }
        if (index == s.length()) {
            if (track.size() >= 2 && String.join("", track).equals(s)) {
                found = true;
            }
            return;
        }

        // Choice 1: s[index] decides to split
        String subStr = s.substring(start, index + 1);
        int leadingZeroCount = 0;
        for (int j = 0; j < subStr.length(); j++) {
            if (subStr.charAt(j) == '0') {
                leadingZeroCount++;
            } else {
                break;
            }
        }
        // Pruning: if substring without leading zeros is too long, no need to continue
        if (subStr.length() - leadingZeroCount > (s.length() + 1) / 2) {
            return;
        }

        long curNum = Long.parseLong(subStr);
        if (track.isEmpty() || Long.parseLong(track.getLast()) - curNum == 1) {
            // Current number is 1 less than the previous number, satisfying the requirement
            // Make choice: create a substring
            track.add(subStr);
            backtrack(s, index + 1, index + 1);
            // Undo choice
            track.removeLast();
        }

        // Choice 2: s[index] decides not to split
        backtrack(s, start, index + 1);
    }

    // Alternative approach: Substring perspective - consider all possible lengths
    public boolean splitStringAlt(String s) {
        return backtrackAlt(s, 0);
    }

    boolean backtrackAlt(String s, int start) {
        if (found) {
            return true;
        }
        if (start == s.length()) {
            return track.size() >= 2;
        }

        for (int i = start; i < s.length(); i++) {
            String subStr = s.substring(start, i + 1);
            int leadingZeroCount = 0;
            for (int j = 0; j < subStr.length(); j++) {
                if (subStr.charAt(j) == '0') {
                    leadingZeroCount++;
                } else {
                    break;
                }
            }
            // Pruning: if substring without leading zeros is too long
            if (subStr.length() - leadingZeroCount > (s.length() + 1) / 2) {
                return false;
            }

            long curNum = Long.parseLong(subStr);
            if (track.isEmpty() || Long.parseLong(track.getLast()) - curNum == 1) {
                track.add(subStr);
                if (backtrackAlt(s, i + 1)) {
                    return true;
                }
                track.removeLast();
            }
        }

        return false;
    }
}