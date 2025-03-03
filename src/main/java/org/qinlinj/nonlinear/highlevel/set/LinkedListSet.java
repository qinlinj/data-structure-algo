package org.qinlinj.nonlinear.highlevel.set;

import org.qinlinj.linear.linkedlist.LinkedList;

public class LinkedListSet<E> implements Set<E> {
    // Internal LinkedList to store unique elements
    private LinkedList<E> data;

    // Constructor: Initialize empty LinkedList
    public LinkedListSet() {
        this.data = new LinkedList<>();
    }

    // Main method to demonstrate LinkedListSet functionality
    public static void main(String[] args) {
        // Create a new set and test its basic operations
        Set<Integer> set = new LinkedListSet<>();
        set.add(2);
        set.add(4);
        set.add(1);

        // Print initial set
        System.out.println(set);

        // Attempt to add duplicate (will be ignored)
        set.add(2);
        System.out.println(set);

        // Check for element existence
        System.out.println(set.contains(4));

        // Remove an element
        set.remove(1);
        System.out.println(set);
    }

    // Return the number of elements in the set
    @Override
    public int size() {
        return data.getSize();
    }

    // Check if the set is empty
    @Override
    public boolean isEmpty() {
        return data.isEmpty();
    }

    // Add element to the set only if it doesn't already exist
    @Override
    public void add(E e) { // O(n) time complexity due to contains check
        if (!data.contains(e)) {
            data.addFirst(e); // Add to the beginning of the list
        }
    }

    // Remove an element from the set
    @Override
    public void remove(E e) { // O(n) time complexity
        data.removeElement(e);
    }

    // Check if an element exists in the set
    @Override
    public boolean contains(E e) { // O(n) time complexity
        return data.contains(e);
    }

    // Custom toString method for set representation
    @Override
    public String toString() {
        return "LinkedListSet{" +
                "data=" + data.toString() +
                '}';
    }
}