package org.qinlinj.practical.multlinemerging;

import java.util.*;

/**
 * Two Sorted Array Merger
 * <p>
 * Concept and Principles:
 * This class provides functionality to merge two already sorted integer arrays into a single
 * sorted array. It uses a standard two-pointer approach, which is an efficient algorithm
 * for merging sorted data structures.
 * <p>
 * Advantages:
 * 1. Time Complexity: O(m+n) where m and n are the lengths of the input arrays
 * 2. Space Complexity: O(m+n) for the result array
 * 3. Single-pass algorithm - only need to traverse each element once
 * 4. Stable merge - maintains the relative order of equal elements
 * 5. Works well for pre-sorted arrays of any size
 * <p>
 * Applications:
 * 1. Fundamental operation in merge sort algorithm
 * 2. Database operations (merging sorted records)
 * 3. External sorting (when data doesn't fit in memory)
 * 4. Combining sorted data from multiple sources
 * <p>
 * Visual Example:
 * <p>
 * Input:
 * a = [1, 2, 6, 9, 10]
 * b = [2, 3, 7, 11]
 * <p>
 * Merging process visualization:
 * <p>
 * i points to a[0] = 1, j points to b[0] = 2
 * 1 < 2, so take 1, result = [1], increment i
 * <p>
 * i points to a[1] = 2, j points to b[0] = 2
 * 2 == 2, so take 2 from array b, result = [1, 2], increment j
 * <p>
 * i points to a[1] = 2, j points to b[1] = 3
 * 2 < 3, so take 2, result = [1, 2, 2], increment i
 * <p>
 * i points to a[2] = 6, j points to b[1] = 3
 * 6 > 3, so take 3, result = [1, 2, 2, 3], increment j
 * <p>
 * ... and so on until both arrays are fully traversed.
 * <p>
 * Final result: [1, 2, 2, 3, 6, 7, 9, 10, 11]
 */
public class TwoSortedArrayMerger {
    /**
     * Main method demonstrating the usage of the merger.
     * <p>
     * Creates two sample sorted arrays and prints the merged result.
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        // Sample sorted input arrays
        int[] a = {1, 2, 6, 9, 10};
        int[] b = {2, 3, 7, 11};

        // Create an instance and call the merge method
        int[] res = new TwoSortedArrayMerger().mergeTwoSortedArray(a, b);

        // Print the result
        System.out.println(Arrays.toString(res));
        // Output: [1, 2, 2, 3, 6, 7, 9, 10, 11]
    }

    /**
     * Merges two sorted integer arrays into a single sorted array.
     * <p>
     * Algorithm:
     * 1. Create a result array with size equal to the sum of both input arrays
     * 2. Use two pointers (i, j) to track positions in both arrays
     * 3. Compare elements at current positions and add the smaller one to the result
     * 4. Advance the pointer for the array from which we took the element
     * 5. Handle cases where one array is exhausted by adding remaining elements
     * from the other array
     * <p>
     * Time Complexity: O(m+n) where m and n are the lengths of arrays a and b
     * Space Complexity: O(m+n) for the result array
     *
     * @param a first sorted array
     * @param b second sorted array
     * @return a new array containing all elements from a and b in sorted order
     */
    public int[] mergeTwoSortedArray(int[] a, int[] b) {
        // Get the lengths of both arrays
        int m = a.length, n = b.length;

        // Create result array with size equal to sum of both arrays
        int[] res = new int[m + n];

        // Pointers for arrays a and b
        int i = 0, j = 0;

        // Index to track position in result array
        int index = 0;

        // Traverse through both arrays and fill the result array
        for (int k = 0; k < res.length; k++) {
            if (i == m) {
                // If array a is exhausted, add element from array b
                res[index++] = b[j++];
            } else if (j == n) {
                // If array b is exhausted, add element from array a
                res[index++] = a[i++];
            } else if (a[i] < b[j]) {
                // If current element in a is smaller, add it to result
                res[index++] = a[i++];
            } else {
                // If current element in b is smaller or equal, add it to result
                // This makes the merge stable for equal elements
                res[index++] = b[j++];
            }
        }

        return res;
    }
}