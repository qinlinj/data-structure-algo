package org.qinlinj.algocamp.day1;

public class _80_RemoveDuplicatesFromSortedArrayIi {
    public static void main(String[] args) {
        _80_RemoveDuplicatesFromSortedArrayIi.Solution solution = new _80_RemoveDuplicatesFromSortedArrayIi().new Solution();
        // put your test code here
        int[] data = new int[]{0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        solution.removeDuplicates(data);
    }

    class Solution {
        public int removeDuplicates(int[] nums) {
            int slow = 1, fast = 2;
            for (int i = 2; i < nums.length; i++) {
                if (nums[fast] != nums[slow - 1]) {
                    slow++;
                    nums[slow] = nums[fast];
                }
                fast++;
            }
//            System.out.println(Arrays.toString(nums));
            return slow + 1;
        }
    }
}
