package org.qinlinj.nonlinear.graph.floodfill;

import java.util.*;

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
    private int rows;
    private int cols;

    private int[][] grid;

    private Set<Integer>[] graph;
    private int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};


    public static void main(String[] args) {
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

    private int maxAreaOfIsland(int[][] grid) {
        if (grid == null) return 0;

        rows = grid.length;
        if (rows == 0) return 0;

        cols = grid[0].length;
        if (cols == 0) return 0;

        this.grid = grid;

        int res = 0;

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (grid[row][col] == 1) {
                    res = Math.max(dfs(row, col), res);
                }
            }
        }
        return res;
    }

    private int dfs(int row, int col) {
        grid[row][col] = 0;
        int res = 1;
        for (int[] dir : directions) {
            int nextRow = row + dir[0];
            int nextCol = col + dir[1];
            if (inArea(nextRow, nextCol)
                    && grid[nextRow][nextCol] == 1) {
                res += dfs(nextRow, nextCol);
            }
        }
        return res;
    }

    private boolean inArea(int row, int col) {
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }
}
