package org.qinlinj.algoframework._300_classical_data_structure_algo._340_stack_queue_algo._346_monotonic_stack_variations;

/**
 * Monotonic Stack - Practice Problem 1
 * Next Greater Nodes in Linked List (LeetCode 1019)
 * <p>
 * Problem Description:
 * For each node in a linked list, find the value of the next greater node.
 * That is, for each node, find the value of the first node with a greater value to its right.
 * Return 0 if there is no next greater node.
 * <p>
 * Approach:
 * 1. Convert the linked list to an array for easy indexing
 * 2. Apply the monotonic stack pattern for finding the next greater element
 * 3. Process the array from right to left
 * <p>
 * Key insight:
 * This problem is essentially the same as finding the next greater element in an array,
 * but with a linked list input that we need to convert first.
 * <p>
 * Time Complexity: O(n) where n is the length of the linked list
 * Space Complexity: O(n) for the array and stack
 */

import java.util.*;

public class _346_c_NextGreaterNodesLinkedList {

    /**
     * Helper method to create a linked list from an array
     */
    public static ListNode createLinkedList(int[] values) {
        if (values == null || values.length == 0) {
            return null;
        }

        ListNode dummy = new ListNode(0);
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
    public static void printLinkedList(ListNode head) {
        System.out.print("Linked List: ");
        while (head != null) {
            System.out.print(head.val + " -> ");
            head = head.next;
        }
        System.out.println("null");
    }

    /**
     * Main method to demonstrate the solution
     */
    public static void main(String[] args) {
        _346_c_NextGreaterNodesLinkedList solution = new _346_c_NextGreaterNodesLinkedList();

        System.out.println("===== NEXT GREATER NODES IN LINKED LIST =====");

        // Example 1
        int[] values1 = {2, 1, 5};
        ListNode head1 = createLinkedList(values1);
        printLinkedList(head1);

        int[] result1 = solution.nextLargerNodes(head1);
        System.out.println("Next Greater Nodes: " + Arrays.toString(result1));
        System.out.println("Expected: [5, 5, 0]");

        // Example 2
        int[] values2 = {2, 7, 4, 3, 5};
        ListNode head2 = createLinkedList(values2);
        printLinkedList(head2);

        int[] result2 = solution.nextLargerNodes(head2);
        System.out.println("Next Greater Nodes: " + Arrays.toString(result2));
        System.out.println("Expected: [7, 0, 5, 5, 0]");

        // Step-by-step explanation
        System.out.println("\n===== DETAILED EXPLANATION =====");
        System.out.println("For example [2, 7, 4, 3, 5]:");
        System.out.println("1. Convert linked list to array: [2, 7, 4, 3, 5]");
        System.out.println("2. Process from right to left using monotonic stack:");
        System.out.println("   - Start with i=4 (value 5): No greater element, result[4] = 0");
        System.out.println("   - For i=3 (value 3): 5 is greater, result[3] = 5");
        System.out.println("   - For i=2 (value 4): 5 is greater, result[2] = 5");
        System.out.println("   - For i=1 (value 7): No greater element, result[1] = 0");
        System.out.println("   - For i=0 (value 2): 7 is greater, result[0] = 7");
        System.out.println("3. Final result: [7, 0, 5, 5, 0]");
    }

    /**
     * Find the next greater node value for each node in the linked list
     *
     * @param head The head of the linked list
     * @return An array where result[i] is the next greater value for the ith node
     */
    public int[] nextLargerNodes(ListNode head) {
        // Convert linked list to array for easy indexing
        ArrayList<Integer> nodeValues = new ArrayList<>();
        for (ListNode p = head; p != null; p = p.next) {
            nodeValues.add(p.val);
        }

        int n = nodeValues.size();
        int[] result = new int[n];
        Stack<Integer> stack = new Stack<>();

        // Apply monotonic stack pattern (process from right to left)
        for (int i = n - 1; i >= 0; i--) {
            // Remove elements smaller than or equal to current
            while (!stack.isEmpty() && stack.peek() <= nodeValues.get(i)) {
                stack.pop();
            }

            // Set result (use 0 instead of -1 for no greater element)
            result[i] = stack.isEmpty() ? 0 : stack.peek();

            // Push current element
            stack.push(nodeValues.get(i));
        }

        return result;
    }

    /**
     * Definition for singly-linked list node
     */
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
