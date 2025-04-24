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
     * Demonstration of the grid shifting algorithm.
     */
    public static void main(String[] args) {
        _323_g_ShiftGrid solution = new _323_g_ShiftGrid();

        // Example 1
        int[][] grid1 = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        int k1 = 1;

        System.out.println("Example 1:");
        System.out.println("Original grid:");
        solution.printGrid(solution.gridToList(grid1));

        List<List<Integer>> result1 = solution.shiftGrid(grid1, k1);
        System.out.println("After " + k1 + " shift(s):");
        solution.printGrid(result1);

        // Example 2
        int[][] grid2 = {
                {3, 8, 1, 9},
                {19, 7, 2, 5},
                {4, 6, 11, 10},
                {12, 0, 21, 13}
        };
        int k2 = 4;

        System.out.println("Example 2:");
        System.out.println("Original grid:");
        solution.printGrid(solution.gridToList(grid2));

        List<List<Integer>> result2 = solution.shiftGrid(grid2, k2);
        System.out.println("After " + k2 + " shift(s):");
        solution.printGrid(result2);

        // Example 3
        int[][] grid3 = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        int k3 = 9;

        System.out.println("Example 3:");
        System.out.println("Original grid:");
        solution.printGrid(solution.gridToList(grid3));

        List<List<Integer>> result3 = solution.shiftGrid(grid3, k3);
        System.out.println("After " + k3 + " shift(s):");
        solution.printGrid(result3);
    }

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

    /**
     * Converts a 2D array to a List of Lists.
     *
     * @param grid The 2D array to convert
     * @return A List of Lists representation of the grid
     */
    private List<List<Integer>> gridToList(int[][] grid) {
        List<List<Integer>> result = new ArrayList<>();

        for (int[] row : grid) {
            List<Integer> rowList = new ArrayList<>();
            for (int cell : row) {
                rowList.add(cell);
            }
            result.add(rowList);
        }

        return result;
    }

    /**
     * Reverses elements in the grid from index start to index end (inclusive),
     * treating the grid as a 1D array.
     *
     * @param grid  The 2D grid
     * @param start The starting index (in 1D representation)
     * @param end   The ending index (in 1D representation)
     */
    private void reverse(int[][] grid, int start, int end) {
        while (start < end) {
            int temp = get(grid, start);
            set(grid, start, get(grid, end));
            set(grid, end, temp);
            start++;
            end--;
        }
    }

    /**
     * Gets an element from the grid using a 1D array index.
     *
     * @param grid  The 2D grid
     * @param index The 1D index
     * @return The value at the corresponding position
     */
    private int get(int[][] grid, int index) {
        int n = grid[0].length;
        int row = index / n;
        int col = index % n;
        return grid[row][col];
    }

    /**
     * Sets an element in the grid using a 1D array index.
     *
     * @param grid  The 2D grid
     * @param index The 1D index
     * @param value The value to set
     */
    private void set(int[][] grid, int index, int value) {
        int n = grid[0].length;
        int row = index / n;
        int col = index % n;
        grid[row][col] = value;
    }

    /**
     * Alternative approach that creates a new grid instead of modifying in-place.
     * This might be easier to understand but uses more memory.
     */
    public List<List<Integer>> shiftGridAlternative(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int totalSize = m * n;

        // Handle case where k is greater than total size
        k = k % totalSize;

        // Create a new grid to store the result
        int[][] result = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // Convert 2D position to 1D index
                int index = i * n + j;

                // Calculate the new position after shifting
                int newIndex = (index + k) % totalSize;

                // Convert back to 2D position
                int newRow = newIndex / n;
                int newCol = newIndex % n;

                // Set the value in the new position
                result[newRow][newCol] = grid[i][j];
            }
        }

        return gridToList(result);
    }

    /**
     * Prints a 2D grid for visualization.
     */
    public void printGrid(List<List<Integer>> grid) {
        for (List<Integer> row : grid) {
            System.out.print("[");
            for (int i = 0; i < row.size(); i++) {
                System.out.print(row.get(i));
                if (i < row.size() - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println("]");
        }
        System.out.println();
    }
}
