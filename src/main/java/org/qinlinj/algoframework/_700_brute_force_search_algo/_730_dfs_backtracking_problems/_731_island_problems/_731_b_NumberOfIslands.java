package org.qinlinj.algoframework._700_brute_force_search_algo._730_dfs_backtracking_problems._731_island_problems;

/**
 * Number of Islands (LeetCode 200)
 * <p>
 * Problem:
 * Given a 2D grid where '1' represents land and '0' represents water, count the number
 * of islands. An island is surrounded by water and is formed by connecting adjacent
 * lands horizontally or vertically.
 * <p>
 * Algorithm:
 * 1. Iterate through each cell in the grid
 * 2. When a land cell ('1') is found, increment the island count
 * 3. Use DFS to mark all connected land cells as visited by changing them to '0'
 * (effectively "sinking" the entire island)
 * 4. Continue until all cells are processed
 * <p>
 * Time Complexity: O(m*n) where m is the number of rows and n is the number of columns
 * Space Complexity: O(m*n) in the worst case for the recursion stack
 * <p>
 * Note: This approach modifies the input grid. If that's not allowed, we would need
 * a separate visited array.
 */

public class _731_b_NumberOfIslands {

    /**
     * Example usage
     */
    public static void main(String[] args) {
        _731_b_NumberOfIslands solution = new _731_b_NumberOfIslands();

        // Example 1: Grid with 1 island
        char[][] grid1 = {
                {'1', '1', '1', '1', '0'},
                {'1', '1', '0', '1', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '0', '0', '0'}
        };
        System.out.println("Number of islands in grid1: " + solution.numIslands(grid1));

        // Example 2: Grid with 3 islands
        char[][] grid2 = {
                {'1', '1', '0', '0', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '1'}
        };
        System.out.println("Number of islands in grid2: " + solution.numIslands(grid2));
    }

    /**
     * Main function to calculate the number of islands
     */
    public int numIslands(char[][] grid) {
        // Handle edge case
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int count = 0;
        int m = grid.length;
        int n = grid[0].length;

        // Traverse the grid
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    // Found a new island
                    count++;
                    // Use DFS to sink the entire island
                    dfs(grid, i, j);
                }
            }
        }

        return count;
    }

    /**
     * DFS function to "sink" an island by changing all connected '1's to '0's
     */
    private void dfs(char[][] grid, int i, int j) {
        int m = grid.length;
        int n = grid[0].length;

        // Check boundary conditions
        if (i < 0 || j < 0 || i >= m || j >= n) {
            return;
        }

        // If current cell is not land, return
        if (grid[i][j] == '0') {
            return;
        }

        // Mark current cell as visited by changing it to water
        grid[i][j] = '0';

        // Recursively check all adjacent cells
        dfs(grid, i + 1, j); // Down
        dfs(grid, i - 1, j); // Up
        dfs(grid, i, j + 1); // Right
        dfs(grid, i, j - 1); // Left
    }
}