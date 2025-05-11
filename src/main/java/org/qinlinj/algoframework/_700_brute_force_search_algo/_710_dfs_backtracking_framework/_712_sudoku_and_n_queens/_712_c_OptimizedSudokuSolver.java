package org.qinlinj.algoframework._700_brute_force_search_algo._710_dfs_backtracking_framework._712_sudoku_and_n_queens;

import java.util.*;

/**
 * OPTIMIZED SUDOKU SOLVER USING HASH SETS
 * =======================================
 * <p>
 * Problem: Solve a 9x9 Sudoku puzzle with improved efficiency.
 * <p>
 * Optimization Strategy:
 * 1. Use hash sets to track numbers already present in each row, column, and 3x3 box
 * 2. This replaces the O(n) validation checks with O(1) lookups
 * 3. Trade-off: More memory usage for faster validation
 * <p>
 * Key Insights:
 * - The basic backtracking approach remains the same
 * - The isValid function is optimized by replacing linear scans with constant-time lookups
 * - This optimization is especially helpful for languages like Python that might time out otherwise
 * - For some languages, the simpler approach might actually be faster due to lower overhead
 * <p>
 * Data Structures:
 * - List<Set<Character>> rows: Track numbers in each row
 * - List<Set<Character>> cols: Track numbers in each column
 * - List<Set<Character>> boxes: Track numbers in each 3x3 box
 * <p>
 * Time Complexity: Still depends on the input, but each validation is now O(1)
 * Space Complexity: O(1) as we use a fixed amount of extra space for the hash sets
 */
public class _712_c_OptimizedSudokuSolver {

    // Flag to indicate if a solution has been found
    private boolean found = false;

    // Track numbers in each row, column, and box
    private List<Set<Character>> rows = new ArrayList<>(9);
    private List<Set<Character>> cols = new ArrayList<>(9);
    private List<Set<Character>> boxes = new ArrayList<>(9);

    /**
     * Constructor to initialize the hash sets
     */
    public _712_c_OptimizedSudokuSolver() {
        for (int i = 0; i < 9; i++) {
            rows.add(new HashSet<>());
            cols.add(new HashSet<>());
            boxes.add(new HashSet<>());
        }
    }

    /**
     * Main method to demonstrate solving a Sudoku puzzle with the optimized approach
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

        _712_c_OptimizedSudokuSolver solver = new _712_c_OptimizedSudokuSolver();
        solver.solveSudoku(board);

        // Print the solution
        System.out.println("Optimized Sudoku Solution:");
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
        // Initialize the hash sets with the prefilled numbers
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    rows.get(i).add(board[i][j]);
                    cols.get(j).add(board[i][j]);
                    boxes.get(getBoxIndex(i, j)).add(board[i][j]);
                }
            }
        }

        // Start backtracking
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
            if (!isValid(i, j, ch)) {
                continue;
            }

            // Make a choice
            board[i][j] = ch;
            rows.get(i).add(ch);
            cols.get(j).add(ch);
            boxes.get(getBoxIndex(i, j)).add(ch);

            // Explore further
            backtrack(board, index + 1);

            // If solution found, return without undoing (to preserve the solution)
            if (found) {
                return;
            }

            // Undo the choice (backtrack)
            board[i][j] = '.';
            rows.get(i).remove(ch);
            cols.get(j).remove(ch);
            boxes.get(getBoxIndex(i, j)).remove(ch);
        }
    }

    /**
     * Get the box index (0-8) for a given cell position
     *
     * @param r Row index
     * @param c Column index
     * @return The box index (0-8)
     */
    private int getBoxIndex(int r, int c) {
        return (r / 3) * 3 + (c / 3);
    }

    /**
     * Check if it's valid to place digit 'num' at position (r,c)
     * This version uses O(1) hash set lookups instead of O(n) scans
     *
     * @param r   Row index
     * @param c   Column index
     * @param num The digit to check
     * @return true if the placement is valid, false otherwise
     */
    private boolean isValid(int r, int c, char num) {
        // Check if the number exists in the current row
        if (rows.get(r).contains(num)) {
            return false;
        }

        // Check if the number exists in the current column
        if (cols.get(c).contains(num)) {
            return false;
        }

        // Check if the number exists in the current 3x3 box
        if (boxes.get(getBoxIndex(r, c)).contains(num)) {
            return false;
        }

        return true;
    }
}