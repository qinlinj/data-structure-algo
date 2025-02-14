package org.qinlinj.linear.algo.sort;

public class ThreeWayQuickSorter extends Sorter {
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

        int less = 0;
        int great = end;
        int i = 0;

        while (great > less) {
            
        }

    }
}
