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

    private void backtrack(int[] nums, LinkedList<Integer> track, boolean[] used) {
    }
}
