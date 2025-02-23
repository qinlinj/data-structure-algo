package org.qinlinj.leetcode.editor.en;

import org.qinlinj.linear.algo.linkedlist.ListNode;

// [83] Remove Duplicates from Sorted List
public class RemoveDuplicatesFromSortedList {
    public static void main(String[] args) {
        Solution solution = new RemoveDuplicatesFromSortedList().new Solution();
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
        public ListNode deleteDuplicates(ListNode head) {
            if (head == null || head.next == null) {
                return head;
            }

            ListNode cur = head;
            while (cur != null && cur.next != null) {
                if (cur.val == cur.next.val) {
                    cur.next = cur.next.next;
                } else {
                    cur = cur.next;
                }
            }
            return head;
        }
    }

    //leetcode submit region end(Prohibit modification and deletion)
    // redundant version
    class Solution1 {
        public ListNode deleteDuplicates(ListNode head) {
            if (head == null || head.next == null) {
                return head;
            }

            ListNode slow = head;
            ListNode fast = head.next;

            while (fast != null) {
                if (slow.val == fast.val) {
                    slow.next = fast.next;
                    fast = fast.next;
                } else {
                    slow = slow.next;
                    fast = fast.next;
                }
            }

            return head;
        }
    }

    // better solution
    class Solution2 {
        public ListNode deleteDuplicates(ListNode head) {
            if (head == null) return null;
            ListNode slow = head, fast = head;
            while (fast != null) {
                if (fast.val != slow.val) {
                    // nums[slow] = nums[fast];
                    slow.next = fast;
                    // slow++;
                    slow = slow.next;
                }
                // fast++
                fast = fast.next;
            }
            slow.next = null;
            return head;
        }
    }


}