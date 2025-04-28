package org.qinlinj.algoframework._300_classical_data_structure_algo._340_stack_queue_algo._342_stack_classic_practice;

/**
 * LeetCode 895: Maximum Frequency Stack
 * <p>
 * Problem Description:
 * Design a stack-like data structure that pushes elements to the stack and pops the most
 * frequent element from the stack.
 * <p>
 * Implement the FreqStack class:
 * - FreqStack() Initializes an empty frequency stack.
 * - void push(int val) Pushes an integer val onto the top of the stack.
 * - int pop() Removes and returns the most frequent element in the stack.
 * If there is a tie for the most frequent element, remove the element closest to the stack's top.
 * <p>
 * Solution Approach:
 * The solution requires tracking three key pieces of information:
 * <p>
 * 1. The maximum frequency (maxFreq) of any element in the stack
 * 2. A mapping from each value to its frequency (valToFreq)
 * 3. A mapping from each frequency to a stack of values with that frequency (freqToVals)
 * <p>
 * For push operations:
 * - Increment the frequency of the value in valToFreq
 * - Add the value to the appropriate stack in freqToVals
 * - Update maxFreq if needed
 * <p>
 * For pop operations:
 * - Get the stack of values with maxFreq frequency
 * - Pop the most recent value (top of that stack)
 * - Decrement the frequency of that value in valToFreq
 * - Update maxFreq if the stack becomes empty
 * <p>
 * The key insight is using a stack for each frequency level to maintain
 * insertion order, which allows us to retrieve the most recently added
 * element when there's a tie in frequency.
 * <p>
 * Time Complexity: O(1) for all operations
 * Space Complexity: O(n) where n is the number of push operations
 */

import java.util.*;

public class _342_h_FreqStack {
    // Track the maximum frequency of any element in the stack
    private int maxFreq;

    // Map each value to its frequency (Value-to-Frequency table)
    private HashMap<Integer, Integer> valToFreq;

    // Map each frequency to a stack of values with that frequency (Frequency-to-Values table)
    private HashMap<Integer, Stack<Integer>> freqToVals;

    public _342_h_FreqStack() {
        maxFreq = 0;
        valToFreq = new HashMap<>();
        freqToVals = new HashMap<>();
    }

    // Example usage
    public static void main(String[] args) {
        _342_h_FreqStack freqStack = new _342_h_FreqStack();

        // Test case from the problem description
        freqStack.push(5);  // Stack: [5]
        freqStack.push(7);  // Stack: [5,7]
        freqStack.push(5);  // Stack: [5,7,5]
        freqStack.push(7);  // Stack: [5,7,5,7]
        freqStack.push(4);  // Stack: [5,7,5,7,4]
        freqStack.push(5);  // Stack: [5,7,5,7,4,5]

        System.out.println(freqStack.pop());  // Returns 5 (highest frequency, 3 times)
        System.out.println(freqStack.pop());  // Returns 7 (tied highest frequency, 2 times each, but 7 is more recent)
        System.out.println(freqStack.pop());  // Returns 5 (highest frequency, 2 times)
        System.out.println(freqStack.pop());  // Returns 4 (all values now have frequency 1, 4 is most recent)
    }

    /**
     * Pushes an element onto the stack.
     * 1. Increment the frequency of the value
     * 2. Add the value to the appropriate frequency stack
     * 3. Update the maximum frequency if needed
     */
    public void push(int val) {
        // Update value's frequency
        int freq = valToFreq.getOrDefault(val, 0) + 1;
        valToFreq.put(val, freq);

        // Ensure there's a stack for this frequency
        freqToVals.putIfAbsent(freq, new Stack<>());

        // Add the value to the stack for this frequency
        freqToVals.get(freq).push(val);

        // Update maximum frequency
        maxFreq = Math.max(maxFreq, freq);
    }

    /**
     * Removes and returns the most frequent element.
     * If there's a tie, returns the most recently added element.
     * 1. Get the stack of values with maximum frequency
     * 2. Pop the most recent value
     * 3. Update the value's frequency
     * 4. Update maxFreq if that frequency stack is now empty
     */
    public int pop() {
        // Get the stack for maximum frequency
        Stack<Integer> stack = freqToVals.get(maxFreq);

        // Pop the most recent value with that frequency
        int val = stack.pop();

        // Decrement the value's frequency
        int freq = valToFreq.get(val) - 1;
        valToFreq.put(val, freq);

        // If no more elements with current maxFreq, decrement maxFreq
        if (stack.isEmpty()) {
            maxFreq--;
        }

        return val;
    }
}