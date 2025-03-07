package org.qinlinj.linear.algo.sort;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Quick Sort Algorithm Principle:
 * <p>
 * Quick sort is a highly efficient, comparison-based, divide-and-conquer sorting algorithm.
 * It works by selecting a 'pivot' element from the array and partitioning the other elements
 * into two sub-arrays according to whether they are less than or greater than the pivot.
 * The sub-arrays are then recursively sorted.
 * <p>
 * The algorithm works as follows:
 * 1. Choose a pivot element from the array (in this implementation, the last element is chosen)
 * 2. Partition the array so that:
 * - All elements less than the pivot come before the pivot
 * - All elements greater than the pivot come after the pivot
 * 3. Recursively apply the above steps to the sub-arrays formed on either side of the pivot
 * <p>
 * This implementation uses a variant of the Lomuto partition scheme, with two pointers:
 * - 'less' pointer scans through the array
 * - 'great' pointer marks the boundary between elements less than pivot and others
 * <p>
 * Time Complexity:
 * - Average case: O(n log n)
 * - Best case: O(n log n)
 * - Worst case: O(n²) when the array is already sorted or reverse sorted
 * <p>
 * Space Complexity: O(log n) for the recursion stack in average case
 * <p>
 * Advantages:
 * - Fast in practice
 * - In-place sorting algorithm (requires small additional space)
 * - Good locality of reference
 * <p>
 * Limitations:
 * - Unstable sorting algorithm (does not preserve the relative order of equal elements)
 * - Worst-case performance is O(n²)
 * - Not adaptive (performance doesn't improve for partially sorted arrays)
 */
public class IntegerArrayQuickSorter extends Sorter {

    /**
     * Sorts an ArrayList of Integers using QuickSort
     * <p>
     * Example: For ArrayList [6, 1, 34, 12]
     * 1. Convert to array: [6, 1, 34, 12]
     * 2. Apply quick sort recursively
     * 3. Convert back to ArrayList: [1, 6, 12, 34]
     *
     * @param data The ArrayList of Integers to be sorted
     */
    public void sort(ArrayList<Integer> data) {
        // Return early if array is null or has 0 or 1 element (already sorted)
        if (data == null || data.size() <= 1) {
            return;
        }

        // Convert ArrayList to array for efficient access
        Integer[] dataArr = data.toArray(new Integer[data.size()]);

        // Call recursive quick sort on the entire array
        sort(dataArr, 0, dataArr.length - 1);

        // Convert sorted array back to ArrayList
        data.clear();
        Collections.addAll(data, dataArr);

        // Example: For ArrayList [6, 1, 34, 12]
        // 1. Convert to array: [6, 1, 34, 12]
        // 2. After quick sort (detailed below), array becomes: [1, 6, 12, 34]
        // 3. Convert back to ArrayList: [1, 6, 12, 34]
    }

    /**
     * Recursive quick sort implementation
     * <p>
     * Example: For array [6, 1, 34, 12] with start=0, end=3
     *
     * @param data  The array to be sorted
     * @param start The starting index of the subarray
     * @param end   The ending index of the subarray
     */
    public void sort(Integer[] data, int start, int end) {
        // Base case: if start >= end, subarray has 0 or 1 element (already sorted)
        if (start >= end) return;

        // Partition the array and get the pivot position
        int j = partition(data, start, end);

        // Example step-by-step for [6, 1, 34, 12] with start=0, end=3:
        // 1. Choose pivot = 12 (data[end])
        // 2. After partition: [1, 6, 12, 34], pivot index j = 2
        // 3. Now recursively sort:
        //    - Left subarray [1, 6] (start=0, end=1)
        //    - Right subarray [34] (start=3, end=3)

        // Recursively sort elements before the pivot
        sort(data, start, j - 1);

        // Recursively sort elements after the pivot
        sort(data, j + 1, end);
    }

    /**
     * Partitions the array around a pivot (last element)
     * <p>
     * Example: For array [6, 1, 34, 12] with start=0, end=3
     * Pivot = 12 (data[end])
     *
     * @param data  The array to be partitioned
     * @param start The starting index of the subarray
     * @param end   The ending index of the subarray (pivot)
     * @return The index where the pivot ends up
     */
    private int partition(Integer[] data, int start, int end) {
        // Initialize pointers
        int less = start;   // Scanning pointer
        int great = start;  // Boundary pointer between elements < pivot and others

        // Select the last element as pivot
        // For our example: pivot = data[3] = 12

        // Scan through the array (excluding the pivot)
        while (less != end) {
            // If current element is less than pivot
            if (data[less] < data[end]) {
                // Swap current element with the first element >= pivot
                swap(data, less, great);
                // Move the boundary pointer
                great++;
            }
            // Move to the next element
            less++;

            // Example step-by-step for [6, 1, 34, 12]:
            // Initial: less=0, great=0, array=[6, 1, 34, 12]
            //
            // 1. less=0: data[0]=6 < pivot(12)
            //    - Swap data[0] with data[0] (no change)
            //    - Increment great to 1
            //    - Array remains [6, 1, 34, 12]
            //
            // 2. less=1: data[1]=1 < pivot(12)
            //    - Swap data[1] with data[1] (no change)
            //    - Increment great to 2
            //    - Array remains [6, 1, 34, 12]
            //
            // 3. less=2: data[2]=34 > pivot(12)
            //    - No swap, great remains 2
            //    - Array remains [6, 1, 34, 12]
            //
            // After loop: less=3, great=2, array=[6, 1, 34, 12]
        }

        // Place the pivot in its correct position
        swap(data, great, end);

        // Example: After final swap, array becomes [6, 1, 12, 34]
        // The pivot (12) is now at index 2 (great)

        // Return the position of the pivot
        return great;
    }
}