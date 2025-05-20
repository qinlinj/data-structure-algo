package org.qinlinj.algoframework._800_dynamic_programming_algo._820_subsequence_problems._822_maximum_subarray;

/**
 * Sliding Window Approach to the Maximum Subarray Problem
 * <p>
 * Key Insight:
 * The sliding window can be used to solve this problem by expanding the window when the sum is
 * positive (or zero) and shrinking it when the sum becomes negative.
 * <p>
 * Algorithm Steps:
 * 1. Initialize a window with left and right pointers both at 0.
 * 2. Expand the window by moving the right pointer and add the new element to the window sum.
 * 3. Update the maximum sum if the current window sum is larger.
 * 4. If the window sum becomes negative, shrink the window from the left until the sum is no longer negative.
 * 5. Repeat until the right pointer reaches the end of the array.
 * <p>
 * Time Complexity: O(n) - Each element is processed at most twice (once when added, once when removed)
 * Space Complexity: O(1) - Only using a constant amount of extra space
 * <p>
 * Edge Case Handling:
 * - When all numbers are negative, the algorithm correctly returns the largest negative number
 * (the maximum subarray will be a single element).
 * - The maximum sum is updated after expanding the window, ensuring we capture all possible subarrays.
 */
public class _822_b_MaxSubArraySlidingWindow {

    /**
     * Main method to demonstrate the sliding window approach
     */
    public static void main(String[] args) {
        _822_b_MaxSubArraySlidingWindow solution = new _822_b_MaxSubArraySlidingWindow();

        // Example 1: Mixed positive and negative numbers
        int[] nums1 = {-3, 1, 3, -1, 2, -4, 2};
        System.out.println("Example 1 result: " + solution.maxSubArray(nums1)); // Expected: 5

        // Example 2: All negative numbers
        int[] nums2 = {-2, -1, -3, -4, -1, -2, -5};
        System.out.println("Example 2 result: " + solution.maxSubArray(nums2)); // Expected: -1

        // Example 3: All positive numbers
        int[] nums3 = {1, 2, 3, 4, 5};
        System.out.println("Example 3 result: " + solution.maxSubArray(nums3)); // Expected: 15

        // Example 4: Single element array
        int[] nums4 = {5};
        System.out.println("Example 4 result: " + solution.maxSubArray(nums4)); // Expected: 5
    }

    public int maxSubArray(int[] nums) {
        int left = 0, right = 0;
        int windowSum = 0, maxSum = Integer.MIN_VALUE;

        while (right < nums.length) {
            // Expand the window and update the window sum
            windowSum += nums[right];
            right++;

            // Update the maximum sum found so far
            maxSum = Math.max(maxSum, windowSum);

            // Shrink the window if the sum becomes negative
            while (windowSum < 0) {
                windowSum -= nums[left];
                left++;
            }
        }

        return maxSum;
    }
}