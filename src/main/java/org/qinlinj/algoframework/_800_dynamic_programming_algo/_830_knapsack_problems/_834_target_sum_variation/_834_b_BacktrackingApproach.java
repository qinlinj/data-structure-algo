package org.qinlinj.algoframework._800_dynamic_programming_algo._830_knapsack_problems._834_target_sum_variation;

/**
 * Backtracking Approach to Target Sum Problem
 * <p>
 * Key Points:
 * - Backtracking is a brute force algorithm that exhaustively explores all possibilities
 * - For the Target Sum problem, we consider two choices for each number: add or subtract
 * - The backtracking process follows a template: make a choice, recurse, and undo the choice
 * - Time complexity is O(2^n) where n is the size of the input array
 * - This approach creates a binary tree of recursive calls where each node represents a decision
 * - While inefficient for larger inputs, backtracking is intuitive and easy to implement
 * - The solution tracks a remaining target value that we aim to reduce to zero
 */
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

    /**
     * Alternative backtracking method using a class variable
     * This follows the typical backtracking template more closely
     */
    private static void backtrackWithClassVar(int[] nums, int index, int remain) {
        // Base case: we've processed all numbers
        if (index == nums.length) {
            if (remain == 0) {
                // Found a valid combination
                result++;
            }
            return;
        }

        // Choose minus sign
        remain += nums[index];
        backtrackWithClassVar(nums, index + 1, remain);
        remain -= nums[index]; // Undo choice

        // Choose plus sign
        remain -= nums[index];
        backtrackWithClassVar(nums, index + 1, remain);
        remain += nums[index]; // Undo choice
    }

    /**
     * Demonstration of the backtracking approach
     */
    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 1, 1};
        int target = 3;

        System.out.println("=== Backtracking Approach ===");
        System.out.println("Input: [1, 1, 1, 1, 1], target = 3");

        // Using the functional approach
        int ways = findTargetSumWays(nums, target);
        System.out.println("Number of ways (functional approach): " + ways);

        // Using the class variable approach
        ways = findTargetSumWaysWithClassVar(nums, target);
        System.out.println("Number of ways (class variable approach): " + ways);

        // Demonstration with smaller example to trace
        int[] smallNums = {1, 1, 1};
        int smallTarget = 1;
        System.out.println("\nTracing smaller example:");
        System.out.println("Input: [1, 1, 1], target = 1");

        // Reset result for new example
        result = 0;
        backtrackWithClassVar(smallNums, 0, smallTarget);
        System.out.println("Number of ways: " + result);

        System.out.println("\nTime complexity: O(2^n)");
        System.out.println("The backtracking approach explores a binary tree with a maximum of 2^n leaf nodes");
        System.out.println("where n is the length of the nums array.");
    }
}