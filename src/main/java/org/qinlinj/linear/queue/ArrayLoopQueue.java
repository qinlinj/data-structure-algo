package org.qinlinj.linear.queue;

public class ArrayLoopQueue<E> implements Queue<E> {
    private E[] data;
    private int head;
    private int tail;
    private int size;

    public ArrayLoopQueue(int capacity) {
        this.data = (E[]) new Object[capacity];
        head = tail = size = 0;
    }

    public ArrayLoopQueue() {
        this(15);
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return tail == head;
    }

    @Override
    public void enqueue(E e) {
        if (size == data.length) {
            resize(data.length * 2);
        }
        data[tail] = e;
        size++;
        tail = (tail + 1) % data.length;
    }

    @Override
    public E dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("dequeue error, this queue is empty");
        }
        E res = data[head];
        data[head] = null;
        head = (head + 1) % data.length;
        size--;
        if (size <= data.length / 4) resize(data.length / 2);
        return res;
    }

    @Override
    public E getFront() {
        if (isEmpty()) {
            throw new RuntimeException("get front error, this queue is empty");
        }
        return data[head];
    }

    private void resize(int newCapacity) {
        E[] newData = (E[]) new Object[newCapacity];
        int j = 0;
        for (int i = 0; i < size; i++) {
            newData[i] = data[(head + i) % data.length];
        }
        data = newData;
        head = 0;
        tail = size;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Queue：size = %d, capacity = %d\n", size, data.length));
        sb.append("Head [");
        for (int i = head; i != tail; i = (i + 1) % data.length) {
            sb.append(data[i]);
            if ((i + 1) % data.length != tail) {
                sb.append(",");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}

