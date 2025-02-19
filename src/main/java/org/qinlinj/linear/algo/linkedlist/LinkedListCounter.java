package org.qinlinj.linear.algo.linkedlist;

public class LinkedListCounter {
    public static void main(String[] args) {
        ListNode head = ListNode.fromArray(new int[]{23, 12, 11, 34, 12});
        int cnt = new LinkedListCounter().countTarget(head, 12);
        System.out.println(cnt);
    }

    /**
     * Calculate the number of nodes in a linked list
     *
     * @param head
     * @return
     */
    public int count(ListNode head) {
        if (head == null) return 0;
        int cnt = 0;
        ListNode curr = head;
        while (curr != null) {
            cnt++;
            curr = curr.next;
        }
        return cnt;
    }

    public int countFor(ListNode head) {
        if (head == null) return 0;
        int cnt = 0;
        for (ListNode curr = head; curr != null; curr = curr.next) {
            cnt++;
        }
        return cnt;
    }

    /**
     * Calculates the number of nodes in the linked list equal to the target value
     *
     * @param head
     * @param target
     * @return
     */
    public int countTarget(ListNode head, int target) {
        if (head == null) return 0;
        int cnt = 0;
        ListNode curr = head;
        // 使用 while 循环，遍历整个链表
        while (curr != null) {
            if (curr.val == target) cnt++;
            curr = curr.next;
        }
        return cnt;
    }
}