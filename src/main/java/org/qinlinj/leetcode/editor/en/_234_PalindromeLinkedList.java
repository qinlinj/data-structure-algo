package org.qinlinj.leetcode.editor.en;

import org.qinlinj.linear.algo.linkedlist.ListNode;

// [234] Palindrome Linked List
public class _234_PalindromeLinkedList {
    public static void main(String[] args) {
        Solution solution = new _234_PalindromeLinkedList().new Solution();
        ListNode head = new ListNode(1);
        ListNode node1 = new ListNode(4);
        ListNode node2 = new ListNode(3);
        ListNode node3 = new ListNode(2);
        ListNode node4 = new ListNode(1);
        head.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = null;
        System.out.println(solution.isPalindrome(head));
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
        public boolean isPalindrome(ListNode head) {
            if (head == null || head.next == null) {
                return true;
            }
            ListNode mid = fineMidNode(head);
            mid = reverseList(mid);
            return compareTwoLists(head, mid);
        }

        private ListNode fineMidNode(ListNode head) {
            ListNode slow = head;
            ListNode fast = head.next;
            while (fast != null && fast.next != null) {
                fast = fast.next.next;
                slow = slow.next;
            }
            return slow;
        }

        public boolean compareTwoLists(ListNode list1, ListNode list2) {
            if (list1.val == list2.val && list1.next != null && list2.next != null) {
                return compareTwoLists(list1.next, list2.next);
            } else return list1.val == list2.val && list1.next == null && list2.next == null;
        }

        public ListNode reverseList(ListNode head) {
            if (head == null || head.next == null) {
                return head;
            }
            ListNode curr = reverseList(head.next);
            head.next.next = head;
            head.next = null;
            return curr;
        }
    }

    //leetcode submit region end(Prohibit modification and deletion)
    // simple solution
    class Solution2 {
        public boolean isPalindrome(ListNode head) {
            if (head == null) {
                return true;
            }
            ListNode reversedList = copyList(head);
            reversedList = reverseList(reversedList);
            return compareTwoLists(head, reversedList);
        }

        private ListNode copyList(ListNode head) {
            if (head == null) return null;
            ListNode newNode = new ListNode(head.val);
            newNode.next = copyList(head.next);
            return newNode;
        }

        public boolean compareTwoLists(ListNode list1, ListNode list2) {
            if (list1.val == list2.val && list1.next != null && list2.next != null) {
                return compareTwoLists(list1.next, list2.next);
            } else return list1.val == list2.val && list1.next == null && list2.next == null;
        }

        public ListNode reverseList(ListNode head) {
            if (head == null || head.next == null) {
                return head;
            }
            ListNode curr = reverseList(head.next);
            head.next.next = head;
            head.next = null;
            return curr;
        }
    }
}