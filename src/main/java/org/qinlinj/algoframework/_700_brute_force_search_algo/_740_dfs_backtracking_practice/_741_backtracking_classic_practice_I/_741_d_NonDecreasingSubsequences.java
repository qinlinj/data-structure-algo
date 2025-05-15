package org.qinlinj.algoframework._700_brute_force_search_algo._740_dfs_backtracking_practice._741_backtracking_classic_practice_I;

import java.util.*;

/**
 * 491. Non-decreasing Subsequences
 * <p>
 * Problem Summary:
 * Find all different non-decreasing subsequences of an array of integers.
 * A subsequence with at least two elements is required.
 * The array may contain duplicates, and two equal values can be treated as a non-decreasing sequence.
 * <p>
 * Key Insights:
 * - This is a "elements can be repeated but not reused" combination problem
 * - We use a backtracking approach to build all valid subsequences
 * - Two key constraints:
 * 1. Elements in the subsequence must be in non-decreasing order
 * 2. We need to avoid duplicate subsequences
 * - Unlike typical combination problems, we can't sort the array as we need to preserve the original order
 * - Instead, we use a HashSet at each level to prevent using duplicate elements
 * <p>
 * Time Complexity: O(n * 2^n) in worst case
 * Space Complexity: O(n) for recursion stack and tracking the current path
 */
class _741_d_NonDecreasingSubsequences {

    List<List<Integer>> res = new LinkedList<>();
    // Track the backtracking path
    LinkedList<Integer> track = new LinkedList<>();

    public List<List<Integer>> findSubsequences(int[] nums) {
        if (nums.length == 0) {
            return res;
        }
        backtrack(nums, 0);
        return res;
    }

    // Backtracking main function
    void backtrack(int[] nums, int start) {
        if (track.size() >= 2) {
            // Found a valid answer (subsequence with at least 2 elements)
            res.add(new LinkedList<>(track));
        }

        // Use HashSet to prevent selecting duplicate elements at the same level
        HashSet<Integer> used = new HashSet<>();

        // Standard backtracking framework
        for (int i = start; i < nums.length; i++) {
            // Ensure elements in the subsequence are in non-decreasing order
            if (!track.isEmpty() && track.getLast() > nums[i]) {
                continue;
            }

            // Prevent selecting duplicate elements at the same level
            if (used.contains(nums[i])) {
                continue;
            }

            // Make choice
            used.add(nums[i]);
            track.add(nums[i]);

            // Recursively build the subsequence
            backtrack(nums, i + 1);

            // Undo choice
            track.removeLast();
        }
    }
}