package org.qinlinj.algoframework._700_brute_force_search_algo._740_dfs_backtracking_practice._741_backtracking_classic_practice_I;

import java.util.*;

/**
 * 17. Letter Combinations of a Phone Number
 * <p>
 * Problem Summary:
 * Given a string containing digits from 2-9, return all possible letter combinations that the number could represent.
 * A mapping of digits to letters is provided (like on telephone buttons).
 * <p>
 * Key Insights:
 * - This is a classic backtracking problem
 * - Each digit maps to multiple possible letters
 * - We can build combinations by selecting one letter from each digit's mapping
 * - The problem can be thought of as traversing a backtracking tree where:
 * - Each level corresponds to a digit position
 * - Each branch at that level represents one possible letter for that digit
 * <p>
 * Time Complexity: O(4^n) where n is the number of digits (worst case is 4 letters per digit)
 * Space Complexity: O(n) for recursion stack and tracking current combination
 */
class _741_h_LetterCombinationsPhoneNumber {

    // Mapping from digit to letters
    String[] mapping = new String[]{
            "", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"
    };

    List<String> res = new LinkedList<>();
    StringBuilder sb = new StringBuilder();

    public List<String> letterCombinations(String digits) {
        if (digits.isEmpty()) {
            return res;
        }
        // Start backtracking from digits[0]
        backtrack(digits, 0);
        return res;
    }

    // Backtracking main function
    void backtrack(String digits, int start) {
        if (sb.length() == digits.length()) {
            // Reached bottom of backtracking tree
            res.add(sb.toString());
            return;
        }

        // Backtracking framework
        int digit = digits.charAt(start) - '0';
        for (char c : mapping[digit].toCharArray()) {
            // Make choice
            sb.append(c);
            // Move to next level of backtracking tree
            backtrack(digits, start + 1);
            // Undo choice
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}