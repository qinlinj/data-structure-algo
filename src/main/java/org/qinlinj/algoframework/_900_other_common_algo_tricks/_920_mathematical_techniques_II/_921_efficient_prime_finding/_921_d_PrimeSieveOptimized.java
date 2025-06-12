package org.qinlinj.algoframework._900_other_common_algo_tricks._920_mathematical_techniques_II._921_efficient_prime_finding;

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


}
