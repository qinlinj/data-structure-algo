package org.qinlinj.nonlinear.highlevel.set;

import org.qinlinj.linear.arraylist.ArrayList;

public class SetArrayImplementation<E> implements Set<E> {
    // Internal ArrayList to store unique elements
    private ArrayList<E> data;

    // Constructor: Initialize empty ArrayList
    public SetArrayImplementation() {
        this.data = new ArrayList<>();
    }

    // Main method to demonstrate SetArrayImplementation functionality
    public static void main(String[] args) {
        // Create a new set and test its basic operations
        Set<Integer> set = new SetArrayImplementation<>();
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
            data.addLast(e);
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
        return "SetArrayImplementation{" +
                "data=" + data.toString() +
                '}';
    }
}