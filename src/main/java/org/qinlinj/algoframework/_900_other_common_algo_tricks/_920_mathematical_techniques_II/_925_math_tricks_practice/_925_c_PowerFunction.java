package org.qinlinj.algoframework._900_other_common_algo_tricks._920_mathematical_techniques_II._925_math_tricks_practice;

/**
 * 50. Pow(x, n) - LeetCode Problem
 * <p>
 * SUMMARY:
 * This class implements efficient exponentiation using the "fast exponentiation" algorithm.
 * Instead of naive multiplication (O(n)), it uses divide-and-conquer to achieve O(log n).
 * The algorithm handles negative exponents and the edge case of Integer.MIN_VALUE.
 * <p>
 * KEY CONCEPTS:
 * - Fast exponentiation (exponentiation by squaring)
 * - Divide and conquer optimization
 * - Handling negative exponents by using reciprocal
 * - Integer overflow prevention for Integer.MIN_VALUE
 * - Recursive mathematical relationships: x^n = x * x^(n-1) for odd n, x^n = (x^(n/2))^2 for even n
 * <p>
 * ALGORITHM STRATEGY:
 * - For even exponents: x^n = (x^(n/2))^2
 * - For odd exponents: x^n = x * x^(n-1)
 * - For negative exponents: x^(-n) = 1 / x^n
 * - Special handling for Integer.MIN_VALUE to avoid overflow
 * <p>
 * TIME COMPLEXITY: O(log n) due to halving the exponent in each recursive call
 * SPACE COMPLEXITY: O(log n) for recursion stack depth
 */
public class _925_c_PowerFunction {

    public static void main(String[] args) {
        _925_c_PowerFunction solution = new _925_c_PowerFunction();

        System.out.println("=== Power Function Test Cases ===");

        // Test cases demonstrating various scenarios
        System.out.println("Basic positive exponents:");
        System.out.printf("2^10 = %.5f\n", solution.myPow(2.0, 10));
        System.out.printf("2.1^3 = %.5f\n", solution.myPow(2.1, 3));

        System.out.println("\nNegative exponents:");
        System.out.printf("2^(-2) = %.5f\n", solution.myPow(2.0, -2));
        System.out.printf("0.5^(-2) = %.5f\n", solution.myPow(0.5, -2));

        System.out.println("\nEdge cases:");
        System.out.printf("Any number^0 = %.5f\n", solution.myPow(123.456, 0));
        System.out.printf("1^large_number = %.5f\n", solution.myPow(1.0, 1000000));
        System.out.printf("2^Integer.MIN_VALUE = %.10f\n", solution.myPow(2.0, Integer.MIN_VALUE));

        // Compare recursive vs iterative approaches
        System.out.println("\n=== Comparing Recursive vs Iterative ===");
        double base = 3.0;
        int exp = 15;

        double recursiveResult = solution.myPow(base, exp);
        double iterativeResult = solution.myPowIterative(base, exp);

        System.out.printf("%.1f^%d:\n", base, exp);
        System.out.printf("  Recursive: %.6f\n", recursiveResult);
        System.out.printf("  Iterative: %.6f\n", iterativeResult);
        System.out.printf("  Results match: %s\n", Math.abs(recursiveResult - iterativeResult) < 1e-9);

        // Demonstrate the optimization - show how many operations are saved
        System.out.println("\n=== Algorithm Efficiency Demo ===");
        int largeExp = 1000;
        System.out.printf("To calculate x^%d:\n", largeExp);
        System.out.printf("  Naive approach: %d multiplications\n", largeExp - 1);
        System.out.printf("  Fast exponentiation: ~%d operations\n", (int) (Math.log(largeExp) / Math.log(2)));
        System.out.printf("  Speedup factor: ~%.1fx\n", (double) (largeExp - 1) / (Math.log(largeExp) / Math.log(2)));
    }

    /**
     * Calculates x raised to the power of k using fast exponentiation
     *
     * @param a base number
     * @param k exponent
     * @return a^k as a double
     */
    public double myPow(double a, int k) {
        if (k == 0) return 1;

        if (k == Integer.MIN_VALUE) {
            // Handle Integer.MIN_VALUE separately to avoid overflow when negating
            // Integer.MIN_VALUE = -2^31, and -(-2^31) would overflow int range
            return myPow(1 / a, -(k + 1)) / a;
        }

        if (k < 0) {
            return myPow(1 / a, -k);
        }

        if (k % 2 == 1) {
            // k is odd: a^k = a * a^(k-1)
            return a * myPow(a, k - 1);
        } else {
            // k is even: a^k = (a^(k/2))^2
            double sub = myPow(a, k / 2);
            return sub * sub;
        }
    }

    /**
     * Iterative version of fast exponentiation (more memory efficient)
     */
    public double myPowIterative(double x, int n) {
        if (n == 0) return 1;

        long absN = Math.abs((long) n); // Use long to handle Integer.MIN_VALUE
        double result = 1;
        double currentPower = x;

        while (absN > 0) {
            if (absN % 2 == 1) {
                result *= currentPower;
            }
            currentPower *= currentPower;
            absN /= 2;
        }

        return n < 0 ? 1 / result : result;
    }
}