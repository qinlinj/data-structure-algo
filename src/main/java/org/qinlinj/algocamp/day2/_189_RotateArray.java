package org.qinlinj.algocamp.day2;

public class _189_RotateArray {
    public static void main(String[] args) {
        Solution solution = new _189_RotateArray().new Solution();
        // put your test code here
        int[] data = new int[]{1, 2, 3, 4, 5, 6, 7};
        solution.rotate(data, 3);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public void rotate(int[] nums, int k) {
            int n = nums.length;
            int count = 0;
            for (int start = 0; count < n; start++) {
                int curr = start, currVal = nums[start];
                do {
                    int next = (curr + k) % n;
                    int tmp = nums[next];
                    nums[next] = currVal;
                    curr = next;
                    currVal = tmp;
                    // Use counters to avoid reprocessing elements!
                    count++;
                } while (curr != start);
            }
//            System.out.println(Arrays.toString(nums));
        }
    }
}
