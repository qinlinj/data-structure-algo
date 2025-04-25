package org.qinlinj.algoframework._300_classical_data_structure_algo._320_array_algo_I._326_prefix_sum_practice._326_1_prefix_sum;

/**
 * Product of Array Except Self (LeetCode 238)
 * <p>
 * Key Points:
 * 1. Uses prefix product technique (analogous to prefix sum)
 * 2. Calculates products from left-to-right and right-to-left
 * 3. For each position, the answer is the product of all elements to the left times all elements to the right
 * 4. Time complexity: O(n) - requires two passes through the array
 * 5. Space complexity: O(n) for the result array (not counting output array)
 * 6. Does not use division operation as required by the problem
 * 7. Can be further optimized to use O(1) extra space by reusing the output array
 */
public class ProductExceptSelf {
}
