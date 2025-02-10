package org.qinlinj.linear.queue;

public class QueueTest {
    public static void main(String[] args) {
        ArrayLoopQueue<Integer> queue = new ArrayLoopQueue<>(15);

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

        queue.dequeue();
        System.out.println(queue);

        queue.dequeue();
        System.out.println(queue);
    }
}
