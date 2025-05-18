package org.qinlinj.algoframework._800_dynamic_programming_algo._810_dp_basic_techniques._814_dp_enumeration_perspectives;

/**
 * Main Class to Demonstrate Dynamic Programming Concepts
 * <p>
 * Summary of Dynamic Programming Concepts from All Classes:
 * <p>
 * 1. Core Principles:
 * - DP is about exhaustive enumeration of all possible solutions
 * - Key challenges: elimination of redundancy, state transition formulation, perspective choice
 * - Mathematical induction helps derive state transitions
 * <p>
 * 2. The Ball-Box Model:
 * - An abstract model for permutation and combination problems
 * - Different enumeration perspectives: box selecting balls vs. balls selecting boxes
 * - Same mathematical result but potentially different algorithmic efficiency
 * <p>
 * 3. Distinct Subsequences Problem:
 * - Demonstrated two different approaches for the same problem:
 * a) T-perspective: O(N*M²) time complexity
 * b) S-perspective: O(M*N) time complexity
 * - Both are correct but with significant performance differences
 * <p>
 * 4. Broader Applications:
 * - Many DP problems can be approached from multiple perspectives
 * - String matching, subsequence, subset problems are prime candidates
 * - The best perspective typically minimizes nested loops
 * - Choosing the right perspective can reduce time complexity significantly
 * <p>
 * General Guideline: When solving DP problems, try to identify if multiple enumeration
 * perspectives are possible, and analyze which one leads to a more efficient algorithm.
 */
public class _814_e_DynamicProgrammingMain {

    /**
     * Demonstrates all concepts through a simple example: 0-1 Knapsack problem
     * Solved using two different perspectives
     */
    public static void main(String[] args) {
        System.out.println("Dynamic Programming with Different Enumeration Perspectives\n");

        // 0-1 Knapsack Problem Example
        int[] values = {60, 100, 120};
        int[] weights = {10, 20, 30};
        int capacity = 50;

        System.out.println("0-1 Knapsack Problem:");
        System.out.println("Values: [60, 100, 120]");
        System.out.println("Weights: [10, 20, 30]");
        System.out.println("Capacity: 50\n");

        // Solve using item perspective
        int resultItemPerspective = knapsackItemPerspective(values, weights, capacity);
        System.out.println("Result using item perspective: " + resultItemPerspective);

        // Solve using capacity perspective
        int resultCapacityPerspective = knapsackCapacityPerspective(values, weights, capacity);
        System.out.println("Result using capacity perspective: " + resultCapacityPerspective);

        System.out.println("\nBoth approaches yield the same result, but with different implementations.");
        System.out.println("The item perspective is generally more efficient for the knapsack problem.");

        // Summary
        System.out.println("\nSummary:");
        System.out.println("1. Dynamic programming requires proper problem decomposition");
        System.out.println("2. Different enumeration perspectives can lead to different implementations");
        System.out.println("3. All valid perspectives produce correct results");
        System.out.println("4. The choice of perspective can significantly impact algorithm efficiency");
        System.out.println("5. Identify problems that can be modeled as the 'ball-box' problem");
        System.out.println("6. Choose the perspective that minimizes nested loops and complexity");
    }

    /**
     * Knapsack solution from the item perspective
     * For each item, decide whether to include it or not
     */
    public static int knapsackItemPerspective(int[] values, int[] weights, int capacity) {
        int n = values.length;
        // dp[i][w] = maximum value that can be obtained using first i items with capacity w
        int[][] dp = new int[n + 1][capacity + 1];

        for (int i = 1; i <= n; i++) {
            for (int w = 0; w <= capacity; w++) {
                // Don't take item i
                dp[i][w] = dp[i - 1][w];

                // Take item i if it fits
                if (w >= weights[i - 1]) {
                    dp[i][w] = Math.max(dp[i][w],
                            dp[i - 1][w - weights[i - 1]] + values[i - 1]);
                }
            }
        }

        return dp[n][capacity];
    }

    /**
     * Knapsack solution from the capacity perspective
     * For each capacity, find the best combination of items
     * <p>
     * Note: This is not actually the most efficient perspective for knapsack,
     * but is shown here to illustrate the concept of different perspectives
     */
    public static int knapsackCapacityPerspective(int[] values, int[] weights, int capacity) {
        // Using a recursive approach with memoization for this perspective
        Integer[][] memo = new Integer[capacity + 1][values.length];
        return knapsackHelper(values, weights, capacity, 0, memo);
    }

    private static int knapsackHelper(int[] values, int[] weights, int capacity,
                                      int index, Integer[][] memo) {
        // Base cases
        if (capacity <= 0 || index >= values.length) {
            return 0;
        }

        // Check memo
        if (memo[capacity][index] != null) {
            return memo[capacity][index];
        }

        int result = 0;

        // Try all items that fit the current capacity
        for (int i = index; i < values.length; i++) {
            if (weights[i] <= capacity) {
                // Take item i
                result = Math.max(result,
                        values[i] + knapsackHelper(values, weights, capacity - weights[i], i + 1, memo));
            }
        }

        // Also consider not taking any more items
        result = Math.max(result, knapsackHelper(values, weights, 0, values.length, memo));

        // Save to memo
        memo[capacity][index] = result;
        return result;
    }
}
