package org.qinlinj.algoframework._800_dynamic_programming_algo._810_dp_basic_techniques._811_dp_framework;

/**
 * Fibonacci Sequence Using Dynamic Programming
 * ============================================
 * <p>
 * The Fibonacci sequence is a classic example used to demonstrate dynamic programming concepts.
 * While not a true DP problem (as it doesn't involve finding an optimal value), it perfectly
 * illustrates the concept of overlapping subproblems.
 * <p>
 * This class implements four approaches to calculate the nth Fibonacci number:
 * 1. Naive Recursive Solution - O(2^n) time complexity
 * 2. Top-down with Memoization - O(n) time, O(n) space
 * 3. Bottom-up with Tabulation - O(n) time, O(n) space
 * 4. Optimized Bottom-up - O(n) time, O(1) space
 * <p>
 * Key Insights:
 * - Recursive solution has exponential time complexity due to redundant calculations
 * - Memoization eliminates recalculation of previously solved subproblems
 * - Bottom-up approach builds solutions iteratively from base cases
 * - Space optimization is possible when only a limited number of previous states are needed
 */
public class _811_b_FibonacciSequence {

    public static void main(String[] args) {
        _811_b_FibonacciSequence solution = new _811_b_FibonacciSequence();

        int n = 10;  // Calculate F(10)

        System.out.println("Fibonacci sequence examples (F(" + n + ")):");

        // Using optimized method for larger values to avoid timeout
        int approach1 = (n <= 30) ? solution.fibRecursive(n) : solution.fibOptimized(n);
        System.out.println("Approach 1 (Recursive): " + approach1);

        System.out.println("Approach 2 (Memoized): " + solution.fibMemoized(n));
        System.out.println("Approach 3 (Tabulation): " + solution.fibTabulation(n));
        System.out.println("Approach 4 (Optimized): " + solution.fibOptimized(n));
    }

    /**
     * Approach 1: Naive recursive solution
     * Time Complexity: O(2^n) - exponential due to redundant calculations
     * Space Complexity: O(n) - recursion stack depth
     */
    public int fibRecursive(int n) {
        // Base case
        if (n == 0 || n == 1) {
            return n;
        }

        // Recursive case: F(n) = F(n-1) + F(n-2)
        return fibRecursive(n - 1) + fibRecursive(n - 2);
    }

    /**
     * Approach 2: Top-down with memoization
     * Time Complexity: O(n) - each subproblem computed only once
     * Space Complexity: O(n) - for memo array and recursion stack
     */
    public int fibMemoized(int n) {
        // Initialize memo array with -1 (indicating uncalculated values)
        int[] memo = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            memo[i] = -1;
        }

        return fibMemoHelper(n, memo);
    }

    private int fibMemoHelper(int n, int[] memo) {
        // Base case
        if (n == 0 || n == 1) {
            return n;
        }

        // Check if already calculated
        if (memo[n] != -1) {
            return memo[n];
        }

        // Calculate and store in memo
        memo[n] = fibMemoHelper(n - 1, memo) + fibMemoHelper(n - 2, memo);
        return memo[n];
    }

    /**
     * Approach 3: Bottom-up with tabulation
     * Time Complexity: O(n)
     * Space Complexity: O(n) - for dp array
     */
    public int fibTabulation(int n) {
        if (n == 0 || n == 1) {
            return n;
        }

        // Create dp table
        int[] dp = new int[n + 1];

        // Initialize base cases
        dp[0] = 0;
        dp[1] = 1;

        // Fill dp table bottom-up
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[n];
    }

    /**
     * Approach 4: Optimized bottom-up with constant space
     * Time Complexity: O(n)
     * Space Complexity: O(1) - only storing 2 previous values
     */
    public int fibOptimized(int n) {
        if (n == 0 || n == 1) {
            return n;
        }

        // Only store the two previous values instead of the entire array
        int prev2 = 0;  // F(0)
        int prev1 = 1;  // F(1)
        int current = 0;

        for (int i = 2; i <= n; i++) {
            current = prev1 + prev2;
            prev2 = prev1;
            prev1 = current;
        }

        return prev1;
    }
}