package org.qinlinj.algoframework._800_dynamic_programming_algo._830_knapsack_problems._834_target_sum_variation;

public class _834_b_BacktrackingApproach {
    /**
     * Result counter - in a real solution, this would be inside the class
     * but outside the method to avoid being reset during recursion
     */
    private static int result = 0;

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

    /**
     * Alternative implementation with a class variable to track results
     */
    public static int findTargetSumWaysWithClassVar(int[] nums, int target) {
        if (nums.length == 0) return 0;

        // Reset result before starting
        result = 0;
        backtrackWithClassVar(nums, 0, target);
        return result;
    }

    /**
     * Helper method that implements the backtracking algorithm
     *
     * @param nums   array of non-negative integers
     * @param index  current index in the array
     * @param remain remaining value to reach zero
     * @return number of ways to reach target from this point
     */
    private static int backtrack(int[] nums, int index, int remain) {
        // Base case: we've processed all numbers
        if (index == nums.length) {
            // If we've successfully reached the target (remain == 0), count this way
            if (remain == 0) {
                return 1;
            }
            return 0;
        }

        // Choose minus sign for current number
        // (remain + nums[index] means we're adding to 'remain' to offset the effect)
        int minusCount = backtrack(nums, index + 1, remain + nums[index]);

        // Choose plus sign for current number
        // (remain - nums[index] means we're reducing 'remain' to approach 0)
        int plusCount = backtrack(nums, index + 1, remain - nums[index]);

        // Return total ways by summing both choices
        return minusCount + plusCount;
    }
}
