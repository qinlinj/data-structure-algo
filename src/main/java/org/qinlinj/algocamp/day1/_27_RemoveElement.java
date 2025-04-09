package org.qinlinj.algocamp.day1;

public class _27_RemoveElement {
    public static void main(String[] args) {
        Solution solution = new _27_RemoveElement().new Solution();
        // put your test code here

    }

    class Solution {
        public int removeElement(int[] nums, int val) {
            if (nums == null || nums.length == 0) {
                return 0;
            }

            int slow = 0, fast = nums.length - 1;
            while (slow <= fast) {
                if (nums[slow] == val) {
                    nums[slow] = nums[fast];
                    fast--;
                } else {
                    slow++;
                }
            }
            return slow;
        }
    }
}
