package org.qinlinj.nonlinear.highlevel.heap;

import java.util.Random;

/**
 * Test class for validating the MaxHeap implementation.
 */
public class MaxHeapTest {
    /**
     * Main method to run the MaxHeap test.
     *
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        // Number of elements to test
        int n = 10000;
        Random random = new Random();

        // Create a new max heap
        MaxHeap<Integer> heap = new MaxHeap<>();

        // Add random integers to the heap
        for (int i = 0; i < n; i++) {
            heap.add(random.nextInt());
        }

        // Extract all elements from the heap
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = heap.removeMax();
        }

        // Verify that elements are extracted in descending order
        // This confirms the max heap property is maintained
        for (int i = 1; i < n; i++) {
            if (arr[i - 1] < arr[i]) {
                throw new RuntimeException("Error");
            }
        }

        // If no exception is thrown, the test passed
        System.out.println("Test MaxHeap Success");
    }
}
