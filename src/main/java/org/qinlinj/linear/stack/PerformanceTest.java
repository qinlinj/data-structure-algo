package org.qinlinj.linear.stack;

import java.util.Random;

/**
 * A performance comparison test for different Stack implementations.
 * This class measures and compares the execution time of push and pop operations
 * for DynamicArrayStack and LinkedListStack.
 */
public class PerformanceTest {
    // Shared random number generator for creating test data
    private static Random random = new Random();

    /**
     * Measures the time taken to perform push and pop operations on a given stack.
     *
     * @param stack the Stack implementation to test
     * @param cnt   the number of push and pop operations to perform
     * @return the total time taken for the operations in seconds
     */
    private static double testStack(Stack<Integer> stack, int cnt) {
        // Record the start time
        long startTime = System.nanoTime();

        // Push random integers onto the stack
        for (int i = 0; i < cnt; i++) {
            stack.push(random.nextInt());
        }

        // Pop all elements from the stack
        for (int i = 0; i < cnt; i++) {
            stack.pop();
        }

        // Calculate and return the total time spent in seconds
        return (System.nanoTime() - startTime) / 1_000_000_000.0;
    }

    /**
     * Main method to run the performance comparison test.
     * Compares the performance of DynamicArrayStack and LinkedListStack.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        // Number of push and pop operations to perform
        int cnt = 100_000_000;

        // Test DynamicArrayStack
        Stack<Integer> stack = new DynamicArrayStack<>(10);
        double time1 = testStack(stack, cnt);
        System.out.println("DynamicArrayStack time spent：" + time1 + " seconds");

        // Test LinkedListStack
        stack = new LinkedListStack<>();
        double time2 = testStack(stack, cnt);
        System.out.println("LinkedListStack time spent：" + time2 + " seconds");
    }
}