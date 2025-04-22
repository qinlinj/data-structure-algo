package org.qinlinj.algoframework._200_other_algo_framework._210_greedy_algo_framework;

/**
 * Greedy Algorithm Explanation
 * ===========================
 * <p>
 * This class demonstrates the concept of greedy algorithms through a simple example:
 * <p>
 * Problem: You can choose 10 bills, each either worth $1 or $100, to maximize the total amount.
 * <p>
 * Key Insights:
 * <p>
 * 1. The Essence of Algorithms is Exhaustive Enumeration:
 * - At its core, all algorithms involve exploring possible solutions.
 * - For this problem, we have 2^10 possible combinations (choosing either $1 or $100 for each position).
 * <p>
 * 2. Recursive Thinking:
 * - The problem can be visualized as a binary tree with height 10.
 * - Each node represents a choice between $1 and $100.
 * - The complete tree would represent all possible combinations.
 * <p>
 * 3. Greedy Choice Property:
 * - In this problem, the optimal strategy is always to choose the higher denomination ($100).
 * - This "greedy" choice at each step leads to the globally optimal solution.
 * <p>
 * 4. Optimization Path:
 * - We start with a recursive solution (exponential complexity: O(2^n))
 * - Then optimize by recognizing we always choose $100 (linear complexity: O(n))
 * - Finally optimize to a direct calculation (constant complexity: O(1))
 * <p>
 * 5. Greedy Algorithm Essence:
 * - Make the locally optimal choice at each step.
 * - In appropriate problems, this leads to the globally optimal solution.
 * - This dramatically reduces computational complexity.
 * <p>
 * Note: Not all problems can be solved optimally with greedy algorithms.
 * The problem must have the "greedy choice property" where local optimal
 * choices lead to a global optimum.
 */
public class GreedyAlgorithmFramework {
    /**
     * Initial recursive approach - exponential complexity O(2^n)
     * This represents the exhaustive enumeration approach
     */
    public static int findMaxRecursive(int n) {
        if (n == 0) return 0;

        // Choose $1 bill for this position, then solve for remaining positions
        int result1 = 1 + findMaxRecursive(n - 1);

        // Choose $100 bill for this position, then solve for remaining positions
        int result2 = 100 + findMaxRecursive(n - 1);

        // Return the better of the two choices
        return Math.max(result1, result2);
    }

    /**
     * Optimization 1: Eliminate unnecessary comparison
     * Since we know choosing $100 is always better than choosing $1
     */
    public static int findMaxOptimized1(int n) {
        if (n == 0) return 0;
        return 100 + findMaxOptimized1(n - 1);
    }

    /**
     * Optimization 2: Convert recursion to iteration
     * Reduces call stack overhead and improves readability
     */
    public static int findMaxOptimized2(int n) {
        int result = 0;
        for (int i = 0; i < n; i++) {
            result += 100;
        }
        return result;
    }

    /**
     * Optimization 3: Direct calculation
     * Recognizing the pattern allows constant time complexity O(1)
     */
    public static int findMaxOptimized3(int n) {
        return 100 * n;
    }

    /**
     * A more general method that works with any two denominations
     */
    public static int findMaxGeneral(int n, int value1, int value2) {
        // In a greedy approach, always choose the larger value
        int largerValue = Math.max(value1, value2);
        return largerValue * n;
    }

    /**
     * Demonstrates how the complexity decreases with each optimization
     */
    public static void main(String[] args) {
        int n = 10; // Number of bills to select

        System.out.println("Problem: Select " + n + " bills of either $1 or $100 to maximize total amount");

        // Original recursive (exponential) approach
        // Warning: This would be very slow for large n due to exponential complexity
        if (n <= 20) { // Only run for small n to avoid stack overflow
            System.out.println("\nApproach 1 (Recursive - O(2^n))");
            long startTime = System.nanoTime();
            int maxAmount1 = findMaxRecursive(n);
            long endTime = System.nanoTime();
            System.out.println("Maximum amount: $" + maxAmount1);
            System.out.println("Execution time: " + (endTime - startTime) / 1000000.0 + " ms");
        } else {
            System.out.println("\nApproach 1 (Recursive - O(2^n))");
            System.out.println("Skipped due to large n (would cause stack overflow)");
        }

        // Optimized recursive approach
        System.out.println("\nApproach 2 (Optimized Recursive - O(n))");
        long startTime = System.nanoTime();
        int maxAmount2 = findMaxOptimized1(n);
        long endTime = System.nanoTime();
        System.out.println("Maximum amount: $" + maxAmount2);
        System.out.println("Execution time: " + (endTime - startTime) / 1000000.0 + " ms");

        // Iterative approach
        System.out.println("\nApproach 3 (Iterative - O(n))");
        startTime = System.nanoTime();
        int maxAmount3 = findMaxOptimized2(n);
        endTime = System.nanoTime();
        System.out.println("Maximum amount: $" + maxAmount3);
        System.out.println("Execution time: " + (endTime - startTime) / 1000000.0 + " ms");

        // Direct calculation approach
        System.out.println("\nApproach 4 (Direct Calculation - O(1))");
        startTime = System.nanoTime();
        int maxAmount4 = findMaxOptimized3(n);
        endTime = System.nanoTime();
        System.out.println("Maximum amount: $" + maxAmount4);
        System.out.println("Execution time: " + (endTime - startTime) / 1000000.0 + " ms");

        System.out.println("\nConclusion: The greedy approach dramatically reduced the complexity from O(2^n) to O(1)");
        System.out.println("This is the power of identifying the greedy choice property in a problem!");
    }
}
