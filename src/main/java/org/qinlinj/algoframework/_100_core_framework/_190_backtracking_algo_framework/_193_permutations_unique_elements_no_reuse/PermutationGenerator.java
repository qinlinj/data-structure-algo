package org.qinlinj.algoframework._100_core_framework._190_backtracking_algo_framework._193_permutations_unique_elements_no_reuse;

/**
 * SUMMARY OF PERMUTATION GENERATION USING BACKTRACKING
 * <p>
 * This class demonstrates how to generate all possible permutations of a given array
 * using the backtracking algorithm technique.
 * <p>
 * Key Concepts:
 * 1. Permutation Problem (LeetCode 46): Given an array of distinct integers, return all possible
 * permutations (rearrangements of the elements).
 * <p>
 * 2. Difference from Subset/Combination Problems:
 * - Subset/Combination: Elements maintain relative order (only consider elements after current position)
 * - Permutation: Elements can be rearranged in any order (can use any unused element at any position)
 * - We can't use the "start" parameter to prevent duplicates as in subset generation
 * <p>
 * 3. Backtracking Approach:
 * - Uses a recursive tree traversal strategy
 * - Each path from root to leaf represents a valid permutation
 * - Need a "used" array to track which elements have already been selected in the current path
 * - Collect all leaf nodes (when path length equals array length)
 * <p>
 * 4. Implementation Strategy:
 * - Use a "track" list to record the current path during recursion
 * - Use a "used" boolean array to mark which elements are already in the current path
 * - For each position, consider all unused elements as candidates
 * - Add complete permutations to result when track size equals input array size
 * - Follow the standard backtracking pattern: choose → explore → unchoose
 * <p>
 * 5. Variations:
 * - Can be modified to generate k-length permutations by changing the base case
 * <p>
 * Time Complexity: O(n * n!) where n is the length of the array
 * Space Complexity: O(n) for recursion stack and tracking used elements
 */
public class PermutationGenerator {
}
