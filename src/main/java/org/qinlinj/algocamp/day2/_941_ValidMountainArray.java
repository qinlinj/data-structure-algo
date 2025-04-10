package org.qinlinj.algocamp.day2;

public class _941_ValidMountainArray {
    public static void main(String[] args) {
        Solution solution = new _941_ValidMountainArray().new Solution();
        // put your test code here

    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean validMountainArray(int[] arr) {
            if (arr == null || arr.length < 3) return false;

            int i = 0;
            while (i < arr.length - 1 && arr[i] < arr[i + 1]) {
                i++;
            }
            if (i == arr.length - 1 || i == 0) {
                return false;
            }
            while (i < arr.length - 1 && arr[i] > arr[i + 1]) {
                i++;
            }
            return i == arr.length - 1;
        }
    }
}
