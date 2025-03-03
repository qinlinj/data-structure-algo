package org.qinlinj.nonlinear.highlevel.set;

import org.qinlinj.nonlinear.tree.binarysearchtree.BinarySearchTree;

import java.util.List;

public class BSTSet<E extends Comparable<E>> implements Set<E> {
    // Internal Binary Search Tree to store unique elements
    private BinarySearchTree<E> bst;

    // Constructor: Initialize empty Binary Search Tree
    public BSTSet() {
        this.bst = new BinarySearchTree<>();
    }

    // Main method to demonstrate BSTSet functionality
    public static void main(String[] args) {
        // Create a new set and test its operations
        BSTSet<Integer> set = new BSTSet<>();
        set.add(2);
        set.add(1);
        set.add(9);
        set.add(5);
        set.add(2); // Duplicate will be ignored

        // Print all elements in-order
        System.out.println(set.getAllElements());
    }

    // Return the number of elements in the set
    @Override
    public int size() {
        return bst.getSize();
    }

    // Check if the set is empty
    @Override
    public boolean isEmpty() {
        return bst.isEmpty();
    }

    // Add element to the set
    @Override
    public void add(E e) { // O(log n) time complexity due to BST properties
        bst.add(e);
    }

    // Remove an element from the set
    @Override
    public void remove(E e) { // O(log n) time complexity
        bst.remove(e);
    }

    // Check if an element exists in the set
    @Override
    public boolean contains(E e) { // O(log n) time complexity
        return bst.contains(e);
    }

    // Retrieve all elements in sorted order
    public List<E> getAllElements() {
        return bst.inOrder();
    }
}