package org.qinlinj.algoframework._700_brute_force_search_algo._720_dfs_backtracking_applications._721_permutation_combination_subset;

import java.util.*;

/**
 * COMBINATION SUM WITH REUSE
 * <p>
 * This class implements the solution for finding all combinations from an array
 * of unique elements, where each element can be used multiple times,
 * and the combination sum equals a target value.
 * <p>
 * Key points:
 * - Elements can be reused any number of times (unlimited supply)
 * - When recursing to the next level, we pass 'i' instead of 'i+1' to allow reuse
 * - Still use 'start' parameter to maintain order and prevent duplicates
 * - Track the current sum to optimize by early termination
 * - Time complexity: Exponential but harder to express precisely due to reuse
 * - Space complexity: O(target/min) where min is the smallest element in the array
 * <p>
 * LeetCode Problem: 39. Combination Sum
 * Problem description: Find all unique combinations where the candidate numbers sum to a target.
 * Each number may be used an unlimited number of times.
 */

public class _721_g_CombinationSumWithReuse {

    private List<List<Integer>> res = new LinkedList<>();
    // Track the backtracking path
    private LinkedList<Integer> track = new LinkedList<>();
    // Track the current sum
    private int trackSum = 0;

    // Example usage
    public static void main(String[] args) {
        _721_g_CombinationSumWithReuse solution = new _721_g_CombinationSumWithReuse();
        int[] candidates = {2, 3, 6, 7};
        int target = 7;
        List<List<Integer>> result = solution.combinationSum(candidates, target);

        System.out.println("All combinations from [2,3,6,7] that sum to " + target + " (with reuse):");
        for (List<Integer> combination : result) {
            System.out.println(combination);
        }
    }

    /**
     * Main function to find combinations that sum to target
     *
     * @param candidates Array of unique candidates
     * @param target     Target sum
     * @return All unique combinations that sum to target
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        if (candidates.length == 0) {
            return res;
        }
        backtrack(candidates, 0, target);
        return res;
    }

    /**
     * Core backtracking function to explore the combination tree
     *
     * @param nums   Input array
     * @param start  Starting position to maintain order
     * @param target Target sum
     */
    private void backtrack(int[] nums, int start, int target) {
        // Base case: found a combination that sums to target
        if (trackSum == target) {
            res.add(new LinkedList<>(track));
            return;
        }
        // Optimization: stop if sum exceeds target
        if (trackSum > target) {
            return;
        }

        // Standard backtracking framework
        for (int i = start; i < nums.length; i++) {
            // Make a choice
            trackSum += nums[i];
            track.add(nums[i]);
            // Key difference: pass 'i' instead of 'i+1' to allow element reuse
            backtrack(nums, i, target);
            // Undo the choice
            trackSum -= nums[i];
            track.removeLast();
        }
    }
}