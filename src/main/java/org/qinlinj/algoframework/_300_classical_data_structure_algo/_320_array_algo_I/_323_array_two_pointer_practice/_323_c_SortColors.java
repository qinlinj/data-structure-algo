package org.qinlinj.algoframework._300_classical_data_structure_algo._320_array_algo_I._323_array_two_pointer_practice;

/**
 * Sort Colors (Dutch National Flag Problem) - LeetCode 75
 * =====================================================
 * <p>
 * This class implements a solution to the "Sort Colors" problem, also known as the
 * Dutch National Flag problem (originally posed by Edsger Dijkstra).
 * <p>
 * Problem:
 * Given an array nums with n objects colored red, white, or blue, sort them in-place
 * so that objects of the same color are adjacent, with the colors in the order red,
 * white, and blue. We use the integers 0, 1, and 2 to represent red, white, and blue.
 * <p>
 * You must solve this problem without using the library's sort function and with
 * a one-pass algorithm using only O(1) extra space.
 * <p>
 * Examples:
 * - [2,0,2,1,1,0] -> [0,0,1,1,2,2]
 * - [2,0,1] -> [0,1,2]
 * <p>
 * Approach:
 * We use a three-pointer technique (also known as the Dutch National Flag algorithm):
 * 1. p0: Boundary for the section containing 0s [0, p0)
 * 2. p: Current element being examined
 * 3. p2: Boundary for the section containing 2s (p2, n-1]
 * <p>
 * As we traverse the array, we:
 * - If nums[p] is 0, swap it to the p0 boundary and increment both p0 and p
 * - If nums[p] is 2, swap it to the p2 boundary and decrement p2 (don't increment p)
 * - If nums[p] is 1, just increment p
 * <p>
 * Time Complexity: O(n) - we process each element once
 * Space Complexity: O(1) - we only use a constant amount of extra space
 */
public class _323_c_SortColors {
}
