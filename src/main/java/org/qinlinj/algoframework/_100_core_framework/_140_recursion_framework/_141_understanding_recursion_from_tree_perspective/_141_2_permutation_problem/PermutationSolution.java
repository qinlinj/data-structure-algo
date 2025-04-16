package org.qinlinj.algoframework._100_core_framework._140_recursion_framework._141_understanding_recursion_from_tree_perspective._141_2_permutation_problem;

import java.util.*;

public class PermutationSolution {
    private List<List<Integer>> result = new LinkedList<>();

    /**
     * Main function to find all permutations of the given array of distinct integers.
     *
     * @param nums An array of distinct integers
     * @return All possible permutations
     */
    public List<List<Integer>> permute(int[] nums) {
        // Track the current permutation being built
        LinkedList<Integer> track = new LinkedList<>();

        // Keep track of which elements have been used
        boolean[] used = new boolean[nums.length];

        // Start the backtracking process
        backtrack(nums, track, used);

        return result;
    }

    /**
     * Backtracking function to build permutations.
     *
     * @param nums  The input array of distinct integers
     * @param track The current permutation being built
     * @param used  Boolean array tracking which elements have been used
     */
    private void backtrack(int[] nums, LinkedList<Integer> track, boolean[] used) {
        // Base case: if the track is the same length as nums, we have a complete permutation
        if (track.size() == nums.length) {
            // Add a copy of the current permutation to our result
            result.add(new LinkedList<>(track));
            return;
        }

        // Try each number as the next element in our permutation
        for (int i = 0; i < nums.length; i++) {
            // Skip elements that have already been used
            if (used[i]) {
                continue;
            }

            // Make a choice - add the current number to our permutation
            track.add(nums[i]);
            used[i] = true;

            // Explore further with this choice
            backtrack(nums, track, used);

            // Undo the choice (backtrack) to try other possibilities
            track.removeLast();
            used[i] = false;
        }
    }
}
