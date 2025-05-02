package org.qinlinj.algoframework._400_binary_tree_algo._420_binary_tree_algo_practice_I._425_problem_decomposition_thinking_practice_II;

import java.util.*;

/**
 * Problem 100: Same Tree
 * <p>
 * Description:
 * Given the roots of two binary trees p and q, write a function to check if they are the same or not.
 * Two binary trees are considered the same if they are structurally identical, and the nodes have the same value.
 * <p>
 * Key Concepts:
 * - Uses the "divide and conquer" approach for binary tree comparison
 * - Recursively compares the structure and values of two trees
 * - Handles base cases of null nodes effectively
 * - Follows a logical sequence of comparisons for efficiency
 * <p>
 * Time Complexity: O(min(m,n)), where m and n are the number of nodes in the two trees
 * Space Complexity: O(min(h1,h2)), where h1 and h2 are the heights of the two trees
 */
public class _425_a_SameTree {
    /**
     * Checks if two binary trees are identical in structure and values
     *
     * @param p Root of the first tree
     * @param q Root of the second tree
     * @return True if the trees are identical, false otherwise
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        // Case 1: Both nodes are null - they are identical (empty subtrees match)
        if (p == null && q == null) {
            return true;
        }

        // Case 2: One node is null but the other isn't - trees differ in structure
        if (p == null || q == null) {
            return false;
        }

        // Case 3: Both nodes exist but have different values - trees differ in values
        if (p.val != q.val) {
            return false;
        }

        // Case 4: Nodes match, so recursively check their subtrees
        // Both left and right subtrees must match for the trees to be identical
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    /**
     * Alternative iterative implementation using a stack
     */
    public boolean isSameTreeIterative(TreeNode p, TreeNode q) {
        // Stack to store pairs of nodes to compare
        Stack<Pair<TreeNode, TreeNode>> stack = new Stack<>();
        stack.push(new Pair<>(p, q));

        while (!stack.isEmpty()) {
            Pair<TreeNode, TreeNode> current = stack.pop();
            TreeNode node1 = current.getKey();
            TreeNode node2 = current.getValue();

            // Check current pair of nodes
            if (node1 == null && node2 == null) {
                continue;
            }
            if (node1 == null || node2 == null || node1.val != node2.val) {
                return false;
            }

            // Push child nodes for comparison
            stack.push(new Pair<>(node1.right, node2.right));
            stack.push(new Pair<>(node1.left, node2.left));
        }

        return true;
    }

    // Definition for a binary tree node
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    // Simple pair class for the iterative solution
    private class Pair<K, V> {
        private final K key;
        private final V value;

        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
    }
}