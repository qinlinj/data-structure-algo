package org.qinlinj.linear.algo.sort;

import java.util.Arrays;

public class ThreeWayQuickSorter extends Sorter {
    public static void main(String[] args) {
        int[] data = new int[]{12, 23, 36, 9, 24, 42, 1, 4, 100, 99, 34};
        new ThreeWayQuickSorter().sort(data);
        System.out.println(Arrays.toString(data));
    }

    public void sort(int[] data) {
        if (data == null || data.length <= 1) return;
        sort(data, 0, data.length - 1);
    }

    private void sort(int[] data, int lo, int hi) {
        if (lo >= hi) return;
        int mid = lo + (hi - lo) / 2;
        swap(data, mid, hi);
        int pivot = data[hi];

        int less = lo;
        int great = hi;

        int i = lo;
        while (i <= great) {
            if (data[i] < pivot) {
                swap(data, i, less);
                less++;
                i++;
            } else if (data[i] > pivot) {
                swap(data, i, great);
                great--;
            } else {
                i++;
            }
        }

        sort(data, lo, less - 1);
        sort(data, great + 1, hi);
    }


    public void sort_ql(int[] data) {
        if (data == null || data.length < 2) return;

        sort_ql(data, 0, data.length - 1);
    }

    public void sort_ql(int[] data, int start, int end) {
        if (start >= end) return;

        int[] partition = partition(data, start, end);

        sort_ql(data, start, partition[0] - 1);
        sort_ql(data, partition[1] + 1, end);
    }

    private int[] partition(int[] data, int start, int end) {
        int[] partition = new int[2];

        int less = start;
        int great = end;
        int i = less;

        int pivot = data[end];

        while (i <= great) {
            if (data[i] < pivot) {
                swap(data, i, less);
                less++;
                i++;
            } else if (data[i] > pivot) {
                swap(data, i, great);
                great--;
            } else {
                i++;
            }
        }
        partition[0] = less;
        partition[1] = great;
        return partition;
    }
}
