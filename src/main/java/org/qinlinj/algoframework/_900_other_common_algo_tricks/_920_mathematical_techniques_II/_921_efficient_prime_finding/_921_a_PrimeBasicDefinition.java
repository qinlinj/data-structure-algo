package org.qinlinj.algoframework._900_other_common_algo_tricks._920_mathematical_techniques_II._921_efficient_prime_finding;

import java.util.*;

public class _921_a_PrimeBasicDefinition {

    /**
     * Counts prime numbers in range [2, n) using naive approach
     * Time Complexity: O(n²)
     * Space Complexity: O(1)
     */
    public static int countPrimes(int n) {
        int count = 0;
        for (int i = 2; i < n; i++) {
            if (isPrime(i)) {
                count++;
            }
        }
        return count;
    }

    /**
     * Basic prime checking function - checks all numbers from 2 to n-1
     * Time Complexity: O(n)
     */
    public static boolean isPrime(int n) {
        if (n < 2) return false;

        for (int i = 2; i < n; i++) {
            if (n % i == 0) {
                // Found a divisor, not prime
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Prime Numbers - Basic Definition Demo ===");
        System.out.print("Enter a number to count primes up to: ");
        int n = scanner.nextInt();

        System.out.println("\nTesting basic prime definition:");
        System.out.println("A prime number can only be divided by 1 and itself.");

        // Show individual prime checks for small numbers
        System.out.println("\nChecking individual numbers:");
        for (int i = 2; i <= Math.min(n, 10); i++) {
            boolean prime = isPrime(i);
            System.out.printf("%d is %s\n", i, prime ? "prime" : "not prime");
        }

        // Count total primes
        long startTime = System.currentTimeMillis();
        int primeCount = countPrimes(n);
        long endTime = System.currentTimeMillis();

        System.out.printf("\nResult: There are %d prime numbers less than %d\n",
                primeCount, n);
        System.out.printf("Time taken: %d ms\n", endTime - startTime);

        // Example: countPrimes(10) should return 4 (primes: 2, 3, 5, 7)
        System.out.println("\nExample verification:");
        System.out.println("countPrimes(10) = " + countPrimes(10) + " (should be 4)");
        System.out.println("Primes less than 10: 2, 3, 5, 7");

        scanner.close();
    }
}