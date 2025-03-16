package org.qinlinj.nonlinear.graph.floodfill;

public class ArrayHelper {
    /**
     * Converts a 2D array to a 1D array by flattening it in row-major order.
     * <p>
     * Algorithm:
     * 1. Determine dimensions of the input 2D array
     * 2. Create a new 1D array with size = rows * cols
     * 3. Map each element (i,j) to position (i * cols + j) in the 1D array
     * <p>
     * Visual Example:
     * 2D array:
     * [
     * [4, 2, 5],
     * [3, 7, 1]
     * ]
     * <p>
     * Converted to 1D array:
     * [4, 2, 5, 3, 7, 1]
     * <p>
     * Mapping:
     * (0,0) → 0 * 3 + 0 = 0 → 4
     * (0,1) → 0 * 3 + 1 = 1 → 2
     * (0,2) → 0 * 3 + 2 = 2 → 5
     * (1,0) → 1 * 3 + 0 = 3 → 3
     * (1,1) → 1 * 3 + 1 = 4 → 7
     * (1,2) → 1 * 3 + 2 = 5 → 1
     * <p>
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

    public static int[][] oneDimConvertTwoDim(int[] arr, int rows, int cols) {
        int[][] res = new int[rows][cols];
        for (int index = 0; index < arr.length; index++) {
            int i = index / cols;
            int j = index % cols;
            res[i][j] = arr[index];
        }
        return res;
    }

    public static void printAdj(int[][] arr, int i, int j) {
        int rows = arr.length;
        int cols = arr[0].length;

        int[][] directions = {
                {-1, 0}, {1, 0}, {0, -1}, {0, 1},
                {-1, -1}, {-1, 1}, {1, -1}, {1, 1}
        };
        for (int[] dir : directions) {
            int row = i + dir[0];
            int col = j + dir[1];
            if (row < rows && col < cols && row >= 0 && col >= 0) {
                System.out.println(arr[row][col]);
            }
        }
    }

    public static void main(String[] args) {
        int[][] data = {
                {4, 2, 5, 11},
                {3, 7, 1, 9},
                {32, 22, 13, 8}
        };
        // System.out.println(Arrays.toString(twoDimConvertOneDim(data)));

        /*int[] arr = {4, 2, 5, 11, 3, 7, 1, 9, 32, 22, 13, 8};
        for (int[] a : oneDimConvertTwoDim(arr, 3, 4)) {
            System.out.println(Arrays.toString(a));
        }*/

        printAdj(data, 0, 0);
    }
}
