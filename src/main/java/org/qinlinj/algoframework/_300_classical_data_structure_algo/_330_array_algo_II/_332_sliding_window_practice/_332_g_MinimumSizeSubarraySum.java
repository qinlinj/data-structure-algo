package org.qinlinj.algoframework._300_classical_data_structure_algo._330_array_algo_II._332_sliding_window_practice;

import java.util.*;

/**
 * LeetCode 209: Minimum Size Subarray Sum
 * <p>
 * Problem Description:
 * Given an array of positive integers nums and a positive integer target, find the
 * minimum length of a contiguous subarray whose sum is greater than or equal to target.
 * If there is no such subarray, return 0.
 * <p>
 * Key Insight:
 * This is a classic sliding window problem:
 * 1. The positive integers constraint enables the sliding window approach
 * 2. We expand the window until the sum is >= target
 * 3. Then we shrink it from the left to find the minimum length
 * <p>
 * Time Complexity: O(n) where n is the length of the array
 * Space Complexity: O(1) as we only use a constant amount of extra space
 */

public class _332_g_MinimumSizeSubarraySum {

    /**
     * Visual representation of the sliding window algorithm:
     * <p>
     * Example: nums = [2,3,1,2,4,3], target = 7
     * <p>
     * Iteration steps:
     * <p>
     * Initialize: left=0, sum=0, minLength=MAX_VALUE
     * <p>
     * 1. right=0, nums[0]=2: sum=2, window=[2]
     * sum(2) < target(7), continue
     * <p>
     * 2. right=1, nums[1]=3: sum=5, window=[2,3]
     * sum(5) < target(7), continue
     * <p>
     * 3. right=2, nums[2]=1: sum=6, window=[2,3,1]
     * sum(6) < target(7), continue
     * <p>
     * 4. right=3, nums[3]=2: sum=8, window=[2,3,1,2]
     * sum(8) >= target(7)
     * minLength = min(MAX_VALUE, 4) = 4
     * Remove nums[0]=2: sum=6, window=[3,1,2]
     * sum(6) < target(7), continue
     * <p>
     * 5. right=4, nums[4]=4: sum=10, window=[3,1,2,4]
     * sum(10) >= target(7)
     * minLength = min(4, 4) = 4
     * Remove nums[1]=3: sum=7, window=[1,2,4]
     * sum(7) >= target(7)
     * minLength = min(4, 3) = 3
     * Remove nums[2]=1: sum=6, window=[2,4]
     * sum(6) < target(7), continue
     * <p>
     * 6. right=5, nums[5]=3: sum=9, window=[2,4,3]
     * sum(9) >= target(7)
     * minLength = min(3, 3) = 3
     * Remove nums[3]=2: sum=7, window=[4,3]
     * sum(7) >= target(7)
     * minLength = min(3, 2) = 2
     * Remove nums[4]=4: sum=3, window=[3]
     * sum(3) < target(7), continue
     * <p>
     * Final minLength = 2
     * The shortest subarray with sum >= target is [4,3]
     */

    public static void main(String[] args) {
        _332_g_MinimumSizeSubarraySum solution = new _332_g_MinimumSizeSubarraySum();

        // Example 1
        int[] nums1 = {2, 3, 1, 2, 4, 3};
        int target1 = 7;
        System.out.println("Example 1:");
        System.out.println("Input: target = " + target1 + ", nums = " + Arrays.toString(nums1));
        System.out.println("Output: " + solution.minSubArrayLen(target1, nums1)); // Expected: 2
        System.out.println("Window Output: " + solution.minSubArrayLenWithWindow(target1, nums1)); // Expected: 2

        // Example 2
        int[] nums2 = {1, 4, 4};
        int target2 = 4;
        System.out.println("\nExample 2:");
        System.out.println("Input: target = " + target2 + ", nums = " + Arrays.toString(nums2));
        System.out.println("Output: " + solution.minSubArrayLen(target2, nums2)); // Expected: 1
        System.out.println("Window Output: " + solution.minSubArrayLenWithWindow(target2, nums2)); // Expected: 1

        // Example 3
        int[] nums3 = {1, 1, 1, 1, 1, 1, 1, 1};
        int target3 = 11;
        System.out.println("\nExample 3:");
        System.out.println("Input: target = " + target3 + ", nums = " + Arrays.toString(nums3));
        System.out.println("Output: " + solution.minSubArrayLen(target3, nums3)); // Expected: 0
        System.out.println("Window Output: " + solution.minSubArrayLenWithWindow(target3, nums3)); // Expected: 0
    }

    public int minSubArrayLen(int target, int[] nums) {
        int left = 0;
        int sum = 0;
        int minLength = Integer.MAX_VALUE;

        for (int right = 0; right < nums.length; right++) {
            // Expand window by adding current element
            sum += nums[right];

            // Shrink window from left as long as sum >= target
            while (sum >= target) {
                // Update minimum length
                minLength = Math.min(minLength, right - left + 1);

                // Remove leftmost element from sum
                sum -= nums[left];
                // Move left pointer forward
                left++;
            }
        }

        // If no valid subarray was found, return 0
        return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }

    /**
     * Alternative implementation using the explicit sliding window framework
     */
    public int minSubArrayLenWithWindow(int target, int[] nums) {
        int left = 0, right = 0;
        int windowSum = 0;
        int minLength = Integer.MAX_VALUE;

        while (right < nums.length) {
            // Expand window and add element
            windowSum += nums[right];
            right++;

            // Shrink window from left as long as sum >= target
            while (windowSum >= target && left < right) {
                // Update minimum length (right is exclusive, so no +1 needed)
                minLength = Math.min(minLength, right - left);

                // Remove leftmost element
                windowSum -= nums[left];
                left++;
            }
        }

        return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }
}