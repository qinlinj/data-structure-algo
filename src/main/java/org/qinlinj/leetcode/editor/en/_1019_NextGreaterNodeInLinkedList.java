package org.qinlinj.leetcode.editor.en;

import org.qinlinj.leetcode.editor.common.ListNode;

import java.util.*;

// [1019] Next Greater Node In Linked List
public class _1019_NextGreaterNodeInLinkedList {

    public static void main(String[] args) {
        Solution solution = new _1019_NextGreaterNodeInLinkedList().new Solution();
        // put your test code here

    }

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Definition for singly-linked list.
     * public class ListNode {
     * int val;
     * ListNode next;
     * ListNode() {}
     * ListNode(int val) { this.val = val; }
     * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     * }
     */
    class Solution {
        public int[] nextLargerNodes(ListNode head) {
            Stack<Integer> stack = new Stack<>();
            ArrayList<Integer> data = new ArrayList<>();
            while (head != null) {
                data.add(head.val);
                head = head.next;
            }
            int[] nodesData = data.stream().mapToInt(Integer::intValue).toArray();
            int[] res = new int[nodesData.length];
            for (int i = nodesData.length - 1; i >= 0; i--) {
                while (!stack.isEmpty() && stack.peek() <= nodesData[i]) {
                    stack.pop();
                }
                if (!stack.isEmpty()) {
                    res[i] = stack.peek();
                } else {
                    res[i] = 0;
                }
                stack.push(nodesData[i]);
            }
            return res;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    class Solution1 {
        public int[] nextLargerNodes(ListNode head) {
            ArrayList<Integer> nums = new ArrayList<>();
            for (ListNode p = head; p != null; p = p.next) {
                nums.add(p.val);
            }
            int[] res = new int[nums.size()];
            Stack<Integer> stk = new Stack<>();
            for (int i = nums.size() - 1; i >= 0; i--) {
                while (!stk.isEmpty() && stk.peek() <= nums.get(i)) {
                    stk.pop();
                }
                res[i] = stk.isEmpty() ? 0 : stk.peek();
                stk.push(nums.get(i));
            }
            return res;
        }
    }
}


//You are given the head of a linked list with n nodes.
//
// For each node in the list, find the value of the next greater node. That is, 
//for each node, find the value of the first node that is next to it and has a 
//strictly larger value than it. 
//
// Return an integer array answer where answer[i] is the value of the next 
//greater node of the iᵗʰ node (1-indexed). If the iᵗʰ node does not have a next 
//greater node, set answer[i] = 0. 
//
// 
// Example 1: 
// 
// 
//Input: head = [2,1,5]
//Output: [5,5,0]
// 
//
// Example 2: 
// 
// 
//Input: head = [2,7,4,3,5]
//Output: [7,0,5,5,0]
// 
//
// 
// Constraints: 
//
// 
// The number of nodes in the list is n. 
// 1 <= n <= 10⁴ 
// 1 <= Node.val <= 10⁹ 
// 
//
// Related TopicsArray | Linked List | Stack | Monotonic Stack 
//
// 👍 3378, 👎 121bug 反馈 | 使用指南 | 更多配套插件 
//
//
//
//
