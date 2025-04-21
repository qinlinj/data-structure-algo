package org.qinlinj.algoframework._100_core_framework._190_backtracking_algo_framework._193_permutations_unique_elements_no_reuse;

import java.util.*;

/**
 * SUMMARY OF PERMUTATION GENERATION USING BACKTRACKING
 * <p>
 * This class demonstrates how to generate all possible permutations of a given array
 * using the backtracking algorithm technique.
 * <p>
 * Key Concepts:
 * 1. Permutation Problem (LeetCode 46): Given an array of distinct integers, return all possible
 * permutations (rearrangements of the elements).
 * <p>
 * 2. Difference from Subset/Combination Problems:
 * - Subset/Combination: Elements maintain relative order (only consider elements after current position)
 * - Permutation: Elements can be rearranged in any order (can use any unused element at any position)
 * - We can't use the "start" parameter to prevent duplicates as in subset generation
 * <p>
 * 3. Backtracking Approach:
 * - Uses a recursive tree traversal strategy
 * - Each path from root to leaf represents a valid permutation
 * - Need a "used" array to track which elements have already been selected in the current path
 * - Collect all leaf nodes (when path length equals array length)
 * <p>
 * 4. Implementation Strategy:
 * - Use a "track" list to record the current path during recursion
 * - Use a "used" boolean array to mark which elements are already in the current path
 * - For each position, consider all unused elements as candidates
 * - Add complete permutations to result when track size equals input array size
 * - Follow the standard backtracking pattern: choose → explore → unchoose
 * <p>
 * 5. Variations:
 * - Can be modified to generate k-length permutations by changing the base case
 * <p>
 * Time Complexity: O(n * n!) where n is the length of the array
 * Space Complexity: O(n) for recursion stack and tracking used elements
 */
public class PermutationGenerator {
    /**
     * Example usage of the permutation generation algorithm.
     */
    public static void main(String[] args) {
        PermutationGenerator generator = new PermutationGenerator();
        int[] nums = {1, 2, 3};

        // Generate all permutations
        List<List<Integer>> allPermutations = generator.permute(nums);

        System.out.println("All permutations of " + java.util.Arrays.toString(nums) + ":");
        for (List<Integer> permutation : allPermutations) {
            System.out.println(permutation);
        }

        // Expected output for [1,2,3]:
        // [1, 2, 3]
        // [1, 3, 2]
        // [2, 1, 3]
        // [2, 3, 1]
        // [3, 1, 2]
        // [3, 2, 1]

        // Generate k-length permutations
        int k = 2;
        List<List<Integer>> kPermutations = generator.permuteK(nums, k);

        System.out.println("\nAll " + k + "-length permutations of " + java.util.Arrays.toString(nums) + ":");
        for (List<Integer> permutation : kPermutations) {
            System.out.println(permutation);
        }

        // Expected output for k=2:
        // [1, 2]
        // [1, 3]
        // [2, 1]
        // [2, 3]
        // [3, 1]
        // [3, 2]
    }

    /**
     * Generates all possible permutations of the given array.
     *
     * @param nums An array of distinct integers
     * @return A list containing all possible permutations
     */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new LinkedList<>();
        // List to track the current permutation being built
        LinkedList<Integer> track = new LinkedList<>();
        // Boolean array to track which elements have been used
        boolean[] used = new boolean[nums.length];

        backtrack(nums, used, track, result);
        return result;
    }

    /**
     * Backtracking function to explore the tree of all possible permutations.
     *
     * @param nums   The input array of distinct integers
     * @param used   Boolean array marking which elements are already used in current path
     * @param track  The current permutation being built
     * @param result The list to store all found permutations
     */
    private void backtrack(int[] nums, boolean[] used, LinkedList<Integer> track, List<List<Integer>> result) {
        // Base case: if track size equals the array length, we have a complete permutation
        if (track.size() == nums.length) {
            // Add a copy of the current permutation to the result
            result.add(new LinkedList<>(track));
            return;
        }

        // Standard backtracking framework
        for (int i = 0; i < nums.length; i++) {
            // Skip elements that are already used in the current path
            if (used[i]) {
                continue;
            }

            // Make a choice: add current element to the permutation
            used[i] = true;
            track.addLast(nums[i]);

            // Explore further: recursive call
            backtrack(nums, used, track, result);

            // Undo the choice: remove the last added element
            track.removeLast();
            used[i] = false;
        }
    }

    /**
     * Generates all k-length permutations of the given array.
     *
     * @param nums An array of distinct integers
     * @param k    The desired length of each permutation
     * @return A list containing all possible k-length permutations
     */
    public List<List<Integer>> permuteK(int[] nums, int k) {
        List<List<Integer>> result = new LinkedList<>();
        LinkedList<Integer> track = new LinkedList<>();
        boolean[] used = new boolean[nums.length];

        backtrackK(nums, k, used, track, result);
        return result;
    }

    /**
     * Backtracking function to generate k-length permutations.
     */
    private void backtrackK(int[] nums, int k, boolean[] used, LinkedList<Integer> track, List<List<Integer>> result) {
        // Base case: if track size equals k, we have a k-length permutation
        if (track.size() == k) {
            result.add(new LinkedList<>(track));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (used[i]) {
                continue;
            }

            used[i] = true;
            track.addLast(nums[i]);

            backtrackK(nums, k, used, track, result);

            track.removeLast();
            used[i] = false;
        }
    }
}
