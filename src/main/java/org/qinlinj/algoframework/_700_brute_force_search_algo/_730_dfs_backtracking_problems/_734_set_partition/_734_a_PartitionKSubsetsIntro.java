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
}
