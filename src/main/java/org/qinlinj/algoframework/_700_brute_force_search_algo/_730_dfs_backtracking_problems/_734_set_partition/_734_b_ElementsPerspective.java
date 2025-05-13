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
}
