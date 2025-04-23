package org.qinlinj.algoframework._300_classical_data_structure_algo._310_linked_list_algo._313_linked_list_reversal_techniques;

/**
 * REVERSING LINKED LIST IN K-GROUPS
 * <p>
 * This class demonstrates techniques for reversing linked lists in groups of K nodes,
 * which is a more complex variation of linked list reversal problems.
 * <p>
 * KEY TECHNIQUES:
 * <p>
 * 1. K-Group Reversal
 * - Breaking the list into segments of K nodes
 * - Reversing each segment independently
 * - Maintaining proper connections between segments
 * - Handling the last segment if it contains fewer than K nodes
 * <p>
 * 2. Recursive Approach
 * - Using recursive structure to process each K-group
 * - Leveraging the "reverseN" function to reverse each segment
 * - Reconnecting segments through recursive calls
 * <p>
 * 3. Iterative Approach
 * - Using dummy nodes and multiple pointers to track segments
 * - Manually reversing each K-group and reconnecting
 * - Constant space complexity without using the call stack
 */
public class ReverseKGroup {
    /**
     * 1. Reverse Nodes in K-Group - Recursive Approach
     * <p>
     * This method uses recursion to process each K-group segment:
     * 1. Identify the current K-group
     * 2. Reverse the K-group using reverseN
     * 3. Recursively process the next K-group
     * 4. Connect the segments properly
     * <p>
     * Time Complexity: O(n) where n is the number of nodes
     * Space Complexity: O(n/k) due to the recursive call stack
     */
    public ListNode reverseKGroupRecursive(ListNode head, int k) {
        if (head == null || k <= 1) {
            return head;
        }

        // Identify the K-group: [head, end)
        ListNode end = head;
        for (int i = 0; i < k; i++) {
            // If fewer than k nodes remain, keep original order
            if (end == null) {
                return head;
            }
            end = end.next;
        }

        // Reverse the current K-group (from head to end-1)
        ListNode newHead = reverseN(head, k);

        // After reversal, 'head' is now the tail of the reversed segment
        // Recursively reverse the next K-group and connect
        head.next = reverseKGroupRecursive(end, k);

        return newHead;
    }

    /**
     * 2. Reverse Nodes in K-Group - Iterative Approach
     * <p>
     * This method uses iteration to avoid recursive call stack:
     * 1. Use a dummy node to simplify head pointer handling
     * 2. Process each K-group iteratively
     * 3. Maintain pointers to connect segments properly
     * <p>
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public ListNode reverseKGroupIterative(ListNode head, int k) {
        if (head == null || k <= 1) {
            return head;
        }

        // Dummy node to simplify handling of the head node
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        // prevGroupEnd points to the node before each K-group
        ListNode prevGroupEnd = dummy;

        // Count total nodes to avoid traversing the list multiple times
        int count = 0;
        ListNode current = head;
        while (current != null) {
            count++;
            current = current.next;
        }

        // Process each K-group
        current = head;
        while (count >= k) {
            // The first node of current K-group (will be the tail after reversal)
            ListNode groupStart = current;
            // The node after the current K-group
            ListNode nextGroupStart = null;

            // Reverse the current K-group
            ListNode prev = null;
            for (int i = 0; i < k; i++) {
                ListNode next = current.next;
                current.next = prev;
                prev = current;
                current = next;
                count--;
            }

            // At this point:
            // - current points to the first node after this K-group
            // - prev points to the new head of the reversed K-group
            // - groupStart points to the new tail of the reversed K-group

            // Connect the previous group's end to the new head of this group
            prevGroupEnd.next = prev;

            // Connect the new tail of this group to the start of the next group
            groupStart.next = current;

            // Move prevGroupEnd to the end of the current group (for next iteration)
            prevGroupEnd = groupStart;
        }

        return dummy.next;
    }

    /**
     * Helper method to reverse the first N nodes of a linked list
     * Used by the recursive approach to reverse each K-group
     */
    private ListNode reverseN(ListNode head, int n) {
        if (head == null || head.next == null || n <= 1) {
            return head;
        }

        ListNode prev = null;
        ListNode current = head;
        ListNode next = null;

        // The node that will follow the reversed portion
        ListNode connection = null;

        int count = 0;
        while (current != null && count < n) {
            next = current.next;

            // Save the connection point when we're at the last node of the segment
            if (count == n - 1) {
                connection = next;
            }

            current.next = prev;
            prev = current;
            current = next;
            count++;
        }

        // Connect the original head (now the tail of reversed portion)
        // to the remainder of the list
        head.next = connection;

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
