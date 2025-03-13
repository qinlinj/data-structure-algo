package org.qinlinj.leetcode.editor.en;

import org.qinlinj.linear.algo.linkedlist.ListNode;

// [86] Partition List
public class _86_PartitionList {
    public static void main(String[] args) {
        Solution solution = new _86_PartitionList().new Solution();
        ListNode head = new ListNode(1);
        ListNode node1 = new ListNode(4);
        ListNode node2 = new ListNode(3);
        ListNode node3 = new ListNode(2);
        ListNode node4 = new ListNode(5);
        ListNode node5 = new ListNode(2);
        head.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = null;
        System.out.println(solution.partition(head, 3).toString());
    }
//leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Definition for singly-linked list.
     * public class ListNode {
     * int val;
     * ListNode next;
     * ListNode() {}
     * ListNode(int val) { this.val = val; }
     * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     * }
     */
    class Solution {
        public ListNode partition(ListNode head, int x) {
            ListNode dummy1 = new ListNode(-1);
            ListNode dummy2 = new ListNode(-1);
            ListNode p1 = dummy1, p2 = dummy2;

            ListNode p = head;
            while (p != null) {
                if (p.val >= x) {
                    p2.next = p;
                    p2 = p2.next;
                } else {
                    p1.next = p;
                    p1 = p1.next;
                }
                ListNode temp = p.next;
                p.next = null;
                p = temp;
            }

            p1.next = dummy2.next;

            return dummy1.next;
        }
    }


    //leetcode submit region end(Prohibit modification and deletion)
    class Solution1 {
        // operating in only one linked list
        public ListNode partition(ListNode head, int x) {
            if (head == null) {
                return null;
            }
            ListNode dummyHead = new ListNode(-1);
            dummyHead.next = head;
            ListNode fast = dummyHead;
            while (fast != null && fast.next != null) {
                ListNode nextNode = fast.next;
                if (nextNode != null && nextNode.val < x) {
                    ListNode slow = dummyHead;
                    while (slow.next != nextNode && slow.next.val < x) {
                        slow = slow.next;
                    }
                    if (slow.next != nextNode) {
                        fast.next = nextNode.next;
                        nextNode.next = slow.next;
                        slow.next = nextNode;
                        continue;
                    }
                }
                fast = fast.next;
            }
            return dummyHead.next;
        }
    }
}