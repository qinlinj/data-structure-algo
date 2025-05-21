package org.qinlinj.algoframework._800_dynamic_programming_algo._830_knapsack_problems._834_target_sum_variation;

public class _834_c_DPWithMemoization {
    /**
     * HashMap to serve as a memoization cache
     * Key is "index,remain" and value is the number of ways to reach target from that state
     */
    private static java.util.HashMap<String, Integer> memo;

    /**
     * Main solution method using DP with memoization
     *
     * @param nums   array of non-negative integers
     * @param target the target sum to reach
     * @return number of ways to reach the target
     */
    public static int findTargetSumWays(int[] nums, int target) {
        if (nums.length == 0) return 0;

        // Initialize memoization cache
        memo = new java.util.HashMap<>();

        return dp(nums, 0, target);
    }
}
