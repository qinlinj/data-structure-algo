package org.qinlinj.linear.algo.sort.pratice;

public class _JZ_51_493_ReversePairs {
    class Solution1 {
        // traversal
        public int reversePairs(int[] nums) {
            if (nums == null || nums.length < 2) {
                return 0;
            }

            int[] tmp = new int[nums.length];
            System.arraycopy(nums, 0, tmp, 0, nums.length);
            int count = 0;

            for (int size = 1; size < nums.length; size *= 2) {
                for (int left = 0; left < nums.length - size; left += size * 2) {
                    int mid = left + size;
                    int right = Math.min(left + size * 2 - 1, nums.length - 1);
                    count += sortAndCount(nums, tmp, left, mid, right);
                }
                System.arraycopy(tmp, 0, nums, 0, nums.length);
            }

            return count;
        }

        private int sortAndCount(int[] nums, int[] tmp, int left, int mid, int right) {
            int count = 0;
            int pos = left;
            int start1 = left;
            int start2 = mid;

            int j = mid;
            for (int i = left; i < mid; i++) {
                while (j <= right && (long) nums[i] > (long) 2 * nums[j]) {
                    j++;
                }
                count += j - mid;
            }

            while (start1 < mid && start2 <= right) {
                if (nums[start1] <= nums[start2]) {
                    tmp[pos++] = nums[start1++];
                } else {
                    tmp[pos++] = nums[start2++];
                }
            }

            while (start1 < mid) {
                tmp[pos++] = nums[start1++];
            }
            while (start2 <= right) {
                tmp[pos++] = nums[start2++];
            }

            return count;
        }
    }

    //----------------------------------------------------
    // recursion

}
