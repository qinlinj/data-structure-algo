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
}
