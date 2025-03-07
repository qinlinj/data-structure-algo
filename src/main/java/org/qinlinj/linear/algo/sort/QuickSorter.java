package org.qinlinj.linear.algo.sort;

import java.util.Arrays;

/**
 * Quick Sort Algorithm Principle:
 * <p>
 * Quick sort is a highly efficient, comparison-based, divide-and-conquer sorting algorithm.
 * It works by selecting a 'pivot' element from the array and partitioning the other elements
 * into two sub-arrays according to whether they are less than or greater than the pivot.
 * The sub-arrays are then recursively sorted.
 * <p>
 * This implementation provides two different partition strategies:
 * 1. The first strategy uses a dual-pointer approach where:
 * - 'less' pointer scans through the array
 * - 'great' pointer marks the boundary between elements less than pivot and others
 * <p>
 * 2. The second strategy (Dutch flag partition variant):
 * - Uses a pivot value rather than a position
 * - Separate pointers track elements known to be less than pivot
 * <p>
 * Time Complexity:
 * - Average case: O(n log n)
 * - Best case: O(n log n)
 * - Worst case: O(n²) when the array is already sorted or reverse sorted
 * <p>
 * Space Complexity: O(log n) for the recursion stack in average case
 * <p>
 * Advantages:
 * - Fast in practice with good cache locality
 * - In-place sorting algorithm (requires small additional space)
 * - Works well with virtual memory
 * <p>
 * Limitations:
 * - Unstable sorting algorithm (does not preserve the relative order of equal elements)
 * - Worst-case performance is O(n²)
 * - Not adaptive (performance doesn't improve for partially sorted arrays)
 */
public class QuickSorter extends Sorter {
    public static void main(String[] args) {
        int[] data = new int[]{12, 23, 36, 9, 24, 42, 1, 4, 100, 99, 34};
        new QuickSorter().sort_dm(data);
        System.out.println(Arrays.toString(data));
    }

    /**
     * Primary sorting method that initializes the recursive quick sort
     * <p>
     * Example: Initial array [12, 23, 36, 9, 24, 42, 1, 4, 100, 99, 34]
     *
     * @param data the array to be sorted
     */
    public void sort(int[] data) {
        // Return early if array is null or has 0 or 1 element (already sorted)
        if (data == null || data.length <= 1) {
            return;
        }
        // Start the recursive quick sort process
        sort(data, 0, data.length - 1);

        // Final sorted array: [1, 4, 9, 12, 23, 24, 34, 36, 42, 99, 100]
    }

    /**
     * Recursive implementation of quick sort using the first partition strategy
     *
     * @param data  the array to be sorted
     * @param start the starting index of the subarray
     * @param end   the ending index of the subarray
     */
    public void sort(int[] data, int start, int end) {
        // Base case: if the subarray has 0 or 1 element, it's already sorted
        if (start >= end) return;

        // Partition the array and get the pivot position
        int j = partition(data, start, end);

        // Example: For array [12, 23, 36, 9, 24, 42, 1, 4, 100, 99, 34] with start=0, end=10
        // After first partition with pivot=34, array might become:
        // [12, 23, 9, 1, 4, 24, 34, 42, 100, 99, 36], with j=6 (pivot at index 6)

        // Recursively sort elements before the pivot
        sort(data, start, j - 1);

        // Recursively sort elements after the pivot
        sort(data, j + 1, end);
    }

    /**
     * Partitions the array around a pivot (last element) using the first strategy
     *
     * @param data  the array to be partitioned
     * @param start the starting index of the subarray
     * @param end   the ending index of the subarray (pivot)
     * @return the index where the pivot ends up
     */
    private int partition(int[] data, int start, int end) {
        // Initialize pointers
        int less = start;   // Scanning pointer
        int great = start;  // Boundary pointer between elements < pivot and others

        // Select the last element as pivot
        // For our example: pivot = data[10] = 34

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

            // Example step-by-step for [12, 23, 36, 9, 24, 42, 1, 4, 100, 99, 34]:
            // Initial: less=0, great=0, pivot=34
            //
            // 1. less=0: data[0]=12 < pivot(34)
            //    - Swap data[0] with data[0] (no change)
            //    - Increment great to 1
            //    - Array remains [12, 23, 36, 9, 24, 42, 1, 4, 100, 99, 34]
            //
            // 2. less=1: data[1]=23 < pivot(34)
            //    - Swap data[1] with data[1] (no change)
            //    - Increment great to 2
            //    - Array remains [12, 23, 36, 9, 24, 42, 1, 4, 100, 99, 34]
            //
            // 3. less=2: data[2]=36 > pivot(34)
            //    - No swap, great remains 2
            //    - Array remains [12, 23, 36, 9, 24, 42, 1, 4, 100, 99, 34]
            //
            // 4. less=3: data[3]=9 < pivot(34)
            //    - Swap data[3] with data[2]: [12, 23, 9, 36, 24, 42, 1, 4, 100, 99, 34]
            //    - Increment great to 3
            //
            // This process continues...
            // After scanning the entire array, it might look like:
            // [12, 23, 9, 1, 4, 24, 36, 42, 100, 99, 34] with less=10, great=6
        }

        // Place the pivot in its correct position
        swap(data, great, end);

        // Example: After final swap with pivot: [12, 23, 9, 1, 4, 24, 34, 42, 100, 99, 36]
        // The pivot (34) is now at index 6 (great)

        // Return the position of the pivot
        return great;
    }

    //----------------------------------------
    // Dutch flag partition variant implementation
    //----------------------------------------

    /**
     * Primary sorting method that initializes the recursive quick sort
     * using the Dutch flag partition variant
     *
     * @param data the array to be sorted
     */
    public void sort_dm(int[] data) {
        if (data == null || data.length <= 1) {
            return;
        }
        sort_dm(data, 0, data.length - 1);
    }

    /**
     * Recursive implementation of quick sort using the Dutch flag partition variant
     *
     * @param data  the array to be sorted
     * @param start the starting index of the subarray
     * @param end   the ending index of the subarray
     */
    public void sort_dm(int[] data, int start, int end) {
        if (start >= end) return;

        // Partition the array and get the pivot position
        int j = partition_dm(data, start, end);

        // Example: For array [12, 23, 36, 9, 24, 42, 1, 4, 100, 99, 34] with start=0, end=10
        // After partition with pivot=34, array might become:
        // [12, 23, 9, 1, 4, 24, 34, 36, 42, 100, 99], with j=6

        // Recursively sort elements before the pivot
        sort_dm(data, start, j - 1);

        // Recursively sort elements after the pivot
        sort_dm(data, j + 1, end);
    }

    /**
     * Partitions the array around a pivot (last element) using the Dutch flag variant
     *
     * @param data the array to be partitioned
     * @param lo   the starting index of the subarray
     * @param hi   the ending index of the subarray (pivot)
     * @return the index where the pivot ends up
     */
    private int partition_dm(int[] data, int lo, int hi) {
        // Select the last element as pivot value
        int pivot = data[hi];

        // Initialize pointers
        int less = lo;     // Elements less than pivot will be before this index
        int great = lo;    // Current scanning position

        // Scan through the array (excluding the pivot)
        for (; great <= hi - 1; great++) {
            // If current element is less than pivot
            if (data[great] < pivot) {
                // Swap current element with the first element >= pivot
                swap(data, less, great);
                // Move the boundary pointer
                less++;
            }

            // Example step-by-step for [12, 23, 36, 9, 24, 42, 1, 4, 100, 99, 34]:
            // Initial: less=0, great=0, pivot=34
            //
            // 1. great=0: data[0]=12 < pivot(34)
            //    - Swap data[0] with data[0] (no change)
            //    - Increment less to 1
            //    - Array remains [12, 23, 36, 9, 24, 42, 1, 4, 100, 99, 34]
            //
            // 2. great=1: data[1]=23 < pivot(34)
            //    - Swap data[1] with data[1] (no change)
            //    - Increment less to 2
            //    - Array remains [12, 23, 36, 9, 24, 42, 1, 4, 100, 99, 34]
            //
            // 3. great=2: data[2]=36 > pivot(34)
            //    - No swap, less remains 2
            //    - Array remains [12, 23, 36, 9, 24, 42, 1, 4, 100, 99, 34]
            //
            // 4. great=3: data[3]=9 < pivot(34)
            //    - Swap data[3] with data[2]: [12, 23, 9, 36, 24, 42, 1, 4, 100, 99, 34]
            //    - Increment less to 3
            //
            // This process continues...
            // After scanning the entire array, it might look like:
            // [12, 23, 9, 24, 1, 4, 36, 42, 100, 99, 34] with less=6, great=10
        }

        // Place the pivot in its correct position
        swap(data, less, hi);

        // Example: After final swap with pivot: [12, 23, 9, 24, 1, 4, 34, 42, 100, 99, 36]
        // The pivot (34) is now at index 6 (less)

        // Return the position of the pivot
        return less;
    }
}