package org.qinlinj.algoframework._700_brute_force_search_algo._740_dfs_backtracking_practice._742_backtracking_classic_practice_II;

import java.util.*;

/**
 * 996. Number of Squareful Arrays
 * <p>
 * Problem Summary:
 * A squareful array is an array where any two adjacent elements sum to a perfect square.
 * Given an array of integers nums, return the number of permutations of nums that are squareful.
 * <p>
 * Key Insights:
 * - This is a "permutation with elements that can repeat but not be reused" problem
 * - We need an additional constraint: adjacent elements must sum to a perfect square
 * - Sort the input first to handle duplicates efficiently
 * - Use backtracking to try all valid permutations
 * - Need a helper function to check if a number is a perfect square
 * <p>
 * Time Complexity: O(n!) where n is the length of the input array
 * Space Complexity: O(n) for recursion stack and tracking used elements
 */
class _742_e_NumberOfSquarefulArrays {

    LinkedList<Integer> track = new LinkedList<>();
    boolean[] used;
    int count = 0;

    public int numSquarefulPerms(int[] nums) {
        Arrays.sort(nums);
        used = new boolean[nums.length];
        backtrack(nums);
        return count;
    }

    void backtrack(int[] nums) {
        if (track.size() == nums.length) {
            // Found a valid permutation, increment counter
            count++;
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            // Skip if this element is already used
            if (used[i]) {
                continue;
            }

            // Skip duplicate elements to avoid duplicate permutations
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
                continue;
            }

            // Additional constraint: adjacent elements must sum to a perfect square
            if (track.size() > 0 && !isSquareful(track.getLast(), nums[i])) {
                continue;
            }

            // Make choice
            track.add(nums[i]);
            used[i] = true;

            // Explore next choices
            backtrack(nums);

            // Undo choice
            track.removeLast();
            used[i] = false;
        }
    }

    // Helper function to check if a + b is a perfect square
    boolean isSquareful(int a, int b) {
        int sum = a + b;
        int sqrt = (int) Math.sqrt(sum);
        return sqrt * sqrt == sum;
    }
}