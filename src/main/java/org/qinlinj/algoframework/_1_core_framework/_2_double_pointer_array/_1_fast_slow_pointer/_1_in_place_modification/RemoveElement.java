package org.qinlinj.algoframework._1_core_framework._2_double_pointer_array._1_fast_slow_pointer._1_in_place_modification;

// @formatter:off
public class RemoveElement {
    /**
     * Removes all occurrences of a specified value from an array and returns the new length.
     *
     * This method uses the two-pointer technique (slow and fast pointers) to efficiently
     * remove elements in-place without using extra space.
     *
     * Visual example:
     * Original array: [3, 2, 2, 3, 4, 5, 3] with val = 3
     *
     * Initial state:
     * [3, 2, 2, 3, 4, 5, 3]
     *  s
     *  f
     *
     * After first iteration (f=0, val matches, only fast advances):
     * [3, 2, 2, 3, 4, 5, 3]
     *  s
     *     f
     *
     * After second iteration (f=1, slow copies 2 and advances):
     * [2, 2, 2, 3, 4, 5, 3]
     *     s
     *        f
     *
     * Final state after complete execution:
     * [2, 2, 4, 5, 4, 5, 3] (first 4 elements are valid)
     *              s
     *                       f
     *
     * Return value: 4 (the new length after removing all occurrences of val)
     *
     * @param nums The integer array from which to remove elements
     * @param val The value to be removed from the array
     * @return The length of the array after removing the specified value
     */
    public int removeElement(int[] nums, int val) {
        // Initialize two pointers:
        // slow tracks the position for the next element to keep
        // fast scans through the array to find elements different from val
        int fast = 0, slow = 0;

        // Iterate through the array with fast pointer
        while (fast < nums.length) {
            // When we find an element different from val
            if (nums[fast] != val) {
                // Copy the element to the slow pointer position
                // This maintains nums[0..slow-1] as the array of elements different from val
                nums[slow] = nums[fast];

                // Move slow pointer forward to prepare for the next element to keep
                slow++;
            }

            // Always move fast pointer forward to continue scanning the array
            fast++;
        }

        // The slow pointer represents the length of the new array without the specified value
        return slow;
    }
}
