package org.qinlinj.algoframework._100_algo_core_framework._150_dynamic_programming_framework._151_fibonacci_sequence;

// @formatter:off
/**
 * Dynamic Programming with Memoization
 * ------------------------------------
 *
 * This class demonstrates the power of memoization in dynamic programming,
 * particularly focusing on top-down recursive approaches.
 *
 * Key concepts:
 * 1. Memoization is a technique to avoid redundant calculations by storing previously
 *    computed results in a "memo" data structure (often an array or hash map)
 * 2. It transforms an exponential time recursive algorithm into a linear time algorithm
 * 3. It follows a "top-down" approach: starting from the original problem and breaking it down
 * 4. Memoization maintains the recursive structure while eliminating redundant calculations
 *
 * Contrast with bottom-up DP:
 * - Top-down (memoization): Recursive, starts with the original problem, caches results
 * - Bottom-up (tabulation): Iterative, starts with base cases, builds up to the solution
 */
public class _151_b_DynamicProgrammingMemoization {
    /**
     * Generic template for memoized dynamic programming:
     *
     * 1. Define the recursive function with state parameters
     * 2. Initialize a memoization data structure
     * 3. Check base cases
     * 4. Check if the result is already in memo, return it if so
     * 5. Calculate the result recursively
     * 6. Store the result in memo before returning
     *
     * Example template:
     *
     * public int solve(InputType input) {
     *     // Initialize memoization structure
     *     Map<State, Result> memo = new HashMap<>();
     *     // Call recursive helper
     *     return dp(input, initialState, memo);
     * }
     *
     * private int dp(InputType input, State state, Map<State, Result> memo) {
     *     // Base case
     *     if (isBaseCase(state)) return baseValue;
     *
     *     // Check memoization
     *     if (memo.containsKey(state)) return memo.get(state);
     *
     *     // Calculate result
     *     Result result = initialValue;
     *     for (Choice choice : possibleChoices(state)) {
     *         State nextState = applyChoice(state, choice);
     *         Result nextResult = dp(input, nextState, memo);
     *         result = getBetterResult(result, nextResult);
     *     }
     *
     *     // Memoize and return
     *     memo.put(state, result);
     *     return result;
     * }
     */
    public static void main(String[] args) {
        _151_b_DynamicProgrammingMemoization dp = new _151_b_DynamicProgrammingMemoization();

        // Test Fibonacci
        int n = 30;
        long start = System.nanoTime();
        int fibResult = dp.fibonacci(n);
        long end = System.nanoTime();
        System.out.println("Fibonacci(" + n + ") = " + fibResult);
        System.out.println("Time taken (ms): " + (end - start) / 1_000_000.0);

        // Test coin change
        int[] coins = {1, 2, 5};
        int amount = 11;
        System.out.println("Minimum coins needed for amount " + amount + ": " +
                dp.coinChange(coins, amount));
    }

    /**
     * Example: Fibonacci sequence with memoization
     * <p>
     * This implementation demonstrates how a memoized solution transforms the
     * recursive tree into a recursive graph, eliminating redundant calculations.
     * <p>
     * Time complexity: O(n) - each subproblem computed exactly once
     * Space complexity: O(n) - for the memoization array and recursion stack
     */
    public int fibonacci(int n) {
        // Initialize memo array with zeros
        int[] memo = new int[n + 1];
        return fibMemoized(memo, n);
    }

    /**
     * Visualization of memoization benefit:
     *
     * Without memoization (exponential time):
     *                 f(5)
     *               /     \
     *          f(4)         f(3)
     *        /     \       /    \
     *     f(3)     f(2)  f(2)    f(1)
     *    /   \    /   \  /  \
     * f(2)  f(1) f(1) f(0) f(1) f(0)
     * /  \
     *f(1) f(0)
     *
     * With memoization (linear time):
     *                 f(5)
     *               /     \
     *          f(4)         f(3)*
     *        /     \
     *     f(3)     f(2)*
     *    /   \
     * f(2)  f(1)*
     * /  \
     *f(1) f(0)*
     *
     * (* = already computed, retrieved from memo)
     */

    private int fibMemoized(int[] memo, int n) {
        // Base cases
        if (n == 0 || n == 1) return n;

        // Check if we've already calculated this value
        if (memo[n] != 0) return memo[n];

        // Calculate and store the result
        memo[n] = fibMemoized(memo, n - 1) + fibMemoized(memo, n - 2);
        return memo[n];
    }

    /**
     * Example 2: Coin change problem with memoization
     *
     * This demonstrates how the same problem can be solved with
     * a top-down memoized approach.
     */
    public int coinChange(int[] coins, int amount) {
        // Initialize memo array with -1 (indicating not calculated yet)
        int[] memo = new int[amount + 1];
        java.util.Arrays.fill(memo, -1);

        return coinChangeMemoized(coins, amount, memo);
    }

    private int coinChangeMemoized(int[] coins, int amount, int[] memo) {
        // Base case
        if (amount == 0) return 0;
        if (amount < 0) return -1;

        // Check if we've already calculated this value
        if (memo[amount] != -1) return memo[amount];

        // Initialize with a value greater than any possible answer
        int minCoins = Integer.MAX_VALUE;

        // Try each coin
        for (int coin : coins) {
            int result = coinChangeMemoized(coins, amount - coin, memo);

            // If it's possible to make change with this coin
            if (result >= 0) {
                minCoins = Math.min(minCoins, result + 1);
            }
        }

        // Store the result in memo
        memo[amount] = (minCoins == Integer.MAX_VALUE) ? -1 : minCoins;
        return memo[amount];
    }
}
