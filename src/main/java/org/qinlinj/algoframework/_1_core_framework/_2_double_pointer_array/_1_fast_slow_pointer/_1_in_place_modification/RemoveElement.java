package org.qinlinj.algoframework._1_core_framework._2_double_pointer_array._1_fast_slow_pointer._1_in_place_modification;

// @formatter:off
public class RemoveElement {
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
