package org.qinlinj.nonlinear.graph.floodfill;

// @formatter:off
/**
 * In-place Max Area of Island - Optimized Flood Fill Algorithm
 *
 * Concept and Principles:
 * - A flood fill algorithm is used to determine connected regions in a 2D grid.
 * - This implementation finds islands (connected land cells) in a grid where:
 *   - 1 represents land
 *   - 0 represents water
 * - Unlike the previous version, this implementation works directly on the grid
 *   without constructing an explicit graph representation.
 * - We use DFS to explore connected land cells and mark visited cells by changing
 *   their values in-place from 1 to 0 (effectively "sinking" the island).
 *
 * Advantages:
 * - Memory Efficient: No need for additional visited array or graph representation
 * - Simplified Implementation: Works directly on the 2D grid
 * - Reduced Overhead: Eliminates the graph construction step
 * - More Intuitive: DFS directly manipulates the grid as it "sinks" each island
 * - Faster Execution: Fewer data structures and transformations
 *
 * Visualization Example:
 * Consider a small 3x3 grid:
 *
 *   1 1 0
 *   1 0 0
 *   0 0 1
 *
 * DFS exploration from position (0,0):
 * 1. Mark (0,0) as visited by changing it to 0
 * 2. Explore neighbors: (0,1) and (1,0) are land
 * 3. DFS on (0,1):
 *    - Mark (0,1) as visited
 *    - No unvisited land neighbors
 * 4. DFS on (1,0):
 *    - Mark (1,0) as visited
 *    - No unvisited land neighbors
 * 5. Area of first island: 3
 *
 * After first DFS, the grid becomes:
 *   0 0 0
 *   0 0 0
 *   0 0 1
 *
 * DFS exploration from position (2,2):
 * 1. Mark (2,2) as visited by changing it to 0
 * 2. No unvisited land neighbors
 * 3. Area of second island: 1
 *
 * The maximum island area is therefore 3.
 */
public class InPlaceMaxAreaOfIsland {
    // Number of rows in the grid
    private int rows;

    // Number of columns in the grid
    private int cols;

    // The original grid (will be modified during processing)
    private int[][] grid;

    // Four possible directions: up, down, left, right
    private int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    /**
     * Main method to demonstrate the optimized Max Area of Island solution.
     *
     * Time Complexity: O(R*C) where R is the number of rows and C is the number of columns
     * Space Complexity: O(R*C) for the recursive call stack in worst case
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
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0},
        };
        InPlaceMaxAreaOfIsland maxAreaOfIsland = new InPlaceMaxAreaOfIsland();
        System.out.println(maxAreaOfIsland.maxAreaOfIsland(grid));
    }

    /**
     * Find the maximum area of an island in the given grid using an in-place approach.
     *
     * Time Complexity: O(R*C) where R is the number of rows and C is the number of columns
     * Space Complexity: O(1) extra space (not counting recursion stack)
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
                // If the cell is land, explore the island
                if (grid[row][col] == 1) {
                    res = Math.max(dfs(row, col), res);
                }
            }
        }
        return res;
    }

    /**
     * Perform DFS traversal from a cell to find the area of an island.
     * This method modifies the grid in-place by marking visited cells as 0.
     *
     * Time Complexity: O(area of island) - bounded by O(R*C)
     * Space Complexity: O(area of island) for the recursion stack in worst case
     *
     * @param row The current row being visited
     * @param col The current column being visited
     * @return The area of the island (number of connected land cells)
     */
    private int dfs(int row, int col) {
        // Mark the current cell as visited by changing it to water (0)
        // This eliminates the need for a separate visited array
        grid[row][col] = 0;

        // Start with area 1 (counting the current cell)
        int res = 1;

        // Explore all four directions
        for (int[] dir : directions) {
            int nextRow = row + dir[0];
            int nextCol = col + dir[1];

            // If the neighboring cell is within bounds and is land
            if (inArea(nextRow, nextCol) && grid[nextRow][nextCol] == 1) {
                // Add the area of the connected subisland
                res += dfs(nextRow, nextCol);
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