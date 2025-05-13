package org.qinlinj.algoframework._700_brute_force_search_algo._730_dfs_backtracking_problems._734_set_partition;

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
}
