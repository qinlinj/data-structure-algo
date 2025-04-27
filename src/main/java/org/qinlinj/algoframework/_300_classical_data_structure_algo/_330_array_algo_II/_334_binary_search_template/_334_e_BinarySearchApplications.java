package org.qinlinj.algoframework._300_classical_data_structure_algo._330_array_algo_II._334_binary_search_template;

/**
 * _334_e_BinarySearchApplications.java
 * <p>
 * This class demonstrates practical applications of binary search algorithms.
 * <p>
 * BINARY SEARCH APPLICATIONS:
 * -------------------------
 * 1. Solving LeetCode Problem #34: Find First and Last Position of Element in Sorted Array
 * - This uses both left and right bound binary search
 * <p>
 * 2. Finding the smallest element >= target (ceiling) and largest element <= target (floor)
 * - Useful for approximation and range queries
 * <p>
 * 3. Searching in rotated sorted arrays
 * - Modified binary search that handles rotation point
 * <p>
 * 4. Finding peek element in a mountain array
 * - Using binary search on arrays that are not strictly monotonic
 * <p>
 * KEY TAKEAWAYS:
 * -------------
 * 1. Binary search is not just for exact matches - it can be used to find boundaries,
 * ranges, and positions in various array structures.
 * <p>
 * 2. The core idea of binary search is to eliminate half of the remaining search space
 * in each iteration.
 * <p>
 * 3. Always be careful with these details:
 * - Search interval definition: [left, right] or [left, right)
 * - Loop termination condition: left <= right or left < right
 * - Mid calculation: mid = left + (right - left) / 2 (avoids overflow)
 * - Boundary updates: mid+1, mid-1, or just mid
 * - Edge case handling: empty arrays, targets not present, etc.
 */

public class _334_e_BinarySearchApplications {
    /**
     * LeetCode #34: Find First and Last Position of Element in Sorted Array
     * Returns an array containing the starting and ending position of target
     *
     * @param nums   Sorted array to search in
     * @param target Value to search for
     * @return Array with [first, last] indices or [-1, -1] if not found
     */
    public static int[] searchRange(int[] nums, int target) {
        int[] result = {-1, -1};

        // Edge case: empty array
        if (nums == null || nums.length == 0) {
            return result;
        }

        // Find left boundary
        int left = 0;
        int right = nums.length - 1;

        // First pass: find leftmost occurrence
        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                right = mid - 1;  // Continue searching on left side
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        // Check if target exists
        if (left >= nums.length || nums[left] != target) {
            return result;  // Target not found
        }

        result[0] = left;  // Store leftmost position

        // Reset boundaries for right search
        left = 0;
        right = nums.length - 1;

        // Second pass: find rightmost occurrence
        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                left = mid + 1;  // Continue searching on right side
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        result[1] = right;  // Store rightmost position

        return result;
    }

    /**
     * Search in rotated sorted array (LeetCode #33)
     * Array was originally sorted but rotated at some pivot
     *
     * @param nums   Rotated sorted array
     * @param target Value to search for
     * @return Index of target or -1 if not found
     */
    public static int searchInRotatedArray(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            // Found target
            if (nums[mid] == target) {
                return mid;
            }

            // Check which side is sorted
            if (nums[left] <= nums[mid]) {
                // Left side is sorted
                if (target >= nums[left] && target < nums[mid]) {
                    // Target is in the left sorted portion
                    right = mid - 1;
                } else {
                    // Target is in the right portion
                    left = mid + 1;
                }
            } else {
                // Right side is sorted
                if (target > nums[mid] && target <= nums[right]) {
                    // Target is in the right sorted portion
                    left = mid + 1;
                } else {
                    // Target is in the left portion
                    right = mid - 1;
                }
            }
        }

        return -1;  // Target not found
    }
}
