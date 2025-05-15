package org.qinlinj.algoframework._700_brute_force_search_algo._750_bfs_algo._753_bfs_classic_practice_II; /**
 * Minimum Number of Moves to Scatter Stones (LeetCode 2850)
 * -------------------------------------------------------
 * <p>
 * Summary:
 * This problem involves a 3x3 grid with a total of exactly 9 stones distributed across the cells.
 * Multiple stones can be in the same cell. The goal is to determine the minimum number of moves
 * to reach a state where each cell has exactly one stone. A move consists of transferring
 * a stone from one cell to an adjacent cell (sharing an edge).
 * <p>
 * Key Concepts:
 * 1. Grid-based movement with adjacent cell constraints
 * 2. Resource allocation problem (distributing stones optimally)
 * 3. Backtracking to try all possible stone movements
 * 4. Pruning to optimize the search
 * <p>
 * Approach:
 * - Identify cells with excess stones (more than 1) and empty cells (0 stones)
 * - Use backtracking to try distributing stones from excess cells to empty cells
 * - Track the minimum number of moves required
 * - For each excess cell, try distributing its stones to each empty cell and calculate the moves
 * <p>
 * Note: While BFS might seem appropriate, it's difficult to model this as a standard BFS problem
 * due to the complex state space. Backtracking is more suitable for this specific problem.
 * <p>
 * Time Complexity: O(9!) in the worst case, but much better in practice due to pruning
 * Space Complexity: O(9) for the recursion stack and tracking arrays
 */

import java.util.*;

public class _753_h_ScatterStones {

    // Track the minimum number of moves
    private int minMoves = Integer.MAX_VALUE;

    // Track the current number of moves
    private int currentMoves = 0;

    // Track the number of empty cells remaining
    private int emptyCount = 0;

    /**
     * Example usage
     */
    public static void main(String[] args) {
        _753_h_ScatterStones solution = new _753_h_ScatterStones();

        // Example 1: grid = [[1,1,0],[1,1,1],[1,2,1]]
        int[][] grid1 = {
                {1, 1, 0},
                {1, 1, 1},
                {1, 2, 1}
        };
        System.out.println("Example 1: " + solution.minimumMoves(grid1));  // Expected: 3

        // Example 2: grid = [[1,3,0],[1,0,0],[1,0,3]]
        int[][] grid2 = {
                {1, 3, 0},
                {1, 0, 0},
                {1, 0, 3}
        };
        System.out.println("Example 2: " + solution.minimumMovesEfficient(grid2));  // Expected: 4

        // Example 3: grid = [[1,0,1],[1,2,0],[1,0,3]]
        int[][] grid3 = {
                {1, 0, 1},
                {1, 2, 0},
                {1, 0, 3}
        };
        System.out.println("Example 3: " + solution.minimumMovesEfficient(grid3));  // Expected: 3
    }

    /**
     * Find the minimum number of moves to have exactly one stone in each cell
     * @param grid 3x3 grid representing the number of stones in each cell
     * @return Minimum number of moves
     */
    public int minimumMoves(int[][] grid) {
        // Identify cells with excess stones and empty cells
        List<int[]> excessCells = new ArrayList<>();  // Cells with more than 1 stone
        List<int[]> emptyCells = new ArrayList<>();   // Cells with 0 stones

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] > 1) {
                    excessCells.add(new int[]{i, j});
                } else if (grid[i][j] == 0) {
                    emptyCells.add(new int[]{i, j});
                    emptyCount++;
                }
            }
        }

        // Use backtracking to find the minimum moves
        backtrack(grid, excessCells, emptyCells);

        return minMoves;
    }

    /**
     * Backtracking function to try all possible stone distributions
     * @param grid Current state of the grid
     * @param excessCells Cells with more than 1 stone
     * @param emptyCells Cells with 0 stones
     */
    private void backtrack(int[][] grid, List<int[]> excessCells, List<int[]> emptyCells) {
        // Base case: all cells have been filled
        if (emptyCount == 0) {
            minMoves = Math.min(minMoves, currentMoves);
            return;
        }

        // Try distributing stones from each excess cell
        for (int[] excessCell : excessCells) {
            int srcRow = excessCell[0];
            int srcCol = excessCell[1];

            if (grid[srcRow][srcCol] <= 1) {
                // This cell no longer has excess stones
                continue;
            }

            // Try moving stones to each empty cell
            for (int[] emptyCell : emptyCells) {
                int destRow = emptyCell[0];
                int destCol = emptyCell[1];

                if (grid[destRow][destCol] != 0) {
                    // This cell is no longer empty
                    continue;
                }

                // Calculate the Manhattan distance (number of moves)
                int distance = Math.abs(srcRow - destRow) + Math.abs(srcCol - destCol);

                // Make the move
                grid[srcRow][srcCol]--;
                grid[destRow][destCol]++;
                currentMoves += distance;
                emptyCount--;

                // Recursively try more moves
                backtrack(grid, excessCells, emptyCells);

                // Undo the move (backtrack)
                grid[srcRow][srcCol]++;
                grid[destRow][destCol]--;
                currentMoves -= distance;
                emptyCount++;
            }
        }
    }

    /**
     * Alternative solution using a more efficient backtracking approach
     * This avoids repeatedly checking all excess and empty cells
     */
    public int minimumMovesEfficient(int[][] grid) {
        return backtrackEfficient(grid);
    }

    private int backtrackEfficient(int[][] grid) {
        // Check if all cells have exactly one stone
        boolean allOnes = true;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (grid[i][j] != 1) {
                    allOnes = false;
                    break;
                }
            }
            if (!allOnes) break;
        }

        // Base case: all cells have exactly one stone
        if (allOnes) {
            return 0;
        }

        int minMoves = Integer.MAX_VALUE;

        // Find a cell with no stones
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (grid[i][j] == 0) {
                    // Try moving a stone from each cell with excess stones
                    for (int x = 0; x < 3; x++) {
                        for (int y = 0; y < 3; y++) {
                            if (grid[x][y] > 1) {
                                // Move a stone from (x,y) to (i,j)
                                grid[x][y]--;
                                grid[i][j]++;

                                // Calculate moves for this step + remaining moves
                                int moves = Math.abs(i - x) + Math.abs(j - y);
                                int remainingMoves = backtrackEfficient(grid);

                                if (remainingMoves != Integer.MAX_VALUE) {
                                    minMoves = Math.min(minMoves, moves + remainingMoves);
                                }

                                // Undo the move
                                grid[x][y]++;
                                grid[i][j]--;
                            }
                        }
                    }

                    // We only need to find and fill one empty cell at a time
                    return minMoves;
                }
            }
        }

        return minMoves;
    }
}