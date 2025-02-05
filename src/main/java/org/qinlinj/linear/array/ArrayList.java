package org.qinlinj.linear.array;


public class ArrayList<E> {
    private E[] data;
    private int capacity;
    private int size;

    public ArrayList(int capacity) {
        this.data = (E[]) new Object[capacity];
        this.capacity = capacity;
        this.size = 0;
    }

    public ArrayList(E[] arr) {
        this.data = (E[]) new Object[capacity];
        for (int i = 0; i < arr.length; i++) {
            data[i] = arr[i];
        }
        size = arr.length;
        this.capacity = arr.length;
    }

    public ArrayList() {
        this(15);
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int getSize() {
        return size;
    }

    public int getCapacity() {
        return capacity;
    }

    /**** Create ****/

    /**
     * Adding a new element to the specified index location
     * @param index Specified index
     * @param e New element
     */
    public void add(int index, E e) {
        // check parameter validity
        if (index > size || index < 0) {
            throw new IllegalArgumentException("add failed, require index >= 0 && index <= size");
        }
        // capacity expansion
        if (size == data.length) {
            resize(size * 2);
        }
        for (int i = size - 1; i >= index; i--) {
            data[i + 1] = data[i];
        }
        data[index] = e;
        size++;
    }

    public void addFirst(E e) {
        add(0, e);
    }

    public void addLast(E e) {
        add(size, e);
    }

    public void resize(int newCapacity) {
        E[] newData = (E[]) new Object[newCapacity];
        for (int i = 0; i < data.length; i++) {
            newData[i] = data[i];
        }
        data = newData;
        capacity = newCapacity;
    }

    /**** Update ****/
    public void set(int index, E e) {

    }

    /**** Retrieve ****/
    public E get(int index) {
        return null;
    }

    public E getFirst() {
        return null;
    }

    public E getLast() {
        return null;
    }

    public boolean contains(E e) {
        return false;
    }

    public int find(E e) {
        return -1;
    }

    /**** Delete ****/
    public E remove(int index) {
        return null;
    }

    public E removeFirst() {
        return null;
    }

    public E removeLast() {
        return null;
    }

    public void removeElement(E e) {

    }

    @Override
    public String toString() {
        return "";
    }


}
