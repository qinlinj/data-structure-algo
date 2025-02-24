package org.qinlinj.leetcode.editor.en;

import org.qinlinj.linear.algo.linkedlist.ListNode;

// [92] Reverse Linked List II
public class ReverseLinkedListIi {
    public static void main(String[] args) {
        Solution solution = new ReverseLinkedListIi().new Solution();
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
        public ListNode reverseBetween(ListNode head, int left, int right) {
            if (head == null || head.next == null || left == right) {
                return head;
            }
            if (left == 1) {
                return reverseK(head, right);
            }
            head.next = reverseBetween(head.next, left - 1, right - 1);
            return head;
        }

        public ListNode reverseK(ListNode head, int k) {
            ListNode prev = null;
            ListNode curr = head;
            ListNode next = null;
            ListNode tail = curr;
            int count = 0;
            while (curr != null && count < k) {
                next = curr.next;
                curr.next = prev;
                prev = curr;
                curr = next;
                count++;
            }
            tail.next = curr;
            return prev;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}