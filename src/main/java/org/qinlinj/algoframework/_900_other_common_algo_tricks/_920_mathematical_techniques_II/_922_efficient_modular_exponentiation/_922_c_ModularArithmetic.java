package org.qinlinj.algoframework._900_other_common_algo_tricks._920_mathematical_techniques_II._922_efficient_modular_exponentiation;

/*
 * Modular Arithmetic Handler - Preventing Integer Overflow in Large Calculations
 *
 * Key Mathematical Property:
 * (a * b) % k = ((a % k) * (b % k)) % k
 *
 * Proof:
 * Let a = A*k + B and b = C*k + D (where A,B,C,D are constants)
 * Then: a*b = (A*k + B)(C*k + D) = AC*k² + AD*k + BC*k + BD
 * So: (a*b) % k = BD % k
 * Since: a % k = B and b % k = D
 * Therefore: ((a % k) * (b % k)) % k = (B * D) % k = BD % k
 *
 * This property extends to power operations:
 * a^n % k can be computed by applying modulo at each multiplication step
 *
 * Benefits:
 * 1. Prevents integer overflow during intermediate calculations
 * 2. Keeps all intermediate results bounded by the modulus
 * 3. Final result is mathematically equivalent to computing a^n first, then taking modulo
 */

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

    private static void demonstrateEfficiency(ModularCalculator calc) {
        int base = 2;
        int exponent = 1000;

        System.out.println("Calculating " + base + "^" + exponent + " mod " + MOD);

        // Basic method
        long startTime = System.nanoTime();
        int basicResult = calc.modPowerBasic(base, exponent);
        long basicTime = System.nanoTime() - startTime;

        // Fast method
        startTime = System.nanoTime();
        int fastResult = calc.modPowerFast(base, exponent);
        long fastTime = System.nanoTime() - startTime;

        System.out.println("Basic method result: " + basicResult + " (Time: " + basicTime + " ns)");
        System.out.println("Fast method result: " + fastResult + " (Time: " + fastTime + " ns)");
        System.out.println("Results match: " + (basicResult == fastResult));
        System.out.println("Speed improvement: " + (basicTime / (double) fastTime) + "x\n");
    }

    private static void explainModulusChoice() {
        System.out.println("The choice of 1337 as modulus in LeetCode problems:");
        System.out.println("1. Large enough to avoid trivial results");
        System.out.println("2. Small enough to fit comfortably in 32-bit integers");
        System.out.println("3. Not a power of 2, which makes the problem more interesting");
        System.out.println("4. Prime factorization: 1337 = 7 × 191 (both prime)");
        System.out.println("5. Provides good distribution of remainder values");
        System.out.println("6. Easy to remember and type!\n");
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