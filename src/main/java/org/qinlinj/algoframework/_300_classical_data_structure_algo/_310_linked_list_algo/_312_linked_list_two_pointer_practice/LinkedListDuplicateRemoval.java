package org.qinlinj.algoframework._300_classical_data_structure_algo._310_linked_list_algo._312_linked_list_two_pointer_practice;

/**
 * LINKED LIST DECOMPOSITION TECHNIQUES
 * <p>
 * This class demonstrates techniques for decomposing a linked list,
 * specifically focusing on removing duplicate elements from sorted linked lists.
 * <p>
 * KEY TECHNIQUES:
 * <p>
 * 1. List Decomposition
 * - Splitting a linked list into multiple lists based on certain criteria
 * - Using dummy nodes to track the head of each new list
 * - Recombining lists as needed
 * - Can be applied whenever we need to filter/select nodes from a linked list
 * <p>
 * 2. Fast/Slow Pointers
 * - Using two pointers that move at different paces
 * - Helps in identifying patterns like duplicates, cycles, etc.
 * - Often more space-efficient than using additional data structures
 * <p>
 * 3. Recursive Approach
 * - Breaking down the problem into smaller sub-problems
 * - Handling the base case and building the solution recursively
 * - Particularly useful for operations that can be defined in terms of themselves
 * <p>
 * PROBLEM FOCUS:
 * LeetCode 82: Remove Duplicates from Sorted List II
 * Given the head of a sorted linked list, delete all nodes that have duplicate numbers,
 * leaving only distinct numbers from the original list.
 * Return the linked list sorted as well.
 */
public class LinkedListDuplicateRemoval {
    /**
     * Utility method to create a linked list from an array of values
     */
    public static ListNode createList(int[] values) {
        if (values == null || values.length == 0) {
            return null;
        }

        ListNode dummy = new ListNode();
        ListNode current = dummy;

        for (int val : values) {
            current.next = new ListNode(val);
            current = current.next;
        }

        return dummy.next;
    }

    /**
     * Utility method to print a linked list
     */
    public static void printList(ListNode head) {
        ListNode current = head;
        while (current != null) {
            System.out.print(current.val + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }

    /**
     * Main method to demonstrate the solutions
     */
    public static void main(String[] args) {
        LinkedListDuplicateRemoval solution = new LinkedListDuplicateRemoval();

        // Example 1: [1,2,3,3,4,4,5] -> [1,2,5]
        int[] values1 = {1, 2, 3, 3, 4, 4, 5};
        ListNode list1 = createList(values1);

        System.out.println("Original List:");
        printList(list1);

        System.out.println("\nAfter removing duplicates (Decomposition):");
        ListNode result1 = solution.deleteDuplicates_decomposition(list1);
        printList(result1);

        // Example 2: [1,1,1,2,3] -> [2,3]
        int[] values2 = {1, 1, 1, 2, 3};
        ListNode list2 = createList(values2);

        System.out.println("\nOriginal List:");
        printList(list2);

        System.out.println("\nAfter removing duplicates (Two Pointers):");
        ListNode result2 = solution.deleteDuplicates_twoPointers(list2);
        printList(result2);

        // Example with recursive solution
        int[] values3 = {1, 2, 2, 3, 3, 3, 4, 5, 5};
        ListNode list3 = createList(values3);

        System.out.println("\nOriginal List:");
        printList(list3);

        System.out.println("\nAfter removing duplicates (Recursive):");
        ListNode result3 = solution.deleteDuplicates_recursive(list3);
        printList(result3);
    }

    /**
     * Solution 1: List Decomposition Technique
     * <p>
     * This approach decomposes the original list into two separate lists:
     * - One for unique elements
     * - One for duplicate elements
     * <p>
     * Then it returns only the unique elements list.
     * <p>
     * Time Complexity: O(n) where n is the number of nodes in the list
     * Space Complexity: O(1) - only constant extra space used
     */
    public ListNode deleteDuplicates_decomposition(ListNode head) {
        // Edge case: empty list or single node
        if (head == null || head.next == null) {
            return head;
        }

        // Create dummy nodes for both lists
        // Since node values are between -100 and 100, we can use 101 as dummy value
        ListNode dummyUnique = new ListNode(101);
        ListNode dummyDuplicate = new ListNode(101);

        ListNode uniquePtr = dummyUnique;    // Pointer for unique elements list
        ListNode duplicatePtr = dummyDuplicate;  // Pointer for duplicate elements list
        ListNode current = head;              // Pointer for traversing the original list

        while (current != null) {
            // Check if current node is a duplicate
            // Either its value matches the next node or it matches the last duplicate we saw
            if ((current.next != null && current.val == current.next.val) ||
                    current.val == duplicatePtr.val) {

                // Add to duplicate list
                duplicatePtr.next = current;
                duplicatePtr = duplicatePtr.next;
            } else {
                // Add to unique list
                uniquePtr.next = current;
                uniquePtr = uniquePtr.next;
            }

            // Move to next node in original list
            current = current.next;

            // Disconnect the node from its original next pointer
            // This is crucial to avoid cycles and ensure proper list termination
            if (uniquePtr != dummyUnique) {
                uniquePtr.next = null;
            }
            if (duplicatePtr != dummyDuplicate) {
                duplicatePtr.next = null;
            }
        }

        // Return the list of unique elements
        return dummyUnique.next;
    }

    /**
     * Solution 2: Fast/Slow Pointer Technique
     * <p>
     * This approach uses two pointers:
     * - A slow pointer (p) that builds the result list
     * - A fast pointer (q) that scans ahead to detect duplicates
     * <p>
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public ListNode deleteDuplicates_twoPointers(ListNode head) {
        // Edge case: empty list or single node
        if (head == null || head.next == null) {
            return head;
        }

        // Create a dummy node to simplify handling the head
        ListNode dummy = new ListNode(-1);

        // p builds the result list, q scans the original list
        ListNode p = dummy;
        ListNode q = head;

        while (q != null) {
            // Check if current node is the start of a duplicate sequence
            if (q.next != null && q.val == q.next.val) {
                // Skip all nodes with this value
                int duplicateValue = q.val;
                while (q != null && q.val == duplicateValue) {
                    q = q.next;
                }
                // At this point, q points to either null or the first node with a different value
            } else {
                // Current node is unique, add it to the result list
                p.next = q;
                p = p.next;
                q = q.next;
                p.next = null;  // Disconnect to avoid cycles
            }
        }

        return dummy.next;
    }

    /**
     * Solution 3: Recursive Approach
     * <p>
     * This solution uses recursion to solve the problem:
     * - If the current node is not part of a duplicate sequence, keep it and process the rest
     * - If the current node is part of a duplicate sequence, skip all duplicates and process the rest
     * <p>
     * Time Complexity: O(n)
     * Space Complexity: O(n) due to the recursive call stack
     */
    public ListNode deleteDuplicates_recursive(ListNode head) {
        // Base case: empty list or single node
        if (head == null || head.next == null) {
            return head;
        }

        // If current node's value is different from the next node
        if (head.val != head.next.val) {
            // Keep this node and recursively process the rest
            head.next = deleteDuplicates_recursive(head.next);
            return head;
        }

        // Current node is the start of a duplicate sequence
        // Find the first node after all duplicates
        int duplicateValue = head.val;
        while (head != null && head.val == duplicateValue) {
            head = head.next;
        }

        // Recursively process the rest of the list
        return deleteDuplicates_recursive(head);
    }

    // Definition for a singly linked list node
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
