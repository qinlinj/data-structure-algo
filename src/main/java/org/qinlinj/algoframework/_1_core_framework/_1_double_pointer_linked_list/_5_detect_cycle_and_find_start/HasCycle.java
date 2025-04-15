package org.qinlinj.algoframework._1_core_framework._1_double_pointer_linked_list._5_detect_cycle_and_find_start;

import org.qinlinj.leetcode.editor.common.ListNode;

// @formatter:off
public class HasCycle {
    /**
     * Detects whether a linked list contains a cycle using Floyd's Cycle-Finding Algorithm
     * (Also known as the "Tortoise and Hare" algorithm)
     *
     * Algorithm Strategy: Two-Pointer Technique
     *
     * Key Concept:
     * - Use two pointers moving at different speeds
     * - Slow pointer moves 1 step at a time
     * - Fast pointer moves 2 steps at a time
     *
     * Visualization:
     * Cycle Example:
     * 1 -> 2 -> 3 -> 4 -> 5
     *      ↑_____________|
     *
     * Pointer Movement:
     * Slow: 1 -> 2 -> 3 -> 4 -> 5
     * Fast: 1 -> 3 -> 5 -> 3 -> 5
     *
     * Scenarios Handled:
     * 1. Linked list with a cycle
     * 2. Linked list without a cycle
     * 3. Empty list or single-node list
     *
     * Why This Works:
     * - If a cycle exists, fast pointer will eventually
     *   catch up to slow pointer inside the cycle
     * - If no cycle, fast pointer reaches end of list
     *
     * Time Complexity: O(n)
     * - In worst case, traverses each node once
     *
     * Space Complexity: O(1)
     * - Uses only two pointers, regardless of list size
     *
     * @param head The head of the linked list
     * @return true if a cycle is detected, false otherwise
     */
    public boolean hasCycle(ListNode head) {
        // Handle empty list or single-node list
        if (head == null || head.next == null) {
            return false;
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

            // Check if pointers meet (cycle detected)
            if (slow == fast) {
                return true;
            }
        }

        // No cycle found
        return false;
    }
}
