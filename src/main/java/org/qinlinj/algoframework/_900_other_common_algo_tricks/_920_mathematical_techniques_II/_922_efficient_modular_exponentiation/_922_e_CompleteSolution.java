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
    }
}
