package org.qinlinj.linear.queue;

public class QueueTest {
    public static void main(String[] args) {
        ArrayListQueue<Integer> queue = new ArrayListQueue<>();

        queue.enqueue(10);
        System.out.println(queue);

        queue.enqueue(20);
        System.out.println(queue);

        queue.enqueue(30);
        System.out.println(queue);

        queue.dequeue();
        System.out.println(queue);

        queue.dequeue();
        System.out.println(queue);
    }
}
