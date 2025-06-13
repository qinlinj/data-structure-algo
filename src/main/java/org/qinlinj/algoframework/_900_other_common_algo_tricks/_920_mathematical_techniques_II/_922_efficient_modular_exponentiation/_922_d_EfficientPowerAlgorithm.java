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
    }
    
}
