package org.qinlinj.linear.algo.linkedlist.practice;

import org.qinlinj.linear.algo.linkedlist.ListNode;

public class _19_RemoveNthNodeFromEndOfList {
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode subNode = new ListNode(2);
        head.next = subNode;
        _19_RemoveNthNodeFromEndOfList solution = new _19_RemoveNthNodeFromEndOfList();
        System.out.println(solution.removeNthFromEnd(head, 1).toString());
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null || head.next == null) {
            return null;
        }
        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;
        ListNode fast = head;
        ListNode slow = head;
        ListNode prev = dummyHead;

        for (int i = 0; i < n - 1; i++) {
            fast = fast.next;
        }

        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
            prev = prev.next;
        }

        prev.next = slow.next;
        slow = null;

        return head;
    }
}
