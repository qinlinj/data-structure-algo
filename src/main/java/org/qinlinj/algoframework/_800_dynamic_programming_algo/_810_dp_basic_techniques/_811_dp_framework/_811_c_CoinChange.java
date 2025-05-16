package org.qinlinj.algoframework._800_dynamic_programming_algo._810_dp_basic_techniques._811_dp_framework;

import java.util.*;

/**
 * Coin Change Problem - Dynamic Programming Solution
 * =================================================
 * <p>
 * This class demonstrates solving the Coin Change problem using dynamic programming.
 * LeetCode Problem 322: Given a set of coin denominations and a target amount,
 * find the minimum number of coins needed to make up that amount.
 * <p>
 * Key Concepts Illustrated:
 * 1. Optimal Substructure - The solution to the problem contains optimal solutions to subproblems
 * 2. Overlapping Subproblems - Same subproblems are solved multiple times
 * 3. State Identification - The "state" is the target amount
 * 4. Choices - For each state, we can choose any available coin
 * 5. State Transition Equation: dp[amount] = min(dp[amount], 1 + dp[amount - coin])
 * <p>
 * Implementation Approaches:
 * 1. Naive recursive solution - Exponential time complexity
 * 2. Top-down with memoization - O(amount * coins.length) time complexity
 * 3. Bottom-up with tabulation - O(amount * coins.length) time complexity
 * <p>
 * This problem exemplifies classic dynamic programming where we build solutions
 * to larger problems from solutions to smaller subproblems.
 */
public class _811_c_CoinChange {

    public static void main(String[] args) {
        _811_c_CoinChange solution = new _811_c_CoinChange();

        // Example: coins = [1, 2, 5], amount = 11
        // Expected output: 3 (11 = 5 + 5 + 1)
        int[] coins = {1, 2, 5};
        int amount = 11;

        System.out.println("Coin Change Problem Example:");
        System.out.println("Coins: [1, 2, 5], Amount: 11");

        // Using optimized method for first calculation to avoid timeout
        System.out.println("Approach 1 (Recursive): " + solution.coinChangeRecursive(coins, amount));
        System.out.println("Approach 2 (Memoized): " + solution.coinChangeMemoized(coins, amount));
        System.out.println("Approach 3 (Tabulation): " + solution.coinChangeTabulation(coins, amount));
    }

    /**
     * Approach 1: Naive recursive solution
     * Time Complexity: O(coins.length^amount) in worst case
     * Space Complexity: O(amount) - recursion stack depth
     */
    public int coinChangeRecursive(int[] coins, int amount) {
        return coinChangeRecursiveHelper(coins, amount);
    }

    private int coinChangeRecursiveHelper(int[] coins, int amount) {
        // Base case: exact change
        if (amount == 0) {
            return 0;
        }
        // Base case: negative amount (invalid)
        if (amount < 0) {
            return -1;
        }

        int minCoins = Integer.MAX_VALUE;

        // Try each coin denomination
        for (int coin : coins) {
            // Recursive call for remaining amount after using this coin
            int subproblem = coinChangeRecursiveHelper(coins, amount - coin);

            // If subproblem has a valid solution
            if (subproblem != -1) {
                // Update minimum coins needed (current coin + coins for subproblem)
                minCoins = Math.min(minCoins, subproblem + 1);
            }
        }

        // Return -1 if no valid combination was found
        return minCoins == Integer.MAX_VALUE ? -1 : minCoins;
    }

    /**
     * Approach 2: Top-down with memoization
     * Time Complexity: O(amount * coins.length)
     * Space Complexity: O(amount) - memo array + recursion stack
     */
    public int coinChangeMemoized(int[] coins, int amount) {
        // Initialize memo array with a special value to indicate uncalculated states
        int[] memo = new int[amount + 1];
        for (int i = 0; i <= amount; i++) {
            memo[i] = -666; // Special value indicating "not calculated yet"
        }

        return coinChangeMemoHelper(coins, amount, memo);
    }

    private int coinChangeMemoHelper(int[] coins, int amount, int[] memo) {
        // Base cases
        if (amount == 0) return 0;
        if (amount < 0) return -1;

        // Check if already calculated
        if (memo[amount] != -666) {
            return memo[amount];
        }

        int minCoins = Integer.MAX_VALUE;

        // Try each coin
        for (int coin : coins) {
            int subproblem = coinChangeMemoHelper(coins, amount - coin, memo);

            // If subproblem has a valid solution
            if (subproblem != -1) {
                minCoins = Math.min(minCoins, subproblem + 1);
            }
        }

        // Save result in memo
        memo[amount] = (minCoins == Integer.MAX_VALUE) ? -1 : minCoins;
        return memo[amount];
    }

    /**
     * Approach 3: Bottom-up with tabulation
     * Time Complexity: O(amount * coins.length)
     * Space Complexity: O(amount) - dp array
     */
    public int coinChangeTabulation(int[] coins, int amount) {
        // Initialize dp array
        // dp[i] represents the minimum number of coins needed to make amount i
        int[] dp = new int[amount + 1];

        // Initialize with a value larger than any possible result
        // (amount+1 works because we can't use more than 'amount' coins of value 1)
        Arrays.fill(dp, amount + 1);

        // Base case
        dp[0] = 0;

        // Fill the dp table bottom-up
        for (int currentAmount = 1; currentAmount <= amount; currentAmount++) {
            // Try each coin
            for (int coin : coins) {
                // If this coin can be used (doesn't exceed the current amount)
                if (currentAmount - coin >= 0) {
                    // Update with minimum coins needed
                    dp[currentAmount] = Math.min(dp[currentAmount], 1 + dp[currentAmount - coin]);
                }
            }
        }

        // If dp[amount] is still amount+1, it means no solution exists
        return dp[amount] > amount ? -1 : dp[amount];
    }
}