package org.qinlinj.leetcode.editor.en;

import org.qinlinj.linear.algo.linkedlist.ListNode;

// [25] Reverse Nodes in k-Group
public class ReverseNodesInKGroup {
    public static void main(String[] args) {
        Solution solution = new ReverseNodesInKGroup().new Solution();
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
        public ListNode reverseKGroup(ListNode head, int k) {
            if (head == null || head.next == null) {
                return head;
            }

            ListNode m, n;
            m = n = head;

            for (int i = 0; i < k; i++) {
                if (n == null) {
                    return head;
                }
                n = n.next;
            }

            ListNode nextHead = reverseK(head, k);
            m.next = reverseKGroup(n, k);

            return nextHead;
        }

        private ListNode reverseInterval(ListNode head, int m, int n) {
            if (m == 1) {
                return reverseK(head, n);
            }
            head.next = reverseInterval(head.next, m - 1, n - 1);
            return head;
        }

        ListNode reverseK(ListNode head, int n) {
            if (head == null || head.next == null) {
                return head;
            }
            ListNode pre, cur, nxt;
            pre = null;
            cur = head;
            nxt = head.next;
            while (n > 0) {
                cur.next = pre;
                pre = cur;
                cur = nxt;
                if (nxt != null) {
                    nxt = nxt.next;
                }
                n--;
            }
            head.next = cur;

            return pre;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}