package org.qinlinj.linear.algo.linkedlist;

/**
 * ListNode
 * <p>
 * This class represents a node in a singly linked list data structure.
 * Each node contains:
 * - A value (int)
 * - A reference to the next node in the sequence
 * <p>
 * The class provides utility methods for:
 * - Creating a linked list from an array
 * - Displaying the linked list as a string
 */
public class ListNode {
    /**
     * The value stored in this node.
     * Made public for easy access in algorithms operating on linked lists.
     */
    public int val;

    /**
     * Reference to the next node in the linked list.
     * If this is the last node in the list, next will be null.
     */
    public ListNode next;

    /**
     * Constructor to create a new node with a specified value.
     * By default, the next reference is set to null.
     *
     * @param val The integer value to store in this node
     */
    public ListNode(int val) {
        this.val = val;
        this.next = null;
    }

    /**
     * Static utility method to create a linked list from an array of integers.
     * This provides a convenient way to create test data for linked list operations.
     * <p>
     * Algorithm:
     * 1. Create the head node with the first element of the array
     * 2. Iterate through the rest of the array, creating new nodes and linking them
     * 3. Return the head node, which represents the entire linked list
     * <p>
     * Time Complexity: O(n) where n is the length of the input array
     * Space Complexity: O(n) for storing n nodes
     *
     * @param arr The array of integers to convert to a linked list
     * @return The head node of the newly created linked list, or null if the input array is empty
     * <p>
     * Example: fromArray([1, 2, 3]) creates the linked list 1->2->3->null
     */
    public static ListNode fromArray(int[] arr) {
        // Handle edge case: empty or null array
        if (arr == null || arr.length == 0) return null;

        // Create the head node with the first element
        ListNode head = new ListNode(arr[0]);

        // Use curr to keep track of the current node while building the list
        ListNode curr = head;

        // Iterate through the rest of the array elements
        for (int i = 1; i < arr.length; i++) {
            // Create a new node for the current array element
            curr.next = new ListNode(arr[i]);

            // Move to the newly created node
            curr = curr.next;
        }

        // Return the head node, which represents the entire linked list
        return head;
    }

    /**
     * Main method to demonstrate the creation and string representation of a linked list.
     * <p>
     * This example:
     * 1. Creates a linked list [23, 12, 11, 34] using the fromArray method
     * 2. Prints the linked list using the toString method
     *
     * @param args Command-line arguments (not used)
     */
    public static void main(String[] args) {
        // Create a linked list from an array
        ListNode head = ListNode.fromArray(new int[]{23, 12, 11, 34});

        // Print the linked list (should output "23->12->11->34->null")
        System.out.println(head);
    }

    /**
     * Overrides the default toString method to provide a visual representation of the linked list.
     * The format is: val1->val2->val3->...->null
     * <p>
     * This is useful for debugging and visualizing the structure of the linked list.
     * <p>
     * Time Complexity: O(n) where n is the length of the linked list
     * Space Complexity: O(n) for storing the string representation
     *
     * @return A string representation of the linked list starting from this node
     * <p>
     * Example: For a list starting with nodes containing values 1, 2, 3, the output would be "1->2->3->null"
     */
    @Override
    public String toString() {
        // Use StringBuilder for efficient string concatenation
        StringBuilder sb = new StringBuilder();

        // Start from the current node (this)
        ListNode curr = this;

        // Traverse the list until we reach the end (null)
        while (curr != null) {
            // Append the current node's value and the arrow
            sb.append(curr.val).append("->");

            // Move to the next node
            curr = curr.next;
        }

        // Append "null" to indicate the end of the list
        sb.append("null");

        // Convert StringBuilder to String and return
        return sb.toString();
    }

    /**
     * Potential additional methods that could be added to this class:
     *
     * 1. A method to find the length of the list:
     *    public int length() {
     *        int count = 0;
     *        for (ListNode curr = this; curr != null; curr = curr.next) {
     *            count++;
     *        }
     *        return count;
     *    }
     *
     * 2. A method to find a node with a specific value:
     *    public ListNode find(int target) {
     *        for (ListNode curr = this; curr != null; curr = curr.next) {
     *            if (curr.val == target) return curr;
     *        }
     *        return null;
     *    }
     *
     * 3. A method to create a copy of the list:
     *    public ListNode copy() {
     *        ListNode newHead = new ListNode(this.val);
     *        ListNode curr = this.next;
     *        ListNode newCurr = newHead;
     *        while (curr != null) {
     *            newCurr.next = new ListNode(curr.val);
     *            curr = curr.next;
     *            newCurr = newCurr.next;
     *        }
     *        return newHead;
     *    }
     */
}