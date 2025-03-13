package org.qinlinj.leetcode.editor.en;

// [26] Remove Duplicates from Sorted Array
public class _26_RemoveDuplicatesFromSortedArray {
    public static void main(String[] args) {
        Solution solution = new _26_RemoveDuplicatesFromSortedArray().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
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
//leetcode submit region end(Prohibit modification and deletion)

}