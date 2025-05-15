package org.qinlinj.algoframework._700_brute_force_search_algo._750_bfs_algo._752_bfs_classic_practice_I; /**
 * Shortest Path in Binary Matrix (LeetCode 1091)
 * --------------------------------------------
 * <p>
 * Summary:
 * This problem asks us to find the shortest path from the top-left corner (0,0) to the
 * bottom-right corner (n-1, n-1) in a binary matrix. We can move in 8 directions (including diagonals),
 * and we can only travel through cells with a value of 0.
 * <p>
 * Key Concepts:
 * 1. BFS to find the shortest path in an unweighted graph
 * 2. Grid traversal with eight possible moves (including diagonals)
 * 3. Tracking visited cells to avoid revisiting
 * 4. Counting path length (number of cells traversed)
 * <p>
 * Approach:
 * - Use BFS starting from the top-left corner
 * - Explore in eight directions (horizontal, vertical, and diagonal)
 * - Track visited cells to avoid revisiting
 * - Count the path length (including start and end cells)
 * - Return when we reach the bottom-right corner
 * <p>
 * Time Complexity: O(n²) where n is the size of the grid
 * Space Complexity: O(n²) for the visited array and the queue
 */

import java.util.*;

public class _752_j_ShortestPathBinaryMatrix {

    /**
     * Example usage
     */
    public static void main(String[] args) {
        _752_j_ShortestPathBinaryMatrix solution = new _752_j_ShortestPathBinaryMatrix();

        // Example 1: grid = [[0,1],[1,0]]
        int[][] grid1 = {
                {0, 1},
                {1, 0}
        };

        int result1 = solution.shortestPathBinaryMatrix(grid1);
        System.out.println("Example 1: " + result1); // Should output 2

        // Example 2: grid = [[0,0,0],[1,1,0],[1,1,0]]
        int[][] grid2 = {
                {0, 0, 0},
                {1, 1, 0},
                {1, 1, 0}
        };

        int result2 = solution.shortestPathBinaryMatrix(grid2);
        System.out.println("Example 2: " + result2); // Should output 4

        // Example 3: grid = [[1,0,0],[1,1,0],[1,1,0]]
        int[][] grid3 = {
                {1, 0, 0},
                {1, 1, 0},
                {1, 1, 0}
        };

        int result3 = solution.shortestPathBinaryMatrix(grid3);
        System.out.println("Example 3: " + result3); // Should output -1

        // Example 4: A more complex grid
        int[][] grid4 = {
                {0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0},
                {0, 1, 0, 1, 0},
                {0, 0, 0, 1, 0},
                {0, 0, 0, 0, 0}
        };

        int result4 = solution.shortestPathBinaryMatrix(grid4);
        System.out.println("Example 4: " + result4); // Should output 8

        // Compare with optimized implementation
        int result4Optimized = solution.shortestPathBinaryMatrixOptimized(grid4.clone());
        System.out.println("Example 4 (Optimized): " + result4Optimized); // Should also output 8
    }

    /**
     * Find the shortest path from top-left to bottom-right in a binary matrix
     * @param grid Binary matrix where 0 represents an empty cell and 1 represents a wall
     * @return Length of the shortest path or -1 if no path exists
     */
    public int shortestPathBinaryMatrix(int[][] grid) {
        int n = grid.length;

        // Check if start or end is blocked
        if (grid[0][0] == 1 || grid[n - 1][n - 1] == 1) {
            return -1;
        }

        // Eight possible directions: right, left, down, up, and four diagonals
        int[][] directions = {
                {0, 1}, {0, -1}, {1, 0}, {-1, 0},
                {1, 1}, {1, -1}, {-1, 1}, {-1, -1}
        };

        // BFS implementation
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[n][n];

        // Start from the top-left corner
        queue.offer(new int[]{0, 0});
        visited[0][0] = true;

        int pathLength = 1; // Include the starting cell

        while (!queue.isEmpty()) {
            int levelSize = queue.size();

            for (int i = 0; i < levelSize; i++) {
                int[] current = queue.poll();
                int row = current[0];
                int col = current[1];

                // If we've reached the bottom-right corner
                if (row == n - 1 && col == n - 1) {
                    return pathLength;
                }

                // Explore all eight directions
                for (int[] dir : directions) {
                    int newRow = row + dir[0];
                    int newCol = col + dir[1];

                    // Check bounds and if the cell is valid and unvisited
                    if (newRow >= 0 && newRow < n && newCol >= 0 && newCol < n &&
                            grid[newRow][newCol] == 0 && !visited[newRow][newCol]) {

                        queue.offer(new int[]{newRow, newCol});
                        visited[newRow][newCol] = true;
                    }
                }
            }

            // Increment path length after processing each level
            pathLength++;
        }

        // If we exit the loop, no path was found
        return -1;
    }

    /**
     * Alternative implementation with additional optimizations
     */
    public int shortestPathBinaryMatrixOptimized(int[][] grid) {
        int n = grid.length;

        // Check if start or end is blocked
        if (grid[0][0] == 1 || grid[n - 1][n - 1] == 1) {
            return -1;
        }

        // Early exit for single cell grid
        if (n == 1) {
            return 1;
        }

        // Eight possible directions
        int[][] directions = {
                {0, 1}, {0, -1}, {1, 0}, {-1, 0},
                {1, 1}, {1, -1}, {-1, 1}, {-1, -1}
        };

        // Use grid itself to mark visited cells
        grid[0][0] = 1; // Mark as visited by setting to 1

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0, 1}); // [row, col, distance]

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int row = current[0];
            int col = current[1];
            int distance = current[2];

            // Explore all eight directions
            for (int[] dir : directions) {
                int newRow = row + dir[0];
                int newCol = col + dir[1];

                // Check if we've reached the destination
                if (newRow == n - 1 && newCol == n - 1) {
                    return distance + 1;
                }

                // Check bounds and if the cell is valid
                if (newRow >= 0 && newRow < n && newCol >= 0 && newCol < n &&
                        grid[newRow][newCol] == 0) {

                    grid[newRow][newCol] = 1; // Mark as visited
                    queue.offer(new int[]{newRow, newCol, distance + 1});
                }
            }
        }

        return -1;
    }
}