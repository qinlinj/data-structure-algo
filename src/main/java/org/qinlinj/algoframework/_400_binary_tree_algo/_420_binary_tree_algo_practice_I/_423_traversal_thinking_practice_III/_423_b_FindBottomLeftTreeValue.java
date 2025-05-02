package org.qinlinj.algoframework._400_binary_tree_algo._420_binary_tree_algo_practice_I._423_traversal_thinking_practice_III;

import java.util.*;

/**
 * Problem 513: Find Bottom Left Tree Value
 * <p>
 * Description:
 * Given a binary tree, find the leftmost value in the last row of the tree.
 * <p>
 * Key Concepts:
 * - Uses the "traversal" thinking pattern for binary trees
 * - Tracks the current depth during traversal
 * - Maintains a record of the maximum depth seen
 * - The leftmost node at the maximum depth is the first node we encounter at that depth
 * - The recursive traversal naturally processes left children before right children
 * <p>
 * Time Complexity: O(N), where N is the number of nodes in the tree
 * Space Complexity: O(H), where H is the height of the tree (for recursion stack)
 */
public class _423_b_FindBottomLeftTreeValue {
    // Maximum depth seen so far
    private int maxDepth = 0;
    // Current depth in traversal
    private int currentDepth = 0;
    // Node with the bottom left value
    private TreeNode result = null;

    public int findBottomLeftValue(TreeNode root) {
        // Start traversal from root
        traverse(root);
        return result.val;
    }

    private void traverse(TreeNode root) {
        if (root == null) {
            return;
        }

        // Preorder traversal position - increment depth
        currentDepth++;

        // Check if we've reached a new maximum depth
        if (currentDepth > maxDepth) {
            // This is the first node we encounter at this depth
            // So it's the leftmost node at this depth
            maxDepth = currentDepth;
            result = root;
        }

        // Visit left child first, then right child
        // This ensures we encounter the leftmost node first at each level
        traverse(root.left);
        traverse(root.right);

        // Postorder traversal position - decrement depth when backtracking
        currentDepth--;
    }

    /**
     * Alternative solution using BFS (level-order traversal)
     */
    public int findBottomLeftValue_BFS(TreeNode root) {
        if (root == null) {
            return 0;
        }

        // Use a queue for level-order traversal
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        TreeNode leftmost = root;

        while (!queue.isEmpty()) {
            int levelSize = queue.size();

            // Process each level
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();

                // The first node in each level is the leftmost
                if (i == 0) {
                    leftmost = node;
                }

                // Add children to the queue
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }

        // The leftmost node of the last level
        return leftmost.val;
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
}
