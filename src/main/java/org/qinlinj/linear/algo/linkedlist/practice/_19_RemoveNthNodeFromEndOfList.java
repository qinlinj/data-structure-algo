package org.qinlinj.linear.algo.linkedlist.practice;

import org.qinlinj.linear.algo.linkedlist.ListNode;

public class _19_RemoveNthNodeFromEndOfList {
    static class Solution1 {
        public static void main(String[] args) {
            ListNode head = new ListNode(1);
            ListNode subNode = new ListNode(2);
            head.next = subNode;
            Solution1 solution = new Solution1();
            System.out.println(solution.removeNthFromEnd(head, 2).toString());
        }

        public ListNode removeNthFromEnd(ListNode head, int n) {
            if (head == null) return null;
            ListNode dummyNode = new ListNode(-1);
            dummyNode.next = head;

            ListNode slow = dummyNode;
            ListNode fast = dummyNode;

            while (n-- > 0) {
                fast = fast.next;
            }

            while (fast.next != null) {
                slow = slow.next;
                fast = fast.next;
            }

            ListNode delNode = slow.next;
            slow.next = delNode.next;
            delNode.next = null;

            return dummyNode.next;
        }
    }

}
