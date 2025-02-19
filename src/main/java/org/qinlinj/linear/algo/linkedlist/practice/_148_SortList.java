package org.qinlinj.linear.algo.linkedlist.practice;

import org.qinlinj.linear.algo.linkedlist.ListNode;

public class _148_SortList {
    // recursion
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

    // BU iteration
    class Solution2 {
        // 自底朝上实现归并排序
        public ListNode sortList(ListNode head) {
            if (head == null || head.next == null)
                return head;

            ListNode dummy = new ListNode(-1);
            dummy.next = head;
            // 计算链表的长度
            int length = 0;
            while (head != null) {
                length++;
                head = head.next;
            }
            // bug 修复：step 从 1 开始，而不是从 0 开始
            for (int step = 1; step < length; step <<= 1) {
                ListNode prev = dummy;
                ListNode curr = dummy.next;
                while (curr != null) {
                    ListNode left = curr;
                    ListNode right = split(left, step);
                    // 分割得到下次处理的链表头
                    curr = split(right, step);
                    // 合并 left 和 right 链表
                    prev = merge(left, right, prev);
                }
            }

            return dummy.next;
        }

        // 合并 left 和 right 两个有序链表
        // 将 prev.next 设置为合并之后链表的表头
        // 返回合并之后链表的最后一个节点
        private ListNode merge(ListNode left, ListNode right, ListNode prev) {
            ListNode curr = prev;
            while (left != null && right != null) {
                if (left.val <= right.val) {
                    curr.next = left;
                    left = left.next;
                } else {
                    curr.next = right;
                    right = right.next;
                }
                curr = curr.next;
            }
            if (left == null) curr.next = right;
            if (right == null) curr.next = left;
            // 保证 curr 是合并之后链表的最后一个节点
            // bug 修复：使用 while 循环找到最后一个节点
            while (curr.next != null) curr = curr.next;
            return curr;
        }

        // 将 node 从第 step 个节点切断，并返回右边链表的头节点
        private ListNode split(ListNode node, int step) {
            if (node == null) return null;
            // 找到分割点
            for (int i = 1; node.next != null && i < step; i++) {
                node = node.next;
            }
            ListNode right = node.next;
            node.next = null;

            return right;
        }
    }
}
