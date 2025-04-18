package org.qinlinj.algoframework._100_core_framework._110_double_pointer_linked_list._115_detect_cycle_and_find_start;

import org.qinlinj.leetcode.editor.common.ListNode;

// @formatter:off
public class DetectCycle {
    /**
     * Detects the start of a cycle in a linked list using Floyd's Cycle-Finding Algorithm (Tortoise and Hare).
     *
     * Algorithm Visualization:
     *
     * Initial State:
     * head -> A -> B -> C -> D -> E
     *             ↑_____________|
     *
     * 1. Two pointers start at the head:
     *    - Slow pointer moves 1 step at a time
     *    - Fast pointer moves 2 steps at a time
     *
     * 2. If a cycle exists, pointers will meet inside the cycle
     *
     * 3. When they meet, reset slow pointer to head
     *    Keep fast pointer at meeting point
     *
     * 4. Move both pointers 1 step at a time
     *    Their meeting point is the cycle's start
     *
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     *
     * @param head The head of the linked list
     * @return The node where the cycle begins, or null if no cycle exists
     */
    public ListNode detectCycle(ListNode head) {
        // Initialize fast and slow pointers to the head
        ListNode fast, slow;
        fast = slow = head;

        // Phase 1: Detect if a cycle exists
        // Move fast pointer 2 steps, slow pointer 1 step
        while (fast != null && fast.next != null) {
            fast = fast.next.next;  // Move 2 steps
            slow = slow.next;        // Move 1 step

            // If pointers meet, a cycle is detected
            if (fast == slow) break;
        }

        // If no cycle exists (fast reaches end of list)
        if (fast == null || fast.next == null) {
            return null;
        }

        // Phase 2: Find the start of the cycle
        // Reset slow pointer to head
        slow = head;

        // Move both pointers at same speed until they meet
        // Meeting point is the start of the cycle
        while (slow != fast) {
            fast = fast.next;
            slow = slow.next;
        }

        // Return the node where the cycle begins
        return slow;
    }
}

