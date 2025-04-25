package org.qinlinj.algoframework._300_classical_data_structure_algo._320_array_algo_I._326_prefix_sum_practice._326_1_prefix_sum;

/**
 * Find Pivot Index (LeetCode 724)
 * <p>
 * Key Points:
 * 1. Uses prefix sum technique to find the index where left sum equals right sum
 * 2. A pivot index is a position where the sum of all elements to the left equals the sum of all elements to the right
 * 3. Time complexity: O(n) - one pass to build prefix sum, one pass to find pivot
 * 4. Space complexity: O(n) for the prefix sum array
 * 5. Edge cases: leftmost and rightmost positions are handled by considering empty sides as sum 0
 * 6. This problem demonstrates how prefix sums can be used to quickly compare sums of different parts of an array
 */
public class PivotIndex {
}
