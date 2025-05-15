package org.qinlinj.algoframework._700_brute_force_search_algo._740_dfs_backtracking_practice._742_backtracking_classic_practice_II;

import java.util.*;

/**
 * 1079. Letter Tile Possibilities
 * <p>
 * Problem Summary:
 * Given a string of uppercase letters (tiles), return the number of possible non-empty sequences
 * you can form. Each tile can only be used once.
 * <p>
 * Key Insights:
 * - This is a permutation problem where not all elements need to be used (unlike full permutation)
 * - We need to handle duplicate characters in the input
 * - This is similar to "permutation with elements that can repeat but not be reused"
 * - The key difference from full permutation is that we collect results at every level of the
 * recursion tree, not just at the leaf nodes
 * - We sort the input first to handle duplicates efficiently
 * <p>
 * Time Complexity: O(n!) where n is the length of the input string
 * Space Complexity: O(n) for recursion stack and tracking used elements
 */
class _742_d_LetterTilePossibilities {

    int res = 0;
    boolean[] used;

    public int numTilePossibilities(String s) {
        char[] chars = s.toCharArray();
        // Sort characters to bring identical characters together
        Arrays.sort(chars);
        used = new boolean[chars.length];
        backtrack(chars);
        // Subtract 1 to exclude the empty sequence
        return res - 1;
    }

    void backtrack(char[] chars) {
        // Count the current state (increment for every node in the recursion tree)
        res++;

        for (int i = 0; i < chars.length; i++) {
            // Skip if this character is already used
            if (used[i]) {
                continue;
            }

            // Skip duplicate characters to avoid duplicate permutations
            // Only consider the first occurrence of a character at each level
            if (i > 0 && chars[i] == chars[i - 1] && !used[i - 1]) {
                continue;
            }

            // Make choice
            used[i] = true;
            backtrack(chars);
            // Undo choice
            used[i] = false;
        }
    }
}