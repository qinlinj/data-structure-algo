package org.qinlinj.linear.algo.linkedlist.practice;

import org.qinlinj.linear.algo.linkedlist.ListNode;

public class _876_MiddleOfTheLinkedList {
    public ListNode middleNode1(ListNode head) {
        if (head == null || head.next == null) return head;
        int length = 0;
        ListNode curr = head;
        while (curr != null) { // O(n)
            length++;
            curr = curr.next;
        }
        return head;
    }

    class Solution1 {
        public ListNode middleNode(ListNode head) {
            if (head == null || head.next == null) return head;

            ListNode slow = head;
            ListNode fast = head;

            while (fast != null && fast.next != null) {
                slow = slow.next;
                fast = fast.next.next;
            }

            return slow;
        }
    }
}
