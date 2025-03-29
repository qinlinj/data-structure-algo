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
            if (pattern[next[j - 1] + 1] == pattern[j]) {
                next[j] = next[j - 1] + 1;
            } else {
                int pre = next[j - 1];

                while (pre >= 0 && pattern[pre + 1] != pattern[j]) {
                    pre = next[pre];
                }

                if (pattern[pre + 1] == pattern[j]) {
                    next[j] = pre + 1;
                } else {
                    next[j] = pre;
                }
            }
        }

        return next;
    }
}
