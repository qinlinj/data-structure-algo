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
}
