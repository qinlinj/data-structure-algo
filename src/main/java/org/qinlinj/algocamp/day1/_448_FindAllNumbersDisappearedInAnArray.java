package org.qinlinj.algocamp.day1;

import java.util.*;

public class _448_FindAllNumbersDisappearedInAnArray {

    public static void main(String[] args) {
        Solution solution = new _448_FindAllNumbersDisappearedInAnArray().new Solution();
        // put your test code here

    }

    public List<Integer> findDisappearedNumbers1(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            int index = (nums[i] - 1 + n) % n;
            nums[index] += n;
        }
        ArrayList<Integer> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (nums[i] <= n) {
                res.add(i + 1);
            }
        }
        return res;
    }

    //leetcode submit region end(Prohibit modification and deletion)

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<Integer> findDisappearedNumbers(int[] nums) {
            int n = nums.length;

            for (int i = 0; i < n; i++) {
                int index = Math.abs(nums[i]) - 1;
                if (nums[index] > 0) {
                    nums[index] = -nums[index];
                }
            }

            ArrayList<Integer> res = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                if (nums[i] > 0) {
                    res.add(i + 1);
                }
            }

            return res;
        }
    }
}