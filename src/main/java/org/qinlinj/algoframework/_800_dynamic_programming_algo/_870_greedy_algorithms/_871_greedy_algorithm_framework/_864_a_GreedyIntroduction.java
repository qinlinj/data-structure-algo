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
}
