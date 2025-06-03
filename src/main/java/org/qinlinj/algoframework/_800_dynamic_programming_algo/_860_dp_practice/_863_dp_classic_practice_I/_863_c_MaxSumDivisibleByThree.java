package org.qinlinj.algoframework._800_dynamic_programming_algo._860_dp_practice._863_dp_classic_practice_I;

/**
 * LeetCode 1262. Greatest Sum Divisible by Three - Dynamic Programming Solution
 * <p>
 * PROBLEM SUMMARY:
 * Given an array of integers, find the maximum sum of elements that is divisible by 3.
 * <p>
 * KEY CONCEPTS:
 * 1. State-based Dynamic Programming with remainder consideration
 * 2. State Definition: dp[i][j] = maximum sum using nums[0..i] with remainder j when divided by 3
 * 3. Three possible remainders: 0, 1, 2
 * 4. State Transition based on current number's remainder:
 * - If nums[i] % 3 == 0: Adding it doesn't change remainder
 * - If nums[i] % 3 == 1: remainder changes: 0→1, 1→2, 2→0
 * - If nums[i] % 3 == 2: remainder changes: 0→2, 1→0, 2→1
 * <p>
 * TIME COMPLEXITY: O(n)
 * SPACE COMPLEXITY: O(n) for 2D DP, can be optimized to O(1)
 * <p>
 * EXAMPLE:
 * Input: [3,6,5,1,8]
 * Output: 18 (select 3,6,1,8: sum=18, 18%3=0)
 */

public class _863_c_MaxSumDivisibleByThree {
    public int maxSumDivThree(int[] nums) {
        // dp[i][j] = maximum sum using nums[0..i] with remainder j when divided by 3
        int[][] dp = new int[nums.length + 1][3];

        // Base case: without any numbers
        dp[0][0] = 0;                    // remainder 0 is possible with sum 0
        dp[0][1] = Integer.MIN_VALUE;    // remainder 1 is impossible initially
        dp[0][2] = Integer.MIN_VALUE;    // remainder 2 is impossible initially

        // Fill the DP table
        for (int i = 1; i <= nums.length; i++) {
            int curNum = nums[i - 1];
            int remainder = curNum % 3;

            if (remainder == 0) {
                // Adding a number divisible by 3 doesn't change remainder
                dp[i][0] = dp[i - 1][0] + curNum;
                dp[i][1] = dp[i - 1][1] + curNum;
                dp[i][2] = dp[i - 1][2] + curNum;
            } else if (remainder == 1) {
                // Adding number with remainder 1: 0→1, 1→2, 2→0
                dp[i][0] = Math.max(dp[i - 1][2] + curNum, dp[i - 1][0]);
                dp[i][1] = Math.max(dp[i - 1][0] + curNum, dp[i - 1][1]);
                dp[i][2] = Math.max(dp[i - 1][1] + curNum, dp[i - 1][2]);
            } else { // remainder == 2
                // Adding number with remainder 2: 0→2, 1→0, 2→1
                dp[i][0] = Math.max(dp[i - 1][1] + curNum, dp[i - 1][0]);
                dp[i][1] = Math.max(dp[i - 1][2] + curNum, dp[i - 1][1]);
                dp[i][2] = Math.max(dp[i - 1][0] + curNum, dp[i - 1][2]);
            }
        }

        return dp[nums.length][0]; // Return maximum sum with remainder 0
    }
}
