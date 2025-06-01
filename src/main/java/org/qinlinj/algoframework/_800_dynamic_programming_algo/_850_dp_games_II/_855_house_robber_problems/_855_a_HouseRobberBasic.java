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

import java.util.*;

public class _855_a_HouseRobberBasic {

    // Approach 1: Top-down recursive with memoization
    private int[] memo;

    public static void main(String[] args) {
        _855_a_HouseRobberBasic solution = new _855_a_HouseRobberBasic();

        // Test case 1: [2,1,7,9,3,1] -> Expected: 12 (rob houses 0,3,5: 2+9+1=12)
        int[] test1 = {2, 1, 7, 9, 3, 1};
        System.out.println("Test Case 1: " + Arrays.toString(test1));
        System.out.println("Memoization result: " + solution.robWithMemo(test1));
        System.out.println("Bottom-up result: " + solution.robBottomUp(test1));
        System.out.println("Optimized result: " + solution.robOptimized(test1));
        System.out.println("Expected: 12\n");

        // Test case 2: [5,1,3,9] -> Expected: 14 (rob houses 0,3: 5+9=14)
        int[] test2 = {5, 1, 3, 9};
        System.out.println("Test Case 2: " + Arrays.toString(test2));
        System.out.println("Memoization result: " + solution.robWithMemo(test2));
        System.out.println("Bottom-up result: " + solution.robBottomUp(test2));
        System.out.println("Optimized result: " + solution.robOptimized(test2));
        System.out.println("Expected: 14\n");

        // Test case 3: [1,2,3,1] -> Expected: 4 (rob houses 0,2: 1+3=4)
        int[] test3 = {1, 2, 3, 1};
        System.out.println("Test Case 3: " + Arrays.toString(test3));
        System.out.println("Memoization result: " + solution.robWithMemo(test3));
        System.out.println("Bottom-up result: " + solution.robBottomUp(test3));
        System.out.println("Optimized result: " + solution.robOptimized(test3));
        System.out.println("Expected: 4\n");

        // Edge case: Single house
        int[] test4 = {5};
        System.out.println("Edge Case - Single house: " + Arrays.toString(test4));
        System.out.println("Result: " + solution.robOptimized(test4));
        System.out.println("Expected: 5");
    }

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

    // Approach 3: Space-optimized O(1) solution
    public int robOptimized(int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;
        if (n == 1) return nums[0];

        // Only keep track of last two states
        int dp_i_1 = 0; // dp[i+1]
        int dp_i_2 = 0; // dp[i+2]
        int dp_i = 0;   // dp[i]

        for (int i = n - 1; i >= 0; i--) {
            dp_i = Math.max(dp_i_1, nums[i] + dp_i_2);
            dp_i_2 = dp_i_1;
            dp_i_1 = dp_i;
        }

        return dp_i;
    }
}