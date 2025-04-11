package org.qinlinj.algocamp.day2;

import java.util.*;

public class _228_SummaryRanges {
    class Solution {
        public List<String> summaryRanges(int[] nums) {
            ArrayList<String> list = new ArrayList<>();

            if (nums.length == 0) {
                return list;
            }

            for (int i = 0; i < nums.length; i++) {
                int start = nums[i];

                while (i + 1 < nums.length && nums[i + 1] - nums[i] == 1) {
                    i++;
                }

                if (start != nums[i]) {
                    list.add(start + "->" + nums[i]);
                } else {
                    list.add(String.valueOf(start));
                }
            }

            return list;
        }
    }
}
