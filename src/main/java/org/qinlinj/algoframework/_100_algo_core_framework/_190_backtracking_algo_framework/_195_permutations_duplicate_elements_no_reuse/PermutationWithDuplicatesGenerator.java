package org.qinlinj.algoframework._100_algo_core_framework._190_backtracking_algo_framework._195_permutations_duplicate_elements_no_reuse;

import java.util.*;

/**
 * SUMMARY OF PERMUTATION GENERATION WITH DUPLICATES USING BACKTRACKING
 * <p>
 * This class demonstrates how to generate all unique permutations of an array
 * that may contain duplicate elements using the backtracking algorithm.
 * <p>
 * Key Concepts:
 * 1. Permutation II Problem (LeetCode 47): Given an array of integers that might contain
 * duplicates, return all possible unique permutations.
 * <p>
 * 2. Handling Duplicates in Permutations:
 * - First sort the array to group duplicate elements together
 * - Use a 'used' array to track which elements have been used in the current path
 * - Add pruning logic to maintain a consistent relative order among duplicate elements
 * <p>
 * 3. Pruning Strategy:
 * - Skip when current element equals the previous element AND the previous element is not used
 * - This ensures we only use duplicate elements in a specific order
 * - Example: for [1,2,2',2''] we ensure 2 is used before 2', and 2' is used before 2''
 * <p>
 * 4. Alternative Pruning Strategies:
 * - Method 1: Using used[i-1] instead of !used[i-1] works but is less efficient
 * (it prunes fewer branches)
 * - Method 2: Using prevNum to avoid processing the same value at the same level
 * <p>
 * Time Complexity: O(n * n!) where n is the length of the array
 * Space Complexity: O(n) for recursion stack, tracking used elements, and current permutation
 */
public class PermutationWithDuplicatesGenerator {
    /**
     * Example usage of both permutation generation approaches.
     */
    public static void main(String[] args) {
        PermutationWithDuplicatesGenerator generator = new PermutationWithDuplicatesGenerator();

        int[] nums = {1, 2, 2};

        // Method 1: Using !used[i-1] pruning
        List<List<Integer>> uniquePermutations = generator.permuteUnique(nums);

        System.out.println("All unique permutations of " + Arrays.toString(nums) + " (Method 1):");
        for (List<Integer> perm : uniquePermutations) {
            System.out.println(perm);
        }

        // Method 2: Using prevNum approach
        List<List<Integer>> uniquePermutationsAlt = generator.permuteUniqueAlt(nums);

        System.out.println("\nAll unique permutations of " + Arrays.toString(nums) + " (Method 2):");
        for (List<Integer> perm : uniquePermutationsAlt) {
            System.out.println(perm);
        }

        // Expected output for both methods:
        // [1, 2, 2]
        // [2, 1, 2]
        // [2, 2, 1]
    }

    /**
     * Generates all possible unique permutations of the given array with duplicates.
     *
     * @param nums An array of integers, possibly containing duplicates
     * @return A list containing all possible unique permutations
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new LinkedList<>();
        LinkedList<Integer> track = new LinkedList<>();
        boolean[] used = new boolean[nums.length];

        // Sort the array to group duplicates together - critical step!
        Arrays.sort(nums);

        backtrack(nums, used, track, result);
        return result;
    }

    /**
     * Backtracking function to explore the tree of all possible permutations.
     *
     * @param nums   The input array of integers (sorted)
     * @param used   Boolean array marking which elements are already used in current path
     * @param track  The current permutation being built
     * @param result The list to store all found permutations
     */
    private void backtrack(int[] nums, boolean[] used, LinkedList<Integer> track, List<List<Integer>> result) {
        // Base case: if track size equals the array length, we have a complete permutation
        if (track.size() == nums.length) {
            result.add(new LinkedList<>(track));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            // Skip if the element is already used in current permutation
            if (used[i]) {
                continue;
            }

            // Skip if current element is a duplicate and its previous duplicate hasn't been used
            // This ensures we maintain fixed relative ordering of duplicate elements
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
                continue;
            }

            // Make a choice
            used[i] = true;
            track.addLast(nums[i]);

            // Explore further
            backtrack(nums, used, track, result);

            // Undo the choice
            track.removeLast();
            used[i] = false;
        }
    }

    /**
     * Alternative pruning approach using prevNum to avoid duplicates.
     */
    public List<List<Integer>> permuteUniqueAlt(int[] nums) {
        List<List<Integer>> result = new LinkedList<>();
        LinkedList<Integer> track = new LinkedList<>();
        boolean[] used = new boolean[nums.length];

        // Sort the array to group duplicates together
        Arrays.sort(nums);

        backtrackAlt(nums, used, track, result);
        return result;
    }

    private void backtrackAlt(int[] nums, boolean[] used, LinkedList<Integer> track, List<List<Integer>> result) {
        if (track.size() == nums.length) {
            result.add(new LinkedList<>(track));
            return;
        }

        // Use prevNum to track the previous value at this level
        // Initialize with a value outside the possible range (-10 to 10 per problem constraints)
        int prevNum = -666;

        for (int i = 0; i < nums.length; i++) {
            if (used[i]) {
                continue;
            }

            // Skip if this value equals the previous value at this level
            if (nums[i] == prevNum) {
                continue;
            }

            // Update prevNum for this level
            prevNum = nums[i];

            // Make a choice
            used[i] = true;
            track.addLast(nums[i]);

            // Explore further
            backtrackAlt(nums, used, track, result);

            // Undo the choice
            track.removeLast();
            used[i] = false;
        }
    }

    /**
     * Demonstrates the difference between using !used[i-1] vs used[i-1] in pruning.
     */
    public List<List<Integer>> permuteUniqueAlternativePruning(int[] nums) {
        List<List<Integer>> result = new LinkedList<>();
        LinkedList<Integer> track = new LinkedList<>();
        boolean[] used = new boolean[nums.length];

        Arrays.sort(nums);

        backtrackAltPruning(nums, used, track, result);
        return result;
    }

    private void backtrackAltPruning(int[] nums, boolean[] used, LinkedList<Integer> track, List<List<Integer>> result) {
        if (track.size() == nums.length) {
            result.add(new LinkedList<>(track));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (used[i]) {
                continue;
            }

            // Alternative pruning logic using used[i-1] instead of !used[i-1]
            // This works but is less efficient as it prunes fewer branches
            if (i > 0 && nums[i] == nums[i - 1] && used[i - 1]) {
                continue;
            }

            used[i] = true;
            track.addLast(nums[i]);

            backtrackAltPruning(nums, used, track, result);

            track.removeLast();
            used[i] = false;
        }
    }
}
