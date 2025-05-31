package org.qinlinj.algoframework._800_dynamic_programming_algo._850_dp_games_II._854_game_theory_problems; /**
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

import java.util.*;

public class _854_b_DPStateDefinition {

    public static void main(String[] args) {
        _854_b_DPStateDefinition dpState = new _854_b_DPStateDefinition();

        // Demonstrate DP array structure
        dpState.demonstrateDPArrayStructure();

        System.out.println("=".repeat(60));
        System.out.println();

        // Explain the first/second player concept
        dpState.explainFirstSecondConcept();

        System.out.println("=".repeat(60));
        System.out.println();

        // Show base cases
        dpState.demonstrateBaseCases();

        System.out.println("=".repeat(60));
        System.out.println();

        // Visualize dependencies
        dpState.visualizeDependencyStructure();

        System.out.println("=".repeat(60));
        System.out.println();

        // Demonstrate final answer
        dpState.demonstrateFinalAnswer();

        System.out.println("=".repeat(60));
        System.out.println();

        System.out.println("=== Key Insights About DP State Design ===");
        System.out.println("1. Use pairs to track both players' optimal scores");
        System.out.println("2. 'First' and 'second' are relative to each subgame");
        System.out.println("3. Role-swapping enables reuse of computed results");
        System.out.println("4. Dependencies determine computation order");
        System.out.println("5. Base cases handle single-pile scenarios");

        System.out.println();
        System.out.println("Next: We'll derive the state transition equations...");
    }

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
     * Explains the meaning of first and second in different contexts
     */
    public void explainFirstSecondConcept() {
        System.out.println("=== Understanding 'First' and 'Second' Player Concept ===");
        System.out.println();

        System.out.println("Important: 'First' and 'Second' are RELATIVE to each subgame!");
        System.out.println();

        System.out.println("Example scenario:");
        System.out.println("Original game: Player A vs Player B on [2, 8, 3, 5]");
        System.out.println("- Player A moves first (is the 'first' player)");
        System.out.println("- Player B moves second (is the 'second' player)");
        System.out.println();

        System.out.println("After Player A takes 2, remaining: [8, 3, 5]");
        System.out.println("- Now Player B moves first on [8, 3, 5] (becomes the 'first' player)");
        System.out.println("- Player A will move second on [8, 3, 5] (becomes the 'second' player)");
        System.out.println("- Roles have SWAPPED for this subgame!");
        System.out.println();

        System.out.println("This role-swapping is why the DP approach works:");
        System.out.println("1. We can reuse previously computed optimal strategies");
        System.out.println("2. The optimal play is independent of which specific player is 'first'");
        System.out.println("3. Only the turn order matters, not player identity");
    }

    /**
     * Shows how base cases work in this DP formulation
     */
    public void demonstrateBaseCases() {
        System.out.println("=== Base Cases in Stone Game DP ===");
        System.out.println();

        int[] piles = {2, 8, 3, 5};

        System.out.println("Base case: Single pile subgames (i == j)");
        System.out.println();

        for (int i = 0; i < piles.length; i++) {
            System.out.println("dp[" + i + "][" + i + "] represents subarray [" + piles[i] + "]:");
            System.out.println("  - First player takes the only pile: " + piles[i]);
            System.out.println("  - Second player gets nothing: 0");
            System.out.println("  - So dp[" + i + "][" + i + "] = (" + piles[i] + ", 0)");
        }

        System.out.println();
        System.out.println("Why these are base cases:");
        System.out.println("1. No further decisions needed - only one pile available");
        System.out.println("2. First player must take it, second player gets nothing");
        System.out.println("3. These serve as building blocks for larger subproblems");
    }

    /**
     * Visualizes the dependency structure of the DP
     */
    public void visualizeDependencyStructure() {
        System.out.println("=== DP Dependency Structure ===");
        System.out.println();

        System.out.println("How dp[i][j] depends on smaller subproblems:");
        System.out.println();
        System.out.println("To compute dp[i][j], we need:");
        System.out.println("1. dp[i+1][j] - if we take left pile piles[i]");
        System.out.println("2. dp[i][j-1] - if we take right pile piles[j]");
        System.out.println();

        System.out.println("Example: Computing dp[1][3] for piles[1...3] = [8, 3, 5]");
        System.out.println("Option 1: Take left pile (8)");
        System.out.println("  - Remaining subgame: piles[2...3] = [3, 5]");
        System.out.println("  - Need dp[2][3] to know optimal play on [3, 5]");
        System.out.println("Option 2: Take right pile (5)");
        System.out.println("  - Remaining subgame: piles[1...2] = [8, 3]");
        System.out.println("  - Need dp[1][2] to know optimal play on [8, 3]");
        System.out.println();

        System.out.println("Dependency pattern:");
        System.out.println("- dp[i][j] depends on dp[i+1][j] and dp[i][j-1]");
        System.out.println("- These are subproblems with smaller ranges");
        System.out.println("- Must compute smaller ranges before larger ones");

        demonstrateComputationOrder();
    }

    /**
     * Shows the correct order for computing DP values
     */
    private void demonstrateComputationOrder() {
        System.out.println();
        System.out.println("=== Computation Order ===");
        System.out.println();

        int n = 4; // For a 4-element array
        System.out.println("For a 4x4 DP table, computation order should be:");
        System.out.println();

        // Show the order by range length
        for (int len = 1; len <= n; len++) {
            System.out.println("Range length " + len + ":");
            for (int i = 0; i <= n - len; i++) {
                int j = i + len - 1;
                if (len == 1) {
                    System.out.println("  dp[" + i + "][" + j + "] = base case");
                } else {
                    System.out.println("  dp[" + i + "][" + j + "] depends on dp[" + (i + 1) + "][" + j +
                            "] and dp[" + i + "][" + (j - 1) + "]");
                }
            }
        }

        System.out.println();
        System.out.println("Alternative traversal (bottom-up, left-right):");
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                if (i == j) {
                    System.out.println("dp[" + i + "][" + j + "] = base case");
                } else {
                    System.out.println("dp[" + i + "][" + j + "] = computed from previous values");
                }
            }
        }
    }

    /**
     * Demonstrates the final answer extraction
     */
    public void demonstrateFinalAnswer() {
        System.out.println("=== Final Answer Extraction ===");
        System.out.println();

        System.out.println("Goal: Find score difference between original first and second player");
        System.out.println();
        System.out.println("For the entire array piles[0...n-1]:");
        System.out.println("- dp[0][n-1].first = best score for original first player");
        System.out.println("- dp[0][n-1].second = best score for original second player");
        System.out.println("- Answer = dp[0][n-1].first - dp[0][n-1].second");
        System.out.println();

        System.out.println("Interpretation:");
        System.out.println("- Positive result: First player wins");
        System.out.println("- Negative result: Second player wins");
        System.out.println("- Zero result: Tie game");
        System.out.println();

        System.out.println("For LeetCode 486 (Predict the Winner):");
        System.out.println("- Return dp[0][n-1].first >= dp[0][n-1].second");
        System.out.println("- Or equivalently: dp[0][n-1].first - dp[0][n-1].second >= 0");
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