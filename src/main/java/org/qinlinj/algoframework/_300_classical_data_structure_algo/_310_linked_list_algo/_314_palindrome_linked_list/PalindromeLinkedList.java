package org.qinlinj.algoframework._300_classical_data_structure_algo._310_linked_list_algo._314_palindrome_linked_list;

/**
 * PALINDROME LINKED LIST DETECTION
 * <p>
 * This class demonstrates techniques for determining if a linked list is a palindrome.
 * A palindrome reads the same forward and backward (e.g., 1->2->2->1).
 * <p>
 * KEY TECHNIQUES:
 * <p>
 * 1. Recursive Approach
 * - Uses the recursive call stack to simulate backwards traversal
 * - Compares nodes from front and back simultaneously
 * - Simple but requires O(n) space due to the call stack
 * <p>
 * 2. Optimized Two-Pointer Approach
 * - Finds the middle of the list using fast/slow pointers
 * - Reverses the second half of the list
 * - Compares the first half with the reversed second half
 * - Achieves O(1) space complexity
 * <p>
 * 3. Array Conversion Approach
 * - Converts the linked list to an array
 * - Uses traditional palindrome checking with two pointers
 * - Simple but requires O(n) extra space
 */
public class PalindromeLinkedList {
    // Class member to track the "left" or "front" pointer
    private ListNode frontPointer;

    /**
     * 1. Recursive Approach
     * <p>
     * Uses recursion to traverse to the end of the list and then compare
     * nodes from both ends while returning from the recursive calls.
     * <p>
     * Time Complexity: O(n)
     * Space Complexity: O(n) due to the recursive call stack
     */
    public boolean isPalindromeRecursive(ListNode head) {
        // Initialize the front pointer as a class member
        this.frontPointer = head;
        // Start the recursive comparison
        return recursivelyCheck(head);
    }

    /**
     * Helper method for recursive palindrome checking
     */
    private boolean recursivelyCheck(ListNode currentNode) {
        // Base case: if we've reached the end of the list
        if (currentNode == null) {
            return true;
        }

        // Recurse to the end of the list
        // This puts all nodes on the call stack
        boolean isStillPalindrome = recursivelyCheck(currentNode.next);

        // If we've already found a mismatch in a previous call
        if (!isStillPalindrome) {
            return false;
        }

        // Compare the current node (from the back) with the front pointer
        boolean valueMatch = (frontPointer.val == currentNode.val);

        // Move the front pointer forward for the next comparison
        frontPointer = frontPointer.next;

        return valueMatch;
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
