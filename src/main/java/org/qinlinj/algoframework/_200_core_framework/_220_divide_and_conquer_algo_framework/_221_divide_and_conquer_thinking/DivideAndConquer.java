package org.qinlinj.algoframework._200_core_framework._220_divide_and_conquer_algo_framework._221_divide_and_conquer_thinking;


/**
 * Divide and Conquer Algorithm
 * <p>
 * This class summarizes the core principles and techniques of divide and conquer algorithms.
 * <p>
 * Key Points:
 * <p>
 * 1. Difference between "Divide and Conquer Thinking" and "Divide and Conquer Algorithm":
 * - Divide and Conquer Thinking is a broad concept often referred to as "problem decomposition approach"
 * - It involves breaking down a problem into smaller sub-problems, solving each sub-problem,
 * and then combining their solutions to solve the original problem
 * - This thinking is widely present in recursive algorithms
 * <p>
 * 2. Examples of Divide and Conquer Thinking:
 * - Fibonacci sequence recursive solution
 * - Binary tree algorithms (e.g., counting nodes)
 * - Dynamic programming algorithms
 * <p>
 * 3. Classification of Recursive Algorithms:
 * - There are two main approaches in recursive algorithms:
 * a) Traversal approach (typical in backtracking algorithms)
 * b) Problem decomposition approach (divide and conquer thinking)
 * - Apart from backtracking algorithms, most recursive algorithms
 * fall under the divide and conquer thinking
 * <p>
 * 4. Formal Divide and Conquer Algorithm:
 * While many algorithms use the divide and conquer thinking, formal "Divide and Conquer Algorithm"
 * refers to a specific class of algorithms with these characteristics:
 * - The problem is divided into completely independent sub-problems
 * - Sub-problems don't overlap (unlike dynamic programming)
 * - Examples include: merge sort, quick sort, binary search
 */
public class DivideAndConquer {
    /**
     * Example 1: Fibonacci sequence using recursive approach
     * This demonstrates divide and conquer thinking by breaking the problem
     * into smaller sub-problems (fib(n-1) and fib(n-2))
     */
    public static int fib(int n) {
        // Base case
        if (n == 0 || n == 1) {
            return n;
        }
        // Divide the problem and combine the results
        return fib(n - 1) + fib(n - 2);
    }
}
