package org.qinlinj.nonlinear.highlevel.set;

import org.qinlinj.nonlinear.tree.binarysearchtree.BinarySearchTree;

public class BSTSet<E extends Comparable<E>> implements Set<E> {
    private BinarySearchTree<E> bst;

    public BSTSet() {
        this.bst = new BinarySearchTree<>();
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void add(Object o) {

    }

    @Override
    public void remove(Object o) {

    }

    @Override
    public boolean contains(Object o) {
        return false;
    }
}
