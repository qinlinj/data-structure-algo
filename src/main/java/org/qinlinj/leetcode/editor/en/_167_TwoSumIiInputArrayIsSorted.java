package org.qinlinj.leetcode.editor.en;

// [167] Two Sum II - Input Array Is Sorted
public class _167_TwoSumIiInputArrayIsSorted {
    public static void main(String[] args) {
        Solution solution = new _167_TwoSumIiInputArrayIsSorted().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)

    // more concise version
    class Solution {
        public int[] twoSum(int[] numbers, int target) {
            if (numbers == null || numbers.length < 2) {
                return new int[]{};
            }
            int slow = 0;
            int fast = numbers.length - 1;
            while (slow < fast) {
                int sum = numbers[slow] + numbers[fast];
                if (sum < target) {
                    slow++;
                } else if (sum > target) {
                    fast--;
                }
            }
            return new int[]{};
        }
    }

    //leetcode submit region end(Prohibit modification and deletion)
    // redundant version
    class Solution1 {
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
}