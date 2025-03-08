package org.qinlinj.linear.algo.binarysearch;

/**
 * Advanced Binary Search implementations for various use cases.
 * This class provides specialized binary search methods to find:
 * - First occurrence of a target
 * - First element greater than or equal to target
 * - Last occurrence of a target
 * - Last element less than or equal to target
 * <p>
 * Time Complexity: O(log n) for all methods
 * Space Complexity: O(1) for all methods
 */
public class BinarySearch {
    public static void main(String[] args) {
        BinarySearch bs = new BinarySearch();

        // Test data: a sorted array with duplicate elements
        int[] data = new int[]{1, 3, 5, 6, 8, 22, 22, 22, 22, 22, 45, 77};

        // Find the index of the last element that is less than or equal to 23
        // In this case, it should return the index of the last occurrence of 22 (index 9)
        System.out.println(bs.lastLesserEqualTargetElement(data, 23));

        // Other test examples:
        // Find first occurrence of 22: bs.firstTargetElement(data, 22) would return 5
        // Find last occurrence of 22: bs.lastTargetElement(data, 22) would return 9
        // Find first element >= 7: bs.firstGreaterEqualTargetElement(data, 7) would return 4 (element 8)
    }

    /**
     * Alternative implementation to find the first occurrence of the target.
     * Uses a different binary search pattern with left+1 < right condition.
     *
     * @param nums   The sorted array to search in
     * @param target The value to search for
     * @return The index of the first occurrence of target, or -1 if not found
     */
    public int firstTargetElement_ql(int[] nums, int target) {
        // Check for empty or null array
        if (nums == null || nums.length == 0) return -1;

        // Initialize pointers
        int left = 0;
        int right = nums.length - 1;

        // Continue until left and right pointers are adjacent or cross
        while (left + 1 < right) {
            // Calculate the middle index (note the +1 in the numerator which shifts mid to the right)
            int mid = left + (right - left + 1) / 2;

            // If target found at mid position
            if (target == nums[mid]) {
                // Check if this is the first occurrence
                if (mid != 0 && nums[mid - 1] == target) {
                    // Not the first occurrence, look in left half
                    right = mid - 1;
                } else {
                    // First occurrence found
                    return mid;
                }
            } else if (target > nums[mid]) {
                // Target is greater, search right half
                left = mid + 1;
            } else {
                // Target is smaller, search left half
                right = mid - 1;
            }
        }

        // Check the two remaining elements
        if (nums[left] == target) return left;
        if (nums[right] == target) return right;

        // Target not found
        return -1;
    }

    /**
     * Standard implementation to find the first occurrence of the target.
     *
     * @param nums   The sorted array to search in
     * @param target The value to search for
     * @return The index of the first occurrence of target, or -1 if not found
     * <p>
     * Example: For array [1, 3, 5, 6, 8, 22, 22, 22, 22, 22, 45, 77] and target 22
     * The function will return 5, which is the index of the first occurrence of 22.
     */
    public int firstTargetElement(int[] nums, int target) {
        // Check for empty or null array
        if (nums == null || nums.length == 0) return -1;

        // Initialize pointers
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            // Calculate the middle index
            int mid = left + (right - left) / 2;

            if (target == nums[mid]) {
                // If target found, check if it's the first occurrence
                // First occurrence if either it's at index 0 or the previous element is different
                if (mid == 0 || nums[mid - 1] != target) return mid;
                else right = mid - 1; // Not the first occurrence, look left
            } else if (target < nums[mid]) {
                // Target is smaller, search left half
                right = mid - 1;
            } else {
                // Target is greater, search right half
                left = mid + 1;
            }
        }

        // Target not found
        return -1;
    }

    /**
     * Finds the index of the first element greater than or equal to the target.
     * This is useful for finding the insertion position or lower bound.
     *
     * @param nums   The sorted array to search in
     * @param target The value to search for
     * @return The index of the first element >= target, or -1 if all elements are < target
     * <p>
     * Example: For array [1, 3, 5, 6, 8, 22, 22, 22, 22, 22, 45, 77] and target 7
     * The function will return 4, which is the index of 8 (first element >= 7).
     */
    public int firstGreaterEqualTargetElement(int[] nums, int target) {
        // Check for empty or null array
        if (nums == null || nums.length == 0) return -1;

        // Initialize pointers
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            // Calculate the middle index
            int mid = left + (right - left) / 2;

            if (target <= nums[mid]) {
                // If current element is >= target, check if it's the first such element
                // First if either at index 0 or previous element is < target
                if (mid == 0 || nums[mid - 1] < target) return mid;
                else right = mid - 1; // Look for earlier occurrence
            } else { // target > nums[mid]
                // Current element is too small, search right half
                left = mid + 1;
            }
        }

        // No element >= target found
        return -1;
    }

    /**
     * Finds the index of the last occurrence of the target.
     *
     * @param nums   The sorted array to search in
     * @param target The value to search for
     * @return The index of the last occurrence of target, or -1 if not found
     * <p>
     * Example: For array [1, 3, 5, 6, 8, 22, 22, 22, 22, 22, 45, 77] and target 22
     * The function will return 9, which is the index of the last occurrence of 22.
     */
    public int lastTargetElement(int[] nums, int target) {
        // Check for empty or null array
        if (nums == null || nums.length == 0) return -1;

        // Initialize pointers
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            // Calculate the middle index
            int mid = left + (right - left) / 2;

            if (target == nums[mid]) {
                // If target found, check if it's the last occurrence
                // Last occurrence if either at the end of array or next element is different
                if (mid == nums.length - 1 || nums[mid + 1] != target) {
                    return mid;
                } else left = mid + 1; // Not the last occurrence, look right
            } else if (target > nums[mid]) {
                // Target is greater, search right half
                left = mid + 1;
            } else {
                // Target is smaller, search left half
                right = mid - 1;
            }
        }

        // Target not found
        return -1;
    }

    /**
     * Finds the index of the last element less than or equal to the target.
     * This is useful for finding the upper bound that doesn't exceed the target.
     *
     * @param nums   The sorted array to search in
     * @param target The value to search for
     * @return The index of the last element <= target, or -1 if all elements are > target
     * <p>
     * Example: For array [1, 3, 5, 6, 8, 22, 22, 22, 22, 22, 45, 77] and target 23
     * The function will return 9, which is the index of the last occurrence of 22 (last element <= 23).
     * <p>
     * Another example: For the same array and target 4
     * The function will return 1, which is the index of 3 (last element <= 4).
     */
    public int lastLesserEqualTargetElement(int[] nums, int target) {
        // Check for empty or null array
        if (nums == null || nums.length == 0) return -1;

        // Initialize pointers
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            // Calculate the middle index
            int mid = left + (right - left) / 2;

            if (target >= nums[mid]) {
                // If current element is <= target, check if it's the last such element
                // Last if either at the end of array or next element is > target
                if (mid == nums.length - 1 || nums[mid + 1] > target) {
                    return mid;
                } else left = mid + 1; // Look for later occurrence
            } else {
                // Current element is too large, search left half
                right = mid - 1;
            }
        }

        // No element <= target found
        return -1;
    }

    /**
     * Example trace of lastLesserEqualTargetElement for demonstration:
     *
     * For array [1, 3, 5, 6, 8, 22, 22, 22, 22, 22, 45, 77] and target 23:
     *
     * Iteration 1:
     * left = 0, right = 11
     * mid = 5
     * nums[5] = 22 <= 23, so we look for a potentially better answer on the right
     * left = 6
     *
     * Iteration 2:
     * left = 6, right = 11
     * mid = 8
     * nums[8] = 22 <= 23, so we continue looking right
     * left = 9
     *
     * Iteration 3:
     * left = 9, right = 11
     * mid = 10
     * nums[10] = 45 > 23, so we look left
     * right = 9
     *
     * Iteration 4:
     * left = 9, right = 9
     * mid = 9
     * nums[9] = 22 <= 23, and either it's the last element or nums[10] > 23 (true)
     * return 9
     */
}