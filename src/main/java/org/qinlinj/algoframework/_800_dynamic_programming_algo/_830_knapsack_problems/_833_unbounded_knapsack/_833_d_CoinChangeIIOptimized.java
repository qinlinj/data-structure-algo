package org.qinlinj.algoframework._800_dynamic_programming_algo._830_knapsack_problems._833_unbounded_knapsack;

/**
 * Optimized Solution for Coin Change II Problem
 * <p>
 * Key Points:
 * - Space-optimized solution using 1D DP array
 * - Time Complexity: O(N * amount) where N is the number of coin denominations
 * - Space Complexity: O(amount) using space compression technique
 * - Observation: dp[i][j] only depends on dp[i-1][j] and dp[i][j-coins[i-1]]
 * - We can use a 1D array and update it in place since each state only depends
 * on values that have already been calculated in the current iteration
 * - This optimization is possible because we process coins in order and amounts from small to large
 */
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

    /**
     * Print helper for the DP array
     */
    private static void printDPArray(int[] dp) {
        System.out.print("[");
        for (int i = 0; i < dp.length; i++) {
            System.out.print(dp[i]);
            if (i < dp.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }

    /**
     * Comparison between 2D and 1D approaches
     */
    public static void compareApproaches() {
        System.out.println("Comparison between 2D and 1D DP Approaches:");
        System.out.println("1. 2D Approach:");
        System.out.println("   - Uses dp[i][j] where i is coin index and j is amount");
        System.out.println("   - Space Complexity: O(N * amount)");
        System.out.println("   - More intuitive to understand the state transitions");
        System.out.println("2. 1D Approach:");
        System.out.println("   - Uses dp[j] where j is amount");
        System.out.println("   - Space Complexity: O(amount)");
        System.out.println("   - Space optimized but may be harder to understand");
        System.out.println("   - Both approaches have the same time complexity: O(N * amount)");
        System.out.println("   - The order of loops is critical for the 1D approach:");
        System.out.println("     * Outer loop: coins");
        System.out.println("     * Inner loop: amounts (from coin value to target amount)");
    }

    public static void main(String[] args) {
        // Example 1
        int amount1 = 5;
        int[] coins1 = {1, 2, 5};
        System.out.println("Example 1:");
        System.out.println("Amount: " + amount1);
        System.out.println("Coins: [1, 2, 5]");
        System.out.println("Number of ways (optimized): " + change(amount1, coins1));
        System.out.println("Expected: 4");
        visualizeOptimizedDP(amount1, coins1);

        System.out.println("\n-----------------------------------\n");

        // Example 2
        int amount2 = 3;
        int[] coins2 = {2};
        System.out.println("Example 2:");
        System.out.println("Amount: " + amount2);
        System.out.println("Coins: [2]");
        System.out.println("Number of ways (optimized): " + change(amount2, coins2));
        System.out.println("Expected: 0");
        visualizeOptimizedDP(amount2, coins2);

        System.out.println("\n-----------------------------------\n");

        compareApproaches();
    }
}