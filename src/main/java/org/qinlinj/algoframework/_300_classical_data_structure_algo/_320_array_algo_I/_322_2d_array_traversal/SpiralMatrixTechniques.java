package org.qinlinj.algoframework._300_classical_data_structure_algo._320_array_algo_I._322_2d_array_traversal;

import java.util.*;

/**
 * Spiral Matrix Techniques
 * =======================
 * <p>
 * This class demonstrates techniques for traversing and generating matrices in a spiral pattern.
 * These techniques are commonly used in coding interviews and competitive programming.
 * <p>
 * The core principle is to define four boundaries (upper, lower, left, right) and traverse
 * the matrix in a clockwise direction: right → down → left → up, adjusting the boundaries
 * as we complete each direction.
 * <p>
 * Two main operations are implemented:
 * 1. Spiral Traversal: Return all elements of a matrix in spiral order
 * 2. Spiral Generation: Create a matrix with numbers 1 to n² in spiral order
 * <p>
 * Time Complexity: O(m*n) where m and n are the dimensions of the matrix
 * Space Complexity: O(1) excluding the output array
 */
public class SpiralMatrixTechniques {
    /**
     * Traverses a matrix in spiral order and returns all elements.
     * <p>
     * Algorithm:
     * 1. Define four boundaries: upper, lower, left, right
     * 2. Traverse the matrix in four directions: right, down, left, up
     * 3. After traversing in each direction, update the respective boundary
     * 4. Continue until all elements are visited
     *
     * @param matrix The input matrix to traverse
     * @return List of elements in spiral order
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();

        if (matrix == null || matrix.length == 0) {
            return result;
        }

        int m = matrix.length;        // Number of rows
        int n = matrix[0].length;     // Number of columns

        // Define the four boundaries
        int upperBound = 0;
        int lowerBound = m - 1;
        int leftBound = 0;
        int rightBound = n - 1;

        // Continue until we've visited all elements
        while (result.size() < m * n) {
            // Traverse right along top row
            if (upperBound <= lowerBound) {
                for (int j = leftBound; j <= rightBound; j++) {
                    result.add(matrix[upperBound][j]);
                }
                upperBound++; // Move upper boundary down
            }

            // Traverse down along rightmost column
            if (leftBound <= rightBound) {
                for (int i = upperBound; i <= lowerBound; i++) {
                    result.add(matrix[i][rightBound]);
                }
                rightBound--; // Move right boundary left
            }

            // Traverse left along bottom row
            if (upperBound <= lowerBound) {
                for (int j = rightBound; j >= leftBound; j--) {
                    result.add(matrix[lowerBound][j]);
                }
                lowerBound--; // Move lower boundary up
            }

            // Traverse up along leftmost column
            if (leftBound <= rightBound) {
                for (int i = lowerBound; i >= upperBound; i--) {
                    result.add(matrix[i][leftBound]);
                }
                leftBound++; // Move left boundary right
            }
        }

        return result;
    }
}
