package org.qinlinj.algoframework._300_classical_data_structure_algo._330_array_algo_II._332_sliding_window_practice;

/**
 * LeetCode 1658: Minimum Operations to Reduce X to Zero
 * <p>
 * Problem Description:
 * Given an integer array nums and an integer x, you can either remove the leftmost or the rightmost
 * element from nums and subtract its value from x. The goal is to reduce x to exactly 0
 * with the minimum number of operations. Return the minimum number of operations needed,
 * or -1 if it's not possible.
 * <p>
 * Key Insight:
 * Instead of directly thinking about removing elements from both ends, we can transform this into
 * finding the longest subarray with sum equal to (total_sum - x). This is because removing elements
 * from both ends to sum to x is equivalent to finding the middle subarray whose sum is (total_sum - x).
 * <p>
 * Time Complexity: O(n) where n is the length of the array
 * Space Complexity: O(1) as we only use a constant amount of extra space
 * <p>
 * Sliding Window Approach:
 * 1. Calculate the target sum = sum(nums) - x
 * 2. Use sliding window to find the longest subarray with sum equal to target
 * 3. Return (array_length - subarray_length) as the minimum operations needed
 */
public class _332_a_MinOperationsToReduceXToZero {
    public int minOperations(int[] nums, int x) {
        // Calculate total sum of the array
        int totalSum = 0;
        for (int num : nums) {
            totalSum += num;
        }

        // Calculate target sum for the subarray
        int target = totalSum - x;

        // If target is negative, it's impossible to reduce x to exactly 0
        if (target < 0) {
            return -1;
        }

        // If target is 0, we need to remove all elements
        if (target == 0) {
            return nums.length;
        }

        int left = 0, right = 0;
        int currentSum = 0;
        int maxLength = Integer.MIN_VALUE;

        // Sliding window to find longest subarray with sum = target
        while (right < nums.length) {
            // Expand window
            currentSum += nums[right];
            right++;

            // Shrink window while sum exceeds target
            while (currentSum > target && left < right) {
                currentSum -= nums[left];
                left++;
            }

            // Update max length when we find a valid subarray
            if (currentSum == target) {
                maxLength = Math.max(maxLength, right - left);
            }
        }

        // If no valid subarray found
        if (maxLength == Integer.MIN_VALUE) {
            return -1;
        }

        // The answer is the total operations needed = array length - length of kept subarray
        return nums.length - maxLength;
    }
}
