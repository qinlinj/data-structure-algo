package org.qinlinj.algoframework._800_dynamic_programming_algo._850_dp_games_II._853_burst_balloons;

/**
 * BURST BALLOONS PROBLEM - BACKTRACKING APPROACH
 * <p>
 * Problem Summary:
 * - Given n balloons with numbers, burst all balloons to maximize coins
 * - Bursting balloon i gives coins = nums[i-1] * nums[i] * nums[i+1]
 * - Boundaries are treated as balloons with value 1
 * <p>
 * Backtracking Approach:
 * - Exhaustively try all possible orders of bursting balloons
 * - This is essentially a permutation problem
 * - Time Complexity: O(n!) - factorial time, very inefficient
 * - Space Complexity: O(n) for recursion stack
 * <p>
 * Key Insights:
 * 1. For optimization problems, we need to exhaust all possibilities
 * 2. Backtracking is brute force enumeration
 * 3. This approach works but is too slow for large inputs (n ≤ 500)
 * <p>
 * Example:
 * Input: [3,1,5,8]
 * Process: [3,1,5,8] → [3,5,8] → [3,8] → [8] → []
 * Coins:   3*1*5 + 3*5*8 + 1*3*8 + 1*8*1 = 15 + 120 + 24 + 8 = 167
 */

import java.util.*;

public class _853_a_BacktrackingSolution {
    private int maxResult = 0;

    public static void main(String[] args) {
        _853_a_BacktrackingSolution solution = new _853_a_BacktrackingSolution();

        // Test case 1
        int[] nums1 = {3, 1, 5, 8};
        solution.demonstrateProcess(nums1);

        System.out.println();

        // Test case 2
        int[] nums2 = {1, 5};
        System.out.println("=== Test Case 2 ===");
        System.out.println("Input: " + Arrays.toString(nums2));
        System.out.println("Maximum coins: " + solution.maxCoins(nums2));

        System.out.println();

        // Performance analysis
        System.out.println("=== Performance Analysis ===");
        System.out.println("Time Complexity: O(n!) - factorial");
        System.out.println("Space Complexity: O(n) - recursion stack");
        System.out.println("Suitable for: Small inputs only (n < 10)");
        System.out.println("For n=500 (problem constraint): 500! operations - impractical!");
    }

    /**
     * Main method to find maximum coins using backtracking
     */
    public int maxCoins(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        List<Integer> balloons = new ArrayList<>();
        for (int num : nums) {
            balloons.add(num);
        }

        maxResult = 0;
        backtrack(balloons, 0);
        return maxResult;
    }

    /**
     * Backtracking helper method
     * @param balloons Current list of remaining balloons
     * @param currentScore Current accumulated score
     */
    private void backtrack(List<Integer> balloons, int currentScore) {
        // Base case: no balloons left
        if (balloons.isEmpty()) {
            maxResult = Math.max(maxResult, currentScore);
            return;
        }

        // Try bursting each balloon
        for (int i = 0; i < balloons.size(); i++) {
            // Calculate coins for bursting balloon at index i
            int leftVal = (i - 1 >= 0) ? balloons.get(i - 1) : 1;
            int rightVal = (i + 1 < balloons.size()) ? balloons.get(i + 1) : 1;
            int coins = leftVal * balloons.get(i) * rightVal;

            // Make choice: remove balloon
            int removed = balloons.remove(i);

            // Recursively explore
            backtrack(balloons, currentScore + coins);

            // Backtrack: restore balloon
            balloons.add(i, removed);
        }
    }

    /**
     * Helper method to demonstrate the step-by-step process
     */
    public void demonstrateProcess(int[] nums) {
        System.out.println("=== Backtracking Approach Demo ===");
        System.out.println("Input: " + Arrays.toString(nums));
        System.out.println("Trying all possible orders...");

        List<Integer> balloons = new ArrayList<>();
        for (int num : nums) {
            balloons.add(num);
        }

        demonstrateBacktrack(balloons, 0, new ArrayList<>());
        System.out.println("Maximum coins: " + maxCoins(nums));
    }

    private void demonstrateBacktrack(List<Integer> balloons, int score, List<String> steps) {
        if (balloons.isEmpty()) {
            System.out.println("Order: " + String.join(" → ", steps) + " | Total: " + score);
            return;
        }

        // Only show first few combinations to avoid too much output
        if (steps.size() < 2) {
            for (int i = 0; i < balloons.size(); i++) {
                int leftVal = (i - 1 >= 0) ? balloons.get(i - 1) : 1;
                int rightVal = (i + 1 < balloons.size()) ? balloons.get(i + 1) : 1;
                int coins = leftVal * balloons.get(i) * rightVal;

                int removed = balloons.remove(i);
                steps.add("Burst " + removed + " (+" + coins + ")");

                demonstrateBacktrack(balloons, score + coins, steps);

                balloons.add(i, removed);
                steps.remove(steps.size() - 1);
            }
        }
    }
}