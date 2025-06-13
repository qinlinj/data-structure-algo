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

    }
}
