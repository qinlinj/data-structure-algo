package org.qinlinj.algocamp.day1;

public class _283_MoveZeroes {
    public static void main(String[] args) {
        Solution solution = new _283_MoveZeroes().new Solution();
        // put your test code here
        int[] data = new int[]{1, 2, 0, 3, 1, 0, 0, 5};
        solution.moveZeroes(data);
        for (int datum : data) {
            System.out.println(datum);
        }
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public void moveZeroes(int[] nums) {
            int slow = 0, fast = 0;
            for (int i = 0; i < nums.length; i++) {
                if (nums[fast] != 0) {
                    nums[slow] = nums[fast];
                    slow++;
                }
                fast++;
            }
            for (; slow < nums.length; slow++) {
                nums[slow] = 0;
            }
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)
}
