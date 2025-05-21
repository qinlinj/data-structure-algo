package org.qinlinj.algoframework._800_dynamic_programming_algo._830_knapsack_problems._833_unbounded_knapsack;

/**
 * Approach to Solving Coin Change II Problem
 * <p>
 * Key Points:
 * - This is a "Complete Knapsack Problem" variation where each item can be used unlimited times
 * - The approach follows standard dynamic programming steps:
 * 1. Identify states and choices
 * 2. Define the DP array
 * 3. Establish state transition logic
 * 4. Handle base cases and edge conditions
 * - States: available coins and remaining amount
 * - Choices: use a particular coin or skip it
 * - The difference from 0-1 knapsack is that items can be reused
 */
public class _833_b_CoinChangeIIApproach {

    /**
     * Step 1: Understanding states and choices
     * States:
     * - The coins available for use (first i coins)
     * - The target amount to make change for
     * <p>
     * Choices:
     * - Use the current coin
     * - Skip the current coin
     */
    public static void explainStatesAndChoices() {
        System.out.println("States:");
        System.out.println("1. Available coins (first i coins)");
        System.out.println("2. Target amount (j)");
        System.out.println("\nChoices:");
        System.out.println("1. Use the current coin");
        System.out.println("2. Skip the current coin");
    }

    /**
     * Step 2: DP array definition
     * dp[i][j] = number of ways to make amount j using only the first i coins
     */
    public static void explainDPDefinition() {
        System.out.println("DP Array Definition:");
        System.out.println("dp[i][j] = number of ways to make amount j using only the first i coins");
        System.out.println("\nBase Cases:");
        System.out.println("dp[0][0] = 1 (one way to make 0 amount: use no coins)");
        System.out.println("dp[0][j] = 0 for j > 0 (no way to make positive amount with no coins)");
        System.out.println("dp[i][0] = 1 for all i (one way to make 0 amount: use no coins)");
    }

    /**
     * Step 3: State transition logic
     * If we can use coin i (coins[i-1]):
     * dp[i][j] = dp[i-1][j] + dp[i][j-coins[i-1]]
     * Else:
     * dp[i][j] = dp[i-1][j]
     */
    public static void explainStateTransition() {
        System.out.println("State Transition Logic:");
        System.out.println("For each coin i and amount j:");
        System.out.println("If j >= coins[i-1]: dp[i][j] = dp[i-1][j] + dp[i][j-coins[i-1]]");
        System.out.println("Otherwise: dp[i][j] = dp[i-1][j]");
        System.out.println("\nExplanation:");
        System.out.println("dp[i-1][j] = ways without using the current coin");
        System.out.println("dp[i][j-coins[i-1]] = ways using at least one of the current coin");
        System.out.println("Note: We use dp[i] (not dp[i-1]) when considering reuse of the current coin");
    }

    public static void main(String[] args) {
        System.out.println("=== Dynamic Programming Approach for Coin Change II ===\n");
        explainStatesAndChoices();
        System.out.println("\n-----------------------------------\n");
        explainDPDefinition();
        System.out.println("\n-----------------------------------\n");
        explainStateTransition();
    }
}
