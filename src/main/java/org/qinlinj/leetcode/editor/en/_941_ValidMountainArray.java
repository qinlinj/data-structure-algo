package org.qinlinj.leetcode.editor.en;

import java.util.*;
import org.qinlinj.leetcode.editor.common.*;
// [941] Valid Mountain Array
public class _941_ValidMountainArray {
    
    public static void main(String[] args) {
        Solution solution = new _941_ValidMountainArray().new Solution();
        // put your test code here
        
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean validMountainArray(int[] arr) {
            
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


}





//Given an array of integers arr, return true if and only if it is a valid 
//mountain array. 
//
// Recall that arr is a mountain array if and only if: 
//
// 
// arr.length >= 3 
// There exists some i with 0 < i < arr.length - 1 such that: 
// 
// arr[0] < arr[1] < ... < arr[i - 1] < arr[i] 
// arr[i] > arr[i + 1] > ... > arr[arr.length - 1] 
// 
// 
// 
// 
// Example 1: 
// Input: arr = [2,1]
//Output: false
// 
// Example 2: 
// Input: arr = [3,5,5]
//Output: false
// 
// Example 3: 
// Input: arr = [0,3,2,1]
//Output: true
// 
// 
// Constraints: 
//
// 
// 1 <= arr.length <= 10⁴ 
// 0 <= arr[i] <= 10⁴ 
// 
//
// Related TopicsArray 
//
// 👍 3018, 👎 192bug 反馈 | 使用指南 | 更多配套插件 
//
//
//
//
