package org.qinlinj.algoframework._300_classical_data_structure_algo._330_array_algo_II._331_sliding_window_template;

import java.util.*;

/**
 * Sliding Window Algorithm Framework and Examples
 * <p>
 * Key Concepts:
 * 1. The sliding window technique is an efficient algorithm pattern used to process arrays or strings
 * by maintaining a "window" that slides through the data.
 * <p>
 * 2. Time Complexity: O(N) vs. Brute Force O(N²)
 * - The sliding window achieves O(N) because each element enters and exits the window exactly once
 * - In contrast, brute force nested loops process some elements multiple times
 * <p>
 * 3. When to Use:
 * - Finding subarrays/substrings that meet certain conditions
 * - Problems involving continuous sequences or ranges
 * - When looking for optimal subarrays (max/min sum, etc.)
 * <p>
 * 4. Framework Structure:
 * - Initialize window bounds (left and right pointers)
 * - Expand window by moving right pointer
 * - Contract window by moving left pointer when needed
 * - Update result during expansion or contraction
 * <p>
 * 5. Important Note:
 * - Sliding window does NOT enumerate all possible subarrays
 * - It intelligently prunes the search space
 */
public class SlidingWindowAlgorithm {
    /**
     * General sliding window algorithm framework
     * This demonstrates the basic pattern used in sliding window problems
     */
    public static void slidingWindowFramework(String s) {
        // Use appropriate data structure to track window data
        // For this example, we'll use a simple counter
        int[] window = new int[128]; // Assuming ASCII characters

        // Initialize window pointers
        int left = 0, right = 0;

        // Expand window until we reach the end of the input
        while (right < s.length()) {
            // Get character that will enter the window
            char c = s.charAt(right);
            // Add it to our window data structure
            window[c]++;
            // Expand window
            right++;

            // Perform window data updates
            // (Specific logic depends on the problem)

            // Debug output (remove in final solution)
            System.out.printf("Window: [%d, %d), Current char: %c\n", left, right, c);

            // Check if window needs to shrink
            // (Condition depends on the problem)
            while (left < right && windowNeedsShrink(window)) {
                // Get character that will exit the window
                char d = s.charAt(left);
                // Remove it from our window data structure
                window[d]--;
                // Shrink window
                left++;

                // Perform window data updates after shrinking
                // (Specific logic depends on the problem)
            }

            // Update result if needed
            // (Specific logic depends on the problem)
        }
    }

    // Placeholder for window shrinking condition
    private static boolean windowNeedsShrink(int[] window) {
        // Implement specific condition based on the problem
        return false;
    }

    /**
     * Example 1: Find the length of the longest substring without repeating characters
     * LeetCode Problem 3
     */
    public static int lengthOfLongestSubstring(String s) {
        // Track character frequency in the current window
        int[] window = new int[128];

        int left = 0, right = 0;
        int maxLength = 0;

        while (right < s.length()) {
            // Expand window
            char c = s.charAt(right);
            window[c]++;
            right++;

            // Shrink window if we have a repeating character
            while (window[c] > 1) {
                char d = s.charAt(left);
                window[d]--;
                left++;
            }

            // Update result - current window size is a candidate
            maxLength = Math.max(maxLength, right - left);
        }

        return maxLength;
    }

    /**
     * Example 2: Find all anagrams in a string
     * LeetCode Problem 438
     */
    public static List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();
        if (s.length() < p.length()) return result;

        // Track character counts for pattern and current window
        int[] pCount = new int[26];
        int[] window = new int[26];

        // Initialize pattern frequency count
        for (char c : p.toCharArray()) {
            pCount[c - 'a']++;
        }

        int left = 0, right = 0;

        while (right < s.length()) {
            // Expand window
            char c = s.charAt(right);
            window[c - 'a']++;
            right++;

            // When window size equals pattern length, check for match
            if (right - left == p.length()) {
                // If window matches pattern frequency, we found an anagram
                if (Arrays.equals(window, pCount)) {
                    result.add(left);
                }

                // Shrink window from left to maintain fixed size
                char d = s.charAt(left);
                window[d - 'a']--;
                left++;
            }
        }

        return result;
    }
}
