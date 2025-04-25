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
}
