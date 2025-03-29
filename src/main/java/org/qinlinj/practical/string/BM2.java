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
