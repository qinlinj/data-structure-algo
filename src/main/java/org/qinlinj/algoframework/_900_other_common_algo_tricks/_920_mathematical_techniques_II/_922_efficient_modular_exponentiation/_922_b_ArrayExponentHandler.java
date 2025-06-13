package org.qinlinj.algoframework._900_other_common_algo_tricks._920_mathematical_techniques_II._922_efficient_modular_exponentiation;

import java.util.*;

public class _922_b_ArrayExponentHandler {
    /**
     * Demonstrates different approaches to handle array-based exponents
     */
    public static class ExponentProcessor {
        /**
         * Method 1: Recursive approach (recommended for this problem)
         * Processes the array by removing the last digit and recursing
         */
        public long processExponentRecursive(int base, List<Integer> exponentArray) {
            if (exponentArray.isEmpty()) {
                return 1; // base^0 = 1
            }

            // Remove last digit (rightmost, least significant)
            int lastDigit = exponentArray.remove(exponentArray.size() - 1);

            // Recursive breakdown: base^(abc) = base^c * (base^(ab))^10
            long part1 = fastPower(base, lastDigit);
            long part2 = fastPower(processExponentRecursive(base, exponentArray), 10);

            return part1 * part2;
        }

        /**
         * Method 2: Iterative approach
         * Processes array from right to left, building up the result
         */
        public long processExponentIterative(int base, int[] exponentArray) {
            long result = 1;
            long currentBase = base;

            // Process from right to left (least to most significant digit)
            for (int i = exponentArray.length - 1; i >= 0; i--) {
                int digit = exponentArray[i];

                // Add contribution of current digit
                result *= fastPower(currentBase, digit);

                // Update base for next position (multiply by 10)
                if (i > 0) { // Don't do this for the last iteration
                    currentBase = fastPower(currentBase, 10);
                }
            }

            return result;
        }

        /**
         * Simple power function for demonstration
         * In real implementation, this would include modular arithmetic
         */
        private long fastPower(long base, int exponent) {
            if (exponent == 0) return 1;
            if (exponent == 1) return base;

            long result = 1;
            while (exponent > 0) {
                if (exponent % 2 == 1) {
                    result *= base;
                }
                base *= base;
                exponent /= 2;
            }
            return result;
        }

    }
}
