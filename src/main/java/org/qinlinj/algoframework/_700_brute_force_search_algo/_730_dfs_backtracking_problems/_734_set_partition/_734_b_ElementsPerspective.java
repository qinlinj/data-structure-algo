package org.qinlinj.algoframework._700_brute_force_search_algo._730_dfs_backtracking_problems._734_set_partition;

import java.util.*;

public class _734_b_ElementsPerspective {
    /**
     * Determines if the array can be partitioned into k subsets with equal sum
     * using the elements' perspective approach
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

        // Create an array to track the current sum of each subset
        int[] buckets = new int[k];

        // Optimization: Sort the array in descending order
        // This helps with pruning by making it more likely to hit the
        // "bucket overflow" condition earlier in the recursion
        Arrays.sort(nums);
        reverseArray(nums);

        // Start the backtracking process
        return backtrack(nums, 0, buckets, target);
    }

    /**
     * Backtracking method to place each element in one of the k buckets
     *
     * @param nums    Array of numbers to partition
     * @param index   Current index in the nums array being considered
     * @param buckets Array tracking the sum in each bucket
     * @param target  Target sum for each bucket
     * @return true if a valid partition exists
     */
    private boolean backtrack(int[] nums, int index, int[] buckets, int target) {
        // Base case: all elements have been placed in buckets
        if (index == nums.length) {
            // Check if all buckets have the target sum
            for (int bucket : buckets) {
                if (bucket != target) {
                    return false;
                }
            }
            return true;
        }

        // Try to place the current element in each bucket
        for (int i = 0; i < buckets.length; i++) {
            // Skip if adding the current element would exceed the target sum
            if (buckets[i] + nums[index] > target) {
                continue;
            }

            // Place the element in the current bucket
            buckets[i] += nums[index];

            // Recursively try to place the next element
            if (backtrack(nums, index + 1, buckets, target)) {
                return true;
            }

            // Backtrack: remove the element from the current bucket
            buckets[i] -= nums[index];

            // Optimization: If this bucket is empty, break
            // If placing the element in an empty bucket didn't work,
            // placing it in another empty bucket won't work either
            if (buckets[i] == 0) {
                break;
            }
        }

        // Cannot partition the array with the current arrangement
        return false;
    }
}
