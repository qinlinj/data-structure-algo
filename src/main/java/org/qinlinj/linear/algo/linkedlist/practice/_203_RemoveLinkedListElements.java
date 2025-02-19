package org.qinlinj.linear.algo.linkedlist.practice;

import org.qinlinj.linear.algo.linkedlist.ListNode;

public class _203_RemoveLinkedListElements {
    class Solution1 {
        public ListNode removeElements(ListNode head, int val) {
            if (head == null) {
                return head;
            }
            ListNode dummyhead = new ListNode(-1);
            dummyhead.next = head;
            ListNode curr = dummyhead.next;
            ListNode prev = dummyhead;
            while (curr != null) {
                if (curr.val == val) {
                    ListNode newCurr = curr.next;
                    curr = null;
                    prev.next = newCurr;
                    curr = prev.next;
                } else {
                    curr = curr.next;
                    prev = prev.next;
                }

            }
            return dummyhead.next;
        }
    }

    class Solution2 {
        public ListNode removeElements(ListNode head, int val) {
            ListNode dummyNode = new ListNode(-1);
            dummyNode.next = head;
            ListNode curr = head;
            ListNode prev = dummyNode;
            while (curr != null) {
                if (curr.val == val) {
                    prev.next = curr.next;
                    curr.next = null;
                } else {
                    prev = curr;
                }
                curr = prev.next;
            }
            return dummyNode.next;
        }
    }
}
