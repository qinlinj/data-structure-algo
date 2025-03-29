package org.qinlinj.practical.string;

// @formatter:off

import java.util.*;
/**
 * Complete Boyer-Moore String Matching Algorithm Implementation
 *
 * This class implements the Boyer-Moore algorithm with both the Bad Character rule
 * and the Good Suffix rule. It represents one of the most efficient string matching
 * algorithms available, especially for large texts and patterns.
 *
 * Key concepts and principles:
 * 1. Right-to-Left Scanning: The algorithm compares characters from right to left.
 * 2. Bad Character Rule: When a mismatch occurs, shift the pattern to align the
 *    mismatched character with its rightmost occurrence in the pattern.
 * 3. Good Suffix Rule: After a mismatch, shift the pattern to align with the next
 *    occurrence of the matched suffix or a prefix of the pattern that matches a suffix.
 * 4. Combined Approach: The algorithm takes the maximum shift suggested by either rule.
 *
 * Advantages of Full Boyer-Moore Algorithm:
 * - Highly efficient: Often sublinear time complexity in practice
 * - Optimal shifts: By using both rules, the algorithm makes larger jumps
 * - Performance improves with pattern length: Longer patterns allow longer jumps
 * - Industry standard: Used in many text editors, search utilities, and databases
 *
 * Time Complexity:
 * - Preprocessing: O(n + σ) where n is pattern length and σ is alphabet size
 * - Search: O(m) best case, O(m*n) worst case, but typically O(m/n) in practice
 *
 * Space Complexity:
 * - O(n + σ) for the pattern preprocessing
 *
 * Example visualization with mainStr="aaabaaa", pattern="baaa":
 *
 * Bad Character Map: {'b': 0, 'a': 1, 'a': 2, 'a': 3} (simplified to {'b': 0, 'a': 3})
 * Good Suffix arrays initialized
 *
 * Step 1: Align pattern at i=0
 * "aaabaaa"
 *  "baaa"
 *     ^ Start comparing from right
 * Text[3]='b' doesn't match Pattern[3]='a' ✗
 * Bad character 'b' appears at position 0 in pattern
 * Bad Character shift: 3-0 = 3
 * No Good Suffix shift (mismatch at the rightmost position)
 * Shift pattern by 3 positions
 *
 * Step 2: Align pattern at i=3
 * "aaabaaa"
 *     "baaa"
 *        ^ Start comparing from right
 * Text[6]='a' matches Pattern[3]='a' ✓
 * Text[5]='a' matches Pattern[2]='a' ✓
 * Text[4]='a' matches Pattern[1]='a' ✓
 * Text[3]='b' matches Pattern[0]='b' ✓
 * All characters match! Return i=3
 */
public class BM2 {
    public static void main(String[] args) {
        BM2 b = new BM2();
        String mainStr = "aaabaaa";
        String patternStr = "baaa";

        System.out.println(b.indexOf(mainStr, patternStr));
    }

    /**
     * Finds the first occurrence of a pattern in a main string using the full Boyer-Moore algorithm
     * with both Bad Character and Good Suffix rules.
     *
     * Algorithm steps:
     * 1. Preprocess the pattern:
     *    a. Generate the bad character map
     *    b. Calculate the good suffix arrays
     * 2. Align pattern at the beginning of the text
     * 3. Compare characters from right to left
     * 4. If mismatch occurs:
     *    a. Calculate shift using bad character rule
     *    b. Calculate shift using good suffix rule
     *    c. Use the maximum of the two shifts
     * 5. If all characters match, return the current position
     *
     * Visualization for mainStr="aaabaaa", pattern="baaa":
     *
     * Text:    "aaabaaa"
     * Pattern: "baaa"
     *
     * i=0: Align pattern at position 0
     *     "aaabaaa"
     *     "baaa"
     *        ^ Start comparing from right
     *     Text[3]='b' doesn't match Pattern[3]='a' ✗
     *     Bad character 'b' found at position 0 in pattern
     *     Bad character shift = 3-0 = 3
     *     No good suffix (mismatch at rightmost position)
     *     Shift pattern by 3 positions
     *
     * i=3: Align pattern at position 3
     *     "aaabaaa"
     *        "baaa"
     *           ^ Start comparing from right
     *     Text[6]='a' matches Pattern[3]='a' ✓
     *     Text[5]='a' matches Pattern[2]='a' ✓
     *     Text[4]='a' matches Pattern[1]='a' ✓
     *     Text[3]='b' matches Pattern[0]='b' ✓
     *     All characters match! Return i=3
     *
     * Time Complexity:
     * - Worst case: O(m*n) (rarely occurs in practice)
     * - Average case: O(m/n) which is sublinear
     * - Best case: O(m/n)
     *
     * @param mainStr The main string to search in
     * @param pattern The pattern to search for
     * @return The starting index of the first occurrence of pattern in mainStr, or -1 if not found
     */
    public int indexOf(String mainStr, String pattern) {
        if (mainStr == null || pattern == null) return -1;

        int m = mainStr.length();
        int n = pattern.length();
        if (m < n) return -1;

        Map<Character, Integer> bc = genBadCharIndexMap(pattern);

        int[] suffix = new int[n];
        boolean[] prefix = new boolean[n];
        genGoodSuffixArr(pattern.toCharArray(), suffix, prefix);

        int i = 0;

        while (i <= m - n) {
            int y;
            for (y = n - 1; y >= 0; y--) {
                if (mainStr.charAt(i + y) != pattern.charAt(y)) break;
            }

            if (y < 0) {
                return i;
            }

            char badChar = mainStr.charAt(i + y);
            int x = bc.getOrDefault(badChar, -1);
            int badCharMoveSteps = y - x;

            int goodSuffixMoveSteps = 0;
            if (y < n - 1) {
                goodSuffixMoveSteps = calMoveSteps(y, n, suffix, prefix);
            }

            i = i + Math.max(badCharMoveSteps, goodSuffixMoveSteps);
        }
        return -1;
    }

    /**
     * Calculates the number of steps to move based on the Good Suffix rule.
     *
     * The Good Suffix rule has two cases:
     * 1. If the same suffix occurs elsewhere in the pattern, shift to align with the next occurrence.
     * 2. If a prefix of the pattern matches a suffix of the good suffix, shift to that position.
     *
     * Time Complexity: O(n) where n is the pattern length
     *
     * Example:
     * For pattern="baaa" with a mismatch at position 0 (after matching "aaa"):
     * - The good suffix is "aaa"
     * - There's no other occurrence of "aaa" in the pattern
     * - We check if prefixes of the pattern match suffixes of "aaa"
     * - No such prefix exists in this case
     * - Return n (the length of the pattern) as the shift distance
     *
     * @param y The position of the mismatch
     * @param n The length of the pattern
     * @param suffix The suffix array from preprocessing
     * @param prefix The prefix array from preprocessing
     * @return The number of steps to move based on the Good Suffix rule
     */
    private int calMoveSteps(int y, int n, int[] suffix, boolean[] prefix) {
        int k = n - y - 1;

        if (suffix[k] != -1) return y - suffix[k] + 1;
        for (int i = y + 1; i < n; i++) {
            if (prefix[n - i]) {
                return i;
            }
        }
        return n;
    }

    private void genGoodSuffixArr(char[] pattern, int[] suffix, boolean[] prefix) {
        Arrays.fill(suffix, -1);
        int n = pattern.length;
        for (int i = 0; i < n - 1; i++) {
            int j = i;
            int k = 0;
            while (j >= 0 && pattern[j] == pattern[n - 1 - k]) {
                k++;
                suffix[k] = j;
                j--;
            }
            if (j == -1) prefix[k] = true;
        }
    }

    private Map<Character, Integer> genBadCharIndexMap(String pattern) {
        char[] patternChars = pattern.toCharArray();
        Map<Character, Integer> bc = new HashMap<>();
        for (int i = 0; i < patternChars.length; i++) {
            bc.put(patternChars[i], i);
        }
        return bc;
    }

}
