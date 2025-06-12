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
}
