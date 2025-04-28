package org.qinlinj.algoframework._300_classical_data_structure_algo._340_stack_queue_algo._342_stack_classic_practice;

/**
 * LeetCode 143: Reorder List
 * <p>
 * Problem Description:
 * Given the head of a singly linked list L: L0 → L1 → ... → Ln-1 → Ln
 * Reorder it to: L0 → Ln → L1 → Ln-1 → L2 → Ln-2 → ...
 * You must do this in-place without altering the nodes' values.
 * <p>
 * Solution Approach:
 * The challenge is that a singly linked list can only be traversed from head to tail,
 * but we need to access elements from the tail. We can use a stack to solve this:
 * <p>
 * 1. Push all nodes into a stack to get them in reverse order
 * 2. Traverse the list from the beginning and interleave:
 * - Save next node as temp
 * - Connect current node to node from stack
 * - Connect stack node to temp
 * - Move to temp node and continue
 * 3. Handle termination for both odd and even length lists
 * <p>
 * Time Complexity: O(n) where n is the number of nodes
 * Space Complexity: O(n) for the stack
 */

import java.util.*;

public class _342_b_ReorderList {
    // Utility method to convert list to string for testing
    public static String listToString(ListNode head) {
        StringBuilder sb = new StringBuilder();
        while (head != null) {
            sb.append(head.val);
            if (head.next != null) {
                sb.append(" -> ");
            }
            head = head.next;
        }
        return sb.toString();
    }

    // Example usage
    public static void main(String[] args) {
        _342_b_ReorderList solution = new _342_b_ReorderList();

        // Example 1: [1,2,3,4] -> [1,4,2,3]
        ListNode head1 = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4))));
        System.out.println("Before: " + listToString(head1));
        solution.reorderList(head1);
        System.out.println("After: " + listToString(head1));

        // Example 2: [1,2,3,4,5] -> [1,5,2,4,3]
        ListNode head2 = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        System.out.println("\nBefore: " + listToString(head2));
        solution.reorderList(head2);
        System.out.println("After: " + listToString(head2));
    }

    public void reorderList(ListNode head) {
        if (head == null || head.next == null) {
            return; // No reordering needed for empty or single-node list
        }

        Stack<ListNode> stk = new Stack<>();

        // Push all nodes into stack to get reverse order access
        ListNode p = head;
        while (p != null) {
            stk.push(p);
            p = p.next;
        }

        p = head;
        while (p != null) {
            // Get the node from the end
            ListNode lastNode = stk.pop();

            // Save the next node
            ListNode next = p.next;

            // Check termination conditions for both odd and even length lists
            if (lastNode == next || lastNode.next == next) {
                lastNode.next = null; // Terminate the list
                break;
            }

            // Connect current node to the last node
            p.next = lastNode;

            // Connect last node to the next node in original sequence
            lastNode.next = next;

            // Move to the next node in original sequence
            p = next;
        }
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
