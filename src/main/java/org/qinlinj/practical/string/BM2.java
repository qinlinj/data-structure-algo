package org.qinlinj.practical.string;

// @formatter:off

import java.util.*;

/**
 * Complete Boyer-Moore String Matching Algorithm Implementation
 *
 * This implementation includes both the Bad Character rule and the Good Suffix rule,
 * making it a full Boyer-Moore algorithm implementation. The combination of these two
 * rules makes Boyer-Moore one of the most efficient string matching algorithms in practice.
 *
 * Key concepts and principles:
 * 1. Right-to-Left Scanning: The algorithm compares characters from right to left within the pattern.
 * 2. Bad Character Rule: When a mismatch occurs, the algorithm uses information about the character
 *    in the text that caused the mismatch to determine one possible shift distance.
 * 3. Good Suffix Rule: The algorithm also uses information about the matched suffix to determine
 *    another possible shift distance.
 * 4. Maximum Shift Strategy: The algorithm takes the maximum of the shifts suggested by the two rules,
 *    which ensures safe skipping while maximizing efficiency.
 *
 * Advantages of Full Boyer-Moore Algorithm:
 * - Maximum Efficiency: Combining both rules provides the best possible skip distances
 * - Optimal for large texts: Performance advantage increases with text size
 * - Excellent for repetitive patterns: Good suffix rule shines when patterns have repetitions
 * - Industry standard: Used in many text processing applications and search tools
 *
 * Time Complexity:
 * - Worst case: O(m*n) where m is the length of main string and n is the length of pattern
 * - Average case: O(m/n) which is sublinear, often approaching O(m/n) in practice
 * - Best case: O(m/n) when the pattern has both distinct characters and non-repeating suffixes
 *
 * Example visualization with mainStr="aaabaaa", pattern="baaa":
 *
 * Preprocessing:
 * - Bad Character Map: {'b': 0, 'a': 1, 'a': 2, 'a': 3} (simplified to {'b': 0, 'a': 3})
 * - Good Suffix arrays calculated (see visualization in genGoodSuffixArr method)
 *
 * Step 1: Align pattern at i=0
 * "aaabaaa"
 *  "baaa"
 *     ^ Start comparing from right
 * Compare: pattern[3]='a' matches text[3]='b' ✗
 * Bad character 'b' is at position 0 in pattern
 * Bad character shift = 3 - 0 = 3
 * Good suffix shift = 0 (no suffix matched)
 * Shift by max(3, 0) = 3
 *
 * Step 2: Align pattern at i=3
 * "aaabaaa"
 *     "baaa"
 *        ^
 * Compare from right: All characters match! ✓
 * Match found at position 3
 */
public class BM2 {
    /**
     * Main method to demonstrate the full Boyer-Moore string matching algorithm
     *
     * Time Complexity: Average case O(m/n) where m is the length of mainStr and n is the length of patternStr
     *
     * @param args Command line arguments (not used)
     */
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
     * 1. Preprocess:
     *    a. Generate bad character map from the pattern
     *    b. Generate good suffix arrays from the pattern
     * 2. Align pattern at the beginning of the text
     * 3. Compare characters from right to left
     * 4. If mismatch occurs:
     *    a. Calculate shift using bad character rule
     *    b. Calculate shift using good suffix rule (if applicable)
     *    c. Take the maximum of the two shifts
     * 5. If all characters match, return the current position
     *
     * Visualization for mainStr="aaabaaa", pattern="baaa":
     *
     * Step 1: i=0
     * "aaabaaa"
     *  "baaa"
     *     ^
     * Compare from right to left: Pattern[3]='a' doesn't match Text[3]='b' ✗
     * Bad character is 'b', found at position 0 in pattern
     * Bad character shift = 3-0 = 3
     * No good suffix (mismatch at last character)
     * Shift = max(3, 0) = 3
     *
     * Step 2: i=3
     * "aaabaaa"
     *     "baaa"
     *        ^
     * Compare from right to left:
     * Pattern[3]='a' matches Text[6]='a' ✓
     * Pattern[2]='a' matches Text[5]='a' ✓
     * Pattern[1]='a' matches Text[4]='a' ✓
     * Pattern[0]='b' matches Text[3]='b' ✓
     * All characters match! Return i=3
     *
     * Time Complexity:
     * - Preprocessing: O(n) to build the bad character map and good suffix arrays
     * - Search: Average case O(m/n), worst case O(m*n)
     *
     * @param mainStr The main string to search in
     * @param pattern The pattern to search for
     * @return The starting index of the first occurrence of pattern in mainStr, or -1 if not found
     */
    public int indexOf(String mainStr, String pattern) {
        // Check for null inputs
        if (mainStr == null || pattern == null) return -1;

        int m = mainStr.length();
        int n = pattern.length();

        // Pattern can't be longer than the main string
        if (m < n) return -1;

        // Preprocessing phase: Generate data structures for both rules
        // 1. Bad Character Rule: Create character-to-index map
        Map<Character, Integer> bc = genBadCharIndexMap(pattern);

        // 2. Good Suffix Rule: Create suffix and prefix arrays
        int[] suffix = new int[n];
        boolean[] prefix = new boolean[n];
        genGoodSuffixArr(pattern.toCharArray(), suffix, prefix);

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

            // Calculate shift according to Bad Character Rule
            char badChar = mainStr.charAt(i + y);
            int x = bc.getOrDefault(badChar, -1);
            int badCharMoveSteps = y - x;

            // Calculate shift according to Good Suffix Rule
            int goodSuffixMoveSteps = 0;
            // Only apply good suffix rule if we matched at least one character
            if (y < n - 1) {
                goodSuffixMoveSteps = calMoveSteps(y, n, suffix, prefix);
            }

            // Take the maximum of the two shift values
            i = i + Math.max(badCharMoveSteps, goodSuffixMoveSteps);
        }
        return -1;  // Pattern not found
    }

    /**
     * Calculates the shift distance according to the Good Suffix Rule.
     *
     * This rule handles two cases:
     * 1. Case 1: If the matched suffix appears elsewhere in the pattern, shift to align with the rightmost occurrence
     * 2. Case 2: If a prefix of the pattern matches a suffix of the matched part, shift to that position
     *
     * Time Complexity: O(n) in worst case, O(1) in average case
     *
     * Example for pattern="baaa" with mismatch at position y=0 (matched suffix "aaa"):
     * The suffix array shows that "aaa" doesn't appear elsewhere in the pattern
     * No prefix of "baaa" matches the suffix of "aaa"
     * Therefore shift by the pattern length n=4
     *
     * @param y The position where mismatch occurred (0-indexed from left)
     * @param n The length of the pattern
     * @param suffix The suffix array generated during preprocessing
     * @param prefix The prefix array generated during preprocessing
     * @return The number of positions to shift according to the Good Suffix Rule
     */
    private int calMoveSteps(int y, int n, int[] suffix, boolean[] prefix) {
        // Length of the matched suffix
        int k = n - y - 1;

        // Case 1: The matched suffix appears elsewhere in the pattern
        if (suffix[k] != -1) return y - suffix[k] + 1;

        // Case 2: A prefix of the pattern matches a suffix of the matched part
        // Search for the longest prefix that matches a suffix of the matched part
        for (int i = y + 1; i < n; i++) {
            if (prefix[n - i]) {
                return i;
            }
        }

        // If neither case applies, shift by the pattern length
        return n;
    }

    /**
     * Generates the Good Suffix arrays used by the Good Suffix Rule.
     *
     * This preprocessing computes:
     * 1. suffix[i] = The starting position of the rightmost substring that matches suffix of length i
     * 2. prefix[i] = True if a prefix of the pattern with length i matches its suffix
     *
     * Time Complexity: O(n²) where n is the length of the pattern
     * Space Complexity: O(n)
     *
     * Example for pattern="baaa":
     * - For suffix "a" (length 1), rightmost occurrence is at position 2
     * - For suffix "aa" (length 2), rightmost occurrence is at position 1
     * - For suffix "aaa" (length 3), no previous occurrence
     * - No prefix matches any suffix except the pattern itself
     *
     * suffix = [-1, 2, 1, -1]
     * prefix = [false, false, false, true]
     *
     * @param pattern The pattern character array
     * @param suffix Array to store the rightmost positions of suffixes
     * @param prefix Array to mark whether a prefix matches a suffix
     */
    private void genGoodSuffixArr(char[] pattern, int[] suffix, boolean[] prefix) {
        // Initialize all suffix positions to -1 (not found)
        Arrays.fill(suffix, -1);
        int n = pattern.length;

        // For each position in the pattern (except the last character)
        for (int i = 0; i < n - 1; i++) {
            int j = i;
            int k = 0;

            // Compare characters from current position i with characters from the end
            while (j >= 0 && pattern[j] == pattern[n - 1 - k]) {
                k++;  // Increment matched suffix length
                suffix[k] = j;  // Store the rightmost position for this suffix length
                j--;
            }

            // If we matched all the way to the beginning, mark this as a prefix match
            if (j == -1) prefix[k] = true;
        }
    }

    /**
     * Generates a map of characters in the pattern to their rightmost positions.
     * This map is used by the Bad Character Rule to determine how far to shift the pattern.
     *
     * Time Complexity: O(n) where n is the length of the pattern
     * Space Complexity: O(k) where k is the number of unique characters in the pattern
     *
     * Example:
     * For pattern="baaa":
     * - 'b' appears at position 0
     * - 'a' appears at positions 1, 2, and 3, but we store only the rightmost position 3
     * Result: {'b': 0, 'a': 3}
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