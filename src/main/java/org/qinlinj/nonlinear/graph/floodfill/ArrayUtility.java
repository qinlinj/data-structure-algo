package org.qinlinj.nonlinear.graph.floodfill;

// @formatter:off
/**
 * Array Helper Utility Class
 *
 * Concept and Principles:
 * This utility class provides methods for array transformations and operations commonly
 * needed in grid-based algorithms and data structures. It specifically addresses:
 * 1. Converting between 1D and 2D array representations
 * 2. Finding adjacent elements in a 2D grid (including diagonals)
 *
 * Advantages of these array utilities:
 * 1. Memory efficiency - 1D arrays have less overhead than 2D arrays
 * 2. Performance optimization - certain algorithms perform better on 1D arrays
 * 3. Simplifies grid traversal operations for graph algorithms
 * 4. Facilitates flood fill and graph search in grid-based problems
 * 5. Useful for image processing, game development, and matrix operations
 *
 * The class handles transformations that preserve the logical structure of the data
 * while changing its physical representation, enabling different access patterns
 * and algorithmic approaches.
 */
public class ArrayUtility {

    /**
     * Converts a 2D array to a 1D array by flattening it in row-major order.
     *
     * Algorithm:
     * 1. Determine dimensions of the input 2D array
     * 2. Create a new 1D array with size = rows * cols
     * 3. Map each element (i,j) to position (i * cols + j) in the 1D array
     *
     * Visual Example:
     * 2D array:
     * [
     *   [4, 2, 5],
     *   [3, 7, 1]
     * ]
     *
     * Converted to 1D array:
     * [4, 2, 5, 3, 7, 1]
     *
     * Mapping:
     * (0,0) → 0 * 3 + 0 = 0 → 4
     * (0,1) → 0 * 3 + 1 = 1 → 2
     * (0,2) → 0 * 3 + 2 = 2 → 5
     * (1,0) → 1 * 3 + 0 = 3 → 3
     * (1,1) → 1 * 3 + 1 = 4 → 7
     * (1,2) → 1 * 3 + 2 = 5 → 1
     *
     * Time Complexity: O(rows * cols) - must visit every element once
     * Space Complexity: O(rows * cols) - for the new 1D array
     *
     * @param arr The input 2D array to be converted
     * @return A 1D array containing all elements from the 2D array in row-major order
     */
    public static int[] twoDimConvertOneDim(int[][] arr) {
        int rows = arr.length;
        int cols = arr[0].length;

        int[] res = new int[rows * cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int index = i * cols + j;
                res[index] = arr[i][j];
            }
        }

        return res;
    }

    /**
     * Converts a 1D array back to a 2D array with specified dimensions.
     *
     * Algorithm:
     * 1. Create a new 2D array with given rows and columns
     * 2. For each position in the 1D array:
     *    - Calculate the corresponding row: i = index / cols
     *    - Calculate the corresponding column: j = index % cols
     *    - Assign the value to the 2D array at position (i,j)
     *
     * Visual Example:
     * 1D array: [4, 2, 5, 3, 7, 1]
     * With rows=2, cols=3
     *
     * Converted to 2D array:
     * [
     *   [4, 2, 5],
     *   [3, 7, 1]
     * ]
     *
     * Mapping:
     * 0 → row=0/3=0, col=0%3=0 → (0,0) → 4
     * 1 → row=1/3=0, col=1%3=1 → (0,1) → 2
     * 2 → row=2/3=0, col=2%3=2 → (0,2) → 5
     * 3 → row=3/3=1, col=3%3=0 → (1,0) → 3
     * 4 → row=4/3=1, col=4%3=1 → (1,1) → 7
     * 5 → row=5/3=1, col=5%3=2 → (1,2) → 1
     *
     * Time Complexity: O(rows * cols) - must process every element once
     * Space Complexity: O(rows * cols) - for the new 2D array
     *
     * @param arr The input 1D array to be converted
     * @param rows The number of rows for the resulting 2D array
     * @param cols The number of columns for the resulting 2D array
     * @return A 2D array with the specified dimensions
     */
    public static int[][] oneDimConvertTwoDim(int[] arr, int rows, int cols) {
        int[][] res = new int[rows][cols];
        for (int index = 0; index < arr.length; index++) {
            int i = index / cols;
            int j = index % cols;
            res[i][j] = arr[index];
        }
        return res;
    }

    /**
     * Prints all adjacent elements (including diagonals) to the element at position (i,j).
     *
     * Algorithm:
     * 1. Define all 8 possible directions (4 cardinal + 4 diagonal)
     * 2. For each direction, calculate the adjacent position
     * 3. Check if the adjacent position is within bounds
     * 4. If valid, print the value at that position
     *
     * Visual Example:
     * For a 3x4 grid:
     * [
     *   [4, 2, 5, 11],
     *   [3, 7, 1, 9],
     *   [32, 22, 13, 8]
     * ]
     *
     * Adjacent cells to position (1,1) which contains value 7:
     * - North (1,1) + (-1,0) = (0,1) → 2
     * - South (1,1) + (1,0) = (2,1) → 22
     * - West (1,1) + (0,-1) = (1,0) → 3
     * - East (1,1) + (0,1) = (1,2) → 1
     * - Northwest (1,1) + (-1,-1) = (0,0) → 4
     * - Northeast (1,1) + (-1,1) = (0,2) → 5
     * - Southwest (1,1) + (1,-1) = (2,0) → 32
     * - Southeast (1,1) + (1,1) = (2,2) → 13
     *
     * Time Complexity: O(1) - constant number of directions (8) to check
     * Space Complexity: O(1) - fixed-size directions array
     *
     * @param arr The 2D array to operate on
     * @param i The row index of the target element
     * @param j The column index of the target element
     */
    public static void printAdj(int[][] arr, int i, int j) {
        int rows = arr.length;
        int cols = arr[0].length;

        // Define all 8 directions: N, S, W, E, NW, NE, SW, SE
        int[][] directions = {
                {-1, 0}, {1, 0}, {0, -1}, {0, 1},    // Cardinal directions
                {-1, -1}, {-1, 1}, {1, -1}, {1, 1}   // Diagonal directions
        };
        for (int[] dir : directions) {
            int row = i + dir[0];
            int col = j + dir[1];
            // Check if the adjacent position is within bounds
            if (row < rows && col < cols && row >= 0 && col >= 0) {
                System.out.println(arr[row][col]);
            }
        }
    }

    /**
     * Main method to demonstrate the array helper utilities.
     *
     * Visual demonstration with sample data:
     * Sample 2D array:
     * [
     *   [4, 2, 5, 11],
     *   [3, 7, 1, 9],
     *   [32, 22, 13, 8]
     * ]
     *
     * 1. twoDimConvertOneDim would convert this to:
     *    [4, 2, 5, 11, 3, 7, 1, 9, 32, 22, 13, 8]
     *
     * 2. oneDimConvertTwoDim would convert the 1D array back to the original 2D array
     *
     * 3. printAdj for position (0,0) would output adjacent values:
     *    - Existing adjacents: [2, 3, 7]
     *    (Other directions are out of bounds)
     *
     * Time Complexity: Depends on which method is called and the input size
     * Space Complexity: Depends on which method is called and the input size
     *
     * @param args Command-line arguments (not used)
     */
    public static void main(String[] args) {
        int[][] data = {
                {4, 2, 5, 11},
                {3, 7, 1, 9},
                {32, 22, 13, 8}
        };
        // Uncomment to demonstrate twoDimConvertOneDim
        // System.out.println(Arrays.toString(twoDimConvertOneDim(data)));

        // Uncomment to demonstrate oneDimConvertTwoDim
        /*int[] arr = {4, 2, 5, 11, 3, 7, 1, 9, 32, 22, 13, 8};
        for (int[] a : oneDimConvertTwoDim(arr, 3, 4)) {
            System.out.println(Arrays.toString(a));
        }*/

        // Demonstrate printAdj
        printAdj(data, 0, 0);
    }
}
