package org.qinlinj.algoframework._900_other_common_algo_tricks._920_mathematical_techniques_II._921_efficient_prime_finding;

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

}
