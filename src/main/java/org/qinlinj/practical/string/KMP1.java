package org.qinlinj.practical.string;

import java.util.*;

// @formatter:off
/**
 * KMP (Knuth-Morris-Pratt) String Matching Algorithm
 *
 * Advantages of KMP:
 * 1. Efficiency: O(m + n) time complexity where m is the length of the main string and n is the length of the pattern
 * 2. No backtracking: Unlike naive approaches, KMP doesn't need to re-examine characters it has already checked
 * 3. Preprocessing: Uses pattern information to skip unnecessary comparisons
 *
 * Concept and Principle:
 * KMP algorithm improves string matching by using information from previous character comparisons.
 * When a mismatch occurs, instead of starting comparison from the beginning of the pattern,
 * KMP uses a precomputed "next" array (also called "partial match table" or "failure function")
 * to determine how far to shift the pattern.
 *
 * The key insight is: when a mismatch occurs after matching some characters, we already know part of the text.
 * We can use this information to avoid redundant comparisons.
 *
 * Visual example:
 * Text:    "A B A B C A B A B D"
 * Pattern: "A B A B D"
 *
 * Step 1: Compare characters until mismatch
 * A B A B C A B A B D
 * A B A B D
 *         ^ Mismatch at position 4
 *
 * Step 2: Instead of shifting by just one character, KMP shifts according to the next array
 * A B A B C A B A B D
 *     A B A B D  <- Pattern shifted based on next array
 *     ^ ^ ^ ^ ^
 *
 * Step 3: Continue matching
 * A B A B C A B A B D
 *     A B A B D
 *         ^ Mismatch again
 *
 * Step 4: Shift again based on next array
 * A B A B C A B A B D
 *           A B A B D  <- Final position to match
 *           ^ ^ ^ ^ ^
 */
public class KMP1 {
    public static void main(String[] args) {
        KMP1 b = new KMP1();
        String mainStr = "aaabaaa";
        String patternStr = "baaa";

        // Return the starting index of the first occurrence of pattern in the main string
        System.out.println(b.indexOf(mainStr, patternStr));
    }

    /**
     * Finds the first occurrence of pattern in mainStr using KMP algorithm
     *
     * Visual example:
     * mainStr:  "a a a b a a a"
     * pattern:  "b a a a"
     *
     * Step 1: Compare mainStr[0]='a' with pattern[0]='b'. Mismatch, no shift in pattern needed as j=0.
     * a a a b a a a
     * b a a a
     * ^ Mismatch
     *
     * Step 2: Compare mainStr[1]='a' with pattern[0]='b'. Mismatch, no shift needed.
     * a a a b a a a
     *   b a a a
     *   ^ Mismatch
     *
     * Step 3: Compare mainStr[2]='a' with pattern[0]='b'. Mismatch, no shift needed.
     * a a a b a a a
     *     b a a a
     *     ^ Mismatch
     *
     * Step 4: Compare mainStr[3]='b' with pattern[0]='b'. Match! Increment j.
     * a a a b a a a
     *       b a a a
     *       ^ Match
     *
     * Step 5: Continue matching remaining characters.
     * a a a b a a a
     *       b a a a
     *         ^ ^ ^ All match
     *
     * Result: Pattern found at index 3
     *
     * Time Complexity: O(m + n) where m is length of mainStr and n is length of pattern
     *
     * @param mainStr the main string to search in
     * @param pattern the pattern string to search for
     * @return the starting index of the first occurrence of pattern in mainStr, or -1 if not found
     */
    public int indexOf(String mainStr, String pattern) {
        // Handle null inputs
        if (mainStr == null || pattern == null) return -1;

        int m = mainStr.length();
        int n = pattern.length();
        // If pattern is longer than main string, it cannot be found
        if (m < n) return -1;

        // Get the next array (partial match table) for the pattern
        int[] next = getNext(pattern.toCharArray());

        // j is the index in the pattern
        int j = 0;
        // Iterate through the main string
        for (int i = 0; i < m; i++) {
            // If mismatch occurs and we've already matched some characters
            // Use the next array to determine how far to shift the pattern
            while (j > 0 && mainStr.charAt(i) != pattern.charAt(j)) {
                j = next[j - 1] + 1;
            }

            // If current characters match, advance in the pattern
            if (mainStr.charAt(i) == pattern.charAt(j)) {
                j++;
            }

            // If we've matched all characters in the pattern
            if (j == n) {
                // Return the starting index of the match
                return i - n + 1;
            }
        }
        // Pattern not found
        return -1;
    }

    /**
     * Computes the next array (partial match table) for the KMP algorithm
     *
     * The next array tells us the length of the longest proper prefix of the pattern
     * that is also a suffix of the pattern up to the current position.
     *
     * Visual example for pattern "ABABC":
     * - For prefix "A": next[0] = -1 (no proper prefix that's also a suffix)
     * - For prefix "AB": next[1] = -1 (no proper prefix that's also a suffix)
     * - For prefix "ABA": next[2] = 0 (proper prefix "A" is also a suffix)
     * - For prefix "ABAB": next[3] = 1 (proper prefix "AB" is also a suffix)
     * - For prefix "ABABC": next[4] = -1 (no proper prefix that's also a suffix)
     *
     * Time Complexity: O(n²) where n is the length of the pattern
     * Note: The implementation in this code is O(n³) due to string operations,
     * but a more efficient implementation would be O(n)
     *
     * @param pattern the pattern character array
     * @return the next array for the pattern
     */
    private int[] getNext(char[] pattern) { // O(n³)
        int n = pattern.length;
        // Handle single character pattern
        if (n == 1) return new int[0];

        // Initialize next array with -1 (no proper prefix is also a suffix)
        int[] next = new int[n - 1];
        Arrays.fill(next, -1);

        // For each position in the pattern (excluding the first character)
        for (int i = 1; i < n - 1; i++) {
            // Get the prefix up to position i+1
            String goodPrefix = new String(pattern, 0, i + 1);

            // Check all possible proper suffixes of the good prefix
            for (int j = i; j > 0; j--) {
                // Extract the suffix
                String suffix = goodPrefix.substring(j);
                int k;
                // Compare suffix with the beginning of the good prefix
                for (k = 0; k < suffix.length(); k++) {
                    if (suffix.charAt(k) != goodPrefix.charAt(k)) {
                        break;
                    }
                }

                // If the entire suffix matches with the beginning of the good prefix
                if (k == suffix.length()) {
                    // Update next[i] with the longest match length - 1
                    next[i] = Math.max(k - 1, next[i]);
                }
            }
        }
        return next;
    }
}