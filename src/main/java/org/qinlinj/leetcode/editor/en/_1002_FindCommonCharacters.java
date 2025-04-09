package org.qinlinj.leetcode.editor.en;

import java.util.*;

// [1002] Find Common Characters
public class _1002_FindCommonCharacters {

    public static void main(String[] args) {
        Solution solution = new _1002_FindCommonCharacters().new Solution();
        // put your test code here
        String[] data = new String[]{"bella", "label", "roller"};
        solution.commonChars(data);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<String> commonChars(String[] words) {
            int n = words.length;
            int[] res = new int[26];
            char[] data = words[0].toCharArray();

            for (char datum : data) {
                res[datum - 'a']++;
            }

            for (int i = 1; i < n; i++) {
                int[] currRes = new int[26];
                for (char datum : words[i].toCharArray()) {
                    currRes[datum - 'a']++;
                }
                for (int j = 0; j < 26; j++) {
                    res[j] = Math.min(res[j], currRes[j]);
                }
            }

            ArrayList<String> list = new ArrayList<>();
            for (int i = 0; i < 26; i++) {
                if (res[i] > 0) {
                    while (res[i] > 0) {
                        list.add(String.valueOf((char) ('a' + i)));
                        res[i]--;
                    }
                }
            }
            return list;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


}


//Given a string array words, return an array of all characters that show up in
//all strings within the words (including duplicates). You may return the answer 
//in any order. 
//
// 
// Example 1: 
// Input: words = ["bella","label","roller"]
//Output: ["e","l","l"]
// 
// Example 2: 
// Input: words = ["cool","lock","cook"]
//Output: ["c","o"]
// 
// 
// Constraints: 
//
// 
// 1 <= words.length <= 100 
// 1 <= words[i].length <= 100 
// words[i] consists of lowercase English letters. 
// 
//
// Related TopicsArray | Hash Table | String 
//
// 👍 4364, 👎 423bug 反馈 | 使用指南 | 更多配套插件 
//
//
//
//
