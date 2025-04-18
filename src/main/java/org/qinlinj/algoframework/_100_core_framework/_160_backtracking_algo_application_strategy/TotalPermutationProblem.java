package org.qinlinj.algoframework._100_core_framework._160_backtracking_algo_application_strategy;

// @formatter:off
import java.util.*;
/**
 * Backtracking Algorithm Framework and Explanation
 * -----------------------------------------------
 *
 * Overview:
 * Backtracking is essentially a depth-first search (DFS) algorithm applied to a decision tree.
 * It systematically explores all possible solutions by building candidates incrementally and
 * abandoning a candidate (backtracking) when it determines the candidate cannot lead to a valid solution.
 *
 * Core Concepts:
 * 1. Path: Choices already made in the current exploration.
 * 2. Choice List: Available choices at the current decision point.
 * 3. Termination Condition: Criteria to identify when a complete solution is found.
 *
 * The Algorithm Framework:
 * - At each decision point, try each available choice
 * - Make a choice and mark it as used
 * - Recursively explore with this choice added to the path
 * - Undo the choice (backtrack) and try the next option
 *
 * Visualization of a Decision Tree:
 * - Each node represents a state in the solution construction
 * - Each branch represents a choice
 * - Leaf nodes represent either complete solutions or dead-ends
 *
 * Time Complexity:
 * - Generally high (often exponential or factorial)
 * - Unlike dynamic programming, backtracking doesn't benefit from overlapping subproblems
 * - For permutation problems: O(n!)
 *
 * Common Pattern in Code:
 * ```
 * void backtrack(path, choices) {
 *     if (terminationCondition) {
 *         saveResult(path);
 *         return;
 *     }
 *
 *     for (choice in choices) {
 *         // Make choice
 *         add choice to path;
 *         mark choice as used;
 *
 *         // Recursive exploration
 *         backtrack(path, choices);
 *
 *         // Undo choice (backtrack)
 *         remove choice from path;
 *         mark choice as unused;
 *     }
 * }
 * ```
 *
 * This class demonstrates backtracking algorithm using the classic permutation problem as an example.
 */
public class TotalPermutationProblem {
    // ========== PERMUTATION EXAMPLE ==========
    /**
     * Generate all permutations of the given array of integers
     * This demonstrates the core backtracking framework
     */
    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();

        // Track the current path (partial permutation being built)
        List<Integer> path = new ArrayList<>();

        // Keep track of which elements have been used
        boolean[] used = new boolean[nums.length];

        backtrackPermute(nums, path, used, result);
        return result;
    }

    /**
     * Backtracking function for permutation problem
     *
     * Path: Elements currently in 'path'
     * Choice List: Unused elements in 'nums' (tracked by 'used' array)
     * Termination Condition: When path.size() == nums.length
     */
    private static void backtrackPermute(int[] nums, List<Integer> path, boolean[] used, List<List<Integer>> result) {
        // Termination condition: path contains all elements
        if (path.size() == nums.length) {
            // Save a copy of the current permutation
            result.add(new ArrayList<>(path));
            return;
        }

        // Try each possible next choice
        for (int i = 0; i < nums.length; i++) {
            // Skip if this element is already used
            if (used[i]) {
                continue;
            }

            // Make choice
            path.add(nums[i]);
            used[i] = true;

            // Explore next level of decision tree
            backtrackPermute(nums, path, used, result);

            // Undo choice (backtrack)
            path.remove(path.size() - 1);
            used[i] = false;
        }
    }

    // ========== COMBINATION EXAMPLE ==========
    /**
     * Generate all combinations of k numbers out of the range [1, n]
     * Another application of backtracking
     */
    public static List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        backtrackCombine(1, n, k, new ArrayList<>(), result);
        return result;
    }

    /**
     * Backtracking function for combination problem
     *
     * Path: Elements currently in 'path'
     * Choice List: Numbers from 'start' to 'n'
     * Termination Condition: When path.size() == k
     */
    private static void backtrackCombine(int start, int n, int k, List<Integer> path, List<List<Integer>> result) {
        // Termination condition: path contains k elements
        if (path.size() == k) {
            result.add(new ArrayList<>(path));
            return;
        }

        // Try each possible next choice
        for (int i = start; i <= n; i++) {
            // Make choice
            path.add(i);

            // Explore next level of decision tree
            // Notice we pass i+1 as the start to avoid using the same number again
            backtrackCombine(i + 1, n, k, path, result);

            // Undo choice (backtrack)
            path.remove(path.size() - 1);
        }
    }

    // ========== SUBSET EXAMPLE ==========
    /**
     * Generate all possible subsets of the given array
     * Another classic backtracking problem
     */
    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrackSubsets(nums, 0, new ArrayList<>(), result);
        return result;
    }

    /**
     * Backtracking function for subset problem
     *
     * Path: Elements currently in 'path'
     * Choice List: Include or exclude each element starting from 'start'
     * Termination Condition: When start == nums.length
     */
    private static void backtrackSubsets(int[] nums, int start, List<Integer> path, List<List<Integer>> result) {
        // Add the current subset
        result.add(new ArrayList<>(path));

        // Try each possible next choice
        for (int i = start; i < nums.length; i++) {
            // Make choice to include nums[i]
            path.add(nums[i]);

            // Explore next level of decision tree
            backtrackSubsets(nums, i + 1, path, result);

            // Undo choice (backtrack)
            path.remove(path.size() - 1);
        }
    }

    // ========== ADVANCED APPLICATION: N-QUEENS PROBLEM ==========
    /**
     * Solve the N-Queens problem: place N queens on an NxN chessboard
     * such that no two queens attack each other
     */
    public static List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();

        // Represent the board as a 2D char array
        char[][] board = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = '.';
            }
        }

        backtrackNQueens(board, 0, result);
        return result;
    }

    /**
     * Backtracking function for N-Queens problem
     *
     * Path: Queens placed in rows 0 to row-1
     * Choice List: Valid positions in the current row
     * Termination Condition: When all rows have a queen (row == board.length)
     */
    private static void backtrackNQueens(char[][] board, int row, List<List<String>> result) {
        // Termination condition: all rows have a queen
        if (row == board.length) {
            result.add(constructBoard(board));
            return;
        }

        // Try each column in the current row
        for (int col = 0; col < board.length; col++) {
            // Skip if this position is under attack
            if (!isValid(board, row, col)) {
                continue;
            }

            // Make choice
            board[row][col] = 'Q';

            // Explore next row
            backtrackNQueens(board, row + 1, result);

            // Undo choice (backtrack)
            board[row][col] = '.';
        }
    }

    /**
     * Check if placing a queen at position (row, col) is valid
     */
    private static boolean isValid(char[][] board, int row, int col) {
        // Check column
        for (int i = 0; i < row; i++) {
            if (board[i][col] == 'Q') {
                return false;
            }
        }

        // Check left diagonal
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }

        // Check right diagonal
        for (int i = row - 1, j = col + 1; i >= 0 && j < board.length; i--, j++) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }

        return true;
    }

    /**
     * Convert the char[][] board to List<String> representation
     */
    private static List<String> constructBoard(char[][] board) {
        List<String> result = new ArrayList<>();
        for (char[] row : board) {
            result.add(new String(row));
        }
        return result;
    }
}
