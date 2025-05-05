package org.qinlinj.algoframework._500_data_structure_design._520_advanced_array_and_hash._522_hash_table_practice; /**
 * _522_b_LongestConsecutiveSequence
 * <p>
 * LeetCode #128: Longest Consecutive Sequence
 * <p>
 * Problem:
 * Given an unsorted array of integers nums, find the length of the longest
 * consecutive elements sequence. The sequence doesn't need to be contiguous
 * in the original array. The algorithm must run in O(n) time complexity.
 * <p>
 * Approach:
 * The key insight is to use a HashSet for O(1) lookups and focus on finding
 * the "start" of each potential sequence.
 * <p>
 * 1. Add all elements to a HashSet for O(1) lookups
 * 2. For each number in the set, check if it's the start of a sequence
 * (i.e., num-1 doesn't exist in the set)
 * 3. If it is the start, count how many consecutive numbers follow it
 * 4. Track and return the longest consecutive sequence found
 * <p>
 * Time Complexity: O(n) - Though we have nested loops, each element
 * is only visited at most twice (once in the outer loop, and at most once
 * in the inner while loop)
 * Space Complexity: O(n) - For storing elements in the HashSet
 */

import java.util.*;

public class _522_b_LongestConsecutiveSequence {

    public static void main(String[] args) {
        _522_b_LongestConsecutiveSequence solution = new _522_b_LongestConsecutiveSequence();

        // Test case 1
        int[] nums1 = {100, 4, 200, 1, 3, 2};
        int result1 = solution.longestConsecutive(nums1);
        System.out.println("Example 1: " + result1);
        // Expected output: 4 (the sequence is [1, 2, 3, 4])

        // Test case 2
        int[] nums2 = {0, 3, 7, 2, 5, 8, 4, 6, 0, 1};
        int result2 = solution.longestConsecutive(nums2);
        System.out.println("Example 2: " + result2);
        // Expected output: 9 (the sequence is [0, 1, 2, 3, 4, 5, 6, 7, 8])
    }

    public int longestConsecutive(int[] nums) {
        // Convert array to a HashSet for O(1) lookups
        HashSet<Integer> numSet = new HashSet<>();
        for (int num : nums) {
            numSet.add(num);
        }

        int longestStreak = 0;

        // Try each number as a potential start of a sequence
        for (int num : numSet) {
            // Skip if this number is not the start of a sequence
            if (numSet.contains(num - 1)) {
                continue;
            }

            // Found a potential start of a sequence
            int currentNum = num;
            int currentStreak = 1;

            // Count consecutive elements
            while (numSet.contains(currentNum + 1)) {
                currentNum++;
                currentStreak++;
            }

            // Update longest streak if needed
            longestStreak = Math.max(longestStreak, currentStreak);
        }

        return longestStreak;
    }
}