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
        if (mainStr == null || pattern == null) return -1;

        int m = mainStr.length();
        int n = pattern.length();
        if (m < n) return -1;

        int[] next = getNext(pattern.toCharArray());

        int j = 0;
        for (int i = 0; i < m; i++) {
            while (j > 0 && mainStr.charAt(i) != pattern.charAt(j)) {
                j = next[j - 1] + 1;
            }

            if (mainStr.charAt(i) == pattern.charAt(j)) {
                j++;
            }

            if (j == n) {
                return i - n + 1;
            }
        }
        return -1;
    }

    private int[] getNext(char[] pattern) {
        int n = pattern.length;
        if (n == 1) return new int[0];

        int[] next = new int[n - 1];

        next[0] = -1;

        for (int j = 1; j < n - 1; j++) {
            int pre = next[j - 1];
            while (pre != -1 && pattern[pre + 1] != pattern[j]) {
                pre = next[pre];
            }
            if (pattern[pre + 1] == pattern[j]) {
                pre++;
            }
            next[j] = pre;
        }

        return next;
    }
}
