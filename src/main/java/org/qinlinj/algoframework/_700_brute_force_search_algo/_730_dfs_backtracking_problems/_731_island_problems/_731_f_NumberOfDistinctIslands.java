package org.qinlinj.algoframework._700_brute_force_search_algo._730_dfs_backtracking_problems._731_island_problems;

import java.util.*;

public class _731_f_NumberOfDistinctIslands {
    /**
     * Main function to count the number of distinct islands
     */
    public int numDistinctIslands(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int m = grid.length;
        int n = grid[0].length;

        // HashSet to store unique island signatures
        HashSet<String> distinctIslands = new HashSet<>();

        // Traverse the grid
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    // Found a new island
                    StringBuilder sb = new StringBuilder();
                    // Generate the path signature using a starting direction (arbitrary value)
                    dfs(grid, i, j, sb, 'o'); // 'o' for origin
                    distinctIslands.add(sb.toString());
                }
            }
        }

        // The number of distinct islands is the size of the HashSet
        return distinctIslands.size();
    }

    /**
     * DFS function to generate a path signature for an island
     *
     * @param grid - the input grid
     * @param i,   j - current position
     * @param sb   - StringBuilder to build the path signature
     * @param dir  - direction from which we arrived at current position
     */
    private void dfs(int[][] grid, int i, int j, StringBuilder sb, char dir) {
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

        // Mark current cell as visited
        grid[i][j] = 0;

        // Append the current direction to the path signature
        sb.append(dir);

        // Explore in all four directions with distinct direction codes
        dfs(grid, i - 1, j, sb, 'u'); // Up
        dfs(grid, i + 1, j, sb, 'd'); // Down
        dfs(grid, i, j - 1, sb, 'l'); // Left
        dfs(grid, i, j + 1, sb, 'r'); // Right

        // Append a backtrack marker
        // This is crucial for distinguishing different shapes
        sb.append('b'); // Backtrack
    }

    /**
     * Alternative implementation using a direction array and numeric codes
     */
    public int numDistinctIslandsAlt(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int m = grid.length;
        int n = grid[0].length;
        HashSet<String> islands = new HashSet<>();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    StringBuilder sb = new StringBuilder();
                    // Use a numeric code (666 is arbitrary)
                    dfsAlt(grid, i, j, sb, 666);
                    islands.add(sb.toString());
                }
            }
        }

        return islands.size();
    }
}
