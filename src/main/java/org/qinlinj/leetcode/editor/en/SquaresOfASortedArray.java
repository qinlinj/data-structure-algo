package org.qinlinj.leetcode.editor.en;

import java.util.Arrays;

// [977] Squares of a Sorted Array
public class SquaresOfASortedArray {
    public static void main(String[] args) {
        Solution solution = new SquaresOfASortedArray().new Solution();
        int[] nums = new int[]{-10000, -9999, -7, -5, 0, 0, 10000};
        int[] res = solution.sortedSquares(nums);
        System.out.println(Arrays.toString(res));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int[] sortedSquares(int[] nums) {
            if (nums.length == 1) {
                nums[0] *= nums[0];
                return nums;
            }
            for (int i = 0; i < nums.length; i++) {
                nums[i] = Math.abs(nums[i]);
            }
            Arrays.sort(nums);
            System.out.println(Arrays.toString(nums));
            int slow = 0;
            int fast = 0;
            while (fast < nums.length) {
                if (nums[slow] != nums[fast] || fast == nums.length - 1) {
                    nums[slow] *= nums[slow];
                    while (slow < fast - 1) {
                        nums[slow + 1] = nums[slow];
                        slow++;
                    }
                    slow = fast;
                    fast++;
                } else {
                    fast++;
                }
            }
            nums[nums.length - 1] *= nums[nums.length - 1];
            return nums;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}