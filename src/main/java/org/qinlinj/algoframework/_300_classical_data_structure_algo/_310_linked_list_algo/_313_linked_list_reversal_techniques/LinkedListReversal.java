package org.qinlinj.algoframework._300_classical_data_structure_algo._310_linked_list_algo._313_linked_list_reversal_techniques;

/**
 * LINKED LIST REVERSAL TECHNIQUES
 * <p>
 * This class demonstrates different approaches to reversing linked lists:
 * 1. Reversing an entire linked list (iterative approach)
 * 2. Reversing an entire linked list (recursive approach)
 * 3. Reversing a specific portion of a linked list
 * 4. Reversing a linked list in k-group segments
 * <p>
 * KEY TECHNIQUES:
 * <p>
 * 1. Iterative Reversal
 * - Using multiple pointers to track current, previous, and next nodes
 * - Careful handling of boundary conditions
 * - Efficient O(n) time complexity with O(1) space complexity
 * <p>
 * 2. Recursive Reversal
 * - Breaking down the problem into smaller subproblems
 * - Using the call stack to implicitly track nodes
 * - Elegant but less space-efficient (O(n) space due to call stack)
 * <p>
 * 3. Partial Reversal
 * - Combining traversal with reversal techniques
 * - Proper handling of boundary nodes for reconnection
 * - Maintaining references to critical nodes
 * <p>
 * 4. K-Group Reversal
 * - Using length calculation and sublist identification
 * - Applying reversal techniques to specific segments
 * - Reconnecting segments properly
 */
public class LinkedListReversal {
    /**
     * 1A. Reverse the entire linked list - Iterative Approach
     * <p>
     * This method reverses a linked list by changing the direction of pointers
     * while iterating through the list once.
     * <p>
     * Time Complexity: O(n) where n is the number of nodes
     * Space Complexity: O(1) - only uses a constant amount of extra space
     */
    public ListNode reverseListIterative(ListNode head) {
        // Edge cases: empty list or single node
        if (head == null || head.next == null) {
            return head;
        }

        // Initialize three pointers for the reversal process
        ListNode prev = null;          // Previous node (initially null)
        ListNode current = head;       // Current node being processed
        ListNode next = null;          // Temporary storage for the next node

        // Iterate through the list
        while (current != null) {
            // Save the next node before we change the pointer
            next = current.next;

            // Reverse the pointer of the current node
            current.next = prev;

            // Move the pointers one position ahead
            prev = current;
            current = next;
        }

        // At the end, prev will be pointing to the new head
        return prev;
    }

    /**
     * 1B. Reverse the entire linked list - Recursive Approach
     * <p>
     * This method uses recursion to reverse a linked list by:
     * 1. Recursively reversing the sublist starting from the next node
     * 2. Adjusting the current node's pointers
     * <p>
     * Time Complexity: O(n)
     * Space Complexity: O(n) due to the recursive call stack
     */
    public ListNode reverseListRecursive(ListNode head) {
        // Base case: empty list or single node
        if (head == null || head.next == null) {
            return head;
        }

        // Recursive step: reverse the rest of the list
        ListNode newHead = reverseListRecursive(head.next);

        // Adjust the pointers
        head.next.next = head;  // Make the next node point back to the current node
        head.next = null;       // Break the original forward pointer

        // Return the new head (which was found in the recursive call)
        return newHead;
    }

    /**
     * 2A. Reverse a portion of a linked list (from position left to right) - Iterative
     * <p>
     * This corresponds to LeetCode 92: Reverse Linked List II
     * <p>
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public ListNode reverseBetweenIterative(ListNode head, int left, int right) {
        // Edge cases
        if (head == null || head.next == null || left == right) {
            return head;
        }

        // Create a dummy node to handle the case where left = 1
        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        // Find the node before the reversal start point
        ListNode beforeReverse = dummy;
        for (int i = 1; i < left; i++) {
            beforeReverse = beforeReverse.next;
        }

        // Start of the sublist to reverse
        ListNode current = beforeReverse.next;
        ListNode prev = null;
        ListNode next = null;

        // The tail of the reversed sublist (will be the same node, but positioned differently)
        ListNode sublistTail = current;

        // Reverse the sublist
        for (int i = left; i <= right; i++) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }

        // Connect the reversed sublist back to the original list
        beforeReverse.next = prev;          // Connect the node before reversal to the new head of the reversed portion
        sublistTail.next = current;         // Connect the tail of the reversed portion to the rest of the list

        return dummy.next;
    }

    // Definition for singly-linked list
    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
