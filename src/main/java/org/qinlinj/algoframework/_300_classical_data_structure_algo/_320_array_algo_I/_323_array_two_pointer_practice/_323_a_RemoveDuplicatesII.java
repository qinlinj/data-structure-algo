package org.qinlinj.algoframework._300_classical_data_structure_algo._320_array_algo_I._323_array_two_pointer_practice;

/**
 * Remove Duplicates from Sorted Array II (LeetCode 80)
 * ===================================================
 * <p>
 * This class implements a solution to the "Remove Duplicates from Sorted Array II" problem.
 * <p>
 * Problem:
 * Given a sorted array, remove duplicates such that each element appears at most twice.
 * The solution must be in-place with O(1) extra memory.
 * <p>
 * Example:
 * Input: [1,1,1,2,2,3]
 * Output: 5, with first five elements being [1,1,2,2,3]
 * <p>
 * Approach:
 * This problem extends the classic "Remove Duplicates from Sorted Array" problem
 * by allowing elements to appear at most twice. We use the fast-slow pointer technique:
 * <p>
 * 1. Fast pointer iterates through the array
 * 2. Slow pointer maintains the boundary of the resulting array
 * 3. A count variable tracks how many times the current element has appeared
 * 4. When a new element is encountered, reset the count
 * <p>
 * Time Complexity: O(n) where n is the length of the array
 * Space Complexity: O(1) as we modify the array in-place
 */
public class _323_a_RemoveDuplicatesII {
}
