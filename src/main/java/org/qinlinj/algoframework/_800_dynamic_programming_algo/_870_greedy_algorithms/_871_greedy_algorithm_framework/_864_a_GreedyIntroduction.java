package org.qinlinj.algoframework._800_dynamic_programming_algo._870_greedy_algorithms._871_greedy_algorithm_framework;

/**
 * GREEDY ALGORITHM INTRODUCTION - BASIC EXAMPLE
 * <p>
 * Key Concepts:
 * 1. Greedy Algorithm Definition: Makes locally optimal choices at each step
 * 2. Algorithm Evolution: From brute force O(2^n) to optimized O(1)
 * 3. Core Principle: All algorithms are essentially exhaustive search with optimizations
 * 4. Problem: Given unlimited 1-yuan and 100-yuan bills, choose 10 bills to maximize total value
 * 5. Solution Evolution:
 * - Brute Force: Enumerate all 2^10 = 1024 possibilities
 * - Optimization 1: Recognize 100 > 1, always choose 100-yuan bills
 * - Optimization 2: Convert recursion to iteration
 * - Optimization 3: Direct calculation
 * <p>
 * Time Complexity Evolution: O(2^n) → O(n) → O(1)
 * <p>
 * This demonstrates how greedy algorithms can dramatically improve efficiency
 * when the problem has greedy choice property.
 */

public class _864_a_GreedyIntroduction {

    /**
     * Brute force approach - explores all possible combinations
     * Time Complexity: O(2^n)
     * Space Complexity: O(n) due to recursion stack
     */
    public static int findMaxBruteForce(int n) {
        if (n == 0) return 0;

        // Choose 1-yuan bill, then solve remaining n-1 choices
        int result1 = 1 + findMaxBruteForce(n - 1);
        // Choose 100-yuan bill, then solve remaining n-1 choices
        int result2 = 100 + findMaxBruteForce(n - 1);

        // Return maximum of two choices
        return Math.max(result1, result2);
    }

    /**
     * Optimization 1: No need to compare since 100 > 1
     * Time Complexity: O(n)
     * Space Complexity: O(n) due to recursion
     */
    public static int findMaxOptimized1(int n) {
        if (n == 0) return 0;
        // Always choose 100-yuan bill
        return 100 + findMaxOptimized1(n - 1);
    }

    /**
     * Optimization 2: Convert recursion to iteration
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public static int findMaxOptimized2(int n) {
        int result = 0;
        for (int i = 0; i < n; i++) {
            result += 100;
        }
        return result;
    }

    /**
     * Optimization 3: Direct calculation (Greedy Algorithm)
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    public static int findMaxGreedy(int n) {
        return 100 * n;
    }

    /**
     * Demonstration of the algorithm evolution
     */
    public static void demonstrateEvolution() {
        int n = 10;

        System.out.println("=== Greedy Algorithm Evolution Demo ===");
        System.out.println("Problem: Choose " + n + " bills to maximize value");
        System.out.println("Available: 1-yuan and 100-yuan bills (unlimited quantity)");
        System.out.println();

        long startTime, endTime;

        // Brute Force (only for small n due to exponential complexity)
        if (n <= 20) {
            startTime = System.nanoTime();
            int result1 = findMaxBruteForce(n);
            endTime = System.nanoTime();
            System.out.println("1. Brute Force Result: " + result1 +
                    " yuan (Time: " + (endTime - startTime) / 1000.0 + " microseconds)");
        } else {
            System.out.println("1. Brute Force: Skipped (too slow for n=" + n + ")");
        }

        // Optimized Recursion
        startTime = System.nanoTime();
        int result2 = findMaxOptimized1(n);
        endTime = System.nanoTime();
        System.out.println("2. Optimized Recursion: " + result2 +
                " yuan (Time: " + (endTime - startTime) / 1000.0 + " microseconds)");

        // Iterative
        startTime = System.nanoTime();
        int result3 = findMaxOptimized2(n);
        endTime = System.nanoTime();
        System.out.println("3. Iterative Solution: " + result3 +
                " yuan (Time: " + (endTime - startTime) / 1000.0 + " microseconds)");

        // Greedy
        startTime = System.nanoTime();
        int result4 = findMaxGreedy(n);
        endTime = System.nanoTime();
        System.out.println("4. Greedy Algorithm: " + result4 +
                " yuan (Time: " + (endTime - startTime) / 1000.0 + " microseconds)");

        System.out.println();
        System.out.println("All methods produce the same result: " +
                (result2 == result3 && result3 == result4));
        System.out.println("Optimal Strategy: Always choose 100-yuan bills");
        System.out.println("Complexity reduced from O(2^n) to O(1)!");
    }

    public static void main(String[] args) {
        demonstrateEvolution();

        System.out.println("\n=== Testing with Different Values ===");
        int[] testCases = {1, 5, 10, 15, 20};

        for (int n : testCases) {
            int maxValue = findMaxGreedy(n);
            System.out.println("Choosing " + n + " bills: Maximum value = " + maxValue + " yuan");
        }

        System.out.println("\n=== Key Insight ===");
        System.out.println("This problem has GREEDY CHOICE PROPERTY:");
        System.out.println("- Local optimal choice: Always pick 100-yuan bill (100 > 1)");
        System.out.println("- Global optimal solution: Sum of all local optimal choices");
        System.out.println("- No need to explore all possibilities!");
    }
}