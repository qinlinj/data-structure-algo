package org.qinlinj.linear.arraylist;

/**
 * A demonstration and test class for the ArrayList implementation.
 * This class showcases various methods of the ArrayList class
 * and provides a simple way to verify its functionality.
 */
public class ArrayListTest {
    /**
     * Main method to demonstrate ArrayList operations.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        // Create a new ArrayList of Integers with initial capacity of 10
        ArrayList<Integer> arrayList = new ArrayList<>(10);

        // Check if the list is initially empty
        System.out.println("Is list empty initially? " + arrayList.isEmpty());

        // Demonstrate various ways of adding elements
        arrayList.addFirst(34);     // Add 34 at the beginning
        arrayList.addLast(23);      // Add 23 at the end
        arrayList.add(2, 50);       // Add 50 at index 2

        // Check if the list is empty after adding elements
        System.out.println("Is list empty after adding elements? " + arrayList.isEmpty());

        // Print the current state of the list
        System.out.println("List after adding elements: " + arrayList);

        // Remove an element at a specific index
        arrayList.remove(1);
        System.out.println("List after removing element at index 1: " + arrayList);

        // Remove a specific element
        arrayList.removeElement(1);
        System.out.println("List after attempting to remove element 1: " + arrayList);

        // Demonstrate ArrayList with String type
        ArrayList<String> arrayList1 = new ArrayList<>(10);
        arrayList1.add(0, "hello");     // Add "hello" at index 0
        arrayList1.addLast("world");    // Add "world" at the end
        System.out.println("String ArrayList: " + arrayList1);
    }
}