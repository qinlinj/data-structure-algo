package org.qinlinj.algoframework._700_brute_force_search_algo._730_dfs_backtracking_problems._731_island_problems;

/**
 * Number of Distinct Islands (LeetCode 694)
 * <p>
 * Problem:
 * Given a 2D grid where 1 represents land and 0 represents water, count the number of distinct
 * islands. Islands are considered distinct if their shapes are different (not just rotations or
 * reflections of each other).
 * <p>
 * Key insight:
 * We need a way to encode the shape of each island so we can identify duplicates.
 * The traversal path of DFS can serve as a unique signature for an island's shape.
 * <p>
 * Algorithm:
 * 1. Traverse the grid and for each island, generate a path signature using DFS
 * 2. Store the signatures in a HashSet to eliminate duplicates
 * 3. The size of the HashSet represents the number of distinct islands
 * <p>
 * The path signature:
 * - We record the direction of each move in the DFS (up, down, left, right)
 * - We also record when we backtrack from each direction
 * - This creates a unique "path signature" for islands of the same shape
 * <p>
 * Time Complexity: O(m*n) where m is the number of rows and n is the number of columns
 * Space Complexity: O(m*n) for storing the path signatures
 */

import java.util.*;

public class _731_f_NumberOfDistinctIslands {

    /**
     * Example usage
     */
    public static void main(String[] args) {
        _731_f_NumberOfDistinctIslands solution = new _731_f_NumberOfDistinctIslands();

        // Example 1: Grid with islands of 3 distinct shapes
        int[][] grid1 = {
                {1, 1, 0, 0, 0},
                {1, 1, 0, 0, 0},
                {0, 0, 0, 1, 1},
                {0, 0, 0, 1, 1}
        };

        System.out.println("Number of distinct islands in grid1: " + solution.numDistinctIslands(grid1));

        // Example 2: Grid with islands of different shapes
        int[][] grid2 = {
                {1, 1, 0, 1, 1},
                {1, 0, 0, 0, 0},
                {0, 0, 0, 0, 1},
                {1, 1, 0, 1, 1}
        };

        System.out.println("Number of distinct islands in grid2: " + solution.numDistinctIslandsAlt(grid2));
    }

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
     * @param i, j - current position
     * @param sb - StringBuilder to build the path signature
     * @param dir - direction from which we arrived at current position
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

    private void dfsAlt(int[][] grid, int i, int j, StringBuilder sb, int dir) {
        int m = grid.length;
        int n = grid[0].length;

        if (i < 0 || j < 0 || i >= m || j >= n || grid[i][j] == 0) {
            return;
        }

        grid[i][j] = 0;
        sb.append(dir).append(',');

        // Direction codes: 1 = up, 2 = down, 3 = left, 4 = right
        dfsAlt(grid, i - 1, j, sb, 1); // Up
        dfsAlt(grid, i + 1, j, sb, 2); // Down
        dfsAlt(grid, i, j - 1, sb, 3); // Left
        dfsAlt(grid, i, j + 1, sb, 4); // Right

        // Important: record backtracking with negative direction
        sb.append(-dir).append(',');
    }
}