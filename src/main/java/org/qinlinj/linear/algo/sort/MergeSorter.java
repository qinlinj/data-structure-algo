package org.qinlinj.linear.algo.sort;

import java.util.Arrays;

public class MergeSorter extends Sorter {
    public static void main(String[] args) {
        int[] data = new int[]{12, 23, 36, 9, 24, 42, 1, 4, 100, 99, 34};
        new MergeSorter().sort(data);
        System.out.println(Arrays.toString(data));
    }

    public void sort(int[] data) {
        if (data == null || data.length < 2) {
            return;
        }
        int[] tmp = new int[data.length];
        sort(data, 0, data.length - 1, tmp);
    }

    public void sort(int[] data, int start, int end, int[] tmp) {
        if (start >= end) {
            return;
        }

        int mid = start + (end - start) / 2;
        sort(data, start, mid, tmp);
        sort(data, mid + 1, end, tmp);

        mergeArray(data, start, mid, end, tmp);
    }

    // initial temp array
    private void mergeArray(int[] data, int start, int mid, int end, int[] tmp) {
        int i = start;
        int j = mid + 1;
        int tmpPos = start;

        while (i <= mid && j <= end) {
            if (data[i] > data[j]) {
                tmp[tmpPos++] = data[j++];
            } else {
                tmp[tmpPos++] = data[i++];
            }
        }

        while (i <= mid) {
            tmp[tmpPos++] = data[i++];
        }
        while (j <= end) {
            tmp[tmpPos++] = data[j++];
        }
        for (tmpPos = start; tmpPos <= end; tmpPos++) {
            data[start++] = tmp[tmpPos];
        }
//        StringBuilder sb = new StringBuilder();
//        for (int datum : tmp) {
//            sb.append(datum);
//        }
//        System.out.println(sb);
    }

    public void sort_ql(int[] data) {
        if (data == null || data.length < 2) {
            return;
        }
        sort_ql(data, 0, data.length - 1);
    }

    public void sort_ql(int[] data, int start, int end) {
        if (start >= end) {
            return;
        }

        int mid = start + (end - start) / 2;
        sort_ql(data, start, mid);
        sort_ql(data, mid + 1, end);

        mergeArray_ql(data, start, mid, end);
    }

    // insertion sort
    private void mergeArray_ql(int[] data, int start, int mid, int end) {
        for (int i = mid + 1; i <= end; i++) {
            int tmp = data[i];
            int j;
            for (j = i; j > mid && data[j - 1] > tmp; j--) {
                data[j] = data[j - 1];
            }
            data[j] = tmp;
        }
    }
}
