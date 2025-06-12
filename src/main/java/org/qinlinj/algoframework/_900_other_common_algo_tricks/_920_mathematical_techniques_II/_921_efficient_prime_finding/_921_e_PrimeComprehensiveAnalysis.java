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
}
