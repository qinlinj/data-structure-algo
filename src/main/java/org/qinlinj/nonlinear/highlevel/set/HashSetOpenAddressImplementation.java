package org.qinlinj.nonlinear.highlevel.set;

public class HashSetOpenAddressImplementation<E> implements Set<E> {
    Item[] data;
    int size;

    public HashSetOpenAddressImplementation() {
        this.data = new Item[10];
        this.size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    public int hash(E e) {
        return Math.abs(e.hashCode());
    }

    @Override
    public void add(E e) {
        int index = e.hashCode();
        while (data[index] != null && !data[index].isDeleted) {
            if (data[index].data.equals(e)) {
                return;
            }
            index = (index + 1) % data.length;
        }
        while (data[index] != null || !data[index].isDeleted) {
            data[index].data = e;
        }

    }

    @Override
    public void remove(E e) {

    }

    @Override
    public boolean contains(E e) {
        return false;
    }

    private class Item<E> {
        E data;
        boolean isDeleted;

        public Item(E data) {
            this.data = data;
            this.isDeleted = false;
        }

        @Override
        public int hashCode() {
            return data.hashCode();
        }
    }
}
