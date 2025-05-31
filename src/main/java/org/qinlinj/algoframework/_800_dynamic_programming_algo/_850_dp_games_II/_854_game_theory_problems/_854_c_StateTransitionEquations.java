package org.qinlinj.algoframework._800_dynamic_programming_algo._850_dp_games_II._854_game_theory_problems;

/**
 * STONE GAME - STATE TRANSITION EQUATIONS
 * <p>
 * State Transition Logic:
 * The core of the DP solution lies in deriving correct state transition equations
 * that capture the optimal decision-making of both players.
 * <p>
 * Key State Transition Equations:
 * <p>
 * For the FIRST player in range [i,j]:
 * dp[i][j].first = max(
 * piles[i] + dp[i+1][j].second,  // Take left pile
 * piles[j] + dp[i][j-1].second   // Take right pile
 * )
 * <p>
 * For the SECOND player in range [i,j]:
 * if (first player chose left):
 * dp[i][j].second = dp[i+1][j].first
 * if (first player chose right):
 * dp[i][j].second = dp[i][j-1].first
 * <p>
 * Explanation:
 * 1. First player optimizes their own score by choosing the better option
 * 2. After first player's choice, second player becomes first in remaining subgame
 * 3. Role reversal is automatically handled by accessing .first and .second appropriately
 * <p>
 * Decision Process:
 * - Each player, when it's their turn, will choose the option that maximizes their score
 * - This choice affects what the opponent gets in the resulting subgame
 * - The optimal strategy considers not just immediate gain but future consequences
 * <p>
 * Time Complexity: O(n²) - fill n² DP states
 * Space Complexity: O(n²) - store n² GameState objects
 */

public class _854_c_StateTransitionEquations {
    /**
     * Helper class to represent game state
     */
    static class GameState {
        int first;   // Score for first player in this subgame
        int second;  // Score for second player in this subgame

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
