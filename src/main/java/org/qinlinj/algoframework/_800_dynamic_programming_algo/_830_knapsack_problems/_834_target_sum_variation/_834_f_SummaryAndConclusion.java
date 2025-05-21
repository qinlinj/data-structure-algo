package org.qinlinj.algoframework._800_dynamic_programming_algo._830_knapsack_problems._834_target_sum_variation;

public class _834_f_SummaryAndConclusion {
    /**
     * Final optimized solution for the Target Sum problem using 1D DP approach
     * This combines all insights from previous approaches
     */
    public static int findTargetSumWays(int[] nums, int target) {
        // Calculate total sum of the array
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }

        // Edge cases:
        // 1. If target's absolute value exceeds sum, impossible to reach
        // 2. If (sum + target) is odd, impossible to have an integer solution for subset sum
        if (sum < Math.abs(target) || (sum + target) % 2 != 0) {
            return 0;
        }

        // Calculate the subset sum we need to find
        int subsetSum = (sum + target) / 2;

        // Optimized 1D DP array
        int[] dp = new int[subsetSum + 1];
        dp[0] = 1; // Base case: one way to make zero sum

        // Fill the dp array
        for (int num : nums) {
            // Process from right to left to avoid counting elements multiple times
            for (int j = subsetSum; j >= num; j--) {
                dp[j] += dp[j - num];
            }
        }

        return dp[subsetSum];
    }

}
