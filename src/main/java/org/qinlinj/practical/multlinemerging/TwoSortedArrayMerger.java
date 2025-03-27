package org.qinlinj.practical.multlinemerging;

public class TwoSortedArrayMerger {
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
