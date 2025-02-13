package org.qinlinj.linear.algo.sort;

import java.util.Arrays;

public class MergeSorter extends Sorter {
    public static void main(String[] args) {
        int[] data = new int[]{12, 23, 36, 9, 24, 42, 1, 4, 100, 99, 34};
        new MergeSorter().sort_ql(data);
        System.out.println(Arrays.toString(data));
    }

    public void sort(int[] data) {
        sort(data, 0, data.length - 1);
    }

    public void sort(int[] data, int start, int end) {
        if (start >= end) {
            return;
        }

        int mid = start + (end - start) / 2;
        sort(data, start, mid);
        sort(data, mid + 1, end);

        mergeArray(data, start, mid, end);
    }

    // initial temp array
    private void mergeArray(int[] data, int start, int mid, int end) {
        int tmp[] = new int[end - start + 1];
        int i = start;
        int j = mid + 1;
        int k = 0;

        while (i <= mid && j <= end) {
            if (data[i] > data[j]) {
                tmp[k++] = data[j++];
            } else {
                tmp[k++] = data[i++];
            }
        }

        while (i <= mid) {
            tmp[k++] = data[i++];
        }
        while (j <= end) {
            tmp[k++] = data[j++];
        }

        System.arraycopy(tmp, 0, data, start, tmp.length);
    }

    public void sort_ql(int[] data) {
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
            for (j = i; j > start && data[j - 1] > tmp; j--) {
                data[j] = data[j - 1];
            }
            data[j] = tmp;
        }
    }
}
