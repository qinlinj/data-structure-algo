package org.qinlinj.linear.algo.sort;

import java.util.Arrays;

public class BubbleSorter extends Sorter {
    public static void main(String[] args) {
        int[] data = new int[]{12, 23, 36, 9, 24, 42};
        new BubbleSorter().sort(data);
        System.out.println(Arrays.toString(data));
    }

    public void sort(int[] data) {
        if (data == null || data.length <= 1) {
            return;
        }
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data.length - i - 1; j++) {
                if (data[j] > data[j + 1]) {
                    swap(data, j, j + 1);
                }
            }
        }
    }
}
