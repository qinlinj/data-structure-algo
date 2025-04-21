package org.qinlinj.algoframework._100_core_framework._190_backtracking_algo_framework._197_permutations_unique_elements_with_reuse;

import java.util.*;

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
    /**
     * Generates all possible permutations of the given array with element reuse.
     *
     * @param nums An array of distinct integers
     * @return A list containing all possible permutations with element reuse
     */
    public List<List<Integer>> permuteWithReuse(int[] nums) {
        List<List<Integer>> result = new LinkedList<>();
        LinkedList<Integer> track = new LinkedList<>();

        // Start backtracking
        backtrack(nums, track, result);
        return result;
    }

    /**
     * Backtracking function to explore the tree of all possible permutations with element reuse.
     *
     * @param nums   The input array of distinct integers
     * @param track  The current permutation being built
     * @param result The list to store all found permutations
     */
    private void backtrack(int[] nums, LinkedList<Integer> track, List<List<Integer>> result) {
        // Base case: if track size equals the array length, we have a complete permutation
        if (track.size() == nums.length) {
            result.add(new LinkedList<>(track));
            return;
        }

        // Standard backtracking framework without the 'used' check
        for (int i = 0; i < nums.length; i++) {
            // Make a choice
            track.addLast(nums[i]);

            // Explore further
            backtrack(nums, track, result);

            // Undo the choice
            track.removeLast();
        }
    }

    /**
     * Generates permutations with element reuse of a specified length.
     *
     * @param nums   An array of distinct integers
     * @param length The desired length of each permutation
     * @return A list containing all possible permutations of the specified length
     */
    public List<List<Integer>> permuteWithReuseOfLength(int[] nums, int length) {
        List<List<Integer>> result = new LinkedList<>();
        LinkedList<Integer> track = new LinkedList<>();

        backtrackWithLength(nums, length, track, result);
        return result;
    }

    /**
     * Backtracking function for generating permutations of specified length.
     */
    private void backtrackWithLength(int[] nums, int length, LinkedList<Integer> track, List<List<Integer>> result) {
        // Base case: if track size equals the desired length, we have a complete permutation
        if (track.size() == length) {
            result.add(new LinkedList<>(track));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            track.addLast(nums[i]);
            backtrackWithLength(nums, length, track, result);
            track.removeLast();
        }
    }
}
