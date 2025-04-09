package org.qinlinj.leetcode.editor.en;

// [1370] Increasing Decreasing String
public class _1370_IncreasingDecreasingString {

    public static void main(String[] args) {
        Solution solution = new _1370_IncreasingDecreasingString().new Solution();
        // put your test code here

    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String sortString(String s) {
            return null;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


}


//You are given a string s. Reorder the string using the following algorithm:
//
// 
// Remove the smallest character from s and append it to the result. 
// Remove the smallest character from s that is greater than the last appended 
//character, and append it to the result. 
// Repeat step 2 until no more characters can be removed. 
// Remove the largest character from s and append it to the result. 
// Remove the largest character from s that is smaller than the last appended 
//character, and append it to the result. 
// Repeat step 5 until no more characters can be removed. 
// Repeat steps 1 through 6 until all characters from s have been removed. 
// 
//
// If the smallest or largest character appears more than once, you may choose 
//any occurrence to append to the result. 
//
// Return the resulting string after reordering s using this algorithm. 
//
// 
// Example 1: 
//
// 
//Input: s = "aaaabbbbcccc"
//Output: "abccbaabccba"
//Explanation: After steps 1, 2 and 3 of the first iteration, result = "abc"
//After steps 4, 5 and 6 of the first iteration, result = "abccba"
//First iteration is done. Now s = "aabbcc" and we go back to step 1
//After steps 1, 2 and 3 of the second iteration, result = "abccbaabc"
//After steps 4, 5 and 6 of the second iteration, result = "abccbaabccba"
// 
//
// Example 2: 
//
// 
//Input: s = "rat"
//Output: "art"
//Explanation: The word "rat" becomes "art" after re-ordering it with the 
//mentioned algorithm.
// 
//
// 
// Constraints: 
//
// 
// 1 <= s.length <= 500 
// s consists of only lowercase English letters. 
// 
//
// Related TopicsHash Table | String | Counting 
//
// 👍 817, 👎 873bug 反馈 | 使用指南 | 更多配套插件 
//
//
//
//
