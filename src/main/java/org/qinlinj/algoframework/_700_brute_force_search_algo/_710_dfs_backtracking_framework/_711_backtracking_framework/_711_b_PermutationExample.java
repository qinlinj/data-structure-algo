package org.qinlinj.algoframework._700_brute_force_search_algo._710_dfs_backtracking_framework._711_backtracking_framework;

import java.util.*;

public class _711_b_PermutationExample {
    private List<List<Integer>> result = new LinkedList<>();

    /**
     * Main function to compute all permutations of a given array
     *
     * @param nums Array of distinct integers
     * @return List of all possible permutations
     */
    public List<List<Integer>> permute(int[] nums) {
        // Record the "path" (current permutation being built)
        LinkedList<Integer> track = new LinkedList<>();

        // Track which elements have been used to avoid duplicates
        boolean[] used = new boolean[nums.length];

        // Start backtracking
        backtrack(nums, track, used);

        return result;
    }
}
