package org.qinlinj.algoframework._700_brute_force_search_algo._710_dfs_backtracking_framework._712_sudoku_and_n_queens;

/**
 * SUDOKU SOLVER USING BACKTRACKING
 * ================================
 * <p>
 * Problem: Solve a 9x9 Sudoku puzzle where some cells may already be filled.
 * LeetCode Problem 37: https://leetcode.com/problems/sudoku-solver/
 * <p>
 * Sudoku Rules:
 * 1. Each row must contain digits 1-9 without repetition
 * 2. Each column must contain digits 1-9 without repetition
 * 3. Each 3x3 box must contain digits 1-9 without repetition
 * <p>
 * Key Insights:
 * 1. We can use backtracking to try different numbers in each empty cell
 * 2. We'll convert the 2D board into a 1D conceptual space for simpler traversal
 * 3. For each cell, we try digits 1-9, validate, and move to the next cell
 * 4. If we reach a dead end, we backtrack
 * 5. Since we only need one valid solution, we can stop once found
 * <p>
 * Optimization Observations:
 * - While 9^81 seems like a huge search space, the actual search space is much smaller:
 * - Many cells are pre-filled
 * - Each empty cell has far fewer than 9 valid choices due to constraints
 * - We only need to find one solution
 * - For computers, more rules actually make problems easier by reducing the search space
 * <p>
 * Time Complexity: Depends on the input, but much less than O(9^81)
 * Space Complexity: O(1) as the board size is fixed at 9x9
 */
public class _712_b_SudokuSolver {

    // Flag to indicate if a solution has been found
    private boolean found = false;

    /**
     * Main method to demonstrate solving a Sudoku puzzle
     */
    public static void main(String[] args) {
        // Example Sudoku puzzle
        char[][] board = {
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };

        _712_b_SudokuSolver solver = new _712_b_SudokuSolver();
        solver.solveSudoku(board);

        // Print the solution
        System.out.println("Sudoku Solution:");
        for (char[] row : board) {
            System.out.println(new String(row));
        }
    }

    /**
     * Solve the Sudoku puzzle
     *
     * @param board The 9x9 Sudoku board
     */
    public void solveSudoku(char[][] board) {
        backtrack(board, 0);
    }

    /**
     * Backtracking function to solve Sudoku
     *
     * @param board The current state of the board
     * @param index The current position (0 to 80) being considered
     */
    private void backtrack(char[][] board, int index) {
        // If a solution is already found, return immediately
        if (found) {
            return;
        }

        int m = 9, n = 9;
        // If we've filled all 81 cells, we've found a solution
        if (index == m * n) {
            found = true;
            return;
        }

        // Convert 1D index to 2D coordinates
        int i = index / n;
        int j = index % n;

        // If the current cell is already filled, move to the next cell
        if (board[i][j] != '.') {
            backtrack(board, index + 1);
            return;
        }

        // Try each possible digit (1-9) for the current cell
        for (char ch = '1'; ch <= '9'; ch++) {
            // Skip invalid choices
            if (!isValid(board, i, j, ch)) {
                continue;
            }

            // Make a choice
            board[i][j] = ch;

            // Explore further
            backtrack(board, index + 1);

            // If solution found, return without undoing (to preserve the solution)
            if (found) {
                return;
            }

            // Undo the choice (backtrack)
            board[i][j] = '.';
        }
    }

    /**
     * Check if it's valid to place digit 'num' at position (r,c)
     *
     * @param board The current board state
     * @param r     Row index
     * @param c     Column index
     * @param num   The digit to check
     * @return true if the placement is valid, false otherwise
     */
    private boolean isValid(char[][] board, int r, int c, char num) {
        // Check the entire row
        for (int i = 0; i < 9; i++) {
            if (board[r][i] == num) {
                return false;
            }
        }

        // Check the entire column
        for (int i = 0; i < 9; i++) {
            if (board[i][c] == num) {
                return false;
            }
        }

        // Check the 3x3 box
        int boxRow = (r / 3) * 3;
        int boxCol = (c / 3) * 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[boxRow + i][boxCol + j] == num) {
                    return false;
                }
            }
        }

        return true;
    }
}