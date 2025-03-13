package org.qinlinj.leetcode.editor.en;

// [875] Koko Eating Bananas
public class _875_KokoEatingBananas {
    public static void main(String[] args) {
        Solution solution = new _875_KokoEatingBananas().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int minEatingSpeed(int[] piles, int h) {
            int right = Integer.MIN_VALUE;
            int left = 1;
            for (int pile : piles) {
                right = Math.max(right, pile);
            }
            while (left < right) {
                int mid = left + (right - left) / 2;
                int t = usedTime(piles, mid);
                if (t == h) {
                    right = mid;
                } else if (t < h) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }
            return left;
        }

        public int usedTime(int[] piles, int mid) {
            int count = 0;
            for (int i = 0; i < piles.length; i++) {
                count += piles[i] / mid;
                if (piles[i] % mid > 0) {
                    count++;
                }
            }
            return count;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}


//Koko loves to eat bananas. There are n piles of bananas, the iᵗʰ pile has
//piles[i] bananas. The guards have gone and will come back in h hours. 
//
// Koko can decide her bananas-per-hour eating speed of k. Each hour, she 
//chooses some pile of bananas and eats k bananas from that pile. If the pile has less 
//than k bananas, she eats all of them instead and will not eat any more bananas 
//during this hour. 
//
// Koko likes to eat slowly but still wants to finish eating all the bananas 
//before the guards return. 
//
// Return the minimum integer k such that she can eat all the bananas within h 
//hours. 
//
// 
// Example 1: 
//
// 
//Input: piles = [3,6,7,11], h = 8
//Output: 4
// 
//
// Example 2: 
//
// 
//Input: piles = [30,11,23,4,20], h = 5
//Output: 30
// 
//
// Example 3: 
//
// 
//Input: piles = [30,11,23,4,20], h = 6
//Output: 23
// 
//
// 
// Constraints: 
//
// 
// 1 <= piles.length <= 10⁴ 
// piles.length <= h <= 10⁹ 
// 1 <= piles[i] <= 10⁹ 
// 
//
// Related TopicsArray | Binary Search 
//
// 👍 11675, 👎 764bug 反馈 | 使用指南 | 更多配套插件 
//
//
//
//
