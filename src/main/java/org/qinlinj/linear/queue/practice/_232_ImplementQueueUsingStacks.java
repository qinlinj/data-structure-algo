package org.qinlinj.linear.queue.practice;

import java.util.Stack;

public class _232_ImplementQueueUsingStacks {
    class MyQueue {
        Stack<Integer> stack1;
        Stack<Integer> stack2;

        public MyQueue() {
            this.stack1 = new Stack<>();
            this.stack2 = new Stack<>();
        }

        public void push(int x) {
            stack1.push(x);
        }

        public int pop() {
            move(stack1, stack2);
            return stack2.pop();
        }

        public int peek() {
            move(stack1, stack2);
            return stack2.peek();
        }

        public boolean empty() {
            return stack1.isEmpty() && stack2.isEmpty();
        }

        private void move(Stack<Integer> stack1, Stack<Integer> stack2) {
            if (stack2.isEmpty()) {
                while (!stack1.isEmpty()) {
                    stack2.push(stack1.pop());
                }
            }
        }
    }
}