package org.qinlinj.linear.algo.sort;

import java.util.Arrays;

/**
 * Selection Sort Algorithm Principle:
 * <p>
 * Selection sort is a simple comparison-based sorting algorithm that works by dividing the input
 * array into two parts: a sorted subarray and an unsorted subarray. The algorithm repeatedly
 * finds the minimum (or maximum) element from the unsorted subarray and moves it to the
 * beginning (or end) of the sorted subarray.
 * <p>
 * The algorithm works as follows:
 * 1. Divide the array into a sorted part (initially empty) and an unsorted part (initially the whole array)
 * 2. Find the smallest element in the unsorted part
 * 3. Swap this element with the first element of the unsorted part
 * 4. Expand the sorted part to include this newly sorted element
 * 5. Repeat steps 2-4 until the entire array is sorted
 * <p>
 * This implementation provides two variants:
 * 1. The optimized version that finds the minimum element first, then performs a single swap
 * 2. An alternative version that swaps elements whenever a smaller element is found
 * <p>
 * Time Complexity:
 * - Best case: O(n²)
 * - Average case: O(n²)
 * - Worst case: O(n²)
 * <p>
 * Space Complexity: O(1) as it sorts in-place with only a constant amount of extra memory
 * <p>
 * Advantages:
 * - Simple implementation
 * - Performs well on small arrays
 * - Minimizes the number of swaps (optimized version)
 * - In-place sorting algorithm
 * <p>
 * Limitations:
 * - Inefficient for large arrays due to quadratic time complexity
 * - Not stable (relative order of equal elements may change)
 * - Not adaptive (performance doesn't improve if array is partially sorted)
 */
public class SelectionSorter extends Sorter {
    public static void main(String[] args) {
        int[] data = new int[]{12, 23, 36, 9, 24, 42, 1, 4, 100, 99, 34};
        new SelectionSorter().sort(data);
        System.out.println(Arrays.toString(data));
    }

    /**
     * Sorts an array using the optimized selection sort algorithm
     * This implementation finds the minimum element first, then performs a single swap per iteration
     * <p>
     * Example: Initial array [12, 23, 36, 9, 24, 42, 1, 4, 100, 99, 34]
     *
     * @param data the array to be sorted
     */
    public void sort(int[] data) {
        // Return early if array is null or has 0 or 1 element (already sorted)
        if (data == null || data.length <= 1) return;

        // Iterate through the array
        for (int i = 0; i < data.length; i++) {
            // Assume the current position has the minimum value
            int swapIndex = i;

            // Find the position of the minimum element in the unsorted part
            for (int j = i; j < data.length; j++) {
                // If a smaller element is found, update swapIndex
                if (data[swapIndex] > data[j]) {
                    swapIndex = j;
                }
            }

            // Swap the minimum element with the first element of the unsorted part
            swap(data, swapIndex, i);

            // Example step-by-step execution for [12, 23, 36, 9, 24, 42, 1, 4, 100, 99, 34]:
            // i=0: unsorted=[12, 23, 36, 9, 24, 42, 1, 4, 100, 99, 34], sorted=[]
            //      Find minimum: 1 at position 6
            //      Swap 12 and 1: [1, 23, 36, 9, 24, 42, 12, 4, 100, 99, 34]
            //      After iteration: sorted=[1], unsorted=[23, 36, 9, 24, 42, 12, 4, 100, 99, 34]
            //
            // i=1: unsorted=[23, 36, 9, 24, 42, 12, 4, 100, 99, 34], sorted=[1]
            //      Find minimum: 4 at position 7
            //      Swap 23 and 4: [1, 4, 36, 9, 24, 42, 12, 23, 100, 99, 34]
            //      After iteration: sorted=[1, 4], unsorted=[36, 9, 24, 42, 12, 23, 100, 99, 34]
            //
            // i=2: unsorted=[36, 9, 24, 42, 12, 23, 100, 99, 34], sorted=[1, 4]
            //      Find minimum: 9 at position 3
            //      Swap 36 and 9: [1, 4, 9, 36, 24, 42, 12, 23, 100, 99, 34]
            //      After iteration: sorted=[1, 4, 9], unsorted=[36, 24, 42, 12, 23, 100, 99, 34]
            //
            // i=3: unsorted=[36, 24, 42, 12, 23, 100, 99, 34], sorted=[1, 4, 9]
            //      Find minimum: 12 at position 6
            //      Swap 36 and 12: [1, 4, 9, 12, 24, 42, 36, 23, 100, 99, 34]
            //      After iteration: sorted=[1, 4, 9, 12], unsorted=[24, 42, 36, 23, 100, 99, 34]
            //
            // This process continues for the remaining iterations...
            // Final sorted array: [1, 4, 9, 12, 23, 24, 34, 36, 42, 99, 100]
        }
    }

    /**
     * Alternative implementation of selection sort that swaps elements whenever a smaller element is found
     * This implementation performs more swaps than the optimized version
     *
     * @param data the array to be sorted
     */
    public void sort_swap(int[] data) {
        // Return early if array is null or has 0 or 1 element (already sorted)
        if (data == null || data.length <= 1) return;

        // Iterate through the array
        for (int i = 0; i < data.length; i++) {
            // For each position in the unsorted part
            for (int j = i; j < data.length; j++) {
                // If a smaller element is found, swap immediately
                if (data[i] > data[j]) {
                    swap(data, i, j);
                }
            }

            // Example step-by-step execution for [12, 23, 36, 9, 24, 42, 1, 4, 100, 99, 34]:
            // i=0, j=0: No swap (same element)
            // i=0, j=1: 12 < 23, no swap
            // i=0, j=2: 12 < 36, no swap
            // i=0, j=3: 12 > 9, swap: [9, 23, 36, 12, 24, 42, 1, 4, 100, 99, 34]
            // i=0, j=4: 9 < 24, no swap
            // i=0, j=5: 9 < 42, no swap
            // i=0, j=6: 9 > 1, swap: [1, 23, 36, 12, 24, 42, 9, 4, 100, 99, 34]
            // i=0, j=7: 1 < 4, no swap
            // ... and so on
            //
            // Note: This implementation is less efficient as it performs more swaps,
            // which can be expensive operations, especially for large objects.
            // The optimized version above performs at most one swap per outer loop iteration.
        }

        // Final sorted array: [1, 4, 9, 12, 23, 24, 34, 36, 42, 99, 100]
    }
}