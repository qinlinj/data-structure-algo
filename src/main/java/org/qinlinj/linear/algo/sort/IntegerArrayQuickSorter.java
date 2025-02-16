package org.qinlinj.linear.algo.sort;

import java.util.ArrayList;
import java.util.Collections;

public class IntegerArrayQuickSorter extends Sorter {
    public void sort(ArrayList<Integer> data) {
        if (data == null || data.size() <= 1) {
            return;
        }
        Integer[] dataArr = data.toArray(new Integer[data.size()]);
        sort(dataArr, 0, dataArr.length - 1);
        data.clear();
        Collections.addAll(data, dataArr);
    }

    public void sort(Integer[] data, int start, int end) {
        if (start >= end) return;
        int j = partition(data, start, end);

        sort(data, start, j - 1);
        sort(data, j + 1, end);
    }

    private int partition(Integer[] data, int start, int end) {
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
}
