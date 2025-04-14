package org.qinlinj.algoframework.coreframework.doublepointerlinkedlist;

import org.qinlinj.leetcode.editor.common.ListNode;

public class RemoveNthFromEnd {
    class Solution {
        public ListNode removeNthFromEnd(ListNode head, int n) {

            ListNode dummy = new ListNode(-1);
            dummy.next = head;

            ListNode x = findFromEnd(dummy, n + 1);

            x.next = x.next.next;
            return dummy.next;
        }

        private ListNode findFromEnd(ListNode head, int k) {
            ListNode p1 = head;

            for (int i = 0; i < k; i++) {
                p1 = p1.next;
            }
            ListNode p2 = head;

            while (p1 != null) {
                p2 = p2.next;
                p1 = p1.next;
            }

            return p2;
        }
    }
}
