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
            int start = 0, end = data.length - 1;
            while (start < end) {
                while (start < data.length - 1 && !Character.isLetterOrDigit(data[start])) {
                    start++;
                }
                while (end > 0 && !Character.isLetterOrDigit(data[end])) {
                    end--;
                }
                if (Character.toLowerCase(data[start]) != Character.toLowerCase(data[end])) {
                    return false;
                }
                start++;
                end--;
            }
            return start != data.length || end != 0;
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
