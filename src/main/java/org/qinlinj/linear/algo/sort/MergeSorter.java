package org.qinlinj.linear.algo.sort;

import java.util.Arrays;

/**
 * Merge Sort Algorithm Principle:
 * <p>
 * Merge sort is a divide-and-conquer sorting algorithm that works by:
 * 1. Recursively dividing the array into two halves until each subarray contains a single element
 * (an array with one element is considered sorted)
 * 2. Repeatedly merging these sorted subarrays to produce larger sorted subarrays
 * until the entire array is sorted
 * <p>
 * The key operation is the merge step, which combines two sorted subarrays into a single sorted array.
 * <p>
 * This implementation provides multiple variations:
 * - Top-down (recursive) approach with different merging strategies
 * - Bottom-up (iterative) approach that avoids recursion
 * - A hybrid approach that uses insertion sort for small subarrays
 * <p>
 * Time Complexity:
 * - Best case: O(n log n)
 * - Average case: O(n log n)
 * - Worst case: O(n log n)
 * <p>
 * Space Complexity: O(n) for the temporary array
 * <p>
 * Advantages:
 * - Stable sorting algorithm (preserves the relative order of equal elements)
 * - Guaranteed O(n log n) performance regardless of input data
 * - Works well for linked lists and external sorting
 * <p>
 * Limitations:
 * - Requires additional O(n) space for merging
 * - Not adaptive without modifications (doesn't take advantage of partially sorted input)
 * - Not in-place (uses additional memory)
 */
public class MergeSorter extends Sorter {
    public static void main(String[] args) {
        int[] data = new int[]{12, 23, 36, 9, 24, 42, 1, 4, 100, 99, 34};
        new MergeSorter().sort(data);
        System.out.println(Arrays.toString(data));
    }

    /**
     * Primary sorting method that initializes the recursive merge sort
     * <p>
     * Example: Initial array [12, 23, 36, 9, 24, 42, 1, 4, 100, 99, 34]
     *
     * @param data the array to be sorted
     */
    public void sort(int[] data) {
        // Return early if array is null or has less than 2 elements (already sorted)
        if (data == null || data.length < 2) {
            return;
        }

        // Create a temporary array of the same size for merging operations
        int[] tmp = new int[data.length];

        // Start the recursive merge sort process
        sort(data, 0, data.length - 1, tmp);

        // Final sorted array: [1, 4, 9, 12, 23, 24, 34, 36, 42, 99, 100]
    }

    /**
     * Recursive implementation of merge sort
     *
     * @param data  the array to be sorted
     * @param start the starting index of the subarray
     * @param end   the ending index of the subarray
     * @param tmp   temporary array used for merging
     */
    public void sort(int[] data, int start, int end, int[] tmp) {
        // Base case: if the subarray has 0 or 1 element, it's already sorted
        if (start >= end) {
            return;
        }

        // Find the middle point to divide the array into two halves
        // Using (start + (end - start) / 2) prevents integer overflow
        int mid = start + (end - start) / 2;

        // Example: For array [12, 23, 36, 9, 24, 42, 1, 4, 100, 99, 34] with start=0, end=10
        // mid = 0 + (10 - 0) / 2 = 5
        // This divides the array into [12, 23, 36, 9, 24, 42] and [1, 4, 100, 99, 34]

        // Recursively sort the first half
        sort(data, start, mid, tmp);

        // Recursively sort the second half
        sort(data, mid + 1, end, tmp);

        // Merge the sorted halves
        merge_ori2tmp(data, start, mid, end, tmp);

        // Example of recursive division:
        // 1. [12, 23, 36, 9, 24, 42, 1, 4, 100, 99, 34]
        // 2. Divide into [12, 23, 36, 9, 24, 42] and [1, 4, 100, 99, 34]
        // 3. Further divide [12, 23, 36, 9, 24, 42] into [12, 23, 36] and [9, 24, 42]
        // 4. And so on until single elements...
        // 5. Then merge back up, combining sorted subarrays
    }

    /**
     * Bottom-up (iterative) implementation of merge sort
     * Avoids recursion by starting with size=1 subarrays and doubling the size each iteration
     *
     * @param data the array to be sorted
     */
    public void sortBU(int[] data) {
        if (data == null || data.length <= 1) return;
        int len = data.length;
        int[] tmp = new int[len];

        // size represents the length of subarrays to be merged
        // Start with size=1 (individual elements) and double each time
        for (int size = 1; size < len; size += size) { // size grows as 1, 2, 4, 8, ...
            // Process pairs of subarrays of size 'size'
            for (int left = 0; left < len - size; left += 2 * size) {
                int mid = left + size - 1;
                // Ensure right doesn't exceed array bounds
                int right = Math.min(left + 2 * size - 1, len - 1);
                mergeArray(data, left, mid, right, tmp);
            }

            // Example for array [12, 23, 36, 9, 24, 42, 1, 4, 100, 99, 34]:
            // size=1: Merge pairs of single elements
            //   - Merge [12] and [23] → [12, 23]
            //   - Merge [36] and [9] → [9, 36]
            //   - Merge [24] and [42] → [24, 42]
            //   - Merge [1] and [4] → [1, 4]
            //   - Merge [100] and [99] → [99, 100]
            //   - [34] remains as is (no pair)
            //   Array after size=1: [12, 23, 9, 36, 24, 42, 1, 4, 99, 100, 34]

            // size=2: Merge pairs of size 2 subarrays
            //   - Merge [12, 23] and [9, 36] → [9, 12, 23, 36]
            //   - Merge [24, 42] and [1, 4] → [1, 4, 24, 42]
            //   - Merge [99, 100] and [34] → [34, 99, 100]
            //   Array after size=2: [9, 12, 23, 36, 1, 4, 24, 42, 34, 99, 100]

            // size=4: Merge pairs of size 4 subarrays
            //   - Merge [9, 12, 23, 36] and [1, 4, 24, 42] → [1, 4, 9, 12, 23, 24, 36, 42]
            //   - [34, 99, 100] remains as is (no pair)
            //   Array after size=4: [1, 4, 9, 12, 23, 24, 36, 42, 34, 99, 100]

            // size=8: Merge pairs of size 8 subarrays
            //   - Merge [1, 4, 9, 12, 23, 24, 36, 42] and [34, 99, 100]
            //   Final array: [1, 4, 9, 12, 23, 24, 34, 36, 42, 99, 100]
        }
    }

    /**
     * Merges two sorted subarrays of the original array using a temporary array
     * This is a helper method for sortBU
     *
     * @param data  the array containing the subarrays to be merged
     * @param start the starting index of the first subarray
     * @param mid   the ending index of the first subarray
     * @param end   the ending index of the second subarray
     * @param tmp   temporary array used for merging
     */
    private void mergeArray(int[] data, int start, int mid, int end, int[] tmp) {
        int i = start;      // Index for the first subarray
        int j = mid + 1;    // Index for the second subarray
        int tmpPos = start; // Index for the temporary array

        // Merge elements from both subarrays in sorted order
        while (i <= mid && j <= end) {
            if (data[i] > data[j]) {
                tmp[tmpPos++] = data[j++]; // Take the smaller element from the second subarray
            } else {
                tmp[tmpPos++] = data[i++]; // Take the smaller element from the first subarray
            }
        }

        // Copy any remaining elements from the first subarray
        while (i <= mid) {
            tmp[tmpPos++] = data[i++];
        }

        // Copy any remaining elements from the second subarray
        while (j <= end) {
            tmp[tmpPos++] = data[j++];
        }

        // Copy the merged result back to the original array
        for (tmpPos = start; tmpPos <= end; tmpPos++) {
            data[start++] = tmp[tmpPos];
        }

        // Example merging [9, 36] and [24, 42]:
        // 1. Compare 9 and 24: 9 < 24, so tmp[0] = 9, i = 1
        // 2. Compare 36 and 24: 36 > 24, so tmp[1] = 24, j = 1
        // 3. Compare 36 and 42: 36 < 42, so tmp[2] = 36, i = 2
        // 4. First subarray is exhausted, copy 42 from second subarray: tmp[3] = 42
        // 5. Merged result: [9, 24, 36, 42]
    }

    /**
     * Alternative implementation of merge sort using a different merging strategy
     *
     * @param data the array to be sorted
     */
    public void sort_ql(int[] data) {
        if (data == null || data.length < 2) {
            return;
        }
        sort_ql(data, 0, data.length - 1);
    }

    /**
     * Recursive helper method for the alternative merge sort implementation
     *
     * @param data  the array to be sorted
     * @param start the starting index of the subarray
     * @param end   the ending index of the subarray
     */
    public void sort_ql(int[] data, int start, int end) {
        if (start >= end) {
            return;
        }

        int mid = start + (end - start) / 2;
        sort_ql(data, start, mid);
        sort_ql(data, mid + 1, end);

        // Merge using insertion sort approach instead of the standard merging
        mergeArray_ql(data, start, mid, end);
    }

    /**
     * Merges two sorted subarrays using an insertion sort approach
     * This is a hybrid method that assumes the first subarray is already sorted
     * and inserts elements from the second subarray into their correct positions
     *
     * @param data  the array containing the subarrays to be merged
     * @param start the starting index of the first subarray
     * @param mid   the ending index of the first subarray
     * @param end   the ending index of the second subarray
     */
    private void mergeArray_ql(int[] data, int start, int mid, int end) {
        // For each element in the second subarray
        for (int i = mid + 1; i <= end; i++) {
            // Store the current element to be inserted
            int tmp = data[i];
            int j;

            // Find the correct position by shifting larger elements to the right
            for (j = i; j > mid && data[j - 1] > tmp; j--) {
                data[j] = data[j - 1];
            }

            // Insert the element in its correct position
            data[j] = tmp;

            // Example with subarrays [9, 23] and [12, 42]:
            // 1. i=2, tmp=12:
            //    - Compare 12 and 23: 12 < 23, shift 23 right
            //    - Compare 12 and 9: 12 > 9, stop shifting
            //    - Insert 12 at index 1: [9, 12, 23, 42]
            // 2. i=3, tmp=42:
            //    - Compare 42 and 23: 42 > 23, no shifting needed
            //    - Array remains [9, 12, 23, 42]
        }
    }

    /**
     * Merges two sorted subarrays using the original array and a temporary array
     * This method first copies the range to be merged to a temporary array,
     * then merges back into the original array
     *
     * @param data  the array containing the subarrays to be merged
     * @param left  the starting index of the first subarray
     * @param mid   the ending index of the first subarray
     * @param right the ending index of the second subarray
     * @param tmp   temporary array used for merging
     */
    private void merge_ori2tmp(int[] data, int left, int mid, int right, int[] tmp) {
        // Copy the range to be merged to the temporary array
        if (right + 1 - left >= 0) System.arraycopy(data, left, tmp, left, right + 1 - left);

        int i = left;     // Index for the first subarray in tmp
        int j = mid + 1;  // Index for the second subarray in tmp

        // Merge back into the original array
        for (int k = left; k <= right; k++) {
            if (i == mid + 1) {
                // First subarray is exhausted, take from second subarray
                data[k] = tmp[j++];
            } else if (j == right + 1) {
                // Second subarray is exhausted, take from first subarray
                data[k] = tmp[i++];
            } else if (tmp[i] <= tmp[j]) {
                // Current element in first subarray is smaller or equal
                data[k] = tmp[i++];
            } else {
                // Current element in second subarray is smaller
                // Note: Using data or tmp here is the same because when merging,
                // we're modifying data from left to right, so the unmodified part
                // of data is identical to the corresponding part in tmp
                data[k] = tmp[j++];
            }
        }

        // Example merging subarrays [9, 36] and [24, 42]:
        // 1. Copy to tmp: tmp[start...end] = [9, 36, 24, 42]
        // 2. Compare tmp[0]=9 and tmp[2]=24: 9 < 24, so data[0] = 9, i = 1
        // 3. Compare tmp[1]=36 and tmp[2]=24: 36 > 24, so data[1] = 24, j = 3
        // 4. Compare tmp[1]=36 and tmp[3]=42: 36 < 42, so data[2] = 36, i = 2
        // 5. First subarray is exhausted, copy tmp[3]=42: data[3] = 42
        // 6. Merged result: data = [9, 24, 36, 42]
    }
}