package org.qinlinj.algoframework._700_brute_force_search_algo._710_dfs_backtracking_framework._713_sudoku_solver_game;

public class _713_a_SudokuCheater {
    // Flag to indicate if a solution has been found
    private boolean found = false;
    // The board handler instance
    private BoardHandler boardHandler;

    /**
     * Solve the Sudoku puzzle
     *
     * @param boardHandler Interface to interact with the Sudoku board
     */
    public void solveSudoku(BoardHandler boardHandler) {
        this.boardHandler = boardHandler;
        this.found = false;
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
            // Skip invalid placements
            if (!isValid(i, j, digit)) {
                continue;
            }

            // Place the digit
            boardHandler.set(i, j, digit);

            // Move to the next cell
            backtrack(index + 1);

            // If we found a solution, don't undo this choice
            if (found) {
                return;
            }

            // Undo the choice (backtrack)
            boardHandler.set(i, j, null);
        }
    }

    /**
     * Check if placing digit at position (row, col) is valid
     *
     * @return true if the placement is valid, false otherwise
     */
    private boolean isValid(int row, int col, int digit) {
        // Check the entire row
        for (int c = 0; c < 9; c++) {
            if (boardHandler.get(row, c) != null && boardHandler.get(row, c) == digit) {
                return false;
            }
        }

        // Check the entire column
        for (int r = 0; r < 9; r++) {
            if (boardHandler.get(r, col) != null && boardHandler.get(r, col) == digit) {
                return false;
            }
        }

        // Check the 3x3 box
        int boxRow = (row / 3) * 3;
        int boxCol = (col / 3) * 3;
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                if (boardHandler.get(boxRow + r, boxCol + c) != null &&
                        boardHandler.get(boxRow + r, boxCol + c) == digit) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Interface to handle the board operations
     * This simulates the boardHandler from the JavaScript version
     */
    public interface BoardHandler {
        /**
         * Get the value at position (row, col)
         *
         * @return null if empty, otherwise the digit 1-9
         */
        Integer get(int row, int col);

        /**
         * Set a value at position (row, col)
         *
         * @param value The digit to set (1-9), or null to clear the cell
         */
        void set(int row, int col, Integer value);

        /**
         * Check if the cell at position (row, col) is editable
         *
         * @return true if the cell can be modified, false if it's a preset value
         */
        boolean isEditable(int row, int col);
    }
}
