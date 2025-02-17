package org.qinlinj.linear.algo.sort.pratice;

public class _75_SortColors {
    class Solution {
        public void sortColors(int[] nums) {
            int[] tmp = new int[3];
            for (int i = 0; i < nums.length; i++) {
                tmp[nums[i]]++;
            }
            for (int m = 1; m < tmp.length; m++) {
                tmp[m] += tmp[m - 1];
            }
            int[] res = new int[nums.length];
            for (int j = nums.length - 1; j >= 0; j--) {
                res[(--tmp[nums[j]])] = nums[j];
            }
            System.arraycopy(res, 0, nums, 0, nums.length);
        }

        //------------------------------------
        // more concise way
        public void sortColors1(int[] nums) {
            int[] count = new int[3];
            for (int num : nums) {
                count[num]++;
            }

            int k = 0;
            for (int i = 0; i <= 2; i++) {
                int num = count[i];
                for (int j = 1; j <= num; j++) {
                    nums[k++] = i;
                }
            }
        }
    }
}
