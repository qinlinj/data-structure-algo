package org.qinlinj.practical.string;

// @formatter:off
/**
 * Optimized Brute Force String Matching Algorithm Implementation
 *
 * This is an improved version of the basic Brute Force (BF) algorithm for string matching.
 * It optimizes the search by skipping unnecessary comparisons when the first character
 * of the pattern doesn't match.
 *
 * Key concepts and principles:
 * 1. First Character Optimization: The algorithm first searches for the first character of the pattern.
 * 2. Skip Non-matching Positions: It skips positions where the first character doesn't match.
 * 3. Sequential Comparison: Once a potential match is found, it checks the remaining characters.
 *
 * Advantages of Optimized BF Algorithm:
 * - Improved efficiency: Reduces unnecessary comparisons by skipping non-matching positions
 * - Still maintains simplicity: The implementation remains relatively straightforward
 * - No preprocessing required: Works directly on the input strings
 * - Better average-case performance than the standard BF algorithm
 *
 * Time Complexity:
 * - Worst case: O(m*n) where m is the length of main string and n is the length of pattern
 * - Average case: Better than O(m*n) due to the optimization
 * - Best case: O(m) when the pattern's first character rarely appears in the text
 *
 * Example visualization:
 * Main string:    "this is your code"
 * Pattern:        "your"
 *
 * Step 1: First, find occurrences of 'y' (first character of pattern)
 * "this is your code"
 *          ^
 * Found 'y' at position 8
 *
 * Step 2: Compare remaining characters
 * "this is your code"
 *           ^
 * "your"
 *   ^
 * 'o' == 'o' ✓
 *
 * "this is your code"
 *            ^
 * "your"
 *    ^
 * 'u' == 'u' ✓
 *
 * "this is your code"
 *             ^
 * "your"
 *     ^
 * 'r' == 'r' ✓
 *
 * All characters match! Pattern found at index 8
 */
public class BF2 {
    public static void main(String[] args) {
        BF2 b = new BF2();
        String mainStr = "this is your code";
        String patternStr = "your";

        System.out.println(b.indexOf(mainStr, patternStr));
    }

    public int indexOf(String mainStr, String pattern) {
        if (mainStr == null || pattern == null) return -1;

        int m = mainStr.length();
        int n = pattern.length();
        if (m < n) return -1;

        char first = pattern.charAt(0);
        for (int i = 0; i < m; i++) {
            if (mainStr.charAt(i) != first) {
                while (++i < m && mainStr.charAt(i) != first) ;
            }

            if (i < m) {
                int k = i + 1;
                int j = 1;
                if (j == n) return i;
                for (; j < n && k < m; j++, k++) {
                    if (mainStr.charAt(k) == pattern.charAt(j)) {
                        if (j == n - 1) return i;
                    } else {
                        break;
                    }
                }
            }
        }

        return -1;
    }
}
