package org.qinlinj.algoframework._700_brute_force_search_algo._720_dfs_backtracking_applications._721_permutation_combination_subset; /**
 * SUBSETS (WITHOUT DUPLICATES, NO REUSE)
 * <p>
 * This class implements the solution for generating all subsets from an array
 * of unique elements where each element can be used at most once.
 * <p>
 * Key points:
 * - This is the most basic backtracking pattern for subset generation
 * - The recursive function uses a 'start' parameter to avoid duplicates
 * - Each node in the backtracking tree represents a subset
 * - Time complexity: O(2^n) where n is the length of the input array
 * - Space complexity: O(n) for the recursion stack
 * <p>
 * LeetCode Problem: 78. Subsets
 * Problem description: Given an integer array nums of unique elements, return all possible subsets.
 */

import java.util.*;

public class _721_a_SubsetsWithoutDuplicates {

    private List<List<Integer>> res = new LinkedList<>();
    // Track the backtracking path
    private LinkedList<Integer> track = new LinkedList<>();

    // Example usage
    public static void main(String[] args) {
        _721_a_SubsetsWithoutDuplicates solution = new _721_a_SubsetsWithoutDuplicates();
        int[] nums = {1, 2, 3};
        List<List<Integer>> result = solution.subsets(nums);

        System.out.println("All subsets of [1,2,3]:");
        for (List<Integer> subset : result) {
            System.out.println(subset);
        }
    }

    /**
     * Main function to generate all subsets
     * @param nums An array of unique integers
     * @return All possible subsets
     */
    public List<List<Integer>> subsets(int[] nums) {
        backtrack(nums, 0);
        return res;
    }

    /**
     * Core backtracking function to explore the subset tree
     * @param nums Input array
     * @param start Starting position to avoid duplicates
     */
    private void backtrack(int[] nums, int start) {
        // Pre-order position: add the current subset to result
        res.add(new LinkedList<>(track));

        // Standard backtracking framework
        for (int i = start; i < nums.length; i++) {
            // Make a choice
            track.addLast(nums[i]);
            // Use start parameter to control branch traversal and avoid duplicates
            backtrack(nums, i + 1);
            // Undo the choice
            track.removeLast();
        }
    }
}