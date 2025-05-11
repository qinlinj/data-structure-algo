package org.qinlinj.algoframework._700_brute_force_search_algo._710_dfs_backtracking_framework._713_sudoku_solver_game;

/**
 * SUDOKU SOLVER WITH VISUALIZATION
 * ================================
 * <p>
 * This class extends the Sudoku solver to include visualization capabilities,
 * allowing users to see the backtracking algorithm in action.
 * <p>
 * Key Features:
 * 1. Step-by-step visualization of the backtracking process
 * 2. Configurable delay between steps for better observation
 * 3. Highlighting of the current cell being considered
 * 4. Statistics tracking (attempts, backtracks, etc.)
 * <p>
 * This visualization helps to:
 * - Understand how backtracking works in practice
 * - See how constraints propagate and reduce the search space
 * - Appreciate the efficiency of the algorithm despite its brute-force nature
 * <p>
 * Educational Value:
 * - Demonstrates algorithm execution flow visually
 * - Shows the power of backtracking for constraint satisfaction problems
 * - Illustrates how "failures" (backtracking) are essential to finding solutions
 */
public class _713_c_SudokuVisualizer {

    // Flag to indicate if a solution has been found
    private boolean found = false;
    // Statistics
    private int attempts = 0;
    private int backtracks = 0;
    // The board handler instance
    private VisualizableBoardHandler boardHandler;
    // Visualization delay in milliseconds
    private long delay;

    /**
     * Constructor with configurable delay
     *
     * @param delay Time to pause between steps (in milliseconds)
     */
    public _713_c_SudokuVisualizer(long delay) {
        this.delay = delay;
    }

    /**
     * Main method to demonstrate the Sudoku solver with visualization
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

        // Create a console board handler
        ConsoleBoardHandler boardHandler = new ConsoleBoardHandler(initialBoard);

        // Create and use the Sudoku solver with visualization
        // Set delay to 50ms for a reasonable visualization speed
        _713_c_SudokuVisualizer solver = new _713_c_SudokuVisualizer(50);
        solver.solveSudoku(boardHandler);
    }

    /**
     * Solve the Sudoku puzzle with visualization
     *
     * @param boardHandler Interface to interact with the Sudoku board
     */
    public void solveSudoku(VisualizableBoardHandler boardHandler) {
        this.boardHandler = boardHandler;
        this.found = false;
        this.attempts = 0;
        this.backtracks = 0;

        System.out.println("Starting Sudoku solver visualization...");
        System.out.println("Initial board:");
        boardHandler.display();

        long startTime = System.currentTimeMillis();
        backtrack(0);
        long endTime = System.currentTimeMillis();

        System.out.println("\nSolution " + (found ? "found" : "not found") + "!");
        System.out.println("Statistics:");
        System.out.println("- Time taken: " + (endTime - startTime) + " ms");
        System.out.println("- Attempts: " + attempts);
        System.out.println("- Backtracks: " + backtracks);

        if (found) {
            System.out.println("\nFinal solution:");
            boardHandler.display();
        }
    }

    /**
     * Backtracking function to solve Sudoku with visualization
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

        // Highlight the current cell
        boardHandler.highlightCell(i, j);
        boardHandler.pause(delay);

        // If this cell is not editable (has a preset value), move to the next cell
        if (boardHandler.get(i, j) != null && !boardHandler.isEditable(i, j)) {
            backtrack(index + 1);
            return;
        }

        // Try each digit from 1 to 9
        for (int digit = 1; digit <= 9; digit++) {
            attempts++;

            // Skip invalid placements
            if (!isValid(i, j, digit)) {
                continue;
            }

            // Place the digit
            boardHandler.set(i, j, digit);
            boardHandler.display();
            boardHandler.pause(delay);

            // Move to the next cell
            backtrack(index + 1);

            // If we found a solution, don't undo this choice
            if (found) {
                return;
            }

            // Backtrack
            backtracks++;
            boardHandler.set(i, j, null);
            boardHandler.highlightCell(i, j);
            boardHandler.display();
            boardHandler.pause(delay);
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
     * Interface to handle the board operations with visualization support
     */
    public interface VisualizableBoardHandler {
        Integer get(int row, int col);

        void set(int row, int col, Integer value);

        boolean isEditable(int row, int col);

        // Visualization methods
        void highlightCell(int row, int col);

        void clearHighlight();

        void display();

        void pause(long milliseconds);
    }

    /**
     * Console-based implementation of the VisualizableBoardHandler for testing
     */
    public static class ConsoleBoardHandler implements VisualizableBoardHandler {
        private Integer[][] board;
        private boolean[][] editable;
        private int highlightRow = -1;
        private int highlightCol = -1;

        public ConsoleBoardHandler(Integer[][] initialBoard) {
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

        @Override
        public void highlightCell(int row, int col) {
            this.highlightRow = row;
            this.highlightCol = col;
        }

        @Override
        public void clearHighlight() {
            this.highlightRow = -1;
            this.highlightCol = -1;
        }

        @Override
        public void display() {
            // Clear console for better visualization (works in some terminals)
            System.out.print("\033[H\033[2J");
            System.out.flush();

            System.out.println("Sudoku Board:");
            for (int i = 0; i < 9; i++) {
                if (i % 3 == 0 && i > 0) {
                    System.out.println("------+-------+------");
                }
                for (int j = 0; j < 9; j++) {
                    if (j % 3 == 0 && j > 0) {
                        System.out.print("| ");
                    }

                    // Highlight the current cell
                    if (i == highlightRow && j == highlightCol) {
                        System.out.print("[");
                        System.out.print(board[i][j] == null ? "." : board[i][j]);
                        System.out.print("]");
                    } else {
                        System.out.print(" ");
                        System.out.print(board[i][j] == null ? "." : board[i][j]);
                        System.out.print(" ");
                    }
                }
                System.out.println();
            }
        }

        @Override
        public void pause(long milliseconds) {
            try {
                Thread.sleep(milliseconds);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}