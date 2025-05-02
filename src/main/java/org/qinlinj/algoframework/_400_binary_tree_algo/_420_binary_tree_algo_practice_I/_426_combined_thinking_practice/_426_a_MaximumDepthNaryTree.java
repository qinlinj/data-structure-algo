package org.qinlinj.algoframework._400_binary_tree_algo._420_binary_tree_algo_practice_I._426_combined_thinking_practice;

import java.util.*;

/**
 * Problem 559: Maximum Depth of N-ary Tree
 * <p>
 * Description:
 * Given an n-ary tree, find its maximum depth.
 * The maximum depth is the number of nodes along the longest path from the root node
 * down to the farthest leaf node.
 * <p>
 * Key Concepts:
 * - Extends binary tree depth concepts to n-ary trees
 * - Can be solved using either "divide and conquer" or "traversal" approaches
 * - Demonstrates how tree algorithms can be adapted for more general tree structures
 * <p>
 * Time Complexity: O(n), where n is the number of nodes in the tree
 * Space Complexity: O(h), where h is the height of the tree
 */
public class _426_a_MaximumDepthNaryTree {
    /**
     * Solution 1: Divide and Conquer Approach
     * <p>
     * With this approach, we break down the problem:
     * - Maximum depth of an n-ary tree = 1 + maximum of the depths of all its subtrees
     *
     * @param root Root of the n-ary tree
     * @return Maximum depth of the tree
     */
    public int maxDepth(Node root) {
        // Base case: empty tree has depth 0
        if (root == null) {
            return 0;
        }

        // Find the maximum depth among all children
        int maxChildDepth = 0;
        for (Node child : root.children) {
            int childDepth = maxDepth(child);
            maxChildDepth = Math.max(maxChildDepth, childDepth);
        }

        // Return current depth (1) plus maximum depth of children
        return 1 + maxChildDepth;
    }

    /**
     * Solution 2: Traversal Approach
     * <p>
     * With this approach, we traverse the tree while tracking depth:
     * - Maintain a current depth as we traverse
     * - Update the maximum depth whenever we go deeper
     */
    public int maxDepthTraversal(Node root) {
        // Initialize result and current depth trackers
        int[] result = {0};  // Using an array to simulate pass-by-reference
        traverseWithDepth(root, 0, result);
        return result[0];
    }

    /**
     * Helper method for traversal approach
     *
     * @param node         Current node being visited
     * @param currentDepth Current depth in the traversal
     * @param maxDepth     Reference to the maximum depth found so far
     */
    private void traverseWithDepth(Node node, int currentDepth, int[] maxDepth) {
        if (node == null) {
            return;
        }

        // Increment depth when visiting this node
        currentDepth++;

        // Update maximum depth if needed
        maxDepth[0] = Math.max(maxDepth[0], currentDepth);

        // Recursively traverse all children
        for (Node child : node.children) {
            traverseWithDepth(child, currentDepth, maxDepth);
        }

        // Note: We don't decrement currentDepth here because each recursive call
        // gets its own copy of currentDepth, so backtracking happens automatically
    }

    /**
     * Alternative implementation of traversal approach
     * This version explicitly handles the backtracking using depth variable
     */
    public int maxDepthExplicitTraversal(Node root) {
        return new TraversalHelper().maxDepth(root);
    }

    // Definition for a Node in N-ary Tree
    class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    private class TraversalHelper {
        // Current depth in traversal
        private int depth = 0;
        // Maximum depth found
        private int maxDepth = 0;

        public int maxDepth(Node root) {
            if (root == null) {
                return 0;
            }

            traverse(root);
            return maxDepth;
        }

        private void traverse(Node node) {
            if (node == null) {
                return;
            }

            // Preorder: increment depth when entering node
            depth++;
            maxDepth = Math.max(maxDepth, depth);

            // Traverse all children
            for (Node child : node.children) {
                traverse(child);
            }

            // Postorder: decrement depth when leaving node
            depth--;
        }
    }
}