package org.qinlinj.algoframework._300_classical_data_structure_algo._330_array_algo_II._332_sliding_window_practice;

/**
 * LeetCode 395: Longest Substring with At Least K Repeating Characters
 * <p>
 * Problem Description:
 * Given a string s and an integer k, return the length of the longest substring of s
 * such that the frequency of each character in this substring is greater than or equal to k.
 * <p>
 * Key Insight:
 * This problem requires a modified sliding window approach with an additional constraint:
 * 1. Standard sliding window techniques cannot easily determine when to shrink the window
 * 2. We add an additional constraint: the number of unique characters in the window
 * 3. By trying all possible counts of unique characters (max 26 for lowercase letters),
 * we can use the sliding window technique for each specific count
 * <p>
 * Time Complexity: O(26 * n) = O(n) where n is the length of the string
 * Space Complexity: O(1) as we only use fixed-size arrays
 */

public class _332_h_LongestSubstringWithKRepeatingChars {

    /**
     * Visual representation of the sliding window algorithm:
     * <p>
     * Example: s = "aaabb", k = 3, uniqueTarget = 2
     * <p>
     * Iteration steps:
     * <p>
     * Initialize: charCount=[0,0,...], uniqueChars=0, charsWithKFreq=0, left=0, maxLength=0
     * <p>
     * 1. right=0, Add s[0]='a': charCount[a]=1, uniqueChars=1, window="a"
     * uniqueChars(1) < uniqueTarget(2), continue
     * <p>
     * 2. right=1, Add s[1]='a': charCount[a]=2, uniqueChars=1, window="aa"
     * uniqueChars(1) < uniqueTarget(2), continue
     * <p>
     * 3. right=2, Add s[2]='a': charCount[a]=3, uniqueChars=1, charsWithKFreq=1, window="aaa"
     * uniqueChars(1) < uniqueTarget(2), continue
     * <p>
     * 4. right=3, Add s[3]='b': charCount[b]=1, uniqueChars=2, window="aaab"
     * uniqueChars(2) == uniqueTarget(2), but charsWithKFreq(1) < uniqueChars(2), continue
     * <p>
     * 5. right=4, Add s[4]='b': charCount[b]=2, uniqueChars=2, window="aaabb"
     * uniqueChars(2) == uniqueTarget(2), but charsWithKFreq(1) < uniqueChars(2), continue
     * <p>
     * Final result for uniqueTarget=2: maxLength=0 (no valid substring)
     * <p>
     * When uniqueTarget=1:
     * - We get valid substring "aaa" with uniqueChars=1, charsWithKFreq=1
     * - maxLength = 3
     * <p>
     * Overall maxLength = 3
     */

    public static void main(String[] args) {
        _332_h_LongestSubstringWithKRepeatingChars solution = new _332_h_LongestSubstringWithKRepeatingChars();

        // Example 1
        String s1 = "aaabb";
        int k1 = 3;
        System.out.println("Example 1:");
        System.out.println("Input: s = \"" + s1 + "\", k = " + k1);
        System.out.println("Output: " + solution.longestSubstring(s1, k1)); // Expected: 3
        System.out.println("Divide-and-Conquer Output: " + solution.longestSubstringDivideAndConquer(s1, k1)); // Expected: 3

        // Example 2
        String s2 = "ababbc";
        int k2 = 2;
        System.out.println("\nExample 2:");
        System.out.println("Input: s = \"" + s2 + "\", k = " + k2);
        System.out.println("Output: " + solution.longestSubstring(s2, k2)); // Expected: 5
        System.out.println("Divide-and-Conquer Output: " + solution.longestSubstringDivideAndConquer(s2, k2)); // Expected: 5

        // Additional example
        String s3 = "abcdef";
        int k3 = 2;
        System.out.println("\nAdditional example:");
        System.out.println("Input: s = \"" + s3 + "\", k = " + k3);
        System.out.println("Output: " + solution.longestSubstring(s3, k3)); // Expected: 0
        System.out.println("Divide-and-Conquer Output: " + solution.longestSubstringDivideAndConquer(s3, k3)); // Expected: 0
    }

    public int longestSubstring(String s, int k) {
        // Try all possible counts of unique characters (1 to 26)
        int maxLength = 0;

        // For each unique character count constraint
        for (int uniqueTarget = 1; uniqueTarget <= 26; uniqueTarget++) {
            maxLength = Math.max(maxLength, longestSubstringWithNUniqueChars(s, k, uniqueTarget));
        }

        return maxLength;
    }

    /**
     * Helper method: Find the longest substring with exactly uniqueTarget unique characters,
     * each appearing at least k times.
     */
    private int longestSubstringWithNUniqueChars(String s, int k, int uniqueTarget) {
        int[] charCount = new int[26]; // Count of each character in the current window
        int left = 0, right = 0;
        int uniqueChars = 0;     // Count of unique characters in window
        int charsWithKFreq = 0;  // Count of characters with at least k frequency
        int maxLength = 0;

        while (right < s.length()) {
            // Expand window
            char c = s.charAt(right);
            if (charCount[c - 'a'] == 0) {
                uniqueChars++; // New unique character
            }
            charCount[c - 'a']++;

            if (charCount[c - 'a'] == k) {
                charsWithKFreq++; // Character now has frequency k
            }

            right++;

            // Shrink window while we have more unique characters than our target
            while (uniqueChars > uniqueTarget) {
                char d = s.charAt(left);
                if (charCount[d - 'a'] == k) {
                    charsWithKFreq--; // Character no longer has frequency k
                }

                charCount[d - 'a']--;
                if (charCount[d - 'a'] == 0) {
                    uniqueChars--; // Character removed from window
                }

                left++;
            }

            // Update max length if all characters in window have frequency >= k
            if (uniqueChars == uniqueTarget && charsWithKFreq == uniqueTarget) {
                maxLength = Math.max(maxLength, right - left);
            }
        }

        return maxLength;
    }

    /**
     * Alternative recursive divide-and-conquer solution.
     * While not a sliding window approach, it's included for comparison.
     */
    public int longestSubstringDivideAndConquer(String s, int k) {
        return divideAndConquer(s, 0, s.length(), k);
    }

    private int divideAndConquer(String s, int start, int end, int k) {
        // Base case: string too short to have any valid character
        if (end - start < k) {
            return 0;
        }

        // Count character frequencies
        int[] count = new int[26];
        for (int i = start; i < end; i++) {
            count[s.charAt(i) - 'a']++;
        }

        // Check if the current substring already satisfies the condition
        boolean allAtLeastK = true;
        for (int i = 0; i < 26; i++) {
            if (count[i] > 0 && count[i] < k) {
                allAtLeastK = false;
                break;
            }
        }

        if (allAtLeastK) {
            return end - start;
        }

        // Find a character with frequency < k to split on
        int maxLength = 0;
        int splitIndex = start;
        for (int i = start; i < end; i++) {
            if (count[s.charAt(i) - 'a'] < k) {
                // Split the string and recur on both sides
                if (i > splitIndex) {
                    maxLength = Math.max(maxLength, divideAndConquer(s, splitIndex, i, k));
                }
                splitIndex = i + 1;
            }
        }

        // Handle the last segment
        if (splitIndex < end) {
            maxLength = Math.max(maxLength, divideAndConquer(s, splitIndex, end, k));
        }

        return maxLength;
    }
}