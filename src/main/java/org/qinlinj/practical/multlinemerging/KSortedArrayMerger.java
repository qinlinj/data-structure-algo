package org.qinlinj.practical.multlinemerging;

import java.util.*;

public class KSortedArrayMerger {
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

        return res;

    }
}