package org.qinlinj.algoframework._800_dynamic_programming_algo._830_knapsack_problems._831_zero_one_knapsack;

/**
 * DYNAMIC PROGRAMMING FRAMEWORK FOR 0-1 KNAPSACK PROBLEM
 * <p>
 * This class demonstrates the standard dynamic programming approach to solve the 0-1 Knapsack Problem.
 * <p>
 * Key Concepts:
 * 1. State Definition:
 * - Two states are needed to describe the problem: "remaining capacity of the knapsack" and "items considered so far"
 * <p>
 * 2. Choices for Each State:
 * - For each item, we have two choices: "put it in the knapsack" or "don't put it in the knapsack"
 * <p>
 * 3. DP Array Definition:
 * - dp[i][w] = maximum value that can be obtained using the first i items with knapsack capacity w
 * <p>
 * 4. State Transition Logic:
 * - If we don't include item i: dp[i][w] = dp[i-1][w]
 * - If we include item i: dp[i][w] = val[i-1] + dp[i-1][w-wt[i-1]]
 * - The final value is the maximum of these two possibilities
 * <p>
 * 5. Base Case:
 * - dp[0][w] = 0 (no items means zero value)
 * - dp[i][0] = 0 (zero capacity means zero value)
 */

public class _831_b_DynamicProgrammingFramework {

    public static void main(String[] args) {
        // Example input
        int N = 3;
        int W = 4;
        int[] wt = {2, 1, 3};
        int[] val = {4, 2, 3};

        // Compute and display the result
        int maxValue = knapsack(W, N, wt, val);
        System.out.println("Maximum value: " + maxValue);

        // Demonstrate the DP table construction and decision process
        displayDPTable(W, N, wt, val);
    }

    /**
     * 0-1 Knapsack algorithm implementation using dynamic programming
     *
     * @param W   Maximum weight capacity of the knapsack
     * @param N   Number of items
     * @param wt  Array of item weights
     * @param val Array of item values
     * @return Maximum possible value that can be put in the knapsack
     */
    public static int knapsack(int W, int N, int[] wt, int[] val) {
        // Create a DP table of size (N+1) x (W+1)
        // dp[i][w] represents the maximum value for first i items with capacity w
        int[][] dp = new int[N + 1][W + 1];

        // Base case is implicitly handled because the dp array is initialized with zeros

        // Fill the dp table in bottom-up manner
        for (int i = 1; i <= N; i++) {
            for (int w = 1; w <= W; w++) {
                // If current item's weight exceeds the capacity w
                if (wt[i - 1] > w) {
                    // Can't include this item, so value remains same as without this item
                    dp[i][w] = dp[i - 1][w];
                } else {
                    // Two choices: include the item or exclude it
                    // Take the maximum of:
                    // 1. Value without including current item
                    // 2. Value including current item + maximum value with remaining capacity
                    dp[i][w] = Math.max(
                            dp[i - 1][w],                              // Don't take item i
                            val[i - 1] + dp[i - 1][w - wt[i - 1]]          // Take item i
                    );
                }
            }
        }

        // The final cell contains the maximum value
        return dp[N][W];
    }

    /**
     * Displays the dynamic programming table and explains the decision process
     */
    private static void displayDPTable(int W, int N, int[] wt, int[] val) {
        int[][] dp = new int[N + 1][W + 1];

        System.out.println("\nDP Table Construction:");
        System.out.println("=====================");

        // Initial state
        System.out.println("Initial DP table (all zeros):");
        printDPTable(dp, N, W);

        // Fill the dp table step by step
        for (int i = 1; i <= N; i++) {
            for (int w = 1; w <= W; w++) {
                if (wt[i - 1] > w) {
                    dp[i][w] = dp[i - 1][w];
                    System.out.printf("dp[%d][%d] = dp[%d][%d] = %d (item %d too heavy for capacity %d)\n",
                            i, w, i - 1, w, dp[i][w], i, w);
                } else {
                    int notTake = dp[i - 1][w];
                    int take = val[i - 1] + dp[i - 1][w - wt[i - 1]];
                    dp[i][w] = Math.max(notTake, take);

                    System.out.printf("dp[%d][%d] = max(%d, %d + dp[%d][%d]) = max(%d, %d) = %d\n",
                            i, w, notTake, val[i - 1], i - 1, w - wt[i - 1], notTake, take, dp[i][w]);
                }
            }
            System.out.println("\nDP table after considering item " + i + ":");
            printDPTable(dp, N, W);
        }

        System.out.println("\nFinal result: dp[" + N + "][" + W + "] = " + dp[N][W]);
    }

    /**
     * Helper method to print the DP table
     */
    private static void printDPTable(int[][] dp, int N, int W) {
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
}
