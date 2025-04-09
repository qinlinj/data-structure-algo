package org.qinlinj.algocamp.day1;

public class _125_ValidPalindrome {
    public static void main(String[] args) {
        Solution solution = new _125_ValidPalindrome().new Solution();
        // put your test code here

    }

    class Solution {
        public boolean isPalindrome(String s) {
//            Character.isLetterOrDigit();
            char[] data = s.toCharArray();
            int left = 0, right = data.length - 1;
            while (left < right) {
                while (left < right && !Character.isLetterOrDigit(data[left])) {
                    left++;
                }
                while (left < right && !Character.isLetterOrDigit(data[right])) {
                    right--;
                }
                if (left < right) {
                    if (Character.toLowerCase(data[left]) != Character.toLowerCase(data[right])) {
                        return false;
                    }
                    left++;
                    right--;
                }
            }
            return true;
        }
    }
}
