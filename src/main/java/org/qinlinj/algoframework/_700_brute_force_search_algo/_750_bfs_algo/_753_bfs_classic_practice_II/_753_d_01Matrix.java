package org.qinlinj.algoframework._700_brute_force_search_algo._750_bfs_algo._753_bfs_classic_practice_II; /**
 * 01 Matrix (LeetCode 542)
 * -----------------------
 * <p>
 * Summary:
 * This problem asks us to find the distance of the nearest 0 for each cell in a binary matrix.
 * The distance between two adjacent cells is 1. We need to create a new matrix where each cell
 * contains the distance to the nearest 0.
 * <p>
 * Key Concepts:
 * 1. Multi-source BFS starting from all zeros
 * 2. Level-by-level processing to track distance
 * 3. Using queue to process cells in order of increasing distance
 * 4. Updating distances during the BFS traversal
 * <p>
 * Approach:
 * - Initialize a result matrix with special values (-1) to indicate unprocessed cells
 * - Add all cells with value 0 to the queue with distance 0
 * - Perform BFS, gradually filling in distances for all other cells
 * - For each cell, calculate distances to adjacent cells by adding 1 to the current distance
 * <p>
 * Time Complexity: O(m*n) where m and n are the dimensions of the matrix
 * Space Complexity: O(m*n) for the queue and result matrix
 * <p>
 * Note: This problem can also be solved using dynamic programming, but BFS is more intuitive.
 */

import java.util.*;

public class _753_d_01Matrix {

    /**
     * Helper method to print a matrix
     */
    private static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
        System.out.println();
    }

    /**
     * Example usage
     */
    public static void main(String[] args) {
        _753_d_01Matrix solution = new _753_d_01Matrix();

        // Example 1: mat = [[0,0,0],[0,1,0],[0,0,0]]
        int[][] mat1 = {
                {0, 0, 0},
                {0, 1, 0},
                {0, 0, 0}
        };
        System.out.println("Example 1 (BFS):");
        printMatrix(solution.updateMatrix(mat1));

        // Example 2: mat = [[0,0,0],[0,1,0],[1,1,1]]
        int[][] mat2 = {
                {0, 0, 0},
                {0, 1, 0},
                {1, 1, 1}
        };
        System.out.println("Example 2 (BFS):");
        printMatrix(solution.updateMatrix(mat2));

        // Using DP approach
        System.out.println("Example 1 (DP):");
        printMatrix(solution.updateMatrixDP(mat1));

        System.out.println("Example 2 (DP):");
        printMatrix(solution.updateMatrixDP(mat2));

        // Additional example
        int[][] mat3 = {
                {1, 1, 1, 1},
                {1, 1, 1, 1},
                {1, 0, 1, 1},
                {1, 1, 1, 1}
        };
        System.out.println("Additional Example:");
        printMatrix(solution.updateMatrix(mat3));
    }

    /**
     * Calculate the distance to the nearest 0 for each cell
     * @param mat Binary matrix with 0s and 1s
     * @return Matrix where each cell contains the distance to the nearest 0
     */
    public int[][] updateMatrix(int[][] mat) {
        int rows = mat.length;
        int cols = mat[0].length;

        // Initialize result matrix with -1 (unvisited)
        int[][] result = new int[rows][cols];
        for (int[] row : result) {
            Arrays.fill(row, -1);
        }

        // Queue for BFS
        Queue<int[]> queue = new LinkedList<>();

        // Add all zeros to the queue as starting points
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (mat[i][j] == 0) {
                    queue.offer(new int[]{i, j});
                    result[i][j] = 0;  // Distance to itself is 0
                }
            }
        }

        // Four directions: up, down, left, right
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        // BFS to fill distances
        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            int row = cell[0];
            int col = cell[1];

            // Process all adjacent cells
            for (int[] dir : directions) {
                int newRow = row + dir[0];
                int newCol = col + dir[1];

                // Check if the adjacent cell is valid and unvisited
                if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols
                        && result[newRow][newCol] == -1) {

                    // Distance to adjacent cell is current distance + 1
                    result[newRow][newCol] = result[row][col] + 1;
                    queue.offer(new int[]{newRow, newCol});
                }
            }
        }

        return result;
    }

    /**
     * Alternative solution using dynamic programming
     * This is a two-pass approach:
     * 1. Top-down, left-right pass to handle cells above and to the left
     * 2. Bottom-up, right-left pass to handle cells below and to the right
     */
    public int[][] updateMatrixDP(int[][] mat) {
        int rows = mat.length;
        int cols = mat[0].length;
        int maxDist = rows + cols;  // Maximum possible distance

        int[][] dist = new int[rows][cols];

        // Initialize with maximum distances
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (mat[i][j] == 0) {
                    dist[i][j] = 0;
                } else {
                    dist[i][j] = maxDist;
                }
            }
        }

        // First pass: top-down, left-right
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (i > 0) {
                    dist[i][j] = Math.min(dist[i][j], dist[i - 1][j] + 1);
                }
                if (j > 0) {
                    dist[i][j] = Math.min(dist[i][j], dist[i][j - 1] + 1);
                }
            }
        }

        // Second pass: bottom-up, right-left
        for (int i = rows - 1; i >= 0; i--) {
            for (int j = cols - 1; j >= 0; j--) {
                if (i < rows - 1) {
                    dist[i][j] = Math.min(dist[i][j], dist[i + 1][j] + 1);
                }
                if (j < cols - 1) {
                    dist[i][j] = Math.min(dist[i][j], dist[i][j + 1] + 1);
                }
            }
        }

        return dist;
    }
}