package org.qinlinj.nonlinear.highlevel.heap;

/**
 * DataStreamOrderedLinkedListImplementation - Implementation of a data stream using a sorted linked list
 * <p>
 * This implementation maintains a descending order in the linked list, which makes
 * removing the maximum element very fast (O(1)) at the cost of slower element addition (O(n)).
 * <p>
 * Characteristics:
 * - Elements are stored in descending order (largest to smallest)
 * - Maximum element is always at the head of the list for instant access
 * - Suitable for scenarios where retrieving the maximum value is more frequent than adding elements
 */
public class DataStreamOrderedLinkedListImplementation {
    private Node dummyNode;  // Dummy head node for easier list operations

    /**
     * Constructor to initialize the data stream with an empty linked list
     */
    public DataStreamOrderedLinkedListImplementation() {
        // Create a dummy node with value -1 to simplify operations
        // especially when dealing with the head of the list
        this.dummyNode = new Node(-1);
    }

    /**
     * Adds a new value to the data stream, maintaining the descending order
     * <p>
     * Time Complexity: O(n) - May require traversing the entire list to find
     * the correct insertion position
     *
     * @param val The integer value to add to the stream
     */
    public void add(int val) {
        // Find the first node that has a value less than the new value
        // This ensures we maintain the descending order of the list
        Node prev = dummyNode;
        Node curr = dummyNode.next;

        while (curr != null) {
            if (curr.val < val) {
                break;  // Found the insertion point
            }
            prev = curr;
            curr = curr.next;
        }

        // Insert the new node between prev and curr
        // This maintains the descending order of values in the list
        Node newNode = new Node(val);
        newNode.next = curr;
        prev.next = newNode;
    }

    /**
     * Removes and returns the maximum value in the data stream
     * <p>
     * Time Complexity: O(1) - Constant time operation since the maximum
     * is always at the head of the list
     *
     * @return The maximum value that was in the stream
     * @throws RuntimeException if the stream is empty
     */
    public int removeMax() {
        // Check if the list is empty
        if (dummyNode.next == null)
            throw new RuntimeException("removeMax failed: the data stream is empty");

        // Since the list is sorted in descending order,
        // the maximum value is always at the head of the list
        int res = dummyNode.next.val;

        // Remove the head node (which contains the maximum value)
        Node head = dummyNode.next;
        dummyNode.next = head.next;
        head.next = null;  // Clean up the removed node

        return res;  // Return the maximum value
    }

    /**
     * Inner Node class to create a singly linked list
     */
    private class Node {
        int val;       // Value stored in the node
        Node next;     // Reference to the next node in the list

        /**
         * Constructor to create a new node with a given value
         *
         * @param val The integer value to be stored in the node
         */
        Node(int val) {
            this.val = val;
        }
    }
}
