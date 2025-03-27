package org.qinlinj.practical.multlinemerging;

import java.util.*;

public class KSortedArrayMerger {
    public int[] mergeKSortedArray(List<int[]> data, int k) {
        int len = 0;
        for (int i = 0; i < k; i++) {
            len += data.get(i).length;
        }
    }
}