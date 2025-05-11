package org.qinlinj.algoframework._700_brute_force_search_algo._710_dfs_backtracking_framework._713_sudoku_solver_game;

import java.util.*;

/**
 * INTERACTIVE SUDOKU CHEATER APPLICATION
 * =====================================
 * <p>
 * A complete interactive console application that allows users to:
 * 1. Enter their own Sudoku puzzles
 * 2. Solve puzzles automatically using backtracking
 * 3. Get hints for specific cells
 * 4. Visualize the solving process
 * 5. Save and load puzzles
 * <p>
 * Key Features:
 * - User-friendly console interface
 * - Multiple solving modes (instant, step-by-step, hint)
 * - Input validation to ensure valid Sudoku puzzles
 * - Performance statistics
 * <p>
 * This application demonstrates:
 * - How to build a practical tool using backtracking algorithms
 * - User interface considerations for algorithm visualization
 * - Integration of multiple solving strategies
 * <p>
 * Usage scenarios:
 * - Checking solutions to newspaper/magazine Sudoku puzzles
 * - Learning Sudoku solving techniques
 * - Testing performance of different Sudoku algorithms
 */
public class _713_e_SudokuCheaterInteractive {

    // The Sudoku board
    private Integer[][] board = new Integer[9][9];

    // Track which cells are editable (not part of the original puzzle)
    private boolean[][] editable = new boolean[9][9];

    // Sets to track numbers in each row, column, and box
    private Set<Integer>[] rows = new HashSet[9];
    private Set<Integer>[] cols = new HashSet[9];
    private Set<Integer>[] boxes = new HashSet[9];

    // Flag to indicate if a solution has been found
    private boolean found = false;

    // Statistics
    private int attempts = 0;
    private int backtracks = 0;

    /**
     * Constructor to initialize the application
     */
    public _713_e_SudokuCheaterInteractive() {
        // Initialize the sets
        for (int i = 0; i < 9; i++) {
            rows[i] = new HashSet<>();
            cols[i] = new HashSet<>();
            boxes[i] = new HashSet<>();
        }

        // Initialize the board to empty
        clearBoard();
    }

    /**
     * Main method to start the application
     */
    public static void main(String[] args) {
        _713_e_SudokuCheaterInteractive app = new _713_e_SudokuCheaterInteractive();
        app.run();
    }

    /**
     * Clear the board and reset all tracking variables
     */
    public void clearBoard() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                board[i][j] = null;
                editable[i][j] = true;
            }
            rows[i].clear();
            cols[i].clear();
            boxes[i].clear();
        }
        found = false;
        attempts = 0;
        backtracks = 0;
    }

    /**
     * Get the box index for a given cell position
     */
    private int getBoxIndex(int row, int col) {
        return (row / 3) * 3 + (col / 3);
    }

    /**
     * Check if a number can be placed at the specified position
     */
    public boolean isValid(int row, int col, int num) {
        return !rows[row].contains(num) &&
                !cols[col].contains(num) &&
                !boxes[getBoxIndex(row, col)].contains(num);
    }

    /**
     * Place a number on the board and update tracking sets
     */
    public void setNumber(int row, int col, Integer num) {
        // If there was a previous number here, remove it from tracking sets
        if (board[row][col] != null) {
            rows[row].remove(board[row][col]);
            cols[col].remove(board[row][col]);
            boxes[getBoxIndex(row, col)].remove(board[row][col]);
        }

        // Set the new number
        board[row][col] = num;

        // If the new number is not null, add it to tracking sets
        if (num != null) {
            rows[row].add(num);
            cols[col].add(num);
            boxes[getBoxIndex(row, col)].add(num);
        }
    }

    /**
     * Set a cell as part of the original puzzle (non-editable)
     */
    public void setOriginal(int row, int col, int num) {
        setNumber(row, col, num);
        editable[row][col] = false;
    }

    /**
     * Solve the Sudoku puzzle
     *
     * @return true if a solution was found, false otherwise
     */
    public boolean solve() {
        found = false;
        attempts = 0;
        backtracks = 0;
        backtrack(0);
        return found;
    }

    /**
     * Backtracking algorithm to solve the puzzle
     */
    private void backtrack(int index) {
        if (found) return;

        int i = index / 9;
        int j = index % 9;

        // If we've filled all cells, we've found a solution
        if (index == 81) {
            found = true;
            return;
        }

        // If this cell is not editable (part of the original puzzle), move to the next cell
        if (board[i][j] != null && !editable[i][j]) {
            backtrack(index + 1);
            return;
        }

        // Try each digit from 1 to 9
        for (int digit = 1; digit <= 9; digit++) {
            attempts++;

            // Skip invalid placements
            if (!isValid(i, j, digit)) continue;

            // Place the digit
            setNumber(i, j, digit);

            // Move to the next cell
            backtrack(index + 1);

            // If we found a solution, don't undo this choice
            if (found) return;

            // Undo the choice (backtrack)
            setNumber(i, j, null);
            backtracks++;
        }
    }

    /**
     * Get a hint for a specific cell
     *
     * @return the correct digit for the cell, or null if no solution exists
     */
    public Integer getHint(int row, int col) {
        if (board[row][col] != null) return board[row][col];

        // Save the current board state
        Integer[][] savedBoard = new Integer[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                savedBoard[i][j] = board[i][j];
            }
        }

        // Solve the puzzle
        boolean canBeSolved = solve();

        // Get the digit from the solution
        Integer hint = canBeSolved ? board[row][col] : null;

        // Restore the board state
        for (int i = 0; i < 9; i++) {
            rows[i].clear();
            cols[i].clear();
            boxes[i].clear();
        }

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                board[i][j] = savedBoard[i][j];
                if (board[i][j] != null) {
                    rows[i].add(board[i][j]);
                    cols[j].add(board[i][j]);
                    boxes[getBoxIndex(i, j)].add(board[i][j]);
                }
            }
        }

        return hint;
    }

    /**
     * Print the current state of the board
     */
    public void printBoard() {
        System.out.println("-------------------------");
        for (int i = 0; i < 9; i++) {
            System.out.print("| ");
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == null) {
                    System.out.print(". ");
                } else {
                    System.out.print(board[i][j] + " ");
                }
                if ((j + 1) % 3 == 0) System.out.print("| ");
            }
            System.out.println();
            if ((i + 1) % 3 == 0) {
                System.out.println("-------------------------");
            }
        }
    }

    /**
     * Check if the current board state is valid
     */
    public boolean isValidBoard() {
        // Check each row, column, and box for duplicates
        for (int i = 0; i < 9; i++) {
            Set<Integer> rowCheck = new HashSet<>();
            Set<Integer> colCheck = new HashSet<>();
            Set<Integer> boxCheck = new HashSet<>();

            for (int j = 0; j < 9; j++) {
                // Check row
                if (board[i][j] != null) {
                    if (rowCheck.contains(board[i][j])) return false;
                    rowCheck.add(board[i][j]);
                }

                // Check column
                if (board[j][i] != null) {
                    if (colCheck.contains(board[j][i])) return false;
                    colCheck.add(board[j][i]);
                }

                // Check box
                int boxRow = (i / 3) * 3 + j / 3;
                int boxCol = (i % 3) * 3 + j % 3;
                if (board[boxRow][boxCol] != null) {
                    if (boxCheck.contains(board[boxRow][boxCol])) return false;
                    boxCheck.add(board[boxRow][boxCol]);
                }
            }
        }

        return true;
    }

    /**
     * Run the interactive application
     */
    public void run() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        // Welcome message
        System.out.println("===== SUDOKU CHEATER =====");
        System.out.println("Solve any Sudoku puzzle with ease!");
        System.out.println();

        while (running) {
            System.out.println("Menu:");
            System.out.println("1. Enter a new puzzle");
            System.out.println("2. Load example puzzle");
            System.out.println("3. Solve puzzle");
            System.out.println("4. Get a hint");
            System.out.println("5. Print current board");
            System.out.println("6. Clear board");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1: // Enter a new puzzle
                    enterPuzzle(scanner);
                    break;

                case 2: // Load example puzzle
                    loadExamplePuzzle();
                    System.out.println("Example puzzle loaded:");
                    printBoard();
                    break;

                case 3: // Solve puzzle
                    System.out.println("Solving puzzle...");
                    long startTime = System.currentTimeMillis();
                    boolean solved = solve();
                    long endTime = System.currentTimeMillis();

                    if (solved) {
                        System.out.println("Puzzle solved!");
                        printBoard();
                        System.out.println("Statistics:");
                        System.out.println("- Time taken: " + (endTime - startTime) + " ms");
                        System.out.println("- Attempts: " + attempts);
                        System.out.println("- Backtracks: " + backtracks);
                    } else {
                        System.out.println("No solution exists for this puzzle!");
                    }
                    break;

                case 4: // Get a hint
                    getHint(scanner);
                    break;

                case 5: // Print current board
                    System.out.println("Current board:");
                    printBoard();
                    break;

                case 6: // Clear board
                    clearBoard();
                    System.out.println("Board cleared.");
                    break;

                case 7: // Exit
                    running = false;
                    System.out.println("Thank you for using Sudoku Cheater!");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }

            System.out.println();
        }

        scanner.close();
    }

    /**
     * Enter a new puzzle manually
     */
    private void enterPuzzle(Scanner scanner) {
        clearBoard();
        System.out.println("Enter the Sudoku puzzle row by row.");
        System.out.println("Use numbers 1-9 for filled cells and '.' or '0' for empty cells.");

        for (int i = 0; i < 9; i++) {
            boolean validRow = false;
            while (!validRow) {
                System.out.print("Row " + (i + 1) + ": ");
                String row = scanner.nextLine().trim();

                // Check if input has correct length
                if (row.length() != 9) {
                    System.out.println("Row must contain exactly 9 characters. Try again.");
                    continue;
                }

                validRow = true;
                for (int j = 0; j < 9; j++) {
                    char c = row.charAt(j);
                    if (c >= '1' && c <= '9') {
                        int num = c - '0';
                        if (!isValid(i, j, num)) {
                            System.out.println("Invalid placement: " + num + " at position (" + (i + 1) + "," + (j + 1) + ")");
                            validRow = false;
                            break;
                        }
                        setOriginal(i, j, num);
                    } else if (c == '.' || c == '0') {
                        // Empty cell
                        board[i][j] = null;
                    } else {
                        System.out.println("Invalid character: " + c + ". Use 1-9 or '.' or '0'.");
                        validRow = false;
                        break;
                    }
                }

                if (!validRow) {
                    // Reset this row
                    for (int j = 0; j < 9; j++) {
                        setNumber(i, j, null);
                    }
                }
            }
        }

        System.out.println("Puzzle entered successfully:");
        printBoard();
    }

    /**
     * Get a hint for a specific cell
     */
    private void getHint(Scanner scanner) {
        System.out.println("Enter the row and column for the hint (1-9):");

        int row, col;
        try {
            System.out.print("Row: ");
            row = Integer.parseInt(scanner.nextLine().trim()) - 1;

            System.out.print("Column: ");
            col = Integer.parseInt(scanner.nextLine().trim()) - 1;

            if (row < 0 || row >= 9 || col < 0 || col >= 9) {
                System.out.println("Invalid row or column. Must be between 1 and 9.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
            return;
        }

        if (board[row][col] != null && !editable[row][col]) {
            System.out.println("This cell is part of the original puzzle and cannot be changed.");
            return;
        }

        System.out.println("Calculating hint...");
        Integer hint = getHint(row, col);

        if (hint != null) {
            System.out.println("Hint for cell (" + (row + 1) + "," + (col + 1) + "): " + hint);
        } else {
            System.out.println("No valid solution exists for this puzzle!");
        }
    }

    /**
     * Load an example Sudoku puzzle
     */
    private void loadExamplePuzzle() {
        clearBoard();

        // Example puzzle (same as in previous examples)
        int[][] examplePuzzle = {
                {5, 3, 0, 0, 7, 0, 0, 0, 0},
                {6, 0, 0, 1, 9, 5, 0, 0, 0},
                {0, 9, 8, 0, 0, 0, 0, 6, 0},
                {8, 0, 0, 0, 6, 0, 0, 0, 3},
                {4, 0, 0, 8, 0, 3, 0, 0, 1},
                {7, 0, 0, 0, 2, 0, 0, 0, 6},
                {0, 6, 0, 0, 0, 0, 2, 8, 0},
                {0, 0, 0, 4, 1, 9, 0, 0, 5},
                {0, 0, 0, 0, 8, 0, 0, 7, 9}
        };

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (examplePuzzle[i][j] != 0) {
                    setOriginal(i, j, examplePuzzle[i][j]);
                }
            }
        }
    }
}