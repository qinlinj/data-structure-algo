package org.qinlinj.algoframework._900_other_common_algo_tricks._910_mathematical_techniques_I._911_one_line_solutions;

/**
 * STONE GAME - Brain Teaser Algorithm Problem
 * <p>
 * Problem: You and your friend have piles of stones in a row. Take turns picking from
 * either end. The player with more stones wins. You go first. Can you guarantee a win?
 * <p>
 * Key Constraints:
 * - Even number of piles (so both players get same number of piles)
 * - Odd total number of stones (so there's always a winner)
 * - Both players play optimally
 * <p>
 * Hidden Insight: The first player can ALWAYS win! Here's why:
 * - Divide piles into two groups: odd-indexed (1,3,5...) and even-indexed (2,4,6...)
 * - Since total stones is odd, one group has more stones than the other
 * - First player can force themselves to get ALL piles from the larger group
 * - By choosing the first pile strategically, you control which group you collect
 * <p>
 * Strategy: Always return true - first player wins with optimal play!
 * <p>
 * Time Complexity: O(1)
 * Space Complexity: O(1)
 */
public class _911_b_StoneGame {

    public static void main(String[] args) {
        _911_b_StoneGame game = new _911_b_StoneGame();

        System.out.println("=== Stone Game Test Cases ===");

        // Test case 1: [2, 1, 9, 5]
        int[] piles1 = {2, 1, 9, 5};
        boolean result1 = game.stoneGame(piles1);
        System.out.println("Result: " + result1);
        game.explainStrategy(piles1);

        System.out.println("\n" + "=".repeat(50));

        // Test case 2: [3, 7, 2, 3]
        int[] piles2 = {3, 7, 2, 3};
        boolean result2 = game.stoneGame(piles2);
        System.out.println("Result: " + result2);
        game.explainStrategy(piles2);

        System.out.println("\n" + "=".repeat(50));

        // Test case 3: [5, 3, 4, 5]
        int[] piles3 = {5, 3, 4, 5};
        boolean result3 = game.stoneGame(piles3);
        System.out.println("Result: " + result3);
        game.explainStrategy(piles3);

        // Demonstrate the mathematical proof
        System.out.println("\n=== Mathematical Proof ===");
        System.out.println("Why first player ALWAYS wins:");
        System.out.println("1. Even number of piles → both players get same number of piles");
        System.out.println("2. Odd total stones → one group (odd/even positions) has more stones");
        System.out.println("3. First player can choose which group to collect entirely");
        System.out.println("4. Therefore, first player can guarantee the larger group");
        System.out.println("5. Result: First player always wins!");
    }

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
