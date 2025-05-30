package org.qinlinj.leetcode.editor.en;

// [26] Remove Duplicates from Sorted Array
public class _26_RemoveDuplicatesFromSortedArray {
    public static void main(String[] args) {
        Solution solution = new _26_RemoveDuplicatesFromSortedArray().new Solution();
        int[] data = new int[]{0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        solution.removeDuplicates(data);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int removeDuplicates(int[] nums) {
            int slow = 0, fast = 1;
            for (int i = 1; i < nums.length; i++) {
                if (nums[fast] != nums[slow]) {
                    slow++;
                    nums[slow] = nums[fast];
                }
                fast++;
            }
//            System.out.println(Arrays.toString(nums));
            return slow + 1;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}