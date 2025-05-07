package org.qinlinj.algoframework._500_data_structure_design._560_application_design._563_classic_design_practice; /**
 * LeetCode Problem 155: Min Stack
 * <p>
 * This problem requires designing a stack data structure that supports regular stack operations
 * plus a getMin() operation in constant time.
 * <p>
 * Problem Description:
 * - Implement a stack that supports push, pop, top, and retrieving the minimum element
 * - All operations must work in constant time O(1)
 * <p>
 * Key Insight:
 * - The challenge is maintaining the minimum value when elements are popped
 * - When an element is popped from the stack, if it was the minimum, we need to know the new minimum
 * - Solution: Use an additional stack to track minimums at each state of the stack
 * <p>
 * Two Implementation Approaches:
 * 1. Original approach: Maintain a parallel stack that tracks the minimum at each position
 * - Every time we push a value, we also push the current minimum to minStack
 * - Every time we pop a value, we also pop from minStack
 * <p>
 * 2. Optimized approach: Only push to minStack when a new minimum is encountered
 * - When we pop, we only pop from minStack if the popped value equals the current minimum
 * - This reduces space usage when there are many elements greater than the minimum
 * <p>
 * Time Complexity: O(1) for all operations
 * Space Complexity: O(n) in worst case, potentially better with optimization
 */

import java.util.*;

public class _563_d_MinStack {

    public static void main(String[] args) {
        // Test the original implementation
        System.out.println("Testing original MinStack implementation:");
        MinStack1 minStack1 = new MinStack1();
        testMinStack(minStack1);

        // Test the optimized implementation
        System.out.println("\nTesting optimized MinStack implementation:");
        MinStack minStack2 = new MinStack();
        testMinStack(minStack2);
    }

    /**
     * Helper method to test a MinStack implementation with the example from the problem
     */
    private static void testMinStack(Object stack) {
        System.out.println("Push -2");
        if (stack instanceof MinStack1) {
            ((MinStack1) stack).push(-2);
        } else {
            ((MinStack) stack).push(-2);
        }

        System.out.println("Push 0");
        if (stack instanceof MinStack1) {
            ((MinStack1) stack).push(0);
        } else {
            ((MinStack) stack).push(0);
        }

        System.out.println("Push -3");
        if (stack instanceof MinStack1) {
            ((MinStack1) stack).push(-3);
        } else {
            ((MinStack) stack).push(-3);
        }

        System.out.println("getMin() -> " +
                (stack instanceof MinStack1 ?
                        ((MinStack1) stack).getMin() :
                        ((MinStack) stack).getMin()));

        System.out.println("pop()");
        if (stack instanceof MinStack1) {
            ((MinStack1) stack).pop();
        } else {
            ((MinStack) stack).pop();
        }

        System.out.println("top() -> " +
                (stack instanceof MinStack1 ?
                        ((MinStack1) stack).top() :
                        ((MinStack) stack).top()));

        System.out.println("getMin() -> " +
                (stack instanceof MinStack1 ?
                        ((MinStack1) stack).getMin() :
                        ((MinStack) stack).getMin()));
    }

    /**
     * Original approach: Store minimum value for every element
     */
    static class MinStack1 {
        // Main stack to store all elements
        private Stack<Integer> stack;

        // Auxiliary stack to track minimums
        private Stack<Integer> minStack;

        public MinStack1() {
            stack = new Stack<>();
            minStack = new Stack<>();
        }

        public void push(int val) {
            stack.push(val);

            // For minStack, push the current minimum (either val or previous min)
            if (minStack.isEmpty() || val <= minStack.peek()) {
                minStack.push(val);
            } else {
                minStack.push(minStack.peek());
            }
        }

        public void pop() {
            stack.pop();
            minStack.pop();
        }

        public int top() {
            return stack.peek();
        }

        public int getMin() {
            return minStack.peek();
        }
    }

    /**
     * Optimized approach: Only store minimum values when they change
     */
    static class MinStack {
        // Main stack to store all elements
        private Stack<Integer> stack;

        // Auxiliary stack to track minimums
        private Stack<Integer> minStack;

        public MinStack() {
            stack = new Stack<>();
            minStack = new Stack<>();
        }

        public void push(int val) {
            stack.push(val);

            // Only push to minStack if val is a new minimum
            if (minStack.isEmpty() || val <= minStack.peek()) {
                minStack.push(val);
            }
        }

        public void pop() {
            // If we're popping the current minimum, update minStack
            if (stack.peek().equals(minStack.peek())) {
                minStack.pop();
            }
            stack.pop();
        }

        public int top() {
            return stack.peek();
        }

        public int getMin() {
            return minStack.peek();
        }
    }
}