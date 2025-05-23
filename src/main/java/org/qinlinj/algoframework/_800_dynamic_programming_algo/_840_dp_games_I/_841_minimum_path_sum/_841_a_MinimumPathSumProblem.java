package org.qinlinj.algoframework._800_dynamic_programming_algo._840_dp_games_I._841_minimum_path_sum;

/**
 * MINIMUM PATH SUM - PROBLEM INTRODUCTION AND ANALYSIS
 * <p>
 * KNOWLEDGE POINTS:
 * 1. Dynamic Programming Problem Recognition: 2D grid optimization problems typically require DP
 * 2. State Definition: Define what each state represents in the problem context
 * 3. Constraint Analysis: Movement restrictions (only right/down) simplify state transitions
 * 4. Problem Transformation: Break complex problems into smaller subproblems
 * 5. State Transition Logic: Current optimal solution depends on previous optimal solutions
 * <p>
 * PROBLEM ANALYSIS:
 * - Given: m x n grid with non-negative integers
 * - Goal: Find path from top-left to bottom-right with minimum sum
 * - Constraint: Can only move right or down
 * - This is a classic 2D dynamic programming optimization problem
 * <p>
 * INTUITION:
 * To reach any cell (i,j), we can only come from:
 * - Cell above: (i-1, j)
 * - Cell left: (i, j-1)
 * We choose the path that gives us the minimum sum so far.
 */
public class _841_a_MinimumPathSumProblem {

    // Test method
    public static void main(String[] args) {
        _841_a_MinimumPathSumProblem analyzer = new _841_a_MinimumPathSumProblem();
        analyzer.analyzeProblem();
        analyzer.whyDynamicProgramming();
    }

    /**
     * LeetCode 64: Minimum Path Sum
     * <p>
     * Given a m x n grid filled with non-negative numbers,
     * find a path from top left to bottom right,
     * which minimizes the sum of all numbers along its path.
     * <p>
     * Note: You can only move either down or right at any point in time.
     * <p>
     * Example 1:
     * Input: grid = [[1,3,1],[1,5,1],[4,2,1]]
     * Output: 7
     * Explanation: Path 1→3→1→1→1 minimizes the sum.
     * <p>
     * Example 2:
     * Input: grid = [[1,2,3],[4,5,6]]
     * Output: 12
     */

    // Function signature - this will be implemented in subsequent classes
    public int minPathSum(int[][] grid) {
        // Implementation will be shown step by step in following classes
        return 0;
    }

    /**
     * Problem Analysis Method
     * This method demonstrates how to think about the problem
     */
    public void analyzeProblem() {
        System.out.println("=== MINIMUM PATH SUM PROBLEM ANALYSIS ===");
        System.out.println("1. Problem Type: 2D Grid Optimization");
        System.out.println("2. Constraints: Only move right or down");
        System.out.println("3. Goal: Minimize path sum from top-left to bottom-right");
        System.out.println("4. Approach: Dynamic Programming");
        System.out.println("5. Key Insight: Optimal path to (i,j) depends on optimal paths to (i-1,j) and (i,j-1)");

        // Visual representation of the problem
        int[][] exampleGrid = {{1, 3, 1}, {1, 5, 1}, {4, 2, 1}};
        System.out.println("\nExample Grid:");
        printGrid(exampleGrid);
        System.out.println("Optimal Path: 1→3→1→1→1 = 7");
    }

    /**
     * Helper method to visualize the grid
     */
    private void printGrid(int[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * Demonstrates why this is a DP problem
     */
    public void whyDynamicProgramming() {
        System.out.println("\n=== WHY DYNAMIC PROGRAMMING? ===");
        System.out.println("1. Optimal Substructure: Solution contains optimal solutions to subproblems");
        System.out.println("2. Overlapping Subproblems: Same subproblems are solved multiple times");
        System.out.println("3. Choice at each step affects overall optimization");
        System.out.println("4. State can be clearly defined and transitions are clear");
    }
}