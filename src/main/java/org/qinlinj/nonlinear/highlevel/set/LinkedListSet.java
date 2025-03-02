package org.qinlinj.nonlinear.highlevel.set;

import org.qinlinj.linear.linkedlist.LinkedList;

public class LinkedListSet<E> implements Set<E> {
    private LinkedList<E> data;

    public LinkedListSet() {
        this.data = new LinkedList<>();
    }

    public static void main(String[] args) {
        Set<Integer> set = new LinkedListSet<>();
        set.add(2);
        set.add(4);
        set.add(1);

        System.out.println(set);

        set.add(2);
        System.out.println(set);

        System.out.println(set.contains(4));

        set.remove(1);
        System.out.println(set);
    }

    @Override
    public int size() {
        return data.getSize();
    }

    @Override
    public boolean isEmpty() {
        return data.isEmpty();
    }

    @Override
    public void add(E e) { // O(n)
        if (!data.contains(e)) {
            data.addFirst(e);
        }
    }

    @Override
    public void remove(E e) { // O(n)
        data.removeElement(e);
    }

    @Override
    public boolean contains(E e) { // O(n)
        return data.contains(e);
    }

    @Override
    public String toString() {
        return "LinkedListSet{" +
                "data=" + data.toString() +
                '}';
    }
}
