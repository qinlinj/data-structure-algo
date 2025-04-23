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
    // Global variable to track the node after the reversed portion
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
     * Main method to demonstrate all the linked list reversal techniques
     */
    public static void main(String[] args) {
        LinkedListReversal solution = new LinkedListReversal();

        // Example list: 1->2->3->4->5
        int[] values = {1, 2, 3, 4, 5};

        System.out.println("1. Reversing entire linked list:");

        // Iterative approach
        ListNode list1 = createList(values);
        System.out.print("Original list: ");
        printList(list1);

        ListNode reversed1 = solution.reverseListIterative(list1);
        System.out.print("After iterative reversal: ");
        printList(reversed1);

        // Recursive approach
        ListNode list2 = createList(values);
        ListNode reversed2 = solution.reverseListRecursive(list2);
        System.out.print("After recursive reversal: ");
        printList(reversed2);
        System.out.println();

        System.out.println("2. Reversing a portion of linked list (from position 2 to 4):");

        // Iterative approach
        ListNode list3 = createList(values);
        System.out.print("Original list: ");
        printList(list3);

        ListNode partialReversed1 = solution.reverseBetweenIterative(list3, 2, 4);
        System.out.print("After iterative partial reversal: ");
        printList(partialReversed1);

        // Recursive approach
        ListNode list4 = createList(values);
        ListNode partialReversed2 = solution.reverseBetweenRecursive(list4, 2, 4);
        System.out.print("After recursive partial reversal: ");
        printList(partialReversed2);
        System.out.println();

        System.out.println("3. Reversing in k-group segments (k=2):");

        // Iterative approach
        ListNode list5 = createList(values);
        System.out.print("Original list: ");
        printList(list5);

        ListNode kGroupReversed1 = solution.reverseKGroupIterative(list5, 2);
        System.out.print("After iterative k-group reversal: ");
        printList(kGroupReversed1);

        // Recursive approach
        ListNode list6 = createList(values);
        ListNode kGroupReversed2 = solution.reverseKGroupRecursive(list6, 2);
        System.out.print("After recursive k-group reversal: ");
        printList(kGroupReversed2);
    }

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

    /**
     * 2B. Reverse a portion of a linked list (from position left to right) - Recursive
     * <p>
     * Time Complexity: O(n)
     * Space Complexity: O(n) due to the recursive call stack
     */
    public ListNode reverseBetweenRecursive(ListNode head, int left, int right) {
        // Base case: if we're at the start position, reverse until the end position
        if (left == 1) {
            return reverseFirstN(head, right);
        }

        // Move head forward and adjust positions
        head.next = reverseBetweenRecursive(head.next, left - 1, right - 1);
        return head;
    }

    /**
     * Helper method to reverse the first n nodes of a linked list
     */
    private ListNode reverseFirstN(ListNode head, int n) {
        // Base case: when we've reversed enough nodes
        if (n == 1) {
            // Save the node after the reversed portion
            successor = head.next;
            return head;
        }

        // Recursively reverse the remaining nodes
        ListNode newHead = reverseFirstN(head.next, n - 1);

        // Adjust pointers for the current node
        head.next.next = head;
        head.next = successor;

        return newHead;
    }

    /**
     * 3A. Reverse linked list in k-group segments - Iterative
     * <p>
     * This corresponds to LeetCode 25: Reverse Nodes in k-Group
     * <p>
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public ListNode reverseKGroupIterative(ListNode head, int k) {
        if (head == null || head.next == null || k <= 1) {
            return head;
        }

        // Create a dummy node to simplify edge cases
        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        // Pointers to track segments
        ListNode prevGroupEnd = dummy;  // Points to the node before the current group
        ListNode current = head;        // Current node being processed

        int count = 0;  // Count of nodes processed

        // Count the total number of nodes
        int length = 0;
        ListNode temp = head;
        while (temp != null) {
            length++;
            temp = temp.next;
        }

        // Process each group of k nodes
        while (length >= k) {
            // The current group starts from 'current'
            ListNode groupStart = current;
            ListNode prev = null;
            ListNode next = null;

            // Reverse k nodes
            for (int i = 0; i < k; i++) {
                next = current.next;
                current.next = prev;
                prev = current;
                current = next;
                length--;
            }

            // Connect the reversed group to the rest of the list
            prevGroupEnd.next = prev;            // Connect previous segment to new head of reversed segment
            groupStart.next = current;           // Connect tail of reversed segment to next segment

            // Update prevGroupEnd for the next iteration
            prevGroupEnd = groupStart;
        }

        return dummy.next;
    }

    /**
     * 3B. Reverse linked list in k-group segments - Recursive
     * <p>
     * Time Complexity: O(n)
     * Space Complexity: O(n/k) due to the recursive call stack
     */
    public ListNode reverseKGroupRecursive(ListNode head, int k) {
        if (head == null) return null;

        // Check if there are at least k nodes remaining
        ListNode current = head;
        int count = 0;
        while (current != null && count < k) {
            current = current.next;
            count++;
        }

        // If less than k nodes, don't reverse
        if (count < k) {
            return head;
        }

        // Reverse the first k nodes
        ListNode prev = null;
        ListNode next = null;
        current = head;
        for (int i = 0; i < k; i++) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }

        // Recursively reverse the next k nodes and connect
        head.next = reverseKGroupRecursive(current, k);

        return prev; // prev is the new head of the reversed k-group
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
