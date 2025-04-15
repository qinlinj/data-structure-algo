package org.qinlinj.algoframework._1_core_framework._2_double_pointer_array._2_left_right_pointer._2_n_sum;

// @formatter:off
public class TwoSum {
    /**
     * Finds two numbers in a sorted array that add up to the target sum.
     *
     * This solution uses the two-pointer approach (left-right pointers) to efficiently
     * find the pair of numbers. Since the array is sorted, we can move pointers
     * strategically based on the current sum comparison with the target.
     *
     * Visual example:
     * For array: [2, 7, 11, 15] with target = 9
     *
     * Initial state:
     * [2, 7, 11, 15]
     *  l           r
     * (left = 0, right = 3, sum = 2+15 = 17 > 9)
     *
     * Move right pointer left to decrease sum:
     * [2, 7, 11, 15]
     *  l       r
     * (left = 0, right = 2, sum = 2+11 = 13 > 9)
     *
     * Move right pointer left again:
     * [2, 7, 11, 15]
     *  l   r
     * (left = 0, right = 1, sum = 2+7 = 9 == target)
     *
     * Return [1, 2] (adding 1 to convert to 1-indexed)
     *
     * Time Complexity: O(n) where n is the length of the array
     * Space Complexity: O(1)
     *
     * @param numbers The sorted array of integers
     * @param target The target sum to find
     * @return An array of two 1-indexed positions whose elements sum to target
     */
    public int[] twoSum(int[] numbers, int target) {
        // Initialize two pointers - left at start, right at end
        int left = 0, right = numbers.length - 1;

        // Continue searching while pointers haven't crossed
        while (left < right) {
            // Calculate current sum
            int sum = numbers[left] + numbers[right];

            if (sum == target) {
                // Found the pair - return indices (adding 1 to convert to 1-indexed)
                return new int[]{left + 1, right + 1};
            } else if (sum < target) {
                // Current sum is too small, need to increase it
                // Since array is sorted, moving left pointer right increases the sum
                left++;
            } else if (sum > target) {
                // Current sum is too large, need to decrease it
                // Since array is sorted, moving right pointer left decreases the sum
                right--;
            }
        }

        // No solution found
        return new int[]{-1, -1};
    }
}
