package org.qinlinj.algoframework._300_classical_data_structure_algo._330_array_algo_II._334_binary_search_template;

/**
 * _334_c_BinarySearchLeftBound.java
 * <p>
 * This class implements binary search algorithms for finding the leftmost occurrence of a target.
 * <p>
 * LEFT-BOUND BINARY SEARCH:
 * -----------------------
 * 1. Purpose: Find the leftmost/first occurrence of target in a sorted array.
 * <p>
 * 2. Two common implementations:
 * a) Half-open interval approach: [left, right) where right = nums.length
 * b) Closed interval approach: [left, right] where right = nums.length - 1
 * <p>
 * 3. Key characteristic: When target is found, DON'T return immediately.
 * Instead, continue searching in the left subarray to find the leftmost occurrence.
 * <p>
 * 4. Behavior when target doesn't exist:
 * - Returns index of the smallest element greater than target
 * - Can be used to implement floor/ceiling functions
 * - Can be modified to return -1 when target doesn't exist
 */
public class _334_c_BinarySearchLeftBound {

    /**
     * Left-bound binary search with closed interval [left, right]
     *
     * @param nums   Sorted array to search in
     * @param target Value to search for
     * @return Index of the leftmost occurrence or -1 if not found
     */
    public static int leftBoundClosedInterval(int[] nums, int target) {
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
                // Instead, narrow the right boundary to search left half
                right = mid - 1;
            } else if (nums[mid] < target) {
                // Target is in the right half
                left = mid + 1;
            } else {
                // Target is in the left half
                right = mid - 1;
            }
        }

        // Check if target exists in the array
        // After the loop, left is the potential insertion position
        if (left < 0 || left >= nums.length || nums[left] != target) {
            return -1;
        }

        return left;
    }


}
