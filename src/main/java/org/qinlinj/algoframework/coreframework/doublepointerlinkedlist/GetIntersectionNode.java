package org.qinlinj.algoframework.coreframework.doublepointerlinkedlist;

import org.qinlinj.leetcode.editor.common.ListNode;

public class GetIntersectionNode {
    class Solution {
        public ListNode getIntersectionNode(ListNode headA, ListNode headB) {

            ListNode p1 = headA, p2 = headB;
            while (p1 != p2) {

                if (p1 == null) {
                    p1 = headB;
                } else {
                    p1 = p1.next;
                }

                if (p2 == null) {
                    p2 = headA;
                } else {
                    p2 = p2.next;
                }
            }
            return p1;
        }
    }

    class Solution1 {
        public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
            int lenA = 0, lenB = 0;

            for (ListNode p1 = headA; p1 != null; p1 = p1.next) {
                lenA++;
            }
            for (ListNode p2 = headB; p2 != null; p2 = p2.next) {
                lenB++;
            }

            ListNode p1 = headA, p2 = headB;
            if (lenA > lenB) {
                for (int i = 0; i < lenA - lenB; i++) {
                    p1 = p1.next;
                }
            } else {
                for (int i = 0; i < lenB - lenA; i++) {
                    p2 = p2.next;
                }
            }

            while (p1 != p2) {
                p1 = p1.next;
                p2 = p2.next;
            }
            return p1;
        }
    }
}
