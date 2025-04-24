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
}
