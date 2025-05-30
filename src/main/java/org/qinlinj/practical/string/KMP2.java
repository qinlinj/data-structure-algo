package org.qinlinj.practical.string;

// @formatter:off
/**
 * KMP (Knuth-Morris-Pratt) String Matching Algorithm - Optimized Implementation
 *
 * Advantages of KMP:
 * 1. Efficiency: O(m + n) time complexity where m is the main string length and n is the pattern length
 * 2. Linear-time matching: Achieves string matching in linear time without backtracking
 * 3. Optimal shift: Computes the optimal shift distance when a mismatch occurs
 * 4. Memory efficiency: Only requires O(n) extra space for the next array
 *
 * Concept and Principle:
 * The KMP algorithm optimizes string matching by avoiding unnecessary character comparisons.
 * When a mismatch occurs, instead of starting over, KMP uses a precomputed "next" array
 * to skip ahead to the next potential match position.
 *
 * The "next" array stores information about the longest proper prefix that is also a suffix
 * for each prefix of the pattern. This allows the algorithm to "remember" what it has seen
 * and avoid redundant comparisons.
 *
 * Visual example of KMP matching:
 * Text:    "a a a b a a a"
 * Pattern: "b a a a"
 *
 * Step 1: Compare first characters - mismatch
 * a a a b a a a
 * b a a a
 * ^ Mismatch
 *
 * Step 2: Shift pattern by 1 and compare - mismatch
 * a a a b a a a
 *   b a a a
 *   ^ Mismatch
 *
 * Step 3: Shift pattern by 1 again - mismatch
 * a a a b a a a
 *     b a a a
 *     ^ Mismatch
 *
 * Step 4: Shift pattern by 1 again - match at first position
 * a a a b a a a
 *       b a a a
 *       ^ Match
 *
 * Step 5: Continue matching remaining characters
 * a a a b a a a
 *       b a a a
 *         ^ ^ ^ All match
 *
 * Result: Pattern found at index 3
 */
public class KMP2 {
    public static void main(String[] args) {
        KMP2 b = new KMP2();
        String mainStr = "aaabaaa";
        String patternStr = "baaa";

        // Print the index of the first occurrence of patternStr in mainStr
        System.out.println(b.indexOf(mainStr, patternStr));
    }

    /**
     * Finds the first occurrence of pattern in mainStr using the KMP algorithm
     *
     * Visual example for our specific case:
     * mainStr:  "a a a b a a a"
     * pattern:  "b a a a"
     * next array: [-1] (Since we only compute for n-1 elements)
     *
     * Time Complexity: O(m) where m is the length of mainStr
     * Space Complexity: O(n) where n is the length of pattern
     *
     * @param mainStr the main string to search in
     * @param pattern the pattern string to search for
     * @return the index of the first occurrence of pattern in mainStr, or -1 if not found
     */
    public int indexOf(String mainStr, String pattern) {
        // Handle null inputs
        if (mainStr == null || pattern == null) return -1;

        int m = mainStr.length();
        int n = pattern.length();
        // If pattern is longer than the main string, it cannot be found
        if (m < n) return -1;

        // Compute the next array for the pattern
        int[] next = getNext(pattern.toCharArray());

        // j tracks the current position in the pattern
        int j = 0;
        // Iterate through the main string
        for (int i = 0; i < m; i++) {
            // While we have a mismatch and we've already matched some characters,
            // use the next array to determine how far to shift the pattern
            while (j > 0 && mainStr.charAt(i) != pattern.charAt(j)) {
                j = next[j - 1] + 1;
            }

            // If the current characters match, advance in the pattern
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
     * Computes the next array (partial match table) for the KMP algorithm using an optimized approach
     *
     * The next array at position j indicates the length of the longest proper prefix
     * that is also a suffix of the pattern substring [0...j].
     *
     * This implementation uses dynamic programming to compute the next array efficiently.
     *
     * Visual example for pattern "ABABC":
     * - next[0] = -1 (base case, no proper prefix for single character)
     * - For "AB": If pattern[next[0]+1] == pattern[1], then next[1] = next[0]+1
     *   Otherwise, attempt to find a shorter prefix-suffix match
     * - Continue this process for each position
     *
     * Time Complexity: O(n) where n is the length of the pattern
     * Space Complexity: O(n)
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
            // Case 1: If the character after the previous match extends the current match
            if (pattern[next[j - 1] + 1] == pattern[j]) {
                next[j] = next[j - 1] + 1;
            } else {
                // Case 2: Try to find a shorter prefix that can be extended
                int pre = next[j - 1];

                // Backtrack through the next array until we find a matching prefix or reach -1
                while (pre >= 0 && pattern[pre + 1] != pattern[j]) {
                    pre = next[pre];
                }

                // If we found a matching prefix, extend it
                if (pattern[pre + 1] == pattern[j]) {
                    next[j] = pre + 1;
                } else {
                    // No matching prefix found
                    next[j] = pre;
                }
            }
        }

        return next;
    }
}
