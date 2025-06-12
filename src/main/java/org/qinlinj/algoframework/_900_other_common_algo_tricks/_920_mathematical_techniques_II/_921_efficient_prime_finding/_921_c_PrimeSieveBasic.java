package org.qinlinj.algoframework._900_other_common_algo_tricks._920_mathematical_techniques_II._921_efficient_prime_finding;

import java.util.*;

public class _921_c_PrimeSieveBasic {
    /**
     * Basic Sieve of Eratosthenes implementation
     * Time Complexity: O(n log log n)
     * Space Complexity: O(n)
     */
    public static int countPrimesBasicSieve(int n) {
        if (n <= 2) return 0;

        // Initialize array - assume all numbers are prime initially
        boolean[] isPrime = new boolean[n];
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false; // 0 and 1 are not prime

        // Sieve process
        for (int i = 2; i < n; i++) {
            if (isPrime[i]) {
                // Mark all multiples of i as non-prime
                for (int j = 2 * i; j < n; j += i) {
                    isPrime[j] = false;
                }
            }
        }

        // Count primes
        int count = 0;
        for (int i = 2; i < n; i++) {
            if (isPrime[i]) count++;
        }

        return count;
    }

    /**
     * Returns list of all primes less than n using basic sieve
     */
    public static List<Integer> getAllPrimesBasicSieve(int n) {
        List<Integer> primes = new ArrayList<>();
        if (n <= 2) return primes;

        boolean[] isPrime = new boolean[n];
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;

        for (int i = 2; i < n; i++) {
            if (isPrime[i]) {
                primes.add(i);
                // Mark multiples as non-prime
                for (int j = 2 * i; j < n; j += i) {
                    isPrime[j] = false;
                }
            }
        }

        return primes;
    }

    /**
     * Visualizes the sieve process step by step
     */
    public static void visualizeSieveProcess(int n) {
        if (n > 30) {
            System.out.println("Visualization limited to n <= 30 for readability");
            n = Math.min(n, 30);
        }

        boolean[] isPrime = new boolean[n];
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;

        System.out.printf("Sieve of Eratosthenes visualization for n = %d:\n", n);
        System.out.println("T = potentially prime, F = marked as composite\n");

        // Initial state
        System.out.print("Initial: ");
        printSieveState(isPrime, n);

        for (int i = 2; i < n; i++) {
            if (isPrime[i]) {
                System.out.printf("\nProcessing prime %d:\n", i);

                // Mark multiples
                for (int j = 2 * i; j < n; j += i) {
                    if (isPrime[j]) {
                        isPrime[j] = false;
                        System.out.printf("  Marking %d as composite (%d × %d)\n",
                                j, i, j / i);
                    }
                }

                System.out.print("Current: ");
                printSieveState(isPrime, n);
            }
        }

        System.out.println("\nFinal primes:");
        for (int i = 2; i < n; i++) {
            if (isPrime[i]) {
                System.out.print(i + " ");
            }
        }
        System.out.println();
    }

    private static void printSieveState(boolean[] isPrime, int n) {
        for (int i = 0; i < n; i++) {
            System.out.printf("%2d", i);
        }
        System.out.println();
        for (int i = 0; i < n; i++) {
            System.out.printf("%2s", isPrime[i] ? "T" : "F");
        }
        System.out.println();
    }
}
