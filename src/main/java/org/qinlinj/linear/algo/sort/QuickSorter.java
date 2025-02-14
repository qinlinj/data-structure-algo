package org.qinlinj.linear.algo.sort;

import java.util.Arrays;

public class QuickSorter extends Sorter {
    public static void main(String[] args) {
        int[] data = new int[]{12, 23, 36, 9, 24, 42, 1, 4, 100, 99, 34};
        new QuickSorter().sort_dm(data);
        System.out.println(Arrays.toString(data));
    }

    public void sort(int[] data) {
        if (data == null || data.length <= 1) {
            return;
        }
        sort(data, 0, data.length - 1);
    }

    public void sort(int[] data, int start, int end) {
        if (start >= end) return;
        int j = partition(data, start, end);

        sort(data, start, j - 1);
        sort(data, j + 1, end);
    }

    private int partition(int[] data, int start, int end) {
        int less = start;
        int great = start;
        while (less != end) {
            if (data[less] < data[end]) {
                swap(data, less, great);
                great++;
            }
            less++;
        }
        swap(data, great, end);
        return great;
    }

    //----------------------------------------

    public void sort_dm(int[] data) {
        if (data == null || data.length <= 1) {
            return;
        }
        sort_dm(data, 0, data.length - 1);
    }

    public void sort_dm(int[] data, int start, int end) {
        if (start >= end) return;
        int j = partition_dm(data, start, end);

        sort_dm(data, start, j - 1);
        sort_dm(data, j + 1, end);
    }

    private int partition_dm(int[] data, int lo, int hi) {
        int pivot = data[hi];
        int less = lo;
        int great = lo;
        for (; great <= hi - 1; great++) {
            if (data[great] < pivot) {
                swap(data, less, great);
                less++;
            }
        }
        swap(data, less, hi);
        return less;
    }
}
