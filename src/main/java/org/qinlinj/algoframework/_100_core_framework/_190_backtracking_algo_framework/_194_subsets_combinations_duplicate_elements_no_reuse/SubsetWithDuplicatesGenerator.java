package org.qinlinj.algoframework._100_core_framework._190_backtracking_algo_framework._194_subsets_combinations_duplicate_elements_no_reuse;

import java.util.*;

/**
 * SUMMARY OF SUBSET GENERATION WITH DUPLICATES USING BACKTRACKING
 * <p>
 * This class demonstrates how to generate all possible subsets of an array
 * that may contain duplicate elements using the backtracking algorithm.
 * <p>
 * Key Concepts:
 * 1. Subset II Problem (LeetCode 90): Given an array of integers that might contain
 * duplicates, return all possible subsets (the power set).
 * <p>
 * 2. Handling Duplicates:
 * - First sort the array to group duplicate elements together
 * - When building the decision tree, skip adjacent duplicate values at the same level
 * - Only take the first occurrence of duplicate elements at each level
 * <p>
 * 3. Pruning Strategy:
 * - Check if current element is the same as previous element (nums[i] == nums[i-1])
 * - If it is, and we're not at the start of the current level (i > start), skip it
 * - This prevents generating duplicate subsets while still allowing duplicates in different positions
 * <p>
 * 4. Extended Application - Combination Sum II (LeetCode 40):
 * - Find all combinations that sum to a target value
 * - Each number can only be used once
 * - Same core algorithm but with additional tracking of sum and modified base case
 * <p>
 * Time Complexity: O(n * 2^n) where n is the length of the array
 * Space Complexity: O(n) for recursion stack, O(2^n) for storing all subsets
 */
public class SubsetWithDuplicatesGenerator {
    /**
     * Example usage of the subset and combination sum algorithms with duplicates.
     */
    public static void main(String[] args) {
        SubsetWithDuplicatesGenerator generator = new SubsetWithDuplicatesGenerator();

        // Example 1: Subsets with duplicates
        int[] nums1 = {1, 2, 2};
        List<List<Integer>> allSubsets = generator.subsetsWithDup(nums1);

        System.out.println("All unique subsets of " + Arrays.toString(nums1) + ":");
        for (List<Integer> subset : allSubsets) {
            System.out.println(subset);
        }
        // Expected output:
        // []
        // [1]
        // [1, 2]
        // [1, 2, 2]
        // [2]
        // [2, 2]

        // Example 2: Combination Sum II
        int[] nums2 = {10, 1, 2, 7, 6, 1, 5};
        int target = 8;
        List<List<Integer>> combinations = generator.combinationSum2(nums2, target);

        System.out.println("\nAll combinations from " + Arrays.toString(nums2) +
                " that sum to " + target + ":");
        for (List<Integer> combination : combinations) {
            System.out.println(combination);
        }
        // Expected output:
        // [1, 1, 6]
        // [1, 2, 5]
        // [1, 7]
        // [2, 6]
    }

    /**
     * Generates all possible subsets of the given array with possible duplicates.
     *
     * @param nums An array of integers, possibly containing duplicates
     * @return A list containing all possible unique subsets
     */
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> result = new LinkedList<>();
        LinkedList<Integer> track = new LinkedList<>();

        // Sort the array to group duplicates together - critical step!
        Arrays.sort(nums);

        backtrack(nums, 0, track, result);
        return result;
    }

    /**
     * Backtracking function to explore the tree of all possible subsets.
     *
     * @param nums   The input array of integers (sorted)
     * @param start  The starting index to consider for this recursive call
     * @param track  The current subset being built
     * @param result The list to store all found subsets
     */
    private void backtrack(int[] nums, int start, LinkedList<Integer> track, List<List<Integer>> result) {
        // Add current subset to the result (pre-order position)
        result.add(new LinkedList<>(track));

        for (int i = start; i < nums.length; i++) {
            // Skip duplicate elements at the same level (pruning)
            // i > start ensures we only skip duplicates at the same level, not across levels
            if (i > start && nums[i] == nums[i - 1]) {
                continue;
            }

            // Make a choice
            track.addLast(nums[i]);

            // Explore further (next level in the tree)
            backtrack(nums, i + 1, track, result);

            // Undo the choice
            track.removeLast();
        }
    }

    /**
     * Finds all combinations of numbers that sum to target, with each number used at most once.
     *
     * @param candidates Array of integers to choose from (may contain duplicates)
     * @param target     The target sum to achieve
     * @return All unique combinations that sum to target
     */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new LinkedList<>();
        LinkedList<Integer> track = new LinkedList<>();

        // Sort to group duplicates and enable pruning
        Arrays.sort(candidates);

        combinationBacktrack(candidates, 0, target, 0, track, result);
        return result;
    }

    /**
     * Backtracking function for finding combinations that sum to target.
     *
     * @param nums       The input array of integers (sorted)
     * @param start      The starting index to consider
     * @param target     The target sum to achieve
     * @param currentSum The current sum of elements in the track
     * @param track      The current combination being built
     * @param result     The list to store all valid combinations
     */
    private void combinationBacktrack(int[] nums, int start, int target, int currentSum,
                                      LinkedList<Integer> track, List<List<Integer>> result) {
        // Base case: found a valid combination
        if (currentSum == target) {
            result.add(new LinkedList<>(track));
            return;
        }

        // Base case: exceeded target sum, no need to explore further
        if (currentSum > target) {
            return;
        }

        for (int i = start; i < nums.length; i++) {
            // Skip duplicates at the same level
            if (i > start && nums[i] == nums[i - 1]) {
                continue;
            }

            // Early termination: if current element would exceed target, stop
            // This works because the array is sorted
            if (currentSum + nums[i] > target) {
                break;
            }

            // Make a choice
            track.addLast(nums[i]);

            // Explore further with updated sum
            combinationBacktrack(nums, i + 1, target, currentSum + nums[i], track, result);

            // Undo the choice
            track.removeLast();
        }
    }
}
