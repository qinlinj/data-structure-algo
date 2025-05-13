package org.qinlinj.algoframework._700_brute_force_search_algo._730_dfs_backtracking_problems._734_set_partition;

public class _734_a_PartitionKSubsetsIntro {
    /**
     * Basic implementation of canPartitionKSubsets to validate if an array
     * can be divided into k subsets with equal sum
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
        int targetSum = sum / k;

        // Create an array to track the current sum of each subset
        int[] buckets = new int[k];

        // Start the backtracking process
        return canPartition(nums, 0, buckets, targetSum);
    }

    /**
     * Simple backtracking approach from the elements' perspective
     * This method attempts to assign each element to one of k buckets
     */
    private boolean canPartition(int[] nums, int index, int[] buckets, int targetSum) {
        // Base case: all elements have been placed in buckets
        if (index == nums.length) {
            // Check if all buckets have the target sum
            for (int bucket : buckets) {
                if (bucket != targetSum) {
                    return false;
                }
            }
            return true;
        }

        // Try to place the current element in each bucket
        for (int i = 0; i < buckets.length; i++) {
            // Skip if adding the current element would exceed the target sum
            if (buckets[i] + nums[index] > targetSum) {
                continue;
            }

            // Place the element in the current bucket
            buckets[i] += nums[index];

            // Recursively try to place the next element
            if (canPartition(nums, index + 1, buckets, targetSum)) {
                return true;
            }

            // Backtrack: remove the element from the current bucket
            buckets[i] -= nums[index];
        }

        // Cannot partition the array with the current arrangement
        return false;
    }

}
