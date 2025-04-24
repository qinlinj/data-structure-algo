package org.qinlinj.algoframework._300_classical_data_structure_algo._320_array_algo_I._323_array_two_pointer_practice;

import java.util.*;

/**
 * Shift 2D Grid (LeetCode 1260)
 * ============================
 * <p>
 * This class implements a solution to shift elements in a 2D grid.
 * <p>
 * Problem:
 * Given a 2D grid of size m x n and an integer k, shift the grid k times.
 * <p>
 * In one shift operation:
 * - Element at grid[i][j] moves to grid[i][j+1]
 * - Element at grid[i][n-1] moves to grid[i+1][0]
 * - Element at grid[m-1][n-1] moves to grid[0][0]
 * <p>
 * In other words, all elements move right by one position, and elements that would move
 * off the right edge wrap around to the next row (or to the top-left corner for the last element).
 * <p>
 * Examples:
 * - grid = [[1,2,3],[4,5,6],[7,8,9]], k = 1 -> [[9,1,2],[3,4,5],[6,7,8]]
 * - grid = [[1,2,3],[4,5,6],[7,8,9]], k = 9 -> [[1,2,3],[4,5,6],[7,8,9]]
 * <p>
 * Approach:
 * The key insight is to view the 2D grid as a 1D array, then this becomes a standard array rotation problem.
 * To rotate an array by k positions:
 * 1. Reverse the last k elements
 * 2. Reverse the first (n-k) elements
 * 3. Reverse the entire array
 * <p>
 * Time Complexity: O(m*n) where m and n are the dimensions of the grid
 * Space Complexity: O(1) for the algorithm itself (excluding the output space)
 */
public class _323_g_ShiftGrid {
    /**
     * Shifts a 2D grid k times.
     *
     * @param grid The input 2D grid
     * @param k    The number of shifts to perform
     * @return The shifted grid as a List of Lists
     */
    public List<List<Integer>> shiftGrid(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int totalSize = m * n;

        // Handle case where k is greater than total size
        k = k % totalSize;

        // If k is 0, no shifting needed
        if (k == 0) {
            return gridToList(grid);
        }

        // Perform the three-step reversal
        // 1. Reverse the last k elements
        reverse(grid, totalSize - k, totalSize - 1);

        // 2. Reverse the first (totalSize - k) elements
        reverse(grid, 0, totalSize - k - 1);

        // 3. Reverse the entire grid
        reverse(grid, 0, totalSize - 1);

        // Convert the grid to the expected output format
        return gridToList(grid);
    }

    private void reverse(int[][] grid, int i, int i1) {
    }

    private List<List<Integer>> gridToList(int[][] grid) {
        return null;
    }
}
