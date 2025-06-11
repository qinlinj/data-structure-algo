package org.qinlinj.algoframework._900_other_common_algo_tricks._910_mathematical_techniques_I._915_factorial_algorithm_problems;

/**
 * FACTORIAL TRAILING ZEROS - LEETCODE 172
 * <p>
 * PROBLEM SUMMARY:
 * Given a non-negative integer n, calculate how many trailing zeros are in n!
 * <p>
 * KEY INSIGHTS:
 * 1. Trailing zeros come from factors of 10 = 2 × 5
 * 2. In factorials, there are always more factors of 2 than 5
 * 3. So we only need to count factors of 5
 * 4. Numbers like 25, 125 contribute multiple factors of 5
 * <p>
 * APPROACH:
 * - Count multiples of 5: n/5
 * - Count multiples of 25 (contribute extra 5): n/25
 * - Count multiples of 125 (contribute another extra 5): n/125
 * - Continue until 5^k > n
 * <p>
 * TIME COMPLEXITY: O(log₅n) = O(log n)
 * SPACE COMPLEXITY: O(1)
 * <p>
 * EXAMPLES:
 * n = 5:  5! = 120 → 1 trailing zero
 * n = 25: 25! has 6 trailing zeros (5,10,15,20,25×2)
 * n = 125: 125! has 31 trailing zeros
 */

public class _915_a_FactorialTrailingZeros {
    /**
     * Method 1: Using while loop with long divisor
     * Safer approach to avoid integer overflow
     */
    public static int trailingZeroes(int n) {
        int res = 0;
        long divisor = 5;
        while (divisor <= n) {
            res += n / divisor;
            divisor *= 5;
        }
        return res;
    }

    /**
     * Method 2: Simplified version using for loop
     * More concise but need to be careful about overflow
     */
    public static int trailingZeroesSimplified(int n) {
        int res = 0;
        for (int d = n; d / 5 > 0; d = d / 5) {
            res += d / 5;
        }
        return res;
    }

    /**
     * Helper method to demonstrate the factor counting logic
     */
    public static void demonstrateFactorCounting(int n) {
        System.out.println("Counting factors of 5 in " + n + "!:");
        int total = 0;
        long divisor = 5;
        int level = 1;

        while (divisor <= n) {
            int count = (int) (n / divisor);
            System.out.println("Level " + level + " (÷" + divisor + "): " + count + " numbers");
            total += count;
            divisor *= 5;
            level++;
        }

        System.out.println("Total factors of 5: " + total);
        System.out.println("Therefore, trailing zeros: " + total);
    }
}
