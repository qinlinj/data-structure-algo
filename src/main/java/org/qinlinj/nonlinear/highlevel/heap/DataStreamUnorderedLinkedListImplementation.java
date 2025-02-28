package org.qinlinj.nonlinear.highlevel.heap;

/**
 * DataStreamUnorderedLinkedListImplementation - Implementation of a data stream using an unordered linked list
 * <p>
 * This implementation prioritizes fast addition of elements (O(1)) at the cost of
 * slower maximum element removal (O(n)).
 * <p>
 * Characteristics:
 * - New elements are added to the head of the list in constant time
 * - Finding and removing the maximum element requires a full traversal of the list
 * - Suitable for scenarios where additions are much more frequent than maximum value retrievals
 */
public class DataStreamUnorderedLinkedListImplementation {
    private Node dummyNode;  // Dummy head node for easier list operations

    /**
     * Constructor to initialize the data stream with an empty linked list
     */
    public DataStreamUnorderedLinkedListImplementation() {
        // Create a dummy node with value -1 to simplify operations
        // especially when dealing with the head of the list
        this.dummyNode = new Node(-1);
    }

    /**
     * Adds a new value to the data stream
     * <p>
     * Time Complexity: O(1) - Constant time operation
     *
     * @param val The integer value to add to the stream
     */
    public void add(int val) {
        // Create a new node with the given value
        Node newNode = new Node(val);

        // Insert the new node at the beginning of the list (after dummy node)
        // This ensures O(1) time complexity for addition operations
        newNode.next = dummyNode.next;
        dummyNode.next = newNode;
    }

    /**
     * Removes and returns the maximum value in the data stream
     * <p>
     * Time Complexity: O(n) - Requires two full traversals of the list
     *
     * @return The maximum value that was in the stream
     * @throws RuntimeException if the stream is empty
     */
    public int removeMax() {
        // Check if the list is empty
        if (dummyNode.next == null)
            throw new RuntimeException("removeMax failed: the data stream is empty");

        // FIRST TRAVERSAL:
        // Scan the entire list to find the node with the maximum value
        Node curr = dummyNode.next;
        Node maxValueNode = curr;  // Start with the first node as the max

        while (curr != null) {
            // Update maxValueNode if current node has a larger value
            if (curr.val > maxValueNode.val) {
                maxValueNode = curr;
            }
            curr = curr.next;
        }

        // SECOND TRAVERSAL:
        // Find the node that precedes the maximum value node
        // This is necessary for removing the maximum node from the list
        curr = dummyNode.next;
        Node maxValueNodePrev = dummyNode;

        while (curr != null) {
            if (curr == maxValueNode) {
                break;  // Found the max node, so its predecessor is maxValueNodePrev
            }
            maxValueNodePrev = curr;
            curr = curr.next;
        }

        // Remove the maximum value node from the list
        maxValueNodePrev.next = maxValueNode.next;
        maxValueNode.next = null;  // Clean up the removed node

        return maxValueNode.val;  // Return the maximum value
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

