package org.qinlinj.linear.algo.sort.pratice;

import java.util.Arrays;
import java.util.Comparator;

public class _179_LargestNumber {
    class Solution {
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
    }
}
