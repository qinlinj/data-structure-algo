package org.qinlinj.linear.algo.sort;

import java.util.Arrays;

/**
 * Insertion Sort Algorithm Principle:
 * <p>
 * Insertion sort is a simple comparison-based sorting algorithm that builds the final sorted array
 * one element at a time. It works by taking elements from the unsorted part of the array and inserting
 * them into their correct positions in the sorted part of the array.
 * <p>
 * The algorithm works as follows:
 * 1. The array is virtually split into a sorted part (initially just the first element) and an unsorted part.
 * 2. We iterate through the unsorted part, taking one element at a time.
 * 3. For each element, we find its correct position in the sorted part by shifting larger elements to the right.
 * 4. We insert the element into its correct position in the sorted part.
 * 5. Repeat steps 2-4 until all elements are sorted.
 * <p>
 * Time complexity:
 * - Best case: O(n) when the array is already sorted
 * - Average case: O(n²)
 * - Worst case: O(n²) when the array is sorted in reverse order
 * <p>
 * Space complexity: O(1) as it uses only a constant amount of extra space
 * <p>
 * Advantages:
 * - Simple implementation
 * - Efficient for small data sets
 * - Adaptive (efficient for nearly sorted data)
 * - Stable sorting algorithm
 * - In-place sorting algorithm
 * <p>
 * Limitations:
 * - Inefficient for large data sets compared to more advanced algorithms like quicksort or merge sort
 */
public class InsertionSorter extends Sorter {
    public static void main(String[] args) {
        int[] data = new int[]{12, 23, 36, 9, 24, 42, 1, 4, 100, 99, 34};
        new InsertionSorter().sort(data);
        System.out.println(Arrays.toString(data));
    }

    /**
     * Sort an array using the insertion sort algorithm
     * This implementation uses the shifting approach which is more efficient than swapping
     * <p>
     * Example: Initial array [12, 23, 36, 9, 24, 42, 1, 4, 100, 99, 34]
     *
     * @param data the array to be sorted
     */
    public void sort(int[] data) {
        // Return early if array is null or has 0 or 1 element (already sorted)
        if (data == null || data.length <= 1) return;

        // Iterate through the array starting from the second element (index 1)
        // The first element (index 0) is considered already sorted
        for (int i = 1; i < data.length; i++) {
            // Store the current element that needs to be inserted
            int tmp = data[i];
            int j;

            // Find the correct position for the current element in the sorted part
            // Shift elements that are greater than the current element to the right
            for (j = i; j > 0 && tmp < data[j - 1]; j--) {
                data[j] = data[j - 1];
            }

            // Insert the current element into its correct position
            data[j] = tmp;

            // Example: Step-by-step execution for the initial array [12, 23, 36, 9, 24, 42, 1, 4, 100, 99, 34]
            // i=1: tmp=23, j=1, 23>12, no shifts needed, array remains [12, 23, 36, 9, 24, 42, 1, 4, 100, 99, 34]
            // i=2: tmp=36, j=2, 36>23, no shifts needed, array remains [12, 23, 36, 9, 24, 42, 1, 4, 100, 99, 34]
            // i=3: tmp=9, j=3
            //      - 9<36, shift 36 right: [12, 23, 36, 36, 24, 42, 1, 4, 100, 99, 34], j=2
            //      - 9<23, shift 23 right: [12, 23, 23, 36, 24, 42, 1, 4, 100, 99, 34], j=1
            //      - 9<12, shift 12 right: [12, 12, 23, 36, 24, 42, 1, 4, 100, 99, 34], j=0
            //      - Insert 9 at j=0: [9, 12, 23, 36, 24, 42, 1, 4, 100, 99, 34]
            // i=4: tmp=24, j=4
            //      - 24<36, shift 36 right: [9, 12, 23, 36, 36, 42, 1, 4, 100, 99, 34], j=3
            //      - 24>23, no more shifts needed
            //      - Insert 24 at j=3: [9, 12, 23, 24, 36, 42, 1, 4, 100, 99, 34]
            // i=5: tmp=42, j=5, 42>36, no shifts needed, array becomes [9, 12, 23, 24, 36, 42, 1, 4, 100, 99, 34]
            // i=6: tmp=1, j=6
            //      - Multiple shifts occur as 1 is smaller than all sorted elements
            //      - After shifts and insertion: [1, 9, 12, 23, 24, 36, 42, 4, 100, 99, 34]
            // The process continues similarly for remaining elements
            // Final sorted array: [1, 4, 9, 12, 23, 24, 34, 36, 42, 99, 100]
        }
    }

    /**
     * Alternative implementation of insertion sort (kept for reference)
     * This version has a bug in the inner loop where tmp is assigned to data[j-1] incorrectly
     *
     * @param data the array to be sorted
     */
    public void sort_ql(int[] data) {
        if (data == null || data.length <= 1) return;
        int tmp;
        for (int i = 1; i < data.length; i++) {
            tmp = data[i];
            for (int j = i; j > 0; j--) {
                if (data[j] < data[j - 1]) {
                    data[j] = data[j - 1];
                } else {
                    break;
                }
                data[j - 1] = tmp;
            }
        }
    }

    /**
     * Another alternative implementation of insertion sort using swap operations
     * This is less efficient than the shifting approach as it requires multiple swap operations
     *
     * @param data the array to be sorted
     */
    public void sort_swap(int[] data) {
        if (data == null || data.length <= 1) return;
        for (int i = 1; i < data.length; i++) {
            for (int j = i; j > 0; j--) {
                if (data[j] < data[j - 1]) {
                    swap(data, j, j - 1);
                } else {
                    break;
                }
            }
        }
    }
}