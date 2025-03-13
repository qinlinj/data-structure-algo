package org.qinlinj.leetcode.editor.en;

import org.qinlinj.linear.algo.linkedlist.ListNode;

// [23] Merge k Sorted Lists
public class _23_MergeKSortedLists {
    public static void main(String[] args) {
        Solution solution = new _23_MergeKSortedLists().new Solution();
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
        // BU
        public ListNode mergeKLists(ListNode[] lists) {
            if (lists == null) {
                return null;
            }
            if (lists.length < 1) {
                return null;
            }

            for (int size = 1; size < lists.length; size = size * 2) {
                for (int j = 0; j + size < lists.length; j = j + size * 2) {
                    lists[j] = mergeTwoList(lists[j], lists[j + size]);
                }
            }
            return lists[0];
        }

        public ListNode mergeTwoList(ListNode node1, ListNode node2) {
            if (node1 == null) return node2;
            if (node2 == null) return node1;
            ListNode dummyHead = new ListNode(-1);
            ListNode p = dummyHead;
            while (node1 != null && node2 != null) {
                if (node1.val <= node2.val) {
                    p.next = node1;
                    node1 = node1.next;
                } else {
                    p.next = node2;
                    node2 = node2.next;
                }
                p = p.next;
            }
            p.next = node1 != null ? node1 : node2;

            return dummyHead.next;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}