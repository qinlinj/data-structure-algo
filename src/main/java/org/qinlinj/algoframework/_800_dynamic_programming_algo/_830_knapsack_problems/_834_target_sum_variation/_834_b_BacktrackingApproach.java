package org.qinlinj.algoframework._800_dynamic_programming_algo._830_knapsack_problems._834_target_sum_variation;

public class _834_b_BacktrackingApproach {
    /**
     * Main solution method using backtracking
     *
     * @param nums   array of non-negative integers
     * @param target the target sum to reach
     * @return number of ways to reach the target
     */
    public static int findTargetSumWays(int[] nums, int target) {
        if (nums.length == 0) return 0;
        return backtrack(nums, 0, target);
    }

    private static int backtrack(int[] nums, int i, int target) {
    }

}
