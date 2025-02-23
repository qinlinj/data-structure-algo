package org.qinlinj.leetcode.editor.en;

import org.qinlinj.linear.algo.linkedlist.ListNode;

// [83] Remove Duplicates from Sorted List
public class RemoveDuplicatesFromSortedList {
    public static void main(String[] args) {
        Solution solution = new RemoveDuplicatesFromSortedList().new Solution();
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
        public ListNode deleteDuplicates(ListNode head) {
            if (head == null || head.next == null) {
                return head;
            }
            ListNode dummyHead = new ListNode(-1);
            dummyHead.next = head;
            ListNode fast = head.next;
            ListNode slow = head;
            while (fast != null) {
                if (fast.val == slow.val && fast.next != null) {
                    slow.next = slow.next.next;
                    slow = slow.next;
                    fast = slow.next;
                } else if (fast.val == slow.val) {
                    slow.next = null;
                } else {
                    fast = fast.next;
                    slow = slow.next;
                }
            }
            return head;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}