package org.qinlinj.algoframework._200_core_framework._210_greedy_algo_framework;

/**
 * Greedy Choice Property Explanation
 * ==================================
 * <p>
 * This class demonstrates the concept of the "Greedy Choice Property" through examples
 * and implements the Jump Game solution to illustrate greedy algorithms in practice.
 * <p>
 * Key Concepts:
 * <p>
 * 1. Greedy Choice Property:
 * - The ability to determine the global optimum solution through local optimal choices
 * - Allows us to make decisions directly without exploring all possible solutions
 * - Dramatically reduces computational complexity from exponential to linear or constant time
 * <p>
 * 2. Greedy Choice Property vs. Optimal Substructure:
 * - Optimal Substructure: You need solutions to all subproblems before determining the global solution
 * - Greedy Choice Property: You can make locally optimal choices immediately that lead to the global optimum
 * <p>
 * 3. Example Comparison:
 * - Problem 1 (Has Greedy Choice Property):
 * Choose 10 bills of $1 or $100 to maximize total amount
 * Solution: Always choose $100 bills (local optimal = global optimal)
 * <p>
 * - Problem 2 (No Greedy Choice Property):
 * Find minimum number of bills needed to make a specific amount
 * Solution: Local optimal (choose largest bill) doesn't always lead to global optimal
 * (e.g., for amount = 3, the optimal is three $1 bills, not one $100 bill)
 * <p>
 * 4. Jump Game Problem (LeetCode 55):
 * - A practical example of applying the greedy choice property
 * - Instead of examining all possible jump paths (which would be exponential)
 * - We make a greedy choice at each step by tracking the farthest reachable position
 */
public class GreedyChoiceProperty {
    /**
     * Problem 1: Has Greedy Choice Property
     * Maximize total amount with 10 bills of $1 or $100
     */
    public static int maximizeMoney(int n) {
        // Greedy approach: Always choose $100 bills
        return 100 * n; // O(1) solution
    }

    /**
     * Problem 2: No Greedy Choice Property
     * Minimize number of bills to make a specific amount
     * This requires dynamic programming, not greedy algorithm
     */
    public static int minimizeBills(int amount) {
        // Create DP array, initialize with a value larger than any possible solution
        int[] dp = new int[amount + 1];
        for (int i = 0; i <= amount; i++) {
            dp[i] = amount + 1; // Initialize with a value larger than worst case
        }
        dp[0] = 0; // Base case

        int[] coins = {1, 100}; // Available bill denominations

        // Fill the DP table
        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (i - coin >= 0) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }

        return dp[amount] > amount ? -1 : dp[amount]; // Return -1 if solution impossible
    }

}
