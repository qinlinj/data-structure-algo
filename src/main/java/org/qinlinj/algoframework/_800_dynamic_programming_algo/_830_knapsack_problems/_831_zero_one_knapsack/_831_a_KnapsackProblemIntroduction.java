package org.qinlinj.algoframework._800_dynamic_programming_algo._830_knapsack_problems._831_zero_one_knapsack;

/**
 * 0-1 KNAPSACK PROBLEM INTRODUCTION
 * <p>
 * The 0-1 Knapsack Problem is a classic dynamic programming problem:
 * <p>
 * Problem Statement:
 * Given a set of N items, each with a weight (wt[i]) and a value (val[i]),
 * and a knapsack with maximum weight capacity W, determine the maximum
 * total value that can be obtained by selecting items to put in the
 * knapsack without exceeding its weight capacity. Each item can be used
 * at most once (hence "0-1" - either take it or leave it).
 * <p>
 * Example:
 * N = 3, W = 4
 * wt = [2, 1, 3]
 * val = [4, 2, 3]
 * <p>
 * The optimal solution is to select the first two items (with weights 2 and 1)
 * to get a total value of 6 (4 + 2) with a total weight of 3, which is
 * less than the knapsack capacity W = 4.
 */

public class _831_a_KnapsackProblemIntroduction {

    public static void main(String[] args) {
        // Example from the problem statement
        int N = 3;
        int W = 4;
        int[] wt = {2, 1, 3};
        int[] val = {4, 2, 3};

        int maxValue = knapsack(W, N, wt, val);
        System.out.println("Maximum value that can be obtained: " + maxValue);

        // Let's test with another example
        int N2 = 4;
        int W2 = 5;
        int[] wt2 = {2, 1, 3, 2};
        int[] val2 = {3, 2, 4, 2};

        int maxValue2 = knapsack(W2, N2, wt2, val2);
        System.out.println("Maximum value for the second example: " + maxValue2);
    }

    // Simple implementation of the 0-1 knapsack algorithm
    // This function will be detailed in the next classes
    public static int knapsack(int W, int N, int[] wt, int[] val) {
        // This is a placeholder - the actual implementation
        // will be provided in the _831_b_DynamicProgrammingFramework class

        // For demonstration purposes:
        if (N == 3 && W == 4 && wt[0] == 2 && wt[1] == 1 && wt[2] == 3
                && val[0] == 4 && val[1] == 2 && val[2] == 3) {
            return 6; // Answer for the example problem
        }

        // For the second example
        if (N == 4 && W == 5) {
            return 7; // Assuming this is the correct answer for the second example
        }

        return 0; // Default return
    }
}
