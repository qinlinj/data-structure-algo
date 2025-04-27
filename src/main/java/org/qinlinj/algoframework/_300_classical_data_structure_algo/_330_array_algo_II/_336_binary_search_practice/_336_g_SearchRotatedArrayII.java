package org.qinlinj.algoframework._300_classical_data_structure_algo._330_array_algo_II._336_binary_search_practice;

/**
 * Binary Search in Special Arrays - Search in Rotated Sorted Array with Duplicates
 * <p>
 * Key Concepts:
 * 1. Extending binary search to handle duplicates in a rotated sorted array
 * 2. Handling the edge case where nums[left] == nums[mid] == nums[right]
 * 3. Preprocessing to eliminate duplicates before applying the standard algorithm
 * <p>
 * This class implements LeetCode 81: Search in Rotated Sorted Array II
 * Problem: Search for a target value in a rotated sorted array that may contain duplicates
 * <p>
 * This is a follow-up to the previous problem, but with the added complexity
 * of duplicates, which can make it impossible to determine which side of the array
 * is sorted when nums[left] == nums[mid] == nums[right].
 */
public class _336_g_SearchRotatedArrayII {

    /**
     * Visual explanation of the problem with duplicates:
     * <p>
     * With a sorted array with duplicates like [1,1,1,1,1,2,1,1,1],
     * after rotation it might look like [1,1,1,1,2,1,1,1,1].
     * <p>
     * Now if mid points to a 1, and both left and right are also 1,
     * we cannot determine which side is sorted.
     * <p>
     * Solution: Skip duplicates at the boundaries before making a decision.
     */

    public static void main(String[] args) {
        _336_g_SearchRotatedArrayII solution = new _336_g_SearchRotatedArrayII();

        // Example 1
        int[] nums1 = {2, 5, 6, 0, 0, 1, 2};
        int target1 = 0;
        System.out.println("Example 1: " + solution.search(nums1, target1)); // Expected: true

        // Example 2
        int[] nums2 = {2, 5, 6, 0, 0, 1, 2};
        int target2 = 3;
        System.out.println("Example 2: " + solution.search(nums2, target2)); // Expected: false

        // Example with lots of duplicates
        int[] nums3 = {2, 2, 2, 2, 2, 2, 2, 2, 0, 1, 2};
        int target3 = 0;
        System.out.println("Example 3: " + solution.search(nums3, target3)); // Expected: true

        // Example with all duplicates
        int[] nums4 = {1, 1, 1, 1, 1, 1, 1};
        int target4 = 2;
        System.out.println("Example 4: " + solution.search(nums4, target4)); // Expected: false
    }

    /**
     * Searches for a target value in a rotated sorted array with possible duplicates
     * Time Complexity: O(log n) in best case, O(n) in worst case due to duplicates
     * Space Complexity: O(1)
     *
     * @param nums   Rotated sorted array that may contain duplicates
     * @param target Target value to find
     * @return true if target exists in the array, false otherwise
     */
    public boolean search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return false;
        }

        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            // Handle duplicates by shrinking the search space
            // This is the key difference from the previous problem
            while (left < right && nums[left] == nums[left + 1]) {
                left++;
            }
            while (left < right && nums[right] == nums[right - 1]) {
                right--;
            }

            int mid = left + (right - left) / 2;

            // Check if we found the target
            if (nums[mid] == target) {
                return true;
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
        return false;
    }
}
