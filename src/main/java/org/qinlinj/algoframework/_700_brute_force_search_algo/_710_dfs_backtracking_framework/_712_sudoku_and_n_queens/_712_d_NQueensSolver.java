package org.qinlinj.algoframework._700_brute_force_search_algo._710_dfs_backtracking_framework._712_sudoku_and_n_queens;

import java.util.*;

/**
 * N-QUEENS PROBLEM SOLVER USING BACKTRACKING
 * ==========================================
 * <p>
 * Problem: Place N queens on an NxN chessboard so that no queen can attack another.
 * LeetCode Problem 51: https://leetcode.com/problems/n-queens/
 * <p>
 * Rules: A queen can attack any piece in the same row, column, or diagonal.
 * <p>
 * Key Insights:
 * 1. We can use backtracking to try different queen placements row by row
 * 2. Unlike Sudoku where we consider each cell, here we consider each row
 * 3. In each row, we need to place exactly one queen in a valid column
 * 4. No need to check placements below the current row (as we build top-down)
 * <p>
 * Optimizations:
 * - Since each row must contain exactly one queen, we can consider rows sequentially
 * - We only need to validate against queens placed in previous rows
 * - Only need to check up, up-left, and up-right diagonals
 * <p>
 * Differences from Sudoku:
 * - Need to find all solutions, not just one
 * - Different validation rules (row, column, diagonal checks)
 * - Different representation of the board (board with dots and 'Q's)
 * <p>
 * Time Complexity: O(N!), as there are N! possible arrangements of N queens
 * Space Complexity: O(N²) for storing the board
 */
public class _712_d_NQueensSolver {

    // Store all valid solutions
    private List<List<String>> results = new ArrayList<>();

    /**
     * Main method to demonstrate the N-Queens solution
     */
    public static void main(String[] args) {
        _712_d_NQueensSolver solver = new _712_d_NQueensSolver();
        int n = 4; // 4 queens on a 4x4 board

        List<List<String>> solutions = solver.solveNQueens(n);

        System.out.println("Found " + solutions.size() + " solutions for " + n + "-Queens problem:");
        for (int i = 0; i < solutions.size(); i++) {
            System.out.println("Solution " + (i + 1) + ":");
            solver.printBoard(solutions.get(i));
        }
    }

    /**
     * Find all solutions to the N-Queens problem
     *
     * @param n Size of the board and number of queens
     * @return List of all valid board configurations
     */
    public List<List<String>> solveNQueens(int n) {
        // Initialize empty board with '.' in all positions
        List<String> board = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            StringBuilder row = new StringBuilder();
            for (int j = 0; j < n; j++) {
                row.append('.');
            }
            board.add(row.toString());
        }

        // Start backtracking from the first row
        backtrack(board, 0);

        return results;
    }

    /**
     * Backtracking function to solve N-Queens
     *
     * @param board Current state of the board
     * @param row   Current row being considered
     */
    private void backtrack(List<String> board, int row) {
        // If all rows have a queen, we've found a valid solution
        if (row == board.size()) {
            results.add(new ArrayList<>(board));
            return;
        }

        int n = board.get(row).length();
        for (int col = 0; col < n; col++) {
            // Skip invalid placements
            if (!isValid(board, row, col)) {
                continue;
            }

            // Make a choice - place a queen
            char[] rowChars = board.get(row).toCharArray();
            rowChars[col] = 'Q';
            board.set(row, new String(rowChars));

            // Explore next row
            backtrack(board, row + 1);

            // Undo the choice (backtrack)
            rowChars[col] = '.';
            board.set(row, new String(rowChars));
        }
    }

    /**
     * Check if placing a queen at (row, col) is valid
     *
     * @param board Current state of the board
     * @param row   Row to place the queen
     * @param col   Column to place the queen
     * @return true if placement is valid, false otherwise
     */
    private boolean isValid(List<String> board, int row, int col) {
        int n = board.size();

        // Check column (vertical)
        for (int i = 0; i < row; i++) {
            if (board.get(i).charAt(col) == 'Q') {
                return false;
            }
        }

        // Check upper-left diagonal
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (board.get(i).charAt(j) == 'Q') {
                return false;
            }
        }

        // Check upper-right diagonal
        for (int i = row - 1, j = col + 1; i >= 0 && j < n; i--, j++) {
            if (board.get(i).charAt(j) == 'Q') {
                return false;
            }
        }

        return true;
    }

    /**
     * Helper method to print a board configuration
     *
     * @param board A valid N-Queens configuration
     */
    private void printBoard(List<String> board) {
        for (String row : board) {
            System.out.println(row);
        }
        System.out.println();
    }
}