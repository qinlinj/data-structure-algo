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
        System.arraycopy(arr, 0, data, 0, arr.length);
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
     *
     * @param index Specified index
     * @param e     New element
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

    /**
     * Modify array capacity
     *
     * @param newCapacity New capacity
     */
    public void resize(int newCapacity) {
        E[] newData = (E[]) new Object[newCapacity];
        System.arraycopy(data, 0, newData, 0, size);
        data = newData;
        capacity = newCapacity;
    }

    /**** Update ****/
    /**
     * Change the element at the index location to a new element e
     *
     * @param index Index position to be modified
     * @param e     The newly set element value
     */
    public void set(int index, E e) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("set failed, require index >= 0 && index < size");
        }
        data[index] = e;
    }

    /**** Retrieve ****/
    /**
     * Gets the element of the index location
     *
     * @param index Assigned index
     * @return the element value corresponding to the specified index
     */
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("get failed, require index >= 0 && index < size");
        }
        return data[index];
    }

    public E getFirst() {
        return get(0);
    }

    public E getLast() {
        return get(size - 1);
    }

    /**
     * Finds whether the array has element e, and returns false if the element e does not exist
     *
     * @param e Assigned array element
     * @return Whether the result of element e exists in the array
     */
    public boolean contains(E e) {
        for (int i = 0; i < size; i++) {
            if (e.equals(i)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Finds the index of the array element e, and returns -1 if the element e does not exist
     *
     * @param e Assigned array element
     * @return The index of the specified element e
     */
    public int find(E e) {
        for (int i = 0; i < size; i++) {
            if (e.equals(i)) {
                return i;
            }
        }
        return -1;
    }

    /**** Delete ****/
    /**
     * Deletes the element at the specified index position
     *
     * @param index Assigned index
     * @return The deleted element
     */
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("remove failed, require index >= 0 && index < size");
        }
        E res = data[index];
        for (int i = index + 1; i < size; i++) {
            data[i - 1] = data[i];
        }
        size--;
        data[size] = null;
        if (size == data.length / 4 && data.length / 2 != 0) {
            resize(data.length / 2);
        }
        return res;
    }

    public E removeFirst() {
        return remove(0);
    }

    public E removeLast() {
        return remove(size - 1);
    }

    public void removeElement(E e) {
        int index = find(e);
        if (index != -1) {
            remove(index);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Array: size = %d, capacity = %d\n", size, data.length));
        sb.append("[");
        for (int i = 0; i < size; i++) {
            sb.append(data[i]);
            if (i != size - 1) {
                sb.append(",");
            }
        }
        sb.append("]");
        return sb.toString();
    }

}
