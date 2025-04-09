package org.qinlinj.algocamp.day1;

public class _1370_IncreasingDecreasingString {
    public static void main(String[] args) {
        Solution solution = new _1370_IncreasingDecreasingString().new Solution();
        // put your test code here
        solution.sortString("aaaabbbbcccc");

    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String sortString(String s) {
            char[] data = s.toCharArray();
            int n = data.length;
            int[] freqChar = new int[26];

            for (int i = 0; i < n; i++) {
                freqChar[(data[i] - 'a')]++;
            }

            StringBuilder sb = new StringBuilder();

            int count = 0;
            while (count < n) {
                for (int i = 0; i < 26; i++) {
                    if (freqChar[i] > 0) {
                        sb.append((char) (i + 'a'));
                        freqChar[i]--;
                    }
                }
                for (int i = 25; i >= 0; i--) {
                    if (freqChar[i] > 0) {
                        sb.append((char) (i + 'a'));
                        freqChar[i]--;
                    }
                }
                count++;
            }

            return sb.toString();
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


}
