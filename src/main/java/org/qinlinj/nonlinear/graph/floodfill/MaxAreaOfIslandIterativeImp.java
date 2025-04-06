package org.qinlinj.nonlinear.graph.floodfill;

import java.util.*;

// @formatter:off
/**
 * Iterative Max Area of Island - Stack-based Flood Fill Algorithm
 *
 * Concept and Principles:
 * - A flood fill algorithm is used to determine connected regions in a 2D grid.
 * - This implementation finds islands (connected land cells) in a grid where:
 *   - 1 represents land
 *   - 0 represents water
 * - Unlike the recursive DFS approach, this implementation uses an explicit stack
 *   to perform the depth-first search iteratively.
 * - We mark visited cells in-place by changing their values from 1 to 0.
 *
 * Advantages:
 * - No Stack Overflow: Avoids the risk of stack overflow for very large islands that
 *   might exceed the recursion depth limit
 * - Memory Efficient: Works directly on the grid without additional visited array
 * - Explicit Control: Provides direct control over the traversal process
 * - Similar Performance: Maintains the same time complexity as recursive approaches
 * - Better for Production: Generally preferred in production environments where
 *   recursive solutions might pose risks
 *
 * Visualization Example:
 * Consider a small 3x3 grid:
 *
 *   1 1 0
 *   1 0 0
 *   0 0 1
 *
 * Iterative exploration from position (0,0):
 * 1. Push (0,0) onto stack and mark as visited (0)
 * 2. Pop (0,0), increase area count to 1
 * 3. Check neighbors: (0,1) and (1,0) are land
 *    - Push (0,1) and (1,0) to stack and mark as visited
 * 4. Pop (1,0), increase area count to 2
 * 5. No unvisited land neighbors
 * 6. Pop (0,1), increase area count to 3
 * 7. No unvisited land neighbors
 * 8. Stack empty, area of first island: 3
 *
 * After first island exploration, the grid becomes:
 *   0 0 0
 *   0 0 0
 *   0 0 1
 *
 * Similar process for position (2,2) results in an island of area 1.
 *
 * The maximum island area is therefore 3.
 */
public class MaxAreaOfIslandIterative {
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
     * Main method to demonstrate the iterative Max Area of Island solution.
     *
     * Time Complexity: O(R*C) where R is the number of rows and C is the number of columns
     * Space Complexity: O(R*C) for the stack in worst case
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
        MaxAreaOfIslandIterative maxAreaOfIsland = new MaxAreaOfIslandIterative();
        System.out.println(maxAreaOfIsland.maxAreaOfIsland(grid));
    }

    /**
     * Find the maximum area of an island in the given grid using an iterative approach.
     *
     * Time Complexity: O(R*C) where R is the number of rows and C is the number of columns
     * Space Complexity: O(min(R,C)) for the stack in the worst case
     *
     * @param grid 2D grid where 1 represents land and 0 represents water
     * @return the maximum area of an island (maximum number of connected land cells)
     */
    public int maxAreaOfIsland(int[][] grid) {
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
                // If the cell is land, explore the island iteratively
                if (grid[row][col] == 1) {
                    // Counter for current island size
                    int currOnes = 0;

                    // Stack for DFS traversal (stores coordinates as [row, col] arrays)
                    ArrayDeque<int[]> stack = new ArrayDeque<>();

                    // Push the starting cell to the stack and mark as visited
                    stack.push(new int[]{row, col});
                    grid[row][col] = 0;

                    // Process cells until stack is empty
                    while (!stack.isEmpty()) {
                        // Pop a cell from the stack
                        int[] curr = stack.pop();
                        int currRow = curr[0], currCol = curr[1];

                        // Increment island size
                        currOnes++;

                        // Check all four directions
                        for (int[] dir : directions) {
                            int nextRow = currRow + dir[0];
                            int nextCol = currCol + dir[1];

                            // If the neighboring cell is within bounds and is land
                            if (inArea(nextRow, nextCol) && grid[nextRow][nextCol] == 1) {
                                // Push to stack and mark as visited
                                stack.push(new int[]{nextRow, nextCol});
                                grid[nextRow][nextCol] = 0; // Mark as visited immediately to avoid duplicate visits
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