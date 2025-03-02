package org.qinlinj.nonlinear.highlevel.set;

import org.qinlinj.nonlinear.tree.binarysearchtree.BinarySearchTree;

import java.util.List;

public class BSTSet<E extends Comparable<E>> implements Set<E> {
    private BinarySearchTree<E> bst;

    public BSTSet() {
        this.bst = new BinarySearchTree<>();
    }

    @Override
    public int size() {
        return bst.getSize();
    }

    @Override
    public boolean isEmpty() {
        return bst.isEmpty();
    }

    @Override
    public void add(E e) { // O(logn)
        bst.add(e);
    }

    @Override
    public void remove(E e) { // O(logn)
        bst.remove(e);
    }

    @Override
    public boolean contains(E e) { // O(logn)
        return bst.contains(e);
    }

    public List<E> getAllElements() {
        return bst.inOrder();
    }
}
