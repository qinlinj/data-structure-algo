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
    // Global variable to keep track of the node after the reversed portion
    private ListNode successor = null;

    /**
     * Helper method to create a linked list from an array
     */
    public static ListNode createList(int[] values) {
        if (values == null || values.length == 0) {
            return null;
        }

        ListNode dummy = new ListNode(-1);
        ListNode current = dummy;

        for (int val : values) {
            current.next = new ListNode(val);
            current = current.next;
        }

        return dummy.next;
    }

    /**
     * Helper method to print a linked list
     */
    public static void printList(ListNode head) {
        ListNode current = head;
        while (current != null) {
            System.out.print(current.val);
            if (current.next != null) {
                System.out.print(" -> ");
            }
            current = current.next;
        }
        System.out.println();
    }

    /**
     * Main method to demonstrate reversing the first N nodes
     */
    public static void main(String[] args) {
        ReverseFirstNNodes solution = new ReverseFirstNNodes();

        // Example list: 1->2->3->4->5
        int[] values = {1, 2, 3, 4, 5};

        System.out.println("Reversing first N nodes of a linked list:");

        // Test iterative approach with N=3
        ListNode list1 = createList(values);
        System.out.print("Original list: ");
        printList(list1);

        ListNode reversed1 = solution.reverseNIterative(list1, 3);
        System.out.print("After reversing first 3 nodes (iterative): ");
        printList(reversed1);

        // Test recursive approach with N=3
        ListNode list2 = createList(values);
        ListNode reversed2 = solution.reverseNRecursive(list2, 3);
        System.out.print("After reversing first 3 nodes (recursive): ");
        printList(reversed2);

        // Test with N=1 (should not change the list)
        ListNode list3 = createList(values);
        ListNode reversed3 = solution.reverseNIterative(list3, 1);
        System.out.print("After reversing first 1 node (iterative): ");
        printList(reversed3);

        // Test with N=5 (entire list)
        ListNode list4 = createList(values);
        ListNode reversed4 = solution.reverseNRecursive(list4, 5);
        System.out.print("After reversing first 5 nodes (recursive): ");
        printList(reversed4);

        // Test the alternative recursive implementation
        ListNode list5 = createList(values);
        ListNode reversed5 = solution.reverseNRecursiveAlternative(list5, 3);
        System.out.print("After reversing first 3 nodes (alternative recursive): ");
        printList(reversed5);
    }

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

    /**
     * 2. Reverse first N nodes - Recursive Approach
     * <p>
     * This method recursively reverses the first N nodes of a linked list.
     * <p>
     * Time Complexity: O(N)
     * Space Complexity: O(N) due to the recursive call stack
     */
    public ListNode reverseNRecursive(ListNode head, int n) {
        // Reset the successor for each new call to this method
        this.successor = null;
        return reverseNRecursiveHelper(head, n);
    }

    /**
     * Helper method for recursive reversal of first N nodes
     */
    private ListNode reverseNRecursiveHelper(ListNode head, int n) {
        // Base case: reached the Nth node
        if (n == 1) {
            // Save the N+1 node (successor)
            successor = head.next;
            return head;
        }

        // Recursively reverse the remaining N-1 nodes
        ListNode newHead = reverseNRecursiveHelper(head.next, n - 1);

        // Reverse the current node's pointer
        head.next.next = head;

        // Connect the current node to the successor (N+1 node)
        head.next = successor;

        // Return the new head of the reversed portion
        return newHead;
    }

    /**
     * Alternative implementation with successor as a parameter
     * This avoids using a class member variable
     */
    public ListNode reverseNRecursiveAlternative(ListNode head, int n) {
        return reverseNWithSuccessor(head, n, new ListNode[1]);
    }

    private ListNode reverseNWithSuccessor(ListNode head, int n, ListNode[] successorHolder) {
        // Base case: reached the Nth node
        if (n == 1) {
            // Save the N+1 node (successor)
            successorHolder[0] = head.next;
            return head;
        }

        // Recursively reverse the remaining N-1 nodes
        ListNode newHead = reverseNWithSuccessor(head.next, n - 1, successorHolder);

        // Reverse the current node's pointer
        head.next.next = head;

        // Connect the current node to the successor (N+1 node)
        head.next = successorHolder[0];

        // Return the new head of the reversed portion
        return newHead;
    }

    /**
     * Helper method to verify if N is valid for the given list
     * (useful for checking before reversal)
     */
    public boolean isValidN(ListNode head, int n) {
        if (n <= 0) return false;

        int length = 0;
        ListNode current = head;
        while (current != null) {
            length++;
            current = current.next;
        }

        return n <= length;
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
