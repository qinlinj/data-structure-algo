package org.qinlinj.linear.stack;

import java.util.Stack;

/**
 * A demonstration class for stack operations using different stack implementations.
 * This class shows basic push and pop operations on a stack.
 */
public class ArrayStackTest {
    /**
     * Main method to demonstrate stack functionality.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        // Uncomment different stack implementations to compare their behavior

        // Using Java's built-in Stack class
        Stack<Integer> stack = new Stack<>();

        // Using custom ArrayStack implementation with fixed capacity
        // Stack<Integer> stack = new ArrayStack<>(15);

        // Using custom DynamicArrayStack implementation
        // Stack<Integer> stack = new DynamicArrayStack<>(15);

        // Using custom LinkedListStack implementation
        // Stack<Integer> stack = new LinkedListStack<>();

        // Alternative: Using ArrayDeque as a stack
        // Deque<Integer> stack = new ArrayDeque<>();

        // Push elements onto the stack
        stack.push(10);
        System.out.println("After pushing 10: " + stack);

        stack.push(20);
        System.out.println("After pushing 20: " + stack);

        stack.push(30);
        System.out.println("After pushing 30: " + stack);

        // Pop elements from the stack
        stack.pop();
        System.out.println("After first pop: " + stack);

        stack.pop();
        System.out.println("After second pop: " + stack);
    }
}