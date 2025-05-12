package org.qinlinj.algoframework._700_brute_force_search_algo._720_dfs_backtracking_applications._721_permutation_combination_subset;

import java.util.*;

/**
 * PERMUTATIONS WITH REUSE
 * <p>
 * This class implements the solution for generating all permutations from an array
 * of unique elements, where each element can be used multiple times.
 * <p>
 * Key points:
 * - Elements can be reused any number of times
 * - No 'used' array is needed since we allow elements to be reused
 * - For n unique elements, there are n^k possible permutations of length k
 * - Time complexity: O(n^k) where n is the array length and k is the permutation length
 * - Space complexity: O(k) for the recursion stack and result path
 * <p>
 * Note: While this specific problem doesn't appear directly on LeetCode,
 * understanding this pattern is important for solving variations of permutation problems.
 */

public class _721_h_PermutationsWithReuse {

    private List<List<Integer>> res = new LinkedList<>();
    // Track the backtracking path
    private LinkedList<Integer> track = new LinkedList<>();

    // Example usage
    public static void main(String[] args) {
        _721_h_PermutationsWithReuse solution = new _721_h_PermutationsWithReuse();
        int[] nums = {1, 2, 3};

        // Full permutations with reuse (will generate 3^3 = 27 permutations)
        // Only showing k=2 permutations for brevity
        int k = 2;
        List<List<Integer>> result = solution.permuteRepeatK(nums, k);

        System.out.println("All " + k + "-permutations of [1,2,3] with reuse:");
        for (List<Integer> perm : result) {
            System.out.println(perm);
        }
    }

    /**
     * Main function to generate all permutations with reuse
     *
     * @param nums An array of unique integers
     * @return All possible permutations with reuse
     */
    public List<List<Integer>> permuteRepeat(int[] nums) {
        backtrack(nums);
        return res;
    }

    /**
     * Core backtracking function to explore the permutation tree
     *
     * @param nums Input array
     */
    private void backtrack(int[] nums) {
        // Base case: reached desired length
        if (track.size() == nums.length) {
            res.add(new LinkedList<>(track));
            return;
        }

        // Standard backtracking framework
        for (int i = 0; i < nums.length; i++) {
            // Make a choice (no need to check if used)
            track.add(nums[i]);
            // Explore next level
            backtrack(nums);
            // Undo the choice
            track.removeLast();
        }
    }

    /**
     * Generate permutations of size k with element reuse
     *
     * @param nums Input array of unique elements
     * @param k    Size of each permutation
     * @return All permutations of size k with reuse
     */
    public List<List<Integer>> permuteRepeatK(int[] nums, int k) {
        backtrackK(nums, k);
        return res;
    }

    /**
     * Helper function for k-permutations with reuse
     */
    private void backtrackK(int[] nums, int k) {
        // Base case: reached the kth level
        if (track.size() == k) {
            res.add(new LinkedList<>(track));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            track.add(nums[i]);
            backtrackK(nums, k);
            track.removeLast();
        }
    }
}
