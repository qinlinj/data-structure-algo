package org.qinlinj.algoframework._300_classical_data_structure_algo._320_array_algo_I._326_prefix_sum_practice._326_3_refix_sum_hash_table;

/**
 * Contiguous Array (LeetCode 525)
 * <p>
 * Key Points:
 * 1. Combines prefix sum technique with hash map for optimal solution
 * 2. Treats 0's as -1 and 1's as 1 to transform the problem
 * 3. Finding subarrays with equal 0's and 1's becomes finding subarrays with sum 0
 * 4. Uses a hash map to track prefix sum values and their first occurrence indices
 * 5. Time complexity: O(n) - single pass through the array
 * 6. Space complexity: O(n) for the hash map and prefix sum array
 * 7. Demonstrates how problem transformation can simplify solution
 */
public class FindMaxLength {
    /**
     * Finds the length of the longest contiguous subarray with an equal number of 0's and 1's
     *
     * @param nums Binary array containing only 0's and 1's
     * @return Length of the longest subarray with equal 0's and 1's
     */
    public int findMaxLength(int[] nums) {
        int n = nums.length;
        int[] preSum = new int[n + 1];
        preSum[0] = 0;

        // Calculate prefix sum, treating 0 as -1 and 1 as 1
        // This transforms the problem to finding subarrays with sum 0
        for (int i = 0; i < n; i++) {
            preSum[i + 1] = preSum[i] + (nums[i] == 0 ? -1 : 1);
        }

        // Map to store the first occurrence index of each prefix sum
        java.util.HashMap<Integer, Integer> valToIndex = new java.util.HashMap<>();
        int maxLength = 0;

        for (int i = 0; i < preSum.length; i++) {
            // If this prefix sum hasn't been seen before, record its index
            if (!valToIndex.containsKey(preSum[i])) {
                valToIndex.put(preSum[i], i);
            } else {
                // If we've seen this prefix sum before, we've found a subarray with sum 0
                // Calculate its length and update maxLength if needed
                maxLength = Math.max(maxLength, i - valToIndex.get(preSum[i]));
            }
        }

        return maxLength;
    }
}
