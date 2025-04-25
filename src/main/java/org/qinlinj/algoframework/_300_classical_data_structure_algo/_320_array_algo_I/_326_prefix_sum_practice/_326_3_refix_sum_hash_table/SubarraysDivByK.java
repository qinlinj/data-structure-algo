package org.qinlinj.algoframework._300_classical_data_structure_algo._320_array_algo_I._326_prefix_sum_practice._326_3_refix_sum_hash_table;

/**
 * Subarray Sums Divisible by K (LeetCode 974)
 * <p>
 * Key Points:
 * 1. Combines prefix sum technique with modular arithmetic
 * 2. Uses a hash map to count occurrences of each prefix sum remainder
 * 3. Key insight: If preSum[i] % k = preSum[j] % k, then subarray (i,j] sum is divisible by k
 * 4. Special handling for negative remainders in languages like Java
 * 5. Time complexity: O(n) - single pass through the array
 * 6. Space complexity: O(min(n,k)) - at most k different remainders possible
 * 7. Demonstrates how prefix sums can be combined with modular arithmetic
 */
public class SubarraysDivByK {
    /**
     * Counts the number of continuous subarrays with sum divisible by k
     *
     * @param nums Array of integers
     * @param k    Divisor
     * @return Number of subarrays with sum divisible by k
     */
    public int subarraysDivByK(int[] nums, int k) {
        int n = nums.length;

        // Prefix sum array (not strictly necessary but helps with explanation)
        int[] preSum = new int[n + 1];
        preSum[0] = 0;

        // Map to count occurrences of each remainder
        java.util.HashMap<Integer, Integer> remainderToCount = new java.util.HashMap<>();
        remainderToCount.put(0, 1);  // Empty subarray has sum 0, which is divisible by any k

        int result = 0;

        // Calculate prefix sums and count subarrays
        for (int i = 0; i < n; i++) {
            // Calculate current prefix sum
            preSum[i + 1] = preSum[i] + nums[i];

            // Calculate remainder when divided by k
            int curRemainder = preSum[i + 1] % k;

            // Handle negative remainders (Java's % operator preserves the sign)
            if (curRemainder < 0) {
                curRemainder += k;
            }

            // If we've seen this remainder before, it means there are subarrays divisible by k
            if (remainderToCount.containsKey(curRemainder)) {
                int count = remainderToCount.get(curRemainder);
                result += count;
                remainderToCount.put(curRemainder, count + 1);
            } else {
                // First occurrence of this remainder
                remainderToCount.put(curRemainder, 1);
            }
        }

        return result;
    }
}
