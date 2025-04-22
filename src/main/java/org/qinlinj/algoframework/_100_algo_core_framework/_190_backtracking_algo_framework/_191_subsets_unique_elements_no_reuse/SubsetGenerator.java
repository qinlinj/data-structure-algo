package org.qinlinj.algoframework._100_algo_core_framework._190_backtracking_algo_framework._191_subsets_unique_elements_no_reuse;

import java.util.*;

/**
 * SUMMARY OF SUBSET GENERATION USING BACKTRACKING
 * <p>
 * This class demonstrates how to generate all subsets (power set) of a given array
 * using the backtracking algorithm technique.
 * <p>
 * Key Concepts:
 * 1. Subset Problem (LeetCode 78): Given an array of unique elements, return all possible subsets.
 * <p>
 * 2. Backtracking Approach:
 * - Uses a recursive tree traversal strategy
 * - Each node in the tree represents a subset
 * - The depth of the tree corresponds to the size of the subset
 * - Tree structure prevents duplicate subsets by maintaining relative order
 * <p>
 * 3. Variations of Combination/Subset Problems:
 * - Form 1: Elements unique, no reuse allowed (basic form shown here)
 * - Form 2: Elements may have duplicates, no reuse allowed
 * - Form 3: Elements unique, reuse allowed
 * <p>
 * 4. Implementation Strategy:
 * - Use a "track" list to record the current path during recursion
 * - Use a "start" parameter to control which elements can be chosen next
 * - Add each valid state to the result set (preorder position)
 * - Follow the standard backtracking pattern: choose → explore → unchoose
 * <p>
 * Time Complexity: O(n * 2^n) where n is the length of the array
 * Space Complexity: O(n) for recursion stack, O(2^n) for storing all subsets
 */
public class SubsetGenerator {
    /**
     * Example usage of the subset generation algorithm.
     */
    public static void main(String[] args) {
        SubsetGenerator generator = new SubsetGenerator();
        int[] nums = {1, 2, 3};

        List<List<Integer>> allSubsets = generator.subsets(nums);

        System.out.println("All subsets of " + java.util.Arrays.toString(nums) + ":");
        for (List<Integer> subset : allSubsets) {
            System.out.println(subset);
        }

        // Expected output for [1,2,3]:
        // []
        // [1]
        // [1, 2]
        // [1, 2, 3]
        // [1, 3]
        // [2]
        // [2, 3]
        // [3]
    }

    /**
     * Generates all possible subsets of the given array.
     *
     * @param nums An array of unique integers
     * @return A list containing all possible subsets
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new LinkedList<>();
        // List to track the current subset being built
        LinkedList<Integer> track = new LinkedList<>();

        // Start backtracking from index 0
        backtrack(nums, 0, track, result);
        return result;
    }

    /**
     * Backtracking function to explore the tree of all possible subsets.
     *
     * @param nums   The input array of unique integers
     * @param start  The starting index to consider for this recursive call
     * @param track  The current subset being built
     * @param result The list to store all found subsets
     */
    private void backtrack(int[] nums, int start, LinkedList<Integer> track, List<List<Integer>> result) {
        // Preorder position: add current subset to the result
        // Every node in the tree represents a valid subset
        result.add(new LinkedList<>(track));

        // Standard backtracking framework
        for (int i = start; i < nums.length; i++) {
            // Make a choice: add current element to the subset
            track.addLast(nums[i]);

            // Explore further: recursive call with increased start index
            // This ensures we only consider elements after the current one
            // which prevents duplicates and maintains order
            backtrack(nums, i + 1, track, result);

            // Undo the choice: remove the last added element
            track.removeLast();
        }

        // Note: No explicit base case is needed as the for loop will not
        // execute when start >= nums.length, naturally ending the recursion
    }
}
