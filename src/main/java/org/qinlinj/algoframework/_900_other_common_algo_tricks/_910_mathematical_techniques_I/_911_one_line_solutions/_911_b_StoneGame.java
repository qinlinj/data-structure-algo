package org.qinlinj.algoframework._900_other_common_algo_tricks._910_mathematical_techniques_I._911_one_line_solutions;

public class _911_b_StoneGame {
    /**
     * Determines if the first player can guarantee a win in Stone Game
     *
     * @param piles array of stone piles
     * @return true - first player can always win with optimal strategy
     */
    public boolean stoneGame(int[] piles) {
        // Mathematical proof: first player always wins
        return true;
    }

    /**
     * Demonstrates why first player always wins
     */
    public void explainStrategy(int[] piles) {
        System.out.println("Piles: " + java.util.Arrays.toString(piles));

        // Calculate odd-indexed and even-indexed sums
        int oddSum = 0, evenSum = 0;
        for (int i = 0; i < piles.length; i++) {
            if (i % 2 == 0) {
                oddSum += piles[i];  // 0-indexed, so even index = odd position
            } else {
                evenSum += piles[i]; // 0-indexed, so odd index = even position
            }
        }

        System.out.println("Odd positions (1,3,5...): " + oddSum);
        System.out.println("Even positions (2,4,6...): " + evenSum);

        String strategy = oddSum > evenSum ?
                "Take first pile to force collecting all odd-positioned piles" :
                "Take last pile to force collecting all even-positioned piles";

        System.out.println("First player strategy: " + strategy);
        System.out.println("Guaranteed win with: " + Math.max(oddSum, evenSum) + " stones");
    }
}
