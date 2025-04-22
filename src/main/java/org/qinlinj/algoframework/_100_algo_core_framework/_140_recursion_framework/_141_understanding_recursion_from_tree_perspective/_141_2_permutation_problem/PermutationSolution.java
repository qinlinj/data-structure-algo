package org.qinlinj.algoframework._100_algo_core_framework._140_recursion_framework._141_understanding_recursion_from_tree_perspective._141_2_permutation_problem;

import java.util.*;


/**
 * PERMUTATION PROBLEM SUMMARY
 * <p>
 * Key Concepts:
 * 1. Permutation: An arrangement of all elements in a set where order matters
 * 2. For n distinct elements, there are n! possible permutations
 * 3. Backtracking Algorithm: A systematic way to enumerate all possibilities
 * <p>
 * Backtracking Approach for Permutations:
 * - Uses a decision tree structure where each level represents a position in the array
 * - At each level, try all possible elements that haven't been used yet
 * - Uses a "track" to record the current path being explored
 * - Uses a "used" array to mark elements that have already been placed
 * - When the track length equals the input array length, we've found a valid permutation
 * <p>
 * Algorithm Structure:
 * 1. Define a result list to store all permutations
 * 2. Define a backtracking function that builds permutations one element at a time
 * 3. In the backtracking function:
 * - If the current permutation is complete, add it to the result
 * - For each element in the input array:
 * - If the element is already used, skip it
 * - Otherwise, add it to the current permutation
 * - Mark it as used
 * - Recursively call the backtracking function
 * - Remove the element (backtrack)
 * - Mark it as unused
 * <p>
 * Time Complexity: O(n!), where n is the number of elements
 * Space Complexity: O(n) for the recursion stack and temporary storage
 */
public class PermutationSolution {
    private List<List<Integer>> result = new LinkedList<>();

    /**
     * Example usage of the permutation algorithm
     */
    public static void main(String[] args) {
        PermutationSolution solution = new PermutationSolution();

        // Example from the problem: [1,2,3]
        int[] nums = {1, 2, 3};
        List<List<Integer>> permutations = solution.permute(nums);

        // Print all permutations
        System.out.println("All permutations of [1,2,3]:");
        for (List<Integer> perm : permutations) {
            System.out.println(perm);
        }

        // The output should be:
        // [1, 2, 3]
        // [1, 3, 2]
        // [2, 1, 3]
        // [2, 3, 1]
        // [3, 1, 2]
        // [3, 2, 1]
    }

    /**
     * Main function to find all permutations of the given array of distinct integers.
     *
     * @param nums An array of distinct integers
     * @return All possible permutations
     */
    public List<List<Integer>> permute(int[] nums) {
        // Track the current permutation being built
        LinkedList<Integer> track = new LinkedList<>();

        // Keep track of which elements have been used
        boolean[] used = new boolean[nums.length];

        // Start the backtracking process
        backtrack(nums, track, used);

        return result;
    }

    /**
     * Backtracking function to build permutations.
     *
     * @param nums  The input array of distinct integers
     * @param track The current permutation being built
     * @param used  Boolean array tracking which elements have been used
     */
    private void backtrack(int[] nums, LinkedList<Integer> track, boolean[] used) {
        // Base case: if the track is the same length as nums, we have a complete permutation
        if (track.size() == nums.length) {
            // Add a copy of the current permutation to our result
            result.add(new LinkedList<>(track));
            return;
        }

        // Try each number as the next element in our permutation
        for (int i = 0; i < nums.length; i++) {
            // Skip elements that have already been used
            if (used[i]) {
                continue;
            }

            // Make a choice - add the current number to our permutation
            track.add(nums[i]);
            used[i] = true;

            // Explore further with this choice
            backtrack(nums, track, used);

            // Undo the choice (backtrack) to try other possibilities
            track.removeLast();
            used[i] = false;
        }
    }
}
