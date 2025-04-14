package org.qinlinj.algoframework.coreframework.doublepointerlinkedlist;

import org.qinlinj.leetcode.editor.common.ListNode;

public class FindFromEnd {
    /**
     * Finds the kth node from the end of a linked list.
     * <p>
     * Algorithm Strategy: Two-Pointer Technique
     * <p>
     * Key Concept:
     * - Create two pointers (p1 and p2)
     * - Advance p1 k nodes ahead of p2
     * - Move both pointers until p1 reaches the end
     * - p2 will be at the kth node from the end
     * <p>
     * Visualization:
     * Initial List: 1 -> 2 -> 3 -> 4 -> 5 -> null
     * k = 2
     * <p>
     * Step 1: Advance p1 by k nodes
     * p1:      2 -> 3 -> 4 -> 5 -> null
     * p2: 1 -> 2 -> 3 -> 4 -> 5 -> null
     * <p>
     * Step 2: Move both pointers until p1 reaches end
     * p1:                5 -> null
     * p2:         3 -> 4 -> 5 -> null
     * <p>
     * Result: Returns node with value 4 (2nd from end)
     * <p>
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     *
     * @param head The head of the linked list
     * @param k    Position from the end (1-based indexing)
     * @return The kth node from the end of the list
     */
    ListNode findFromEnd(ListNode head, int k) {
        // Advance p1 k nodes ahead
        ListNode p1 = head;
        for (int i = 0; i < k; i++) {
            // Move p1 forward k steps
            // Note: Assumes k is valid (not larger than list length)
            p1 = p1.next;
        }

        // Start p2 at the head
        ListNode p2 = head;

        // Move both pointers until p1 reaches the end
        while (p1 != null) {
            // Both pointers move in sync
            p2 = p2.next;
            p1 = p1.next;
        }

        // p2 is now at the kth node from the end
        return p2;
    }
}
