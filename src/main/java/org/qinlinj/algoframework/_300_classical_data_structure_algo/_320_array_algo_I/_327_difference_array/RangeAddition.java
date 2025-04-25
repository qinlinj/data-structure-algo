package org.qinlinj.algoframework._300_classical_data_structure_algo._320_array_algo_I._327_difference_array;

/**
 * Range Addition (LeetCode 370)
 * <p>
 * Key Points:
 * 1. Direct application of the difference array technique
 * 2. Starts with an array of all zeros and applies multiple range updates
 * 3. Perfect demonstration of how difference arrays improve performance for range operations
 * 4. Time Complexity: O(n + k) where n is array length and k is number of updates
 * 5. Space Complexity: O(n) for the difference array
 */
public class RangeAddition {

    /**
     * Example usage
     */
    public static void main(String[] args) {
        RangeAddition solution = new RangeAddition();

        // Example: length = 5, updates = [[1,3,2],[2,4,3],[0,2,-2]]
        int length = 5;
        int[][] updates = {
                {1, 3, 2},  // Add 2 to range [1, 3]
                {2, 4, 3},  // Add 3 to range [2, 4]
                {0, 2, -2}  // Subtract 2 from range [0, 2]
        };

        int[] result = solution.getModifiedArray(length, updates);

        System.out.println("Final array after all operations:");
        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i] + " ");
        }
        // Expected output: [-2, 0, 3, 5, 3]
    }

    /**
     * LeetCode 370: Range Addition
     * <p>
     * You are given an integer array length 0 initialized with all 0's and
     * an array of operations ops where ops[i] = [start_i, end_i, inc_i]
     * indicates that you should increment all elements nums[start_i] through nums[end_i]
     * (inclusive) by inc_i.
     * <p>
     * Return the array after performing all operations.
     *
     * @param length  Length of the target array
     * @param updates Array of operations in the form [startIndex, endIndex, increment]
     * @return The final array after all operations
     */
    public int[] getModifiedArray(int length, int[][] updates) {
        // Initialize array with zeros
        int[] nums = new int[length];

        // Create difference array utility
        Difference diff = new Difference(nums);

        // Apply each update operation
        for (int[] update : updates) {
            int startIdx = update[0];
            int endIdx = update[1];
            int value = update[2];

            diff.increment(startIdx, endIdx, value);
        }

        // Return the result after all operations
        return diff.result();
    }

    /**
     * Utility class for difference array operations
     */
    static class Difference {
        // The difference array
        private int[] diff;

        /**
         * Initializes the difference array from the input array
         */
        public Difference(int[] nums) {
            if (nums.length == 0) {
                throw new IllegalArgumentException("Input array cannot be empty");
            }

            diff = new int[nums.length];
            diff[0] = nums[0];

            for (int i = 1; i < nums.length; i++) {
                diff[i] = nums[i] - nums[i - 1];
            }
        }

        /**
         * Increments all elements in the range [i, j] by val
         */
        public void increment(int i, int j, int val) {
            diff[i] += val;

            if (j + 1 < diff.length) {
                diff[j + 1] -= val;
            }
        }

        /**
         * Reconstructs the original array after all operations
         */
        public int[] result() {
            int[] res = new int[diff.length];
            res[0] = diff[0];

            for (int i = 1; i < diff.length; i++) {
                res[i] = res[i - 1] + diff[i];
            }

            return res;
        }
    }

    /**
     * Implementation Notes:
     *
     * 1. This problem is a direct application of the difference array technique.
     *
     * 2. For each update operation [start, end, inc]:
     *    - We add inc to diff[start] to increase values from start onwards
     *    - We subtract inc from diff[end+1] to stop the effect after end
     *
     * 3. After applying all operations to the difference array, we reconstruct
     *    the final array by calculating the prefix sum of the difference array.
     *
     * 4. Since the array is initialized with zeros, we don't need to handle
     *    special initialization cases in the difference array.
     */
}
