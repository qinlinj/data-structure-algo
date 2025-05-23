package org.qinlinj.algoframework._800_dynamic_programming_algo._830_knapsack_problems._833_unbounded_knapsack;

/**
 * Implementation of the Coin Change II Problem
 * <p>
 * Key Points:
 * - 2D dynamic programming solution
 * - Time Complexity: O(N * amount) where N is the number of coin denominations
 * - Space Complexity: O(N * amount) for the 2D DP array
 * - The solution handles edge cases like zero amount and no coins
 * - Implements the state transition logic based on the DP recurrence relation
 * - For each coin, we decide whether to use it or skip it
 * - Since coins can be reused, we reference dp[i][j-coins[i-1]] (same row) rather than dp[i-1][j-coins[i-1]]
 */
public class _833_c_CoinChangeIIImplementation {

    /**
     * Complete solution for the Coin Change II problem using 2D DP array
     *
     * @param amount the target amount
     * @param coins  array of available coin denominations
     * @return the number of ways to make change for the amount
     */
    public static int change(int amount, int[] coins) {
        int n = coins.length;
        // Create a 2D DP array
        int[][] dp = new int[n + 1][amount + 1];

        // Base case: one way to make zero amount (use no coins)
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 1;
        }

        // Fill the dp array using bottom-up approach
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= amount; j++) {
                // Current coin value
                int coinValue = coins[i - 1];

                if (j - coinValue >= 0) {
                    // We have two choices:
                    // 1. Skip this coin: dp[i-1][j]
                    // 2. Use this coin at least once: dp[i][j-coinValue]
                    dp[i][j] = dp[i - 1][j] + dp[i][j - coinValue];
                } else {
                    // Can't use this coin, so skip it
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        // Return the number of ways to make the target amount
        return dp[n][amount];
    }

    /**
     * Helper method to print the filled DP table for visualization
     */
    public static void printDPTable(int amount, int[] coins) {
        int n = coins.length;
        int[][] dp = new int[n + 1][amount + 1];

        // Base case
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 1;
        }

        // Fill the dp array
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= amount; j++) {
                if (j - coins[i - 1] >= 0) {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - coins[i - 1]];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        // Print the DP table
        System.out.println("DP Table for amount = " + amount + " and coins = " + java.util.Arrays.toString(coins));
        System.out.print("   | ");
        for (int j = 0; j <= amount; j++) {
            System.out.printf("%2d ", j);
        }
        System.out.println("\n---+-" + "---".repeat(amount + 1));

        for (int i = 0; i <= n; i++) {
            System.out.printf("%2d | ", i);
            for (int j = 0; j <= amount; j++) {
                System.out.printf("%2d ", dp[i][j]);
            }
            if (i > 0) {
                System.out.printf(" (coin: %d)", coins[i - 1]);
            } else {
                System.out.print(" (no coins)");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        // Example 1
        int amount1 = 5;
        int[] coins1 = {1, 2, 5};
        System.out.println("Example 1:");
        System.out.println("Number of ways: " + change(amount1, coins1));
        printDPTable(amount1, coins1);

        System.out.println("\n-----------------------------------\n");

        // Example 2
        int amount2 = 3;
        int[] coins2 = {2};
        System.out.println("Example 2:");
        System.out.println("Number of ways: " + change(amount2, coins2));
        printDPTable(amount2, coins2);

        System.out.println("\n-----------------------------------\n");

        // Example 3
        int amount3 = 10;
        int[] coins3 = {10};
        System.out.println("Example 3:");
        System.out.println("Number of ways: " + change(amount3, coins3));
        printDPTable(amount3, coins3);
    }
}