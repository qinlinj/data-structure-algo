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
     * Example usage for the Difference array class
     */
    public static void main(String[] args) {
        // Original array
        int[] nums = {8, 2, 6, 3, 1};
        System.out.println("Original array:");
        printArray(nums);

        // Create a Difference object
        Difference df = new Difference(nums);

        // Perform some range increment operations
        // Add 3 to elements in range [0, 2]
        df.increment(0, 2, 3);

        // Subtract 2 from elements in range [1, 3]
        df.increment(1, 3, -2);

        // Add 5 to elements in range [3, 4]
        df.increment(3, 4, 5);

        // Get the result after all operations
        int[] result = df.result();
        System.out.println("\nAfter range operations:");
        printArray(result);

        // Expected result: [11, 3, 7, 6, 6]
        // Original:        [8,  2, 6, 3, 1]
        // [0,2] += 3:      [11, 5, 9, 3, 1]
        // [1,3] -= 2:      [11, 3, 7, 1, 1]
        // [3,4] += 5:      [11, 3, 7, 6, 6]

        // Verify with traditional method
        int[] expected = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            expected[i] = nums[i];
        }

        // Add 3 to elements in range [0, 2]
        for (int i = 0; i <= 2; i++) {
            expected[i] += 3;
        }

        // Subtract 2 from elements in range [1, 3]
        for (int i = 1; i <= 3; i++) {
            expected[i] -= 2;
        }

        // Add 5 to elements in range [3, 4]
        for (int i = 3; i <= 4; i++) {
            expected[i] += 5;
        }

        System.out.println("\nExpected result (using traditional method):");
        printArray(expected);

        // Show that the results match
        boolean match = true;
        for (int i = 0; i < result.length; i++) {
            if (result[i] != expected[i]) {
                match = false;
                break;
            }
        }
        System.out.println("\nResults match: " + match);
    }

    /**
     * Utility method to print an array
     */
    private static void printArray(int[] arr) {
        System.out.print("[");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i < arr.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
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

    /**
     * Comparison between Prefix Sum and Difference Array:
     *
     * 1. Prefix Sum:
     *    - Used for querying sum of ranges in an immutable array
     *    - Efficient for multiple queries on fixed array
     *    - Operation: query range sum in O(1)
     *    - Limitation: inefficient for array modifications
     *
     * 2. Difference Array:
     *    - Used for modifying ranges in an array
     *    - Efficient for multiple range updates
     *    - Operation: update range values in O(1)
     *    - Good for scenarios with many updates and few queries
     *
     * The key insight is that these two techniques are complementary and
     * solve different problems efficiently.
     */
}
