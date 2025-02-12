package org.qinlinj.linear.algo.sort;

import java.util.Arrays;

public class SelectionSorter extends Sorter {
    public static void main(String[] args) {
        int[] data = new int[]{12, 23, 36, 9, 24, 42, 1, 4, 100, 99, 34};
        new SelectionSorter().sort(data);
        System.out.println(Arrays.toString(data));
    }

    public void sort(int[] data) {
        if (data == null || data.length <= 1) return;

        for (int i = 0; i < data.length; i++) {
            int swapIndex = i;
            for (int j = i; j < data.length; j++) {
                if (data[swapIndex] > data[j]) {
                    swapIndex = j;
                }
            }
            swap(data, swapIndex, i);
        }
    }

    public void sort_swap(int[] data) {
        if (data == null || data.length <= 1) return;

        for (int i = 0; i < data.length; i++) {
            for (int j = i; j < data.length; j++) {
                if (data[i] > data[j]) {
                    swap(data, i, j);
                }
            }
        }
    }
}
