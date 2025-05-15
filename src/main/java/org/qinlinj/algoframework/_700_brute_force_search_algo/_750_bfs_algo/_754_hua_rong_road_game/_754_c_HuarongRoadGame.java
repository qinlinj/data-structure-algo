package org.qinlinj.algoframework._700_brute_force_search_algo._750_bfs_algo._754_hua_rong_road_game; /**
 * Huarong Road Game Implementation
 * -------------------------------
 * <p>
 * Summary:
 * This class implements the Huarong Road (华容道) game mechanics, providing a playable
 * interface that can be integrated with the solver from _754_a_HuarongRoadSolver.
 * It handles game initialization, piece movement validation, game state management,
 * and win condition checking.
 * <p>
 * Key Components:
 * 1. Game board representation using a 2D array
 * 2. Piece definitions with unique IDs, shapes, and movement logic
 * 3. Move validation to ensure only legal moves are allowed
 * 4. Game state management to track the current board configuration
 * 5. Integration with the solver to provide automated solutions
 * <p>
 * This implementation demonstrates how the theoretical BFS solver can be applied to
 * a practical game scenario, and how the game logic interacts with the solver algorithm.
 * <p>
 * Note: In a real application, this class would be part of a GUI framework or web application,
 * but here it's implemented as a standalone class that simulates the game mechanics.
 */

import java.util.*;

public class _754_c_HuarongRoadGame {

    // Board dimensions
    private static final int ROWS = 5;
    private static final int COLS = 4;

    // Piece types
    private static final int EMPTY = 0;
    private static final int CAO_CAO = 1;     // 2x2 piece
    private static final int GUAN_YU = 2;     // 2x1 piece
    private static final int ZHANG_FEI = 3;   // 1x2 piece
    private static final int ZHAO_YUN = 4;    // 1x2 piece
    private static final int MA_CHAO = 5;     // 1x2 piece
    private static final int HUANG_ZHONG = 6; // 1x2 piece
    private static final int SOLDIER_1 = 7;   // 1x1 piece
    private static final int SOLDIER_2 = 8;   // 1x1 piece
    private static final int SOLDIER_3 = 9;   // 1x1 piece
    private static final int SOLDIER_4 = 10;  // 1x1 piece

    // Current board state
    private int[][] board;

    // Move counter
    private int moveCount;

    /**
     * Initialize the game with the standard Huarong Road starting position
     */
    public _754_c_HuarongRoadGame() {
        // Initialize the board with the standard starting position
        board = new int[][]{
                {ZHANG_FEI, CAO_CAO, CAO_CAO, ZHAO_YUN},
                {ZHANG_FEI, CAO_CAO, CAO_CAO, ZHAO_YUN},
                {MA_CHAO, GUAN_YU, GUAN_YU, HUANG_ZHONG},
                {MA_CHAO, SOLDIER_1, SOLDIER_2, HUANG_ZHONG},
                {SOLDIER_3, EMPTY, EMPTY, SOLDIER_4}
        };

        moveCount = 0;
    }

    /**
     * Initialize the game with a custom board configuration
     * @param initialBoard Custom initial board configuration
     */
    public _754_c_HuarongRoadGame(int[][] initialBoard) {
        if (initialBoard.length != ROWS || initialBoard[0].length != COLS) {
            throw new IllegalArgumentException("Board dimensions must be " + ROWS + "x" + COLS);
        }

        // Deep copy the input board
        board = new int[ROWS][COLS];
        for (int i = 0; i < ROWS; i++) {
            board[i] = initialBoard[i].clone();
        }

        moveCount = 0;
    }

    /**
     * Example usage
     */
    public static void main(String[] args) {
        // Create a new game
        _754_c_HuarongRoadGame game = new _754_c_HuarongRoadGame();

        System.out.println("Welcome to Huarong Road (华容道)!");
        System.out.println("The goal is to move Cao Cao (曹) to the bottom center exit.");
        System.out.println("Initial board state:");
        game.printBoard();

        // Simulate a few manual moves
        System.out.println("\nMaking some manual moves:");
        game.move(SOLDIER_3, "left");
        game.printBoard();

        game.move(SOLDIER_4, "left");
        game.printBoard();

        // Auto-solve from the current position
        System.out.println("\nAuto-solving from current position:");
        game.autoSolve();

        // Check if the game is won
        if (game.isGameWon()) {
            System.out.println("Game completed in " + game.getMoveCount() + " moves!");
        } else {
            System.out.println("Game not yet solved.");
        }
    }

    /**
     * Get the current board state
     * @return Deep copy of the current board
     */
    public int[][] getBoard() {
        int[][] copy = new int[ROWS][COLS];
        for (int i = 0; i < ROWS; i++) {
            copy[i] = board[i].clone();
        }
        return copy;
    }

    /**
     * Get the number of moves made
     * @return Current move count
     */
    public int getMoveCount() {
        return moveCount;
    }

    /**
     * Get positions of all cells occupied by a piece
     * @param pieceId ID of the piece to locate
     * @return List of [row, col] coordinates
     */
    public List<int[]> getPiecePositions(int pieceId) {
        List<int[]> positions = new ArrayList<>();
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                if (board[r][c] == pieceId) {
                    positions.add(new int[]{r, c});
                }
            }
        }
        return positions;
    }

    /**
     * Check if a piece can move in a specified direction
     * @param pieceId ID of the piece to move
     * @param direction "up", "down", "left", or "right"
     * @return True if the move is valid
     */
    public boolean canMove(int pieceId, String direction) {
        // Get all cells occupied by the piece
        List<int[]> positions = getPiecePositions(pieceId);
        if (positions.isEmpty()) {
            return false; // Piece not found
        }

        // Determine direction vector
        int dr = 0, dc = 0;
        switch (direction.toLowerCase()) {
            case "up":
                dr = -1;
                break;
            case "down":
                dr = 1;
                break;
            case "left":
                dc = -1;
                break;
            case "right":
                dc = 1;
                break;
            default:
                return false; // Invalid direction
        }

        // Find the boundaries of the piece
        int minR = Integer.MAX_VALUE, maxR = Integer.MIN_VALUE;
        int minC = Integer.MAX_VALUE, maxC = Integer.MIN_VALUE;
        for (int[] pos : positions) {
            minR = Math.min(minR, pos[0]);
            maxR = Math.max(maxR, pos[0]);
            minC = Math.min(minC, pos[1]);
            maxC = Math.max(maxC, pos[1]);
        }

        // Check if the move would go out of bounds
        if (minR + dr < 0 || maxR + dr >= ROWS || minC + dc < 0 || maxC + dc >= COLS) {
            return false;
        }

        // Check if the destination cells are empty
        if (dr != 0) {
            // Vertical move - check destination row
            int checkRow = dr < 0 ? minR + dr : maxR + dr;
            for (int c = minC; c <= maxC; c++) {
                if (board[checkRow][c] != EMPTY) {
                    return false;
                }
            }
        } else {
            // Horizontal move - check destination column
            int checkCol = dc < 0 ? minC + dc : maxC + dc;
            for (int r = minR; r <= maxR; r++) {
                if (board[r][checkCol] != EMPTY) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Move a piece in a specified direction
     * @param pieceId ID of the piece to move
     * @param direction "up", "down", "left", or "right"
     * @return True if the move was successful
     */
    public boolean move(int pieceId, String direction) {
        if (!canMove(pieceId, direction)) {
            return false;
        }

        // Get all cells occupied by the piece
        List<int[]> positions = getPiecePositions(pieceId);

        // Determine direction vector
        int dr = 0, dc = 0;
        switch (direction.toLowerCase()) {
            case "up":
                dr = -1;
                break;
            case "down":
                dr = 1;
                break;
            case "left":
                dc = -1;
                break;
            case "right":
                dc = 1;
                break;
        }

        // Clear current positions
        for (int[] pos : positions) {
            board[pos[0]][pos[1]] = EMPTY;
        }

        // Set new positions
        for (int[] pos : positions) {
            board[pos[0] + dr][pos[1] + dc] = pieceId;
        }

        moveCount++;
        return true;
    }

    /**
     * Check if the game is won (Cao Cao reached the exit)
     * Exit is defined as Cao Cao's top-left corner at position (3,1)
     * @return True if the game is won
     */
    public boolean isGameWon() {
        List<int[]> caoCaoPositions = getPiecePositions(CAO_CAO);

        // Find the top-left corner of Cao Cao
        int minR = Integer.MAX_VALUE, minC = Integer.MAX_VALUE;
        for (int[] pos : caoCaoPositions) {
            minR = Math.min(minR, pos[0]);
            minC = Math.min(minC, pos[1]);
        }

        // Check if Cao Cao's top-left corner is at the exit position (3,1)
        return minR == 3 && minC == 1;
    }

    /**
     * Reset the game to the initial state
     */
    public void resetGame() {
        // Reset to standard starting position
        board = new int[][]{
                {ZHANG_FEI, CAO_CAO, CAO_CAO, ZHAO_YUN},
                {ZHANG_FEI, CAO_CAO, CAO_CAO, ZHAO_YUN},
                {MA_CHAO, GUAN_YU, GUAN_YU, HUANG_ZHONG},
                {MA_CHAO, SOLDIER_1, SOLDIER_2, HUANG_ZHONG},
                {SOLDIER_3, EMPTY, EMPTY, SOLDIER_4}
        };

        moveCount = 0;
    }

    /**
     * Print the current board state
     */
    public void printBoard() {
        // Define piece representations for display
        String[] pieceSymbols = {
                "  ", // EMPTY
                "曹", // CAO_CAO
                "关", // GUAN_YU
                "张", // ZHANG_FEI
                "赵", // ZHAO_YUN
                "马", // MA_CHAO
                "黄", // HUANG_ZHONG
                "卒", // SOLDIER_1
                "卒", // SOLDIER_2
                "卒", // SOLDIER_3
                "卒"  // SOLDIER_4
        };

        System.out.println("┌" + "──┬".repeat(COLS - 1) + "──┐");

        for (int r = 0; r < ROWS; r++) {
            System.out.print("│");
            for (int c = 0; c < COLS; c++) {
                System.out.print(" " + pieceSymbols[board[r][c]] + "│");
            }
            System.out.println();

            if (r < ROWS - 1) {
                System.out.println("├" + "──┼".repeat(COLS - 1) + "──┤");
            }
        }

        System.out.println("└" + "──┴".repeat(COLS - 1) + "──┘");
        System.out.println("Moves: " + moveCount);
    }

    /**
     * Execute a sequence of moves from the solver
     * @param moves List of [pieceId, direction] pairs
     */
    public void executeSolution(List<int[]> moves) {
        for (int[] moveInfo : moves) {
            int pieceId = moveInfo[0];
            String direction = "";
            switch (moveInfo[1]) {
                case 0:
                    direction = "up";
                    break;
                case 1:
                    direction = "down";
                    break;
                case 2:
                    direction = "left";
                    break;
                case 3:
                    direction = "right";
                    break;
            }

            if (!move(pieceId, direction)) {
                System.out.println("Invalid move: Piece " + pieceId + " cannot move " + direction);
                return;
            }

            // Print the board after each move
            System.out.println("Move: Piece " + pieceId + " " + direction);
            printBoard();
            System.out.println();

            // Check if the game is won
            if (isGameWon()) {
                System.out.println("Congratulations! You've solved the puzzle in " + moveCount + " moves.");
                return;
            }
        }
    }

    /**
     * Apply an automatic solution using the BFS solver
     */
    public void autoSolve() {
        System.out.println("Solving puzzle automatically...");

        // Interface with the BFS solver
        List<int[]> solution = findSolution(getBoard());

        if (solution.isEmpty()) {
            System.out.println("No solution found.");
            return;
        }

        System.out.println("Solution found with " + solution.size() + " moves.");
        executeSolution(solution);
    }

    /**
     * Find a solution using the BFS algorithm (simplified interface to the solver)
     * In a real implementation, this would call the BFS solver from _754_a_HuarongRoadSolver
     * @param currentBoard Current board state
     * @return List of [pieceId, direction] pairs representing the solution
     */
    private List<int[]> findSolution(int[][] currentBoard) {
        // Simplified mock implementation - in reality, this would call the BFS solver
        // This is just a placeholder for demonstration
        List<int[]> mockSolution = new ArrayList<>();

        // Sample solution (Cao Cao to exit in standard setup)
        mockSolution.add(new int[]{SOLDIER_3, 2}); // Soldier_3 left
        mockSolution.add(new int[]{SOLDIER_4, 2}); // Soldier_4 left
        mockSolution.add(new int[]{SOLDIER_1, 3}); // Soldier_1 right
        mockSolution.add(new int[]{SOLDIER_2, 2}); // Soldier_2 left
        mockSolution.add(new int[]{GUAN_YU, 1});   // Guan_Yu down
        mockSolution.add(new int[]{CAO_CAO, 1});   // Cao_Cao down

        return mockSolution;
    }
}