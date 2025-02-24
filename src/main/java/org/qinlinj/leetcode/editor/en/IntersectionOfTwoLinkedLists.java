package org.qinlinj.leetcode.editor.en;

import org.qinlinj.linear.algo.linkedlist.ListNode;

// [160] Intersection of Two Linked Lists
public class IntersectionOfTwoLinkedLists {
    public static void main(String[] args) {
        Solution solution = new IntersectionOfTwoLinkedLists().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Definition for singly-linked list.
     * public class ListNode {
     * int val;
     * ListNode next;
     * ListNode(int x) {
     * val = x;
     * next = null;
     * }
     * }
     */
    public class Solution {
        public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
            if (headA == null || headB == null) {
                return null;
            }

            ListNode ptrA = headA;
            ListNode ptrB = headB;

            while (ptrA != ptrB) {
                // If the end of the LinkedList is reached, it switches to the head of another LinkedList
                ptrA = (ptrA == null) ? headB : ptrA.next;
                ptrB = (ptrB == null) ? headA : ptrB.next;
            }

            return ptrA;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}