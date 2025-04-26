package org.qinlinj.algoframework._300_classical_data_structure_algo._330_array_algo_II._332_sliding_window_practice;

/**
 * LeetCode 424: Longest Repeating Character Replacement
 * <p>
 * Problem Description:
 * Given a string s and an integer k, you can choose any character of the string and
 * change it to any other uppercase English character. You can perform this operation
 * at most k times. Return the length of the longest substring containing the same letter
 * you can get after performing the above operations.
 * <p>
 * Key Insight:
 * We need to find a substring where we can make at most k character replacements to have
 * all characters the same. The optimal strategy is to replace characters that are not the
 * most frequent in the current window.
 * <p>
 * Sliding Window Approach:
 * 1. For a window of size windowSize, if we want all characters to be the same:
 * - We need to replace (windowSize - maxFrequency) characters
 * - If (windowSize - maxFrequency) <= k, the window is valid
 * 2. When a window becomes invalid, we shrink it from the left
 * 3. The maximum valid window size is our answer
 * <p>
 * Time Complexity: O(n) where n is the length of the string
 * Space Complexity: O(1) as we only use a fixed-size array for character counts
 */

public class _332_d_LongestRepeatingCharacterReplacement {

    public int characterReplacement(String s, int k) {
        int[] charCount = new int[26]; // Count of each uppercase letter (A-Z)
        int maxFrequency = 0;          // Max frequency of any character in current window
        int left = 0;                  // Left pointer for sliding window
        int maxLength = 0;             // Result: max length of valid substring

        for (int right = 0; right < s.length(); right++) {
            // Expand window and update character frequency
            char currentChar = s.charAt(right);
            charCount[currentChar - 'A']++;

            // Update max frequency within current window
            maxFrequency = Math.max(maxFrequency, charCount[currentChar - 'A']);

            // Current window size is (right - left + 1)
            // Number of characters to replace = window size - max frequency
            // If number of replacements needed exceeds k, shrink window
            if (right - left + 1 - maxFrequency > k) {
                // Shrink window from left
                charCount[s.charAt(left) - 'A']--;
                left++;

                // Note: We don't need to update maxFrequency when shrinking
                // This is an optimization that doesn't affect correctness
                // The key insight is that maxFrequency can only increase as window grows
            }

            // Update max length (current window is always valid after shrinking)
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }

    /**
     * More intuitive implementation that recalculates maxFrequency after each window change.
     * This is less efficient but easier to understand.
     */
    public int characterReplacementIntuitive(String s, int k) {
        int[] charCount = new int[26];
        int left = 0;
        int maxLength = 0;

        for (int right = 0; right < s.length(); right++) {
            // Expand window
            char currentChar = s.charAt(right);
            charCount[currentChar - 'A']++;

            // Calculate max frequency in current window
            int maxFrequency = getMaxFrequency(charCount);

            // If window invalid, shrink from left
            while (right - left + 1 - maxFrequency > k) {
                charCount[s.charAt(left) - 'A']--;
                left++;
                maxFrequency = getMaxFrequency(charCount);
            }

            // Update max length
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }

    // Helper method to find maximum frequency in the count array
    private int getMaxFrequency(int[] counts) {
        int max = 0;
        for (int count : counts) {
            max = Math.max(max, count);
        }
        return max;
    }
}
