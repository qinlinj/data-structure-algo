package org.qinlinj.algoframework._900_other_common_algo_tricks._910_mathematical_techniques_I._915_factorial_algorithm_problems;

/**
 * FACTORIAL TRAILING ZEROS - MATHEMATICAL CONCEPTS AND THEORY
 * <p>
 * FUNDAMENTAL CONCEPTS:
 * 1. Trailing zeros in factorials come from factors of 10 = 2 × 5
 * 2. In factorials, factors of 2 are more abundant than factors of 5
 * 3. The number of trailing zeros = number of times 10 divides n!
 * 4. This equals the number of pairs (2,5) in the prime factorization
 * <p>
 * KEY MATHEMATICAL INSIGHTS:
 * 1. Every even number contributes at least one factor of 2
 * 2. Every 5th number contributes at least one factor of 5
 * 3. Some numbers contribute multiple factors of 5 (25, 125, etc.)
 * 4. The trailingZeroes function is monotonically non-decreasing
 * <p>
 * APPLICATIONS:
 * - LeetCode 172: Count trailing zeros in n!
 * - LeetCode 793: Count how many n give exactly K trailing zeros
 * - Mathematical analysis of factorial properties
 * - Understanding binary search on functions
 * <p>
 * TIME COMPLEXITIES:
 * - Counting trailing zeros: O(log n)
 * - Finding preimage size: O(log K)
 */

public class _915_c_FactorialConcepts {
    /**
     * Demonstrates why factors of 5 are the limiting factor
     */
    public static void explainFactorDistribution(int n) {
        System.out.println("Analyzing factor distribution in " + n + "!:");
        System.out.println("-".repeat(40));

        // Count factors of 2
        int factors2 = 0;
        for (int i = 1; i <= n; i++) {
            int num = i;
            while (num % 2 == 0) {
                factors2++;
                num /= 2;
            }
        }

        // Count factors of 5
        int factors5 = 0;
        for (int i = 1; i <= n; i++) {
            int num = i;
            while (num % 5 == 0) {
                factors5++;
                num /= 5;
            }
        }

        System.out.println("Total factors of 2: " + factors2);
        System.out.println("Total factors of 5: " + factors5);
        System.out.println("Trailing zeros (min of above): " + Math.min(factors2, factors5));
        System.out.println("Ratio (factors of 2 : factors of 5): " +
                String.format("%.2f", (double) factors2 / factors5));
    }
}
