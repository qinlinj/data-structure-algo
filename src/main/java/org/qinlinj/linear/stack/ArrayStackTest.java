package org.qinlinj.linear.stack;

public class ArrayStackTest {
    public static void main(String[] args) {
        // Stack<Integer> stack = new ArrayStack<>(15);
        Stack<Integer> stack = new DynamicArrayStack<>(15);
        // Deque<Integer> stack = new ArrayDeque<>();
        stack.push(10);
        System.out.println(stack);
        stack.push(20);
        System.out.println(stack);
        stack.push(30);
        System.out.println(stack);

        stack.pop();
        System.out.println(stack);
        stack.pop();
        System.out.println(stack);
    }
}
