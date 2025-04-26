package org.qinlinj.algoframework._300_classical_data_structure_algo._330_array_algo_II._332_sliding_window_practice;

import java.util.*;

/**
 * LeetCode 713: Subarray Product Less Than K
 * <p>
 * Problem Description:
 * Given an array of positive integers nums and an integer k, return the number of
 * contiguous subarrays where the product of all elements in the subarray is strictly
 * less than k.
 * <p>
 * Key Insight:
 * The sliding window technique works well here because:
 * 1. All elements are positive, so adding elements always increases the product
 * 2. Removing elements always decreases the product
 * 3. This monotonic property allows us to efficiently find all valid subarrays
 * <p>
 * Time Complexity: O(n) where n is the length of the array
 * Space Complexity: O(1) as we only use a constant amount of extra space
 * <p>
 * Sliding Window Approach:
 * 1. Maintain a sliding window where the product of all elements is less than k
 * 2. For each right pointer position, count all valid subarrays ending at that position
 * 3. Shrink the window from the left when the product exceeds or equals k
 */
public class _332_b_SubarrayProductLessThanK {

    /**
     * Visual representation of the sliding window algorithm:
     * <p>
     * Example: nums = [10, 5, 2, 6], k = 100
     * <p>
     * Iteration steps:
     * <p>
     * Initialize: left=0, right=0, product=1, count=0
     * <p>
     * 1. right=0, Add nums[0]=10: product=10, window=[10]
     * product(10) < k(100)
     * count += 1 (subarray [10])
     * count = 1, right++
     * <p>
     * 2. right=1, Add nums[1]=5: product=50, window=[10,5]
     * product(50) < k(100)
     * count += 2 (subarrays [5], [10,5])
     * count = 3, right++
     * <p>
     * 3. right=2, Add nums[2]=2: product=100, window=[10,5,2]
     * product(100) == k(100), so we need to shrink window
     * Remove nums[0]=10: product=10, window=[5,2]
     * product(10) < k(100)
     * count += 2 (subarrays [2], [5,2])
     * count = 5, right++
     * <p>
     * 4. right=3, Add nums[3]=6: product=60, window=[5,2,6]
     * product(60) < k(100)
     * count += 3 (subarrays [6], [2,6], [5,2,6])
     * count = 8, right++
     * <p>
     * Final count = 8
     * The 8 subarrays are: [10], [5], [2], [6], [10,5], [5,2], [2,6], [5,2,6]
     */

    public static void main(String[] args) {
        _332_b_SubarrayProductLessThanK solution = new _332_b_SubarrayProductLessThanK();

        // Example 1
        int[] nums1 = {10, 5, 2, 6};
        int k1 = 100;
        System.out.println("Example 1:");
        System.out.println("Input: nums = " + Arrays.toString(nums1) + ", k = " + k1);
        System.out.println("Output: " + solution.numSubarrayProductLessThanK(nums1, k1)); // Expected: 8

        // Example 2
        int[] nums2 = {1, 2, 3};
        int k2 = 0;
        System.out.println("\nExample 2:");
        System.out.println("Input: nums = " + Arrays.toString(nums2) + ", k = " + k2);
        System.out.println("Output: " + solution.numSubarrayProductLessThanK(nums2, k2)); // Expected: 0

        // Additional example
        int[] nums3 = {1, 1, 1};
        int k3 = 2;
        System.out.println("\nAdditional example:");
        System.out.println("Input: nums = " + Arrays.toString(nums3) + ", k = " + k3);
        System.out.println("Output: " + solution.numSubarrayProductLessThanK(nums3, k3)); // Expected: 6

        // Edge case
        int[] nums4 = {2, 3, 4};
        int k4 = 1;
        System.out.println("\nEdge case:");
        System.out.println("Input: nums = " + Arrays.toString(nums4) + ", k = " + k4);
        System.out.println("Output: " + solution.numSubarrayProductLessThanK(nums4, k4)); // Expected: 0
    }

    public int numSubarrayProductLessThanK(int[] nums, int k) {
        // Edge case: if k <= 1, no subarray can have product less than k
        // (since all elements are positive integers, minimum product is 1)
        if (k <= 1) {
            return 0;
        }

        int left = 0, right = 0;
        int product = 1; // Initialize product to multiplicative identity
        int count = 0;   // Count of valid subarrays

        while (right < nums.length) {
            // Expand window by including the right element
            product *= nums[right];

            // Shrink window from the left while product >= k
            while (product >= k) {
                product /= nums[left];
                left++;
            }

            // At this point, window [left, right] contains a valid product < k
            // For each new right position, we add subarrays ending at right position
            // The number of these subarrays is (right - left + 1)
            count += right - left + 1;

            // Move right pointer forward
            right++;
        }

        return count;
    }
}
