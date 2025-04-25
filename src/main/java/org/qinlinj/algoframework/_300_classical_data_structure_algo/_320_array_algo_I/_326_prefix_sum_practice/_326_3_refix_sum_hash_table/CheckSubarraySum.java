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
}
