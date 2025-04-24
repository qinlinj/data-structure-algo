package org.qinlinj.algoframework._300_classical_data_structure_algo._320_array_algo_I._324_n_sum_problems;

/**
 * Four Sum Solution (LeetCode 18)
 * =============================
 * <p>
 * This class implements a solution to the Four Sum problem by building on the Three Sum solution.
 * <p>
 * Problem:
 * Given an array of integers and a target value, find all unique quadruplets in the array
 * that sum to the target value.
 * <p>
 * Approach:
 * 1. Sort the array
 * 2. For each unique first element, call the Three Sum function on the remaining elements
 * with an adjusted target
 * 3. Each result from Three Sum combined with the current first element forms a quadruplet
 * 4. Skip duplicates to avoid duplicate quadruplets
 * <p>
 * Time Complexity: O(n³) where n is the length of the array
 * Space Complexity: O(1) excluding the output space
 */
public class FourSumSolution {
}
