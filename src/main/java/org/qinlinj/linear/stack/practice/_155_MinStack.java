package org.qinlinj.linear.stack.practice;

import java.util.Stack;

public class _155_MinStack {
    class MinStack1 {
        private Stack<Integer> stack;

        public MinStack1() {
            stack = new Stack<>();
        }

        public void push(int x) {
            stack.push(x);
        }

        public void pop() {
            stack.pop();
        }

        public int top() {
            return stack.peek();
        }

        public int getMin() {
            int minValue = stack.peek();
            for (Integer i : stack) {
                minValue = Math.min(minValue, i);
            }
            return minValue;
        }
    }

    class MinStack2 {
        private Stack<Integer> dataStack;
        private Stack<Integer> minStack;

        public MinStack2() {
            dataStack = new Stack<>();
            minStack = new Stack<>();
        }

        public void push(int x) {
            dataStack.push(x);
            if (minStack.isEmpty() || x <= minStack.peek()) {
                minStack.push(x);
            }
        }

        public void pop() {
            int top = dataStack.pop();
            if (top == minStack.peek()) {
                minStack.pop();
            }
        }

        public int top() {
            return dataStack.peek();
        }

        public int getMin() {
            return minStack.peek();
        }
    }


    class MinStack3 {
        private Stack<Node> stack;

        public MinStack3() {
            stack = new Stack<>();
        }

        public void push(int x) {
            Node node = new Node();
            node.val = x;
            int min = x;
            if (!stack.isEmpty() && stack.peek().min < x) {
                min = stack.peek().min;
            }
            node.min = min;
            stack.push(node);
        }

        public void pop() {
            stack.pop();
        }

        public int top() {
            return stack.peek().val;
        }

        public int getMin() {
            return stack.peek().min;
        }
    }

    class MinStack4 {
        private Node dummyHead;

        public MinStack4() {
            dummyHead = new Node();
        }

        public void push(int x) {
            int min = x;
            Node head = dummyHead.next;
            if (head != null && head.min < x) {
                min = head.min;
            }
            Node node = new Node(x, min);
            node.next = dummyHead.next;
            dummyHead.next = node;
        }

        public void pop() {
            Node head = dummyHead.next;
            if (head != null) {
                dummyHead.next = head.next;
                head.next = null;
            }
        }

        public int top() {
            return dummyHead.next.val;
        }

        public int getMin() {
            return dummyHead.next.min;
        }
    }

    class Node {
        int val;
        int min;
        Node next;

        public Node() {

        }

        public Node(int val, int min) {
            this.val = val;
            this.min = min;
        }
    }
}

