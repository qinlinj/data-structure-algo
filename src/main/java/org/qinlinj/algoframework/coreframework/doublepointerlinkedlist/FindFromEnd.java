package org.qinlinj.algoframework.coreframework.doublepointerlinkedlist;

import org.qinlinj.leetcode.editor.common.ListNode;

public class FindFromEnd {
    ListNode findFromEnd(ListNode head, int k) {
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
