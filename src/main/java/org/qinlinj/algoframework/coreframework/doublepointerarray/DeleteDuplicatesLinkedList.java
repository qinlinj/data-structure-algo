package org.qinlinj.algoframework.coreframework.doublepointerarray;

import org.qinlinj.leetcode.editor.common.ListNode;

// @formatter:off
public class DeleteDuplicatesLinkedList {
    /**
     * Removes duplicates from a sorted linked list, keeping only unique elements.
     *
     * This method uses the two-pointer technique (slow and fast pointers) to efficiently
     * remove duplicates from a sorted linked list.
     *
     * Visual example:
     * Original linked list: 1 -> 1 -> 2 -> 3 -> 3 -> 4
     *
     * Initial state:
     * 1 -> 1 -> 2 -> 3 -> 3 -> 4
     * s
     * f
     *
     * After first iteration (fast moves to second 1):
     * 1 -> 1 -> 2 -> 3 -> 3 -> 4
     * s    f
     * (No action since fast.val == slow.val)
     *
     * After another iteration (fast moves to 2):
     * 1 -> 1 -> 2 -> 3 -> 3 -> 4
     * s         f
     * (Values differ, so slow.next = fast and slow advances)
     *
     * Final result after complete execution:
     * 1 -> 2 -> 3 -> 4 -> null
     *
     * @param head The head node of the sorted linked list
     * @return The head of the linked list with duplicates removed
     */
    public ListNode deleteDuplicates(ListNode head) {
        // Handle edge case: if the list is empty, return null
        if (head == null) return null;

        // Initialize two pointers:
        // slow points to the last unique node in the result list
        // fast scans through the list to find unique elements
        ListNode slow = head, fast = head;

        // Iterate through the linked list with fast pointer
        while (fast != null) {
            // When we find a new unique element (different from the current unique element at slow)
            if (fast.val != slow.val) {
                // Connect the previous unique node (slow) to the current unique node (fast)
                // This is equivalent to nums[slow] = nums[fast] in array implementation
                slow.next = fast;

                // Move slow pointer forward to the new unique node
                // This is equivalent to slow++ in array implementation
                slow = slow.next;
            }

            // Always move fast pointer forward to continue scanning the list
            // This is equivalent to fast++ in array implementation
            fast = fast.next;
        }

        // Terminate the list after the last unique element
        // This prevents any remaining duplicates from being included
        slow.next = null;

        // Return the head of the modified list with duplicates removed
        return head;
    }
}