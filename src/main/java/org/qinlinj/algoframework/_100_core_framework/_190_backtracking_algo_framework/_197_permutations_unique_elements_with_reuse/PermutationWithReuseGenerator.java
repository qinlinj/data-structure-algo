package org.qinlinj.algoframework._100_core_framework._190_backtracking_algo_framework._197_permutations_unique_elements_with_reuse;

/**
 * SUMMARY OF PERMUTATION GENERATION WITH ELEMENT REUSE
 * <p>
 * This class demonstrates how to generate all permutations of a given array
 * where elements can be reused multiple times using the backtracking algorithm.
 * <p>
 * Key Concepts:
 * 1. Permutation with Reuse: Given an array of distinct integers, return all possible
 * permutations where elements can be used multiple times.
 * <p>
 * 2. Element Reuse Strategy:
 * - In standard permutation, we use a 'used' array to prevent element reuse
 * - For permutation with reuse, we simply remove the 'used' array check
 * - Each element is available for selection at every position
 * <p>
 * 3. Backtracking Approach:
 * - Uses a recursive tree traversal strategy without element usage tracking
 * - For n distinct elements and permutation length n, there are n^n possible permutations
 * - Each complete path to leaf nodes represents a valid permutation
 * <p>
 * 4. Implementation Strategy:
 * - Use a "track" list to record the current permutation being built
 * - No need for a 'used' array since elements can be reused
 * - Add complete permutations to result when track size equals desired length
 * - Follow the standard backtracking pattern: choose → explore → unchoose
 * <p>
 * 5. Summary of Permutation/Combination/Subset Patterns:
 * - Form 1: Elements unique, no reuse (standard case)
 * - Form 2: Elements may have duplicates, no reuse (requires sorting and pruning)
 * - Form 3: Elements unique, reuse allowed (modify recursion or remove used checks)
 * <p>
 * Time Complexity: O(n^k) where n is the number of elements and k is the permutation length
 * Space Complexity: O(k) for recursion stack and tracking current permutation
 */
public class PermutationWithReuseGenerator {
}
