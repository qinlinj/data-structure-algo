package org.qinlinj.algoframework._900_other_common_algo_tricks._920_mathematical_techniques_II._922_efficient_modular_exponentiation;

import java.util.*;

public class _922_a_SuperPowerMain {
    
    private static final int BASE = 1337;

    /**
     * Main solution for Super Power problem
     * Time Complexity: O(n) where n is length of array b
     * Space Complexity: O(n) due to recursion stack
     */
    public int superPow(int a, int[] b) {
        // Convert array to list for easier manipulation
        List<Integer> bList = new ArrayList<>();
        for (int digit : b) {
            bList.add(digit);
        }
        return superPowHelper(a, bList);
    }
}
