package org.qinlinj.algoframework._800_dynamic_programming_algo._830_knapsack_problems._834_target_sum_variation;

public class _834_d_KnapsackApproach {
    /**
     * Main solution method using the subset sum / knapsack approach
     *
     * @param nums   array of non-negative integers
     * @param target the target sum to reach
     * @return number of ways to reach the target
     */
    public static int findTargetSumWays(int[] nums, int target) {
        // Calculate sum of array
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }

        // Edge cases:
        // 1. If target > sum, impossible to reach
        // 2. If (sum + target) is odd, impossible to have an integer solution for subset sum
        if (sum < Math.abs(target) || (sum + target) % 2 != 0) {
            return 0;
        }

        // Calculate the subset sum we need to find
        int subsetSum = (sum + target) / 2;

        // Find number of subsets with the calculated sum
        return countSubsetSum(nums, subsetSum);
    }
}
