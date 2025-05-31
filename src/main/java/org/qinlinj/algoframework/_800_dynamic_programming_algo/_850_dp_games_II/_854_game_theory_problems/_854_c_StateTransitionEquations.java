package org.qinlinj.algoframework._800_dynamic_programming_algo._850_dp_games_II._854_game_theory_problems; /**
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

import java.util.*;

public class _854_c_StateTransitionEquations {

    public static void main(String[] args) {
        _854_c_StateTransitionEquations transitions = new _854_c_StateTransitionEquations();

        // Demonstrate state transition equations
        transitions.demonstrateStateTransitions();

        System.out.println("=".repeat(60));
        System.out.println();

        // Explain second player logic
        transitions.explainSecondPlayerLogic();

        System.out.println("=".repeat(60));
        System.out.println();

        // Show complete example
        transitions.demonstrateCompleteExample();

        System.out.println("=".repeat(60));
        System.out.println();

        // Explain mathematical reasoning
        transitions.explainMathematicalReasoning();

        System.out.println("=".repeat(60));
        System.out.println();

        // Compare with greedy
        transitions.compareWithGreedyApproach();

        System.out.println("=".repeat(60));
        System.out.println();

        System.out.println("=== Key Insights About State Transitions ===");
        System.out.println("1. First player maximizes: max(left_choice, right_choice)");
        System.out.println("2. Second player's score depends on first player's optimal choice");
        System.out.println("3. Role swapping enables recursive subproblem solving");
        System.out.println("4. DP captures perfect information game dynamics");
        System.out.println("5. Optimal substructure allows building complex solutions from simpler ones");

        System.out.println();
        System.out.println("Next: Complete implementation with code...");
    }

    /**
     * Demonstrates the state transition equations step by step
     */
    public void demonstrateStateTransitions() {
        System.out.println("=== State Transition Equations Explained ===");
        System.out.println();

        int[] piles = {3, 7, 2, 3};
        System.out.println("Example: piles = " + Arrays.toString(piles));
        System.out.println();

        System.out.println("State Transition for FIRST player:");
        System.out.println("dp[i][j].first = max(");
        System.out.println("    piles[i] + dp[i+1][j].second,  // Choose left");
        System.out.println("    piles[j] + dp[i][j-1].second   // Choose right");
        System.out.println(")");
        System.out.println();

        System.out.println("Why this works:");
        System.out.println("1. First player can choose left pile (piles[i]) or right pile (piles[j])");
        System.out.println("2. After the choice, remaining game is piles[i+1...j] or piles[i...j-1]");
        System.out.println("3. In the remaining game, first player becomes second player");
        System.out.println("4. So we add current choice to dp[...].second (their score as second player)");
        System.out.println();

        demonstrateSpecificTransition(piles);
    }

    /**
     * Shows a specific state transition calculation
     */
    private void demonstrateSpecificTransition(int[] piles) {
        System.out.println("=== Specific Example: Computing dp[0][2] ===");
        System.out.println("Subarray: piles[0...2] = [" + piles[0] + ", " + piles[1] + ", " + piles[2] + "]");
        System.out.println();

        System.out.println("First player's choices:");
        System.out.println("Choice 1: Take left pile (" + piles[0] + ")");
        System.out.println("  - Remaining game: piles[1...2] = [" + piles[1] + ", " + piles[2] + "]");
        System.out.println("  - First player becomes second in remaining game");
        System.out.println("  - Total score = " + piles[0] + " + dp[1][2].second");
        System.out.println();

        System.out.println("Choice 2: Take right pile (" + piles[2] + ")");
        System.out.println("  - Remaining game: piles[0...1] = [" + piles[0] + ", " + piles[1] + "]");
        System.out.println("  - First player becomes second in remaining game");
        System.out.println("  - Total score = " + piles[2] + " + dp[0][1].second");
        System.out.println();

        System.out.println("First player will choose the option that gives higher total score");
        System.out.println("dp[0][2].first = max(" + piles[0] + " + dp[1][2].second, " + piles[2] + " + dp[0][1].second)");
    }

    /**
     * Explains how second player's score is determined
     */
    public void explainSecondPlayerLogic() {
        System.out.println("=== Second Player Score Determination ===");
        System.out.println();

        System.out.println("The second player's score depends on first player's choice:");
        System.out.println();
        System.out.println("if (first player chose left pile piles[i]):");
        System.out.println("    dp[i][j].second = dp[i+1][j].first");
        System.out.println("    // Second player becomes first in game piles[i+1...j]");
        System.out.println();
        System.out.println("if (first player chose right pile piles[j]):");
        System.out.println("    dp[i][j].second = dp[i][j-1].first");
        System.out.println("    // Second player becomes first in game piles[i...j-1]");
        System.out.println();

        System.out.println("Key insight: After first player moves, second player gets optimal");
        System.out.println("play in the remaining subgame as the new 'first' player.");
    }

    /**
     * Demonstrates the complete transition calculation with a concrete example
     */
    public void demonstrateCompleteExample() {
        System.out.println("=== Complete State Transition Example ===");
        System.out.println();

        int[] piles = {3, 7, 2, 3};
        int n = piles.length;
        GameState[][] dp = new GameState[n][n];

        // Initialize base cases
        for (int i = 0; i < n; i++) {
            dp[i][i] = new GameState(piles[i], 0);
        }

        System.out.println("Base cases (single piles):");
        for (int i = 0; i < n; i++) {
            System.out.println("dp[" + i + "][" + i + "] = " + dp[i][i] +
                    " (first takes " + piles[i] + ", second gets 0)");
        }
        System.out.println();

        // Compute length 2 subarrays
        System.out.println("Computing 2-element subarrays:");
        for (int i = 0; i < n - 1; i++) {
            int j = i + 1;
            computeAndExplain(piles, dp, i, j);
        }

        System.out.println();

        // Compute length 3 subarrays
        System.out.println("Computing 3-element subarrays:");
        for (int i = 0; i < n - 2; i++) {
            int j = i + 2;
            computeAndExplain(piles, dp, i, j);
        }

        System.out.println();

        // Compute the full array
        System.out.println("Computing full array:");
        computeAndExplain(piles, dp, 0, n - 1);

        System.out.println();
        System.out.println("Final answer: " + dp[0][n - 1].first + " - " + dp[0][n - 1].second +
                " = " + (dp[0][n - 1].first - dp[0][n - 1].second));
    }

    /**
     * Helper method to compute and explain a specific DP state
     */
    private void computeAndExplain(int[] piles, GameState[][] dp, int i, int j) {
        if (i == j) return; // Base case already handled

        int[] subarray = Arrays.copyOfRange(piles, i, j + 1);
        System.out.println("Computing dp[" + i + "][" + j + "] for " + Arrays.toString(subarray) + ":");

        // Option 1: take left
        int leftChoice = piles[i] + dp[i + 1][j].second;
        System.out.println("  Take left (" + piles[i] + "): " + piles[i] + " + " +
                dp[i + 1][j].second + " = " + leftChoice);

        // Option 2: take right
        int rightChoice = piles[j] + dp[i][j - 1].second;
        System.out.println("  Take right (" + piles[j] + "): " + piles[j] + " + " +
                dp[i][j - 1].second + " = " + rightChoice);

        // Determine optimal choice
        boolean takeLeft = leftChoice >= rightChoice;
        int firstScore = takeLeft ? leftChoice : rightChoice;
        int secondScore = takeLeft ? dp[i + 1][j].first : dp[i][j - 1].first;

        dp[i][j] = new GameState(firstScore, secondScore);

        System.out.println("  Optimal: Take " + (takeLeft ? "left" : "right") +
                " → dp[" + i + "][" + j + "] = " + dp[i][j]);
    }

    /**
     * Shows the mathematical reasoning behind the state transitions
     */
    public void explainMathematicalReasoning() {
        System.out.println("=== Mathematical Reasoning Behind Transitions ===");
        System.out.println();

        System.out.println("Why the transition equations are correct:");
        System.out.println();
        System.out.println("1. Optimality Principle:");
        System.out.println("   - Each player maximizes their own score");
        System.out.println("   - Given any game state, player chooses the better option");
        System.out.println();

        System.out.println("2. Recursive Structure:");
        System.out.println("   - Game on [i,j] reduces to game on [i+1,j] or [i,j-1]");
        System.out.println("   - Player roles swap in the subgame");
        System.out.println("   - Optimal substructure: optimal solution contains optimal subsolutions");
        System.out.println();

        System.out.println("3. Role Swapping Logic:");
        System.out.println("   - Current first player's score = immediate gain + future score as second");
        System.out.println("   - Current second player inherits the optimal play in reduced game");
        System.out.println();

        System.out.println("4. Max Operation:");
        System.out.println("   - First player chooses max(left_option, right_option)");
        System.out.println("   - This ensures first player plays optimally");
        System.out.println("   - Second player's score is determined by first player's choice");
    }

    /**
     * Demonstrates why greedy approaches fail compared to this DP approach
     */
    public void compareWithGreedyApproach() {
        System.out.println("=== DP vs Greedy Approach Comparison ===");
        System.out.println();

        int[] piles = {1, 5, 233, 7};
        System.out.println("Example: " + Arrays.toString(piles));
        System.out.println();

        System.out.println("Greedy approach (always take larger pile available):");
        simulateGreedy(piles);

        System.out.println();
        System.out.println("DP approach (look ahead optimally):");
        System.out.println("- Considers all possible sequences of moves");
        System.out.println("- Each player anticipates opponent's optimal responses");
        System.out.println("- Guarantees truly optimal play for both players");

        System.out.println();
        System.out.println("Why DP is superior:");
        System.out.println("1. Greedy only considers immediate benefit");
        System.out.println("2. DP considers long-term consequences");
        System.out.println("3. DP handles strategic sacrifices for better positioning");
        System.out.println("4. DP guarantees optimal play assumption");
    }

    /**
     * Simulates greedy play for comparison
     */
    private void simulateGreedy(int[] piles) {
        List<Integer> remaining = new ArrayList<>();
        for (int pile : piles) remaining.add(pile);

        int first = 0, second = 0;
        boolean firstTurn = true;

        while (!remaining.isEmpty()) {
            int left = remaining.get(0);
            int right = remaining.get(remaining.size() - 1);
            boolean takeLeft = left >= right;
            int chosen = takeLeft ? left : right;

            if (takeLeft) remaining.remove(0);
            else remaining.remove(remaining.size() - 1);

            if (firstTurn) {
                first += chosen;
                System.out.println("First player takes " + chosen + " (greedy choice)");
            } else {
                second += chosen;
                System.out.println("Second player takes " + chosen + " (greedy choice)");
            }
            firstTurn = !firstTurn;
        }

        System.out.println("Greedy result: First=" + first + ", Second=" + second +
                ", Difference=" + (first - second));
    }

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