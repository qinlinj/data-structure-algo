package org.qinlinj.practical.string;

// @formatter:off

import java.util.*;
/**
 * Complete Boyer-Moore String Matching Algorithm Implementation
 *
 * This implementation includes both the Bad Character rule and the Good Suffix rule,
 * making it a full implementation of the Boyer-Moore algorithm. The combination of
 * these rules makes Boyer-Moore one of the most efficient string matching algorithms
 * in practice.
 *
 * Key concepts and principles:
 * 1. Right-to-Left Scanning: The algorithm compares characters from right to left within the pattern.
 * 2. Bad Character Rule: When a mismatch occurs, the algorithm shifts the pattern to align the
 *    mismatched character in the text with its rightmost occurrence in the pattern.
 * 3. Good Suffix Rule: After a mismatch, the algorithm also considers the matched suffix and
 *    tries to find another occurrence of it in the pattern, or a prefix of the pattern that
 *    matches a suffix of the matched part.
 * 4. Maximum Shift: The algorithm uses the maximum of the shifts suggested by both rules.
 *
 * Advantages of Full Boyer-Moore Algorithm:
 * - Highly Efficient: Often sublinear time complexity in practice
 * - Optimal Shifts: Combines two heuristics to maximize skipping of unnecessary comparisons
 * - Excellent for Long Patterns: Particularly efficient for long patterns and large texts
 * - Industry Standard: Used in many real-world applications like grep, text editors, etc.
 *
 * Time Complexity:
 * - Worst case: O(m*n) where m is the length of main string and n is the length of pattern
 * - Average case: O(m/n) which is sublinear
 * - Best case: O(m/n) when the pattern has distinct characters and good suffix structure
 *
 * Example visualization with mainStr="aaabaaa", pattern="baaa":
 *
 * Preprocessing:
 * - Bad Character Map: {'b': 0, 'a': 3}
 * - Suffix array and Prefix flags for "baaa"
 *
 * Step 1: Align pattern at i=0
 * "aaabaaa"
 *  "baaa"
 *    ^
 * Compare from right: Pattern[3]='a' matches Text[3]='b' ✗
 * Bad character 'b' appears at position 0 in pattern
 * Bad character shift: 3-0 = 3
 * Good suffix shift: 0 (no suffix matched)
 * Move pattern by max(3,0) = 3 positions
 *
 * Step 2: Align pattern at i=3
 * "aaabaaa"
 *     "baaa"
 *       ^
 * Compare from right: Pattern[3]='a' matches Text[6]='a' ✓
 * Compare next: Pattern[2]='a' matches Text[5]='a' ✓
 * Compare next: Pattern[1]='a' matches Text[4]='b' ✗
 * Bad character 'b' appears at position 0 in pattern
 * Bad character shift: 1-0 = 1
 * Good suffix shift: Based on matched suffix "aa"
 * Move pattern by max(1,goodSuffixShift) positions
 */
public class BM2 {
    public static void main(String[] args) {
        BM2 b = new BM2();
        String mainStr = "aaabaaa";
        String patternStr = "baaa";

        System.out.println(b.indexOf(mainStr, patternStr));
    }

    /**
     * Finds the first occurrence of a pattern in a main string using the Boyer-Moore algorithm
     * with both Bad Character and Good Suffix rules.
     *
     * Algorithm steps:
     * 1. Preprocess the pattern:
     *    a. Generate the bad character map
     *    b. Compute the good suffix array and prefix flags
     * 2. Align pattern at the beginning of the text
     * 3. Compare characters from right to left
     * 4. If mismatch occurs:
     *    a. Calculate shift using bad character rule
     *    b. Calculate shift using good suffix rule (if applicable)
     *    c. Take the maximum of these shifts
     * 5. If all characters match, return the current position
     *
     * Visualization for mainStr="aaabaaa", pattern="baaa":
     *
     * i=0: "aaabaaa"
     *       "baaa"
     *         ^ Start comparing from right
     *     'b' in text doesn't match 'a' in pattern ✗
     *     Bad character shift = 3 (no 'b' in pattern)
     *     No good suffix, so shift = 3
     *
     * i=3: "aaabaaa"
     *          "baaa"
     *            ^ Start comparing from right
     *     'a' in text matches 'a' in pattern ✓
     *     'a' in text matches 'a' in pattern ✓
     *     'b' in text doesn't match 'a' in pattern ✗
     *     Bad character 'b' appears at position 0 in pattern
     *     Good suffix "aa" must be considered
     *     Shift pattern accordingly
     *
     * Time Complexity:
     * - Worst case: O(m*n) in pathological cases
     * - Average case: O(m/n) which is sublinear
     * - Preprocessing: O(n) for bad character map and O(n²) for good suffix computation
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
