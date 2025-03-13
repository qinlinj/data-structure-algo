package org.qinlinj.leetcode.editor.en;

// [5] Longest Palindromic Substring
public class _5_LongestPalindromicSubstring {
    public static void main(String[] args) {
        Solution solution = new _5_LongestPalindromicSubstring().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String longestPalindrome(String s) {
            char[] charArray = s.toCharArray();
            String res = "";
            for (int i = 0; i < s.length(); i++) {
                String resCurr1 = getPalindrome(i, i, s);
                String resCurr2 = getPalindrome(i, i + 1, s);
                res = res.length() > resCurr1.length() ? res : resCurr1;
                res = res.length() > resCurr2.length() ? res : resCurr2;
            }
            return res;
        }

        private String getPalindrome(int left, int right, String s) {
            while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
                left--;
                right++;
            }
            return s.substring(left + 1, right);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}