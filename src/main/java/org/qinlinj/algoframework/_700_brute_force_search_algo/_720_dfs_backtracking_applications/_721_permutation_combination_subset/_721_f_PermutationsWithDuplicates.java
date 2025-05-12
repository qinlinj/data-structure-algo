package org.qinlinj.algoframework._700_brute_force_search_algo._720_dfs_backtracking_applications._721_permutation_combination_subset;

import java.util.*;

/**
 * PERMUTATIONS WITH DUPLICATES (NO REUSE)
 * <p>
 * This class implements the solution for generating all unique permutations from an array
 * that may contain duplicate elements, where each element can be used at most once.
 * <p>
 * Key points:
 * - Must handle duplicate elements to avoid generating duplicate permutations
 * - First sort the array to bring duplicate elements together
 * - Use both a 'used' array and a special pruning logic to avoid duplicates
 * - The critical pruning logic (i > 0 && nums[i] == nums[i-1] && !used[i-1]) ensures
 * that duplicate elements maintain their relative order in the permutation
 * - Time complexity: O(n!) where n is the length of the input array
 * - Space complexity: O(n) for the recursion stack and 'used' array
 * <p>
 * LeetCode Problem: 47. Permutations II
 * Problem description: Given a collection of numbers, nums, that might contain duplicates,
 * return all possible unique permutations.
 */

public class _721_f_PermutationsWithDuplicates {

    private List<List<Integer>> res = new LinkedList<>();
    // Track the backtracking path
    private LinkedList<Integer> track = new LinkedList<>();
    // Track used elements
    private boolean[] used;

    // Example usage
    public static void main(String[] args) {
        _721_f_PermutationsWithDuplicates solution = new _721_f_PermutationsWithDuplicates();
        int[] nums = {1, 1, 2};
        List<List<Integer>> result = solution.permuteUnique(nums);

        System.out.println("All unique permutations of [1,1,2]:");
        for (List<Integer> perm : result) {
            System.out.println(perm);
        }

        // Demonstrate alternative approach
        solution = new _721_f_PermutationsWithDuplicates();
        List<List<Integer>> altResult = solution.permuteUniqueAlt(nums);

        System.out.println("\nAll unique permutations using alternative approach:");
        for (List<Integer> perm : altResult) {
            System.out.println(perm);
        }
    }

    /**
     * Main function to generate all unique permutations
     *
     * @param nums An array that may contain duplicate integers
     * @return All possible unique permutations
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
        // Sort the array to group duplicates together
        Arrays.sort(nums);
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
            // Critical pruning logic: fix the relative order of duplicate elements
            // Only use a duplicate element if its previous duplicate is already used
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
                continue;
            }
            // Make a choice
            track.add(nums[i]);
            used[i] = true;
            // Explore next level
            backtrack(nums);
            // Undo the choice
            track.removeLast();
            used[i] = false;
        }
    }

    /**
     * Alternative approach using prevNum to avoid duplicates
     *
     * @param nums An array that may contain duplicate integers
     * @return All possible unique permutations
     */
    public List<List<Integer>> permuteUniqueAlt(int[] nums) {
        Arrays.sort(nums);
        used = new boolean[nums.length];
        backtrackAlt(nums);
        return res;
    }

    private void backtrackAlt(int[] nums) {
        if (track.size() == nums.length) {
            res.add(new LinkedList<>(track));
            return;
        }

        // Use special value to track previous element
        int prevNum = Integer.MIN_VALUE;

        for (int i = 0; i < nums.length; i++) {
            if (used[i]) {
                continue;
            }
            // Skip duplicate elements at the same level
            if (nums[i] == prevNum) {
                continue;
            }

            track.add(nums[i]);
            used[i] = true;
            // Record this branch's value
            prevNum = nums[i];

            backtrackAlt(nums);

            track.removeLast();
            used[i] = false;
        }
    }
}
