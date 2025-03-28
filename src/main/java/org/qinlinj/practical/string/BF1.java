package org.qinlinj.practical.string;

// @formatter:off
/**
 * Brute Force String Matching Algorithm Implementation
 *
 * The Brute Force (BF) algorithm is a straightforward approach to string matching,
 * where we scan the main string from left to right and check for pattern matches
 * at each possible position.
 *
 * Key concepts and principles:
 * 1. Sequential Scanning: The algorithm examines each possible starting position in the main string.
 * 2. Character-by-Character Comparison: At each position, it compares characters one by one.
 * 3. Early Termination: If a mismatch is found, it immediately moves to the next starting position.
 *
 * Advantages of BF Algorithm:
 * - Simplicity: Easy to understand and implement
 * - No preprocessing required: Works directly on the input strings
 * - Works well for small texts and patterns
 * - Does not require additional memory beyond the input strings
 *
 * Time Complexity:
 * - Worst case: O(m*n) where m is the length of main string and n is the length of pattern
 * - Best case: O(m) when characters immediately mismatch
 *
 * Example visualization:
 * Main string:    "this is your code"
 * Pattern:        "your"
 *
 * Step 1: i=0
 * "this is your code"
 *  ^
 * "your"
 *  ^
 * 't' != 'y' ➝ Mismatch, move to i=1
 *
 * Step 2: i=1
 * "this is your code"
 *   ^
 * "your"
 *  ^
 * 'h' != 'y' ➝ Mismatch, move to i=2
 *
 * ... (continuing this process)
 *
 * Step 8: i=8
 * "this is your code"
 *          ^
 * "your"
 *  ^
 * 'y' == 'y' ➝ Match first character, continue
 * 'o' == 'o' ➝ Match second character, continue
 * 'u' == 'u' ➝ Match third character, continue
 * 'r' == 'r' ➝ Match fourth character, pattern found at index 8
 */
public class BF1 {
    public static void main(String[] args) {
        BF1 b = new BF1();
        String mainStr = "this is your code";
        String patternStr = "your";

        System.out.println(b.indexOf(mainStr, patternStr));
    }

    /**
     * Finds the first occurrence of a pattern in a main string using Brute Force approach
     *
     * Algorithm steps:
     * 1. For each position i in the main string:
     *    a. Start comparing pattern with main string from position i
     *    b. If all characters match, return position i
     *    c. If any character doesn't match, move to next position
     * 2. If no match found, return -1
     *
     * Visualization for mainStr="this is your code", pattern="your":
     *
     * i=0: "this is your code"
     *       ^
     *      "your" -> 't' != 'y', mismatch
     *
     * i=1: "this is your code"
     *        ^
     *      "your" -> 'h' != 'y', mismatch
     *
     * ... (skipping intermediate steps)
     *
     * i=8: "this is your code"
     *               ^
     *      "your" -> Compare all characters:
     *               'y' == 'y' ✓
     *               'o' == 'o' ✓
     *               'u' == 'u' ✓
     *               'r' == 'r' ✓
     *      All characters match! Return i=8
     *
     * Time Complexity:
     * - Worst case: O(m*n) when the pattern almost matches but fails at the last character,
     *   or when many partial matches occur
     * - Best case: O(m) when characters immediately mismatch at each position
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

        for (int i = 0; i < m; i++) {
            int k = i;
            for (int j = 0; j < n; j++) {
                if (k < m && pattern.charAt(j) == mainStr.charAt(k)) {
                    k++;
                    if (j == n - 1) return i;
                } else {
                    break;
                }
            }
        }

        return -1;
    }
}
