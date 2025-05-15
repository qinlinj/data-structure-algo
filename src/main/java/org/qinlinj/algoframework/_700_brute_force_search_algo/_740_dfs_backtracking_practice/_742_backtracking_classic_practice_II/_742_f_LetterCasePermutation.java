package org.qinlinj.algoframework._700_brute_force_search_algo._740_dfs_backtracking_practice._742_backtracking_classic_practice_II;

import java.util.*;

/**
 * 784. Letter Case Permutation
 * <p>
 * Problem Summary:
 * Given a string s, transform every letter individually to be lowercase or uppercase to create a
 * new string. Return a list of all possible strings we could create.
 * <p>
 * Key Insights:
 * - This is a backtracking problem where we make a binary choice for each letter
 * - For each character in the string:
 * 1. If it's a digit, we only have one choice (keep it as is)
 * 2. If it's a letter, we have two choices (uppercase or lowercase)
 * - We build all possible strings by making these choices recursively
 * <p>
 * Time Complexity: O(2^n) where n is the number of letters in the input string
 * Space Complexity: O(n) for recursion stack and storing the current string
 */
class _742_f_LetterCasePermutation {

    StringBuilder track = new StringBuilder();
    List<String> res = new LinkedList<>();

    public List<String> letterCasePermutation(String s) {
        backtrack(s, 0);
        return res;
    }

    void backtrack(String s, int index) {
        if (index == s.length()) {
            // Reached the end of string, add current permutation to result
            res.add(track.toString());
            return;
        }

        char c = s.charAt(index);

        if (Character.isDigit(c)) {
            // Digit - only one choice
            track.append(c);
            backtrack(s, index + 1);
            track.deleteCharAt(track.length() - 1);
        } else {
            // Letter - two choices: lowercase and uppercase

            // Choice 1: lowercase
            track.append(Character.toLowerCase(c));
            backtrack(s, index + 1);
            track.deleteCharAt(track.length() - 1);

            // Choice 2: uppercase
            track.append(Character.toUpperCase(c));
            backtrack(s, index + 1);
            track.deleteCharAt(track.length() - 1);
        }
    }
}