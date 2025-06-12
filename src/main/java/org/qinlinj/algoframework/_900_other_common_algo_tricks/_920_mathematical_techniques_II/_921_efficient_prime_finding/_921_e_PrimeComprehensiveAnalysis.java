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


    /**
     * Performance Benchmarking Suite
     */
    public static class PerformanceBenchmark {

        public static void runComprehensiveBenchmark(int n) {
            System.out.println("=== Comprehensive Performance Benchmark ===");
            System.out.printf("Testing with n = %d\n\n", n);

            String[] methodNames = {
                    "Naive O(n²)",
                    "Square Root O(n√n)",
                    "Basic Sieve O(n log log n)",
                    "Optimized Sieve O(n log log n)"
            };

            long[] times = new long[4];
            int[] results = new int[4];

            // Only test naive approach for small n to avoid timeout
            if (n <= 5000) {
                long start = System.currentTimeMillis();
                results[0] = AlgorithmEvolution.countPrimesNaive(n);
                times[0] = System.currentTimeMillis() - start;
            }

            // Square root optimization
            long start = System.currentTimeMillis();
            results[1] = AlgorithmEvolution.countPrimesSqrt(n);
            times[1] = System.currentTimeMillis() - start;

            // Basic sieve
            start = System.currentTimeMillis();
            results[2] = AlgorithmEvolution.countPrimesBasicSieve(n);
            times[2] = System.currentTimeMillis() - start;

            // Optimized sieve
            start = System.currentTimeMillis();
            results[3] = AlgorithmEvolution.countPrimesOptimal(n);
            times[3] = System.currentTimeMillis() - start;

            // Display results
            System.out.printf("%-25s | %-8s | %-8s | %-10s\n",
                    "Method", "Result", "Time(ms)", "Speedup");
            System.out.println("-".repeat(60));

            for (int i = 0; i < 4; i++) {
                if (i == 0 && n > 5000) {
                    System.out.printf("%-25s | %-8s | %-8s | %-10s\n",
                            methodNames[i], "skipped", "skipped", "N/A");
                    continue;
                }

                String speedup = "1.0x";
                if (times[1] > 0) {
                    speedup = String.format("%.1fx", (double) times[1] / times[i]);
                }

                System.out.printf("%-25s | %-8d | %-8d | %-10s\n",
                        methodNames[i], results[i], times[i], speedup);
            }
        }

        public static void analyzePrimeDistribution(int n) {
            System.out.println("\n=== Prime Distribution Analysis ===");

            List<Integer> primes = new ArrayList<>();
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
                if (isPrime[i]) primes.add(i);
            }

            System.out.printf("Total primes less than %d: %d\n", n, primes.size());
            System.out.printf("Prime density: %.4f%%\n",
                    100.0 * primes.size() / n);

            // Prime Number Theorem approximation
            double pnt = n / Math.log(n);
            System.out.printf("Prime Number Theorem estimate: %.0f\n", pnt);
            System.out.printf("Actual vs PNT ratio: %.4f\n",
                    (double) primes.size() / pnt);

            // Show distribution by ranges
            System.out.println("\nPrime distribution by ranges:");
            int[] ranges = {10, 100, 1000, 10000, 100000};
            int lastCount = 0;

            for (int range : ranges) {
                if (range >= n) break;

                int count = 0;
                for (int prime : primes) {
                    if (prime < range) count++;
                    else break;
                }

                System.out.printf("[%d-%d): %d primes (%.2f%%)\n",
                        lastCount == 0 ? 2 : ranges[getIndex(ranges, lastCount)],
                        range, count - lastCount,
                        100.0 * (count - lastCount) / (range - (lastCount == 0 ? 2 : ranges[getIndex(ranges, lastCount)])));
                lastCount = count;
            }
        }

        private static int getIndex(int[] arr, int val) {
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] >= val) return i;
            }
            return arr.length - 1;
        }
    }
}
