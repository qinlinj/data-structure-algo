package org.qinlinj.algoframework._800_dynamic_programming_algo._860_dp_practice._861_house_robber_pattern_practice;

/**
 * DYNAMIC PROGRAMMING - HOUSE ROBBER PATTERN PROBLEMS
 * <p>
 * This collection demonstrates various problems that follow the House Robber pattern,
 * where we need to make choices between adjacent or related elements with constraints.
 * <p>
 * Key Concepts:
 * 1. State Definition: Define what dp[i] represents clearly
 * 2. State Transition: Identify the choices available at each step
 * 3. Base Cases: Handle boundary conditions properly
 * 4. Optimization: Use memoization or bottom-up approach
 * <p>
 * Common Pattern:
 * - At each position, we have multiple choices (take/skip, different durations, etc.)
 * - Taking an element affects future choices (skip next k elements, etc.)
 * - Goal is to optimize some value (max score, min cost, count ways)
 */

// ==================== Problem 1: Solving Questions With Brainpower ====================
class _861_a_SolvingQuestionsWithBrainpower {
    /**
     * LeetCode 2140: Solving Questions With Brainpower
     * <p>
     * Problem: Given questions array where questions[i] = [points, brainpower],
     * if you solve question i, you gain points[i] but cannot solve next brainpower[i] questions.
     * Find maximum points you can earn.
     * <p>
     * Pattern: House Robber variant - instead of skipping 1 adjacent element,
     * we skip a variable number of elements.
     * <p>
     * DP Definition: dp(i) = maximum points from questions[i] to end
     * Transition: dp(i) = max(points[i] + dp(i + brainpower[i] + 1), dp(i + 1))
     */

    private long[] memo;

    public static void main(String[] args) {
        _861_a_SolvingQuestionsWithBrainpower solution = new _861_a_SolvingQuestionsWithBrainpower();

        // Test case 1
        int[][] questions1 = {{3, 2}, {4, 3}, {4, 4}, {2, 5}};
        System.out.println("Test 1 - Expected: 5, Got: " + solution.mostPoints(questions1));

        // Test case 2
        int[][] questions2 = {{1, 1}, {2, 2}, {3, 3}, {4, 4}, {5, 5}};
        System.out.println("Test 2 - Expected: 7, Got: " + solution.mostPoints(questions2));
    }

    public long mostPoints(int[][] questions) {
        memo = new long[questions.length];
        java.util.Arrays.fill(memo, -1);
        return dp(questions, 0);
    }

    private long dp(int[][] questions, int i) {
        // Base case: no more questions
        if (i >= questions.length) {
            return 0;
        }

        // Check memo
        if (memo[i] != -1) {
            return memo[i];
        }

        // Two choices: solve current question or skip it
        long solveCurrentQuestion = questions[i][0] + dp(questions, i + questions[i][1] + 1);
        long skipCurrentQuestion = dp(questions, i + 1);

        memo[i] = Math.max(solveCurrentQuestion, skipCurrentQuestion);
        return memo[i];
    }
}