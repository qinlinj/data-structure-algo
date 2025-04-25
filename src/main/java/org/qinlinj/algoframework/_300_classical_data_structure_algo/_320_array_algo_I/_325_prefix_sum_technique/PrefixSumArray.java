package org.qinlinj.algoframework._300_classical_data_structure_algo._320_array_algo_I._325_prefix_sum_technique;

/**
 * Prefix Sum Array Technique
 * <p>
 * Key Points:
 * 1. A prefix sum array stores cumulative sums of the original array elements
 * 2. For an array nums, prefixSum[i] represents the sum of nums[0] through nums[i-1]
 * 3. Range sum queries from index i to j can be calculated in O(1) time using:
 * prefixSum[j+1] - prefixSum[i]
 * 4. This technique optimizes multiple range sum queries from O(n) to O(1) per query
 * 5. The prefix sum array requires O(n) space and O(n) preprocessing time
 * 6. Common applications:
 * - Range sum queries (as shown in LeetCode 303)
 * - Counting elements in specific ranges
 * - Computing averages, variances over ranges
 * - Finding subarrays with specific sum properties
 */
public class PrefixSumArray {
    /**
     * Implementation of LeetCode 303: Range Sum Query - Immutable
     * Uses the prefix sum technique to efficiently answer range sum queries
     */
    static class NumArray {
        // Prefix sum array
        private int[] prefixSum;

        /**
         * Initialize the prefix sum array from the input array
         * Time complexity: O(n)
         * Space complexity: O(n)
         */
        public NumArray(int[] nums) {
            // prefixSum[0] = 0 to simplify calculations
            prefixSum = new int[nums.length + 1];

            // Calculate cumulative sum
            for (int i = 1; i < prefixSum.length; i++) {
                prefixSum[i] = prefixSum[i - 1] + nums[i - 1];
            }
        }

        /**
         * Returns the sum of elements in the range [left, right] inclusive
         * Time complexity: O(1)
         * Space complexity: O(1)
         */
        public int sumRange(int left, int right) {
            // Subtract prefix sum at left from prefix sum at right+1
            return prefixSum[right + 1] - prefixSum[left];
        }
    }
}
