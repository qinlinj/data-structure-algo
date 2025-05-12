package org.qinlinj.algoframework._700_brute_force_search_algo._720_dfs_backtracking_applications._721_permutation_combination_subset;

import java.util.*;

/**
 * PERMUTATIONS (WITHOUT DUPLICATES, NO REUSE)
 * <p>
 * This class implements the solution for generating all permutations of an array
 * of unique elements where each element can be used at most once.
 * <p>
 * Key points:
 * - Unlike subset/combination problems, permutation problems must consider different arrangements
 * - We need to use a 'used' array to track which elements have been used in the current path
 * - Permutation problems don't use the 'start' parameter as they allow elements to be selected in any order
 * - Time complexity: O(n!) where n is the length of the input array
 * - Space complexity: O(n) for the recursion stack and 'used' array
 * <p>
 * LeetCode Problem: 46. Permutations
 * Problem description: Given an array nums of distinct integers, return all possible permutations.
 */

public class _721_c_PermutationsWithoutDuplicates {

    private List<List<Integer>> res = new LinkedList<>();
    // Track the backtracking path
    private LinkedList<Integer> track = new LinkedList<>();
    // Track used elements to avoid duplicates
    private boolean[] used;

    // Example usage
    public static void main(String[] args) {
        _721_c_PermutationsWithoutDuplicates solution = new _721_c_PermutationsWithoutDuplicates();
        int[] nums = {1, 2, 3};

        // Full permutations
        List<List<Integer>> result = solution.permute(nums);
        System.out.println("All permutations of [1,2,3]:");
        for (List<Integer> perm : result) {
            System.out.println(perm);
        }

        // Reset for k-permutations demo
        solution = new _721_c_PermutationsWithoutDuplicates();
        int k = 2;
        List<List<Integer>> kResult = solution.permuteK(nums, k);
        System.out.println("\nAll " + k + "-permutations of [1,2,3]:");
        for (List<Integer> perm : kResult) {
            System.out.println(perm);
        }
    }

    /**
     * Main function to generate all permutations
     *
     * @param nums An array of unique integers
     * @return All possible permutations
     */
    public List<List<Integer>> permute(int[] nums) {
        used = new boolean[nums.length];
        backtrack(nums);
        return res;
    }

    /**
     * Core backtracking function to explore the permutation tree
     *
     * @param nums Input array
     */
    private void backtrack(int[] nums) {
        // Base case: reached a leaf node
        if (track.size() == nums.length) {
            res.add(new LinkedList<>(track));
            return;
        }

        // Standard backtracking framework
        for (int i = 0; i < nums.length; i++) {
            // Skip used elements
            if (used[i]) {
                continue;
            }
            // Make a choice
            used[i] = true;
            track.addLast(nums[i]);
            // Explore next level
            backtrack(nums);
            // Undo the choice
            track.removeLast();
            used[i] = false;
        }
    }

    /**
     * Generate permutations of size k (k-permutations)
     *
     * @param nums Input array of unique elements
     * @param k    Size of each permutation
     * @return All permutations of size k
     */
    public List<List<Integer>> permuteK(int[] nums, int k) {
        used = new boolean[nums.length];
        backtrackK(nums, k);
        return res;
    }

    /**
     * Helper function for k-permutations
     */
    private void backtrackK(int[] nums, int k) {
        // Base case: reached the kth level
        if (track.size() == k) {
            res.add(new LinkedList<>(track));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (used[i]) {
                continue;
            }
            used[i] = true;
            track.addLast(nums[i]);
            backtrackK(nums, k);
            track.removeLast();
            used[i] = false;
        }
    }
}
