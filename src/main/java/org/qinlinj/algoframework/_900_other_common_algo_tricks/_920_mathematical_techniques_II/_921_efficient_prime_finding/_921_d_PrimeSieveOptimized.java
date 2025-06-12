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
}
