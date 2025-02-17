package org.qinlinj.linear.algo.sort.pratice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _56_MergeIntervals {
    class Solution {
        public int[][] merge(int[][] intervals) {
            if (intervals == null || intervals.length < 2) {
                return intervals;
            }
            // sort based on fisrt interval
            // fast sort
            sort(intervals);

            int count = 0;
            int[][] tmp = new int[intervals.length][2];
            tmp[count] = intervals[0];

            for (int i = 1; i < intervals.length; i++) {
                if (tmp[count][1] >= intervals[i][0]) {
                    tmp[count][1] = Math.max(tmp[count][1], intervals[i][1]);
                } else {
                    count++;
                    tmp[count] = intervals[i];
                }
            }
            int[][] res = new int[count + 1][2];
            System.arraycopy(tmp, 0, res, 0, count + 1);

            return res;

        }

        public void sort(int[][] data) {
            sort(data, 0, data.length - 1);

        }

        public void sort(int[][] data, int left, int right) {
            if (left >= right) {
                return;
            }
            int i = left;
            int less = i;
            int great = right;

            int mid = less + (great - less) / 2;
            swap(data, mid, great);
            int target = data[great][0];
            while (i <= great) {
                if (data[i][0] > target) {
                    swap(data, great, i);
                    great--;
                } else if (data[i][0] < target) {
                    swap(data, less, i);
                    i++;
                    less++;
                } else {
                    i++;
                }
            }
            sort(data, left, less - 1);
            sort(data, great + 1, right);
        }

        public void swap(int[][] data, int i, int j) {
            int[] tmp = data[i];
            data[i] = data[j];
            data[j] = tmp;
        }
    }

    //----------------------------------------------------------------
    // more concise approach
    class Solution1 {
        public int[][] merge(int[][] intervals) {
            if (intervals == null || intervals.length < 2) {
                return intervals;
            }

            Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

            List<int[]> merged = new ArrayList<>();
            merged.add(intervals[0]);

            for (int i = 1; i < intervals.length; i++) {
                int[] current = intervals[i];
                int[] last = merged.get(merged.size() - 1);

                if (last[1] >= current[0]) {
                    last[1] = Math.max(last[1], current[1]);
                } else {
                    merged.add(current);
                }
            }

            return merged.toArray(new int[merged.size()][]);
        }
    }
}
