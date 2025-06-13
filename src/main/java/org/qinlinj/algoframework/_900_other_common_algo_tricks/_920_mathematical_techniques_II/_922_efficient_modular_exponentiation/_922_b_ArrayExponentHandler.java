package org.qinlinj.algoframework._900_other_common_algo_tricks._920_mathematical_techniques_II._922_efficient_modular_exponentiation;

import java.util.*;

public class _922_b_ArrayExponentHandler {
    public static void main(String[] args) {
        System.out.println("=== Array Exponent Handler Demo ===\n");

        ExponentProcessor processor = new ExponentProcessor();

        // Demo 1: Small example to show the concept
        System.out.println("Demo 1: Understanding Array Representation");
        int[] smallArray = {1, 2, 3};
        System.out.println("Array [1,2,3] represents the number: 123");
        System.out.println("So 2^[1,2,3] means 2^123");

        // Convert to list for recursive method
        List<Integer> smallList = new ArrayList<>();
        for (int digit : smallArray) {
            smallList.add(digit);
        }

        // Note: These results will be very large, this is just for concept demonstration
        System.out.println("Calculating 2^123 using recursive method...");
        try {
            // For very large results, we'd need BigInteger or modular arithmetic
            System.out.println("(Result would be astronomically large)\n");
        } catch (Exception e) {
            System.out.println("Result too large for standard data types\n");
        }

        // Demo 2: Step-by-step breakdown
        System.out.println("Demo 2: Step-by-step Recursive Breakdown");
        demonstrateRecursiveSteps();

        // Demo 3: Why we need this approach
        System.out.println("\nDemo 3: Why Array Representation is Necessary");
        demonstrateNeedForArrays();

        // Demo 4: Handling edge cases
        System.out.println("\nDemo 4: Edge Cases");
        demonstrateEdgeCases();
    }

    private static void demonstrateRecursiveSteps() {
        System.out.println("For exponent [1,5,6,4] (representing 1564):");
        System.out.println("Step 1: Extract last digit 4, remaining [1,5,6]");
        System.out.println("        a^1564 = a^4 * (a^156)^10");
        System.out.println();
        System.out.println("Step 2: Extract last digit 6, remaining [1,5]");
        System.out.println("        a^156 = a^6 * (a^15)^10");
        System.out.println();
        System.out.println("Step 3: Extract last digit 5, remaining [1]");
        System.out.println("        a^15 = a^5 * (a^1)^10");
        System.out.println();
        System.out.println("Step 4: Extract last digit 1, remaining []");
        System.out.println("        a^1 = a^1 * (1)^10 = a");
        System.out.println();
        System.out.println("Base case: empty array means exponent 0, result 1");
    }

    private static void demonstrateNeedForArrays() {
        System.out.println("Consider these large exponents:");
        System.out.println("- 2^(10^100): A googol-sized exponent");
        System.out.println("- 3^(factorial(100)): Even larger exponent");
        System.out.println("- These cannot fit in any standard integer type");
        System.out.println("- int max value: " + Integer.MAX_VALUE);
        System.out.println("- long max value: " + Long.MAX_VALUE);
        System.out.println("- But array can represent arbitrarily large numbers!");
    }

    private static void demonstrateEdgeCases() {
        System.out.println("Edge Case 1: Single digit array [5]");
        System.out.println("  This simply means a^5");
        System.out.println();
        System.out.println("Edge Case 2: Array with zeros [1,0,0,0]");
        System.out.println("  This represents a^1000");
        System.out.println();
        System.out.println("Edge Case 3: Empty array []");
        System.out.println("  This represents a^0 = 1 (base case)");
        System.out.println();
        System.out.println("Edge Case 4: Leading zeros [0,0,1,2,3]");
        System.out.println("  Still represents 123, leading zeros ignored");
    }

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
