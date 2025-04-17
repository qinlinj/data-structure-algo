package org.qinlinj.algoframework._100_core_framework._150_dynamic_programming_framework._151_fibonacci_sequence;

public class DynamicProgrammingGuide {
    /**
     * Example: Fibonacci Sequence
     * Problem: Calculate the nth Fibonacci number where F(1)=1, F(2)=1, F(n)=F(n-1)+F(n-2) for n>2
     * <p>
     * This demonstrates the inefficiency of naive recursion and three approaches to optimize:
     * 1. Naive recursive solution (exponential time)
     * 2. Top-down DP with memoization (linear time)
     * 3. Bottom-up DP with tabulation (linear time)
     */
    // Approach 1: Naive recursive solution - O(2^n) time complexity
    public int fibNaive(int n) {
        if (n == 1 || n == 2) return 1;
        return fibNaive(n - 1) + fibNaive(n - 2);
    }

    // Approach 2: Top-down DP with memoization - O(n) time complexity
    public int fibMemoization(int n) {
        // Create memoization array to store already computed results
        Integer[] memo = new Integer[n + 1];
        return fibMemoHelper(n, memo);
    }

    private int fibMemoHelper(int n, Integer[] memo) {
        // Base case
        if (n == 1 || n == 2) return 1;

        // If we've already calculated this value, return it
        if (memo[n] != null) return memo[n];

        // Calculate and store the result
        memo[n] = fibMemoHelper(n - 1, memo) + fibMemoHelper(n - 2, memo);
        return memo[n];
    }

    // Approach 3: Bottom-up DP with tabulation - O(n) time complexity
    public int fibTabulation(int n) {
        if (n == 1 || n == 2) return 1;

        // Initialize DP table with base cases
        int[] dp = new int[n + 1];
        dp[1] = dp[2] = 1;

        // Fill the table bottom-up
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[n];
    }

    /**
     * Example: Coin Change Problem
     * Problem: Given coins of certain denominations and a target amount,
     * find the minimum number of coins needed to make up that amount.
     * <p>
     * This demonstrates how to formulate a state transition equation:
     * dp[i] = min(dp[i], dp[i - coin] + 1) for each coin in coins
     * where dp[i] represents the minimum number of coins needed for amount i
     */
    public int coinChange(int[] coins, int amount) {
        // Initialize dp array with amount+1 (which is greater than any possible answer)
        int[] dp = new int[amount + 1];
        java.util.Arrays.fill(dp, amount + 1);

        // Base case: 0 coins needed to make amount 0
        dp[0] = 0;

        // Fill dp table bottom-up
        for (int i = 1; i <= amount; i++) {
            // Try each coin
            for (int coin : coins) {
                // If the coin value is less than or equal to current amount
                if (coin <= i) {
                    // State transition: dp[i] = min(dp[i], dp[i-coin] + 1)
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }

        // If dp[amount] is still amount+1, it means no solution exists
        return dp[amount] > amount ? -1 : dp[amount];
    }

    
}
