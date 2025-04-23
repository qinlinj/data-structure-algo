package org.qinlinj.algoframework._300_classical_data_structure_algo._310_linked_list_algo._312_linked_list_two_pointer_practice;

/**
 * LINKED LIST ARITHMETIC OPERATIONS
 * <p>
 * This class demonstrates how to perform arithmetic operations using linked lists,
 * particularly focusing on addition operations.
 * <p>
 * KEY TECHNIQUES:
 * <p>
 * 1. Reverse Order Addition (LeetCode 2)
 * - Handles numbers stored in reverse order (least significant digit first)
 * - Simulates the standard digit-by-digit addition with carry
 * - Uses a dummy node to simplify edge cases
 * <p>
 * 2. Forward Order Addition (LeetCode 445)
 * - Handles numbers stored in forward order (most significant digit first)
 * - Uses stacks to reverse the order or performs in-place reversal
 * - Demonstrates different approaches to handle digit misalignment
 * <p>
 * COMMON PATTERNS:
 * <p>
 * 1. Carry Handling
 * - Tracking and propagating carry values between digits
 * - Managing carryover at the most significant digit
 * <p>
 * 2. Dummy Node Technique
 * - Using a placeholder node to simplify the head insertion logic
 * - Helps handle the case when a new digit needs to be added at the beginning
 * <p>
 * 3. Two-Pointer Traversal
 * - Walking through multiple lists simultaneously
 * - Handling lists of different lengths
 */
public class LinkedListArithmetic {
    /**
     * LeetCode 2: Add Two Numbers
     * <p>
     * Add two non-negative integers represented by linked lists.
     * The digits are stored in reverse order (least significant digit first).
     * <p>
     * Approach:
     * - Traverse both lists simultaneously
     * - Add corresponding digits along with any carry from previous addition
     * - Create a new node for each sum digit
     * - Track carry for the next iteration
     * <p>
     * Time Complexity: O(max(m,n)) where m and n are the lengths of the lists
     * Space Complexity: O(max(m,n)) for the result list
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // Pointers for traversing the input lists
        ListNode p1 = l1, p2 = l2;

        // Dummy node to simplify head insertion
        ListNode dummy = new ListNode(-1);
        ListNode current = dummy;

        // Track carry between additions
        int carry = 0;

        // Continue until both lists are processed and no carry remains
        while (p1 != null || p2 != null || carry > 0) {
            // Add carry from previous addition
            int sum = carry;

            // Add value from first list if available
            if (p1 != null) {
                sum += p1.val;
                p1 = p1.next;
            }

            // Add value from second list if available
            if (p2 != null) {
                sum += p2.val;
                p2 = p2.next;
            }

            // Calculate new carry and digit value
            carry = sum / 10;
            int digit = sum % 10;

            // Create new node for the result
            current.next = new ListNode(digit);
            current = current.next;
        }

        // Return head of the result list (skip dummy node)
        return dummy.next;
    }

    /**
     * LeetCode 445: Add Two Numbers II
     * <p>
     * Add two non-negative integers represented by linked lists.
     * The digits are stored in forward order (most significant digit first).
     * <p>
     * Approach using stacks:
     * - Use stacks to reverse the order of digits
     * - Process digits from least significant to most significant
     * - Insert new digits at the beginning of the result list
     * <p>
     * Time Complexity: O(m+n) where m and n are the lengths of the lists
     * Space Complexity: O(m+n) for the stacks
     */
    public ListNode addTwoNumbersII(ListNode l1, ListNode l2) {
        // Use stacks to reverse the order of digits
        java.util.Stack<Integer> stack1 = new java.util.Stack<>();
        java.util.Stack<Integer> stack2 = new java.util.Stack<>();

        // Push all digits from first list to stack
        ListNode current = l1;
        while (current != null) {
            stack1.push(current.val);
            current = current.next;
        }

        // Push all digits from second list to stack
        current = l2;
        while (current != null) {
            stack2.push(current.val);
            current = current.next;
        }

        // Dummy node for the result
        ListNode dummy = new ListNode(-1);

        // Track carry between additions
        int carry = 0;

        // Process digits from least significant to most significant
        while (!stack1.isEmpty() || !stack2.isEmpty() || carry > 0) {
            // Add carry from previous addition
            int sum = carry;

            // Add digit from first stack if available
            if (!stack1.isEmpty()) {
                sum += stack1.pop();
            }

            // Add digit from second stack if available
            if (!stack2.isEmpty()) {
                sum += stack2.pop();
            }

            // Calculate new carry and digit value
            carry = sum / 10;
            int digit = sum % 10;

            // Insert new digit at the beginning of the result list
            ListNode newNode = new ListNode(digit);
            newNode.next = dummy.next;
            dummy.next = newNode;
        }

        // Return head of the result list (skip dummy node)
        return dummy.next;
    }

    // Definition for singly-linked list node
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
