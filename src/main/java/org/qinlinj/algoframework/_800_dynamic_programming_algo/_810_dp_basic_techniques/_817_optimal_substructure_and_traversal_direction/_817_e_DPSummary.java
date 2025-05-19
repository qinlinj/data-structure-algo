package org.qinlinj.algoframework._800_dynamic_programming_algo._810_dp_basic_techniques._817_optimal_substructure_and_traversal_direction;

/**
 * DYNAMIC PROGRAMMING SUMMARY
 * <p>
 * This class summarizes the key concepts from all previous sections and demonstrates
 * a complete example of solving a problem using dynamic programming principles.
 * <p>
 * Key Points from All Sections:
 * <p>
 * 1. OPTIMAL SUBSTRUCTURE:
 * - Problems where optimal solutions can be built from optimal solutions to subproblems
 * - Requires independence of subproblems
 * - Not exclusive to dynamic programming (many problems have this property)
 * <p>
 * 2. OVERLAPPING SUBPROBLEMS:
 * - When the same subproblems are solved repeatedly in recursive approaches
 * - Can be identified by drawing recursion trees or analyzing recursive frameworks
 * - Finding multiple paths to the same state indicates overlapping subproblems
 * <p>
 * 3. DP ARRAY SIZING:
 * - Often use n+1 size to accommodate base cases elegantly
 * - Allows for natural handling of "empty" states without special case logic
 * - Maintains cleaner code and better mapping to the recursive definition
 * <p>
 * 4. DP ARRAY TRAVERSAL:
 * - Direction depends on state dependencies and base case locations
 * - Follow two key principles:
 * a) Calculate states before they're needed by other states
 * b) Ensure final result location is computed by the end of traversal
 * - Can be forward, backward, or diagonal depending on the problem
 * <p>
 * 5. COMPLETE DP PROCESS:
 * 1. Identify optimal substructure and define state
 * 2. Write recursive solution (top-down) with mathematical formulation
 * 3. Check for overlapping subproblems by analyzing state transitions
 * 4. Optimize using memoization (top-down) or tabulation (bottom-up)
 * 5. Determine appropriate DP array size and traversal direction
 */
public class _817_e_DPSummary {

    /**
     * Complete Example: Coin Change Problem
     *
     * Problem: Given coins of different denominations and a total amount,
     * find the minimum number of coins needed to make up that amount.
     * If the amount cannot be made up, return -1.
     *
     * This example demonstrates all DP concepts:
     * 1. Optimal substructure
     * 2. Overlapping subproblems
     * 3. DP array sizing
     * 4. DP array traversal
     */

    /**
     * Step 1: Recursive solution (top-down)
     * - State: dp(amount) = minimum coins to make up 'amount'
     * - Recurrence: dp(amount) = 1 + min(dp(amount - coin)) for all coins
     */
    public static int coinChangeRecursive(int[] coins, int amount) {
        // Base case
        if (amount == 0) return 0;
        if (amount < 0) return -1;

        int minCoins = Integer.MAX_VALUE;

        // Try each coin and take the minimum
        for (int coin : coins) {
            int subproblem = coinChangeRecursive(coins, amount - coin);
            // Only consider valid solutions
            if (subproblem != -1) {
                minCoins = Math.min(minCoins, subproblem + 1);
            }
        }

        return minCoins == Integer.MAX_VALUE ? -1 : minCoins;
    }

    /**
     * Step 2: Memoization (top-down with cache)
     * - Same recurrence relation but with cache to avoid recomputing
     */
    public static int coinChangeMemo(int[] coins, int amount) {
        // Initialize memo array with -2 (different from -1 return value)
        int[] memo = new int[amount + 1];
        for (int i = 0; i <= amount; i++) {
            memo[i] = -2;
        }

        return coinChangeMemoHelper(coins, amount, memo);
    }

    private static int coinChangeMemoHelper(int[] coins, int amount, int[] memo) {
        // Check memo
        if (memo[amount] != -2) return memo[amount];

        // Base case
        if (amount == 0) return 0;
        if (amount < 0) return -1;

        int minCoins = Integer.MAX_VALUE;

        // Try each coin
        for (int coin : coins) {
            int subproblem = coinChangeMemoHelper(coins, amount - coin, memo);
            if (subproblem != -1) {
                minCoins = Math.min(minCoins, subproblem + 1);
            }
        }

        // Save to memo before returning
        memo[amount] = minCoins == Integer.MAX_VALUE ? -1 : minCoins;
        return memo[amount];
    }

    /**
     * Step 3: Tabulation (bottom-up)
     * - Build DP array from base case
     * - Size dp[amount+1] to include the base case dp[0]=0
     * - Forward traversal because dp[i] depends on dp[i-coin]
     */
    public static int coinChangeDP(int[] coins, int amount) {
        // Initialize DP array with amount+1 (greater than max possible coins)
        int[] dp = new int[amount + 1];
        for (int i = 1; i <= amount; i++) {
            dp[i] = amount + 1; // Value greater than any possible solution
        }

        // Base case
        dp[0] = 0;

        // Forward traversal
        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (coin <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }

        return dp[amount] > amount ? -1 : dp[amount];
    }

    /**
     * Demonstrates how this problem satisfies DP requirements
     */
    public static void analyzeCoinChangeProblem() {
        System.out.println("COIN CHANGE PROBLEM ANALYSIS");

        System.out.println("\n1. Optimal Substructure:");
        System.out.println("   - The minimum coins needed for amount X depends on");
        System.out.println("     the minimum coins needed for smaller amounts (X - coin)");
        System.out.println("   - Formula: minCoins(X) = 1 + min(minCoins(X - coin)) for all coins");

        System.out.println("\n2. Overlapping Subproblems:");
        System.out.println("   - For amount = 11 with coins [1,2,5]:");
        System.out.println("     * minCoins(11) needs minCoins(10), minCoins(9), minCoins(6)");
        System.out.println("     * minCoins(10) needs minCoins(9), minCoins(8), minCoins(5)");
        System.out.println("     * minCoins(9) is computed multiple times in different paths");
        System.out.println("   - The recursive framework shows multiple paths to the same states");

        System.out.println("\n3. DP Array Sizing:");
        System.out.println("   - We use dp[amount+1] to include the base case dp[0]=0");
        System.out.println("   - This maps naturally to the recursive definition");

        System.out.println("\n4. DP Array Traversal:");
        System.out.println("   - We use forward traversal (0 to amount)");
        System.out.println("   - This works because dp[i] depends on dp[i-coin]");
        System.out.println("   - The dependency is on smaller values, so we calculate those first");
    }

    public static void main(String[] args) {
        int[] coins = {1, 2, 5};
        int amount = 11;

        System.out.println("Coin Change Problem for amount " + amount + " with coins " + java.util.Arrays.toString(coins));

        // Step 1: Recursive (will be slow for larger amounts due to overlapping subproblems)
        long startTime = System.nanoTime();
        int recursiveResult = coinChangeRecursive(coins, amount);
        long endTime = System.nanoTime();
        System.out.println("\nRecursive Solution: " + recursiveResult);
        System.out.println("Time taken: " + (endTime - startTime) / 1000000.0 + " ms");

        // Step 2: Memoization
        startTime = System.nanoTime();
        int memoResult = coinChangeMemo(coins, amount);
        endTime = System.nanoTime();
        System.out.println("\nMemoization Solution: " + memoResult);
        System.out.println("Time taken: " + (endTime - startTime) / 1000000.0 + " ms");

        // Step 3: Tabulation
        startTime = System.nanoTime();
        int dpResult = coinChangeDP(coins, amount);
        endTime = System.nanoTime();
        System.out.println("\nDP Solution: " + dpResult);
        System.out.println("Time taken: " + (endTime - startTime) / 1000000.0 + " ms");

        // Analysis
        System.out.println("\n");
        analyzeCoinChangeProblem();
    }
}