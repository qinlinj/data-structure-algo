package org.qinlinj.algocamp.day2;

import java.util.*;

public class _163_MissingRanges {
    class Solution {
        public List<List<Integer>> findMissingRanges(int[] nums, int lower, int upper) {
            List<List<Integer>> lists = new ArrayList<>();
            if (nums.length == 0) {
                lists.add(Arrays.asList(lower, upper));
                return lists;
            }

            if (nums[0] > lower) {
                lists.add(Arrays.asList(lower, nums[0] - 1));
            }

            for (int i = 1; i < nums.length; i++) {
                if (nums[i] == nums[i - 1]) {
                    continue;
                }

                if (nums[i] > nums[i - 1] + 1) {
                    lists.add(Arrays.asList(nums[i - 1] + 1, nums[i] - 1));
                }
            }

            if (nums[nums.length - 1] < upper) {
                lists.add(Arrays.asList(nums[nums.length - 1] + 1, upper));
            }

            return lists;
        }
}
