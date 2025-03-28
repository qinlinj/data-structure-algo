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

    /**
     * Finds the first occurrence of a pattern in a main string using an Optimized Brute Force approach
     *
     * Algorithm steps:
     * 1. Get the first character of the pattern
     * 2. Scan the main string for this first character, skipping non-matching positions
     * 3. When the first character is found, check if the remaining characters match
     * 4. If all characters match, return the starting position
     * 5. If not, continue scanning from the next position
     *
     * Visualization for mainStr="this is your code", pattern="your":
     *
     * First, we look for 'y' (first character of pattern):
     * "this is your code"
     *  ^ ^ ^ ^ ^ ^ ^ ^
     * Checking... No 'y' until position 8
     *
     * At position 8:
     * "this is your code"
     *          ^
     * "your"
     *  ^
     * 'y' == 'y' ✓, proceed to check remaining characters
     *
     * Check subsequent characters:
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
     * All characters match! Return i=8
     *
     * Time Complexity:
     * - Worst case: O(m*n) when the pattern almost matches but fails at the last character,
     *   or when all characters in the main string match the first character of the pattern
     * - Average case: Better than standard BF due to skipping non-matching positions
     * - Best case: O(m) when the first character of pattern rarely appears in the main string
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
