package org.qinlinj.javabasics;

import java.util.*;

public class StackUtil {
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        // add element at the top of the stack

        stack.push(1);
        stack.push(2);
        stack.push(5);
        System.out.println(stack);

        // check if the stack is empty
        System.out.println("is empty: " + stack.isEmpty());
        // get size
        System.out.println("size: " + stack.size());

        // remove the element at the top of the stack
        stack.pop();
        System.out.println(stack);

        // get the element at the top of the stack
        System.out.println(stack.peek());
    }
}
