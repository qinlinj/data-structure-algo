package org.qinlinj.algoframework._800_dynamic_programming_algo._830_knapsack_problems._833_unbounded_knapsack;

public class _833_d_CoinChangeIIOptimized {
    /**
     * Space-optimized solution for the Coin Change II problem using 1D DP array
     *
     * @param amount the target amount
     * @param coins  array of available coin denominations
     * @return the number of ways to make change for the amount
     */
    public static int change(int amount, int[] coins) {
        // Create a 1D DP array
        int[] dp = new int[amount + 1];

        // Base case: one way to make zero amount (use no coins)
        dp[0] = 1;

        // For each coin, update the dp array
        for (int coin : coins) {
            // Update dp[j] for each amount j that can use this coin
            for (int j = coin; j <= amount; j++) {
                // Add the number of ways to make amount j - coin
                dp[j] += dp[j - coin];
            }
        }

        return dp[amount];
    }

    /**
     * Helper method to visualize the DP array updates for each coin
     */
    public static void visualizeOptimizedDP(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;

        System.out.println("Visualization of 1D DP array updates:");
        System.out.print("Initial: ");
        printDPArray(dp);

        for (int i = 0; i < coins.length; i++) {
            int coin = coins[i];

            // Update dp array for this coin
            for (int j = coin; j <= amount; j++) {
                dp[j] += dp[j - coin];
            }

            System.out.print("After using coin " + coin + ": ");
            printDPArray(dp);
        }

        System.out.println("Final result: " + dp[amount]);
    }
}
