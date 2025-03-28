package org.qinlinj.practical.string;

import java.util.*;
// @formatter:off
/**
 * Boyer-Moore String Matching Algorithm Implementation (Bad Character Rule)
 *
 * The Boyer-Moore algorithm is one of the most efficient string matching algorithms
 * in practice. This implementation uses the Bad Character rule, which allows the algorithm
 * to skip characters and achieve sublinear time complexity in the average case.
 *
 * Key concepts and principles:
 * 1. Right-to-Left Scanning: The algorithm compares characters from right to left within the pattern.
 * 2. Bad Character Rule: When a mismatch occurs, the algorithm uses information about the character
 *    in the text that caused the mismatch to determine how far to shift the pattern.
 * 3. Skip Technique: Unlike brute force approaches, Boyer-Moore can skip portions of the text,
 *    potentially examining fewer than m+n characters (where m is text length, n is pattern length).
 *
 * Advantages of Boyer-Moore Algorithm:
 * - Efficiency: Often sublinear time complexity in practice (doesn't need to examine every character)
 * - Fast for large alphabets: Performance improves with larger character sets
 * - Excellent for long patterns: The longer the pattern, the more efficient the algorithm becomes
 * - Widely used in practical applications like text editors and search utilities
 *
 * Time Complexity:
 * - Worst case: O(m*n) where m is the length of main string and n is the length of pattern
 * - Average case: O(m/n) which is sublinear
 * - Best case: O(m/n) when the pattern has distinct characters
 *
 * Example visualization with mainStr="aaabaaabaaabaaaa", pattern="aba":
 *
 * Bad Character Map: {'a': 0, 'b': 1}
 *
 * Step 1: Align pattern at i=0
 * "aaabaaabaaabaaaa"
 *  "aba"
 *    ^
 * Compare from right to left: Pattern[2]='a' matches Text[2]='a' ✓
 * Compare next: Pattern[1]='b' doesn't match Text[1]='a' ✗
 * Bad character 'a' appears at position 0 in pattern
 * Shift: y-x = 1-0 = 1
 *
 * Step 2: Align pattern at i=1
 * "aaabaaabaaabaaaa"
 *   "aba"
 *     ^
 * Compare from right to left: Pattern[2]='a' matches Text[3]='b' ✗
 * Bad character 'b' appears at position 1 in pattern
 * Shift: y-x = 2-1 = 1
 *
 * Step 3: Align pattern at i=2
 * "aaabaaabaaabaaaa"
 *    "aba"
 *      ^
 * Compare from right to left: Pattern[2]='a' matches Text[4]='a' ✓
 * Compare next: Pattern[1]='b' matches Text[3]='b' ✓
 * Compare next: Pattern[0]='a' matches Text[2]='a' ✓
 * Match found at position 2!
 */
public class BM1 {

    public static void main(String[] args) {
        BM1 b = new BM1();
        String mainStr = "aaabaaabaaabaaaa";
        String patternStr = "aba";

        System.out.println(b.indexOf(mainStr, patternStr));
    }

    private int indexOf(String mainStr, String pattern) {
        if (mainStr == null || pattern == null) return -1;

        int m = mainStr.length();
        int n = pattern.length();
        if (m < n) return -1;

        Map<Character, Integer> bc = genBadCharIndexMap(pattern);
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

            i = i + Math.max(1, (y - x));
        }

        return -1;
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
