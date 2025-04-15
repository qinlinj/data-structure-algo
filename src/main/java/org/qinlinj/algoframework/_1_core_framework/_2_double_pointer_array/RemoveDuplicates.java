package org.qinlinj.algoframework._1_core_framework._2_double_pointer_array;

public class RemoveDuplicates {
    // @formatter:off
    /**
     * Removes duplicates from a sorted array and returns the length of the array without duplicates.
     *
     * This method uses the two-pointer technique (slow and fast pointers) to efficiently remove duplicates in-place.
     *
     * Visual example:
     * Original array: [1, 1, 2, 2, 3, 4, 4, 5]
     *
     * Initial state:
     * [1, 1, 2, 2, 3, 4, 4, 5]
     *  s
     *  f
     *
     * After complete execution:
     * [1, 2, 3, 4, 5, *, *, *] (* represents values we don't care about)
     *           s
     *                     f
     *
     * Return value: 5 (the length of the array without duplicates)
     *
     * @param nums The sorted integer array from which to remove duplicates
     * @return The length of the array after removing duplicates
     */
    public int removeDuplicates(int[] nums) {
        // Handle edge case: if the array is empty, return 0
        if (nums.length == 0) {
            return 0;
        }

        // Initialize two pointers: slow tracks the position for the next unique element,
        // fast scans through the array to find unique elements
        int slow = 0, fast = 0;

        // Iterate through the array with fast pointer
        while (fast < nums.length) {
            // When we find a new unique element (different from the current unique element at slow)
            if (nums[fast] != nums[slow]) {
                // Move slow pointer forward to prepare for the next unique element
                slow++;
                // Place the newly found unique element at the slow pointer position
                // This maintains nums[0..slow] as the array of unique elements
                nums[slow] = nums[fast];
            }
            // Always move fast pointer forward to continue scanning the array
            fast++;
        }

        // The length of the array without duplicates is (slow + 1)
        // because array indices are 0-based
        return slow + 1;
    }
}
