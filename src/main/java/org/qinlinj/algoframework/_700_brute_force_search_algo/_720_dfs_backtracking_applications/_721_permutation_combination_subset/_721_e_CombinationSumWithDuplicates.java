package org.qinlinj.algoframework._700_brute_force_search_algo._720_dfs_backtracking_applications._721_permutation_combination_subset;

import java.util.*;

/**
 * COMBINATION SUM WITH DUPLICATES (NO REUSE)
 * <p>
 * This class implements the solution for finding all combinations from an array
 * that may contain duplicate elements, where each element can be used at most once,
 * and the combination sum equals a target value.
 * <p>
 * Key points:
 * - Similar to subset with duplicates, but with an additional constraint (sum equals target)
 * - Sort the array first to handle duplicates properly
 * - Use pruning logic to skip duplicate elements at the same level
 * - Track the current sum to optimize by early termination
 * - Time complexity: O(2^n) where n is the length of the input array
 * - Space complexity: O(n) for the recursion stack
 * <p>
 * LeetCode Problem: 40. Combination Sum II
 * Problem description: Find all unique combinations where the candidate numbers sum to a target.
 */

public class _721_e_CombinationSumWithDuplicates {

    private List<List<Integer>> res = new LinkedList<>();
    // Track the backtracking path
    private LinkedList<Integer> track = new LinkedList<>();
    // Track the current sum
    private int trackSum = 0;

    // Example usage
    public static void main(String[] args) {
        _721_e_CombinationSumWithDuplicates solution = new _721_e_CombinationSumWithDuplicates();
        int[] candidates = {10, 1, 2, 7, 6, 1, 5};
        int target = 8;
        List<List<Integer>> result = solution.combinationSum2(candidates, target);

        System.out.println("All combinations from [10,1,2,7,6,1,5] that sum to " + target + ":");
        for (List<Integer> combination : result) {
            System.out.println(combination);
        }
    }

    /**
     * Main function to find combinations that sum to target
     *
     * @param candidates Array of candidates that may contain duplicates
     * @param target     Target sum
     * @return All unique combinations that sum to target
     */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        if (candidates.length == 0) {
            return res;
        }
        // Sort the array to handle duplicates properly
        Arrays.sort(candidates);
        backtrack(candidates, 0, target);
        return res;
    }

    /**
     * Core backtracking function to explore the combination tree
     *
     * @param nums   Input array
     * @param start  Starting position to avoid duplicates
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
            // Pruning logic: skip duplicate elements at the same level
            if (i > start && nums[i] == nums[i - 1]) {
                continue;
            }
            // Make a choice
            track.add(nums[i]);
            trackSum += nums[i];
            // Explore next level
            backtrack(nums, i + 1, target);
            // Undo the choice
            track.removeLast();
            trackSum -= nums[i];
        }
    }
}
