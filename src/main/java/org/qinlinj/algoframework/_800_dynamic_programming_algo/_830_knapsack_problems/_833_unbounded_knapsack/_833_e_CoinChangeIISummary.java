package org.qinlinj.algoframework._800_dynamic_programming_algo._830_knapsack_problems._833_unbounded_knapsack;

/**
 * Summary of Coin Change II Problem
 * <p>
 * Key Points:
 * - LeetCode 518 "Coin Change II" is a classic complete knapsack problem
 * - Problem: Count the number of ways to make change using unlimited coins
 * - We explored both 2D and 1D dynamic programming solutions
 * - Key differences from 0-1 knapsack: items can be used unlimited times
 * - Time complexity: O(N * amount) for both approaches
 * - Space complexity: O(N * amount) for 2D solution, O(amount) for optimized 1D solution
 * - This problem shows how to adapt the knapsack problem framework to a "complete knapsack"
 * - Important insight: when items can be reused, the state transition changes from
 * dp[i-1][j-value] to dp[i][j-value]
 */
public class _833_e_CoinChangeIISummary {

    /**
     * Optimized solution recap (1D approach)
     */
    public static int change(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;

        for (int coin : coins) {
            for (int j = coin; j <= amount; j++) {
                dp[j] += dp[j - coin];
            }
        }

        return dp[amount];
    }

    /**
     * Key differences between Coin Change I and Coin Change II
     */
    public static void compareCoinChangeProblems() {
        System.out.println("Comparing Coin Change I and Coin Change II:");
        System.out.println("\nCoin Change I (LeetCode 322):");
        System.out.println("- Objective: Find the minimum number of coins needed");
        System.out.println("- DP state: dp[i] = minimum coins to make amount i");
        System.out.println("- DP recurrence: dp[i] = min(dp[i], dp[i-coin] + 1)");
        System.out.println("- Initial state: dp[0] = 0, dp[i] = infinity for i > 0");

        System.out.println("\nCoin Change II (LeetCode 518):");
        System.out.println("- Objective: Count the number of different ways to make change");
        System.out.println("- DP state: dp[i] = number of ways to make amount i");
        System.out.println("- DP recurrence: dp[i] += dp[i-coin]");
        System.out.println("- Initial state: dp[0] = 1, dp[i] = 0 for i > 0");
    }

    /**
     * General tips for knapsack problems
     */
    public static void knapsackProblemTips() {
        System.out.println("General Tips for Knapsack Problems:");
        System.out.println("1. Identify the problem type:");
        System.out.println("   - 0-1 Knapsack: Each item can be used at most once");
        System.out.println("   - Complete Knapsack: Each item can be used unlimited times");
        System.out.println("   - Multiple Knapsack: Each item has a limited quantity");

        System.out.println("\n2. Key elements in any knapsack problem:");
        System.out.println("   - Items with properties (e.g., weight, value, denomination)");
        System.out.println("   - A capacity constraint (e.g., weight limit, target amount)");
        System.out.println("   - An optimization goal (e.g., maximize value, count ways)");

        System.out.println("\n3. For complete knapsack problems (like Coin Change II):");
        System.out.println("   - Process items in the outer loop");
        System.out.println("   - Process capacity in the inner loop (from item size to capacity)");
        System.out.println("   - Use dp[i][j-value] (same row) rather than dp[i-1][j-value]");

        System.out.println("\n4. For 0-1 knapsack problems:");
        System.out.println("   - Either use 2D DP with dp[i][j] = max value with first i items and capacity j");
        System.out.println("   - Or use 1D DP but process capacity from large to small to avoid counting items twice");
    }

    public static void main(String[] args) {
        System.out.println("=== Coin Change II Summary ===\n");

        // Test cases to demonstrate the optimized solution
        int amount1 = 5;
        int[] coins1 = {1, 2, 5};
        System.out.println("Example 1 (amount=5, coins=[1,2,5]):");
        System.out.println("Number of ways: " + change(amount1, coins1));

        int amount2 = 3;
        int[] coins2 = {2};
        System.out.println("\nExample 2 (amount=3, coins=[2]):");
        System.out.println("Number of ways: " + change(amount2, coins2));

        int amount3 = 10;
        int[] coins3 = {10};
        System.out.println("\nExample 3 (amount=10, coins=[10]):");
        System.out.println("Number of ways: " + change(amount3, coins3));

        System.out.println("\n-----------------------------------\n");
        compareCoinChangeProblems();

        System.out.println("\n-----------------------------------\n");
        knapsackProblemTips();

        System.out.println("\n-----------------------------------\n");
        System.out.println("Conclusion:");
        System.out.println("Coin Change II demonstrates how to apply dynamic programming");
        System.out.println("to solve a complete knapsack problem. The key insight is");
        System.out.println("understanding how to handle the reuse of items in the state");
        System.out.println("transition logic. With proper DP formulation, we can solve");
        System.out.println("this combinatorial problem efficiently in O(N * amount) time.");
    }
}