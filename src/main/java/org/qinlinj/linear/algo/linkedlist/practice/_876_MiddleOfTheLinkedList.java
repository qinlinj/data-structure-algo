package org.qinlinj.linear.algo.linkedlist.practice;

import org.qinlinj.linear.algo.linkedlist.ListNode;

public class _876_MiddleOfTheLinkedList {
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

    class Solution2 {
        public ListNode middleNode1(ListNode head) {
            if (head == null || head.next == null) return head;
            int length = 0;
            ListNode curr = head;
            while (curr != null) { // O(n)
                length++;
                curr = curr.next;
            }
            for (int i = 0; i < length / 2; i++) { // O(n/2)
                head = head.next;
            }

            return head;
        }
    }
}
