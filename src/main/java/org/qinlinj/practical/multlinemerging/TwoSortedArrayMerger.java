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
    public static void main(String[] args) {
        int[] a = {1, 2, 6, 9, 10};
        int[] b = {2, 3, 7, 11};
        int[] res = new TwoSortedArrayMerger().mergeTwoSortedArray(a, b);
        System.out.println(Arrays.toString(res));
    }

    public int[] mergeTwoSortedArray(int[] a, int[] b) {
        int m = a.length, n = b.length;
        int[] res = new int[m + n];
        int i = 0, j = 0;
        int index = 0;
        for (int k = 0; k < res.length; k++) {
            if (i == m) {
                res[index++] = b[j++];
            } else if (j == n) {
                res[index++] = a[i++];
            } else if (a[i] < b[j]) {
                res[index++] = a[i++];
            } else {
                res[index++] = b[j++];
            }
        }
        return res;
    }
}
