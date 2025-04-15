package org.qinlinj.algoframework._1_core_framework._1_double_pointer_linked_list._1_merge_k_sorted_lists;

import org.qinlinj.leetcode.editor.common.ListNode;

public class MergeTwoLists {
    /**
     * Merges two sorted linked lists into a single sorted linked list
     * <p>
     * Algorithm Strategy: Two-Pointer Comparison Merge
     * <p>
     * Key Concepts:
     * - Use dummy head to simplify list construction
     * - Compare nodes from both lists
     * - Always select the smaller node
     * - Attach remaining nodes if one list is longer
     * <p>
     * Visualization:
     * List 1: 1 -> 3 -> 5
     * List 2: 2 -> 4 -> 6
     * <p>
     * Merge Process:
     * Step 1: Select 1 from List 1
     * Step 2: Select 2 from List 2
     * Step 3: Select 3 from List 1
     * ...continues until all nodes are merged
     * <p>
     * Final Output:
     * 1 -> 2 -> 3 -> 4 -> 5 -> 6
     * <p>
     * Advantages:
     * - In-place merging
     * - Single pass through both lists
     * - Maintains sorted order
     * <p>
     * Time Complexity: O(n + m)
     * - n: Length of first list
     * - m: Length of second list
     * <p>
     * Space Complexity: O(1)
     * - Only creates a dummy node
     * - Reuses existing nodes
     *
     * @param l1 Head of first sorted linked list
     * @param l2 Head of second sorted linked list
     * @return Head of merged sorted linked list
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        // Create dummy head to simplify list construction
        // Dummy node allows easy return of merged list
        ListNode dummy = new ListNode(-1), p = dummy;

        // Create pointers for traversing input lists
        ListNode p1 = l1, p2 = l2;

        // Compare and merge while both lists have nodes
        while (p1 != null && p2 != null) {
            // Compare current nodes of both lists
            if (p1.val > p2.val) {
                // Attach smaller node (from l2)
                p.next = p2;
                // Move l2 pointer forward
                p2 = p2.next;
            } else {
                // Attach smaller node (from l1)
                p.next = p1;
                // Move l1 pointer forward
                p1 = p1.next;
            }

            // Move merged list pointer forward
            p = p.next;
        }

        // Attach remaining nodes from l1 if any
        if (p1 != null) {
            p.next = p1;
        }

        // Attach remaining nodes from l2 if any
        if (p2 != null) {
            p.next = p2;
        }

        // Return merged list (skip dummy head)
        return dummy.next;
    }
}
