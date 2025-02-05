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

    }

    /**** Update ****/

    /**** Retrieve ****/

    /**** Delete ****/

}
