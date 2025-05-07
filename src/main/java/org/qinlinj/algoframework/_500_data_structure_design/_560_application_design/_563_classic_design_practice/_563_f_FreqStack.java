package org.qinlinj.algoframework._500_data_structure_design._560_application_design._563_classic_design_practice; /**
 * LeetCode Problem 895: Maximum Frequency Stack
 * <p>
 * This problem involves designing a stack-like data structure that pops the most frequent element.
 * <p>
 * Problem Description:
 * - Implement a stack that supports push and pop operations
 * - The pop operation removes the most frequent element in the stack
 * - If multiple elements have the same frequency, remove the one closest to the top (most recently added)
 * <p>
 * Key Insights:
 * 1. We need to track the frequency of each element
 * 2. We need to know which element is the most frequent
 * 3. When multiple elements have the same frequency, we need to know their insertion order
 * <p>
 * Solution Approach:
 * - Use a HashMap to track the frequency of each value
 * - Use a HashMap to track values with the same frequency (using a stack for each frequency)
 * - Keep track of the maximum frequency to quickly find the most frequent element
 * <p>
 * Time Complexity:
 * - push(): O(1)
 * - pop(): O(1)
 * <p>
 * Space Complexity:
 * - O(n) where n is the number of elements in the stack
 */

import java.util.*;

public class _563_f_FreqStack {

    public static void main(String[] args) {
        // Example usage from the problem description
        FreqStack freqStack = new FreqStack();

        System.out.println("Operations:");
        System.out.println("push(5)");
        freqStack.push(5);
        System.out.println("Current state: " + freqStack);

        System.out.println("push(7)");
        freqStack.push(7);
        System.out.println("Current state: " + freqStack);

        System.out.println("push(5)");
        freqStack.push(5);
        System.out.println("Current state: " + freqStack);

        System.out.println("push(7)");
        freqStack.push(7);
        System.out.println("Current state: " + freqStack);

        System.out.println("push(4)");
        freqStack.push(4);
        System.out.println("Current state: " + freqStack);

        System.out.println("push(5)");
        freqStack.push(5);
        System.out.println("Current state: " + freqStack);

        System.out.println("pop() -> " + freqStack.pop());
        System.out.println("Current state: " + freqStack);

        System.out.println("pop() -> " + freqStack.pop());
        System.out.println("Current state: " + freqStack);

        System.out.println("pop() -> " + freqStack.pop());
        System.out.println("Current state: " + freqStack);

        System.out.println("pop() -> " + freqStack.pop());
        System.out.println("Current state: " + freqStack);
    }

    static class FreqStack {
        // Maximum frequency seen so far
        private int maxFreq;

        // Map from value to its frequency (VF table)
        private Map<Integer, Integer> valToFreq;

        // Map from frequency to stack of values with that frequency (FV table)
        private Map<Integer, Stack<Integer>> freqToVals;

        public FreqStack() {
            maxFreq = 0;
            valToFreq = new HashMap<>();
            freqToVals = new HashMap<>();
        }

        /**
         * Pushes a value onto the stack and updates frequency information
         */
        public void push(int val) {
            // Update value-to-frequency map: increment frequency of val
            int freq = valToFreq.getOrDefault(val, 0) + 1;
            valToFreq.put(val, freq);

            // Update frequency-to-values map: add val to the stack for its new frequency
            if (!freqToVals.containsKey(freq)) {
                freqToVals.put(freq, new Stack<>());
            }
            freqToVals.get(freq).push(val);

            // Update maximum frequency
            maxFreq = Math.max(maxFreq, freq);
        }

        /**
         * Pops the most frequent element from the stack
         */
        public int pop() {
            // Get the stack of values with maximum frequency
            Stack<Integer> stack = freqToVals.get(maxFreq);

            // Pop the most recent value with maximum frequency
            int val = stack.pop();

            // Update value-to-frequency map: decrement frequency of val
            int freq = valToFreq.get(val) - 1;
            valToFreq.put(val, freq);

            // If the stack for maxFreq is now empty, decrement maxFreq
            if (stack.isEmpty()) {
                maxFreq--;
            }

            return val;
        }

        /**
         * String representation of the FreqStack for debugging
         */
        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("maxFreq: ").append(maxFreq).append(", ");

            sb.append("valToFreq: {");
            for (Map.Entry<Integer, Integer> entry : valToFreq.entrySet()) {
                if (entry.getValue() > 0) { // Only show values with frequency > 0
                    sb.append(entry.getKey()).append("->").append(entry.getValue()).append(", ");
                }
            }
            if (sb.charAt(sb.length() - 2) == ',') {
                sb.setLength(sb.length() - 2);
            }
            sb.append("}, ");

            sb.append("freqToVals: {");
            for (int freq = 1; freq <= maxFreq; freq++) {
                Stack<Integer> stack = freqToVals.get(freq);
                if (stack != null && !stack.isEmpty()) {
                    sb.append(freq).append("->").append(stack).append(", ");
                }
            }
            if (sb.charAt(sb.length() - 2) == ',') {
                sb.setLength(sb.length() - 2);
            }
            sb.append("}");

            return sb.toString();
        }
    }
}