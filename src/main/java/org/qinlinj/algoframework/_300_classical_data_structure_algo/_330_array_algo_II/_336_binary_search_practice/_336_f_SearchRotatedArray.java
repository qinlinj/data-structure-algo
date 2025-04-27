package org.qinlinj.algoframework._300_classical_data_structure_algo._330_array_algo_II._336_binary_search_practice;

/**
 * Binary Search in Special Arrays - Search in Rotated Sorted Array
 * <p>
 * Key Concepts:
 * 1. Binary search in a rotated sorted array (containing a "cliff")
 * 2. Determining which side of the array is sorted
 * 3. Making decisions based on whether target is in the sorted portion
 * <p>
 * This class implements LeetCode 33: Search in Rotated Sorted Array
 * Problem: Search for a target value in a rotated sorted array
 * <p>
 * A rotated array is created by taking a sorted array and rotating it at some pivot.
 * Example: [0,1,2,4,5,6,7] -> [4,5,6,7,0,1,2] (rotated at index 3)
 * <p>
 * Approach:
 * 1. Determine if mid is on the left or right side of the "cliff"
 * 2. Determine which portion of the array is sorted
 * 3. Check if target is in the sorted portion, and adjust search range accordingly
 */
public class _336_f_SearchRotatedArray {

    /**
     * Visual explanation of how this works:
     * <p>
     * A rotated sorted array forms a "cliff" when visualized:
     * <p>
     * /|
     * / |
     * /  |
     * /   |          /
     * /    |         /
     * ---------
     * cliff
     * <p>
     * 1. We first determine which side of the cliff 'mid' is on by comparing nums[mid] to nums[left]
     * 2. One side of the cliff will always be sorted
     * 3. We check if target is in the sorted portion, and narrow our search accordingly
     */

    public static void main(String[] args) {
        _336_f_SearchRotatedArray solution = new _336_f_SearchRotatedArray();

        // Example 1
        int[] nums1 = {4, 5, 6, 7, 0, 1, 2};
        int target1 = 0;
        System.out.println("Example 1: " + solution.search(nums1, target1)); // Expected: 4

        // Example 2
        int[] nums2 = {4, 5, 6, 7, 0, 1, 2};
        int target2 = 3;
        System.out.println("Example 2: " + solution.search(nums2, target2)); // Expected: -1

        // Example 3
        int[] nums3 = {1};
        int target3 = 0;
        System.out.println("Example 3: " + solution.search(nums3, target3)); // Expected: -1

        // Additional example - target is at the cliff
        int[] nums4 = {4, 5, 6, 7, 0, 1, 2};
        int target4 = 7;
        System.out.println("Example 4: " + solution.search(nums4, target4)); // Expected: 3
    }

    /**
     * Searches for a target value in a rotated sorted array
     * Time Complexity: O(log n)
     * Space Complexity: O(1)
     *
     * @param nums   Rotated sorted array with no duplicates
     * @param target Target value to find
     * @return Index of target if found, -1 otherwise
     */
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        // Use closed interval [left, right]
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            // Check if we found the target
            if (nums[mid] == target) {
                return mid;
            }

            // Determine which half is sorted
            if (nums[mid] >= nums[left]) {
                // Left half is sorted [left...mid]
                if (target >= nums[left] && target < nums[mid]) {
                    // Target is in sorted left half
                    right = mid - 1;
                } else {
                    // Target is in right half
                    left = mid + 1;
                }
            } else {
                // Right half is sorted [mid...right]
                if (target > nums[mid] && target <= nums[right]) {
                    // Target is in sorted right half
                    left = mid + 1;
                } else {
                    // Target is in left half
                    right = mid - 1;
                }
            }
        }

        // Target not found
        return -1;
    }
}