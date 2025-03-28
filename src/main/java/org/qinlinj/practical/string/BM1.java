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

    /**
     * Main method to demonstrate the Boyer-Moore string matching algorithm
     *
     * Time Complexity: Average case O(m/n) where m is the length of mainStr and n is the length of patternStr
     *
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        BM1 b = new BM1();
        String mainStr = "aaabaaabaaabaaaa";
        String patternStr = "aba";

        System.out.println(b.indexOf(mainStr, patternStr));
    }

    /**
     * Finds the first occurrence of a pattern in a main string using the Boyer-Moore algorithm
     * with the Bad Character rule.
     *
     * Algorithm steps:
     * 1. Generate a bad character map from the pattern
     * 2. Align pattern at the beginning of the text
     * 3. Compare characters from right to left
     * 4. If mismatch occurs:
     *    a. Look up the bad character in the map
     *    b. Calculate shift distance using bad character rule
     *    c. Shift pattern accordingly
     * 5. If all characters match, return the current position
     *
     * Visualization for mainStr="aaabaaabaaabaaaa", pattern="aba":
     *
     * Bad Character Map: {'a': 0, 'b': 1}
     *
     * i=0: "aaabaaabaaabaaaa"
     *       "aba"
     *         ^ Start comparing from right
     *     'a' in text matches 'a' in pattern ✓
     *     'a' in text doesn't match 'b' in pattern ✗
     *     Bad character is 'a', found at position 0 in pattern
     *     Shift = max(1, (1-0)) = 1
     *
     * i=1: "aaabaaabaaabaaaa"
     *        "aba"
     *          ^ Start comparing from right
     *     'b' in text doesn't match 'a' in pattern ✗
     *     Bad character is 'b', found at position 1 in pattern
     *     Shift = max(1, (2-1)) = 1
     *
     * i=2: "aaabaaabaaabaaaa"
     *         "aba"
     *           ^ Start comparing from right
     *     'a' in text matches 'a' in pattern ✓
     *     'b' in text matches 'b' in pattern ✓
     *     'a' in text matches 'a' in pattern ✓
     *     All characters match! Return i=2
     *
     * Time Complexity:
     * - Worst case: O(m*n) when pattern consists of repeating characters
     * - Average case: O(m/n) which is sublinear
     * - Preprocessing: O(n) to build the bad character map
     *
     * @param mainStr The main string to search in
     * @param pattern The pattern to search for
     * @return The starting index of the first occurrence of pattern in mainStr, or -1 if not found
     */
    private int indexOf(String mainStr, String pattern) {
        // Check for null inputs
        if (mainStr == null || pattern == null) return -1;

        int m = mainStr.length();
        int n = pattern.length();

        // Pattern can't be longer than the main string
        if (m < n) return -1;

        // Generate the bad character index map for pattern
        Map<Character, Integer> bc = genBadCharIndexMap(pattern);
        int i = 0;  // Current position in the main string

        // Continue searching until the end of potential matching positions
        while (i <= m - n) {
            int y;

            // Compare characters from right to left
            for (y = n - 1; y >= 0; y--) {
                if (mainStr.charAt(i + y) != pattern.charAt(y)) break;
            }

            // If y < 0, all characters matched
            if (y < 0) {
                return i;  // Match found at position i
            }

            // Get the mismatched character (bad character) from the main string
            char badChar = mainStr.charAt(i + y);

            // Look up the rightmost position of this character in the pattern
            // If it doesn't exist in the pattern, use -1
            int x = bc.getOrDefault(badChar, -1);

            // Calculate shift distance using bad character rule:
            // Shift the pattern so that the bad character in the text aligns with
            // its rightmost occurrence in the pattern (if it exists)
            // Ensure we always move forward by at least 1 position
            i = i + Math.max(1, (y - x));
        }

        // Pattern not found
        return -1;
    }

    /**
     * Generates a map of characters in the pattern to their rightmost positions.
     * This map is used by the bad character rule to determine how far to shift the pattern.
     *
     * Time Complexity: O(n) where n is the length of the pattern
     * Space Complexity: O(k) where k is the number of unique characters in the pattern
     *
     * Example:
     * For pattern="aba":
     * - 'a' appears at positions 0 and 2, but we store only the rightmost position 0
     * - 'b' appears at position 1
     * Result: {'a': 0, 'b': 1}
     *
     * @param pattern The pattern string
     * @return A map of characters to their rightmost positions in the pattern
     */
    private Map<Character, Integer> genBadCharIndexMap(String pattern) {
        char[] patternChars = pattern.toCharArray();
        Map<Character, Integer> bc = new HashMap<>();

        // For each character in the pattern, store its position
        // If a character appears multiple times, its last position will be stored
        for (int i = 0; i < patternChars.length; i++) {
            bc.put(patternChars[i], i);
        }

        return bc;
    }
}