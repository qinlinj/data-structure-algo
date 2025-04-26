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
