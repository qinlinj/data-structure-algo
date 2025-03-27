package org.qinlinj.practical.multlinemerging;

import java.util.*;

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
