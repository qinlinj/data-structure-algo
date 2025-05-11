package org.qinlinj.algoframework._700_brute_force_search_algo._710_dfs_backtracking_framework._711_backtracking_framework;

import java.util.*;

/**
 * PERMUTATION PROBLEM USING BACKTRACKING
 * ======================================
 * <p>
 * Problem: Given an array of distinct integers, return all possible permutations.
 * Example: Input: [1,2,3] → Output: [[1,2,3], [1,3,2], [2,1,3], [2,3,1], [3,1,2], [3,2,1]]
 * <p>
 * Decision Tree Analysis:
 * - Each node represents a state in building a permutation
 * - Branches represent choices of which number to place next
 * - The path from root to current node represents numbers already chosen
 * - Available choices are numbers not yet chosen
 * - Leaf nodes (at depth n) represent complete permutations
 * <p>
 * Backtracking Implementation:
 * 1. Use a "track" list to record the current path (numbers selected so far)
 * 2. Use a "used" array to mark which numbers have been used
 * 3. When track.size() equals nums.length, we have a complete permutation
 * 4. For each recursive call, try all unused numbers
 * 5. After exploring each choice, backtrack (undo the choice) to try others
 * <p>
 * Time Complexity: O(n!), where n is the length of the input array
 * - Cannot be optimized further as we must enumerate all n! permutations
 * - This is characteristic of backtracking algorithms: pure brute force enumeration
 */
public class _711_b_PermutationExample {

    private List<List<Integer>> result = new LinkedList<>();

    /**
     * Example usage
     */
    public static void main(String[] args) {
        _711_b_PermutationExample solution = new _711_b_PermutationExample();
        int[] nums = {1, 2, 3};

        List<List<Integer>> permutations = solution.permute(nums);

        System.out.println("All permutations of [1,2,3]:");
        for (List<Integer> perm : permutations) {
            System.out.println(perm);
        }

        // Expected output:
        // [1, 2, 3]
        // [1, 3, 2]
        // [2, 1, 3]
        // [2, 3, 1]
        // [3, 1, 2]
        // [3, 2, 1]
    }

    /**
     * Main function to compute all permutations of a given array
     *
     * @param nums Array of distinct integers
     * @return List of all possible permutations
     */
    public List<List<Integer>> permute(int[] nums) {
        // Record the "path" (current permutation being built)
        LinkedList<Integer> track = new LinkedList<>();

        // Track which elements have been used to avoid duplicates
        boolean[] used = new boolean[nums.length];

        // Start backtracking
        backtrack(nums, track, used);

        return result;
    }

    /**
     * Backtracking function to build permutations
     *
     * @param nums  Input array of integers
     * @param track Current path (partial permutation)
     * @param used  Array marking which numbers have been used
     */
    private void backtrack(int[] nums, LinkedList<Integer> track, boolean[] used) {
        // End condition: if the path contains all numbers
        if (track.size() == nums.length) {
            // Add a copy of the current permutation to results
            result.add(new LinkedList<>(track));
            return;
        }

        // Try each number as the next element in the permutation
        for (int i = 0; i < nums.length; i++) {
            // Skip if this number is already used
            if (used[i]) {
                continue;
            }

            // 1. Make choice - add number to path and mark as used
            track.add(nums[i]);
            used[i] = true;

            // 2. Explore further with this choice
            backtrack(nums, track, used);

            // 3. Undo choice (backtrack)
            track.removeLast();
            used[i] = false;
        }
    }
}
