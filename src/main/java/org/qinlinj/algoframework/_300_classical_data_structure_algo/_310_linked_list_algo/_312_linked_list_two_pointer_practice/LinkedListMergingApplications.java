package org.qinlinj.algoframework._300_classical_data_structure_algo._310_linked_list_algo._312_linked_list_two_pointer_practice;

/**
 * LINKED LIST MERGING APPLICATIONS
 * <p>
 * This class demonstrates how the concept of merging linked lists can be applied
 * to solve problems that don't explicitly involve linked lists.
 * <p>
 * KEY CONCEPT:
 * Merging sorted lists is a fundamental technique that extends beyond actual linked lists.
 * It can be applied to:
 * 1. Generate sequences with certain properties (like ugly numbers)
 * 2. Find specific elements in sorted matrices
 * 3. Find optimal pairs from multiple sorted arrays
 * <p>
 * CORE TECHNIQUES:
 * <p>
 * 1. Multi-Source Merging
 * - Treating different sources of sorted elements as "virtual linked lists"
 * - Using pointers/indices to track progress through each source
 * - Selecting the minimum element across all sources at each step
 * <p>
 * 2. Priority Queue Optimization
 * - Using a min-heap to efficiently select the next smallest element
 * - Adding "successor" elements to maintain the flow of elements
 * - Particularly useful when merging more than two sources
 * <p>
 * 3. Applying Linked List Merging Patterns
 * - Recognizing when a problem can be modeled as merging sorted sequences
 * - Adapting linked list merging algorithms to different data structures
 * - Using similar pointer/index manipulation techniques
 */
public class LinkedListMergingApplications {
    /**
     * LeetCode 264: Ugly Number II
     * <p>
     * An ugly number is a positive integer whose prime factors are limited to 2, 3, and 5.
     * This method finds the nth ugly number in the sequence.
     * <p>
     * Approach:
     * - Think of the ugly number sequence as a merge of three sequences:
     * - Sequence 1: Previous ugly numbers multiplied by 2
     * - Sequence 2: Previous ugly numbers multiplied by 3
     * - Sequence 3: Previous ugly numbers multiplied by 5
     * - Use three pointers to track progress through each sequence
     * <p>
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    public int nthUglyNumber(int n) {
        if (n <= 0) return 0;
        if (n == 1) return 1;

        // Three "pointers" for the three virtual linked lists
        int p2 = 1, p3 = 1, p5 = 1;

        // The values at each pointer position
        int product2 = 1, product3 = 1, product5 = 1;

        // Array to store the merged result (the ugly number sequence)
        int[] ugly = new int[n + 1];
        ugly[1] = 1; // First ugly number is 1

        // Result sequence pointer
        int p = 2;

        // Merge the three sequences until we have n ugly numbers
        while (p <= n) {
            // Get the minimum from the three virtual linked lists
            product2 = 2 * ugly[p2];
            product3 = 3 * ugly[p3];
            product5 = 5 * ugly[p5];

            int min = Math.min(Math.min(product2, product3), product5);
            ugly[p] = min;

            // Move the pointers forward in the appropriate sequences
            // Note: We might move multiple pointers if there are duplicates
            if (min == product2) p2++;
            if (min == product3) p3++;
            if (min == product5) p5++;

            p++;
        }

        return ugly[n];
    }

    /**
     * LeetCode 378: Kth Smallest Element in a Sorted Matrix
     * <p>
     * Given an n x n matrix where each row and column is sorted in ascending order,
     * find the kth smallest element in the matrix.
     * <p>
     * Approach:
     * - Treat each row as a sorted linked list
     * - Use a priority queue to merge these lists efficiently
     * - Track indices to generate "next" elements
     * <p>
     * Time Complexity: O(k log n) where n is the matrix dimension
     * Space Complexity: O(n) for the priority queue
     */
    public int kthSmallest(int[][] matrix, int k) {
        if (matrix == null || matrix.length == 0) return -1;

        int n = matrix.length;

        // Priority queue to hold elements in the form: [value, row, column]
        // Sorted by value in ascending order
        java.util.PriorityQueue<int[]> pq = new java.util.PriorityQueue<>(
                (a, b) -> a[0] - b[0]
        );

        // Initialize with the first element from each row
        for (int i = 0; i < n; i++) {
            pq.offer(new int[]{matrix[i][0], i, 0});
        }

        // Process until we find the kth element
        int result = -1;
        while (!pq.isEmpty() && k > 0) {
            int[] current = pq.poll();
            result = current[0];
            k--;

            // Add the next element from the same row if available
            int row = current[1];
            int col = current[2];
            if (col + 1 < n) {
                pq.offer(new int[]{matrix[row][col + 1], row, col + 1});
            }
        }

        return result;
    }
}
