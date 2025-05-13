package org.qinlinj.algoframework._700_brute_force_search_algo._730_dfs_backtracking_problems._734_set_partition;

import java.util.*;

public class _734_c_BucketsPerspective {
    /**
     * Determines if the array can be partitioned into k subsets with equal sum
     * using the buckets' perspective approach
     */
    public boolean canPartitionKSubsets(int[] nums, int k) {
        // Basic validation
        if (k > nums.length) return false;

        // Calculate the sum of all elements
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }

        // If the sum cannot be divided evenly by k, return false
        if (sum % k != 0) return false;

        // Calculate the target sum for each subset
        int target = sum / k;

        // Create a boolean array to track which elements are used
        boolean[] used = new boolean[nums.length];

        // Start the backtracking process with the first bucket
        return backtrack(k, 0, nums, 0, used, target);
    }


    /**
     * Backtracking method to fill buckets one by one
     *
     * @param k          Number of buckets remaining to be filled
     * @param currentSum Sum of elements in the current bucket so far
     * @param nums       Array of numbers
     * @param start      Index in nums to start considering elements from
     * @param used       Boolean array to track which elements are already used
     * @param target     Target sum for each bucket
     * @return true if a valid partition exists
     */
    private boolean backtrack(int k, int currentSum, int[] nums, int start, boolean[] used, int target) {
        // Base case: all buckets are filled
        if (k == 0) {
            return true;
        }

        // Current bucket is filled, move to the next bucket
        if (currentSum == target) {
            // Start from the beginning of nums for the next bucket
            return backtrack(k - 1, 0, nums, 0, used, target);
        }

        // Try to add nums[i] to the current bucket
        for (int i = start; i < nums.length; i++) {
            // Skip if the element is already used or would overflow the bucket
            if (used[i] || currentSum + nums[i] > target) {
                continue;
            }

            // Use the current element
            used[i] = true;
            currentSum += nums[i];

            // Recursively try to fill the rest of the current bucket
            if (backtrack(k, currentSum, nums, i + 1, used, target)) {
                return true;
            }

            // Backtrack: remove the element from the current bucket
            used[i] = false;
            currentSum -= nums[i];
        }

        // Cannot fill the current bucket with the available elements
        return false;
    }

    /**
     * Optimized version with memoization to avoid redundant calculations
     */
    public boolean canPartitionKSubsetsOptimized(int[] nums, int k) {
        // Basic validation
        if (k > nums.length) return false;

        int sum = 0;
        for (int num : nums) {
            sum += num;
        }

        if (sum % k != 0) return false;

        int target = sum / k;

        // Using bit manipulation to represent the 'used' state
        // Instead of boolean[] used, we use an integer where each bit represents
        // whether an element is used or not
        return backtrackWithMemo(k, 0, nums, 0, 0, target, new HashMap<>());
    }

    /**
     * Optimized backtracking with memoization and bit manipulation
     *
     * @param k          Number of buckets remaining to be filled
     * @param currentSum Sum of elements in the current bucket so far
     * @param nums       Array of numbers
     * @param start      Index in nums to start considering elements from
     * @param used       Bit representation of used elements
     * @param target     Target sum for each bucket
     * @param memo       Memoization map to avoid redundant calculations
     * @return true if a valid partition exists
     */
    private boolean backtrackWithMemo(int k, int currentSum, int[] nums, int start,
                                      int used, int target, HashMap<Integer, Boolean> memo) {
        // Base case: all buckets are filled
        if (k == 0) {
            return true;
        }

        // Current bucket is filled, move to the next bucket
        if (currentSum == target) {
            return backtrackWithMemo(k - 1, 0, nums, 0, used, target, memo);
        }

        // Check memoization
        if (memo.containsKey(used)) {
            return memo.get(used);
        }

        // Try to add nums[i] to the current bucket
        for (int i = start; i < nums.length; i++) {
            // Skip if the element is already used
            if (((used >> i) & 1) == 1) {
                continue;
            }

            // Skip if adding this element would overflow the bucket
            if (currentSum + nums[i] > target) {
                continue;
            }

            // Mark element as used using bit manipulation
            used |= (1 << i);
            currentSum += nums[i];

            // Recursively try to fill the rest of the current bucket
            if (backtrackWithMemo(k, currentSum, nums, i + 1, used, target, memo)) {
                return true;
            }

            // Backtrack: unmark the element
            used ^= (1 << i);  // XOR to flip the bit back to 0
            currentSum -= nums[i];
        }

        // Cache the result for this state
        memo.put(used, false);
        return false;
    }
}
