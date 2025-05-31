package org.qinlinj.algoframework._800_dynamic_programming_algo._850_dp_games_II._852_egg_dropping;

/**
 * EGG DROPPING PROBLEM - BASIC DYNAMIC PROGRAMMING SOLUTION
 * <p>
 * STATE DEFINITION:
 * dp(K, N) = minimum trials needed in worst case with K eggs and N floors
 * <p>
 * STATE TRANSITIONS:
 * For each possible floor i (1 <= i <= N):
 * - If egg breaks at floor i: dp(K-1, i-1) + 1
 * - If egg doesn't break at floor i: dp(K, N-i) + 1
 * - Take maximum of these two (worst case)
 * - Take minimum across all floors i (optimal choice)
 * <p>
 * RECURRENCE RELATION:
 * dp(K, N) = 1 + min(max(dp(K-1, i-1), dp(K, N-i))) for i in [1, N]
 * <p>
 * BASE CASES:
 * - dp(K, 0) = 0 (no floors, no trials needed)
 * - dp(1, N) = N (one egg, must try linearly)
 * <p>
 * ALGORITHM FRAMEWORK:
 * 1. Identify states: (eggs, floors)
 * 2. Identify choices: which floor to try
 * 3. Define recurrence based on worst-case analysis
 * 4. Add memoization to avoid recomputation
 * <p>
 * TIME COMPLEXITY: O(K * N^2)
 * SPACE COMPLEXITY: O(K * N)
 */

import java.util.*;

public class _852_b_EggDropBasicDP {

    // Memoization table
    private int[][] memo;

    public static void main(String[] args) {
        _852_b_EggDropBasicDP solver = new _852_b_EggDropBasicDP();

        // Test with small examples
        System.out.println("=== Test Cases ===");
        System.out.println("1 egg, 7 floors: " + solver.superEggDrop(1, 7));
        System.out.println("2 eggs, 10 floors: " + solver.superEggDrop(2, 10));
        System.out.println("3 eggs, 14 floors: " + solver.superEggDrop(3, 14));

        // Demonstrate decision process
        solver.demonstrateDecisionProcess(2, 6);

        // Visualize small state space
        solver.visualizeStateSpace(3, 6);

        // Explain concepts
        solver.analyzeComplexity();
        solver.explainIntuition();
    }

    /**
     * Main function: calculates minimum trials for K eggs and N floors
     */
    public int superEggDrop(int K, int N) {
        // Initialize memoization table
        memo = new int[K + 1][N + 1];
        for (int[] row : memo) {
            Arrays.fill(row, -1); // -1 indicates not computed
        }

        return dp(K, N);
    }

    /**
     * Core DP function
     * Returns minimum trials needed with K eggs and N floors in worst case
     */
    private int dp(int K, int N) {
        // BASE CASE 1: No floors to test
        if (N == 0) return 0;

        // BASE CASE 2: Only one egg - must use linear search
        if (K == 1) return N;

        // Check memoization table
        if (memo[K][N] != -1) {
            return memo[K][N];
        }

        // Try all possible floors and find the minimum
        int result = Integer.MAX_VALUE;

        for (int i = 1; i <= N; i++) {
            // Calculate trials for both scenarios at floor i
            int eggBreaks = dp(K - 1, i - 1);     // Egg breaks: search floors below
            int eggSurvives = dp(K, N - i);       // Egg survives: search floors above

            // Worst case: maximum of the two scenarios
            int worstCase = Math.max(eggBreaks, eggSurvives);

            // Add 1 for the current trial at floor i
            int totalTrials = worstCase + 1;

            // Keep track of minimum across all floor choices
            result = Math.min(result, totalTrials);
        }

        // Store result in memo and return
        memo[K][N] = result;
        return result;
    }

    /**
     * Demonstrates the decision-making process for a specific case
     */
    public void demonstrateDecisionProcess(int K, int N) {
        System.out.println("=== Decision Process Demo ===");
        System.out.println("Eggs: " + K + ", Floors: " + N);
        System.out.println();

        // Reset memo for clean demonstration
        memo = new int[K + 1][N + 1];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }

        System.out.println("Trying each possible floor:");

        int bestChoice = -1;
        int bestResult = Integer.MAX_VALUE;

        for (int i = 1; i <= N; i++) {
            int eggBreaks = dp(K - 1, i - 1);
            int eggSurvives = dp(K, N - i);
            int worstCase = Math.max(eggBreaks, eggSurvives);
            int totalTrials = worstCase + 1;

            System.out.printf("Floor %d: Break=%d, Survive=%d, Worst=%d, Total=%d%n",
                    i, eggBreaks, eggSurvives, worstCase, totalTrials);

            if (totalTrials < bestResult) {
                bestResult = totalTrials;
                bestChoice = i;
            }
        }

        System.out.println();
        System.out.println("Best choice: Floor " + bestChoice + " with " + bestResult + " trials");
    }

    /**
     * Analyzes the algorithm's performance characteristics
     */
    public void analyzeComplexity() {
        System.out.println("\n=== Complexity Analysis ===");
        System.out.println("Time Complexity: O(K * N^2)");
        System.out.println("- Subproblems: K * N different states");
        System.out.println("- Per subproblem: O(N) to try all floors");
        System.out.println("- Total: K * N * N = K * N^2");
        System.out.println();
        System.out.println("Space Complexity: O(K * N)");
        System.out.println("- Memoization table size: K * N");
        System.out.println("- Recursion depth: O(K + N)");
    }

    /**
     * Visualizes the state space and transitions
     */
    public void visualizeStateSpace(int maxK, int maxN) {
        System.out.println("\n=== State Space Visualization ===");
        System.out.println("Computing dp values for small instances:");
        System.out.println();

        // Reset memo
        memo = new int[maxK + 1][maxN + 1];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }

        // Print header
        System.out.print("K\\N\t");
        for (int n = 0; n <= maxN; n++) {
            System.out.print(n + "\t");
        }
        System.out.println();

        // Print dp values
        for (int k = 0; k <= maxK; k++) {
            System.out.print(k + "\t");
            for (int n = 0; n <= maxN; n++) {
                if (k == 0) {
                    System.out.print("∞\t"); // Impossible with 0 eggs
                } else {
                    System.out.print(dp(k, n) + "\t");
                }
            }
            System.out.println();
        }
    }

    /**
     * Explains the intuition behind the algorithm
     */
    public void explainIntuition() {
        System.out.println("\n=== Algorithm Intuition ===");
        System.out.println("1. EXHAUSTIVE SEARCH:");
        System.out.println("   Try every possible floor as the next test");
        System.out.println();
        System.out.println("2. WORST-CASE ANALYSIS:");
        System.out.println("   For each floor choice, consider both outcomes");
        System.out.println("   Take the worse of the two (egg breaks vs survives)");
        System.out.println();
        System.out.println("3. OPTIMAL CHOICE:");
        System.out.println("   Among all floor choices, pick the one with");
        System.out.println("   minimum worst-case trials");
        System.out.println();
        System.out.println("4. MEMOIZATION:");
        System.out.println("   Cache results to avoid recomputing same states");
    }
}