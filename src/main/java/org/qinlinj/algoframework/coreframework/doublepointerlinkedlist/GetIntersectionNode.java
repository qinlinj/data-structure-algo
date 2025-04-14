package org.qinlinj.algoframework.coreframework.doublepointerlinkedlist;

import org.qinlinj.leetcode.editor.common.ListNode;

// @formatter:off
public class GetIntersectionNode {
    /**
     * Solution 1: Clever Pointer Swap Approach
     *
     * Algorithm Strategy: Synchronized Traversal with Pointer Redirection
     *
     * Key Concept:
     * - Use two pointers that swap lists when they reach the end
     * - This equalizes the total traversal distance
     * - Guarantees finding intersection point or null
     *
     * Visualization:
     * ListA:    1 -> 2 -> 3 -> 4 -> 5
     * ListB:         6 -> 7 -> 4 -> 5
     *
     * Pointer Movement:
     * p1 traverses A, then switches to B
     * p2 traverses B, then switches to A
     *
     * Scenarios Handled:
     * 1. Lists of equal length
     * 2. Lists of different lengths
     * 3. No intersection (returns null)
     * 4. Partial or full intersection
     *
     * Time Complexity: O(m + n)
     * Space Complexity: O(1)
     */
    class Solution {
        public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
            // Initialize pointers to heads of both lists
            ListNode p1 = headA, p2 = headB;

            // Continue until pointers meet
            while (p1 != p2) {
                // If p1 reaches end of list A, redirect to head of list B
                if (p1 == null) {
                    p1 = headB;
                } else {
                    // Move p1 to next node in list A
                    p1 = p1.next;
                }

                // If p2 reaches end of list B, redirect to head of list A
                if (p2 == null) {
                    p2 = headA;
                } else {
                    // Move p2 to next node in list B
                    p2 = p2.next;
                }
            }

            // Return intersection point (or null if no intersection)
            return p1;
        }
    }


    class Solution1 {
        public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
            int lenA = 0, lenB = 0;

            for (ListNode p1 = headA; p1 != null; p1 = p1.next) {
                lenA++;
            }
            for (ListNode p2 = headB; p2 != null; p2 = p2.next) {
                lenB++;
            }

            ListNode p1 = headA, p2 = headB;
            if (lenA > lenB) {
                for (int i = 0; i < lenA - lenB; i++) {
                    p1 = p1.next;
                }
            } else {
                for (int i = 0; i < lenB - lenA; i++) {
                    p2 = p2.next;
                }
            }

            while (p1 != p2) {
                p1 = p1.next;
                p2 = p2.next;
            }
            return p1;
        }
    }
}
