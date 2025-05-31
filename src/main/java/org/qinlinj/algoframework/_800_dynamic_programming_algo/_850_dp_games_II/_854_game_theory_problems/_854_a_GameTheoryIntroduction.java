package org.qinlinj.algoframework._800_dynamic_programming_algo._850_dp_games_II._854_game_theory_problems;

import java.util.*;

/**
 * STONE GAME PROBLEM - INTRODUCTION AND GAME THEORY ANALYSIS
 * <p>
 * Problem Description:
 * - Two players face a row of stone piles represented by array piles[]
 * - Players take turns picking from either end (leftmost or rightmost pile)
 * - Each player wants to maximize their total stones
 * - Both players play optimally (make the best possible moves)
 * - Goal: Determine the score difference between first and second player
 * <p>
 * Game Theory Concepts:
 * 1. Perfect Information Game: Both players know all available moves
 * 2. Zero-Sum Game: One player's gain equals the other's loss
 * 3. Optimal Strategy: Each player makes moves to maximize their advantage
 * 4. Minimax Principle: Maximize your gain while minimizing opponent's gain
 * <p>
 * Key Insight:
 * - This is a generalized version of LeetCode 877 (Stone Game)
 * - Unlike the original where first player always wins, this version can have any outcome
 * - Example: [1, 100, 3] - second player wins by taking 100
 * <p>
 * Related Problems:
 * - LeetCode 486: Predict the Winner
 * - LeetCode 877: Stone Game
 * - General game theory problems with optimal play
 * <p>
 * Solution Approach:
 * - Use Dynamic Programming with game state representation
 * - Track both players' optimal scores simultaneously
 * - Consider all possible moves and their consequences
 */

public class _854_a_GameTheoryIntroduction {

    /**
     * Demonstrates the basic game theory concepts with simple examples
     */
    public void demonstrateGameTheory() {
        System.out.println("=== Stone Game - Game Theory Introduction ===");
        System.out.println();

        // Example 1: Simple case where first player loses
        int[] piles1 = {1, 100, 3};
        System.out.println("Example 1: " + Arrays.toString(piles1));
        System.out.println("Analysis:");
        System.out.println("- First player can take 1 or 3");
        System.out.println("- Either choice leaves 100 for second player");
        System.out.println("- Second player takes 100, then takes remaining pile");
        System.out.println("- Result: First player gets 4, Second player gets 100");
        System.out.println("- Score difference: 4 - 100 = -96 (first player loses)");
        System.out.println();

        // Example 2: Case where first player can win
        int[] piles2 = {1, 5, 233, 7};
        System.out.println("Example 2: " + Arrays.toString(piles2));
        System.out.println("Analysis:");
        System.out.println("- First player takes 1, leaving [5, 233, 7]");
        System.out.println("- Second player must choose 5 or 7");
        System.out.println("- Either way, first player gets 233 on next turn");
        System.out.println("- Possible outcomes:");
        System.out.println("  * First: 1 + 233 = 234, Second: 5 + 7 = 12");
        System.out.println("  * Score difference: 234 - 12 = 222 (first player wins)");
        System.out.println();

        // Example 3: Demonstrate the importance of optimal play
        int[] piles3 = {3, 7, 2, 3};
        System.out.println("Example 3: " + Arrays.toString(piles3));
        System.out.println("Demonstrating optimal vs suboptimal play:");
        demonstrateOptimalPlay(piles3);
    }

    /**
     * Shows the difference between optimal and suboptimal play
     */
    private void demonstrateOptimalPlay(int[] piles) {
        System.out.println("Suboptimal play (greedy - always take larger end):");
        simulateGreedyPlay(piles.clone());

        System.out.println();
        System.out.println("Optimal play (considering future consequences):");
        System.out.println("- Requires looking ahead to see opponent's best responses");
        System.out.println("- May sacrifice immediate gain for long-term advantage");
        System.out.println("- This is where dynamic programming comes in!");
    }

    /**
     * Simulates a greedy strategy for comparison
     */
    private void simulateGreedyPlay(int[] piles) {
        List<Integer> remaining = new ArrayList<>();
        for (int pile : piles) {
            remaining.add(pile);
        }

        int firstScore = 0, secondScore = 0;
        boolean firstPlayerTurn = true;
        int turn = 1;

        while (!remaining.isEmpty()) {
            int leftChoice = remaining.get(0);
            int rightChoice = remaining.get(remaining.size() - 1);

            boolean takeLeft = leftChoice >= rightChoice;
            int chosen = takeLeft ? leftChoice : rightChoice;

            if (takeLeft) {
                remaining.remove(0);
            } else {
                remaining.remove(remaining.size() - 1);
            }

            if (firstPlayerTurn) {
                firstScore += chosen;
                System.out.println("Turn " + turn + ": First player takes " + chosen +
                        " from " + (takeLeft ? "left" : "right"));
            } else {
                secondScore += chosen;
                System.out.println("Turn " + turn + ": Second player takes " + chosen +
                        " from " + (takeLeft ? "left" : "right"));
            }

            firstPlayerTurn = !firstPlayerTurn;
            turn++;
        }

        System.out.println("Final scores - First: " + firstScore + ", Second: " + secondScore);
        System.out.println("Difference: " + (firstScore - secondScore));
    }

    /**
     * Explains why simple strategies fail and DP is needed
     */
    public void explainWhyDPIsNeeded() {
        System.out.println("=== Why Dynamic Programming is Necessary ===");
        System.out.println();
        System.out.println("Why simple strategies fail:");
        System.out.println("1. Greedy (always take larger): Doesn't consider future moves");
        System.out.println("2. Look-ahead 1 step: Still insufficient for complex games");
        System.out.println("3. Need to consider ALL possible game sequences");
        System.out.println();
        System.out.println("Dynamic Programming advantages:");
        System.out.println("1. Explores all possible game states");
        System.out.println("2. Stores optimal results for subproblems");
        System.out.println("3. Guarantees truly optimal play for both players");
        System.out.println("4. Handles the recursive nature of decision-making");
        System.out.println();
        System.out.println("Key challenge: Representing alternating turns in the algorithm");
        System.out.println("Solution: Track both players' optimal scores simultaneously");
    }

}
