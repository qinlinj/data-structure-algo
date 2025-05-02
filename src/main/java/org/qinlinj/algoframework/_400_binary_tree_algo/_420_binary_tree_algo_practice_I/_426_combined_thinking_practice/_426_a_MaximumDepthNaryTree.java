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


}