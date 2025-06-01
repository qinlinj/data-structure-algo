package org.qinlinj.algoframework._800_dynamic_programming_algo._850_dp_games_II._854_game_theory_problems;

/**
 * STONE GAME - COMPLETE DYNAMIC PROGRAMMING IMPLEMENTATION
 * <p>
 * Final Complete Solution:
 * This class provides the full implementation of the Stone Game problem using
 * dynamic programming with optimal game theory strategy.
 * <p>
 * Algorithm Steps:
 * 1. Create 2D DP table to store GameState objects
 * 2. Initialize base cases (single pile games)
 * 3. Fill DP table using bottom-up approach
 * 4. Apply state transition equations for optimal play
 * 5. Extract final answer from dp[0][n-1]
 * <p>
 * Implementation Details:
 * - Use custom Pair class to store both players' scores
 * - Traverse DP table in correct order (smaller ranges first)
 * - Handle base cases and general cases separately
 * - Ensure both players play optimally at each step
 * <p>
 * Complexity Analysis:
 * - Time: O(n²) - fill n² DP states, each in O(1) time
 * - Space: O(n²) - store n² Pair objects
 * - Can be optimized to O(n) space but loses clarity
 * <p>
 * Applications:
 * - LeetCode 486: Predict the Winner
 * - LeetCode 877: Stone Game
 * - General minimax game problems
 * - Any two-player zero-sum game with perfect information
 * <p>
 * Key Features:
 * - Handles any array size and values
 * - Guarantees optimal play for both players
 * - Returns exact score difference
 * - Easily adaptable to similar problems
 */

import java.util.*;

public class _854_d_CompleteImplementation {

    public static void main(String[] args) {
        _854_d_CompleteImplementation solution = new _854_d_CompleteImplementation();

        // Validate with known test cases
        solution.validateSolution();

        System.out.println("=".repeat(60));
        System.out.println();

        // Demonstrate with detailed trace
        int[] example = {3, 7, 2, 3};
        solution.stoneGameWithTrace(example);

        System.out.println("=".repeat(60));
        System.out.println();

        // Show optimal play sequence
        solution.demonstrateOptimalPlay(new int[]{1, 5, 233, 7});

        System.out.println("=".repeat(60));
        System.out.println();

        System.out.println("=== Algorithm Summary ===");
        System.out.println("1. Use 2D DP with Pair objects to track both players");
        System.out.println("2. Fill base cases for single-pile games");
        System.out.println("3. Apply optimal state transitions bottom-up");
        System.out.println("4. Each player chooses the move maximizing their score");
        System.out.println("5. Role-swapping handles alternating turns naturally");
        System.out.println();
        System.out.println("Time: O(n²), Space: O(n²)");
        System.out.println("Handles any game configuration optimally!");

        System.out.println();
        System.out.println("This completes the comprehensive Stone Game DP solution!");
    }

    /**
     * Main solution: Returns score difference between first and second player
     *
     * @param piles Array representing stone piles
     * @return Positive if first player wins, negative if second player wins, 0 for tie
     */
    public int stoneGame(int[] piles) {
        if (piles == null || piles.length == 0) return 0;

        int n = piles.length;

        // Step 1: Initialize DP table
        Pair[][] dp = new Pair[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                dp[i][j] = new Pair(0, 0);
            }
        }

        // Step 2: Fill base cases (single pile games)
        for (int i = 0; i < n; i++) {
            dp[i][i].fir = piles[i];  // First player takes the pile
            dp[i][i].sec = 0;         // Second player gets nothing
        }

        // Step 3: Fill DP table using bottom-up approach
        // Process by increasing range length
        for (int i = n - 2; i >= 0; i--) {         // Start from bottom
            for (int j = i + 1; j < n; j++) {      // Go left to right
                // Calculate scores for both possible moves
                int leftChoice = piles[i] + dp[i + 1][j].sec;   // Take left pile
                int rightChoice = piles[j] + dp[i][j - 1].sec;  // Take right pile

                // First player chooses the better option
                if (leftChoice > rightChoice) {
                    dp[i][j].fir = leftChoice;
                    dp[i][j].sec = dp[i + 1][j].fir;  // Second player's score in remaining game
                } else {
                    dp[i][j].fir = rightChoice;
                    dp[i][j].sec = dp[i][j - 1].fir; // Second player's score in remaining game
                }
            }
        }

        // Step 4: Extract final answer
        Pair result = dp[0][n - 1];
        return result.fir - result.sec;
    }

    /**
     * Enhanced version with detailed step-by-step tracing
     */
    public int stoneGameWithTrace(int[] piles) {
        System.out.println("=== Stone Game DP Solution with Trace ===");
        System.out.println("Input: " + Arrays.toString(piles));
        System.out.println();

        int n = piles.length;
        Pair[][] dp = new Pair[n][n];

        // Initialize
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                dp[i][j] = new Pair(0, 0);
            }
        }

        // Base cases
        System.out.println("Base cases (single piles):");
        for (int i = 0; i < n; i++) {
            dp[i][i].fir = piles[i];
            dp[i][i].sec = 0;
            System.out.println("dp[" + i + "][" + i + "] = " + dp[i][i] +
                    " (pile value: " + piles[i] + ")");
        }
        System.out.println();

        // Fill DP table
        System.out.println("Filling DP table:");
        for (int i = n - 2; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                System.out.println("Computing dp[" + i + "][" + j + "] for subarray " +
                        Arrays.toString(Arrays.copyOfRange(piles, i, j + 1)) + ":");

                int leftChoice = piles[i] + dp[i + 1][j].sec;
                int rightChoice = piles[j] + dp[i][j - 1].sec;

                System.out.println("  Left choice: " + piles[i] + " + " + dp[i + 1][j].sec +
                        " = " + leftChoice);
                System.out.println("  Right choice: " + piles[j] + " + " + dp[i][j - 1].sec +
                        " = " + rightChoice);

                if (leftChoice > rightChoice) {
                    dp[i][j].fir = leftChoice;
                    dp[i][j].sec = dp[i + 1][j].fir;
                    System.out.println("  Choose left → dp[" + i + "][" + j + "] = " + dp[i][j]);
                } else {
                    dp[i][j].fir = rightChoice;
                    dp[i][j].sec = dp[i][j - 1].fir;
                    System.out.println("  Choose right → dp[" + i + "][" + j + "] = " + dp[i][j]);
                }
                System.out.println();
            }
        }

        printDPTable(dp, piles);

        Pair result = dp[0][n - 1];
        int difference = result.fir - result.sec;
        System.out.println("Final result: " + result.fir + " - " + result.sec + " = " + difference);

        return difference;
    }

    /**
     * Print the DP table in a readable format
     */
    private void printDPTable(Pair[][] dp, int[] piles) {
        int n = dp.length;
        System.out.println("Final DP Table:");

        // Print header
        System.out.print("      ");
        for (int j = 0; j < n; j++) {
            System.out.printf("%12d", j);
        }
        System.out.println();

        // Print table
        for (int i = 0; i < n; i++) {
            System.out.printf("%2d: ", i);
            for (int j = 0; j < n; j++) {
                if (j >= i) {
                    System.out.printf("%12s", dp[i][j].toString());
                } else {
                    System.out.print("           -");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     * Solution for LeetCode 486: Predict the Winner
     */
    public boolean predictTheWinner(int[] nums) {
        // First player wins if their score >= second player's score
        return stoneGame(nums) >= 0;
    }

    /**
     * Validates the solution with known test cases
     */
    public void validateSolution() {
        System.out.println("=== Solution Validation ===");
        System.out.println();

        // Test case 1: LeetCode 486 example 1
        int[] test1 = {1, 5, 2};
        int result1 = stoneGame(test1);
        boolean winner1 = predictTheWinner(test1);
        System.out.println("Test 1: " + Arrays.toString(test1));
        System.out.println("Score difference: " + result1);
        System.out.println("First player wins: " + winner1 + " (expected: false)");
        System.out.println();

        // Test case 2: LeetCode 486 example 2
        int[] test2 = {1, 5, 233, 7};
        int result2 = stoneGame(test2);
        boolean winner2 = predictTheWinner(test2);
        System.out.println("Test 2: " + Arrays.toString(test2));
        System.out.println("Score difference: " + result2);
        System.out.println("First player wins: " + winner2 + " (expected: true)");
        System.out.println();

        // Test case 3: Simple case
        int[] test3 = {1, 100, 3};
        int result3 = stoneGame(test3);
        System.out.println("Test 3: " + Arrays.toString(test3));
        System.out.println("Score difference: " + result3 + " (expected: -96)");
        System.out.println();

        // Test case 4: Even game
        int[] test4 = {5, 5};
        int result4 = stoneGame(test4);
        System.out.println("Test 4: " + Arrays.toString(test4));
        System.out.println("Score difference: " + result4 + " (expected: 0)");
    }

    /**
     * Demonstrates the optimal play sequence for a given input
     */
    public void demonstrateOptimalPlay(int[] piles) {
        System.out.println("=== Optimal Play Demonstration ===");
        System.out.println("Input: " + Arrays.toString(piles));

        int n = piles.length;
        Pair[][] dp = new Pair[n][n];

        // Build DP table (without trace for cleaner output)
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                dp[i][j] = new Pair(0, 0);
            }
        }

        for (int i = 0; i < n; i++) {
            dp[i][i] = new Pair(piles[i], 0);
        }

        for (int i = n - 2; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                int leftChoice = piles[i] + dp[i + 1][j].sec;
                int rightChoice = piles[j] + dp[i][j - 1].sec;

                if (leftChoice > rightChoice) {
                    dp[i][j] = new Pair(leftChoice, dp[i + 1][j].fir);
                } else {
                    dp[i][j] = new Pair(rightChoice, dp[i][j - 1].fir);
                }
            }
        }

        // Reconstruct optimal moves
        reconstructOptimalMoves(piles, dp, 0, n - 1, true, 1);

        Pair result = dp[0][n - 1];
        System.out.println();
        System.out.println("Final scores: First player = " + result.fir +
                ", Second player = " + result.sec);
        System.out.println("Winner: " + (result.fir >= result.sec ? "First" : "Second") + " player");
    }

    /**
     * Recursively reconstructs the optimal sequence of moves
     */
    private void reconstructOptimalMoves(int[] piles, Pair[][] dp, int i, int j,
                                         boolean firstPlayerTurn, int moveNum) {
        if (i > j) return;

        if (i == j) {
            System.out.println("Move " + moveNum + ": " +
                    (firstPlayerTurn ? "First" : "Second") +
                    " player takes " + piles[i] + " (only option)");
            return;
        }

        // Determine which choice was made
        int leftChoice = piles[i] + dp[i + 1][j].sec;
        int rightChoice = piles[j] + dp[i][j - 1].sec;

        if (firstPlayerTurn) {
            if (leftChoice > rightChoice) {
                System.out.println("Move " + moveNum + ": First player takes " + piles[i] +
                        " from left (better than " + piles[j] + " from right)");
                reconstructOptimalMoves(piles, dp, i + 1, j, false, moveNum + 1);
            } else {
                System.out.println("Move " + moveNum + ": First player takes " + piles[j] +
                        " from right (better than " + piles[i] + " from left)");
                reconstructOptimalMoves(piles, dp, i, j - 1, false, moveNum + 1);
            }
        } else {
            // For second player, we need to check which choice the first player made
            // by looking at the DP values
            if (dp[i][j].sec == dp[i + 1][j].fir) {
                System.out.println("Move " + moveNum + ": Second player takes " + piles[i] + " from left");
                reconstructOptimalMoves(piles, dp, i + 1, j, true, moveNum + 1);
            } else {
                System.out.println("Move " + moveNum + ": Second player takes " + piles[j] + " from right");
                reconstructOptimalMoves(piles, dp, i, j - 1, true, moveNum + 1);
            }
        }
    }

    /**
     * Helper class to represent both players' optimal scores in a subgame
     */
    static class Pair {
        int fir;  // Score for first player (abbreviated for brevity)
        int sec;  // Score for second player

        Pair(int fir, int sec) {
            this.fir = fir;
            this.sec = sec;
        }

        @Override
        public String toString() {
            return "(" + fir + "," + sec + ")";
        }
    }
}