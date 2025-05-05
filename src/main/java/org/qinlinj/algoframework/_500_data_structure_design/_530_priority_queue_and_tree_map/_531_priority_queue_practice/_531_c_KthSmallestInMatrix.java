package org.qinlinj.algoframework._500_data_structure_design._530_priority_queue_and_tree_map._531_priority_queue_practice; /**
 * _531_c_KthSmallestInMatrix
 * <p>
 * LeetCode #378: Kth Smallest Element in a Sorted Matrix
 * <p>
 * This solution finds the kth smallest element in an n×n matrix where each row and column
 * is sorted in ascending order. This problem is similar to merging k sorted lists.
 * <p>
 * Approach:
 * 1. Treat each row as a sorted list
 * 2. Use a min-heap to merge these rows and find the kth element
 * 3. Initialize the heap with the first element from each row
 * 4. Extract elements from the heap k times to get the kth smallest
 * <p>
 * Time Complexity: O(k log n) where n is the size of the matrix
 * Space Complexity: O(n) for the heap
 */

import java.util.*;

public class _531_c_KthSmallestInMatrix {

    public static void main(String[] args) {
        _531_c_KthSmallestInMatrix solution = new _531_c_KthSmallestInMatrix();

        // Test case 1
        int[][] matrix1 = {
                {1, 5, 9},
                {10, 11, 13},
                {12, 13, 15}
        };
        int k1 = 8;

        System.out.println("Matrix:");
        printMatrix(matrix1);
        System.out.println(k1 + "th smallest element: " + solution.kthSmallest(matrix1, k1));
        // Expected output: 13

        // Test case 2
        int[][] matrix2 = {{-5}};
        int k2 = 1;

        System.out.println("\nMatrix:");
        printMatrix(matrix2);
        System.out.println(k2 + "th smallest element: " + solution.kthSmallest(matrix2, k2));
        // Expected output: -5
    }

    // Helper method to print a matrix
    private static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int val : row) {
                System.out.print(val + "\t");
            }
            System.out.println();
        }
    }

    public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;

        // Priority queue to store elements sorted by their values
        // Each entry is [value, row, column]
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> a[0] - b[0]);

        // Add the first element from each row to the heap
        for (int i = 0; i < n; i++) {
            minHeap.offer(new int[]{matrix[i][0], i, 0});
        }

        // Extract the kth smallest element
        for (int i = 1; i < k; i++) {
            int[] current = minHeap.poll();

            int row = current[1];
            int col = current[2];

            // Add the next element from the same row if available
            if (col + 1 < n) {
                minHeap.offer(new int[]{matrix[row][col + 1], row, col + 1});
            }
        }

        // The kth smallest element is at the top of the heap
        return minHeap.peek()[0];
    }
}