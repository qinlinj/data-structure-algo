package org.qinlinj.leetcode.editor.en;

// [344] Reverse String
public class _344_ReverseString {

    public static void main(String[] args) {
        Solution solution = new _344_ReverseString().new Solution();
        // put your test code here

    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public void reverseString(char[] s) {
            if (s == null || s.length <= 1) {
                return;
            }

            int start = 0, end = s.length - 1;
            for (int i = 0; i < s.length; i++) {
                swap(s, start++, end--);
            }
        }

        private void swap(char[] s, int start, int end) {
            char tmp = s[start];
            s[end] = s[start];
            s[start] = tmp;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


}


//Write a function that reverses a string. The input string is given as an
//array of characters s. 
//
// You must do this by modifying the input array in-place with O(1) extra 
//memory. 
//
// 
// Example 1: 
// Input: s = ["h","e","l","l","o"]
//Output: ["o","l","l","e","h"]
// 
// Example 2: 
// Input: s = ["H","a","n","n","a","h"]
//Output: ["h","a","n","n","a","H"]
// 
// 
// Constraints: 
//
// 
// 1 <= s.length <= 10⁵ 
// s[i] is a printable ascii character. 
// 
//
// Related TopicsTwo Pointers | String 
//
// 👍 8944, 👎 1191bug 反馈 | 使用指南 | 更多配套插件 
//
//
//
//
