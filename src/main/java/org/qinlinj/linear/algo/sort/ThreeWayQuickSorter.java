package org.qinlinj.linear.algo.sort;

import java.util.Arrays;

/**
 * Three-Way QuickSort Implementation
 * <p>
 * Algorithm Principle:
 * Three-way QuickSort is a variation of the standard QuickSort algorithm optimized for
 * arrays with many duplicate elements. It partitions the array into three parts:
 * elements less than the pivot, elements equal to the pivot, and elements greater than the pivot.
 * <p>
 * The algorithm works as follows:
 * 1. Select a pivot element
 * 2. Partition the array into three sections: less than, equal to, and greater than the pivot
 * 3. Recursively sort the "less than" and "greater than" sections
 * 4. Elements equal to the pivot are already in their final sorted positions
 * <p>
 * Time Complexity:
 * - Best case: O(n log n)
 * - Average case: O(n log n)
 * - Worst case: O(n²) (rare with good pivot selection)
 * - With many duplicates: O(n) (advantage over standard QuickSort)
 * <p>
 * Space Complexity:
 * - O(log n) for recursion stack in average case
 * - O(n) in worst case (unlikely with good pivot selection)
 * <p>
 * Advantages:
 * - More efficient than standard QuickSort for arrays with many duplicates
 * - Avoids excessive swapping of equal elements
 * - Performs well on partially sorted arrays with proper pivot selection
 * - In-place sorting (doesn't require additional memory for the sorting itself)
 * <p>
 * Limitations:
 * - Not stable (may change the relative order of equal elements)
 * - Performance depends on good pivot selection
 * - Recursive implementation can cause stack overflow for very large arrays
 */
public class ThreeWayQuickSorter extends Sorter {
    /**
     * Main method to demonstrate Three-Way QuickSort
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        // Using a simple example array for demonstration
        int[] data = new int[]{4, 2, 4, 7, 1, 4, 8, 3, 6, 4, 5};
        System.out.println("Original array: " + Arrays.toString(data));
        new ThreeWayQuickSorter().sort(data);
        System.out.println("Sorted array: " + Arrays.toString(data));
    }

    /**
     * Public sort method - entry point for the sorting algorithm
     *
     * @param data array to be sorted
     */
    public void sort(int[] data) {
        if (data == null || data.length <= 1) return;  // Nothing to sort
        sort(data, 0, data.length - 1);               // Call the recursive sorting method
    }

    /**
     * Recursive Three-Way QuickSort implementation with detailed execution trace
     * <p>
     * We'll trace through sorting this example array: [4, 2, 4, 7, 1, 4, 8, 3, 6, 4, 5]
     *
     * @param data array to be sorted
     * @param lo   start index of the subarray to sort
     * @param hi   end index of the subarray to sort
     */
    private void sort(int[] data, int lo, int hi) {
        // Base case: if subarray has 0 or 1 elements, it's already sorted
        if (lo >= hi) return;

        // First recursive call: sort([4, 2, 4, 7, 1, 4, 8, 3, 6, 4, 5], 0, 10)

        // Step 1: Select a pivot (using middle element to avoid worst-case behavior)
        int mid = lo + (hi - lo) / 2;   // mid = 0 + (10 - 0) / 2 = 5
        swap(data, mid, hi);            // Swap middle element with the last element
        // Array becomes: [4, 2, 4, 7, 1, 5, 8, 3, 6, 4, 4]
        // Using pivot = 4 (previously at mid=5, now at hi=10)
        int pivot = data[hi];           // pivot = 4

        // Step 2: Initialize pointers for the three-way partition
        int less = lo;      // Points to the first element >= pivot, initially less = 0
        int great = hi;     // Points to the last element <= pivot, initially great = 10
        int i = lo;         // Current element being examined, initially i = 0

        // Step 3: Partition the array into three sections
        while (i <= great) {
            // First iteration: i=0, data[0]=4
            // Is 4 < 4? No
            // Is 4 > 4? No
            // 4 == 4, so increment i to 1
            // Array unchanged: [4, 2, 4, 7, 1, 5, 8, 3, 6, 4, 4]

            // Second iteration: i=1, data[1]=2
            // Is 2 < 4? Yes
            // Swap data[1] with data[0]: [2, 4, 4, 7, 1, 5, 8, 3, 6, 4, 4]
            // Increment less to 1, i to 2

            // Third iteration: i=2, data[2]=4
            // Is 4 < 4? No
            // Is 4 > 4? No
            // 4 == 4, so increment i to 3
            // Array unchanged: [2, 4, 4, 7, 1, 5, 8, 3, 6, 4, 4]

            // Fourth iteration: i=3, data[3]=7
            // Is 7 < 4? No
            // Is 7 > 4? Yes
            // Swap data[3] with data[10]: [2, 4, 4, 4, 1, 5, 8, 3, 6, 4, 7]
            // Decrement great to 9
            // i stays at 3

            // Fifth iteration: i=3, data[3]=4
            // Is 4 < 4? No
            // Is 4 > 4? No
            // 4 == 4, so increment i to 4
            // Array unchanged: [2, 4, 4, 4, 1, 5, 8, 3, 6, 4, 7]

            // Sixth iteration: i=4, data[4]=1
            // Is 1 < 4? Yes
            // Swap data[4] with data[1]: [2, 1, 4, 4, 4, 5, 8, 3, 6, 4, 7]
            // Increment less to 2, i to 5

            // ...continuing the partitioning process...
            // After all iterations, the array becomes:
            // [1, 2, 3, 4, 4, 4, 4, 5, 6, 7, 8]
            //        ^     ^
            //      less   great
            // Where elements from less to great are all equal to the pivot (4)

            if (data[i] < pivot) {
                swap(data, i, less);
                less++;
                i++;
            } else if (data[i] > pivot) {
                swap(data, i, great);
                great--;
                // Note: i is not incremented here because we need to check the newly swapped element
            } else {
                // Element is equal to pivot
                i++;
            }
        }

        // At this point, the array is partitioned into three sections:
        // [elements < pivot | elements == pivot | elements > pivot]
        //  lo ... less-1      less ... great     great+1 ... hi

        // Step 4: Recursively sort the "less than" section
        sort(data, lo, less - 1);

        // Step 5: Recursively sort the "greater than" section
        sort(data, great + 1, hi);

        // Note: We don't need to sort the middle section because all elements are equal
    }

    /**
     * Alternative implementation of Three-Way QuickSort
     * This version uses a separate partition method that returns the bounds
     * of the "equal to pivot" section.
     *
     * @param data array to be sorted
     */
    public void sort_ql(int[] data) {
        if (data == null || data.length < 2) return;  // Nothing to sort

        sort_ql(data, 0, data.length - 1);           // Call the recursive sorting method
    }

    /**
     * Recursive implementation of the alternative Three-Way QuickSort
     *
     * @param data  array to be sorted
     * @param start start index of the subarray to sort
     * @param end   end index of the subarray to sort
     */
    public void sort_ql(int[] data, int start, int end) {
        if (start >= end) return;  // Base case: subarray with 0 or 1 elements

        // Step 1: Partition the array and get the bounds of the "equal to pivot" section
        int[] partition = partition(data, start, end);
        // partition[0] = first index of "equal to pivot" section
        // partition[1] = last index of "equal to pivot" section

        // Step 2: Recursively sort the "less than" section
        sort_ql(data, start, partition[0] - 1);

        // Step 3: Recursively sort the "greater than" section
        sort_ql(data, partition[1] + 1, end);
    }

    /**
     * Partition method for the alternative Three-Way QuickSort
     * <p>
     * Using the same example: [4, 2, 4, 7, 1, 4, 8, 3, 6, 4, 5]
     *
     * @param data  array to be partitioned
     * @param start start index of the subarray
     * @param end   end index of the subarray
     * @return an array containing the bounds of the "equal to pivot" section
     */
    private int[] partition(int[] data, int start, int end) {
        int[] partition = new int[2];  // To store the bounds of the "equal to pivot" section

        // Initialize pointers
        int less = start;    // Points to the first element >= pivot
        int great = end;     // Points to the last element to process
        int i = less;        // Current element being examined

        // Choose the last element as the pivot
        int pivot = data[end];  // pivot = 5 for our example's first call

        // Step-by-step partitioning:
        // Initial array: [4, 2, 4, 7, 1, 4, 8, 3, 6, 4, 5]
        //                 ^                             ^
        //               less                          great
        //                 i

        // First iteration: i=0, data[0]=4
        // Is 4 < 5? Yes
        // Swap data[0] with data[0] (no change)
        // Increment less to 1, i to 1
        // Array unchanged: [4, 2, 4, 7, 1, 4, 8, 3, 6, 4, 5]

        // Second iteration: i=1, data[1]=2
        // Is 2 < 5? Yes
        // Swap data[1] with data[1] (no change)
        // Increment less to 2, i to 2
        // Array unchanged: [4, 2, 4, 7, 1, 4, 8, 3, 6, 4, 5]

        // ...continuing similar logic...

        // After all iterations, the array is partitioned:
        // [1, 2, 3, 4, 4, 4, 4, 5, 6, 7, 8]
        //                    ^  ^
        //                  less great
        // Where all elements from less to great are equal to the pivot
        while (i <= great) {
            if (data[i] < pivot) {
                swap(data, i, less);
                less++;
                i++;
            } else if (data[i] > pivot) {
                swap(data, i, great);
                great--;
                // Note: i is not incremented because we need to examine the new element at i
            } else {
                // Element is equal to pivot
                i++;
            }
        }

        // Store the bounds of the "equal to pivot" section
        partition[0] = less;     // First element equal to pivot
        partition[1] = great;    // Last element equal to pivot

        return partition;
    }
}