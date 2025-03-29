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
