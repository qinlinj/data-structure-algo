package org.qinlinj.algoframework._300_classical_data_structure_algo._330_array_algo_II._336_binary_search_practice;

/**
 * Binary Search Applications - Search Insert Position
 * <p>
 * Key Concepts:
 * 1. Using binary search to find insertion position in a sorted array
 * 2. Understanding the multiple interpretations of the left boundary search result
 * <p>
 * This class implements LeetCode 35: Search Insert Position
 * Problem: Given a sorted array and a target value, find the index where the target
 * would be inserted to maintain the sorted order.
 * <p>
 * When the target element doesn't exist in the array, the left boundary binary search
 * result has three equivalent interpretations:
 * 1. The index of the smallest element in the array that is greater than or equal to the target
 * 2. The position where the target should be inserted to maintain sorted order
 * 3. The count of elements in the array that are less than the target
 */
public class _336_h_SearchInsertPosition {

    /**
     * Examples to demonstrate the three interpretations of the result:
     * <p>
     * For array [2,3,5,7] and target = 4:
     * 1. Result is 2: The index of the smallest element (5) that's >= 4
     * 2. Result is 2: Position where 4 should be inserted to maintain sorted order
     * 3. Result is 2: There are 2 elements (2,3) less than 4
     */

    public static void main(String[] args) {
        _336_h_SearchInsertPosition solution = new _336_h_SearchInsertPosition();

        // Example 1
        int[] nums1 = {1, 3, 5, 6};
        int target1 = 5;
        System.out.println("Example 1: " + solution.searchInsert(nums1, target1)); // Expected: 2

        // Example 2
        int[] nums2 = {1, 3, 5, 6};
        int target2 = 2;
        System.out.println("Example 2: " + solution.searchInsert(nums2, target2)); // Expected: 1

        // Example 3
        int[] nums3 = {1, 3, 5, 6};
        int target3 = 7;
        System.out.println("Example 3: " + solution.searchInsert(nums3, target3)); // Expected: 4

        // Example 4
        int[] nums4 = {1, 3, 5, 6};
        int target4 = 0;
        System.out.println("Example 4: " + solution.searchInsert(nums4, target4)); // Expected: 0

        // Example from the detailed explanation
        int[] nums5 = {2, 3, 5, 7};
        int target5 = 4;
        System.out.println("Example 5: " + solution.searchInsert(nums5, target5)); // Expected: 2
    }

    /**
     * Finds the index where target should be inserted in a sorted array
     * Time Complexity: O(log n)
     * Space Complexity: O(1)
     *
     * @param nums   Sorted array with no duplicates
     * @param target Target value to insert
     * @return Index where target should be inserted
     */
    public int searchInsert(int[] nums, int target) {
        return leftBound(nums, target);
    }

    /**
     * Binary search algorithm to find the left bound
     * This returns the index of the first element >= target
     *
     * @param nums   Sorted array
     * @param target Target value
     * @return Left boundary position
     */
    private int leftBound(int[] nums, int target) {
        if (nums.length == 0) {
            return 0;
        }

        int left = 0;
        int right = nums.length; // Note: right is exclusive

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                // When target is found, continue searching left boundary
                right = mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else { // nums[mid] > target
                right = mid;
            }
        }

        return left;
    }

    /**
     * Alternative implementation with closed interval [left, right]
     */
    public int searchInsertAlternative(int[] nums, int target) {
        if (nums.length == 0) {
            return 0;
        }

        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else { // nums[mid] > target
                right = mid - 1;
            }
        }

        // When the loop ends, left > right
        // left is the insertion point
        return left;
    }
}
