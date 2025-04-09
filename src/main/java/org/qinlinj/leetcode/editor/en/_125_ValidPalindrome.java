package org.qinlinj.leetcode.editor.en;

// [125] Valid Palindrome
public class _125_ValidPalindrome {

    public static void main(String[] args) {
        Solution solution = new _125_ValidPalindrome().new Solution();
        // put your test code here

    }

    //leetcode submit region begin(Prohibit modification and deletion)
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
    //leetcode submit region end(Prohibit modification and deletion)


}


//A phrase is a palindrome if, after converting all uppercase letters into
//lowercase letters and removing all non-alphanumeric characters, it reads the same 
//forward and backward. Alphanumeric characters include letters and numbers. 
//
// Given a string s, return true if it is a palindrome, or false otherwise. 
//
// 
// Example 1: 
//
// 
//Input: s = "A man, a plan, a canal: Panama"
//Output: true
//Explanation: "amanaplanacanalpanama" is a palindrome.
// 
//
// Example 2: 
//
// 
//Input: s = "race a car"
//Output: false
//Explanation: "raceacar" is not a palindrome.
// 
//
// Example 3: 
//
// 
//Input: s = " "
//Output: true
//Explanation: s is an empty string "" after removing non-alphanumeric 
//characters.
//Since an empty string reads the same forward and backward, it is a palindrome.
//
// 
//
// 
// Constraints: 
//
// 
// 1 <= s.length <= 2 * 10⁵ 
// s consists only of printable ASCII characters. 
// 
//
// Related TopicsTwo Pointers | String 
//
// 👍 10214, 👎 8529bug 反馈 | 使用指南 | 更多配套插件 
//
//
//
//
