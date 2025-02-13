package org.qinlinj.linear.algo.sort;

import java.util.ArrayList;
import java.util.Arrays;

public class ShellSorter extends Sorter {
    public static void main(String[] args) {
        int[] data = new int[]{12, 23, 36, 9, 24, 42, 1, 4, 100, 99, 34};
        new ShellSorter().sort(data);
        System.out.println(Arrays.toString(data));
    }

    public void sort(int[] data) {
        int h = 1;
        int n = data.length;
        while (h < n / 3) {
            h = h * 3 + 1;
        }
        while (h >= 1) {
            for (int i = 1; i < n / h; i++) {
                int tmp = data[i * h];
                int j;
                for (j = i * h; j - h >= 0 && data[j - h] > tmp && j < n; j = j - h) {
                    data[j] = data[j - h];
                }
                data[j] = tmp;
            }
            h = h / 3;
        }
    }

    public void sort_swap(int[] data) {
        if (data == null || data.length <= 1) return;

        int n = data.length;
        ArrayList<Integer> list = new ArrayList<>();
        int k = 1;
        int h;
        do {
            h = ((int) Math.pow(3, k) - 1) / 2;

            if (h > n / 3 && n >= 3) break;
            list.add(h);
            k++;
        } while (h <= n / 3);

        for (k = list.size() - 1; k >= 0; k--) {
            h = list.get(k);
            for (int i = h; i < n; i++) {
                for (int j = i; j >= h; j = j - h) {
                    if (data[j] < data[j - h]) {
                        swap(data, j, j - h);
                    } else {
                        break;
                    }
                }
            }
        }
    }
}
