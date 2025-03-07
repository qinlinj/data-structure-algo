package org.qinlinj.linear.queue;

import java.util.Random;

/**
 * A performance comparison test for different Queue implementations.
 * This class measures and compares the execution time of enqueue and dequeue operations
 * for ArrayListQueue, ArrayLoopQueue, and LinkedListQueue.
 */
public class PerformanceTest {
    // Shared random number generator for creating test data
    private static Random random = new Random();

    /**
     * Measures the time taken to perform enqueue and dequeue operations on a given queue.
     *
     * @param queue the Queue implementation to test
     * @param cnt   the number of enqueue and dequeue operations to perform
     * @return the total time taken for the operations in seconds
     */
    private static double testQueue(Queue<Integer> queue, int cnt) {
        // Record the start time
        long startTime = System.nanoTime();

        // Enqueue random integers
        for (int i = 0; i < cnt; i++) {
            queue.enqueue(random.nextInt());
        }

        // Dequeue all elements
        for (int i = 0; i < cnt; i++) {
            queue.dequeue();
        }

        // Calculate and return the total time spent in seconds
        return (System.nanoTime() - startTime) / 1_000_000_000.0;
    }

    /**
     * Main method to run the performance comparison test.
     * Compares the performance of different Queue implementations.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        // Number of enqueue and dequeue operations to perform
        int cnt = 100_000;

        // Test ArrayListQueue
        Queue<Integer> queue = new ArrayListQueue<>();
        double time1 = testQueue(queue, cnt);
        System.out.println("ArrayListQueue：" + time1 + " seconds");

        // Test ArrayLoopQueue
        queue = new ArrayLoopQueue<>();
        double time3 = testQueue(queue, cnt);
        System.out.println("ArrayLoopQueue：" + time3 + " seconds");

        // Test LinkedListQueue
        queue = new LinkedListQueue<>();
        double time2 = testQueue(queue, cnt);
        System.out.println("LinkedListQueue：" + time2 + " seconds");
    }
}