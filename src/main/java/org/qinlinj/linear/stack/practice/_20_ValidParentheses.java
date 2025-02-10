package org.qinlinj.linear.stack.practice;

import java.util.Stack;

public class _20_ValidParentheses {
    public boolean isValid(String s) {
        if (s == null) return true;
        if (s.length() % 2 == 1) return false;
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == ' ') continue;
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                char topElement = stack.pop();
                char matched = '#';
                if (c == ')')
                    matched = '(';
                else if (c == ']')
                    matched = '[';
                else
                    matched = '{';

                if (matched != topElement)
                    return false;
            }
        }
        return stack.isEmpty();
    }

    public boolean isValid1(String s) {
        StringBuilder sb = new StringBuilder(s);
        int count = sb.length() / 2;
        for (int i = 0; i < count; i++) {
            for (int j = 0; j < sb.length() - 1; j++) {
                char c1 = sb.charAt(j);
                char c2 = sb.charAt(j + 1);
                if (isMatched(c1, c2)) {
                    sb.delete(j, j + 2);
                    break;
                }
            }
        }
        return sb.length() == 0;
    }

    private boolean isMatched(char c1, char c2) {
        if (c1 == '(')
            return c2 == ')';
        else if (c1 == '[')
            return c2 == ']';
        else if (c1 == '{')
            return c2 == '}';
        else
            return false;
    }
}
