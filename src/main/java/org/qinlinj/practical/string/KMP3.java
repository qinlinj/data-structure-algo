package org.qinlinj.practical.string;

// @formatter:off
/**
 * KMP (Knuth-Morris-Pratt) String Matching Algorithm - Further Optimized Implementation
 *
 * Advantages of KMP:
 * 1. Linear Time Complexity: Achieves O(m + n) time complexity where m is the main string length and n is the pattern length
 * 2. Efficient Pattern Matching: Eliminates redundant comparisons by using previous match information
 * 3. No Backtracking: Unlike naive approaches, KMP never goes backward in the main string
 * 4. Space Efficient: Only requires O(n) extra space for the next array
 * 5. Deterministic: Performance is consistent regardless of input patterns
 *
 * Concept and Principle:
 * The KMP algorithm is based on the observation that when a mismatch occurs, we already know
 * some of the characters in the text (the ones that matched so far). This information can be
 * used to determine where the next match could begin, avoiding unnecessary re-examination of
 * previously matched characters.
 *
 * The algorithm uses a "next" array (also called "failure function" or "partial match table")
 * that tells us how much we can shift the pattern when a mismatch occurs. This array is computed
 * from the pattern itself as a preprocessing step.
 *
 * Visual example:
 * Text:    "a a a b a a a"
 * Pattern: "b a a a"
 *
 * Step 1: Compare "a" with "b" - mismatch
 * a a a b a a a
 * b a a a
 * ^ Mismatch
 *
 * Step 2: Shift pattern by 1 position
 * a a a b a a a
 *   b a a a
 *   ^ Mismatch
 *
 * Step 3: Shift pattern by 1 position again
 * a a a b a a a
 *     b a a a
 *     ^ Mismatch
 *
 * Step 4: Shift pattern by 1 position again - match beginning
 * a a a b a a a
 *       b a a a
 *       ^ Match
 *
 * Step 5: Continue matching subsequent characters
 * a a a b a a a
 *       b a a a
 *         ^ ^ ^ All match
 *
 * Result: Pattern found at index 3
 */
public class KMP3 {
    public static void main(String[] args) {
        KMP3 b = new KMP3();
        String mainStr = "aaabaaa";
        String patternStr = "baaa";

        // Find and print the starting index of the pattern in the main string
        System.out.println(b.indexOf(mainStr, patternStr));
    }

    /**
     * Finds the first occurrence of a pattern in the main string using the KMP algorithm
     *
     * Visual example for our specific case:
     * mainStr:  "a a a b a a a"
     * pattern:  "b a a a"
     *
     * For pattern "baaa":
     * - next[0] = -1 (for 'b', default base case)
     * - next[1] = -1 (for "ba", no proper prefix that is also a suffix)
     * - next[2] = -1 (for "baa", no proper prefix that is also a suffix)
     *
     * Matching process:
     * 1. Start comparing mainStr[0]='a' with pattern[0]='b' -> mismatch, j=0, no shift needed
     * 2. Compare mainStr[1]='a' with pattern[0]='b' -> mismatch, j=0, no shift needed
     * 3. Compare mainStr[2]='a' with pattern[0]='b' -> mismatch, j=0, no shift needed
     * 4. Compare mainStr[3]='b' with pattern[0]='b' -> match, j=1
     * 5. Compare mainStr[4]='a' with pattern[1]='a' -> match, j=2
     * 6. Compare mainStr[5]='a' with pattern[2]='a' -> match, j=3
     * 7. Compare mainStr[6]='a' with pattern[3]='a' -> match, j=4
     * 8. j == n, pattern found at position 3
     *
     * Time Complexity: O(m) where m is the length of the main string
     * Space Complexity: O(1) additional space (not counting the next array)
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

        // Compute the next array for the pattern
        int[] next = getNext(pattern.toCharArray());

        // j tracks the current position in the pattern
        int j = 0;
        // Iterate through the main string
        for (int i = 0; i < m; i++) {
            // If there's a mismatch and we've matched some characters
            // Use the next array to shift the pattern appropriately
            while (j > 0 && mainStr.charAt(i) != pattern.charAt(j)) {
                j = next[j - 1] + 1;
            }

            // If current characters match, advance in the pattern
            if (mainStr.charAt(i) == pattern.charAt(j)) {
                j++;
            }

            // If we've matched the entire pattern
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
     * This version is further optimized compared to previous implementations
     *
     * The next array contains information about the longest proper prefix
     * that is also a suffix for each prefix of the pattern.
     *
     * For a pattern P[0...n-1]:
     * - next[j] = the length of the longest proper prefix of P[0...j] that is also a suffix of P[0...j]
     *
     * Visual example for pattern "ABABAC":
     * - For "A": next[0] = -1 (base case)
     * - For "AB": next[1] = -1 (no proper prefix of "AB" is also a suffix)
     * - For "ABA": next[2] = 0 ("A" is both a prefix and suffix of "ABA")
     * - For "ABAB": next[3] = 1 ("AB" is both a prefix and suffix of "ABAB")
     * - For "ABABA": next[4] = 2 ("ABA" is both a prefix and suffix of "ABABA")
     * - For "ABABAC": next[5] = -1 (no proper prefix is also a suffix)
     *
     * Time Complexity: O(n) where n is the length of the pattern
     * Space Complexity: O(n) for the next array
     *
     * @param pattern the pattern character array
     * @return the next array for the pattern
     */
    private int[] getNext(char[] pattern) {
        int n = pattern.length;
        // Handle single character pattern
        if (n == 1) return new int[0];

        // Initialize next array
        int[] next = new int[n - 1];

        // Base case: first character has no proper prefix
        next[0] = -1;

        // Compute next values for remaining positions
        for (int j = 1; j < n - 1; j++) {
            // Start with the previous position's next value
            int pre = next[j - 1];

            // While we haven't found a match and haven't reached the beginning
            // Backtrack to shorter prefixes using the next array
            while (pre != -1 && pattern[pre + 1] != pattern[j]) {
                pre = next[pre];
            }

            // If we found a character that can extend the current prefix-suffix match
            if (pattern[pre + 1] == pattern[j]) {
                pre++; // Extend the match length
            }

            // Store the computed value
            next[j] = pre;
        }

        return next;
    }
}
