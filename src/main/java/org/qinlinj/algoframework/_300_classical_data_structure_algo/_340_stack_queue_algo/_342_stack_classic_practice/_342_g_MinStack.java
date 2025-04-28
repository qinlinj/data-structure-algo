package org.qinlinj.algoframework._300_classical_data_structure_algo._340_stack_queue_algo._342_stack_classic_practice;

/**
 * LeetCode 155: Min Stack
 * <p>
 * Problem Description:
 * Design a stack that supports push, pop, top, and retrieving the minimum element
 * in constant time.
 * <p>
 * Implement the MinStack class:
 * - MinStack() initializes the stack object.
 * - void push(int val) pushes the element val onto the stack.
 * - void pop() removes the element on the top of the stack.
 * - int top() gets the top element of the stack.
 * - int getMin() retrieves the minimum element in the stack.
 * <p>
 * All operations must run in constant time (O(1)).
 * <p>
 * Solution Approach:
 * The challenge is tracking the minimum element in constant time, especially after
 * popping elements. The key insight is to track minimum values at each step:
 * <p>
 * 1. Use two stacks:
 * - Main stack (stk): Stores all elements
 * - Minimum stack (minStk): Tracks minimum values at each stage
 * <p>
 * 2. When pushing a new element:
 * - Push to the main stack
 * - For the minimum stack, push the element only if it's less than or equal to
 * the current minimum (or if minStk is empty)
 * <p>
 * 3. When popping:
 * - If the popped element equals the current minimum, also pop from minStk
 * - This ensures minStk always has the current minimum at its top
 * <p>
 * Time Complexity: O(1) for all operations
 * Space Complexity: O(n) in the worst case
 */

import java.util.*;

public class _342_g_MinStack {
    private Stack<Integer> stack;      // Main stack for all elements
    private Stack<Integer> minStack;   // Stack to track minimum elements

    /** Initialize your data structure here. */
    public _342_g_MinStack() {
        stack = new Stack<>();
        minStack = new Stack<>();
    }

    // Example usage
    public static void main(String[] args) {
        _342_g_MinStack minStack = new _342_g_MinStack();

        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);

        System.out.println("Current minimum: " + minStack.getMin()); // -3

        minStack.pop();
        System.out.println("Top element: " + minStack.top()); // 0
        System.out.println("Current minimum: " + minStack.getMin()); // -2

        minStack.push(-5);
        System.out.println("Current minimum: " + minStack.getMin()); // -5
    }

    /** Push element val onto stack. */
    public void push(int val) {
        stack.push(val);

        // If minStack is empty or val is the new minimum, push to minStack
        if (minStack.isEmpty() || val <= minStack.peek()) {
            minStack.push(val);
        }
    }

    /** Removes the element on top of the stack. */
    public void pop() {
        // If the popped element is the current minimum, also pop from minStack
        if (stack.peek().equals(minStack.peek())) {
            minStack.pop();
        }
        stack.pop();
    }

    /** Get the top element. */
    public int top() {
        return stack.peek();
    }

    /** Retrieve the minimum element in the stack. */
    public int getMin() {
        return minStack.peek();
    }
}

/**
 * Alternative optimization:
 *
 * We can reduce space by not pushing duplicates to minStack. Instead, we can
 * track how many times the current minimum appears in the main stack.
 *
 * class MinStackOptimized {
 *     private Stack<Integer> stack;
 *     private Stack<int[]> minStack; // [minValue, count]
 *
 *     public MinStackOptimized() {
 *         stack = new Stack<>();
 *         minStack = new Stack<>();
 *     }
 *
 *     public void push(int val) {
 *         stack.push(val);
 *
 *         if (minStack.isEmpty() || val < minStack.peek()[0]) {
 *             // New minimum, push with count 1
 *             minStack.push(new int[]{val, 1});
 *         } else if (val == minStack.peek()[0]) {
 *             // Same as current minimum, increment count
 *             minStack.peek()[1]++;
 *         }
 *     }
 *
 *     public void pop() {
 *         int val = stack.pop();
 *
 *         if (val == minStack.peek()[0]) {
 *             minStack.peek()[1]--;
 *             if (minStack.peek()[1] == 0) {
 *                 minStack.pop();
 *             }
 *         }
 *     }
 *
 *     public int top() {
 *         return stack.peek();
 *     }
 *
 *     public int getMin() {
 *         return minStack.peek()[0];
 *     }
 * }
 */
