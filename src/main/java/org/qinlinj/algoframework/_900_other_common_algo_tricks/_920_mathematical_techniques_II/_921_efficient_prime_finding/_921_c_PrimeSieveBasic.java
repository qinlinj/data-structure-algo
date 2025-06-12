package org.qinlinj.algoframework._900_other_common_algo_tricks._920_mathematical_techniques_II._921_efficient_prime_finding;

/*
 * Prime Numbers - Sieve of Eratosthenes (Basic Version)
 *
 * Key Concepts:
 * 1. Sieve Algorithm: Mark multiples of each prime as composite (not prime)
 * 2. Reverse thinking: Instead of checking if each number is prime,
 *    eliminate numbers that are definitely not prime
 * 3. Process: Start with 2, mark all its multiples as non-prime,
 *    then move to next unmarked number and repeat
 * 4. Historical context: Invented by ancient Greek mathematician Eratosthenes
 *
 * Algorithm Steps:
 * 1. Create boolean array initialized to true (assume all are prime)
 * 2. For each number i from 2 to n:
 *    - If i is still marked as prime, mark all multiples of i as non-prime
 * 3. Count remaining numbers marked as prime
 *
 * This is the first version before optimizations are applied.
 */

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

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Sieve of Eratosthenes - Basic Implementation ===");
        System.out.println("Named after ancient Greek mathematician Eratosthenes");
        System.out.println("Also known as the 'Father of Geography'\n");

        // Demonstrate with small example
        System.out.println("Step-by-step visualization:");
        visualizeSieveProcess(20);

        System.out.print("\nEnter a number to count primes up to: ");
        int n = scanner.nextInt();

        // Performance comparison
        System.out.println("\nPerformance comparison:");

        // Basic individual checking
        long start1 = System.currentTimeMillis();
        int count1 = countPrimesIndividual(n);
        long end1 = System.currentTimeMillis();

        // Basic sieve
        long start2 = System.currentTimeMillis();
        int count2 = countPrimesBasicSieve(n);
        long end2 = System.currentTimeMillis();

        System.out.printf("Individual checking: %d primes, Time: %d ms\n",
                count1, end1 - start1);
        System.out.printf("Basic Sieve: %d primes, Time: %d ms\n",
                count2, end2 - start2);

        if (end1 - start1 > 0) {
            System.out.printf("Sieve speedup: %.2fx\n",
                    (double) (end1 - start1) / (end2 - start2));
        }

        // Show first few primes
        List<Integer> primes = getAllPrimesBasicSieve(Math.min(n, 100));
        System.out.println("\nFirst primes found: " +
                primes.subList(0, Math.min(primes.size(), 15)));

        scanner.close();
    }

    // Helper method for comparison
    private static int countPrimesIndividual(int n) {
        int count = 0;
        for (int i = 2; i < n; i++) {
            if (isPrime(i)) count++;
        }
        return count;
    }

    private static boolean isPrime(int n) {
        if (n < 2) return false;
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) return false;
        }
        return true;
    }
}