package org.qinlinj.algoframework._900_other_common_algo_tricks._920_mathematical_techniques_II._922_efficient_modular_exponentiation;

public class _922_d_EfficientPowerAlgorithm {
    private static final int MOD = 1337;

    public static class PowerCalculator {

        /**
         * Traditional O(n) power calculation
         * Included for comparison purposes
         */
        public long basicPower(long base, int exponent) {
            if (exponent == 0) return 1;
            if (exponent == 1) return base;

            long result = 1;
            for (int i = 0; i < exponent; i++) {
                result *= base;
            }
            return result;
        }

        /**
         * Fast O(log n) power calculation - Recursive approach
         * More intuitive but uses O(log n) stack space
         */
        public long fastPowerRecursive(long base, int exponent) {
            // Base cases
            if (exponent == 0) return 1;
            if (exponent == 1) return base;

            if (exponent % 2 == 0) {
                // Even exponent: a^n = (a^(n/2))^2
                long half = fastPowerRecursive(base, exponent / 2);
                return half * half;
            } else {
                // Odd exponent: a^n = a * a^(n-1)
                return base * fastPowerRecursive(base, exponent - 1);
            }
        }

        /**
         * Fast O(log n) power calculation - Iterative approach
         * More efficient in terms of space, O(1) space complexity
         */
        public long fastPowerIterative(long base, int exponent) {
            if (exponent == 0) return 1;

            long result = 1;
            long currentPower = base;

            while (exponent > 0) {
                // If current bit is 1, multiply result by current power
                if (exponent % 2 == 1) {
                    result *= currentPower;
                }
                // Square the current power and move to next bit
                currentPower *= currentPower;
                exponent /= 2;
            }

            return result;
        }

        /**
         * Modular fast power - for use in Super Power problem
         * Combines fast exponentiation with modular arithmetic
         */
        public int modularFastPower(int base, int exponent, int modulus) {
            if (exponent == 0) return 1;

            base %= modulus;
            long result = 1;
            long currentPower = base;

            while (exponent > 0) {
                if (exponent % 2 == 1) {
                    result = (result * currentPower) % modulus;
                }
                currentPower = (currentPower * currentPower) % modulus;
                exponent /= 2;
            }

            return (int) result;
        }

        /**
         * Demonstrates the step-by-step process of binary exponentiation
         */
        public long demonstrateBinaryExponentiation(int base, int exponent) {
            System.out.println("=== Binary Exponentiation Demo: " + base + "^" + exponent + " ===");

            // Show binary representation of exponent
            String binary = Integer.toBinaryString(exponent);
            System.out.println("Exponent " + exponent + " in binary: " + binary);
            System.out.println("This means: " + exponent + " = " +
                    describeBinaryDecomposition(exponent));
            System.out.println();

            long result = 1;
            long currentPower = base;
            int step = 1;
            int tempExponent = exponent;

            System.out.println("Step-by-step calculation:");
            System.out.println("Initialize: result = 1, currentPower = " + base);
            System.out.println();

            while (tempExponent > 0) {
                boolean shouldMultiply = (tempExponent % 2 == 1);

                System.out.println("Step " + step + ":");
                System.out.println("  Current exponent: " + tempExponent +
                        " (rightmost bit: " + (tempExponent % 2) + ")");
                System.out.println("  Current power: " + currentPower);

                if (shouldMultiply) {
                    long oldResult = result;
                    result *= currentPower;
                    System.out.println("  Bit is 1, so multiply: " + oldResult +
                            " * " + currentPower + " = " + result);
                } else {
                    System.out.println("  Bit is 0, so skip multiplication");
                }

                currentPower *= currentPower;
                tempExponent /= 2;

                System.out.println("  Square current power: " + (currentPower / currentPower) +
                        "^2 = " + currentPower);
                System.out.println("  Shift exponent right: " + tempExponent);
                System.out.println();

                step++;
            }

            System.out.println("Final result: " + result);
            return result;
        }

        /**
         * Helper method to describe binary decomposition
         */
        private String describeBinaryDecomposition(int number) {
            StringBuilder sb = new StringBuilder();
            int power = 0;
            boolean first = true;

            while (number > 0) {
                if (number % 2 == 1) {
                    if (!first) sb.append(" + ");
                    sb.append("2^").append(power);
                    first = false;
                }
                number /= 2;
                power++;
            }

            return sb.toString();
        }
    }


    /**
     * Performance comparison utility
     */
    public static class PerformanceAnalyzer {

        public void compareAlgorithms(int base, int maxExponent) {
            System.out.println("=== Performance Comparison ===");
            System.out.println("Base: " + base + ", Testing exponents from 1 to " + maxExponent);
            System.out.println();

            PowerCalculator calc = new PowerCalculator();

            // Test different exponent sizes
            int[] testExponents = {10, 20, 50, 100, 500, 1000};

            for (int exp : testExponents) {
                if (exp > maxExponent) continue;

                System.out.println("Exponent: " + exp);

                // Basic algorithm timing (only for small exponents to avoid overflow)
                if (exp <= 20) {
                    long startTime = System.nanoTime();
                    long basicResult = calc.basicPower(base, exp);
                    long basicTime = System.nanoTime() - startTime;
                    System.out.println("  Basic O(n):     " + basicResult +
                            " (Time: " + basicTime + " ns)");
                }

                // Fast algorithm timing
                long startTime = System.nanoTime();
                long fastResult = calc.fastPowerIterative(base, exp);
                long fastTime = System.nanoTime() - startTime;
                System.out.println("  Fast O(log n):  " + fastResult +
                        " (Time: " + fastTime + " ns)");
                System.out.println();
            }
        }

        public void analyzeBitOperations(int exponent) {
            System.out.println("=== Bit Operations Analysis for Exponent " + exponent + " ===");

            String binary = Integer.toBinaryString(exponent);
            System.out.println("Binary representation: " + binary);
            System.out.println("Number of bits: " + binary.length());
            System.out.println("Number of 1s: " + binary.replaceAll("0", "").length());
            System.out.println();

            System.out.println("Traditional algorithm: " + (exponent - 1) + " multiplications");
            System.out.println("Fast algorithm: ~" + (binary.length() - 1) +
                    " squarings + " + binary.replaceAll("0", "").length() +
                    " multiplications");
            System.out.println("Total operations: ~" +
                    (binary.length() - 1 + binary.replaceAll("0", "").length()));
            System.out.println();
        }
    }

}
