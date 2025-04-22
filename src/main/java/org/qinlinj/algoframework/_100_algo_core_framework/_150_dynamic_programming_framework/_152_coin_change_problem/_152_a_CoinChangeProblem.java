package org.qinlinj.algoframework._100_algo_core_framework._150_dynamic_programming_framework._152_coin_change_problem;

// @formatter:off
/**
 * Dynamic Programming: Coin Change Problem
 * ----------------------------------------
 *
 * This class explores the famous "Coin Change" problem (LeetCode 322) as a case study
 * for understanding dynamic programming concepts, particularly:
 *
 * 1. Optimal substructure
 * 2. State identification
 * 3. State transition equations
 * 4. Recursive vs iterative solutions
 *
 * Problem Statement:
 * Given an array of coin denominations and a target amount, find the minimum
 * number of coins needed to make up that amount. Return -1 if the amount
 * cannot be made up by any combination of coins.
 */
public class _152_a_CoinChangeProblem {
    /**
     * Understanding Optimal Substructure in Coin Change:
     *
     * The coin change problem exhibits optimal substructure because the optimal solution
     * can be constructed from optimal solutions of its subproblems.
     *
     * For example, if we have coins [1,2,5] and amount 11:
     * - If we know the optimal way to make change for 10, 9, and 6
     * - Then optimal for 11 = min(optimal for 10 + 1 coin of value 1,
     *                             optimal for 9 + 1 coin of value 2,
     *                             optimal for 6 + 1 coin of value 5)
     *
     * This works because:
     * 1. Coins are unlimited, so using a coin doesn't affect future choices
     * 2. We're seeking the minimum, so there's always a single "best" answer for each subproblem
     * 3. Subproblems are independent - the optimal way to make 10 doesn't affect the optimal way to make 6
     */

    /**
     * State and Choices Analysis:
     *
     * 1. State: The only changing variable is the remaining amount
     *    - dp(amount) represents minimum coins needed for that amount
     *
     * 2. Choices: For each state, we have k choices (where k is the number of coin denominations)
     *    - For each coin, we can choose to use it or not
     *    - If we use it, we transition to state dp(amount - coin)
     *
     * 3. State Transition Equation:
     *    dp(amount) = min(dp(amount - coin) + 1) for each coin in coins
     *
     * 4. Base Cases:
     *    - dp(0) = 0 (no coins needed to make amount 0)
     *    - dp(negative) = -1 (impossible)
     */

    /**
     * Recursive Tree for coins=[1,2,5], amount=11:
     *
     * This recursive tree would be extremely large due to overlapping subproblems.
     * For example, dp(5) would be calculated multiple times:
     * - dp(11) → dp(6) → dp(5)
     * - dp(11) → dp(9) → dp(7) → dp(5)
     * - Many more paths...
     *
     * This is why memoization is crucial - it reduces the exponential time complexity
     * to polynomial by avoiding redundant calculations.
     */
    public static void main(String[] args) {
        _152_a_CoinChangeProblem solver = new _152_a_CoinChangeProblem();

        int[] coins = {1, 2, 5};
        int amount = 11;

        // Expected output: 3 (11 = 5 + 5 + 1)

        System.out.println("=== Coin Change Problem ===");
        System.out.println("Coins: [1, 2, 5], Amount: 11");

        // 1. Recursive solution (may be slow for larger amounts)
        long start = System.nanoTime();
        int recursiveResult = solver.coinChangeRecursive(coins, amount);
        long end = System.nanoTime();
        System.out.println("\nRecursive result: " + recursiveResult);
        System.out.println("Time (ms): " + (end - start) / 1_000_000.0);

        // 2. Memoized solution
        start = System.nanoTime();
        int memoizedResult = solver.coinChangeMemoized(coins, amount);
        end = System.nanoTime();
        System.out.println("\nMemoized result: " + memoizedResult);
        System.out.println("Time (ms): " + (end - start) / 1_000_000.0);

        // 3. Bottom-up solution
        start = System.nanoTime();
        int bottomUpResult = solver.coinChangeBottomUp(coins, amount);
        end = System.nanoTime();
        System.out.println("\nBottom-up result: " + bottomUpResult);
        System.out.println("Time (ms): " + (end - start) / 1_000_000.0);

        // Larger example to demonstrate efficiency differences
        int bigAmount = 100;
        System.out.println("\n=== Larger Example ===");
        System.out.println("Coins: [1, 2, 5], Amount: " + bigAmount);

        // Skip recursive for large amount as it would be too slow

        // Memoized solution
        start = System.nanoTime();
        memoizedResult = solver.coinChangeMemoized(coins, bigAmount);
        end = System.nanoTime();
        System.out.println("\nMemoized result: " + memoizedResult);
        System.out.println("Time (ms): " + (end - start) / 1_000_000.0);

        // Bottom-up solution
        start = System.nanoTime();
        bottomUpResult = solver.coinChangeBottomUp(coins, bigAmount);
        end = System.nanoTime();
        System.out.println("\nBottom-up result: " + bottomUpResult);
        System.out.println("Time (ms): " + (end - start) / 1_000_000.0);
    }

    /**
     * 1. Brute Force Recursive Solution
     *
     * This solution directly implements the recursive formula:
     * dp(amount) = min(dp(amount - coin) + 1) for each coin in coins
     *
     * Time Complexity: O(k^n) where k is the number of coin denominations
     * and n is the amount (exponential)
     * Space Complexity: O(n) for the recursion stack
     */
    public int coinChangeRecursive(int[] coins, int amount) {
        // Call our recursive helper function
        return dpRecursive(coins, amount);
    }

    /**
     * Recursive helper function
     * dp(amount) represents the minimum coins needed to make up 'amount'
     */
    private int dpRecursive(int[] coins, int amount) {
        // Base cases
        if (amount == 0) return 0;
        if (amount < 0) return -1;

        // Initialize result to "infinity"
        int minCoins = Integer.MAX_VALUE;

        // Try each coin as the last coin in the solution
        for (int coin : coins) {
            // Recursively solve the subproblem
            int subproblem = dpRecursive(coins, amount - coin);

            // If the subproblem has a valid solution
            if (subproblem != -1) {
                // Update the minimum (+1 because we used one more coin)
                minCoins = Math.min(minCoins, subproblem + 1);
            }
        }

        // Return the minimum or -1 if no solution
        return minCoins == Integer.MAX_VALUE ? -1 : minCoins;
    }

    /**
     * 2. Top-Down DP with Memoization
     *
     * Same recursive structure but with memoization to avoid redundant calculations
     *
     * Time Complexity: O(n * k) where k is the number of coin denominations
     * Space Complexity: O(n) for memo array and recursion stack
     */
    public int coinChangeMemoized(int[] coins, int amount) {
        // Initialize memoization array with -2 (indicating not calculated)
        int[] memo = new int[amount + 1];
        java.util.Arrays.fill(memo, -2);

        return dpMemoized(coins, amount, memo);
    }

    private int dpMemoized(int[] coins, int amount, int[] memo) {
        // Base cases
        if (amount == 0) return 0;
        if (amount < 0) return -1;

        // If already calculated, return the stored result
        if (memo[amount] != -2) return memo[amount];

        // Initialize result to "infinity"
        int minCoins = Integer.MAX_VALUE;

        // Try each coin
        for (int coin : coins) {
            int subproblem = dpMemoized(coins, amount - coin, memo);

            if (subproblem != -1) {
                minCoins = Math.min(minCoins, subproblem + 1);
            }
        }

        // Store and return the result
        memo[amount] = minCoins == Integer.MAX_VALUE ? -1 : minCoins;
        return memo[amount];
    }

    /**
     * 3. Bottom-Up DP with Tabulation
     *
     * Iterative solution that builds up the dp table from base cases
     *
     * Time Complexity: O(n * k) where k is the number of coin denominations
     * Space Complexity: O(n) for the dp array
     */
    public int coinChangeBottomUp(int[] coins, int amount) {
        // Initialize dp array with amount+1 (greater than any possible answer)
        int[] dp = new int[amount + 1];
        java.util.Arrays.fill(dp, amount + 1);

        // Base case
        dp[0] = 0;

        // Build the dp array bottom-up
        for (int currentAmount = 1; currentAmount <= amount; currentAmount++) {
            // Try each coin
            for (int coin : coins) {
                // If this coin can be used (not larger than current amount)
                if (coin <= currentAmount) {
                    // State transition equation
                    dp[currentAmount] = Math.min(dp[currentAmount], dp[currentAmount - coin] + 1);
                }
            }
        }

        // Return the result or -1 if impossible
        return dp[amount] > amount ? -1 : dp[amount];
    }
}
