package org.qinlinj.algoframework._700_brute_force_search_algo._730_dfs_backtracking_problems._731_island_problems;

/**
 * Count Sub Islands (LeetCode 1905)
 * <p>
 * Problem:
 * Given two 2D binary grids grid1 and grid2, each of size m x n, containing only 0s (water)
 * and 1s (land). An island is a group of 1s connected 4-directionally.
 * <p>
 * A sub-island is an island in grid2 that is also completely contained within an island in grid1.
 * Return the number of sub-islands in grid2.
 * <p>
 * Key insight:
 * If any land cell in grid2 corresponds to a water cell in grid1, then that island in grid2
 * cannot be a sub-island. We can eliminate these islands first, and then count the remaining
 * islands in grid2 as sub-islands.
 * <p>
 * Algorithm:
 * 1. Traverse grid2 and find all land cells that correspond to water cells in grid1
 * 2. Use DFS to sink these islands in grid2 (they cannot be sub-islands)
 * 3. Count the remaining islands in grid2 (these are all sub-islands)
 * <p>
 * Time Complexity: O(m*n) where m is the number of rows and n is the number of columns
 * Space Complexity: O(m*n) in the worst case for the recursion stack
 */

public class _731_e_CountSubIslands {

    /**
     * Example usage
     */
    public static void main(String[] args) {
        _731_e_CountSubIslands solution = new _731_e_CountSubIslands();

        // Example 1
        int[][] grid1 = {
                {1, 1, 1, 0, 0},
                {0, 1, 1, 1, 1},
                {0, 0, 0, 0, 0},
                {1, 0, 0, 0, 0},
                {1, 1, 0, 1, 1}
        };

        int[][] grid2 = {
                {1, 1, 1, 0, 0},
                {0, 0, 1, 1, 1},
                {0, 1, 0, 0, 0},
                {1, 0, 1, 1, 0},
                {0, 1, 0, 1, 0}
        };

        System.out.println("Number of sub-islands: " + solution.countSubIslands(grid1, grid2));

        // Example 2
        int[][] grid3 = {
                {1, 0, 1, 0, 1},
                {1, 1, 1, 1, 1},
                {0, 0, 0, 0, 0},
                {1, 1, 1, 1, 1},
                {1, 0, 1, 0, 1}
        };

        int[][] grid4 = {
                {0, 0, 0, 0, 0},
                {1, 1, 1, 1, 1},
                {0, 1, 0, 1, 0},
                {0, 1, 0, 1, 0},
                {1, 0, 0, 0, 1}
        };

        System.out.println("Number of sub-islands in Example 2: " + solution.countSubIslands(grid3, grid4));
    }

    /**
     * Main function to count the number of sub-islands
     */
    public int countSubIslands(int[][] grid1, int[][] grid2) {
        if (grid1 == null || grid2 == null || grid1.length == 0 || grid2.length == 0) {
            return 0;
        }

        int m = grid1.length;
        int n = grid1[0].length;

        // Step 1: Eliminate islands in grid2 that cannot be sub-islands
        // (islands that have land where grid1 has water)
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // If grid2 has land but grid1 has water, this island in grid2 cannot be a sub-island
                if (grid1[i][j] == 0 && grid2[i][j] == 1) {
                    // Sink this island in grid2
                    dfs(grid2, i, j);
                }
            }
        }

        // Step 2: Count remaining islands in grid2 (these are all sub-islands)
        int subIslandCount = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid2[i][j] == 1) {
                    // Found a sub-island
                    subIslandCount++;
                    // Sink this island so we don't count it again
                    dfs(grid2, i, j);
                }
            }
        }

        return subIslandCount;
    }

    /**
     * DFS function to "sink" an island by changing all connected 1's to 0's
     */
    private void dfs(int[][] grid, int i, int j) {
        int m = grid.length;
        int n = grid[0].length;

        // Check boundary conditions
        if (i < 0 || j < 0 || i >= m || j >= n) {
            return;
        }

        // If current cell is not land, return
        if (grid[i][j] == 0) {
            return;
        }

        // Mark current cell as visited by changing it to water
        grid[i][j] = 0;

        // Recursively check all adjacent cells
        dfs(grid, i + 1, j); // Down
        dfs(grid, i - 1, j); // Up
        dfs(grid, i, j + 1); // Right
        dfs(grid, i, j - 1); // Left
    }
}
