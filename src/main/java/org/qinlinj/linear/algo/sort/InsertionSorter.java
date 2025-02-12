package org.qinlinj.linear.algo.sort;

import java.util.Arrays;

public class InsertionSorter extends Sorter {
    public static void main(String[] args) {
        int[] data = new int[]{12, 23, 36, 9, 24, 42, 1, 4, 100, 99, 34};
        new InsertionSorter().sort(data);
        System.out.println(Arrays.toString(data));
    }

    public void sort(int[] data) {
        if (data == null || data.length <= 1) return;
        for (int i = 1; i < data.length; i++) {
            int tmp = data[i];
            int j;
            for (j = i; j > 0 && tmp < data[j - 1]; j--) {
                data[j] = data[j - 1];
            }
            data[j] = tmp;
        }
    }

    public void sort_ql(int[] data) {
        if (data == null || data.length <= 1) return;
        int tmp;
        for (int i = 1; i < data.length; i++) {
            tmp = data[i];
            for (int j = i; j > 0; j--) {
                if (data[j] < data[j - 1]) {
                    data[j] = data[j - 1];
                } else {
                    break;
                }
                data[j - 1] = tmp;
            }
        }
    }

    public void sort_swap(int[] data) {
        if (data == null || data.length <= 1) return;
        for (int i = 1; i < data.length; i++) {
            for (int j = i; j > 0; j--) {
                if (data[j] < data[j - 1]) {
                    swap(data, j, j - 1);
                } else {
                    break;
                }
            }
        }
    }
}
