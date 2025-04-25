package org.qinlinj.algoframework._300_classical_data_structure_algo._320_array_algo_I._326_prefix_sum_practice._326_3_refix_sum_hash_table;

/**
 * Continuous Subarray Sum (LeetCode 523)
 * <p>
 * Key Points:
 * 1. Combines prefix sum technique with hash map for modular arithmetic
 * 2. Uses property: if (preSum[i] - preSum[j]) % k = 0, then preSum[i] % k = preSum[j] % k
 * 3. Stores remainder of prefix sum divided by k in hash map with its index
 * 4. Time complexity: O(n) - single pass through the array
 * 5. Space complexity: O(min(n, k)) for the hash map
 * 6. Special case handling for k = 0 and length requirement of at least 2
 * 7. Demonstrates how prefix sums can be used with modular arithmetic
 */
public class CheckSubarraySum {
    /**
     * Checks if there exists a subarray of length at least 2 with a sum that is a multiple of k
     *
     * @param nums Array of integers
     * @param k    The divisor
     * @return True if a valid subarray exists, false otherwise
     */
    public boolean checkSubarraySum(int[] nums, int k) {
        int n = nums.length;

        // Calculate prefix sum array
        int[] preSum = new int[n + 1];
        preSum[0] = 0;
        for (int i = 1; i <= n; i++) {
            preSum[i] = preSum[i - 1] + nums[i - 1];
        }

        // Map to store remainder -> index
        // We only need to keep track of the first occurrence of each remainder
        java.util.HashMap<Integer, Integer> valToIndex = new java.util.HashMap<>();

        for (int i = 0; i < preSum.length; i++) {
            // Calculate remainder when divided by k
            int val = k == 0 ? preSum[i] : preSum[i] % k;

            // If this remainder hasn't been seen before, record its index
            if (!valToIndex.containsKey(val)) {
                valToIndex.put(val, i);
            }
            // If we've seen this remainder before and the distance between indices is at least 2
            else if (i - valToIndex.get(val) >= 2) {
                return true;
            }
            // Otherwise, we keep the original index (to maximize the potential subarray length)
        }

        return false;
    }
}
