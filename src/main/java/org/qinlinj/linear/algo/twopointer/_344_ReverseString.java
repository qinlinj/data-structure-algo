package org.qinlinj.linear.algo.twopointer;

public class _344_ReverseString {
    class Solution {
        public void reverseString(char[] s) {
            if (s == null || s.length <= 1) {
                return;
            }
            int left = 0;
            int right = s.length - 1;
            char temp;
            while (left <= right) {
                temp = s[left];
                s[left] = s[right];
                s[right] = temp;
                left++;
                right--;
            }
        }
    }
}
