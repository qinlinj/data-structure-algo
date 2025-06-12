package org.qinlinj.algoframework._900_other_common_algo_tricks._920_mathematical_techniques_II._921_efficient_prime_finding;

import java.util.*;

public class _921_b_PrimeOptimizedCheck {
    /**
     * Optimized prime checking using square root method
     * Time Complexity: O(sqrt(n))
     * Space Complexity: O(1)
     */
    public static boolean isPrimeOptimized(int n) {
        if (n < 2) return false;
        if (n == 2) return true;
        if (n % 2 == 0) return false; // Even numbers except 2 are not prime

        // Only check odd divisors up to sqrt(n)
        for (int i = 3; i * i <= n; i += 2) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Count primes using optimized isPrime function
     * Time Complexity: O(n * sqrt(n))
     */
    public static int countPrimesOptimized(int n) {
        int count = 0;
        for (int i = 2; i < n; i++) {
            if (isPrimeOptimized(i)) {
                count++;
            }
        }
        return count;
    }

    /**
     * Demonstrates the factor symmetry principle
     */
    public static void demonstrateFactorSymmetry(int n) {
        System.out.printf("\nFactor symmetry demonstration for %d:\n", n);
        List<String> factors = new ArrayList<>();

        for (int i = 1; i * i <= n; i++) {
            if (n % i == 0) {
                if (i * i == n) {
                    factors.add(String.format("%d × %d", i, i));
                } else {
                    factors.add(String.format("%d × %d", i, n / i));
                    factors.add(String.format("%d × %d", n / i, i));
                }
            }
        }

        for (String factor : factors) {
            System.out.printf("%d = %s\n", n, factor);
        }

        double sqrt = Math.sqrt(n);
        System.out.printf("sqrt(%d) ≈ %.2f\n", n, sqrt);
        System.out.println("Notice the symmetry around sqrt(n)!");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Prime Numbers - Square Root Optimization Demo ===");

        // Demonstrate factor symmetry
        System.out.println("Understanding factor symmetry:");
        demonstrateFactorSymmetry(12);
        demonstrateFactorSymmetry(16);

        System.out.print("\nEnter a number to count primes up to: ");
        int n = scanner.nextInt();

        // Compare basic vs optimized approach
        System.out.println("\nComparing basic vs optimized prime checking:");

        long startTime = System.currentTimeMillis();
        int count1 = countPrimesBasic(n);
        long endTime1 = System.currentTimeMillis();

        long startTime2 = System.currentTimeMillis();
        int count2 = countPrimesOptimized(n);
        long endTime2 = System.currentTimeMillis();

        System.out.printf("Basic approach: %d primes, Time: %d ms\n",
                count1, endTime1 - startTime);
        System.out.printf("Optimized approach: %d primes, Time: %d ms\n",
                count2, endTime2 - startTime2);
        System.out.printf("Speedup: %.2fx\n",
                (double) (endTime1 - startTime) / (endTime2 - startTime2));

        // Show some prime examples
        System.out.println("\nFirst few primes found:");
        int count = 0;
        for (int i = 2; i < n && count < 10; i++) {
            if (isPrimeOptimized(i)) {
                System.out.print(i + " ");
                count++;
            }
        }
        System.out.println();

        scanner.close();
    }

    // Basic implementation for comparison
    private static boolean isPrimeBasic(int n) {
        if (n < 2) return false;
        for (int i = 2; i < n; i++) {
            if (n % i == 0) return false;
        }
        return true;
    }
}
