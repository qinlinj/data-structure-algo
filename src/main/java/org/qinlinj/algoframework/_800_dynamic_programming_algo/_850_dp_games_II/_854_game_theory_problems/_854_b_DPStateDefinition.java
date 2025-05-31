package org.qinlinj.algoframework._800_dynamic_programming_algo._850_dp_games_II._854_game_theory_problems;

import java.util.*;

/**
 * STONE GAME - DYNAMIC PROGRAMMING STATE DEFINITION
 * <p>
 * DP State Design Philosophy:
 * - The key challenge in game theory DP is representing alternating players
 * - Traditional DP tracks one objective, but here we need to track TWO players
 * - Solution: Use a 2D DP array where each cell contains a PAIR of values
 * <p>
 * DP Array Definition:
 * - dp[i][j] represents the game state for subarray piles[i...j]
 * - dp[i][j].fir = maximum score the FIRST player can get from piles[i...j]
 * - dp[i][j].sec = maximum score the SECOND player can get from piles[i...j]
 * <p>
 * Key Insights:
 * 1. "First player" means whoever moves first in the current subgame
 * 2. "Second player" means whoever moves second in the current subgame
 * 3. Roles switch as the game progresses (first becomes second, etc.)
 * 4. Both players play optimally within their respective turns
 * <p>
 * State Space:
 * - States: All possible ranges [i,j] where 0 ≤ i ≤ j < n
 * - Choices: Take from left (index i) or right (index j)
 * - Transitions: After a choice, range shrinks and roles swap
 * <p>
 * Final Answer:
 * - dp[0][n-1].fir - dp[0][n-1].sec = score difference for entire array
 * - Positive: first player wins, Negative: second player wins, Zero: tie
 * <p>
 * Example Visualization:
 * For piles = [2, 8, 3, 5]:
 * - dp[0][1].fir = 8 (facing [2,8], first player takes 8)
 * - dp[1][3].sec = 5 (facing [8,3,5], second player's best is 5)
 */

public class _854_b_DPStateDefinition {
    /**
     * Demonstrates the DP array structure and meaning
     */
    public void demonstrateDPArrayStructure() {
        System.out.println("=== DP Array Structure and Definition ===");
        System.out.println();

        int[] piles = {2, 8, 3, 5};
        int n = piles.length;

        System.out.println("Example piles: " + Arrays.toString(piles));
        System.out.println("DP array will be " + n + "x" + n + " with GameState objects");
        System.out.println();

        // Create and initialize a sample DP table structure
        GameState[][] dp = new GameState[n][n];

        System.out.println("DP Array Interpretation:");
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                System.out.println("dp[" + i + "][" + j + "] represents game on subarray piles[" +
                        i + "..." + j + "] = " + Arrays.toString(Arrays.copyOfRange(piles, i, j + 1)));
                System.out.println("  - .first = max score for whoever moves first on this subarray");
                System.out.println("  - .second = max score for whoever moves second on this subarray");
            }
        }

        System.out.println();
        System.out.println("Note: Only upper triangular part is used (i ≤ j)");
        System.out.println("Lower triangular part represents invalid ranges");
    }

    /**
     * Helper class to represent the pair of scores for both players
     */
    static class GameState {
        int first;   // Score for the player who moves first in this subgame
        int second;  // Score for the player who moves second in this subgame

        GameState(int first, int second) {
            this.first = first;
            this.second = second;
        }

        @Override
        public String toString() {
            return "(" + first + "," + second + ")";
        }
    }
}
