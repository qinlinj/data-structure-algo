package org.qinlinj.algoframework._300_classical_data_structure_algo._320_array_algo_I._323_array_two_pointer_practice;

/**
 * Squares of a Sorted Array (LeetCode 977)
 * ======================================
 * <p>
 * This class implements a solution to the "Squares of a Sorted Array" problem.
 * <p>
 * Problem:
 * Given an integer array sorted in non-decreasing order, return an array of the
 * squares of each number sorted in non-decreasing order.
 * <p>
 * Examples:
 * - [-4,-1,0,3,10] -> [0,1,9,16,100]
 * - [-7,-3,2,3,11] -> [4,9,9,49,121]
 * <p>
 * Approach:
 * The key insight is that squaring can change the order of elements because
 * negative numbers become positive. The largest squares will come from either the
 * most negative or most positive elements of the original array.
 * <p>
 * 1. Use two pointers starting at opposite ends of the array
 * 2. Compare the absolute values of elements at both pointers
 * 3. Square the larger absolute value and place it at the end of the result array
 * 4. Move the appropriate pointer and continue
 * <p>
 * Time Complexity: O(n) where n is the length of the array
 * Space Complexity: O(n) for the result array
 */
public class _323_e_SortedSquares {
}
