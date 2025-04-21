package org.qinlinj.algoframework._100_core_framework._190_backtracking_algo_framework._195_permutations_duplicate_elements_no_reuse;

import java.util.*;

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
    /**
     * Generates all possible unique permutations of the given array with duplicates.
     *
     * @param nums An array of integers, possibly containing duplicates
     * @return A list containing all possible unique permutations
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new LinkedList<>();
        LinkedList<Integer> track = new LinkedList<>();
        boolean[] used = new boolean[nums.length];

        // Sort the array to group duplicates together - critical step!
        Arrays.sort(nums);

        backtrack(nums, used, track, result);
        return result;
    }

    /**
     * Backtracking function to explore the tree of all possible permutations.
     *
     * @param nums   The input array of integers (sorted)
     * @param used   Boolean array marking which elements are already used in current path
     * @param track  The current permutation being built
     * @param result The list to store all found permutations
     */
    private void backtrack(int[] nums, boolean[] used, LinkedList<Integer> track, List<List<Integer>> result) {
        // Base case: if track size equals the array length, we have a complete permutation
        if (track.size() == nums.length) {
            result.add(new LinkedList<>(track));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            // Skip if the element is already used in current permutation
            if (used[i]) {
                continue;
            }

            // Skip if current element is a duplicate and its previous duplicate hasn't been used
            // This ensures we maintain fixed relative ordering of duplicate elements
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
                continue;
            }

            // Make a choice
            used[i] = true;
            track.addLast(nums[i]);

            // Explore further
            backtrack(nums, used, track, result);

            // Undo the choice
            track.removeLast();
            used[i] = false;
        }
    }
}

