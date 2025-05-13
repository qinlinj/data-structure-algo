package org.qinlinj.algoframework._700_brute_force_search_algo._730_dfs_backtracking_problems._732_minesweeper_game;

/**
 * Minesweeper Game Algorithm
 * <p>
 * This class implements the classic Minesweeper game's cell expansion algorithm.
 * In Minesweeper, when a player clicks on a cell, the game reveals the number of mines
 * around that cell. If there are no mines nearby (count is 0), the game automatically
 * expands to reveal adjacent cells until it reaches cells that have mines nearby.
 * <p>
 * Key concepts:
 * 1. Board representation:
 * - -4: represents a mine
 * - -1: represents an unopened cell
 * - 0-8: represents an opened cell with the number of mines around it
 * <p>
 * 2. Expansion algorithm:
 * - If a clicked cell is out of bounds or already opened, do nothing
 * - If a clicked cell is a mine, do nothing (in a real game, this would trigger game over)
 * - Otherwise, count mines around the cell
 * - If mine count > 0, just reveal this cell with the count
 * - If mine count = 0, reveal this cell and recursively expand to adjacent cells
 * <p>
 * This is a classic application of Depth-First Search (DFS) in a grid.
 */

public class _732_a_MinesweeperExpansion {

    /**
     * Demo of the minesweeper expansion algorithm
     */
    public static void main(String[] args) {
        _732_a_MinesweeperExpansion minesweeper = new _732_a_MinesweeperExpansion();

        // Create a sample board
        // -4: mine, -1: unopened cell
        int[][] board = {
                {-1, -1, -1, -1, -1},
                {-1, -4, -1, -1, -1},
                {-1, -1, -1, -1, -1},
                {-1, -1, -1, -4, -1},
                {-1, -1, -1, -1, -1}
        };

        System.out.println("Initial Board:");
        minesweeper.printBoard(board);

        // Click at cell (0, 0)
        System.out.println("\nAfter clicking at (0, 0):");
        minesweeper.expandClick(board, 0, 0);
        minesweeper.printBoard(board);

        // Create another board for a more complex example
        int[][] board2 = {
                {-1, -1, -1, -1, -1, -1, -1},
                {-1, -4, -1, -1, -1, -4, -1},
                {-1, -1, -1, -1, -1, -1, -1},
                {-1, -1, -1, -1, -1, -1, -1},
                {-1, -4, -1, -1, -1, -4, -1},
                {-1, -1, -1, -1, -1, -1, -1}
        };

        System.out.println("\nAnother example - Initial Board:");
        minesweeper.printBoard(board2);

        // Click at center cell (2, 3)
        System.out.println("\nAfter clicking at (2, 3):");
        minesweeper.expandClick(board2, 2, 3);
        minesweeper.printBoard(board2);
    }

    /**
     * Expands the board when a cell is clicked
     *
     * @param board The game board
     * @param i     Row index of the clicked cell
     * @param j     Column index of the clicked cell
     */
    public void expandClick(int[][] board, int i, int j) {
        int height = board.length;
        int width = board[0].length;

        // Base cases: out of bounds, already opened, or clicked on a mine
        if (i < 0 || i >= height || j < 0 || j >= width ||
                board[i][j] != -1 || board[i][j] == -4) {
            return;
        }

        // Count mines in adjacent cells
        int mineCount = 0;
        for (int di = -1; di <= 1; di++) {
            for (int dj = -1; dj <= 1; dj++) {
                // Skip the current cell
                if (di == 0 && dj == 0) continue;

                int ni = i + di;
                int nj = j + dj;

                // Check if adjacent cell is within bounds and contains a mine
                if (ni >= 0 && ni < height && nj >= 0 && nj < width &&
                        board[ni][nj] == -4) {
                    mineCount++;
                }
            }
        }

        // Update current cell with mine count
        board[i][j] = mineCount;

        // If there are mines nearby, stop expansion
        if (mineCount > 0) {
            return;
        }

        // If no mines nearby, recursively expand to adjacent cells
        for (int di = -1; di <= 1; di++) {
            for (int dj = -1; dj <= 1; dj++) {
                // Skip the current cell
                if (di == 0 && dj == 0) continue;

                // Recursively expand adjacent cells
                expandClick(board, i + di, j + dj);
            }
        }
    }

    /**
     * Helper method to print the board for visualization
     */
    public void printBoard(int[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == -4) {
                    System.out.print("M "); // Mine
                } else if (board[i][j] == -1) {
                    System.out.print("? "); // Unopened
                } else {
                    System.out.print(board[i][j] + " "); // Opened with mine count
                }
            }
            System.out.println();
        }
    }
}