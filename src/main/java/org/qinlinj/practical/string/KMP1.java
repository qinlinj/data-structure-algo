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

    private int[] getNext(char[] pattern) { // O(n^3)
        int n = pattern.length;
        if (n == 1) return new int[0];

        int[] next = new int[n - 1];
        Arrays.fill(next, -1);

        for (int i = 1; i < n - 1; i++) {
            String goodPrefix = new String(pattern, 0, i + 1);

            for (int j = i; j > 0; j--) {
                String suffix = goodPrefix.substring(j);
                int k;
                for (k = 0; k < suffix.length(); k++) {
                    if (suffix.charAt(k) != goodPrefix.charAt(k)) {
                        break;
                    }
                }

                if (k == suffix.length()) {
                    next[i] = Math.max(k - 1, next[i]);
                }
            }
        }
        return next;
    }
}