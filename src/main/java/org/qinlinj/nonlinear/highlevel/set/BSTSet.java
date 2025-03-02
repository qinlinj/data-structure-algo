package org.qinlinj.nonlinear.highlevel.set;

import org.qinlinj.nonlinear.tree.binarysearchtree.BinarySearchTree;

import java.util.List;

public class BSTSet<E extends Comparable<E>> implements Set<E> {
    private BinarySearchTree<E> bst;

    public BSTSet() {
        this.bst = new BinarySearchTree<>();
    }

    public static void main(String[] args) {
        BSTSet<Integer> set = new BSTSet<>();
        set.add(2);
        set.add(1);
        set.add(9);
        set.add(5);
        set.add(2);
        System.out.println(set.getAllElements());
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
