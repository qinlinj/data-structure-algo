package org.qinlinj.linear.algo.sort.pratice;

import java.util.Arrays;
import java.util.Comparator;

public class _179_LargestNumber {
    public String largestNumber(int[] nums) {
        String[] tmp = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            tmp[i] = String.valueOf(nums[i]);
        }

        Arrays.sort(tmp, new Comparator<String>() {
            @Override
            // compare: if yx - xy < 0, swap x and y
            public int compare(String x, String y) {
                String xy = x + "" + y;
                String yx = y + "" + x;
                return yx.compareTo(xy);
            }
        });

        if (tmp[0].equals("0")) {
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        for (String string : tmp) {
            sb.append(string);
        }
        return sb.toString();
    }


    //-----------------------------------------
    // using quick sort
    public String Solution2(int[] nums) {

        sort(nums, 0, nums.length - 1);

        if (nums[0] == 0) {
            return "0";
        } // "00000"

        StringBuilder sb = new StringBuilder();
        for (int num : nums) {
            sb.append(num);
        }
        return sb.toString();
    }

    private void sort(int[] data, int lo, int hi) {
        if (lo >= hi) return;
        int pivot = data[hi];

        int less = lo;
        int great = hi;

        int i = lo;
        while (i <= great) {
            String xy = data[i] + "" + pivot;
            String yx = pivot + "" + data[i];
            if (xy.compareTo(yx) > 0) {
                swap(data, i, less);
                less++;
                i++;
            } else if (xy.compareTo(yx) < 0) {
                swap(data, i, great);
                great--;
            } else {
                i++;
            }
        }

        sort(data, lo, less - 1);
        sort(data, great + 1, hi);
    }

    public void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
