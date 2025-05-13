package org.qinlinj.algoframework._700_brute_force_search_algo._730_dfs_backtracking_problems._732_minesweeper_game; /**
 * Minesweeper Expansion Algorithm Implementation with Detailed Analysis
 * <p>
 * This class provides a more detailed implementation and analysis of the Minesweeper
 * expansion algorithm, including alternative approaches and optimizations.
 * <p>
 * Key aspects explored:
 * 1. DFS vs BFS implementation for cell expansion
 * 2. Optimization techniques to improve performance
 * 3. Alternative approaches to handle expansion
 * 4. Analysis of time and space complexity
 * <p>
 * Board representation:
 * - -4: Mine
 * - -1: Unopened cell
 * - 0-8: Opened cell with count of surrounding mines
 */

import java.util.*;

public class _732_b_MinesweeperAnalysis {

    // Direction arrays for 8 adjacent cells
    private static final int[] dr = {-1, -1, -1, 0, 0, 1, 1, 1};
    private static final int[] dc = {-1, 0, 1, -1, 1, -1, 0, 1};

    /**
     * Comparison of different implementations
     */
    public static void main(String[] args) {
        _732_b_MinesweeperAnalysis minesweeper = new _732_b_MinesweeperAnalysis();

        // Create a challenging board with mines scattered around
        int[][] originalBoard = {
                {-1, -1, -1, -1, -1, -1, -1, -1},
                {-1, -4, -1, -1, -1, -4, -1, -1},
                {-1, -1, -1, -1, -1, -1, -1, -1},
                {-1, -1, -1, -1, -1, -1, -4, -1},
                {-1, -4, -1, -1, -1, -1, -1, -1},
                {-1, -1, -1, -4, -1, -1, -1, -1},
                {-1, -1, -1, -1, -1, -4, -1, -1},
                {-1, -1, -1, -1, -1, -1, -1, -1}
        };

        System.out.println("Initial Board:");
        minesweeper.printBoard(originalBoard);

        // Test DFS implementation
        int[][] boardDFS = minesweeper.copyBoard(originalBoard);
        System.out.println("\nAfter DFS expansion at (3, 3):");
        minesweeper.expandClickDFS(boardDFS, 3, 3);
        minesweeper.printBoard(boardDFS);

        // Test BFS implementation
        int[][] boardBFS = minesweeper.copyBoard(originalBoard);
        System.out.println("\nAfter BFS expansion at (3, 3):");
        minesweeper.expandClickBFS(boardBFS, 3, 3);
        minesweeper.printBoard(boardBFS);

        // Test optimized implementation
        int[][] boardOptimized = minesweeper.copyBoard(originalBoard);
        System.out.println("\nAfter optimized expansion at (3, 3):");
        minesweeper.expandClickOptimized(boardOptimized, 3, 3);
        minesweeper.printBoard(boardOptimized);

        System.out.println("\nAlgorithm Analysis:");
        System.out.println("1. DFS Implementation:");
        System.out.println("   - Pros: Simple and intuitive implementation");
        System.out.println("   - Cons: Can cause stack overflow for large boards");
        System.out.println("   - Time Complexity: O(m*n) where m×n is the board size");
        System.out.println("   - Space Complexity: O(m*n) for the recursion stack");

        System.out.println("\n2. BFS Implementation:");
        System.out.println("   - Pros: Avoids stack overflow issues");
        System.out.println("   - Cons: Slightly more complex implementation");
        System.out.println("   - Time Complexity: O(m*n)");
        System.out.println("   - Space Complexity: O(m*n) for the queue");

        System.out.println("\n3. Optimized Implementation:");
        System.out.println("   - Pros: Reduces redundant boundary checks");
        System.out.println("   - Cons: More complex code");
        System.out.println("   - Time Complexity: O(m*n)");
        System.out.println("   - Space Complexity: O(m*n) for recursion + O(1) for adjacent cells");
    }

    /**
     * DFS implementation of cell expansion (same as in _732_a_MinesweeperExpansion)
     * This is the standard recursive approach
     */
    public void expandClickDFS(int[][] board, int i, int j) {
        int height = board.length;
        int width = board[0].length;

        // Base cases
        if (i < 0 || i >= height || j < 0 || j >= width ||
                board[i][j] != -1 || board[i][j] == -4) {
            return;
        }

        // Count mines in adjacent cells
        int mineCount = 0;
        for (int k = 0; k < 8; k++) {
            int ni = i + dr[k];
            int nj = j + dc[k];

            if (isValidCell(ni, nj, height, width) && board[ni][nj] == -4) {
                mineCount++;
            }
        }

        // Update current cell with mine count
        board[i][j] = mineCount;

        // If there are no mines nearby, expand to adjacent cells
        if (mineCount == 0) {
            for (int k = 0; k < 8; k++) {
                expandClickDFS(board, i + dr[k], j + dc[k]);
            }
        }
    }

    /**
     * BFS implementation of cell expansion
     * This avoids potential stack overflow for large boards
     */
    public void expandClickBFS(int[][] board, int i, int j) {
        int height = board.length;
        int width = board[0].length;

        // Check if starting position is valid
        if (!isValidCell(i, j, height, width) ||
                board[i][j] != -1 || board[i][j] == -4) {
            return;
        }

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{i, j});

        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            int r = cell[0];
            int c = cell[1];

            // Skip if cell is already processed or is a mine
            if (board[r][c] != -1 || board[r][c] == -4) {
                continue;
            }

            // Count mines around current cell
            int mineCount = 0;
            for (int k = 0; k < 8; k++) {
                int nr = r + dr[k];
                int nc = c + dc[k];

                if (isValidCell(nr, nc, height, width) && board[nr][nc] == -4) {
                    mineCount++;
                }
            }

            // Update current cell with mine count
            board[r][c] = mineCount;

            // If no mines nearby, add adjacent cells to queue
            if (mineCount == 0) {
                for (int k = 0; k < 8; k++) {
                    int nr = r + dr[k];
                    int nc = c + dc[k];

                    if (isValidCell(nr, nc, height, width) && board[nr][nc] == -1) {
                        queue.offer(new int[]{nr, nc});
                    }
                }
            }
        }
    }

    /**
     * Optimized DFS implementation with pre-computed adjacent cells
     * This reduces repeated boundary checks and calculations
     */
    public void expandClickOptimized(int[][] board, int i, int j) {
        int height = board.length;
        int width = board[0].length;

        // Base case checks
        if (!isValidCell(i, j, height, width) ||
                board[i][j] != -1 || board[i][j] == -4) {
            return;
        }

        // Pre-compute all valid adjacent cells
        int[][] adjacentCells = new int[8][2];
        int validCount = 0;
        int mineCount = 0;

        for (int k = 0; k < 8; k++) {
            int ni = i + dr[k];
            int nj = j + dc[k];

            if (isValidCell(ni, nj, height, width)) {
                adjacentCells[validCount][0] = ni;
                adjacentCells[validCount][1] = nj;
                validCount++;

                if (board[ni][nj] == -4) {
                    mineCount++;
                }
            }
        }

        // Update current cell
        board[i][j] = mineCount;

        // If no mines nearby, expand to valid adjacent cells
        if (mineCount == 0) {
            for (int k = 0; k < validCount; k++) {
                expandClickOptimized(board, adjacentCells[k][0], adjacentCells[k][1]);
            }
        }
    }

    /**
     * Helper method to check if a cell is within bounds
     */
    private boolean isValidCell(int i, int j, int height, int width) {
        return i >= 0 && i < height && j >= 0 && j < width;
    }

    /**
     * Prints the board with descriptive symbols
     */
    public void printBoard(int[][] board) {
        System.out.println("┌─" + "──┬─".repeat(board[0].length - 1) + "──┐");

        for (int i = 0; i < board.length; i++) {
            System.out.print("│ ");
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == -4) {
                    System.out.print("💣"); // Mine
                } else if (board[i][j] == -1) {
                    System.out.print("□ "); // Unopened
                } else if (board[i][j] == 0) {
                    System.out.print("  "); // Empty (no mines nearby)
                } else {
                    System.out.print(board[i][j] + " "); // Number of mines nearby
                }
                System.out.print("│ ");
            }
            System.out.println();

            if (i < board.length - 1) {
                System.out.println("├─" + "──┼─".repeat(board[0].length - 1) + "──┤");
            }
        }

        System.out.println("└─" + "──┴─".repeat(board[0].length - 1) + "──┘");
    }

    /**
     * Helper method to create a deep copy of a board
     */
    private int[][] copyBoard(int[][] original) {
        int[][] copy = new int[original.length][original[0].length];
        for (int i = 0; i < original.length; i++) {
            System.arraycopy(original[i], 0, copy[i], 0, original[i].length);
        }
        return copy;
    }
}