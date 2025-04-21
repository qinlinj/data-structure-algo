package org.qinlinj.algoframework._100_core_framework._190_backtracking_algo_framework._191_subsets_unique_elements_no_reuse;

/**
 * SUMMARY OF SUBSET GENERATION USING BACKTRACKING
 * <p>
 * This class demonstrates how to generate all subsets (power set) of a given array
 * using the backtracking algorithm technique.
 * <p>
 * Key Concepts:
 * 1. Subset Problem (LeetCode 78): Given an array of unique elements, return all possible subsets.
 * <p>
 * 2. Backtracking Approach:
 * - Uses a recursive tree traversal strategy
 * - Each node in the tree represents a subset
 * - The depth of the tree corresponds to the size of the subset
 * - Tree structure prevents duplicate subsets by maintaining relative order
 * <p>
 * 3. Variations of Combination/Subset Problems:
 * - Form 1: Elements unique, no reuse allowed (basic form shown here)
 * - Form 2: Elements may have duplicates, no reuse allowed
 * - Form 3: Elements unique, reuse allowed
 * <p>
 * 4. Implementation Strategy:
 * - Use a "track" list to record the current path during recursion
 * - Use a "start" parameter to control which elements can be chosen next
 * - Add each valid state to the result set (preorder position)
 * - Follow the standard backtracking pattern: choose → explore → unchoose
 * <p>
 * Time Complexity: O(n * 2^n) where n is the length of the array
 * Space Complexity: O(n) for recursion stack, O(2^n) for storing all subsets
 */
public class SubsetGenerator {
}
