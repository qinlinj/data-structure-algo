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

    /**
     * Visual representation of the sliding window algorithm:
     * <p>
     * Example: s = "AABABBA", k = 1
     * <p>
     * Iteration steps:
     * <p>
     * Initialize: charCount=[0,0,...], maxFrequency=0, left=0, maxLength=0
     * <p>
     * 1. right=0, Add s[0]='A': charCount[A]=1, maxFrequency=1, window="A"
     * replacements needed = 1-1 = 0 <= k(1), valid window
     * maxLength = max(0, 1) = 1
     * <p>
     * 2. right=1, Add s[1]='A': charCount[A]=2, maxFrequency=2, window="AA"
     * replacements needed = 2-2 = 0 <= k(1), valid window
     * maxLength = max(1, 2) = 2
     * <p>
     * 3. right=2, Add s[2]='B': charCount[B]=1, maxFrequency=2, window="AAB"
     * replacements needed = 3-2 = 1 <= k(1), valid window
     * maxLength = max(2, 3) = 3
     * <p>
     * 4. right=3, Add s[3]='A': charCount[A]=3, maxFrequency=3, window="AABA"
     * replacements needed = 4-3 = 1 <= k(1), valid window
     * maxLength = max(3, 4) = 4
     * <p>
     * 5. right=4, Add s[4]='B': charCount[B]=2, maxFrequency=3, window="AABAB"
     * replacements needed = 5-3 = 2 > k(1), invalid window
     * Shrink: remove s[0]='A', charCount[A]=2, window="ABAB"
     * replacements needed = 4-2 = 2 > k(1), still invalid
     * Shrink: remove s[1]='A', charCount[A]=1, window="BAB"
     * replacements needed = 3-2 = 1 <= k(1), valid window
     * maxLength = max(4, 3) = 4
     * <p>
     * 6. Continue this process...
     * <p>
     * Final maxLength = 4
     * The optimal substring is "AABA" (or "ABBB" after replacement)
     */

    public static void main(String[] args) {
        _332_d_LongestRepeatingCharacterReplacement solution = new _332_d_LongestRepeatingCharacterReplacement();

        // Example 1
        String s1 = "ABAB";
        int k1 = 2;
        System.out.println("Example 1:");
        System.out.println("Input: s = \"" + s1 + "\", k = " + k1);
        System.out.println("Output: " + solution.characterReplacement(s1, k1)); // Expected: 4
        System.out.println("Intuitive Output: " + solution.characterReplacementIntuitive(s1, k1)); // Expected: 4

        // Example 2
        String s2 = "AABABBA";
        int k2 = 1;
        System.out.println("\nExample 2:");
        System.out.println("Input: s = \"" + s2 + "\", k = " + k2);
        System.out.println("Output: " + solution.characterReplacement(s2, k2)); // Expected: 4
        System.out.println("Intuitive Output: " + solution.characterReplacementIntuitive(s2, k2)); // Expected: 4

        // Additional example
        String s3 = "AAAA";
        int k3 = 0;
        System.out.println("\nAdditional example:");
        System.out.println("Input: s = \"" + s3 + "\", k = " + k3);
        System.out.println("Output: " + solution.characterReplacement(s3, k3)); // Expected: 4
        System.out.println("Intuitive Output: " + solution.characterReplacementIntuitive(s3, k3)); // Expected: 4
    }

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
