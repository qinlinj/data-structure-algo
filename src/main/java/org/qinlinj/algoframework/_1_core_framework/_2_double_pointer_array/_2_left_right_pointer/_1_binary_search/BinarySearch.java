package org.qinlinj.algoframework._1_core_framework._2_double_pointer_array._2_left_right_pointer._1_binary_search;

// @formatter:off
public class BinarySearch {
    /**
     * Performs binary search on a sorted array to find the index of a target value.
     *
     * This method uses the classic two-pointer approach, with left and right pointers
     * moving toward each other until they find the target or determine it doesn't exist.
     *
     * Visual example:
     * For array: [1, 3, 5, 7, 9, 11, 13] with target = 7
     *
     * Initial state:
     * [1, 3, 5, 7, 9, 11, 13]
     *  l           r
     *        m
     * (left = 0, right = 6, mid = 3, nums[mid] = 7)
     *
     * Target found at mid = 3!
     *
     * Another example (target = 6 not in array):
     * [1, 3, 5, 7, 9, 11, 13]
     *  l           r
     *        m
     * (left = 0, right = 6, mid = 3, nums[mid] = 7 > 6)
     *
     * [1, 3, 5, 7, 9, 11, 13]
     *  l     r
     *    m
     * (left = 0, right = 2, mid = 1, nums[mid] = 3 < 6)
     *
     * [1, 3, 5, 7, 9, 11, 13]
     *      l r
     *      m
     * (left = 2, right = 2, mid = 2, nums[mid] = 5 < 6)
     *
     * [1, 3, 5, 7, 9, 11, 13]
     *        lr
     * (left = 3, right = 2, loop exits)
     *
     * Return -1 as target is not found
     *
     * Time Complexity: O(log n) where n is the length of the array
     * Space Complexity: O(1)
     *
     * @param nums The sorted array to search in
     * @param target The value to search for
     * @return The index of the target if found, otherwise -1
     */
    int binarySearch(int[] nums, int target) {
        // Initialize two pointers - left at start, right at end
        int left = 0, right = nums.length - 1;

        // Continue searching while the search space is valid
        while(left <= right) {
            // Calculate middle index (avoiding potential integer overflow with large arrays)
            // Alternative: int mid = left + (right - left) / 2;
            int mid = (right + left) / 2;

            // Found the target
            if(nums[mid] == target)
                return mid;
                // Target is in the right half
            else if (nums[mid] < target)
                left = mid + 1; // Move left pointer past mid
                // Target is in the left half
            else if (nums[mid] > target)
                right = mid - 1; // Move right pointer before mid
        }

        // Target was not found in the array
        return -1;
    }
}
