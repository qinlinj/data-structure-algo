package org.qinlinj.practical.multlinemerging;

import java.util.*;

public class KSortedArrayMerger {
    public static void main(String[] args) {
        int[] a = {1, 2, 6, 9, 10};
        int[] b = {2, 3, 7, 11};
        int[] c = {4, 8, 9, 13};
        int[] d = {5};

        List<int[]> data = new ArrayList<>();
        data.add(a);
        data.add(b);
        data.add(c);
        data.add(d);

        int[] res = new KSortedArrayMerger().mergeKSortedArray(data, 4);
        System.out.println(Arrays.toString(res));
    }

    public int[] mergeKSortedArray(List<int[]> data, int k) {
        int len = 0;
        for (int i = 0; i < k; i++) {
            len += data.get(i).length;
        }

        int[] res = new int[len];

        PriorityQueue<int[]> minHeap = new PriorityQueue<>(k, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] != o2[0] ? o1[0] - o2[0] : o1[1] - o2[1];
            }
        });

        for (int i = 0; i < k; i++) {
            if (data.get(i).length == 0) continue;
            minHeap.add(new int[]{data.get(i)[0], i, 0});
        }

        int index = 0;
        while (!minHeap.isEmpty()) {
            int[] record = minHeap.poll();
            int value = record[0];
            int whichArray = record[1];
            int valueIndex = record[2];

            res[index++] = value;

            valueIndex++;

            if (valueIndex == data.get(whichArray).length) continue;

            minHeap.add(new int[]{data.get(whichArray)[valueIndex], whichArray, valueIndex});
        }
        return res;
    }
}