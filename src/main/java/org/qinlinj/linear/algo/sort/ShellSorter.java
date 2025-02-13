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

    public void sort_swap(int[] data) {
        if (data == null || data.length <= 1) return;

        // 1. 计算递增序列
        int n = data.length;
        ArrayList<Integer> list = new ArrayList<>();
        int k = 1;
        int h;
        do {
            h = ((int) Math.pow(3, k) - 1) / 2;
            // bug 修复：需要考虑 n < 3 的场景，
            // 当 n < 3 的时候，不应该 break，而应该将 h 添加到 list 中
            if (h > n / 3 && n >= 3) break;
            list.add(h); // 1, 4, 13, 40, 121......
            k++;
        } while (h <= n / 3);

        // 2. 希尔排序
        for (k = list.size() - 1; k >= 0; k--) { // 倒序遍历
            h = list.get(k);
            // 将数组变为 h 有序
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
