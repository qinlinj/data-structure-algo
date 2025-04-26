package org.qinlinj.algoframework._300_classical_data_structure_algo._330_array_algo_II._332_sliding_window_practice;

import java.util.*;

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

    /**
     * Visual representation of the sliding window algorithm:
     * <p>
     * Example: nums = [1,1,4,2,3], x = 5
     * <p>
     * Total sum = 11
     * Target = 11 - 5 = 6 (we need to find a subarray with sum 6)
     * <p>
     * Iteration steps:
     * <p>
     * Initialize: left=0, right=0, currentSum=0, maxLength=MIN_VALUE
     * <p>
     * 1. Add nums[0]=1: currentSum=1, window=[1]
     * currentSum(1) < target(6), continue
     * <p>
     * 2. Add nums[1]=1: currentSum=2, window=[1,1]
     * currentSum(2) < target(6), continue
     * <p>
     * 3. Add nums[2]=4: currentSum=6, window=[1,1,4]
     * currentSum(6) == target(6)
     * maxLength = max(MIN_VALUE, 3) = 3
     * <p>
     * 4. Add nums[3]=2: currentSum=8, window=[1,1,4,2]
     * currentSum(8) > target(6)
     * Remove nums[0]=1: currentSum=7, window=[1,4,2]
     * Remove nums[1]=1: currentSum=6, window=[4,2]
     * currentSum(6) == target(6)
     * maxLength = max(3, 2) = 3
     * <p>
     * 5. Add nums[4]=3: currentSum=9, window=[4,2,3]
     * currentSum(9) > target(6)
     * Remove nums[2]=4: currentSum=5, window=[2,3]
     * currentSum(5) < target(6), continue
     * <p>
     * Final maxLength = 3
     * Result = nums.length - maxLength = 5 - 3 = 2 operations
     */

    public static void main(String[] args) {
        _332_a_MinOperationsToReduceXToZero solution = new _332_a_MinOperationsToReduceXToZero();

        // Example 1
        int[] nums1 = {1, 1, 4, 2, 3};
        int x1 = 5;
        System.out.println("Example 1:");
        System.out.println("Input: nums = " + Arrays.toString(nums1) + ", x = " + x1);
        System.out.println("Output: " + solution.minOperations(nums1, x1)); // Expected: 2

        // Example 2
        int[] nums2 = {5, 6, 7, 8, 9};
        int x2 = 4;
        System.out.println("\nExample 2:");
        System.out.println("Input: nums = " + Arrays.toString(nums2) + ", x = " + x2);
        System.out.println("Output: " + solution.minOperations(nums2, x2)); // Expected: -1

        // Example 3
        int[] nums3 = {3, 2, 20, 1, 1, 3};
        int x3 = 10;
        System.out.println("\nExample 3:");
        System.out.println("Input: nums = " + Arrays.toString(nums3) + ", x = " + x3);
        System.out.println("Output: " + solution.minOperations(nums3, x3)); // Expected: 5
    }

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
