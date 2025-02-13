package org.qinlinj.linear.algo.sort;

import java.util.Arrays;

public class ShellSorter extends Sorter {
    public static void main(String[] args) {
        int[] data = new int[]{12, 23, 36, 9, 24, 42, 1, 4, 100, 99, 34};
        new ShellSorter().sort(data);
        System.out.println(Arrays.toString(data));
    }

    public void sort(int[] data) {
        int k = 1;
        int n = data.length;
        while (k < n / 3) {
            k = k * 3 + 1;
        }
        while (k >= 1) {
            for (int i = 1; i < n / k; i++) {
                int tmp = data[i * k];
                int j;
                for (j = i * k; j - k >= 0 && data[j - k] > tmp && j < n; j = j + k) {
                    data[j] = data[j - k];
                }
                data[j] = tmp;
            }
            k = k / 3;
        }
    }
}
