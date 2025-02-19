package org.qinlinj.linear.algo.linkedlist.practice;

import org.qinlinj.linear.algo.linkedlist.ListNode;

public class _21_MergeTwoSortedLists {
    class Solution {
        public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
            if (list1 == null) {
                return list2;
            }
            if (list2 == null) {
                return list1;
            }
            ListNode dummyHead = new ListNode(-1);
            ListNode currNode = dummyHead;

            while (list1 != null && list2 != null) {
                if (list1.val <= list2.val) {
                    currNode.next = list1;
                    list1 = list1.next;
                } else {
                    currNode.next = list2;
                    list2 = list2.next;
                }
                currNode = currNode.next;
            }
            if (list1 == null) currNode.next = list2;
            else currNode.next = list1;
            return dummyHead.next;
        }
    }
}
