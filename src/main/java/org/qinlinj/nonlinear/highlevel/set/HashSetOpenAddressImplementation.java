package org.qinlinj.nonlinear.highlevel.set;

public class HashSetOpenAddressImplementation<E> implements Set<E> {
    private Item<E>[] items;
    private int size;
    private int deleteCount;
    private double loadFactor = 0.75;

    public HashSetOpenAddressImplementation() {
        this.items = new Item[10];
        this.size = 0;
        this.deleteCount = 0;
    }

    public HashSetOpenAddressImplementation(int loadFactor) {
        this();
        this.loadFactor = loadFactor;
    }

    public static void main(String[] args) {
        Set<Integer> set = new HashSetOpenAddressImplementation<>();
        set.add(11);
        set.add(21);

        System.out.println(set.contains(21));
        System.out.println(set.contains(11));

    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    public int hash(E e, int length) {
        return Math.abs(e.hashCode()) % length;
    }

    @Override
    public void add(E e) {
        int index = hash(e, items.length);

        while (items[index] != null) {
            if (!items[index].isDeleted && e.equals(items[index].data)) return;
            index++;
            index = index % items.length;
        }

        items[index] = new Item(e);
        size++;

        if (size >= items.length * loadFactor) {
            resize(2 * items.length);
        }
    }

    private void resize(int newCapacity) {
        Item<E>[] newData = new Item[newCapacity];
        for (int i = 0; i < items.length; i++) {
            if (items[i] != null && !items[i].isDeleted) {
                int newIndex = hash(items[i].data, newCapacity);
                newData[newIndex] = items[i];
            }
        }

        deleteCount = 0;
        items = newData;
    }

    @Override
    public void remove(E e) {
        int index = hash(e, items.length);

        while (items[index] != null && (!e.equals(items[index].data) || !items[index].isDeleted)) {
            index++;
            index = index % items.length;
        }

        if (items[index] != null && !items[index].isDeleted) {
            items[index].isDeleted = true;
            deleteCount++;
            size--;
        }

        if (deleteCount + size >= items.length * loadFactor) {
            resize(items.length);
        }
    }

    @Override
    public boolean contains(E e) {
        int index = hash(e, items.length);

        while (items[index] != null &&
                (!e.equals(items[index].data) || items[index].isDeleted)) {
            index++;
            index = index % items.length;
        }

        return items[index] != null && !items[index].isDeleted;
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
