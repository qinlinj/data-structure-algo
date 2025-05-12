package org.qinlinj.algoframework._700_brute_force_search_algo._720_dfs_backtracking_applications._721_permutation_combination_subset;

import java.util.*;

/**
 * SUBSETS WITH DUPLICATES (NO REUSE)
 * <p>
 * This class implements the solution for generating all subsets from an array
 * that may contain duplicate elements, where each element can be used at most once.
 * <p>
 * Key points:
 * - We must handle duplicate elements to avoid generating duplicate subsets
 * - First sort the array to bring duplicate elements together
 * - Use pruning logic to skip over duplicate elements at the same level of the backtracking tree
 * - Only process the first occurrence of duplicate elements (i > start && nums[i] == nums[i-1])
 * - Time complexity: O(2^n) where n is the length of the input array
 * - Space complexity: O(n) for the recursion stack
 * <p>
 * LeetCode Problem: 90. Subsets II
 * Problem description: Given an integer array nums that may contain duplicates, return all possible subsets.
 */

public class _721_d_SubsetsWithDuplicates {

    private List<List<Integer>> res = new LinkedList<>();
    // Track the backtracking path
    private LinkedList<Integer> track = new LinkedList<>();

    // Example usage
    public static void main(String[] args) {
        _721_d_SubsetsWithDuplicates solution = new _721_d_SubsetsWithDuplicates();
        int[] nums = {1, 2, 2};
        List<List<Integer>> result = solution.subsetsWithDup(nums);

        System.out.println("All unique subsets of [1,2,2]:");
        for (List<Integer> subset : result) {
            System.out.println(subset);
        }
    }

    /**
     * Main function to generate all subsets with possible duplicates
     *
     * @param nums An array that may contain duplicate integers
     * @return All possible unique subsets
     */
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        // First sort the array to group duplicates together
        Arrays.sort(nums);
        backtrack(nums, 0);
        return res;
    }

    /**
     * Core backtracking function to explore the subset tree
     *
     * @param nums  Input array
     * @param start Starting position to avoid duplicates
     */
    private void backtrack(int[] nums, int start) {
        // Pre-order position: add the current subset to result
        res.add(new LinkedList<>(track));

        // Standard backtracking framework
        for (int i = start; i < nums.length; i++) {
            // Pruning logic: skip duplicate elements at the same level
            if (i > start && nums[i] == nums[i - 1]) {
                continue;
            }
            // Make a choice
            track.addLast(nums[i]);
            // Explore next level
            backtrack(nums, i + 1);
            // Undo the choice
            track.removeLast();
        }
    }
}
