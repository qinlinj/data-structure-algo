package org.qinlinj.algoframework._800_dynamic_programming_algo._850_dp_games_II._855_house_robber_problems;

import java.util.*;

/**
 * HOUSE ROBBER I - BASIC DYNAMIC PROGRAMMING SOLUTION
 * <p>
 * PROBLEM SUMMARY:
 * Given an array of non-negative integers representing the amount of money in each house,
 * determine the maximum amount of money you can rob without robbing two adjacent houses.
 * <p>
 * KEY CONCEPTS:
 * 1. Dynamic Programming State: Current house index
 * 2. Choice: Rob current house OR skip it
 * 3. State Transition: dp[i] = max(dp[i+1], nums[i] + dp[i+2])
 * 4. Base Case: When index >= array length, return 0
 * <p>
 * APPROACHES DEMONSTRATED:
 * 1. Top-down recursive with memoization
 * 2. Bottom-up iterative DP
 * 3. Space-optimized O(1) solution
 * <p>
 * TIME COMPLEXITY: O(n)
 * SPACE COMPLEXITY: O(n) for memoization, O(1) for optimized version
 */

public class _855_a_HouseRobberBasic {
    // Approach 1: Top-down recursive with memoization
    private int[] memo;

    public int robWithMemo(int[] nums) {
        memo = new int[nums.length];
        Arrays.fill(memo, -1);
        return dpMemo(nums, 0);
    }

    private int dpMemo(int[] nums, int start) {
        if (start >= nums.length) {
            return 0;
        }

        // Check memo to avoid recalculation
        if (memo[start] != -1) {
            return memo[start];
        }

        int result = Math.max(
                // Don't rob current house, move to next
                dpMemo(nums, start + 1),
                // Rob current house, skip next house
                nums[start] + dpMemo(nums, start + 2)
        );

        memo[start] = result;
        return result;
    }

    // Approach 2: Bottom-up iterative DP
    public int robBottomUp(int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;
        if (n == 1) return nums[0];

        // dp[i] represents max money that can be robbed from house i to end
        int[] dp = new int[n + 2];

        // Fill dp array from right to left
        for (int i = n - 1; i >= 0; i--) {
            dp[i] = Math.max(dp[i + 1], nums[i] + dp[i + 2]);
        }

        return dp[0];
    }
}
