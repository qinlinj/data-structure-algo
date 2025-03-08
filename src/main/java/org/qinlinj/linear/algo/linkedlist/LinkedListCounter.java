package org.qinlinj.linear.algo.linkedlist;

/**
 * LinkedListCounter
 * <p>
 * This class provides various methods to count elements in a linked list.
 * It demonstrates different approaches to traversing a linked list and counting:
 * - Count all nodes in a linked list
 * - Count nodes matching a specific target value
 * <p>
 * The class shows both while-loop and for-loop implementations for counting all nodes.
 */
public class LinkedListCounter {

    /**
     * Main method demonstrating how to use the LinkedListCounter class.
     * <p>
     * This example:
     * 1. Creates a linked list [23, 12, 11, 34, 12] using the fromArray helper method
     * 2. Counts occurrences of the value 12 in the list
     * 3. Prints the result (which should be 2)
     *
     * @param args Command-line arguments (not used)
     */
    public static void main(String[] args) {
        // Create a linked list from an array
        ListNode head = ListNode.fromArray(new int[]{23, 12, 11, 34, 12});

        // Count occurrences of the value 12
        int cnt = new LinkedListCounter().countTarget(head, 12);

        // Print the result (should be 2)
        System.out.println(cnt);
    }

    /**
     * Calculate the number of nodes in a linked list using a while loop.
     * <p>
     * Algorithm:
     * 1. Start with count = 0
     * 2. Traverse the list, incrementing the count for each node
     * 3. Return the final count
     * <p>
     * Time Complexity: O(n) where n is the number of nodes in the list
     * Space Complexity: O(1) as we only use a constant amount of extra space
     *
     * @param head The head node of the linked list
     * @return The total number of nodes in the linked list
     * <p>
     * Example: For list [23, 12, 11, 34, 12], returns 5
     */
    public int count(ListNode head) {
        // Handle edge case: empty list
        if (head == null) return 0;

        // Initialize counter
        int cnt = 0;

        // Start traversal from the head
        ListNode curr = head;

        // Traverse the list until we reach the end (null)
        while (curr != null) {
            // Increment counter for each node
            cnt++;

            // Move to the next node
            curr = curr.next;
        }

        // Return the total count
        return cnt;
    }

    /**
     * Calculate the number of nodes in a linked list using a for loop.
     * This is functionally identical to the count method but uses a more compact for-loop syntax.
     * <p>
     * Time Complexity: O(n) where n is the number of nodes in the list
     * Space Complexity: O(1)
     *
     * @param head The head node of the linked list
     * @return The total number of nodes in the linked list
     * <p>
     * Example: For list [23, 12, 11, 34, 12], returns 5
     */
    public int countFor(ListNode head) {
        // Handle edge case: empty list
        if (head == null) return 0;

        // Initialize counter
        int cnt = 0;

        // For-loop syntax for traversing the linked list:
        // - Initialize curr to head
        // - Continue while curr is not null
        // - Move to next node after each iteration
        for (ListNode curr = head; curr != null; curr = curr.next) {
            // Increment counter for each node
            cnt++;
        }

        // Return the total count
        return cnt;
    }

    /**
     * Calculates the number of nodes in the linked list equal to the target value.
     * This is useful when you need to find the frequency of a specific value in the list.
     * <p>
     * Algorithm:
     * 1. Start with count = 0
     * 2. Traverse the list, incrementing the count only when a node's value matches the target
     * 3. Return the final count
     * <p>
     * Time Complexity: O(n) where n is the number of nodes in the list
     * Space Complexity: O(1)
     *
     * @param head   The head node of the linked list
     * @param target The value to count occurrences of
     * @return The number of nodes with a value equal to the target
     * <p>
     * Example: For list [23, 12, 11, 34, 12] and target 12, returns 2
     */
    public int countTarget(ListNode head, int target) {
        // Handle edge case: empty list
        if (head == null) return 0;

        // Initialize counter
        int cnt = 0;

        // Start traversal from the head
        ListNode curr = head;

        // Traverse the list until we reach the end (null)
        while (curr != null) {
            // Increment counter only when the current node's value matches the target
            if (curr.val == target) cnt++;

            // Move to the next node
            curr = curr.next;
        }

        // Return the count of matching nodes
        return cnt;
    }

    /**
     * Other potential methods that could be added to this class:
     *
     * 1. Recursive counting:
     *    public int countRecursive(ListNode head) {
     *        if (head == null) return 0;
     *        return 1 + countRecursive(head.next);
     *    }
     *
     * 2. Counting nodes that satisfy a condition:
     *    public int countIf(ListNode head, Predicate<Integer> condition) {
     *        int count = 0;
     *        for (ListNode curr = head; curr != null; curr = curr.next) {
     *            if (condition.test(curr.val)) count++;
     *        }
     *        return count;
     *    }
     */
}