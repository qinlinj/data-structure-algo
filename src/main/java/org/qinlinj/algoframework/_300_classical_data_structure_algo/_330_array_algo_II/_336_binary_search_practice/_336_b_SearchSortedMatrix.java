package org.qinlinj.algoframework._300_classical_data_structure_algo._330_array_algo_II._336_binary_search_practice;

/**
 * Binary Search in 2D Arrays - Searching in Sorted Matrix
 * <p>
 * Key Concepts:
 * 1. Treating a 2D sorted matrix as a 1D sorted array for binary search
 * 2. Converting between 1D and 2D coordinates for binary search
 * <p>
 * This class implements LeetCode 74: Search a 2D Matrix
 * Problem: Search for a target value in a matrix with the following properties:
 * - Each row is sorted in non-decreasing order
 * - The first element of each row is greater than the last element of the previous row
 * <p>
 * This structure allows us to treat the matrix as a single sorted array and
 * perform standard binary search by mapping between 1D and 2D coordinates.
 */
public class _336_b_SearchSortedMatrix {

    public static void main(String[] args) {
        _336_b_SearchSortedMatrix solution = new _336_b_SearchSortedMatrix();

        // Example 1
        int[][] matrix1 = {
                {1, 3, 5, 7},
                {10, 11, 16, 20},
                {23, 30, 34, 60}
        };
        int target1 = 3;

        System.out.println("Example 1: Target = " + target1);
        System.out.println("Found: " + solution.searchMatrix(matrix1, target1)); // Expected: true

        // Example 2
        int[][] matrix2 = {
                {1, 3, 5, 7},
                {10, 11, 16, 20},
                {23, 30, 34, 60}
        };
        int target2 = 13;

        System.out.println("\nExample 2: Target = " + target2);
        System.out.println("Found: " + solution.searchMatrix(matrix2, target2)); // Expected: false
    }

    /**
     * Searches for a target value in a sorted matrix
     * Time Complexity: O(log(m*n)) where m*n is the total number of elements
     * Space Complexity: O(1)
     *
     * @param matrix Sorted matrix
     * @param target Target value to find
     * @return true if target exists in matrix, false otherwise
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }

        int m = matrix.length;
        int n = matrix[0].length;

        // Treat the matrix as a sorted array and perform binary search
        int left = 0;
        int right = m * n - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int midValue = getValueFromIndex(matrix, mid);

            if (midValue == target) {
                return true;
            } else if (midValue < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return false;
    }

    /**
     * Gets a value from the matrix using a 1D index
     *
     * @param matrix Input matrix
     * @param index  1D index (0-based)
     * @return Value at the calculated position
     */
    private int getValueFromIndex(int[][] matrix, int index) {
        int n = matrix[0].length; // Number of columns

        // Convert 1D index to 2D coordinates
        int row = index / n;
        int col = index % n;

        return matrix[row][col];
    }

    /**
     * Alternative implementation without helper method
     */
    public boolean searchMatrixDirect(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }

        int m = matrix.length;
        int n = matrix[0].length;

        // Treat the matrix as a sorted array and perform binary search
        int left = 0;
        int right = m * n - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            // Convert 1D index to 2D coordinates
            int row = mid / n;
            int col = mid % n;

            if (matrix[row][col] == target) {
                return true;
            } else if (matrix[row][col] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return false;
    }
}