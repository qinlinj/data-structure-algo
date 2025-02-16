package org.qinlinj.linear.algo.sort;

import java.util.ArrayList;
import java.util.Arrays;

public class BacketSorter extends Sorter {
    public static void main(String[] args) {
        int[] data = new int[]{2, 3, 6, 1, 34, 11, 53, 6, 22, 12};
        BacketSorter sorter = new BacketSorter();
        sorter.sort(data);
        System.out.println(Arrays.toString(data));
    }

    public void sort(int[] data) {
        if (data == null || data.length < 2) {
            return;
        }
        // initial buckets
        int bucketNum = 10;
        // get max
        int max = data[0];
        for (int i = 1; i < data.length; i++) {
            max = Math.max(data[i], max);
        }
        int bucketGap = max / bucketNum + 1;

        ArrayList<Integer>[] buckets = new ArrayList[bucketNum];

        for (int datum : data) {
            // calculate which bucket each element belongs to
            int bucketIndex = datum / bucketGap;
            if (buckets[bucketIndex] == null) {
                buckets[bucketIndex] = new ArrayList<>();
            }
            buckets[bucketIndex].add(datum);
        }
        IntegerArrayQuickSorter sorter = new IntegerArrayQuickSorter();
        // sort each bucket
        for (ArrayList<Integer> bucket : buckets) {
            sorter.sort(bucket);
        }
        int curr = 0;
        // splice each bucket

        for (int j = 0; j < bucketNum && buckets[j] != null; j++) {
            for (int i = 0; i < buckets[j].size() - 1; i++) {
                data[curr] = buckets[j].get(i);
                curr++;
            }
        }
    }

}
