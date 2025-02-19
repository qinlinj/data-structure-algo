package org.qinlinj.linear.algo.linkedlist.practice;

import org.qinlinj.linear.algo.linkedlist.ListNode;

public class _203_RemoveLinkedListElements {
    class Solution {
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
}
