package org.qinlinj.algoframework._300_classical_data_structure_algo._320_array_algo_I._327_difference_array;

/**
 * Difference Array Technique
 * <p>
 * Key Points:
 * 1. While prefix sum is useful for querying sum ranges in an immutable array,
 * difference array is useful for frequent range updates in an array
 * 2. A difference array 'diff' is constructed where diff[i] = nums[i] - nums[i-1] (with diff[0] = nums[0])
 * 3. To add a value to a range nums[i..j], we only need to set diff[i] += val and diff[j+1] -= val
 * 4. After all updates, the original array can be reconstructed from the difference array
 * 5. Time complexity: O(1) for each range update, O(n) to construct or reconstruct the array
 * 6. Space complexity: O(n) for the difference array
 * 7. Perfect for scenarios with many range updates followed by few queries
 */
public class DifferenceArray {
    /**
     * Utility class for efficient range increment operations on an array
     */
    static class Difference {
        // The difference array
        private int[] diff;

        /**
         * Constructs a difference array from the input array
         *
         * @param nums The original array
         */
        public Difference(int[] nums) {
            if (nums.length == 0) {
                throw new IllegalArgumentException("Input array cannot be empty");
            }

            diff = new int[nums.length];
            // Initialize diff[0] with the first element
            diff[0] = nums[0];

            // Build the difference array
            for (int i = 1; i < nums.length; i++) {
                diff[i] = nums[i] - nums[i - 1];
            }
        }

        /**
         * Increments all elements in the range [i, j] by val
         *
         * @param i   Starting index (inclusive)
         * @param j   Ending index (inclusive)
         * @param val The value to add (can be negative)
         */
        public void increment(int i, int j, int val) {
            // Add val to nums[i]
            diff[i] += val;

            // Subtract val from nums[j+1] if j+1 is within bounds
            // This effectively limits the increment to the range [i, j]
            if (j + 1 < diff.length) {
                diff[j + 1] -= val;
            }
        }

        /**
         * Reconstructs the original array after all increment operations
         *
         * @return The array after all increment operations
         */
        public int[] result() {
            int[] res = new int[diff.length];

            // First element remains unchanged
            res[0] = diff[0];

            // Reconstruct the array using the difference array
            for (int i = 1; i < diff.length; i++) {
                res[i] = res[i - 1] + diff[i];
            }

            return res;
        }
    }

    /**
     * Utility class for efficient range increment operations on an array
     */
    static class Difference {
        // The difference array
        private int[] diff;

        /**
         * Constructs a difference array from the input array
         *
         * @param nums The original array
         */
        public Difference(int[] nums) {
            if (nums.length == 0) {
                throw new IllegalArgumentException("Input array cannot be empty");
            }

            diff = new int[nums.length];
            // Initialize diff[0] with the first element
            diff[0] = nums[0];

            // Build the difference array
            for (int i = 1; i < nums.length; i++) {
                diff[i] = nums[i] - nums[i - 1];
            }
        }

        /**
         * Increments all elements in the range [i, j] by val
         *
         * @param i   Starting index (inclusive)
         * @param j   Ending index (inclusive)
         * @param val The value to add (can be negative)
         */
        public void increment(int i, int j, int val) {
            // Add val to nums[i]
            diff[i] += val;

            // Subtract val from nums[j+1] if j+1 is within bounds
            // This effectively limits the increment to the range [i, j]
            if (j + 1 < diff.length) {
                diff[j + 1] -= val;
            }
        }

        /**
         * Reconstructs the original array after all increment operations
         *
         * @return The array after all increment operations
         */
        public int[] result() {
            int[] res = new int[diff.length];

            // First element remains unchanged
            res[0] = diff[0];

            // Reconstruct the array using the difference array
            for (int i = 1; i < diff.length; i++) {
                res[i] = res[i - 1] + diff[i];
            }

            return res;
        }
    }
}
