package org.qinlinj.nonlinear.highlevel.set;

public class HashSetSimple<E> implements Set<E> {
    E[] data;
    int size;

    public HashSetSimple() {
        this.data = (E[]) new Object[10];
        this.size = 0;
    }

    private int hash(E e, int length) {
        return Math.abs(e.hashCode()) % length;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void add(E e) {
        int index = hash(e, data.length);
        if (data[index] == null) {
            data[index] = e;
            size++;

            if (size == data.length) {
                resize(2 * data.length);
            }
        }
    }

    private void resize(int newCapacity) {
        E[] newData = (E[]) new Object[newCapacity];
        for (int i = 0; i < data.length; i++) {
            if (data[i] != null) {
                int newIndex = hash(data[i], newCapacity);
                newData[newIndex] = data[i];
            }
        }
        data = newData;
    }

    @Override
    public void remove(E e) {

    }

    @Override
    public boolean contains(E e) {
        return false;
    }
}
