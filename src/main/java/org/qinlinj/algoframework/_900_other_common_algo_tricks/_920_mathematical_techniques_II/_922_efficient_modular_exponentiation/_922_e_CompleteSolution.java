package org.qinlinj.algoframework._900_other_common_algo_tricks._920_mathematical_techniques_II._922_efficient_modular_exponentiation;

import java.util.*;

public class _922_e_CompleteSolution {
    private static final int BASE = 1337;

    /**
     * Main solution class implementing all optimizations
     */
    public static class SuperPowerSolver {

        /**
         * Primary solution method - handles all edge cases and optimizations
         */
        public int superPow(int a, int[] b) {
            if (b == null || b.length == 0) return 1;

            // Convert array to list for easier manipulation
            List<Integer> bList = new ArrayList<>();
            for (int digit : b) {
                bList.add(digit);
            }

            return superPowRecursive(a, bList);
        }

        /**
         * Recursive helper implementing the core algorithm
         */
        private int superPowRecursive(int a, List<Integer> b) {
            // Base case: empty exponent means a^0 = 1
            if (b.isEmpty()) return 1;

            // Extract the last digit (rightmost, least significant)
            int lastDigit = b.remove(b.size() - 1);

            // Recursive decomposition: a^(xyz) = a^z * (a^(xy))^10
            int part1 = modPower(a, lastDigit);
            int part2 = modPower(superPowRecursive(a, b), 10);

            // Combine results with modular arithmetic
            return modMultiply(part1, part2);
        }
    }
}
