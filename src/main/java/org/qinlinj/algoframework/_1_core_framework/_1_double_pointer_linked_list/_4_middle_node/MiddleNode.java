package org.qinlinj.algoframework._1_core_framework._1_double_pointer_linked_list._4_middle_node;

import org.qinlinj.leetcode.editor.common.ListNode;

public class MiddleNode {
    /**
     * Finds the middle node of a linked list using Floyd's Tortoise and Hare Algorithm
     * <p>
     * Algorithm Strategy: Two-Pointer Technique
     * <p>
     * Key Concepts:
     * - Use two pointers moving at different speeds
     * - Slow pointer moves 1 step at a time
     * - Fast pointer moves 2 steps at a time
     * <p>
     * Visualization:
     * Odd-Length List: 1 -> 2 -> 3 -> 4 -> 5
     * Pointer Movement:
     * Slow: 1 -> 2 -> 3 -> 4 -> 5
     * Fast: 1 -> 3 -> 5
     * Middle Node: 3
     * <p>
     * Even-Length List: 1 -> 2 -> 3 -> 4 -> 5 -> 6
     * Pointer Movement:
     * Slow: 1 -> 2 -> 3 -> 4
     * Fast: 1 -> 3 -> 5
     * Middle Node: 4
     * <p>
     * Scenarios Handled:
     * 1. Odd-length list
     * 2. Even-length list
     * 3. Single-node list
     * 4. Empty list
     * <p>
     * How It Works:
     * - Fast pointer moves twice as fast as slow pointer
     * - When fast pointer reaches end, slow pointer is at middle
     * - For even-length lists, returns second middle node
     * <p>
     * Time Complexity: O(n)
     * - Traverses list once
     * <p>
     * Space Complexity: O(1)
     * - Uses only two pointers
     *
     * @param head The head of the linked list
     * @return The middle node of the list
     */
    public ListNode middleNode(ListNode head) {
        // Handle empty or single-node list
        if (head == null || head.next == null) {
            return head;
        }

        // Initialize slow and fast pointers to head
        ListNode slow = head, fast = head;

        // Traverse the list
        // Ensure fast pointer and its next node are not null
        while (fast != null && fast.next != null) {
            // Move slow pointer one step
            slow = slow.next;

            // Move fast pointer two steps
            fast = fast.next.next;
        }

        // Return middle node
        // For odd-length: exact middle
        // For even-length: second middle node
        return slow;
    }
}
