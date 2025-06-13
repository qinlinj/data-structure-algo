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

        /**
         * Efficient modular exponentiation
         * Uses binary exponentiation for O(log k) complexity
         */
        private int modPower(int base, int exponent) {
            if (exponent == 0) return 1;

            base %= BASE;
            int result = 1;

            while (exponent > 0) {
                if (exponent % 2 == 1) {
                    result = modMultiply(result, base);
                }
                base = modMultiply(base, base);
                exponent /= 2;
            }

            return result;
        }

        /**
         * Safe modular multiplication to prevent overflow
         */
        private int modMultiply(int a, int b) {
            return (int) (((long) a * b) % BASE);
        }

        /**
         * Alternative iterative solution (for comparison)
         */
        public int superPowIterative(int a, int[] b) {
            if (b == null || b.length == 0) return 1;

            int result = 1;
            a %= BASE;

            // Process digits from left to right
            for (int digit : b) {
                // Multiply result by 10th power, then by a^digit
                result = modMultiply(modPower(result, 10), modPower(a, digit));
            }

            return result;
        }
    }


    /**
     * Enhanced solution with detailed logging for educational purposes
     */
    public static class VerboseSuperPowerSolver extends SuperPowerSolver {
        private int recursionDepth = 0;

        @Override
        public int superPow(int a, int[] b) {
            System.out.println("=== Starting Super Power Calculation ===");
            System.out.println("Base: " + a);
            System.out.println("Exponent array: " + Arrays.toString(b));
            System.out.println("Target: " + a + "^" + arrayToString(b) + " mod " + BASE);
            System.out.println();

            recursionDepth = 0;
            int result = super.superPow(a, b);

            System.out.println("=== Final Result: " + result + " ===");
            return result;
        }

        @Override
        protected int superPowRecursive(int a, List<Integer> b) {
            String indent = "  ".repeat(recursionDepth);
            recursionDepth++;

            System.out.println(indent + "Recursion level " + (recursionDepth - 1) + ":");
            System.out.println(indent + "Processing exponent: " + listToString(b));

            if (b.isEmpty()) {
                System.out.println(indent + "Base case reached: a^0 = 1");
                recursionDepth--;
                return 1;
            }

            int lastDigit = b.remove(b.size() - 1);
            System.out.println(indent + "Extracted last digit: " + lastDigit);
            System.out.println(indent + "Remaining exponent: " + listToString(b));

            System.out.println(indent + "Computing a^" + lastDigit + ":");
            int part1 = modPower(a, lastDigit);
            System.out.println(indent + "  Result: " + part1);

            System.out.println(indent + "Computing (a^remaining)^10:");
            int recursiveResult = superPowRecursive(a, b);
            int part2 = modPower(recursiveResult, 10);
            System.out.println(indent + "  Recursive result: " + recursiveResult);
            System.out.println(indent + "  After ^10: " + part2);

            int finalResult = modMultiply(part1, part2);
            System.out.println(indent + "Combining: " + part1 + " * " + part2 +
                    " mod " + BASE + " = " + finalResult);

            recursionDepth--;
            return finalResult;
        }
    }
}
