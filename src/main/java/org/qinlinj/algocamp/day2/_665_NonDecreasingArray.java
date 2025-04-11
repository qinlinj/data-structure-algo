package org.qinlinj.algocamp.day2;

public class _665_NonDecreasingArray {
    public static void main(String[] args) {
        Solution solution = new _665_NonDecreasingArray().new Solution();
        // put your test code here

    }
    
    class Solution {
        public boolean checkPossibility(int[] nums) {
            int count = 0;
            for (int i = 1; i < nums.length; i++) {
                if (nums[i] < nums[i - 1]) {
                    count++;
                    if (count > 1) {
                        return false;
                    }
                    if (i > 1 && nums[i] < nums[i - 2]) {
                        nums[i] = nums[i - 1];
                    } else {
                        nums[i - 1] = nums[i];
                    }
                }
            }
            return true;
        }
    }
}
