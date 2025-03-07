package org.qinlinj.linear.linkedlist;

/**
 * A demonstration class for LinkedList operations.
 * This class showcases various methods of the LinkedList implementation
 * and provides a simple way to verify its functionality.
 */
public class LinkedListTest {
    /**
     * Main method to demonstrate LinkedList functionality.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        // Create a new LinkedList of Integers
        LinkedList<Integer> linkedList = new LinkedList<>();

        // Add an element to the end of the list
        linkedList.addLast(5);
        System.out.println("After addLast(5): " + linkedList);

        // Add an element to the beginning of the list
        linkedList.addFirst(10);
        System.out.println("After addFirst(10): " + linkedList);

        // Add an element at a specific index
        linkedList.add(2, 34);
        System.out.println("After add(2, 34): " + linkedList);

        // Retrieve an element at a specific index
        System.out.println("Element at index 1: " + linkedList.get(1));

        // Add another element to the beginning
        linkedList.addFirst(50);
        System.out.println("After addFirst(50): " + linkedList);

        // Remove an element at a specific index
        linkedList.remove(2);
        System.out.println("After remove(2): " + linkedList);

        // Remove the first element
        linkedList.removeFirst();
        System.out.println("After removeFirst(): " + linkedList);
    }
}