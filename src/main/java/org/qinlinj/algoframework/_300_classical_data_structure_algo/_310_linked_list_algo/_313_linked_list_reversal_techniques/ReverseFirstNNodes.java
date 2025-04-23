package org.qinlinj.algoframework._300_classical_data_structure_algo._310_linked_list_algo._313_linked_list_reversal_techniques;

/**
 * REVERSING FIRST N NODES OF A LINKED LIST
 * <p>
 * This class demonstrates techniques for reversing just the first N nodes of a linked list,
 * which is a variation of the complete list reversal problem.
 * <p>
 * KEY TECHNIQUES:
 * <p>
 * 1. Iterative Approach
 * - Similar to complete reversal but stops after N nodes
 * - Properly connects the reversed portion with the remainder of the list
 * - Handles edge cases for N values
 * <p>
 * 2. Recursive Approach
 * - Uses a successor pointer to track the N+1 node
 * - Base case changes to stopping at the Nth node
 * - Carefully reconnects the reversed portion with the remainder
 * <p>
 * 3. Special Considerations
 * - Handling cases where N equals the list length
 * - Proper connection between the reversed portion and the rest of the list
 * - Maintaining the integrity of the original list structure
 */
public class ReverseFirstNNodes {
    /**
     * 1. Reverse first N nodes - Iterative Approach
     * <p>
     * This method reverses only the first N nodes of a linked list
     * and connects the reversed portion with the remainder of the list.
     * <p>
     * Time Complexity: O(N)
     * Space Complexity: O(1)
     */
    public ListNode reverseNIterative(ListNode head, int n) {
        // Edge cases: empty list, single node, or n <= 1
        if (head == null || head.next == null || n <= 1) {
            return head;
        }

        // Initialize pointers for the reversal process
        ListNode prev = null;
        ListNode current = head;
        ListNode next = null;

        // Counter to track how many nodes we've reversed
        int count = 0;

        // Reverse the first n nodes
        while (current != null && count < n) {
            // Save next node
            next = current.next;

            // Reverse the pointer
            current.next = prev;

            // Move to next nodes
            prev = current;
            current = next;

            // Increment counter
            count++;
        }

        // At this point:
        // - prev points to the new head of the reversed portion (node N)
        // - current points to the N+1 node (first node after the reversed portion)
        // - head is now the tail of the reversed portion (original first node)

        // Connect the original head (now the tail of reversed portion)
        // to the remainder of the list
        head.next = current;

        // Return the new head of the list
        return prev;
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
