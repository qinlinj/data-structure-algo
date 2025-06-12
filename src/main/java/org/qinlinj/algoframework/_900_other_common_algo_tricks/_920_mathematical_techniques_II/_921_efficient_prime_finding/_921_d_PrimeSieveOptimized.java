package org.qinlinj.algoframework._900_other_common_algo_tricks._920_mathematical_techniques_II._921_efficient_prime_finding;

/*
 * Prime Numbers - Sieve of Eratosthenes (Fully Optimized)
 *
 * Key Optimizations Applied:
 * 1. Outer loop optimization: Only iterate up to sqrt(n)
 *    - Reason: If n has a factor > sqrt(n), it must also have one < sqrt(n)
 * 2. Inner loop optimization: Start from i*i instead of 2*i
 *    - Reason: All smaller multiples (2*i, 3*i, ..., (i-1)*i) already marked
 *    - Example: When i=5, don't mark 5*2=10, 5*3=15 (already marked by 2,3)
 *             Start from 5*5=25
 *
 * Mathematical Analysis:
 * - Time Complexity: O(n log log n)
 * - Space Complexity: O(n)
 * - Operations count: n/2 + n/3 + n/5 + n/7 + ... = n × (sum of reciprocals of primes)
 *
 * This represents the most efficient version of the sieve algorithm
 * with both mathematical optimizations applied for maximum performance.
 */

import java.util.*;

public class _921_d_PrimeSieveOptimized {

    /**
     * Fully optimized Sieve of Eratosthenes
     * Time Complexity: O(n log log n)
     * Space Complexity: O(n)
     */
    public static int countPrimesOptimized(int n) {
        if (n <= 2) return 0;

        boolean[] isPrime = new boolean[n];
        Arrays.fill(isPrime, true);

        // Optimization 1: Only check up to sqrt(n)
        for (int i = 2; i * i < n; i++) {
            if (isPrime[i]) {
                // Optimization 2: Start from i*i instead of 2*i
                for (int j = i * i; j < n; j += i) {
                    isPrime[j] = false;
                }
            }
        }

        int count = 0;
        for (int i = 2; i < n; i++) {
            if (isPrime[i]) count++;
        }

        return count;
    }

    /**
     * Returns all primes using optimized sieve
     */
    public static List<Integer> getAllPrimesOptimized(int n) {
        List<Integer> primes = new ArrayList<>();
        if (n <= 2) return primes;

        boolean[] isPrime = new boolean[n];
        Arrays.fill(isPrime, true);

        for (int i = 2; i * i < n; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j < n; j += i) {
                    isPrime[j] = false;
                }
            }
        }

        for (int i = 2; i < n; i++) {
            if (isPrime[i]) {
                primes.add(i);
            }
        }

        return primes;
    }

    /**
     * Demonstrates why starting from i*i is optimal
     */
    public static void demonstrateOptimization(int prime) {
        System.out.printf("\nWhy start from %d² instead of 2×%d?\n", prime, prime);
        System.out.printf("When processing prime %d:\n", prime);

        for (int multiplier = 2; multiplier < prime; multiplier++) {
            int product = prime * multiplier;
            System.out.printf("  %d × %d = %d (already marked by prime %d)\n",
                    prime, multiplier, product, multiplier);
        }

        System.out.printf("  %d × %d = %d (first new composite to mark)\n",
                prime, prime, prime * prime);
        System.out.println("This is why we start from i² - avoid redundant work!");
    }

    /**
     * Compares operation counts between basic and optimized versions
     */
    public static void compareOperationCounts(int n) {
        int basicOps = 0;
        int optimizedOps = 0;

        // Count operations in basic version
        for (int i = 2; i < n; i++) {
            if (isPrimeSimple(i)) {
                for (int j = 2 * i; j < n; j += i) {
                    basicOps++;
                }
            }
        }

        // Count operations in optimized version
        for (int i = 2; i * i < n; i++) {
            if (isPrimeSimple(i)) {
                for (int j = i * i; j < n; j += i) {
                    optimizedOps++;
                }
            }
        }

        System.out.printf("\nOperation count comparison for n = %d:\n", n);
        System.out.printf("Basic sieve operations: %d\n", basicOps);
        System.out.printf("Optimized sieve operations: %d\n", optimizedOps);
        System.out.printf("Reduction: %.1f%%\n",
                100.0 * (basicOps - optimizedOps) / basicOps);
    }

    /**
     * Shows the mathematical series behind time complexity
     */
    public static void explainTimeComplexity(int n) {
        System.out.println("\n=== Time Complexity Analysis ===");
        System.out.println("Operations performed: n/p₁ + n/p₂ + n/p₃ + ...");
        System.out.println("Where p₁, p₂, p₃... are primes ≤ √n");
        System.out.println("= n × (1/p₁ + 1/p₂ + 1/p₃ + ...)");

        List<Integer> primes = getAllPrimesOptimized((int) Math.sqrt(n) + 1);
        double sum = 0;

        System.out.printf("\nFor n = %d (√n ≈ %.1f):\n", n, Math.sqrt(n));
        System.out.print("Primes ≤ √n: ");
        for (int prime : primes) {
            if (prime <= Math.sqrt(n)) {
                System.out.print(prime + " ");
                sum += 1.0 / prime;
            }
        }

        System.out.printf("\nSum of reciprocals: %.4f\n", sum);
        System.out.printf("Estimated operations: %.0f\n", n * sum);
        System.out.println("This sum grows as O(log log n), giving overall O(n log log n)");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Sieve of Eratosthenes - Fully Optimized ===");

        // Demonstrate optimizations
        System.out.println("Understanding the optimizations:");
        demonstrateOptimization(5);
        demonstrateOptimization(7);

        System.out.print("\nEnter a number to analyze: ");
        int n = scanner.nextInt();

        // Performance comparison
        System.out.println("\n=== Performance Comparison ===");

        long[] times = new long[3];
        int[] counts = new int[3];

        // Individual checking
        long start = System.currentTimeMillis();
        counts[0] = countPrimesIndividual(n);
        times[0] = System.currentTimeMillis() - start;

        // Basic sieve
        start = System.currentTimeMillis();
        counts[1] = countPrimesBasicSieve(n);
        times[1] = System.currentTimeMillis() - start;

        // Optimized sieve
        start = System.currentTimeMillis();
        counts[2] = countPrimesOptimized(n);
        times[2] = System.currentTimeMillis() - start;

        String[] methods = {"Individual Check", "Basic Sieve", "Optimized Sieve"};
        for (int i = 0; i < 3; i++) {
            System.out.printf("%-16s: %d primes, %d ms\n",
                    methods[i], counts[i], times[i]);
        }

        if (times[0] > 0) {
            System.out.printf("Final speedup: %.1fx faster than individual checking\n",
                    (double) times[0] / times[2]);
        }

        // Operation count analysis
        if (n <= 10000) { // Only for reasonable sizes
            compareOperationCounts(n);
        }

        // Time complexity explanation
        explainTimeComplexity(n);

        // Show results
        List<Integer> primes = getAllPrimesOptimized(Math.min(n, 100));
        System.out.println("\nFirst 20 primes: " +
                primes.subList(0, Math.min(primes.size(), 20)));

        scanner.close();
    }

    // Helper methods for comparison
    private static boolean isPrimeSimple(int n) {
        if (n < 2) return false;
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) return false;
        }
        return true;
    }

    private static int countPrimesIndividual(int n) {
        int count = 0;
        for (int i = 2; i < n; i++) {
            if (isPrimeSimple(i)) count++;
        }
        return count;
    }

    private static int countPrimesBasicSieve(int n) {
        if (n <= 2) return 0;
        boolean[] isPrime = new boolean[n];
        Arrays.fill(isPrime, true);

        for (int i = 2; i < n; i++) {
            if (isPrime[i]) {
                for (int j = 2 * i; j < n; j += i) {
                    isPrime[j] = false;
                }
            }
        }

        int count = 0;
        for (int i = 2; i < n; i++) {
            if (isPrime[i]) count++;
        }
        return count;
    }
}