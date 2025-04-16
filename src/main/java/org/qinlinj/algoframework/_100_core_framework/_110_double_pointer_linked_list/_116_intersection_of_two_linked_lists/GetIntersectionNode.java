package org.qinlinj.algoframework._100_core_framework._110_double_pointer_linked_list._6_intersection_of_two_linked_lists;

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


    /**
     * Solution 2: Length Difference Approach
     *
     * Algorithm Strategy: Offset Pointers Based on List Lengths
     *
     * Key Concept:
     * - Calculate lengths of both lists
     * - Move longer list's pointer forward to align lengths
     * - Synchronously traverse to find intersection
     *
     * Visualization:
     * ListA: 1 -> 2 -> 3 -> 4 -> 5
     * ListB:      6 -> 7 -> 4 -> 5
     *
     * Steps:
     * 1. Calculate list lengths
     * 2. Offset longer list's pointer
     * 3. Synchronous traversal to find intersection
     *
     * Scenarios Handled:
     * 1. Lists of different lengths
     * 2. No intersection (returns null)
     * 3. Partial or full intersection
     *
     * Time Complexity: O(m + n)
     * Space Complexity: O(1)
     */
    class Solution1 {
        public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
            // Calculate lengths of both lists
            int lenA = 0, lenB = 0;

            // Calculate length of list A
            for (ListNode p1 = headA; p1 != null; p1 = p1.next) {
                lenA++;
            }

            // Calculate length of list B
            for (ListNode p2 = headB; p2 != null; p2 = p2.next) {
                lenB++;
            }

            // Align pointers by offsetting the longer list
            ListNode p1 = headA, p2 = headB;

            // If list A is longer, move p1 forward
            if (lenA > lenB) {
                for (int i = 0; i < lenA - lenB; i++) {
                    p1 = p1.next;
                }
            }
            // If list B is longer, move p2 forward
            else {
                for (int i = 0; i < lenB - lenA; i++) {
                    p2 = p2.next;
                }
            }

            // Synchronous traversal to find intersection
            // Two possible outcomes:
            // 1. No intersection: pointers reach null simultaneously
            // 2. Intersection found: pointers meet at intersection node
            while (p1 != p2) {
                p1 = p1.next;
                p2 = p2.next;
            }

            // Return intersection point (or null if no intersection)
            return p1;
        }
    }
}
