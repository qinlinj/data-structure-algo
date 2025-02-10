package org.qinlinj.linear.queue;

import java.util.Random;

public class PerformanceTest {
    private static Random random = new Random();

    private static double testQueue(Queue<Integer> queue, int cnt) {
        long startTime = System.nanoTime();

        for (int i = 0; i < cnt; i++) {
            queue.enqueue(random.nextInt());
        }
        for (int i = 0; i < cnt; i++) {
            queue.dequeue();
        }

        return (System.nanoTime() - startTime) / 1000_000_000.0;
    }

    public static void main(String[] args) {
        int cnt = 100000;

        Queue<Integer> queue = new ArrayListQueue<>();
        double time1 = testQueue(queue, cnt);
        System.out.println("ArrayListQueue：" + time1);

        queue = new ArrayLoopQueue<>();
        double time3 = testQueue(queue, cnt);
        System.out.println("ArrayLoopQueue：" + time3);

        queue = new LinkedListQueue<>();
        double time2 = testQueue(queue, cnt);
        System.out.println("LinkedListQueue：" + time2);
    }
}
