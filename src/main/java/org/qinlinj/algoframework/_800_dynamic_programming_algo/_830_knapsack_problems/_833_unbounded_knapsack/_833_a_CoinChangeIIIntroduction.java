package org.qinlinj.algoframework._800_dynamic_programming_algo._830_knapsack_problems._833_unbounded_knapsack;

/**
 * Introduction to Coin Change II Problem
 * <p>
 * Key Points:
 * - LeetCode problem 518: Coin Change II is a classic dynamic programming problem
 * - It's a variation of the "Complete Knapsack Problem" where items can be used unlimited times
 * - This problem is related to LeetCode 322 (Coin Change I) but with a different objective
 * - The problem asks for the number of different ways to make change for a given amount
 * - It can be viewed as: how many ways can we fill a knapsack of capacity 'amount'
 * using coins of different denominations, where each coin can be used unlimited times
 */
public class _833_a_CoinChangeIIIntroduction {

    /**
     * Problem description:
     * Given an array of coin denominations and a total amount,
     * return the number of ways to make up that amount using the available coins.
     * Assume you have an unlimited number of each coin.
     */
    public static void main(String[] args) {
        // Example 1
        int amount1 = 5;
        int[] coins1 = {1, 2, 5};
        System.out.println("Example 1:");
        System.out.println("Amount: " + amount1);
        System.out.println("Coins: [1, 2, 5]");
        System.out.println("Number of ways: " + change(amount1, coins1));
        System.out.println("Expected: 4");
        System.out.println("Explanation:");
        System.out.println("5 = 5");
        System.out.println("5 = 2+2+1");
        System.out.println("5 = 2+1+1+1");
        System.out.println("5 = 1+1+1+1+1");

        // Example 2
        int amount2 = 3;
        int[] coins2 = {2};
        System.out.println("\nExample 2:");
        System.out.println("Amount: " + amount2);
        System.out.println("Coins: [2]");
        System.out.println("Number of ways: " + change(amount2, coins2));
        System.out.println("Expected: 0");

        // Example 3
        int amount3 = 10;
        int[] coins3 = {10};
        System.out.println("\nExample 3:");
        System.out.println("Amount: " + amount3);
        System.out.println("Coins: [10]");
        System.out.println("Number of ways: " + change(amount3, coins3));
        System.out.println("Expected: 1");
    }

    /**
     * Function signature for the problem:
     * Returns the number of ways to make change for the given amount using the coins provided
     */
    public static int change(int amount, int[] coins) {
        // This is just a placeholder - the actual implementation will be in other classes
        return -1; // Implementation will be provided in subsequent classes
    }
}
