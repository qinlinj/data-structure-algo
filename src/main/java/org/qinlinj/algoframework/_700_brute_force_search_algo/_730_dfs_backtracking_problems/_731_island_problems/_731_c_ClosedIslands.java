package org.qinlinj.algoframework._700_brute_force_search_algo._730_dfs_backtracking_problems._731_island_problems;

/**
 * Number of Closed Islands (LeetCode 1254)
 * <p>
 * Problem:
 * Given a 2D grid where 0 represents land and 1 represents water, count the number
 * of closed islands. A closed island is an island that is completely surrounded by water
 * (all land cells that are not connected to the boundary of the grid).
 * <p>
 * Key differences from the standard island problem:
 * 1. 0 represents land, 1 represents water (opposite of the usual convention)
 * 2. Only islands that don't touch the boundary count as "closed islands"
 * <p>
 * Algorithm:
 * 1. First, use DFS to "flood" all land cells connected to the boundary
 * (since these are not closed islands)
 * 2. Then, count all remaining islands as closed islands
 * <p>
 * Time Complexity: O(m*n) where m is the number of rows and n is the number of columns
 * Space Complexity: O(m*n) in the worst case for the recursion stack
 */

public class _731_c_ClosedIslands {

    /**
     * Example usage
     */
    public static void main(String[] args) {
        _731_c_ClosedIslands solution = new _731_c_ClosedIslands();

        // Example: Grid with 2 closed islands
        int[][] grid = {
                {1, 1, 1, 1, 1, 1, 1},
                {1, 0, 0, 0, 0, 0, 1},
                {1, 0, 1, 1, 1, 0, 1},
                {1, 0, 1, 0, 1, 0, 1},
                {1, 0, 1, 1, 1, 0, 1},
                {1, 0, 0, 0, 0, 0, 1},
                {1, 1, 1, 1, 1, 1, 1}
        };

        System.out.println("Number of closed islands: " + solution.closedIsland(grid));

        // Another example with an island touching the boundary (not closed)
        int[][] grid2 = {
                {0, 0, 1, 1, 0},
                {0, 1, 0, 1, 0},
                {0, 1, 1, 1, 0}
        };

        System.out.println("Number of closed islands in grid2: " + solution.closedIsland(grid2));
    }

    /**
     * Main function to calculate the number of closed islands
     */
    public int closedIsland(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int m = grid.length;
        int n = grid[0].length;

        // Step 1: Flood all land cells that are connected to the boundary

        // Flood lands connected to top and bottom boundaries
        for (int j = 0; j < n; j++) {
            // Top row
            dfs(grid, 0, j);
            // Bottom row
            dfs(grid, m - 1, j);
        }

        // Flood lands connected to left and right boundaries
        for (int i = 0; i < m; i++) {
            // Left column
            dfs(grid, i, 0);
            // Right column
            dfs(grid, i, n - 1);
        }

        // Step 2: Count remaining islands (these are all closed islands)
        int closedIslandCount = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    // Found a closed island
                    closedIslandCount++;
                    // Flood this island so we don't count it again
                    dfs(grid, i, j);
                }
            }
        }

        return closedIslandCount;
    }

    /**
     * DFS function to "flood" an island by changing all connected '0's to '1's
     */
    private void dfs(int[][] grid, int i, int j) {
        int m = grid.length;
        int n = grid[0].length;

        // Check boundary conditions
        if (i < 0 || j < 0 || i >= m || j >= n) {
            return;
        }

        // If current cell is not land (0), return
        if (grid[i][j] == 1) {
            return;
        }

        // Mark current cell as visited by changing it to water (1)
        grid[i][j] = 1;

        // Recursively check all adjacent cells
        dfs(grid, i + 1, j); // Down
        dfs(grid, i - 1, j); // Up
        dfs(grid, i, j + 1); // Right
        dfs(grid, i, j - 1); // Left
    }
}
