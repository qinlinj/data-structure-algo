package org.qinlinj.algoframework._400_binary_tree_algo._420_binary_tree_algo_practice_I._423_traversal_thinking_practice_III;

import java.util.*;

/**
 * Problem 386: Lexicographical Numbers
 * <p>
 * Description:
 * Given an integer n, return all the numbers in the range [1, n] in lexicographical order.
 * Lexicographical order is the order words would appear in a dictionary, where numbers
 * are treated as strings.
 * <p>
 * Key Concepts:
 * - Uses DFS (Depth-First Search) thinking pattern
 * - Views the problem as traversing a multi-way (10-way) tree
 * - The tree has digits 1-9 as root nodes
 * - Each node can have children by appending 0-9 to the current number
 * - Preorder traversal of this tree gives lexicographical order
 * <p>
 * Time Complexity: O(n), where n is the input integer
 * Space Complexity: O(log n) for recursion stack (max depth is log10(n))
 */
public class _423_d_LexicalOrder {
    // Result list to store lexicographically ordered numbers
    private List<Integer> result = new ArrayList<>();

    public List<Integer> lexicalOrder(int n) {
        // Start DFS from root nodes 1-9
        for (int i = 1; i < 10; i++) {
            dfs(i, n);
        }
        return result;
    }

    /**
     * DFS traversal of the digit tree
     *
     * @param current The current number in the traversal
     * @param n       The upper limit for numbers
     */
    private void dfs(int current, int n) {
        // Base case: if current number exceeds n, stop traversal
        if (current > n) {
            return;
        }

        // Preorder position: add current number to result
        result.add(current);

        // Recursively explore children (append 0-9 to current number)
        for (int i = 0; i < 10; i++) {
            // Calculate the next number in the sequence
            // This is equivalent to appending digit i to current
            int next = current * 10 + i;
            dfs(next, n);
        }
    }

    /**
     * Alternative implementation with optimization
     * Instead of exploring all 10 children for each node, we can optimize by:
     * 1. Only exploring valid children (those that don't exceed n)
     * 2. Using a simpler loop for child generation
     */
    public List<Integer> lexicalOrderOptimized(int n) {
        List<Integer> result = new ArrayList<>();

        // Start DFS from root nodes 1-9
        for (int i = 1; i < 10; i++) {
            dfsOptimized(i, n, result);
        }

        return result;
    }

    private void dfsOptimized(int current, int n, List<Integer> result) {
        if (current > n) {
            return;
        }

        // Add current number to result
        result.add(current);

        // Generate children by appending 0-9
        // This is more efficient than generating each child separately
        // We can directly calculate the range of valid children
        int nextPrefix = current * 10;
        if (nextPrefix <= n) { // Only proceed if at least the smallest child is valid
            // Explore all valid children (0-9 appended to current)
            for (int i = 0; i < 10; i++) {
                int child = nextPrefix + i;
                if (child <= n) {
                    dfsOptimized(child, n, result);
                } else {
                    break; // Early termination when we exceed n
                }
            }
        }
    }
}
