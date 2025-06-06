package org.qinlinj.algoframework._900_other_common_algo_tricks._910_mathematical_techniques_I._911_one_line_solutions;

public class _911_a_NimGame {
    /**
     * Determines if the first player can guarantee a win in Nim Game
     *
     * @param n number of stones
     * @return true if first player can win, false otherwise
     */
    public boolean canWinNim(int n) {
        // If starting with multiple of 4 stones, you lose
        // Otherwise, you can control opponent to face multiples of 4
        return n % 4 != 0;
    }
}
