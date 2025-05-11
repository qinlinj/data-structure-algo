package org.qinlinj.algoframework._700_brute_force_search_algo._710_dfs_backtracking_framework._713_sudoku_solver_game;

import java.util.*;

/**
 * OPTIMIZED SUDOKU CHEATER IMPLEMENTATION
 * ======================================
 * <p>
 * This class implements an optimized version of the Sudoku solver (cheater)
 * that uses hash sets to track occupied numbers in rows, columns, and boxes
 * for faster validation.
 * <p>
 * Key Optimizations:
 * 1. Uses hash sets to track numbers in each row, column, and 3x3 box
 * 2. Reduces validation time from O(n) to O(1) for each placement check
 * 3. Pre-computes box indices for faster lookup
 * 4. Still preserves the core backtracking algorithm
 * <p>
 * This optimization is particularly useful for:
 * - Languages with slower execution (like Python)
 * - Solving more complex Sudoku variants
 * - Demonstrating space-time tradeoffs in algorithms
 * <p>
 * Time Complexity: Still depends on the input board, but validation is now O(1)
 * Space Complexity: O(1) with the additional overhead of 27 hash sets
 */
public class _713_b_SudokuCheaterOptimized {

    // Flag to indicate if a solution has been found
    private boolean found = false;
    // The board handler instance
    private BoardHandler boardHandler;
    // Hash sets to track numbers in each row, column, and box
    private List<Set<Integer>> rows = new ArrayList<>(9);
    private List<Set<Integer>> cols = new ArrayList<>(9);
    private List<Set<Integer>> boxes = new ArrayList<>(9);
    /**
     * Constructor to initialize the hash sets
     */
    public _713_b_SudokuCheaterOptimized() {
        for (int i = 0; i < 9; i++) {
            rows.add(new HashSet<>());
            cols.add(new HashSet<>());
            boxes.add(new HashSet<>());
        }
    }

    /**
     * Main method to demonstrate the optimized Sudoku solver
     */
    public static void main(String[] args) {
        // Example Sudoku puzzle (null represents empty cells)
        Integer[][] initialBoard = {
                {5, 3, null, null, 7, null, null, null, null},
                {6, null, null, 1, 9, 5, null, null, null},
                {null, 9, 8, null, null, null, null, 6, null},
                {8, null, null, null, 6, null, null, null, 3},
                {4, null, null, 8, null, 3, null, null, 1},
                {7, null, null, null, 2, null, null, null, 6},
                {null, 6, null, null, null, null, 2, 8, null},
                {null, null, null, 4, 1, 9, null, null, 5},
                {null, null, null, null, 8, null, null, 7, 9}
        };

        // Create a sample board handler
        SampleBoardHandler boardHandler = new SampleBoardHandler(initialBoard);

        System.out.println("Initial Sudoku Board:");
        boardHandler.printBoard();

        // Create and use the optimized Sudoku solver
        _713_b_SudokuCheaterOptimized solver = new _713_b_SudokuCheaterOptimized();

        // Measure performance
        long startTime = System.nanoTime();
        solver.solveSudoku(boardHandler);
        long endTime = System.nanoTime();

        System.out.println("\nSolved Sudoku Board:");
        boardHandler.printBoard();

        System.out.println("\nSolution took " + (endTime - startTime) / 1000000 + " milliseconds");
    }

    /**
     * Get the box index (0-8) for a given cell position
     */
    private int getBoxIndex(int row, int col) {
        return (row / 3) * 3 + (col / 3);
    }

    /**
     * Solve the Sudoku puzzle
     *
     * @param boardHandler Interface to interact with the Sudoku board
     */
    public void solveSudoku(BoardHandler boardHandler) {
        this.boardHandler = boardHandler;
        this.found = false;

        // Clear all hash sets
        for (int i = 0; i < 9; i++) {
            rows.get(i).clear();
            cols.get(i).clear();
            boxes.get(i).clear();
        }

        // Initialize hash sets with preset numbers
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                Integer value = boardHandler.get(i, j);
                if (value != null) {
                    rows.get(i).add(value);
                    cols.get(j).add(value);
                    boxes.get(getBoxIndex(i, j)).add(value);
                }
            }
        }

        backtrack(0);
    }

    /**
     * Backtracking function to solve Sudoku
     *
     * @param index The current position (0 to 80) being considered
     */
    private void backtrack(int index) {
        if (found) {
            // Already found a solution, no need to continue
            return;
        }

        final int m = 9, n = 9;
        final int i = index / n;
        final int j = index % n;

        // If we've filled all cells, we've found a solution
        if (index == m * n) {
            found = true;
            return;
        }

        // If this cell is not editable (has a preset value), move to the next cell
        if (boardHandler.get(i, j) != null && !boardHandler.isEditable(i, j)) {
            backtrack(index + 1);
            return;
        }

        // Try each digit from 1 to 9
        for (int digit = 1; digit <= 9; digit++) {
            // Skip invalid placements using our optimized validation
            if (!isValid(i, j, digit)) {
                continue;
            }

            // Place the digit and update our hash sets
            boardHandler.set(i, j, digit);
            rows.get(i).add(digit);
            cols.get(j).add(digit);
            boxes.get(getBoxIndex(i, j)).add(digit);

            // Move to the next cell
            backtrack(index + 1);

            // If we found a solution, don't undo this choice
            if (found) {
                return;
            }

            // Undo the choice (backtrack) and update our hash sets
            boardHandler.set(i, j, null);
            rows.get(i).remove(digit);
            cols.get(j).remove(digit);
            boxes.get(getBoxIndex(i, j)).remove(digit);
        }
    }

    /**
     * Check if placing digit at position (row, col) is valid
     * This uses O(1) hash set lookups instead of O(n) scans
     *
     * @return true if the placement is valid, false otherwise
     */
    private boolean isValid(int row, int col, int digit) {
        // Check if the number exists in the current row
        if (rows.get(row).contains(digit)) {
            return false;
        }

        // Check if the number exists in the current column
        if (cols.get(col).contains(digit)) {
            return false;
        }

        // Check if the number exists in the current 3x3 box
        if (boxes.get(getBoxIndex(row, col)).contains(digit)) {
            return false;
        }

        return true;
    }

    /**
     * Interface to handle the board operations
     */
    public interface BoardHandler {
        Integer get(int row, int col);

        void set(int row, int col, Integer value);

        boolean isEditable(int row, int col);
    }

    /**
     * Sample implementation of the BoardHandler for testing
     */
    public static class SampleBoardHandler implements BoardHandler {
        private Integer[][] board;
        private boolean[][] editable;

        public SampleBoardHandler(Integer[][] initialBoard) {
            this.board = new Integer[9][9];
            this.editable = new boolean[9][9];

            // Copy the initial board and mark which cells are editable
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    this.board[i][j] = initialBoard[i][j];
                    this.editable[i][j] = (initialBoard[i][j] == null);
                }
            }
        }

        @Override
        public Integer get(int row, int col) {
            return board[row][col];
        }

        @Override
        public void set(int row, int col, Integer value) {
            board[row][col] = value;
        }

        @Override
        public boolean isEditable(int row, int col) {
            return editable[row][col];
        }

        /**
         * Print the current state of the board
         */
        public void printBoard() {
            for (int i = 0; i < 9; i++) {
                if (i % 3 == 0 && i > 0) {
                    System.out.println("------+-------+------");
                }
                for (int j = 0; j < 9; j++) {
                    if (j % 3 == 0 && j > 0) {
                        System.out.print("| ");
                    }
                    System.out.print(board[i][j] == null ? ". " : board[i][j] + " ");
                }
                System.out.println();
            }
        }
    }
}