package org.qinlinj.linear.queue;

import java.util.Arrays;

public class ArrayQueue<E> implements Queue<E> {
    private E[] data;
    private int head;
    private int tail;

    public ArrayQueue(int capacity) {
        this.data = (E[]) new Object[capacity];
        head = tail = 0;
    }

    public static void main(String[] args) {
        ArrayQueue<Integer> queue1 = new ArrayQueue<>(5);
        queue1.enqueue(10);
        queue1.enqueue(2);
        queue1.enqueue(4);
        queue1.enqueue(3);
        System.out.println(queue1.getSize());
        System.out.println(queue1.dequeue());
        System.out.println(queue1.getSize());
        queue1.enqueue(30);
        System.out.println(queue1.dequeue());
        System.out.println(queue1);
    }

    @Override
    public int getSize() {
        return tail - head;
    }

    @Override
    public boolean isEmpty() {
        return tail == head;
    }

    @Override
    public void enqueue(E e) {
        if (tail == data.length) {
            throw new RuntimeException("enqueue error, this queue is full");
        }
        data[tail] = e;
        tail++;
    }

    @Override
    public E dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("dequeue error, this queue is empty");
        }
        E res = data[head];
        head++;
        return res;
    }

    @Override
    public E getFront() {
        if (isEmpty()) {
            throw new RuntimeException("get front error, this queue is empty");
        }
        return data[head];
    }

    @Override
    public String toString() {
        return "ArrayQueue{" +
                "data=" + Arrays.toString(data) +
                ", head=" + head +
                ", tail=" + tail +
                '}';
    }
}
