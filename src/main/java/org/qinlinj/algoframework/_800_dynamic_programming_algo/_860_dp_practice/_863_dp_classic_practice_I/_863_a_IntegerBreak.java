package org.qinlinj.algoframework._800_dynamic_programming_algo._860_dp_practice._863_dp_classic_practice_I;

/**
 * LeetCode 343. Integer Break - Dynamic Programming Solution
 * <p>
 * PROBLEM SUMMARY:
 * Given a positive integer n, break it into at least two positive integers
 * and maximize the product of those integers.
 * <p>
 * KEY CONCEPTS:
 * 1. Dynamic Programming with Memoization
 * 2. State Transition: For each number i from 1 to n, we have two choices:
 * - Keep i as is (i)
 * - Further break i down (dp(i))
 * 3. Recurrence Relation: dp(n) = max(i * max(dp(n-i), n-i)) for all i from 1 to n
 * 4. The decision between i and dp(i) handles whether to continue breaking down
 * <p>
 * TIME COMPLEXITY: O(n²) with memoization
 * SPACE COMPLEXITY: O(n) for memoization array
 * <p>
 * EXAMPLE:
 * Input: n = 10
 * Output: 36 (10 = 3 + 3 + 4, and 3 × 3 × 4 = 36)
 */

public class _863_a_IntegerBreak {
    private int[] memo;

    public int integerBreak(int n) {
        memo = new int[n + 1];
        return dp(n);
    }

    // Definition: dp(n) returns the maximum product when breaking n
    private int dp(int n) {
        // Base cases
        if (n == 0) return 0;
        if (n == 1) return 1;

        // Check memoization
        if (memo[n] > 0) {
            return memo[n];
        }

        // State transition equation
        int res = Integer.MIN_VALUE;
        for (int i = 1; i <= n; i++) {
            // For each split i and (n-i), we choose:
            // 1. i * (n-i): don't break further
            // 2. i * dp(n-i): break (n-i) further
            res = Math.max(res, i * Math.max(dp(n - i), n - i));
        }

        memo[n] = res;
        return res;
    }
}
