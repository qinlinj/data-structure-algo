package org.qinlinj.algoframework._1_core_framework._2_double_pointer_array._1_fast_slow_pointer._1_in_place_modification;

// @formatter:off
public class MoveZeroes {
    /**
     * Moves all zeros in an array to the end while maintaining the relative order of non-zero elements.
     *
     * This solution uses a two-step approach:
     * 1. First remove all zeros and compact non-zero elements to the front (using removeElement helper)
     * 2. Then fill the remaining positions with zeros
     *
     * Visual example:
     * Original array: [0, 1, 0, 3, 12]
     *
     * After removeElement(nums, 0):
     * [1, 3, 12, 3, 12]  (p = 3)
     *        p
     *
     * After filling remaining positions with zeros:
     * [1, 3, 12, 0, 0]
     *
     * This approach ensures O(n) time complexity with only O(1) extra space.
     *
     * @param nums The array to be modified
     */
    public void moveZeroes(int[] nums) {
        // Remove all zeros from the array and get the new effective length
        // This compacts all non-zero elements to the front of the array
        int p = removeElement(nums, 0);

        // Fill the remaining positions with zeros
        // From position p to the end of the array
        for (; p < nums.length; p++) {
            nums[p] = 0;
        }
    }

    /**
     * Removes all occurrences of a specified value from an array.
     *
     * This helper method uses the two-pointer technique to efficiently
     * remove elements in-place without using extra space.
     *
     * @param nums The array from which to remove elements
     * @param val The value to be removed
     * @return The length of the array after removing the specified value
     */
    public int removeElement(int[] nums, int val) {
        // Initialize two pointers
        int fast = 0, slow = 0;

        // Iterate through the array
        while (fast < nums.length) {
            // When we find an element different from val
            if (nums[fast] != val) {
                // Copy the element to the slow pointer position
                nums[slow] = nums[fast];
                // Move slow pointer forward
                slow++;
            }
            // Always move fast pointer forward
            fast++;
        }

        // Return the new length
        return slow;
    }
}

