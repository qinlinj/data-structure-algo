package org.qinlinj.leetcode.editor.en;

// [167] Two Sum II - Input Array Is Sorted
public class TwoSumIiInputArrayIsSorted {
    public static void main(String[] args) {
        Solution solution = new TwoSumIiInputArrayIsSorted().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int[] twoSum(int[] numbers, int target) {
            if (numbers == null || numbers.length < 2) {
                return null;
            }
            int[] res = new int[2];
            int fast;
            int slow;
            int count = 0;
            while (count < numbers.length) {
                slow = count;
                fast = slow + 1;
                for (int i = count; i < numbers.length - 1; i++) {
                    if (target - numbers[fast] == numbers[slow]) {
                        res[0] = slow + 1;
                        res[1] = fast + 1;
                        return res;
                    } else if (target - numbers[fast] < numbers[slow]) {
                        break;
                    } else {
                        fast++;
                    }
                }
                count++;
            }
            return null;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}