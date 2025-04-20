package org.qinlinj.algoframework._100_core_framework._180_binary_tree_core._186_level_order_traversal;

import java.util.*;

/**
 * BINARY TREE LEVEL-ORDER TRAVERSAL
 * ================================
 * <p>
 * While most binary tree algorithms employ recursion (DFS), level-order traversal uses iteration
 * with a queue (BFS). This approach is vital for many graph algorithms and has unique advantages:
 * <p>
 * - Processes nodes level by level, from top to bottom
 * - Within each level, processes nodes from left to right
 * - Ideal for finding shortest paths in unweighted graphs
 * - Provides a breadth-first view of the tree structure
 * <p>
 * This class demonstrates three approaches to level-order traversal:
 * 1. Standard iterative BFS (most common and efficient)
 * 2. Recursive DFS that simulates level-order traversal using depth tracking
 * 3. Recursive BFS implementation that processes levels recursively
 * <p>
 * The fundamental BFS framework seen in level-order traversal extends to many graph algorithms,
 * particularly those requiring shortest path calculations.
 */
public class BinaryTreeLevelOrder {

    // =====================================================
    // APPROACH 1: STANDARD ITERATIVE LEVEL-ORDER TRAVERSAL
    // =====================================================

    /**
     * Standard iterative level-order traversal using a queue
     * - Time Complexity: O(n) where n is the number of nodes
     * - Space Complexity: O(w) where w is the max width of the tree
     */
    public List<List<Integer>> levelOrderIterative(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        // Process level by level
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            List<Integer> currentLevel = new ArrayList<>();

            // Process all nodes at current level
            for (int i = 0; i < levelSize; i++) {
                TreeNode current = queue.poll();
                currentLevel.add(current.val);

                // Add children to queue for next level processing
                if (current.left != null) {
                    queue.offer(current.left);
                }
                if (current.right != null) {
                    queue.offer(current.right);
                }
            }

            result.add(currentLevel);
        }

        return result;
    }

    // BFS framework for traversal without collecting results
    public void levelTraverse(TreeNode root) {
        if (root == null) return;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        // From top to bottom
        while (!queue.isEmpty()) {
            int levelSize = queue.size();

            // From left to right within each level
            for (int i = 0; i < levelSize; i++) {
                TreeNode current = queue.poll();

                // Process the current node
                System.out.print(current.val + " ");

                // Add children to queue
                if (current.left != null) {
                    queue.offer(current.left);
                }
                if (current.right != null) {
                    queue.offer(current.right);
                }
            }
            System.out.println(); // Line break after each level
        }
    }

    // Modified BFS for finding minimum depth
    public int minDepth(TreeNode root) {
        if (root == null) return 0;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int depth = 1; // Start with depth 1 for root

        while (!queue.isEmpty()) {
            int levelSize = queue.size();

            for (int i = 0; i < levelSize; i++) {
                TreeNode current = queue.poll();

                // Check if this is a leaf node
                if (current.left == null && current.right == null) {
                    return depth; // Found the first leaf node
                }

                // Add children to queue
                if (current.left != null) {
                    queue.offer(current.left);
                }
                if (current.right != null) {
                    queue.offer(current.right);
                }
            }

            depth++; // Increment depth after processing each level
        }

        return depth;
    }

    // Definition for a binary tree node
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }

        // Helper constructor for easier tree creation
        TreeNode(int x, TreeNode left, TreeNode right) {
            this.val = x;
            this.left = left;
            this.right = right;
        }
    }
}
