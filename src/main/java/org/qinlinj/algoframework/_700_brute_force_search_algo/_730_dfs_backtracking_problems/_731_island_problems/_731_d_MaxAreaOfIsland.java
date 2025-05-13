package org.qinlinj.algoframework._700_brute_force_search_algo._730_dfs_backtracking_problems._731_island_problems;

/**
 * Max Area of Island (LeetCode 695)
 * <p>
 * Problem:
 * Given a 2D grid where 1 represents land and 0 represents water, find the maximum area
 * of an island in the grid. An island is a group of 1's connected horizontally or vertically.
 * If there's no island, return 0.
 * <p>
 * Algorithm:
 * 1. Iterate through each cell in the grid
 * 2. When a land cell (1) is found, use DFS to calculate the area of the island
 * 3. Keep track of the maximum area found
 * 4. Return the maximum area
 * <p>
 * The key difference from the standard island counting problem:
 * - DFS function now returns the area of each island instead of just marking cells as visited
 * <p>
 * Time Complexity: O(m*n) where m is the number of rows and n is the number of columns
 * Space Complexity: O(m*n) in the worst case for the recursion stack
 */

public class _731_d_MaxAreaOfIsland {

    /**
     * Example usage
     */
    public static void main(String[] args) {
        _731_d_MaxAreaOfIsland solution = new _731_d_MaxAreaOfIsland();

        // Example: Grid with multiple islands of different sizes
        int[][] grid = {
                {0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0},
                {0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0}
        };

        System.out.println("Maximum area of an island: " + solution.maxAreaOfIsland(grid));

        // Example: Grid with no islands
        int[][] grid2 = {
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0}
        };

        System.out.println("Maximum area of an island in grid2: " + solution.maxAreaOfIsland(grid2));
    }

    /**
     * Main function to find the maximum area of an island
     */
    public int maxAreaOfIsland(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int maxArea = 0;
        int m = grid.length;
        int n = grid[0].length;

        // Traverse the grid
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    // Found a new island, calculate its area
                    int area = dfs(grid, i, j);
                    // Update maximum area
                    maxArea = Math.max(maxArea, area);
                }
            }
        }

        return maxArea;
    }

    /**
     * DFS function to calculate the area of an island
     * Returns the number of land cells in the island
     */
    private int dfs(int[][] grid, int i, int j) {
        int m = grid.length;
        int n = grid[0].length;

        // Check boundary conditions
        if (i < 0 || j < 0 || i >= m || j >= n) {
            return 0;
        }

        // If current cell is not land, it doesn't contribute to area
        if (grid[i][j] == 0) {
            return 0;
        }

        // Mark current cell as visited by changing it to water
        grid[i][j] = 0;

        // Current cell contributes 1 to area
        // Add the areas from all four directions
        return 1 +
                dfs(grid, i + 1, j) + // Down
                dfs(grid, i - 1, j) + // Up
                dfs(grid, i, j + 1) + // Right
                dfs(grid, i, j - 1);  // Left
    }
}
