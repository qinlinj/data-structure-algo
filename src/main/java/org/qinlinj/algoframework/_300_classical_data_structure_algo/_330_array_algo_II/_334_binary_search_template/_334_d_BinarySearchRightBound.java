package org.qinlinj.algoframework._300_classical_data_structure_algo._330_array_algo_II._334_binary_search_template;

/**
 * _334_d_BinarySearchRightBound.java
 * <p>
 * This class implements binary search algorithms for finding the rightmost occurrence of a target.
 * <p>
 * RIGHT-BOUND BINARY SEARCH:
 * ------------------------
 * 1. Purpose: Find the rightmost/last occurrence of target in a sorted array.
 * <p>
 * 2. Two common implementations:
 * a) Half-open interval approach: [left, right) where right = nums.length
 * b) Closed interval approach: [left, right] where right = nums.length - 1
 * <p>
 * 3. Key characteristic: When target is found, DON'T return immediately.
 * Instead, continue searching in the right subarray to find the rightmost occurrence.
 * <p>
 * 4. Behavior when target doesn't exist:
 * - Returns index of the largest element smaller than target
 * - Can be used as part of range search implementations
 * - Can be modified to return -1 when target doesn't exist
 */
public class _334_d_BinarySearchRightBound {

    /**
     * Right-bound binary search with closed interval [left, right]
     *
     * @param nums   Sorted array to search in
     * @param target Value to search for
     * @return Index of the rightmost occurrence or -1 if not found
     */
    public static int rightBoundClosedInterval(int[] nums, int target) {
        // Edge case: empty array
        if (nums == null || nums.length == 0) {
            return -1;
        }

        // Initialize search boundaries for closed interval [left, right]
        int left = 0;
        int right = nums.length - 1;

        // Search while the interval is valid
        while (left <= right) {
            // Calculate middle index (avoid potential integer overflow)
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                // Target found, but don't return immediately
                // Instead, narrow the left boundary to search right half
                left = mid + 1;
            } else if (nums[mid] < target) {
                // Target is in the right half
                left = mid + 1;
            } else {
                // Target is in the left half
                right = mid - 1;
            }
        }

        // After the loop, right points to the potential rightmost occurrence
        // Check if target exists in the array
        if (right < 0 || right >= nums.length || nums[right] != target) {
            return -1;
        }

        return right;
    }

    /**
     * Right-bound binary search with half-open interval [left, right)
     *
     * @param nums   Sorted array to search in
     * @param target Value to search for
     * @return Index of the rightmost occurrence or -1 if not found
     */
    public static int rightBoundHalfOpenInterval(int[] nums, int target) {
        // Edge case: empty array
        if (nums == null || nums.length == 0) {
            return -1;
        }

        // Initialize search boundaries for half-open interval [left, right)
        int left = 0;
        int right = nums.length;

        // Search while the interval is valid
        while (left < right) {
            // Calculate middle index (avoid potential integer overflow)
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                // Target found, but don't return immediately
                // Instead, narrow the left boundary to search right half
                left = mid + 1;
            } else if (nums[mid] < target) {
                // Target is in the right half
                left = mid + 1;
            } else {
                // Target is in the left half
                right = mid;
            }
        }

        // After the loop, left-1 points to the potential rightmost occurrence
        // Check if target exists in the array
        if (left - 1 < 0 || left - 1 >= nums.length || nums[left - 1] != target) {
            return -1;
        }

        return left - 1;
    }


}