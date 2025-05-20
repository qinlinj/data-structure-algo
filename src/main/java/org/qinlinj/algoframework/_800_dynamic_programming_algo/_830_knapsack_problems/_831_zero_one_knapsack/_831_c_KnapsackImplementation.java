package org.qinlinj.algoframework._800_dynamic_programming_algo._830_knapsack_problems._831_zero_one_knapsack;

/**
 * COMPLETE 0-1 KNAPSACK PROBLEM IMPLEMENTATION
 * <p>
 * This class provides the complete implementation of the 0-1 Knapsack algorithm
 * with detailed explanation of each step.
 * <p>
 * Key Implementation Details:
 * <p>
 * 1. Full Dynamic Programming Solution:
 * - Uses a 2D array dp[N+1][W+1] to store intermediate results
 * <p>
 * 2. State Transition Equation:
 * - If item doesn't fit (w < wt[i-1]): dp[i][w] = dp[i-1][w]
 * - If item fits: dp[i][w] = max(dp[i-1][w], val[i-1] + dp[i-1][w-wt[i-1]])
 * <p>
 * 3. Time Complexity: O(N*W) where N is the number of items and W is the knapsack capacity
 * <p>
 * 4. Space Complexity: O(N*W) for the DP table
 * <p>
 * 5. Edge Case Handling:
 * - Handles boundary conditions for when items don't fit in the knapsack
 * - Properly initializes the base cases
 */

public class _831_c_KnapsackImplementation {

    public static void main(String[] args) {
        // Example from the problem statement
        int N = 3;
        int W = 4;
        int[] wt = {2, 1, 3};
        int[] val = {4, 2, 3};

        // Solve using the knapsack algorithm
        int maxValue = knapsack(W, N, wt, val);
        System.out.println("Maximum value that can be obtained: " + maxValue);

        // Trace the selected items for the optimal solution
        System.out.println("\nItems selected for the optimal solution:");
        traceSelectedItems(W, N, wt, val);
    }

    /**
     * Solves the 0-1 Knapsack Problem using dynamic programming.
     *
     * @param W   Maximum weight capacity of the knapsack
     * @param N   Number of items available
     * @param wt  Array of item weights
     * @param val Array of item values
     * @return Maximum possible value that can be put in the knapsack
     */
    public static int knapsack(int W, int N, int[] wt, int[] val) {
        // Validate input
        assert N == wt.length && N == val.length : "Number of weights and values must match N";

        // Create DP table: dp[i][w] = max value with first i items and capacity w
        int[][] dp = new int[N + 1][W + 1];

        // Base case is implicitly initialized (dp[0][..] = 0 and dp[..][0] = 0)

        // Fill the DP table in bottom-up manner
        for (int i = 1; i <= N; i++) {
            for (int w = 1; w <= W; w++) {
                // If current item weight is more than capacity w, we can't include it
                if (wt[i - 1] > w) {
                    // We inherit the value without considering this item
                    dp[i][w] = dp[i - 1][w];
                } else {
                    // We have two choices: include or exclude the current item
                    // 1. If we exclude: dp[i-1][w]
                    // 2. If we include: val[i-1] + dp[i-1][w-wt[i-1]]
                    // Take the maximum of these two options
                    dp[i][w] = Math.max(
                            dp[i - 1][w],  // Exclude current item
                            val[i - 1] + dp[i - 1][w - wt[i - 1]]  // Include current item
                    );
                }
            }
        }

        // Print the DP table for visualization
        printDPTable(dp, N, W);

        // Return the maximum value that can be obtained
        return dp[N][W];
    }

    /**
     * Traces back through the DP table to find which items are selected
     * in the optimal solution.
     */
    public static void traceSelectedItems(int W, int N, int[] wt, int[] val) {
        int[][] dp = new int[N + 1][W + 1];

        // Fill the DP table first
        for (int i = 1; i <= N; i++) {
            for (int w = 1; w <= W; w++) {
                if (wt[i - 1] > w) {
                    dp[i][w] = dp[i - 1][w];
                } else {
                    dp[i][w] = Math.max(
                            dp[i - 1][w],
                            val[i - 1] + dp[i - 1][w - wt[i - 1]]
                    );
                }
            }
        }

        // Trace back to find selected items
        boolean[] selected = new boolean[N];
        int remainingW = W;

        for (int i = N; i > 0; i--) {
            // If value came from including the item
            if (dp[i][remainingW] != dp[i - 1][remainingW]) {
                selected[i - 1] = true;  // Item i-1 is selected
                remainingW -= wt[i - 1]; // Reduce the remaining capacity
            }
        }

        // Print selected items
        int totalWeight = 0;
        int totalValue = 0;

        for (int i = 0; i < N; i++) {
            if (selected[i]) {
                System.out.printf("Item %d: Weight = %d, Value = %d\n",
                        i + 1, wt[i], val[i]);
                totalWeight += wt[i];
                totalValue += val[i];
            }
        }

        System.out.println("\nTotal weight: " + totalWeight);
        System.out.println("Total value: " + totalValue);
    }

    /**
     * Helper method to print the DP table for visualization
     */
    private static void printDPTable(int[][] dp, int N, int W) {
        System.out.println("\nDP Table:");
        System.out.print("    ");
        for (int w = 0; w <= W; w++) {
            System.out.printf("%2d ", w);
        }
        System.out.println("\n    " + "---".repeat(W + 1));

        for (int i = 0; i <= N; i++) {
            System.out.printf("%2d | ", i);
            for (int w = 0; w <= W; w++) {
                System.out.printf("%2d ", dp[i][w]);
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     * Alternative implementation with additional explanatory comments
     * demonstrating the step-by-step logic for each cell in the DP table
     */
    public static int knapsackWithExplanation(int W, int N, int[] wt, int[] val) {
        int[][] dp = new int[N + 1][W + 1];

        for (int i = 1; i <= N; i++) {
            for (int w = 1; w <= W; w++) {
                System.out.printf("Considering item %d (weight=%d, value=%d) with capacity %d:\n",
                        i, wt[i - 1], val[i - 1], w);

                if (wt[i - 1] > w) {
                    dp[i][w] = dp[i - 1][w];
                    System.out.printf("  Item is too heavy, can't include. dp[%d][%d] = dp[%d][%d] = %d\n",
                            i, w, i - 1, w, dp[i][w]);
                } else {
                    int excludeItem = dp[i - 1][w];
                    int includeItem = val[i - 1] + dp[i - 1][w - wt[i - 1]];

                    System.out.printf("  Option 1: Exclude item %d --> dp[%d][%d] = %d\n",
                            i, i - 1, w, excludeItem);
                    System.out.printf("  Option 2: Include item %d --> val[%d] + dp[%d][%d] = %d + %d = %d\n",
                            i, i - 1, i - 1, w - wt[i - 1], val[i - 1], dp[i - 1][w - wt[i - 1]], includeItem);

                    dp[i][w] = Math.max(excludeItem, includeItem);
                    System.out.printf("  Taking maximum: dp[%d][%d] = max(%d, %d) = %d\n",
                            i, w, excludeItem, includeItem, dp[i][w]);
                }
                System.out.println();
            }
        }

        return dp[N][W];
    }
}
