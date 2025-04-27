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

    /**
     * Implementation of ceiling function using binary search
     * Returns the smallest element that is greater than or equal to target
     *
     * @param nums   Sorted array to search in
     * @param target Value to find ceiling of
     * @return Index of ceiling element or -1 if no such element exists
     */
    public static int ceiling(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                // Found exact match, this is the ceiling
                return mid;
            } else if (nums[mid] < target) {
                // Move right to find larger elements
                left = mid + 1;
            } else {
                // Move left to find smaller elements
                right = mid - 1;
            }
        }

        // left will point to the smallest element greater than target
        return (left < nums.length) ? left : -1;
    }

    /**
     * Find the range (first and last occurrence) of target in a sorted array
     * Uses both left and right bound binary search
     *
     * @param nums   Sorted array to search in
     * @param target Value to search for
     * @return Array with [first, last] indices or [-1, -1] if not found
     */
    public static int[] searchRange(int[] nums, int target) {
        int[] result = {-1, -1};

        // Use left bound search to find first occurrence
        result[0] = _334_c_BinarySearchLeftBound.leftBoundClosedInterval(nums, target);

        // If target not found, return [-1, -1]
        if (result[0] == -1) {
            return result;
        }

        // Use right bound search to find last occurrence
        result[1] = rightBoundClosedInterval(nums, target);

        return result;
    }

    /**
     * Demonstrate right-bound binary search with examples
     */
    public static void main(String[] args) {
        // Example 1: Target exists multiple times (should find rightmost)
        int[] nums1 = {1, 2, 2, 2, 3, 4};
        int target1 = 2;
        System.out.println("Example 1: Target exists multiple times");
        System.out.println("Array: [1, 2, 2, 2, 3, 4], Target: " + target1);
        System.out.println("Expected: 3, Actual (closed): " + rightBoundClosedInterval(nums1, target1));
        System.out.println("Expected: 3, Actual (half-open): " + rightBoundHalfOpenInterval(nums1, target1));

        // Example 2: Target exists once
        int[] nums2 = {1, 3, 5, 7, 9};
        int target2 = 5;
        System.out.println("\nExample 2: Target exists once");
        System.out.println("Array: [1, 3, 5, 7, 9], Target: " + target2);
        System.out.println("Expected: 2, Actual (closed): " + rightBoundClosedInterval(nums2, target2));
        System.out.println("Expected: 2, Actual (half-open): " + rightBoundHalfOpenInterval(nums2, target2));

        // Example 3: Target doesn't exist
        int[] nums3 = {1, 3, 5, 7, 9};
        int target3 = 6;
        System.out.println("\nExample 3: Target doesn't exist");
        System.out.println("Array: [1, 3, 5, 7, 9], Target: " + target3);
        System.out.println("Expected: -1, Actual (closed): " + rightBoundClosedInterval(nums3, target3));
        System.out.println("Expected: -1, Actual (half-open): " + rightBoundHalfOpenInterval(nums3, target3));

        // Example 4: Ceiling function demo
        int[] nums4 = {1, 3, 5, 7, 9};
        int target4 = 6;
        System.out.println("\nExample 4: Ceiling function for non-existent target");
        System.out.println("Array: [1, 3, 5, 7, 9], Target: " + target4);
        int ceilingIndex = ceiling(nums4, target4);
        System.out.println("Ceiling index: " + ceilingIndex + ", Ceiling value: " +
                (ceilingIndex != -1 ? nums4[ceilingIndex] : "N/A"));

        // Example 5: Find range demo
        int[] nums5 = {1, 2, 2, 2, 3, 4};
        int target5 = 2;
        System.out.println("\nExample 5: Search range for target with multiple occurrences");
        System.out.println("Array: [1, 2, 2, 2, 3, 4], Target: " + target5);
        int[] range = searchRange(nums5, target5);
        System.out.println("Expected range: [1, 3], Actual range: [" + range[0] + ", " + range[1] + "]");

        // Example 6: Find range when target doesn't exist
        int target6 = 6;
        System.out.println("\nExample 6: Search range for non-existent target");
        System.out.println("Array: [1, 2, 2, 2, 3, 4], Target: " + target6);
        range = searchRange(nums5, target6);
        System.out.println("Expected range: [-1, -1], Actual range: [" + range[0] + ", " + range[1] + "]");
    }
}