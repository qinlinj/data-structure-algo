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
