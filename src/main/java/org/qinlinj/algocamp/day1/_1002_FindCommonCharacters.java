package org.qinlinj.algocamp.day1;

import java.util.*;

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
