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

    /**
     * Brute force approach for small K values (for verification)
     */
    public static int bruteForce(int K) {
        int count = 0;
        for (long n = 0; n <= 100000; n++) {
            if (trailingZeroes(n) == K) {
                count++;
            } else if (trailingZeroes(n) > K) {
                break;
            }
        }
        return count;
    }

    /**
     * Demonstration method to show the monotonic property
     */
    public static void demonstrateMonotonicProperty() {
        System.out.println("Demonstrating monotonic property of trailingZeroes function:");
        System.out.println("n\ttrailingZeroes(n!)");
        System.out.println("-".repeat(25));

        for (int n = 0; n <= 30; n++) {
            long zeros = trailingZeroes(n);
            System.out.printf("%d\t%d\n", n, zeros);
        }
    }

    /**
     * Show detailed binary search process for educational purposes
     */
    public static void demonstrateBinarySearch(int K) {
        System.out.println("\nFinding boundaries for K = " + K + ":");

        // Find left bound with detailed steps
        System.out.println("Searching for left boundary...");
        long left = leftBound(K);
        System.out.println("Left boundary: " + left +
                " (trailingZeroes(" + left + ") = " + trailingZeroes(left) + ")");

        // Find right bound
        System.out.println("Searching for right boundary...");
        long right = rightBound(K);
        System.out.println("Right boundary: " + right +
                " (trailingZeroes(" + right + ") = " + trailingZeroes(right) + ")");

        // Show the range
        System.out.println("Range: [" + left + ", " + right + "]");
        System.out.println("Count: " + (right - left + 1));

        // Verify by showing a few values in the range
        System.out.println("Verification - values in range:");
        for (long n = Math.max(0, left); n <= Math.min(right, left + 10); n++) {
            System.out.println("trailingZeroes(" + n + ") = " + trailingZeroes(n));
        }
    }

    public static void main(String[] args) {
        System.out.println("=== PREIMAGE SIZE OF FACTORIAL ZEROS DEMO ===\n");

        // Show monotonic property first
        demonstrateMonotonicProperty();

        // Test cases
        int[] testCases = {0, 1, 2, 3, 5, 10};

        System.out.println("\n=== TEST CASES ===");
        for (int K : testCases) {
            System.out.println("\nK = " + K);

            // Binary search solution
            int result = preimageSizeFZF(K);
            System.out.println("Binary search result: " + result);

            // Brute force verification for small K
            if (K <= 10) {
                int bruteResult = bruteForce(K);
                System.out.println("Brute force result: " + bruteResult);
                System.out.println("Results match: " + (result == bruteResult));
            }

            // Show detailed search process
            demonstrateBinarySearch(K);
        }

        // Test edge cases
        System.out.println("\n=== EDGE CASES ===");
        System.out.println("K = 0: " + preimageSizeFZF(0) + " values");
        System.out.println("K = 1000000: " + preimageSizeFZF(1000000) + " values");

        // Explain why the answer is always 0 or 5
        System.out.println("\n=== MATHEMATICAL INSIGHT ===");
        System.out.println("Interesting fact: The answer is always either 0 or 5!");
        System.out.println("This is because trailingZeroes function has a specific pattern.");
        System.out.println("When a value K is achievable, there are always exactly 5 consecutive");
        System.out.println("integers n such that trailingZeroes(n) = K.");
    }
}
