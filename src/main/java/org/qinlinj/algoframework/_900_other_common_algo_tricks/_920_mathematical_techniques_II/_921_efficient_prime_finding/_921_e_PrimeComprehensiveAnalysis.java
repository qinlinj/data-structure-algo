package org.qinlinj.algoframework._900_other_common_algo_tricks._920_mathematical_techniques_II._921_efficient_prime_finding;

import java.util.*;

public class _921_e_PrimeComprehensiveAnalysis {
    /**
     * LeetCode 204 Solution - Optimized Sieve of Eratosthenes
     * This is the final, production-ready solution
     */
    public static class Solution {
        public int countPrimes(int n) {
            if (n <= 2) return 0;

            boolean[] isPrime = new boolean[n];
            Arrays.fill(isPrime, true);

            for (int i = 2; i * i < n; i++) {
                if (isPrime[i]) {
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
    }

    /**
     * Algorithm Evolution Demonstrator
     * Shows progression from naive to optimized solutions
     */
    public static class AlgorithmEvolution {

        // Method 1: Naive O(n²) approach
        public static int countPrimesNaive(int n) {
            int count = 0;
            for (int i = 2; i < n; i++) {
                if (isPrimeNaive(i)) count++;
            }
            return count;
        }

        private static boolean isPrimeNaive(int n) {
            for (int i = 2; i < n; i++) {
                if (n % i == 0) return false;
            }
            return true;
        }

        // Method 2: Square root optimization O(n√n)
        public static int countPrimesSqrt(int n) {
            int count = 0;
            for (int i = 2; i < n; i++) {
                if (isPrimeSqrt(i)) count++;
            }
            return count;
        }

        private static boolean isPrimeSqrt(int n) {
            if (n < 2) return false;
            for (int i = 2; i * i <= n; i++) {
                if (n % i == 0) return false;
            }
            return true;
        }

        // Method 3: Basic Sieve O(n log log n)
        public static int countPrimesBasicSieve(int n) {
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

        // Method 4: Optimized Sieve O(n log log n) - Final version
        public static int countPrimesOptimal(int n) {
            return new Solution().countPrimes(n);
        }
    }
}
