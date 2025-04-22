package org.qinlinj.algoframework._100_algo_core_framework._110_double_pointer_linked_list._114_middle_node;

import org.qinlinj.leetcode.editor.common.ListNode;

public class RemoveNthFromEnd {
    /**
     * Removes the nth node from the end of a linked list
     * <p>
     * Algorithm Strategy: Two-Pointer Technique with Dummy Node
     * <p>
     * Key Concepts:
     * - Use dummy node to handle edge cases (removing head)
     * - Find nth node from end using two-pointer method
     * - Skip the target node to remove it
     * <p>
     * Visualization:
     * Input: 1 -> 2 -> 3 -> 4 -> 5, n = 2
     * <p>
     * Pointer Movement:
     * 1. Create dummy node: (dummy) -> 1 -> 2 -> 3 -> 4 -> 5
     * 2. Find (n+1)th node from end
     * 3. Remove 4th node
     * <p>
     * Final List:
     * 1 -> 2 -> 3 -> 5
     * <p>
     * Algorithm Steps:
     * 1. Create dummy node to simplify head removal
     * 2. Use helper method to find node before target
     * 3. Skip the target node by updating next pointer
     * <p>
     * Advantages:
     * - Single-pass solution
     * - Handles removing head node
     * - Works with lists of any length
     * <p>
     * Time Complexity: O(n)
     * - Single pass to find nth node from end
     * <p>
     * Space Complexity: O(1)
     * - Only uses two pointers and dummy node
     *
     * @param head Head of the original linked list
     * @param n    Position of node to remove from end
     * @return Head of modified list
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // Create dummy node to handle edge cases
        // Especially useful when removing the head node
        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        // Find the node just before the target node
        // Use n+1 to get the node before the one to be removed
        ListNode x = findFromEnd(dummy, n + 1);

        // Remove the target node by skipping it
        x.next = x.next.next;

        // Return modified list (use dummy.next to handle head removal)
        return dummy.next;
    }

    /**
     * Helper method to find kth node from the end
     * <p>
     * Two-Pointer Technique Details:
     * 1. Advance first pointer k steps ahead
     * 2. Move both pointers until first reaches end
     * 3. Second pointer will be at desired position
     *
     * @param head Starting node for search
     * @param k    Position from end to find
     * @return Node at kth position from end
     */
    private ListNode findFromEnd(ListNode head, int k) {
        // Advance first pointer k steps
        ListNode p1 = head;
        for (int i = 0; i < k; i++) {
            p1 = p1.next;
        }

        // Start second pointer at head
        ListNode p2 = head;

        // Move both pointers until p1 reaches end
        while (p1 != null) {
            p2 = p2.next;
            p1 = p1.next;
        }

        // Return node just before target
        return p2;
    }
}