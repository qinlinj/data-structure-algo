package org.qinlinj.algocamp.day1;

import java.util.*;

public class _442_FindAllDuplicatesInAnArray {
    public static void main(String[] args) {
        Solution solution = new _442_FindAllDuplicatesInAnArray().new Solution();
        // put your test code here
        int[] nums = new int[]{4, 3, 2, 7, 8, 2, 3, 1};
        System.out.println(solution.findDuplicates(nums));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<Integer> findDuplicates(int[] nums) {
            if (nums == null || nums.length < 2) {
                return new ArrayList<>();
            }
            ArrayList<Integer> list = new ArrayList<>();
            for (int i = 0; i < nums.length; i++) {
                int index = nums[i];
                int absIndex = Math.abs(index);
                if (nums[absIndex - 1] < 0) {
                    list.add(absIndex);
                } else {
                    nums[absIndex - 1] = -nums[absIndex - 1];
                }
            }
            return list;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)
}
