package org.qinlinj.algoframework._700_brute_force_search_algo._730_dfs_backtracking_problems._731_island_problems;

/**
 * Island Problems - Basic Concepts
 * <p>
 * This file covers the fundamental concepts for solving island-related problems in algorithms.
 * Island problems are a classic type of interview questions that involve traversing 2D matrices
 * using DFS (Depth-First Search) or BFS (Breadth-First Search).
 * <p>
 * Key Concepts:
 * 1. Islands are formed by connecting land cells (usually represented as 1's) in a 2D grid
 * 2. The main technique used is DFS/BFS to traverse the grid
 * 3. We can view a 2D matrix as a graph where each cell is connected to its adjacent cells
 * (typically in 4 directions: up, down, left, right)
 * <p>
 * DFS Framework for 2D matrices:
 * - Similar to binary tree traversal but with 4 possible directions
 * - Need to handle boundary conditions and visited cells
 * - Often uses a "flooding" technique where visited cells are marked
 */

public class _731_a_IslandBasics {

    public static void main(String[] args) {
        System.out.println("This class demonstrates the basic framework for solving island problems.");
        System.out.println("It provides the fundamental DFS traversal algorithm for 2D matrices.");
    }

    /**
     * Basic DFS traversal framework for a 2D matrix
     * This is the core algorithm used in island problems
     */
    void dfs(int[][] grid, int i, int j, boolean[][] visited) {
        int m = grid.length, n = grid[0].length;
        // Check boundary conditions
        if (i < 0 || j < 0 || i >= m || j >= n) {
            // Out of bounds
            return;
        }
        // Check if already visited
        if (visited[i][j]) {
            // Already visited this cell
            return;
        }

        // Process the current cell (i, j)
        visited[i][j] = true;

        // Visit the adjacent cells (in all four directions)
        // Up
        dfs(grid, i - 1, j, visited);
        // Down
        dfs(grid, i + 1, j, visited);
        // Left
        dfs(grid, i, j - 1, visited);
        // Right
        dfs(grid, i, j + 1, visited);
    }

    /**
     * Alternative implementation using direction arrays
     * This is a common technique to make the code more concise
     */
    void dfsWithDirectionArray(int[][] grid, int i, int j, boolean[][] visited) {
        int m = grid.length, n = grid[0].length;
        // Check boundary conditions
        if (i < 0 || j < 0 || i >= m || j >= n) {
            return;
        }
        // Check if already visited
        if (visited[i][j]) {
            return;
        }

        // Mark as visited
        visited[i][j] = true;

        // Direction arrays for up, down, left, right
        int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        // Recursively explore all four directions
        for (int[] dir : dirs) {
            int next_i = i + dir[0];
            int next_j = j + dir[1];
            dfsWithDirectionArray(grid, next_i, next_j, visited);
        }
    }
}