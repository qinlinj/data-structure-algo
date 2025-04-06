package org.qinlinj.nonlinear.graph.floodfill;

import java.util.*;

// @formatter:off
/**
 * BFS Max Area of Island - Queue-based Flood Fill Algorithm
 *
 * Concept and Principles:
 * - A flood fill algorithm is used to determine connected regions in a 2D grid.
 * - This implementation finds islands (connected land cells) in a grid where:
 *   - 1 represents land
 *   - 0 represents water
 * - Unlike the previous DFS implementations, this solution uses Breadth-First Search (BFS)
 *   with a queue to explore the island level by level.
 * - We mark visited cells in-place by changing their values from 1 to 0.
 *
 * Advantages:
 * - Level-Order Traversal: Explores cells in order of their distance from the starting point
 * - Shortest Path Property: If path finding were needed, BFS would find the shortest path
 * - Balanced Exploration: More evenly distributed memory usage throughout execution
 * - No Recursion: Avoids stack overflow issues for large islands
 * - Better for Visualization: Provides a natural animation effect if visualized
 *
 * Visualization Example:
 * Consider a small 3x3 grid:
 *
 *   1 1 0
 *   1 0 0
 *   0 0 1
 *
 * BFS exploration from position (0,0):
 * 1. Enqueue (0,0) and mark as visited (0)
 * 2. Dequeue (0,0), increase area count to 1
 * 3. Check neighbors: (0,1) and (1,0) are land
 *    - Enqueue (0,1) and (1,0) and mark as visited
 * 4. Dequeue (0,1), increase area count to 2
 * 5. No unvisited land neighbors
 * 6. Dequeue (1,0), increase area count to 3
 * 7. No unvisited land neighbors
 * 8. Queue empty, area of first island: 3
 *
 * The key difference from DFS is the order of exploration - BFS explores all neighbors
 * at the current level before moving to the next level.
 *
 * The maximum island area is 3.
 */
public class MaxAreaOfIslandBFSImp {
    // Number of rows in the grid
    private int rows;

    // Number of columns in the grid
    private int cols;

    // The original grid (will be modified during processing)
    private int[][] grid;

    // Graph representation (unused in this implementation but kept for consistency)
    private Set<Integer>[] graph;

    // Four possible directions: up, down, left, right
    private int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    /**
     * Main method to demonstrate the BFS-based Max Area of Island solution.
     *
     * Time Complexity: O(R*C) where R is the number of rows and C is the number of columns
     * Space Complexity: O(min(R,C)) for the queue in worst case
     *
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        // Example grid with multiple islands
        int[][] grid = {
                {0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0},
                {0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0},
                {0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0},
        };
        MaxAreaOfIslandBFSImp maxAreaOfIsland = new MaxAreaOfIslandBFSImp();
        System.out.println(maxAreaOfIsland.maxAreaOfIsland(grid));
    }

    /**
     * Find the maximum area of an island in the given grid using a BFS approach.
     *
     * Time Complexity: O(R*C) where R is the number of rows and C is the number of columns
     * Space Complexity: O(min(R,C)) for the queue in the worst case
     *
     * @param grid 2D grid where 1 represents land and 0 represents water
     * @return the maximum area of an island (maximum number of connected land cells)
     */
    private int maxAreaOfIsland(int[][] grid) {
        // Handle edge cases
        if (grid == null) return 0;

        rows = grid.length;
        if (rows == 0) return 0;

        cols = grid[0].length;
        if (cols == 0) return 0;

        this.grid = grid;
        int res = 0;

        // Iterate through all cells in the grid
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                // If the cell is land, explore the island using BFS
                if (grid[row][col] == 1) {
                    // Counter for current island size
                    int currOnes = 0;

                    // Queue for BFS traversal (stores coordinates as [row, col] arrays)
                    Queue<int[]> queue = new LinkedList<>();

                    // Add the starting cell to the queue and mark as visited
                    queue.add(new int[]{row, col});
                    grid[row][col] = 0;  // Mark as visited immediately

                    // Process cells level by level until queue is empty
                    while (!queue.isEmpty()) {
                        // Remove a cell from the queue (FIFO - First In, First Out)
                        int[] curr = queue.remove();
                        int currRow = curr[0], currCol = curr[1];

                        // Increment island size
                        currOnes++;

                        // Check all four directions
                        for (int[] dir : directions) {
                            int nextRow = currRow + dir[0];
                            int nextCol = currCol + dir[1];

                            // If the neighboring cell is within bounds and is land
                            if (inArea(nextRow, nextCol) && grid[nextRow][nextCol] == 1) {
                                // Add to queue and mark as visited
                                queue.add(new int[]{nextRow, nextCol});
                                grid[nextRow][nextCol] = 0;  // Mark as visited immediately
                            }
                        }
                    }

                    // Update maximum island area
                    res = Math.max(res, currOnes);
                }
            }
        }
        return res;
    }

    /**
     * Check if a given row and column are within the grid boundaries.
     *
     * Time Complexity: O(1) - constant time checks
     * Space Complexity: O(1) - no additional space used
     *
     * @param row The row index
     * @param col The column index
     * @return true if the cell is within the grid, false otherwise
     */
    private boolean inArea(int row, int col) {
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }
}