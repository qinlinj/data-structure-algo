package org.qinlinj.linear.algo.sort;

import java.util.Arrays;

public class ThreeWayQuickSorter extends Sorter {
    public static void main(String[] args) {
        int[] data = new int[]{12, 23, 36, 9, 24, 42, 1, 4, 100, 99, 34};
        new ThreeWayQuickSorter().sort(data);
        System.out.println(Arrays.toString(data));
    }

    public void sort(int[] data) {
        if (data == null || data.length < 2) return;

        sort(data, 0, data.length - 1);
    }

    public void sort(int[] data, int start, int end) {
        if (start >= end) return;

        int[] partition = partition(data, start, end);

        sort(data, start, partition[0] - 1);
        sort(data, partition[1] + 1, end);
    }

    private int[] partition(int[] data, int start, int end) {
        int[] partition = new int[2];

        int less = start;
        int great = end;
        int i = start;

        int pivot = data[end];

        while (i <= great) {
            if (data[i] < pivot) {
                swap(data, i, great);
                great--;
            } else if (data[i] > pivot) {
                swap(data, i, less);
                i++;
                less++;
            } else {
                i++;
            }
        }
        partition[0] = less - 1;
        partition[1] = great + 1;
        return partition;
    }
}
