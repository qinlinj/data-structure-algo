package org.qinlinj.algoframework._700_brute_force_search_algo._730_dfs_backtracking_problems._731_island_problems;

/**
 * _421_a_IslandBasics
 * <p>
 * Island Problems Overview:
 * ------------------------
 * Island problems are classic interview questions that involve DFS/BFS traversal of 2D arrays.
 * The core concept is treating the 2D matrix as a graph where:
 * - Each cell is a node
 * - Adjacent cells (up, down, left, right) are neighboring nodes
 * <p>
 * This class provides the basic framework for traversing a 2D matrix using DFS,
 * which can be used to solve various island-related problems.
 * <p>
 * Key points:
 * 1. We use a visited array to avoid revisiting nodes
 * 2. Alternatively, we can modify the original grid to mark visited cells
 * 3. Direction arrays can be used to simplify traversal in four directions
 */
public class _731_a_IslandBasics {

    // Basic DFS traversal framework for a 2D matrix
    void dfs(int[][] grid, int i, int j, boolean[][] visited) {
        int m = grid.length, n = grid[0].length;
        if (i < 0 || j < 0 || i >= m || j >= n) {
            // Out of bounds
            return;
        }
        if (visited[i][j]) {
            // Already visited
            return;
        }

        // Enter the current node (i, j)
        visited[i][j] = true;

        // Visit adjacent nodes (four directions)
        // Up
        dfs(grid, i - 1, j, visited);
        // Down
        dfs(grid, i + 1, j, visited);
        // Left
        dfs(grid, i, j - 1, visited);
        // Right
        dfs(grid, i, j + 1, visited);
    }

    // Alternative approach using direction array
    void dfsWithDirections(int[][] grid, int i, int j, boolean[][] visited) {
        int m = grid.length, n = grid[0].length;
        if (i < 0 || j < 0 || i >= m || j >= n) {
            // Out of bounds
            return;
        }
        if (visited[i][j]) {
            // Already visited
            return;
        }

        // Direction array representing up, down, left, right
        int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        // Enter node (i, j)
        visited[i][j] = true;

        // Recursively visit all four directions
        for (int[] dir : dirs) {
            int next_i = i + dir[0];
            int next_j = j + dir[1];
            dfsWithDirections(grid, next_i, next_j, visited);
        }
    }
}