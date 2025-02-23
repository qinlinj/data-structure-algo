package org.qinlinj.leetcode.editor.en;


import java.util.Arrays;

// [1] Two Sum
public class TwoSum {
    public static void main(String[] args) {
        Solution solution = new TwoSum().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int[] twoSum(int[] nums, int target) {
            int[][] pairs = new int[nums.length][2];
            for (int i = 0; i < nums.length; i++) {
                pairs[i][0] = nums[i];
                pairs[i][1] = i;
            }

            Arrays.sort(pairs, (a, b) -> a[0] - b[0]);

            int left = 0;
            int right = nums.length - 1;

            while (left < right) {
                int sum = pairs[left][0] + pairs[right][0];
                if (sum == target) {
                    return new int[]{pairs[left][1], pairs[right][1]};
                } else if (sum < target) {
                    left++;
                } else {
                    right--;
                }
            }

            return new int[]{};
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

    class Solution1 {
        public int[] twoSum(int[] nums, int target) {
            int[] tmpNums = new int[nums.length];
            System.arraycopy(nums, 0, tmpNums, 0, nums.length);
            Arrays.sort(tmpNums);
            int slow = 0;
            int fast = tmpNums.length - 1;
            while (slow < fast) {
                int sum = tmpNums[slow] + tmpNums[fast];
                if (sum < target) {
                    slow++;
                } else if (sum > target) {
                    fast--;
                } else {
                    break;
                }
            }
            if (slow == fast) {
                return new int[]{};
            } else {
                int[] res = new int[2];
                for (int i = 0; i < nums.length; i++) {
                    if (nums[i] == tmpNums[slow]) {
                        res[0] = i;
                    }
                }
                for (int j = 0; j < nums.length; j++) {
                    if (nums[j] == tmpNums[fast] && j != res[0]) {
                        res[1] = j;
                    }
                }
                Arrays.sort(res);
                return res;
            }
        }
    }
}