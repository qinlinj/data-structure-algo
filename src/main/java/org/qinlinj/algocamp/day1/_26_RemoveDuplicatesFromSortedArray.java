package org.qinlinj.algocamp.day1;

public class _26_RemoveDuplicatesFromSortedArray {
    public static void main(String[] args) {
        Solution solution = new _26_RemoveDuplicatesFromSortedArray().new Solution();
    }

    class Solution {
        public int removeDuplicates(int[] nums) {
            if (nums.length == 0) {
                return 0;
            }
            int slow = 0, fast = 0;
            while (fast < nums.length) {
                if (nums[fast] != nums[slow]) {
                    slow++;
                    nums[slow] = nums[fast];
                }
                fast++;
            }
            return slow + 1;
        }
    }
}
