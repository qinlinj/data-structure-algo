package org.qinlinj.algoframework._700_brute_force_search_algo._750_bfs_algo._753_bfs_classic_practice_II; /**
 * Rotting Oranges (LeetCode 994)
 * ------------------------------
 * <p>
 * Summary:
 * This problem simulates the process of oranges rotting in a grid. In the grid:
 * - 0 represents an empty cell
 * - 1 represents a fresh orange
 * - 2 represents a rotten orange
 * <p>
 * Every minute, rotten oranges cause adjacent fresh oranges (up, down, left, right) to rot.
 * The task is to calculate the minimum time until no fresh oranges remain, or return -1 if
 * some oranges can never rot.
 * <p>
 * Key Concepts:
 * 1. Multi-source BFS - starting from all initially rotten oranges
 * 2. Level-by-level processing to track time (minutes)
 * 3. Grid traversal with four directional movement
 * 4. Checking if all fresh oranges can be reached
 * <p>
 * Approach:
 * - Identify all initially rotten oranges and add them to the queue
 * - Perform BFS spreading the rot to adjacent fresh oranges
 * - Count steps (minutes) as we process each level of the BFS
 * - Check if any fresh oranges remain after the BFS
 * <p>
 * Time Complexity: O(m×n) where m and n are the dimensions of the grid
 * Space Complexity: O(m×n) for the queue in the worst case
 */

import java.util.*;

public class _753_a_RottingOranges {

    /**
     * Example usage
     */
    public static void main(String[] args) {
        _753_a_RottingOranges solution = new _753_a_RottingOranges();

        // Example 1: grid = [[2,1,1],[1,1,0],[0,1,1]]
        int[][] grid1 = {
                {2, 1, 1},
                {1, 1, 0},
                {0, 1, 1}
        };
        System.out.println("Example 1: " + solution.orangesRotting(grid1)); // Expected: 4

        // Example 2: grid = [[2,1,1],[0,1,1],[1,0,1]]
        int[][] grid2 = {
                {2, 1, 1},
                {0, 1, 1},
                {1, 0, 1}
        };
        System.out.println("Example 2: " + solution.orangesRotting(grid2)); // Expected: -1

        // Example 3: grid = [[0,2]]
        int[][] grid3 = {
                {0, 2}
        };
        System.out.println("Example 3: " + solution.orangesRotting(grid3)); // Expected: 0

        // Additional example
        int[][] grid4 = {
                {2, 1, 1},
                {1, 1, 1},
                {1, 1, 1}
        };
        System.out.println("Additional Example: " + solution.orangesRotting(grid4)); // Expected: 4
    }

    /**
     * Calculate the minimum time until no fresh oranges remain
     * @param grid The grid representing oranges and empty cells
     * @return Minimum minutes until all oranges rot, or -1 if impossible
     */
    public int orangesRotting(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;

        // Queue to store the positions of rotten oranges
        Queue<int[]> queue = new LinkedList<>();

        // Count of fresh oranges
        int freshCount = 0;

        // Add all initially rotten oranges to the queue
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 2) {
                    queue.offer(new int[]{i, j});
                } else if (grid[i][j] == 1) {
                    freshCount++;
                }
            }
        }

        // If there are no fresh oranges, return 0
        if (freshCount == 0) {
            return 0;
        }

        // Four directions: up, down, left, right
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        // Minutes passed
        int minutes = 0;

        // BFS to spread the rot
        while (!queue.isEmpty()) {
            int size = queue.size();

            // Process all oranges at the current level (minute)
            for (int i = 0; i < size; i++) {
                int[] position = queue.poll();

                // Try to rot oranges in all four directions
                for (int[] dir : directions) {
                    int newRow = position[0] + dir[0];
                    int newCol = position[1] + dir[1];

                    // Check if the adjacent cell is within bounds and has a fresh orange
                    if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols
                            && grid[newRow][newCol] == 1) {

                        // Make the orange rotten
                        grid[newRow][newCol] = 2;
                        freshCount--;

                        // Add the newly rotten orange to the queue
                        queue.offer(new int[]{newRow, newCol});
                    }
                }
            }

            // Increment minutes after processing all oranges at the current level
            if (!queue.isEmpty()) {
                minutes++;
            }
        }

        // If there are still fresh oranges left, it's impossible to rot all of them
        return freshCount == 0 ? minutes : -1;
    }
}