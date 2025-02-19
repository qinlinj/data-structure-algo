package org.qinlinj.linear.algo.linkedlist.practice;

import org.qinlinj.linear.algo.linkedlist.ListNode;

public class _148_SortList {
    class Solution1 {
        public ListNode sortList(ListNode head) {
            if (head == null || head.next == null) {
                return head;
            }
            ListNode slow = head;
            ListNode fast = head.next;
            while (fast != null && fast.next != null) {
                slow = slow.next;
                fast = fast.next.next;
            }
            ListNode rightHead = slow.next;
            slow.next = null;
            ListNode left = sortList(head);
            ListNode right = sortList(rightHead);
            return merge(left, right);
        }

        public ListNode merge(ListNode head1, ListNode head2) {
            if (head1 == null) {
                return head2;
            }
            if (head2 == null) {
                return head1;
            }

            ListNode dummyNode = new ListNode(-1);
            ListNode curr = dummyNode;

            while (head1 != null && head2 != null) {
                if (head1.val <= head2.val) {
                    curr.next = head1;
                    head1 = head1.next;
                } else {
                    curr.next = head2;
                    head2 = head2.next;
                }
                curr = curr.next;
            }

            if (head1 == null)
                curr.next = head2;
            if (head2 == null)
                curr.next = head1;

            return dummyNode.next;
        }
    }
}
