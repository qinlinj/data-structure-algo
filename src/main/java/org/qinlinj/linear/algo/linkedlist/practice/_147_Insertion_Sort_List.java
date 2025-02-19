package org.qinlinj.linear.algo.linkedlist.practice;

import org.qinlinj.linear.algo.linkedlist.ListNode;

public class _147_Insertion_Sort_List {
    class Solution1 {
        public ListNode insertionSortList(ListNode head) {
            if (head == null || head.next == null) {
                return head;
            }
            ListNode dummyNode = new ListNode(-1);
            dummyNode.next = head;

            ListNode curr = head.next;
            ListNode lastSorted = head;
            while (curr != null) {
                if (lastSorted.val <= curr.val) {
                    lastSorted = curr;
                } else {
                    ListNode prev = dummyNode;
                    while (prev.next.val <= curr.val) {
                        prev = prev.next;
                    }
                    lastSorted.next = curr.next;
                    curr.next = prev.next;
                    prev.next = curr;
                }
                curr = lastSorted.next;
            }

            return dummyNode.next;
        }

        public void swap(ListNode listPrev1, ListNode listPrev2) {
            if (listPrev1 == null || listPrev2 == null ||
                    listPrev1.next == null || listPrev2.next == null) {
                return;
            }

            ListNode list1 = listPrev1.next;
            ListNode list2 = listPrev2.next;

            if (list1.next == list2) {
                list1.next = list2.next;
                list2.next = list1;
                listPrev1.next = list2;
            } else if (list2.next == list1) {
                list2.next = list1.next;
                list1.next = list2;
                listPrev2.next = list1;
            } else {
                ListNode list1Next = list1.next;
                ListNode list2Next = list2.next;

                listPrev1.next = list2;
                listPrev2.next = list1;
                list1.next = list2Next;
                list2.next = list1Next;
            }
        }
    }
}
