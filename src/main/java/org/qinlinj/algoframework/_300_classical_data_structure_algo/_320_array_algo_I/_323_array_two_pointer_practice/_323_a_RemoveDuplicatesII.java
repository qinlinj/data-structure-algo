package org.qinlinj.algoframework._300_classical_data_structure_algo._320_array_algo_I._323_array_two_pointer_practice;

/**
 * Remove Duplicates from Sorted Array II (LeetCode 80)
 * ===================================================
 * <p>
 * This class implements a solution to the "Remove Duplicates from Sorted Array II" problem.
 * <p>
 * Problem:
 * Given a sorted array, remove duplicates such that each element appears at most twice.
 * The solution must be in-place with O(1) extra memory.
 * <p>
 * Example:
 * Input: [1,1,1,2,2,3]
 * Output: 5, with first five elements being [1,1,2,2,3]
 * <p>
 * Approach:
 * This problem extends the classic "Remove Duplicates from Sorted Array" problem
 * by allowing elements to appear at most twice. We use the fast-slow pointer technique:
 * <p>
 * 1. Fast pointer iterates through the array
 * 2. Slow pointer maintains the boundary of the resulting array
 * 3. A count variable tracks how many times the current element has appeared
 * 4. When a new element is encountered, reset the count
 * <p>
 * Time Complexity: O(n) where n is the length of the array
 * Space Complexity: O(1) as we modify the array in-place
 */
public class _323_a_RemoveDuplicatesII {
    /**
     * Removes duplicates from a sorted array such that each element appears at most twice.
     *
     * @param nums The sorted input array
     * @return The length of the resulting array
     */
    public int removeDuplicates(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        // Fast and slow pointers
        // slow maintains the position for the next insertion
        // nums[0..slow] contains the result subarray
        int slow = 0, fast = 0;

        // Count how many times the current element has appeared
        int count = 0;

        while (fast < nums.length) {
            if (nums[fast] != nums[slow]) {
                // When we encounter a new element, add it to the result
                slow++;
                nums[slow] = nums[fast];
                // Reset count for the new element
                count = 1;
            } else if (count < 2) {
                // When the element is the same but has appeared fewer than twice
                // we still add it to the result
                slow++;
                nums[slow] = nums[fast];
                count++;
            } else {
                // The element has already appeared twice, so we skip it
                count++;
            }
            fast++;
        }

        // Return length (index + 1)
        return slow + 1;
    }

    /**
     * Alternative implementation that more closely matches the provided solution.
     * This version resets the count when a new element is encountered.
     */
    public int removeDuplicatesAlt(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        int slow = 0, fast = 0;
        int count = 0;

        while (fast < nums.length) {
            if (nums[fast] != nums[slow]) {
                // This is a new element, add it to the result
                slow++;
                nums[slow] = nums[fast];
            } else if (slow < fast && count < 2) {
                // Element is the same but has appeared fewer than twice
                slow++;
                nums[slow] = nums[fast];
            }

            fast++;
            count++;

            // Reset count when we encounter a new element
            if (fast < nums.length && nums[fast] != nums[fast - 1]) {
                count = 0;
            }
        }

        return slow + 1;
    }

    /**
     * A cleaner implementation that may be easier to understand.
     * This uses a slightly different approach to count duplicates.
     */
    public int removeDuplicatesClean(int[] nums) {
        // Edge case
        if (nums.length <= 2) {
            return nums.length;
        }

        // Position for next insertion
        int index = 2;

        // Start from the third element
        for (int i = 2; i < nums.length; i++) {
            // If the current element is different from the element
            // two positions back in the result array, it's valid to keep
            if (nums[i] != nums[index - 2]) {
                nums[index] = nums[i];
                index++;
            }
        }

        return index;
    }
}
