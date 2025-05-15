package org.qinlinj.algoframework._700_brute_force_search_algo._740_dfs_backtracking_practice._741_backtracking_classic_practice_I;

import java.util.*;

/**
 * 473. Matchsticks to Square
 * <p>
 * Problem Summary:
 * Given an array of matchsticks (represented by their lengths), determine if it's possible to use
 * all matchsticks to form a square. You cannot break any matchstick, but you can link them together.
 * Each matchstick must be used exactly once.
 * <p>
 * Key Insights:
 * - This problem reduces to the "Partition Array into K Equal Sum Subsets" problem where K=4
 * - We need to divide the matchsticks into 4 groups with equal sums
 * - A key optimization is using a bitmap to track used matchsticks
 * - We can use memoization to avoid redundant calculations
 * - The solution uses a recursive backtracking approach with pruning strategies
 * <p>
 * Time Complexity: O(N*2^N) with memoization, where N is the number of matchsticks
 * Space Complexity: O(2^N) for memoization hash map
 */
class _741_j_MatchsticksToSquare {

    HashMap<Integer, Boolean> memo = new HashMap<>();

    public boolean makesquare(int[] matchsticks) {
        return canPartitionKSubsets(matchsticks, 4);
    }

    // Implementation of "Partition Array into K Equal Sum Subsets" problem
    boolean canPartitionKSubsets(int[] nums, int k) {
        // Check basic cases
        if (k > nums.length) return false;
        int sum = 0;
        for (int v : nums) sum += v;
        if (sum % k != 0) return false;

        // Use bitmap technique for tracking used elements
        int used = 0;
        int target = sum / k;
        // Start with empty first bucket, and nums[0]
        return backtrack(k, 0, nums, 0, used, target);
    }

    boolean backtrack(int k, int bucket,
                      int[] nums, int start, int used, int target) {
        // Base case
        if (k == 0) {
            // All buckets are filled and nums are all used
            return true;
        }
        if (bucket == target) {
            // Current bucket is filled, move to next bucket
            // Start selection from nums[0] again
            boolean res = backtrack(k - 1, 0, nums, 0, used, target);
            // Cache result
            memo.put(used, res);
            return res;
        }

        if (memo.containsKey(used)) {
            // Avoid redundant calculations
            return memo.get(used);
        }

        for (int i = start; i < nums.length; i++) {
            // Pruning
            // Check if the i-th bit is 1 (already used)
            if (((used >> i) & 1) == 1) {
                // nums[i] is already in another bucket
                continue;
            }
            if (nums[i] + bucket > target) {
                continue;
            }

            // Make choice
            // Set the i-th bit to 1
            used |= 1 << i;
            bucket += nums[i];

            // Recursively try to add next element to current bucket
            if (backtrack(k, bucket, nums, i + 1, used, target)) {
                return true;
            }

            // Undo choice
            // Flip the i-th bit back to 0 using XOR
            used ^= 1 << i;
            bucket -= nums[i];
        }

        return false;
    }
}