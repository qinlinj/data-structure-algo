package org.qinlinj.algoframework._100_core_framework._190_backtracking_algo_framework._195_permutations_duplicate_elements_no_reuse;

/**
 * SUMMARY OF PERMUTATION GENERATION WITH DUPLICATES USING BACKTRACKING
 * <p>
 * This class demonstrates how to generate all unique permutations of an array
 * that may contain duplicate elements using the backtracking algorithm.
 * <p>
 * Key Concepts:
 * 1. Permutation II Problem (LeetCode 47): Given an array of integers that might contain
 * duplicates, return all possible unique permutations.
 * <p>
 * 2. Handling Duplicates in Permutations:
 * - First sort the array to group duplicate elements together
 * - Use a 'used' array to track which elements have been used in the current path
 * - Add pruning logic to maintain a consistent relative order among duplicate elements
 * <p>
 * 3. Pruning Strategy:
 * - Skip when current element equals the previous element AND the previous element is not used
 * - This ensures we only use duplicate elements in a specific order
 * - Example: for [1,2,2',2''] we ensure 2 is used before 2', and 2' is used before 2''
 * <p>
 * 4. Alternative Pruning Strategies:
 * - Method 1: Using used[i-1] instead of !used[i-1] works but is less efficient
 * (it prunes fewer branches)
 * - Method 2: Using prevNum to avoid processing the same value at the same level
 * <p>
 * Time Complexity: O(n * n!) where n is the length of the array
 * Space Complexity: O(n) for recursion stack, tracking used elements, and current permutation
 */
public class PermutationWithDuplicatesGenerator {
}
