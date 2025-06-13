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
    }
}
