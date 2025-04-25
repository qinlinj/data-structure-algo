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
     * Example usage of the score range counting problem
     * Counts students with scores in given ranges
     */
    public static int countStudentsInScoreRange(int[] scores, int minScore, int maxScore) {
        // Assuming scores are between 0 and 100
        int[] countByScore = new int[101];

        // Count students for each score
        for (int score : scores) {
            countByScore[score]++;
        }

        // Convert to prefix sum array
        for (int i = 1; i < countByScore.length; i++) {
            countByScore[i] += countByScore[i - 1];
        }

        // Query the range [minScore, maxScore]
        // Note: Need to handle edge case for minScore = 0
        return minScore == 0 ? countByScore[maxScore] : countByScore[maxScore] - countByScore[minScore - 1];
    }

    /**
     * Demonstrates using the NumArray class for range sum queries
     */
    public static void main(String[] args) {
        // Example from LeetCode 303
        int[] nums = {-2, 0, 3, -5, 2, -1};
        NumArray numArray = new NumArray(nums);

        System.out.println("Sum of range [0, 2]: " + numArray.sumRange(0, 2));  // Should return 1
        System.out.println("Sum of range [2, 5]: " + numArray.sumRange(2, 5));  // Should return -1
        System.out.println("Sum of range [0, 5]: " + numArray.sumRange(0, 5));  // Should return -3

        // Example of score range counting
        int[] scores = {65, 75, 85, 95, 85, 75, 65, 95, 85, 75};
        System.out.println("Students with scores between 80 and 90: " +
                countStudentsInScoreRange(scores, 80, 90));  // Should return 3
    }

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
