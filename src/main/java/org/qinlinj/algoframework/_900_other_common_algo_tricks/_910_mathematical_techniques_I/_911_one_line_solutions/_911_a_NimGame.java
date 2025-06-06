package org.qinlinj.algoframework._900_other_common_algo_tricks._910_mathematical_techniques_I._911_one_line_solutions;

/**
 * NIM GAME - Brain Teaser Algorithm Problem
 * <p>
 * Problem: You and your friend have a pile of stones. You take turns removing 1-3 stones.
 * The player who takes the last stone wins. You go first. Can you guarantee a win?
 * <p>
 * Key Insight: Think backwards! To win, you need 1-3 stones on your final turn.
 * Force your opponent to face exactly 4 stones - no matter what they take (1-3),
 * you'll have 1-3 stones left to win. This creates a pattern: multiples of 4 are losing positions.
 * <p>
 * Strategy: If stones % 4 != 0, you can always force opponent into multiples of 4.
 * If stones % 4 == 0, you're already in a losing position.
 * <p>
 * Time Complexity: O(1)
 * Space Complexity: O(1)
 */
public class _911_a_NimGame {

    public static void main(String[] args) {
        _911_a_NimGame game = new _911_a_NimGame();

        // Test cases
        System.out.println("=== Nim Game Test Cases ===");

        // Test case 1: 4 stones - should return false
        int stones1 = 4;
        boolean result1 = game.canWinNim(stones1);
        System.out.println("Stones: " + stones1 + " -> Can win: " + result1);
        System.out.println("Explanation: With 4 stones, no matter what you take (1-3), opponent can take all remaining stones");

        // Test case 2: 1 stone - should return true
        int stones2 = 1;
        boolean result2 = game.canWinNim(stones2);
        System.out.println("Stones: " + stones2 + " -> Can win: " + result2);

        // Test case 3: 2 stones - should return true
        int stones3 = 2;
        boolean result3 = game.canWinNim(stones3);
        System.out.println("Stones: " + stones3 + " -> Can win: " + result3);

        // Test case 4: 8 stones - should return false (multiple of 4)
        int stones4 = 8;
        boolean result4 = game.canWinNim(stones4);
        System.out.println("Stones: " + stones4 + " -> Can win: " + result4);

        // Test case 5: 7 stones - should return true
        int stones5 = 7;
        boolean result5 = game.canWinNim(stones5);
        System.out.println("Stones: " + stones5 + " -> Can win: " + result5);
        System.out.println("Explanation: Take 3 stones, leave 4 for opponent (losing position)");

        // Demonstrate the pattern
        System.out.println("\n=== Pattern Demonstration ===");
        for (int i = 1; i <= 12; i++) {
            boolean canWin = game.canWinNim(i);
            String status = canWin ? "WIN" : "LOSE";
            System.out.printf("Stones: %2d -> %s (mod 4 = %d)\n", i, status, i % 4);
        }
    }

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