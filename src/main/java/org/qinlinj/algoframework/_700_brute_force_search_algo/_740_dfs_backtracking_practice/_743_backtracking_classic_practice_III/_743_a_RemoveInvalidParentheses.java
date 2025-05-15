package org.qinlinj.algoframework._700_brute_force_search_algo._740_dfs_backtracking_practice._743_backtracking_classic_practice_III;

import java.util.*;

/**
 * 301. Remove Invalid Parentheses
 * <p>
 * Problem Summary:
 * Given a string of parentheses and letters, remove the minimum number of invalid parentheses
 * to make the input string valid. Return all possible results.
 * <p>
 * Key Insights:
 * - This is a complex backtracking problem requiring careful decision-making
 * - For each character in the string, we have two choices:
 * 1. Keep the character (letters must always be kept)
 * 2. Delete the character (only applicable for parentheses)
 * - We need to find all valid strings with the minimum number of deletions
 * - The solution can be optimized by tracking the count of left parentheses to avoid
 * certain invalid paths
 * - We use a set to eliminate duplicate answers
 * <p>
 * Optimization:
 * - When the number of right parentheses exceeds left parentheses, we can immediately
 * determine that the current right parenthesis must be removed
 * - This optimization significantly reduces the search space
 * <p>
 * Time Complexity: O(2^n) in worst case, where n is the string length
 * Space Complexity: O(n) for recursion stack and tracking the current string
 */
class _743_a_RemoveInvalidParentheses {

    List<String> res = new LinkedList<>();
    StringBuilder track = new StringBuilder();

    public List<String> removeInvalidParentheses(String s) {
        // Use optimized backtracking with left parenthesis count
        backtrack(s, 0, 0);

        // Find the maximum length among valid results (minimum removals)
        int maxLen = 0;
        for (String str : res) {
            maxLen = Math.max(maxLen, str.length());
        }

        // Filter out results with the maximum length (minimum removals)
        HashSet<String> set = new HashSet<>();
        for (String str : res) {
            if (str.length() == maxLen) {
                set.add(str);
            }
        }

        return new LinkedList<>(set);
    }

    // Optimized backtracking with leftCount parameter
    void backtrack(String s, int i, int leftCount) {
        // Base case: reached the end of the string
        if (i == s.length()) {
            if (isValid(track.toString())) {
                res.add(track.toString());
            }
            return;
        }

        char c = s.charAt(i);

        // Case 1: Character is a letter (not a parenthesis)
        if (c != '(' && c != ')') {
            // Letters must always be kept
            track.append(c);
            backtrack(s, i + 1, leftCount);
            track.deleteCharAt(track.length() - 1);
        } else {
            // Case 2: Character is a parenthesis

            // Choice 1: Keep the current parenthesis
            // Only consider keeping if it won't create an invalid state
            if (leftCount > 0 || c != ')') {
                // Make choice
                track.append(c);

                // Update leftCount based on the parenthesis type
                if (c == '(') {
                    leftCount++;
                } else { // c == ')'
                    leftCount--;
                }

                // Continue to next position
                backtrack(s, i + 1, leftCount);

                // Undo choice
                track.deleteCharAt(track.length() - 1);

                // Restore leftCount
                if (c == '(') {
                    leftCount--;
                } else { // c == ')'
                    leftCount++;
                }
            }

            // Choice 2: Remove the current parenthesis
            backtrack(s, i + 1, leftCount);
        }
    }

    // Helper function to check if a string of parentheses is valid
    boolean isValid(String s) {
        int left = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') {
                left++;
            } else if (c == ')') {
                left--;
                if (left < 0) {
                    // Right parentheses more than left, invalid
                    return false;
                }
            }
        }
        // Valid if all parentheses are matched
        return left == 0;
    }

    // Unoptimized version of backtracking for comparison
    void backtrackUnoptimized(String s, int i) {
        if (i == s.length()) {
            if (isValid(track.toString())) {
                res.add(track.toString());
            }
            return;
        }

        char c = s.charAt(i);
        if (c != '(' && c != ')') {
            // Letters, always keep
            track.append(c);
            backtrackUnoptimized(s, i + 1);
            track.deleteCharAt(track.length() - 1);
        } else {
            // Parenthesis, two choices

            // Choice 1: Keep the parenthesis
            track.append(c);
            backtrackUnoptimized(s, i + 1);
            track.deleteCharAt(track.length() - 1);

            // Choice 2: Remove the parenthesis
            backtrackUnoptimized(s, i + 1);
        }
    }
}