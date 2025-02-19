package org.qinlinj.linear.algo.linkedlist.practice;

import org.qinlinj.linear.algo.linkedlist.ListNode;

public class _876_MiddleOfTheLinkedList {
    class Solution1 {
        public ListNode middleNode(ListNode head) {
            if (head == null || head.next == null) {
                return head;
            }
            ListNode dummyHead = new ListNode(-1);
            dummyHead.next = head;
            ListNode fast = dummyHead;
            ListNode slow = head;
            while (fast != null && fast.next != null && fast.next.next != null) {
                slow = slow.next;
                fast = fast.next.next;
            }
            return slow;
        }
    }
}
