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

    /**
     * Backtracking function to build permutations
     *
     * @param nums  Input array of integers
     * @param track Current path (partial permutation)
     * @param used  Array marking which numbers have been used
     */
    private void backtrack(int[] nums, LinkedList<Integer> track, boolean[] used) {
        // End condition: if the path contains all numbers
        if (track.size() == nums.length) {
            // Add a copy of the current permutation to results
            result.add(new LinkedList<>(track));
            return;
        }

        // Try each number as the next element in the permutation
        for (int i = 0; i < nums.length; i++) {
            // Skip if this number is already used
            if (used[i]) {
                continue;
            }

            // 1. Make choice - add number to path and mark as used
            track.add(nums[i]);
            used[i] = true;

            // 2. Explore further with this choice
            backtrack(nums, track, used);

            // 3. Undo choice (backtrack)
            track.removeLast();
            used[i] = false;
        }
    }
}
