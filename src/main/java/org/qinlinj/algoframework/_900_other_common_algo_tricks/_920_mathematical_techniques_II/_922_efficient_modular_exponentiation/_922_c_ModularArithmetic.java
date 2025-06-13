package org.qinlinj.algoframework._900_other_common_algo_tricks._920_mathematical_techniques_II._922_efficient_modular_exponentiation;

public class _922_c_ModularArithmetic {
    private static final int MOD = 1337;

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
    }
}
