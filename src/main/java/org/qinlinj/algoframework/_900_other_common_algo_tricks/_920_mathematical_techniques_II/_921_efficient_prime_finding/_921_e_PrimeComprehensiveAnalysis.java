package org.qinlinj.algoframework._900_other_common_algo_tricks._920_mathematical_techniques_II._921_efficient_prime_finding;

/*
 * Prime Numbers - Comprehensive Analysis and LeetCode Problem Solution
 *
 * Problem: LeetCode 204 - Count Primes
 * "Count the number of prime numbers less than a non-negative integer, n."
 *
 * Complete Evolution of Solutions:
 * 1. Naive O(n²) approach - check each number individually
 * 2. Square root optimization O(n√n) - reduce prime checking time
 * 3. Basic Sieve O(n log log n) - eliminate multiples approach
 * 4. Optimized Sieve O(n log log n) - with sqrt and i² optimizations
 *
 * Key Insights:
 * - Factor symmetry: factors come in pairs around √n
 * - Sieve thinking: mark composites instead of checking primes
 * - Mathematical optimizations can dramatically improve performance
 * - Ancient algorithms (Eratosthenes ~200 BC) still relevant today
 *
 * Time Complexity Analysis:
 * The optimized sieve performs n × (1/2 + 1/3 + 1/5 + 1/7 + ...) operations
 * where the sum is over primes ≤ √n. This sum grows as O(log log n).
 */

import java.util.*;

public class _921_e_PrimeComprehensiveAnalysis {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Prime Numbers - Comprehensive Analysis ===");
        System.out.println("Evolution from O(n²) naive to O(n log log n) optimized sieve");
        System.out.println();

        // Educational demonstration
        EducationalExamples.demonstrateSieveVisualization(20);
        EducationalExamples.explainOptimizations();

        System.out.print("\nEnter n for comprehensive analysis: ");
        int n = scanner.nextInt();

        // LeetCode test cases
        System.out.println("\n=== LeetCode Test Cases ===");
        Solution solution = new Solution();
        int[] testCases = {10, 0, 1, 2, 3};
        for (int test : testCases) {
            System.out.printf("countPrimes(%d) = %d\n", test, solution.countPrimes(test));
        }

        // Performance benchmark
        PerformanceBenchmark.runComprehensiveBenchmark(n);

        // Prime distribution analysis
        if (n >= 10) {
            PerformanceBenchmark.analyzePrimeDistribution(n);
        }

        System.out.println("\n=== Algorithm Summary ===");
        System.out.println("Final optimized solution combines:");
        System.out.println("✓ Sieve of Eratosthenes approach (eliminate multiples)");
        System.out.println("✓ √n optimization (outer loop bound)");
        System.out.println("✓ i² optimization (inner loop start)");
        System.out.println("✓ Time: O(n log log n), Space: O(n)");
        System.out.println("✓ Suitable for LeetCode and production use");

        scanner.close();
    }

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

    /**
     * Educational Examples and Demonstrations
     */
    public static class EducationalExamples {

        public static void demonstrateSieveVisualization(int n) {
            if (n > 30) n = 30; // Limit for readability

            System.out.printf("\n=== Sieve Visualization (n=%d) ===\n", n);
            boolean[] isPrime = new boolean[n];
            Arrays.fill(isPrime, true);
            if (n > 0) isPrime[0] = false;
            if (n > 1) isPrime[1] = false;

            printGrid(isPrime, n, "Initial state");

            for (int i = 2; i * i < n; i++) {
                if (isPrime[i]) {
                    System.out.printf("\nProcessing prime %d:\n", i);

                    for (int j = i * i; j < n; j += i) {
                        isPrime[j] = false;
                        System.out.printf("  Marked %d = %d × %d\n", j, i, j / i);
                    }

                    printGrid(isPrime, n, "After processing " + i);
                }
            }

            System.out.print("\nFinal primes: ");
            for (int i = 2; i < n; i++) {
                if (isPrime[i]) System.out.print(i + " ");
            }
            System.out.println();
        }

        private static void printGrid(boolean[] isPrime, int n, String label) {
            System.out.println(label + ":");
            for (int i = 0; i < n; i++) {
                System.out.printf("%3d", i);
            }
            System.out.println();
            for (int i = 0; i < n; i++) {
                System.out.printf("%3s", isPrime[i] ? "T" : "F");
            }
            System.out.println();
        }

        public static void explainOptimizations() {
            System.out.println("\n=== Understanding the Optimizations ===");

            System.out.println("1. Why only check up to √n?");
            System.out.println("   If n = a × b and a > √n, then b < √n");
            System.out.println("   So if no factors exist ≤ √n, none exist > √n either");

            System.out.println("\n2. Why start inner loop from i²?");
            System.out.println("   When processing prime p:");
            System.out.println("   - p×2, p×3, ..., p×(p-1) already marked by smaller primes");
            System.out.println("   - p×p is the first unmarked multiple");

            System.out.println("\n3. Example with p=7:");
            System.out.println("   - 7×2=14 already marked when processing 2");
            System.out.println("   - 7×3=21 already marked when processing 3");
            System.out.println("   - 7×4=28 already marked when processing 2");
            System.out.println("   - 7×5=35 already marked when processing 5");
            System.out.println("   - 7×6=42 already marked when processing 2");
            System.out.println("   - 7×7=49 is first new composite to mark");
        }
    }
}