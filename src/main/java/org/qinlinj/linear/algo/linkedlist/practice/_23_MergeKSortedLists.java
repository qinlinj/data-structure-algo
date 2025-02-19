package org.qinlinj.linear.algo.linkedlist.practice;

import org.qinlinj.linear.algo.linkedlist.ListNode;

public class _23_MergeKSortedLists {
    class Solution1 {
        public ListNode mergeKLists(ListNode[] lists) {
            if (lists == null || lists.length == 0) {
                return null;
            }
            if (lists.length == 1) {
                return lists[0];
            }
            // BU merge sort
            for (int size = 1; size < lists.length; size = size * 2) {
                for (int j = 0; j < lists.length - size; j += size * 2) {
                    if (j + size < lists.length) {
                        lists[j] = merge(lists[j], lists[j + size]);
                    }
                }
            }
            return lists[0];
        }

        public ListNode merge(ListNode list1, ListNode list2) {
            if (list1 == null) {
                return list2;
            }
            if (list2 == null) {
                return list1;
            }

            if (list1.val <= list2.val) {
                list1.next = merge(list1.next, list2);
                return list1;
            } else {
                list2.next = merge(list1, list2.next);
                return list2;
            }
        }
    }

    class Solution2 {
        class Solution {
            public ListNode mergeKLists(ListNode[] lists) {
                if (lists == null || lists.length == 0) {
                    return null;
                }
                if (lists.length == 1) {
                    return lists[0];
                }
                // merge sort
                return segment(lists, 0, lists.length - 1);
            }

            public ListNode segment(ListNode[] lists, int left, int right) {
                if (left == right) {
                    return lists[left];
                }
                int mid = left + (right - left) / 2;
                ListNode leftNode = segment(lists, left, mid);
                ListNode rightNode = segment(lists, mid + 1, right);
                return merge(leftNode, rightNode);
            }

            public ListNode merge(ListNode list1, ListNode list2) {
                if (list1 == null) {
                    return list2;
                }
                if (list2 == null) {
                    return list1;
                }

                if (list1.val <= list2.val) {
                    list1.next = merge(list1.next, list2);
                    return list1;
                } else {
                    list2.next = merge(list1, list2.next);
                    return list2;
                }
            }
        }
    }
}
