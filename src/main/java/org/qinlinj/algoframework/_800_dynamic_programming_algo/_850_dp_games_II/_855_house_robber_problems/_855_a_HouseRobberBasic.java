package org.qinlinj.algoframework._800_dynamic_programming_algo._850_dp_games_II._855_house_robber_problems;

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
}
