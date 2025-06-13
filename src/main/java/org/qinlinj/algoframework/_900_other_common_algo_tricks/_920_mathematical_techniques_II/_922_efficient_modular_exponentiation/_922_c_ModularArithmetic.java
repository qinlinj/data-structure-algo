package org.qinlinj.algoframework._900_other_common_algo_tricks._920_mathematical_techniques_II._922_efficient_modular_exponentiation;

public class _922_c_ModularArithmetic {
    private static final int MOD = 1337;

    public static void main(String[] args) {
        System.out.println("=== Modular Arithmetic Demo ===\n");

        ModularCalculator calc = new ModularCalculator(MOD);
        SuperPowerModular superCalc = new SuperPowerModular();

        // Demo 1: Basic modular arithmetic properties
        System.out.println("Demo 1: Basic Modular Arithmetic Properties");
        demonstrateBasicProperties(calc);

        // Demo 2: Overflow prevention
        System.out.println("Demo 2: Overflow Prevention");
        calc.demonstrateOverflowPrevention(Integer.MAX_VALUE / 2, Integer.MAX_VALUE / 2);

        // Demo 3: Power calculations with modular arithmetic
        System.out.println("Demo 3: Modular Power Calculations");
        superCalc.demonstratePowerCalculation(2, 5);
        superCalc.demonstratePowerCalculation(3, 4);

        // Demo 4: Efficiency comparison
        System.out.println("Demo 4: Efficiency Comparison (Basic vs Fast Power)");
        demonstrateEfficiency(calc);

        // Demo 5: Why 1337?
        System.out.println("Demo 5: Why Use Modulo 1337?");
        explainModulusChoice();
    }

    private static void demonstrateBasicProperties(ModularCalculator calc) {
        int a = 123, b = 456;

        System.out.println("Property: (a * b) % mod = ((a % mod) * (b % mod)) % mod");
        System.out.println("a = " + a + ", b = " + b + ", mod = " + MOD);

        // Direct calculation
        int direct = (int) ((long) a * b % MOD);
        System.out.println("Direct: (" + a + " * " + b + ") % " + MOD + " = " + direct);

        // Modular calculation
        int modular = calc.modMultiply(a, b);
        System.out.println("Modular: ((" + a + " % " + MOD + ") * (" + b + " % " + MOD + ")) % " + MOD + " = " + modular);

        System.out.println("Results match: " + (direct == modular) + "\n");
    }

    public static class ModularCalculator {
        private final int modulus;

        public ModularCalculator(int modulus) {
            this.modulus = modulus;
        }

        /**
         * Modular multiplication: (a * b) % mod
         * Applies modulo to operands first to prevent overflow
         */
        public int modMultiply(long a, long b) {
            return (int) (((a % modulus) * (b % modulus)) % modulus);
        }


        /**
         * Modular addition: (a + b) % mod
         */
        public int modAdd(long a, long b) {
            return (int) (((a % modulus) + (b % modulus)) % modulus);
        }

        /**
         * Modular power: a^b % mod (basic iterative approach)
         * Applies modulo at each step to prevent overflow
         */
        public int modPowerBasic(int base, int exponent) {
            if (exponent == 0) return 1;

            base %= modulus; // Reduce base first
            int result = 1;

            for (int i = 0; i < exponent; i++) {
                result = modMultiply(result, base);
            }

            return result;
        }

        /**
         * Fast modular power: a^b % mod (using binary exponentiation)
         * Much more efficient for large exponents
         */
        public int modPowerFast(int base, int exponent) {
            if (exponent == 0) return 1;

            base %= modulus;
            int result = 1;

            while (exponent > 0) {
                // If exponent is odd, multiply result by current base
                if (exponent % 2 == 1) {
                    result = modMultiply(result, base);
                }
                // Square the base and halve the exponent
                base = modMultiply(base, base);
                exponent /= 2;
            }

            return result;
        }


        /**
         * Demonstrates overflow prevention
         */
        public void demonstrateOverflowPrevention(int a, int b) {
            System.out.println("=== Overflow Prevention Demo ===");
            System.out.println("Calculating (" + a + " * " + b + ") % " + modulus);

            // Method 1: Direct multiplication (might overflow)
            try {
                long directResult = (long) a * b;
                int directMod = (int) (directResult % modulus);
                System.out.println("Direct: " + a + " * " + b + " = " + directResult +
                        ", mod " + modulus + " = " + directMod);
            } catch (Exception e) {
                System.out.println("Direct method failed due to overflow");
            }

            // Method 2: Modular multiplication (safe)
            int safeResult = modMultiply(a, b);
            System.out.println("Safe: ((" + a + " % " + modulus + ") * (" +
                    b + " % " + modulus + ")) % " + modulus + " = " + safeResult);
            System.out.println();
        }
    }

    /**
     * Specific implementation for the Super Power problem
     */
    public static class SuperPowerModular {
        private final ModularCalculator calc;

        public SuperPowerModular() {
            this.calc = new ModularCalculator(MOD);
        }

        /**
         * Safe modular power for single digit exponents (0-9)
         * Used in the main Super Power algorithm
         */
        public int safePower(int base, int exponent) {
            return calc.modPowerBasic(base, exponent);
        }

        /**
         * Demonstrates the step-by-step modular arithmetic process
         */
        public int demonstratePowerCalculation(int base, int exponent) {
            System.out.println("Calculating " + base + "^" + exponent + " mod " + MOD + ":");

            base %= MOD;
            System.out.println("Step 1: Reduce base: " + base + " mod " + MOD + " = " + base);

            int result = 1;
            System.out.println("Step 2: Initialize result = 1");

            for (int i = 0; i < exponent; i++) {
                int oldResult = result;
                result = calc.modMultiply(result, base);
                System.out.println("Step " + (i + 3) + ": " + oldResult + " * " + base +
                        " = " + (oldResult * base) + ", mod " + MOD + " = " + result);
            }

            System.out.println("Final result: " + result + "\n");
            return result;
        }
    }
}
