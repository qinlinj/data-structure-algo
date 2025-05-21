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

    /**
     * DP function with memoization
     * Definition: dp(i, remain) = number of ways to form target sum using nums[i..n-1]
     *
     * @param nums   array of non-negative integers
     * @param i      current index in the array
     * @param remain remaining value to reach zero
     * @return number of ways to reach target from this state
     */
    private static int dp(int[] nums, int i, int remain) {
        // Base case: processed all numbers
        if (i == nums.length) {
            return remain == 0 ? 1 : 0;
        }

        // Create key for memoization
        String key = i + "," + remain;

        // Check if we've already computed this state
        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        // Calculate result by combining both choices (+ and -)
        int result = dp(nums, i + 1, remain - nums[i]) + // using +
                dp(nums, i + 1, remain + nums[i]);  // using -

        // Store result in memo before returning
        memo.put(key, result);
        return result;
    }

    /**
     * Alternative implementation using a 2D array for memoization
     * This requires handling negative remaining values by using an offset
     */
    public static int findTargetSumWays2DArray(int[] nums, int target) {
        if (nums.length == 0) return 0;

        // Calculate sum to determine the range of possible values
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }

        // If target is beyond possible range or sum+target is odd, no solution exists
        if (Math.abs(target) > sum || (sum + target) % 2 != 0) {
            return 0;
        }

        // Range for 'remain' will be [-sum, sum]
        // Use offset to handle negative values in array indices
        int offset = sum;
        Integer[][] memo2D = new Integer[nums.length][2 * sum + 1];

        return dp2DArray(nums, 0, target, memo2D, offset);
    }

    private static int dp2DArray(int[] nums, int i, int remain, Integer[][] memo, int offset) {
        // Base case
        if (i == nums.length) {
            return remain == 0 ? 1 : 0;
        }

        // Check for out of bounds
        if (Math.abs(remain) > offset) {
            return 0;
        }

        // Check if already computed
        if (memo[i][remain + offset] != null) {
            return memo[i][remain + offset];
        }

        // Calculate and memoize result
        int result = dp2DArray(nums, i + 1, remain - nums[i], memo, offset) +
                dp2DArray(nums, i + 1, remain + nums[i], memo, offset);

        memo[i][remain + offset] = result;
        return result;
    }
}
