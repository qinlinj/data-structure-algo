package org.qinlinj.linear.algo.twopointer;

public class _283_MoveZeroes {
    class Solution {
        public void moveZeroes(int[] nums) {
            int fast = 0;
            int slow = 0;
            for (int i = 0; i < nums.length; i++) {
                if (nums[fast] != 0) {
                    nums[slow] = nums[fast];
                    slow++;
                }
                fast++;
            }
            for (int j = slow; j < nums.length; j++) {
                nums[j] = 0;
            }
        }
    }
}
