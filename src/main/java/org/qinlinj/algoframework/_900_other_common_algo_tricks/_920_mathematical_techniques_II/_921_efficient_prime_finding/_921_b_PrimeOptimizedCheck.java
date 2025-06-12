package org.qinlinj.algoframework._900_other_common_algo_tricks._920_mathematical_techniques_II._921_efficient_prime_finding;

public class _921_b_PrimeOptimizedCheck {
    /**
     * Optimized prime checking using square root method
     * Time Complexity: O(sqrt(n))
     * Space Complexity: O(1)
     */
    public static boolean isPrimeOptimized(int n) {
        if (n < 2) return false;
        if (n == 2) return true;
        if (n % 2 == 0) return false; // Even numbers except 2 are not prime

        // Only check odd divisors up to sqrt(n)
        for (int i = 3; i * i <= n; i += 2) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Count primes using optimized isPrime function
     * Time Complexity: O(n * sqrt(n))
     */
    public static int countPrimesOptimized(int n) {
        int count = 0;
        for (int i = 2; i < n; i++) {
            if (isPrimeOptimized(i)) {
                count++;
            }
        }
        return count;
    }
}
