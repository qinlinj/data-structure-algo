package org.qinlinj.algoframework._900_other_common_algo_tricks._910_mathematical_techniques_I._915_factorial_algorithm_problems;

/**
 * PREIMAGE SIZE OF FACTORIAL ZEROS FUNCTION - LEETCODE 793
 * <p>
 * PROBLEM SUMMARY:
 * Given a non-negative integer K, return how many non-negative integers n
 * satisfy that n! has exactly K trailing zeros.
 * <p>
 * KEY INSIGHTS:
 * 1. trailingZeroes(n) is a monotonically increasing function
 * 2. For any K, there might be multiple consecutive n values that produce K zeros
 * 3. Use binary search to find the leftmost and rightmost boundaries
 * 4. The answer is rightBound - leftBound + 1
 * <p>
 * APPROACH:
 * 1. Use binary search to find left boundary where trailingZeroes(n) = K
 * 2. Use binary search to find right boundary where trailingZeroes(n) = K
 * 3. Return the difference + 1
 * <p>
 * SEARCH RANGE:
 * - Lower bound: 0
 * - Upper bound: Long.MAX_VALUE (since K can be up to 10^9)
 * <p>
 * TIME COMPLEXITY: O(log K) - binary search with trailingZeroes calls
 * SPACE COMPLEXITY: O(1)
 * <p>
 * EXAMPLES:
 * K = 0: n = 0,1,2,3,4 all give 0 trailing zeros → answer = 5
 * K = 1: n = 5,6,7,8,9 all give 1 trailing zero → answer = 5
 * K = 2: n = 10,11,12,13,14 all give 2 trailing zeros → answer = 5
 */

public class _915_b_PreimageSizeKZeros {
    /**
     * Main solution method
     */
    public static int preimageSizeFZF(int K) {
        // Find left and right boundaries, then calculate the range
        return (int) (rightBound(K) - leftBound(K) + 1);
    }


    /**
     * Binary search to find the leftmost n where trailingZeroes(n) = target
     */
    private static long leftBound(int target) {
        long lo = 0, hi = Long.MAX_VALUE;

        while (lo < hi) {
            long mid = lo + (hi - lo) / 2;
            long zeros = trailingZeroes(mid);

            if (zeros < target) {
                lo = mid + 1;          // Search right half
            } else if (zeros > target) {
                hi = mid;              // Search left half
            } else {
                hi = mid;              // Found target, continue searching left
            }
        }
        return lo;
    }

    /**
     * Binary search to find the rightmost n where trailingZeroes(n) = target
     */
    private static long rightBound(int target) {
        long lo = 0, hi = Long.MAX_VALUE;

        while (lo < hi) {
            long mid = lo + (hi - lo) / 2;
            long zeros = trailingZeroes(mid);

            if (zeros < target) {
                lo = mid + 1;          // Search right half
            } else if (zeros > target) {
                hi = mid;              // Search left half
            } else {
                lo = mid + 1;          // Found target, continue searching right
            }
        }
        return lo - 1;
    }

    /**
     * Helper function to count trailing zeros in n!
     * Uses long to avoid overflow issues
     */
    private static long trailingZeroes(long n) {
        long res = 0;
        for (long d = n; d / 5 > 0; d = d / 5) {
            res += d / 5;
        }
        return res;
    }
}
