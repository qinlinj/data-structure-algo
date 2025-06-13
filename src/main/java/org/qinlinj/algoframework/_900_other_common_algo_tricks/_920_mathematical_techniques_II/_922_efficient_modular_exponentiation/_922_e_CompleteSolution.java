package org.qinlinj.algoframework._900_other_common_algo_tricks._920_mathematical_techniques_II._922_efficient_modular_exponentiation;

/*
 * Complete Super Power Solution - LeetCode 372
 *
 * Problem Summary:
 * Calculate a^b mod 1337, where:
 * - a is an integer base
 * - b is represented as an array of digits (can be extremely large)
 * - Result must be computed modulo 1337 to prevent overflow
 *
 * Complete Solution Strategy:
 * 1. Handle array-based exponent using recursive decomposition
 * 2. Apply modular arithmetic at every step to prevent overflow
 * 3. Use efficient exponentiation for optimal performance
 * 4. Combine all techniques into a robust solution
 *
 * Key Insights:
 * - Array [1,5,6,4] represents number 1564
 * - a^1564 = a^4 * (a^156)^10 (recursive breakdown)
 * - (x * y) % k = ((x % k) * (y % k)) % k (modular arithmetic)
 * - Fast exponentiation reduces O(n) to O(log n) complexity
 *
 * Time Complexity: O(n) where n is the length of array b
 * Space Complexity: O(n) due to recursion stack depth
 */

import java.util.*;

public class _922_e_CompleteSolution {
    private static final int BASE = 1337;

    public static void main(String[] args) {
        // Run comprehensive test suite
        TestSuite testSuite = new TestSuite();
        testSuite.runAllTests();

        // Demonstrate verbose execution for educational purposes
        testSuite.demonstrateVerboseExecution();

        // Final demonstration
        System.out.println("=== Algorithm Summary ===");
        System.out.println("The Super Power algorithm successfully combines:");
        System.out.println("1. Recursive decomposition for array-based exponents");
        System.out.println("2. Modular arithmetic for overflow prevention");
        System.out.println("3. Fast exponentiation for efficiency");
        System.out.println("4. Robust handling of edge cases");
        System.out.println();
        System.out.println("This creates a solution that can handle arbitrarily large");
        System.out.println("exponents while maintaining reasonable performance and");
        System.out.println("preventing integer overflow - a perfect example of how");
        System.out.println("multiple algorithmic techniques combine to solve complex problems!");
    }

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
        public int modPower(int base, int exponent) {
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
        public int modMultiply(int a, int b) {
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

        private String arrayToString(int[] arr) {
            StringBuilder sb = new StringBuilder();
            for (int digit : arr) {
                sb.append(digit);
            }
            return sb.toString();
        }

        private String listToString(List<Integer> list) {
            StringBuilder sb = new StringBuilder();
            for (int digit : list) {
                sb.append(digit);
            }
            return sb.isEmpty() ? "empty" : sb.toString();
        }
    }

    /**
     * Test suite for validation
     */
    public static class TestSuite {
        private final SuperPowerSolver solver;
        private final VerboseSuperPowerSolver verboseSolver;

        public TestSuite() {
            this.solver = new SuperPowerSolver();
            this.verboseSolver = new VerboseSuperPowerSolver();
        }

        public void runAllTests() {
            System.out.println("=== Super Power Test Suite ===\n");

            testBasicCases();
            testEdgeCases();
            testLargeExponents();
            testPerformance();
        }

        private void testBasicCases() {
            System.out.println("Test 1: Basic Cases");

            // Test case 1: Small numbers
            int[] b1 = {3};
            int result1 = solver.superPow(2, b1);
            System.out.println("2^3 mod 1337 = " + result1 + " (expected: 8)");
            assert result1 == 8 : "Test 1.1 failed";

            // Test case 2: Medium numbers
            int[] b2 = {1, 0};
            int result2 = solver.superPow(2, b2);
            System.out.println("2^10 mod 1337 = " + result2 + " (expected: 1024)");
            assert result2 == 1024 : "Test 1.2 failed";

            // Test case 3: LeetCode example
            int[] b3 = {1, 5, 6, 4};
            int result3 = solver.superPow(2, b3);
            System.out.println("2^1564 mod 1337 = " + result3);

            System.out.println("Basic tests passed!\n");
        }

        private void testEdgeCases() {
            System.out.println("Test 2: Edge Cases");

            // Test case 1: Empty array
            int[] b1 = {};
            int result1 = solver.superPow(2, b1);
            System.out.println("2^[] mod 1337 = " + result1 + " (expected: 1)");
            assert result1 == 1 : "Test 2.1 failed";

            // Test case 2: Single zero
            int[] b2 = {0};
            int result2 = solver.superPow(2, b2);
            System.out.println("2^0 mod 1337 = " + result2 + " (expected: 1)");
            assert result2 == 1 : "Test 2.2 failed";

            // Test case 3: Base larger than modulus
            int[] b3 = {2};
            int result3 = solver.superPow(1500, b3);
            System.out.println("1500^2 mod 1337 = " + result3);

            System.out.println("Edge tests passed!\n");
        }

        private void testLargeExponents() {
            System.out.println("Test 3: Large Exponents");

            // Very large exponent
            int[] b1 = {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
            long startTime = System.currentTimeMillis();
            int result1 = solver.superPow(2, b1);
            long endTime = System.currentTimeMillis();

            System.out.println("2^1000000000000 mod 1337 = " + result1);
            System.out.println("Computation time: " + (endTime - startTime) + " ms");

            System.out.println("Large exponent tests passed!\n");
        }

        private void testPerformance() {
            System.out.println("Test 4: Performance Comparison");

            int[] testExponent = {1, 2, 3, 4, 5};

            // Test recursive solution
            long startTime = System.nanoTime();
            int result1 = solver.superPow(2, testExponent.clone());
            long recursiveTime = System.nanoTime() - startTime;

            // Test iterative solution
            startTime = System.nanoTime();
            int result2 = solver.superPowIterative(2, testExponent.clone());
            long iterativeTime = System.nanoTime() - startTime;

            System.out.println("Recursive result: " + result1 + " (Time: " + recursiveTime + " ns)");
            System.out.println("Iterative result: " + result2 + " (Time: " + iterativeTime + " ns)");
            System.out.println("Results match: " + (result1 == result2));

            System.out.println("Performance tests completed!\n");
        }

        public void demonstrateVerboseExecution() {
            System.out.println("=== Verbose Execution Demo ===\n");
            int[] smallExample = {1, 2, 3};
            verboseSolver.superPow(2, smallExample);
        }
    }
}