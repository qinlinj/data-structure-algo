package org.qinlinj.algoframework._700_brute_force_search_algo._750_bfs_algo._755_lian_lian_kan_connect_game; /**
 * Lianliankan Game Implementation
 * --------------------------------------------
 * <p>
 * Summary:
 * This class implements a complete Lianliankan game, building on the core connection
 * algorithm from _755_a_LianliankanConnectAlgorithm. It provides game board initialization,
 * game state management, user interactions, and visualization.
 * <p>
 * Key Components:
 * 1. Game board representation and initialization with various piece types
 * 2. User interaction handling for selecting pieces
 * 3. Game logic for matching and removing pieces
 * 4. Path visualization and animation
 * 5. Game state tracking (remaining pieces, score, etc.)
 * <p>
 * Game Rules:
 * - Players select two identical pieces
 * - If the pieces can be connected with at most two turns, they are removed
 * - The goal is to clear all pieces from the board
 * - No time limit is enforced in this implementation
 * <p>
 * Implementation Details:
 * - Uses the connect algorithm from _755_a_LianliankanConnectAlgorithm
 * - Provides command-line visualization for simplicity
 * - Could be extended with GUI components for a more interactive experience
 * <p>
 * Note: This implementation focuses on the game logic rather than graphical elements,
 * which would typically be implemented using frameworks like JavaFX or Swing in a real game.
 */

import java.util.*;

public class _755_b_LianliankanGame {

    // Game board state
    private int[][] board;
    private int rows;
    private int cols;
    private int remainingPairs;
    private int score;

    // Currently selected cells
    private int selectedRow1 = -1;
    private int selectedCol1 = -1;
    private int selectedRow2 = -1;
    private int selectedCol2 = -1;

    // Last valid connection path
    private List<int[]> lastPath = new ArrayList<>();

    /**
     * Initialize a new Lianliankan game with default dimensions
     */
    public _755_b_LianliankanGame() {
        this(8, 10); // Default 8x10 board
    }

    /**
     * Initialize a new Lianliankan game with specified dimensions
     * @param rows Number of rows
     * @param cols Number of columns
     */
    public _755_b_LianliankanGame(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        initializeBoard();
    }

    /**
     * Example usage with automated demo game
     */
    public static void main(String[] args) {
        // Create a smaller board for demonstration
        _755_b_LianliankanGame game = new _755_b_LianliankanGame(6, 6);

        // Uncomment to play interactive game
        // game.playInteractive();

        // Automated demo with predetermined board
        game = createDemoGame();

        game.printBoard();
        System.out.println("\nAutomated Demo:");

        // Make some predetermined valid matches
        System.out.println("\n1. Selecting (0,0) and (0,5)");
        game.selectCell(0, 0);
        boolean result = game.selectCell(0, 5);
        System.out.println("Match result: " + result);
        game.printBoard();
        game.printPath();

        System.out.println("\n2. Selecting (2,2) and (4,4)");
        game.selectCell(2, 2);
        result = game.selectCell(4, 4);
        System.out.println("Match result: " + result);
        game.printBoard();
        game.printPath();

        System.out.println("\n3. Selecting (1,1) and (3,3) - should fail (no valid path)");
        game.selectCell(1, 1);
        result = game.selectCell(3, 3);
        System.out.println("Match result: " + result);
        game.printBoard();

        System.out.println("\nGame state: " + (game.isGameOver() ? "Complete" : "In progress"));
        System.out.println("Score: " + game.getScore());
        System.out.println("Remaining pairs: " + game.getRemainingPairs());
    }

    /**
     * Create a demo game with a predetermined board layout
     * @return Demo game instance
     */
    private static _755_b_LianliankanGame createDemoGame() {
        _755_b_LianliankanGame game = new _755_b_LianliankanGame(6, 6);

        // Override with a predetermined board for consistent demo
        int[][] demoBoard = {
                {1, 2, 3, 4, 5, 1}, // Row 0: matching 1s at (0,0) and (0,5)
                {6, 2, 5, 7, 8, 9}, // Row 1
                {3, 4, 3, 6, 7, 8}, // Row 2: matching 3s at (2,0) and (2,2)
                {5, 7, 8, 2, 9, 5}, // Row 3
                {9, 8, 7, 6, 3, 4}, // Row 4: matching 3s at (2,2) and (4,4)
                {4, 5, 6, 7, 8, 9}  // Row 5
        };

        // Set the demo board
        game.board = demoBoard;
        game.remainingPairs = 9; // Arbitrary value for demo
        game.score = 0;

        return game;
    }

    /**
     * Initialize the game board with randomly distributed pairs of pieces
     */
    private void initializeBoard() {
        board = new int[rows][cols];

        // Calculate total cells and ensure it's even
        int totalCells = rows * cols;
        if (totalCells % 2 != 0) {
            totalCells--; // Ensure even number for pairs
        }

        // Determine how many different piece types to use
        int numPieceTypes = Math.min(10, totalCells / 2); // Max 10 types

        // Create a list with pairs of piece types
        List<Integer> pieces = new ArrayList<>();
        for (int type = 1; type <= numPieceTypes; type++) {
            int pairsOfThisType = totalCells / (2 * numPieceTypes);
            if (type <= (totalCells / 2) % numPieceTypes) {
                pairsOfThisType++; // Distribute remainder
            }

            for (int i = 0; i < pairsOfThisType * 2; i++) {
                pieces.add(type);
            }
        }

        // Shuffle the pieces
        Collections.shuffle(pieces);

        // Place pieces on the board
        int index = 0;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (index < pieces.size()) {
                    board[r][c] = pieces.get(index++);
                } else {
                    board[r][c] = 0; // Empty cell
                }
            }
        }

        remainingPairs = totalCells / 2;
        score = 0;
    }

    /**
     * Select a cell on the board
     * @param row Row index
     * @param col Column index
     * @return True if a match was made and pieces were removed
     */
    public boolean selectCell(int row, int col) {
        if (row < 0 || row >= rows || col < 0 || col >= cols || board[row][col] == 0) {
            // Invalid selection or empty cell
            clearSelection();
            return false;
        }

        if (selectedRow1 == -1) {
            // First selection
            selectedRow1 = row;
            selectedCol1 = col;
            return false;
        } else if (row == selectedRow1 && col == selectedCol1) {
            // Same cell selected again, deselect
            clearSelection();
            return false;
        } else {
            // Second selection
            selectedRow2 = row;
            selectedCol2 = col;

            // Try to connect the selections
            return tryConnect();
        }
    }

    /**
     * Try to connect the two selected pieces
     * @return True if connection was successful and pieces were removed
     */
    private boolean tryConnect() {
        // Verify both cells are selected
        if (selectedRow1 == -1 || selectedRow2 == -1) {
            return false;
        }

        // Check if pieces are the same type
        if (board[selectedRow1][selectedCol1] != board[selectedRow2][selectedCol2]) {
            clearSelection();
            return false;
        }

        // Try to find a valid connection path
        lastPath = _755_a_LianliankanConnectAlgorithm.connect(
                board, selectedRow1, selectedCol1, selectedRow2, selectedCol2);

        if (lastPath.isEmpty()) {
            // No valid path found
            clearSelection();
            return false;
        }

        // Valid connection found, remove the pieces
        int pieceType = board[selectedRow1][selectedCol1];
        board[selectedRow1][selectedCol1] = 0;
        board[selectedRow2][selectedCol2] = 0;

        // Update game state
        remainingPairs--;
        score += 10; // 10 points per match

        clearSelection();
        return true;
    }

    /**
     * Clear the current selection
     */
    private void clearSelection() {
        selectedRow1 = -1;
        selectedCol1 = -1;
        selectedRow2 = -1;
        selectedCol2 = -1;
        lastPath.clear();
    }

    /**
     * Check if the game is over (all pairs matched)
     * @return True if the game is over
     */
    public boolean isGameOver() {
        return remainingPairs <= 0;
    }

    /**
     * Get the current score
     * @return Current score
     */
    public int getScore() {
        return score;
    }

    /**
     * Get the number of remaining pairs
     * @return Number of remaining pairs
     */
    public int getRemainingPairs() {
        return remainingPairs;
    }

    /**
     * Print the current state of the board
     */
    public void printBoard() {
        System.out.println("\nLianliankan Game Board:");
        System.out.println("Score: " + score + " | Remaining Pairs: " + remainingPairs);

        // Print column indices
        System.out.print("  ");
        for (int c = 0; c < cols; c++) {
            System.out.print(" " + c + " ");
        }
        System.out.println();

        // Print top border
        System.out.print("  ");
        for (int c = 0; c < cols; c++) {
            System.out.print("---");
        }
        System.out.println();

        // Print board with row indices
        for (int r = 0; r < rows; r++) {
            System.out.print(r + " |");
            for (int c = 0; c < cols; c++) {
                char symbol;
                if (board[r][c] == 0) {
                    symbol = ' '; // Empty
                } else {
                    // Use different symbols for different piece types
                    symbol = (char) ('A' + (board[r][c] - 1) % 26);
                }

                // Highlight selected cells
                if ((r == selectedRow1 && c == selectedCol1) ||
                        (r == selectedRow2 && c == selectedCol2)) {
                    System.out.print("[" + symbol + "]");
                } else {
                    System.out.print(" " + symbol + " ");
                }
            }
            System.out.println("| " + r);
        }

        // Print bottom border
        System.out.print("  ");
        for (int c = 0; c < cols; c++) {
            System.out.print("---");
        }
        System.out.println();

        // Print column indices again
        System.out.print("  ");
        for (int c = 0; c < cols; c++) {
            System.out.print(" " + c + " ");
        }
        System.out.println();
    }

    /**
     * Print the last connection path if available
     */
    public void printPath() {
        if (lastPath.isEmpty()) {
            System.out.println("No valid path to display.");
            return;
        }

        System.out.println("Connection Path:");
        for (int i = 0; i < lastPath.size(); i++) {
            int[] point = lastPath.get(i);
            System.out.print("(" + point[0] + "," + point[1] + ")");
            if (i < lastPath.size() - 1) {
                System.out.print(" -> ");
            }
        }
        System.out.println();
    }

    /**
     * Play the game interactively in the console
     */
    public void playInteractive() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Lianliankan!");
        System.out.println("Select pairs of matching pieces to connect and remove them.");
        System.out.println("Enter row and column as 'r c' (e.g., '2 3')");
        System.out.println("Enter 'q' to quit.");

        printBoard();

        while (!isGameOver()) {
            System.out.print("\nSelect a piece (r c): ");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("q")) {
                break;
            }

            try {
                String[] parts = input.split("\\s+");
                int row = Integer.parseInt(parts[0]);
                int col = Integer.parseInt(parts[1]);

                boolean madeClear = selectCell(row, col);
                printBoard();

                if (madeClear) {
                    System.out.println("\nMatch found!");
                    printPath();

                    if (isGameOver()) {
                        System.out.println("\nCongratulations! You've cleared the board!");
                        System.out.println("Final Score: " + score);
                        break;
                    }
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter row and column as 'r c'.");
            }
        }

        scanner.close();
    }
}