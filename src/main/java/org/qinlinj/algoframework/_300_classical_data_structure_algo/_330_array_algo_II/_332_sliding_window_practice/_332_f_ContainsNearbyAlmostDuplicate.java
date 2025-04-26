package org.qinlinj.algoframework._300_classical_data_structure_algo._330_array_algo_II._332_sliding_window_practice;

import java.util.*;

/**
 * LeetCode 220: Contains Duplicate III
 * <p>
 * Problem Description:
 * Given an integer array nums and two integers k and t, determine if there are two distinct
 * indices i and j in the array such that:
 * 1. abs(i - j) <= k
 * 2. abs(nums[i] - nums[j]) <= t
 * <p>
 * Key Insight:
 * This is an advanced sliding window problem that requires us to:
 * 1. Maintain a window of size k to satisfy the indices constraint
 * 2. Efficiently check if there's any element in the window whose value is within t of the current element
 * <p>
 * Time Complexity: O(n log(min(n, k))) where n is the length of the array
 * Space Complexity: O(min(n, k)) for storing elements in the sliding window
 */

public class _332_f_ContainsNearbyAlmostDuplicate {

    /**
     * Visual representation of the sliding window algorithm using TreeSet:
     * <p>
     * Example: nums = [1,5,9,1,5,9], indexDiff = 2, valueDiff = 3
     * <p>
     * Iteration steps:
     * <p>
     * Initialize: window = {}
     * <p>
     * 1. i=0, nums[0]=1:
     * Find ceiling(1-3=-2) = null, no match
     * window={1}
     * <p>
     * 2. i=1, nums[1]=5:
     * Find ceiling(5-3=2) = null (1 is not >= 2), no match
     * window={1,5}
     * <p>
     * 3. i=2, nums[2]=9:
     * Find ceiling(9-3=6) = null (1,5 are not >= 6), no match
     * window={1,5,9}
     * <p>
     * 4. i=3, nums[3]=1:
     * Window exceeds indexDiff, remove nums[0]=1: window={5,9}
     * Find ceiling(1-3=-2) = 5, but 5 > 1+3=4, no match
     * window={1,5,9}
     * <p>
     * 5. i=4, nums[4]=5:
     * Window exceeds indexDiff, remove nums[1]=5: window={1,9}
     * Find ceiling(5-3=2) = 9, but 9 > 5+3=8, no match
     * window={1,5,9}
     * <p>
     * 6. i=5, nums[5]=9:
     * Window exceeds indexDiff, remove nums[2]=9: window={1,5}
     * Find ceiling(9-3=6) = null (1,5 are not >= 6), no match
     * window={1,5,9}
     * <p>
     * No matches found, return false
     */

    public static void main(String[] args) {
        _332_f_ContainsNearbyAlmostDuplicate solution = new _332_f_ContainsNearbyAlmostDuplicate();

        // Example 1
        int[] nums1 = {1, 2, 3, 1};
        int indexDiff1 = 3;
        int valueDiff1 = 0;
        System.out.println("Example 1:");
        System.out.println("Input: nums = " + Arrays.toString(nums1) + ", indexDiff = " + indexDiff1 + ", valueDiff = " + valueDiff1);
        System.out.println("Output: " + solution.containsNearbyAlmostDuplicate(nums1, indexDiff1, valueDiff1)); // Expected: true
        System.out.println("Window Output: " + solution.containsNearbyAlmostDuplicateWithWindow(nums1, indexDiff1, valueDiff1)); // Expected: true

        // Example 2
        int[] nums2 = {1, 5, 9, 1, 5, 9};
        int indexDiff2 = 2;
        int valueDiff2 = 3;
        System.out.println("\nExample 2:");
        System.out.println("Input: nums = " + Arrays.toString(nums2) + ", indexDiff = " + indexDiff2 + ", valueDiff = " + valueDiff2);
        System.out.println("Output: " + solution.containsNearbyAlmostDuplicate(nums2, indexDiff2, valueDiff2)); // Expected: false
        System.out.println("Window Output: " + solution.containsNearbyAlmostDuplicateWithWindow(nums2, indexDiff2, valueDiff2)); // Expected: false

        // Additional example
        int[] nums3 = {1, 2, 1, 1};
        int indexDiff3 = 1;
        int valueDiff3 = 0;
        System.out.println("\nAdditional example:");
        System.out.println("Input: nums = " + Arrays.toString(nums3) + ", indexDiff = " + indexDiff3 + ", valueDiff = " + valueDiff3);
        System.out.println("Output: " + solution.containsNearbyAlmostDuplicate(nums3, indexDiff3, valueDiff3)); // Expected: true
        System.out.println("Window Output: " + solution.containsNearbyAlmostDuplicateWithWindow(nums3, indexDiff3, valueDiff3)); // Expected: true
    }

    public boolean containsNearbyAlmostDuplicate(int[] nums, int indexDiff, int valueDiff) {
        // Edge cases
        if (nums == null || nums.length <= 1 || indexDiff <= 0 || valueDiff < 0) {
            return false;
        }

        // TreeSet to efficiently find elements within the value range
        TreeSet<Long> window = new TreeSet<>();

        for (int i = 0; i < nums.length; i++) {
            // Convert to long to avoid integer overflow
            long current = (long) nums[i];

            // Find the smallest element >= current - valueDiff
            Long ceiling = window.ceiling(current - valueDiff);

            // Check if ceiling exists and is <= current + valueDiff
            if (ceiling != null && ceiling <= current + valueDiff) {
                return true;
            }

            // Add current element to window
            window.add(current);

            // If window size exceeds indexDiff, remove the leftmost element
            if (i >= indexDiff) {
                window.remove((long) nums[i - indexDiff]);
            }
        }

        return false;
    }

    /**
     * Alternative implementation using the sliding window framework
     * with explicit left and right pointers
     */
    public boolean containsNearbyAlmostDuplicateWithWindow(int[] nums, int indexDiff, int valueDiff) {
        if (indexDiff <= 0 || valueDiff < 0) {
            return false;
        }

        int left = 0, right = 0;
        TreeSet<Long> window = new TreeSet<>();

        while (right < nums.length) {
            // Before adding the element, check if there's a value within range
            long currentVal = (long) nums[right];

            // Find element >= currentVal - valueDiff
            Long ceiling = window.ceiling(currentVal - valueDiff);
            if (ceiling != null && ceiling <= currentVal + valueDiff) {
                return true;
            }

            // Add current element to window
            window.add(currentVal);
            right++;

            // If window size exceeds indexDiff, remove leftmost element
            if (right - left > indexDiff) {
                window.remove((long) nums[left]);
                left++;
            }
        }

        return false;
    }
}