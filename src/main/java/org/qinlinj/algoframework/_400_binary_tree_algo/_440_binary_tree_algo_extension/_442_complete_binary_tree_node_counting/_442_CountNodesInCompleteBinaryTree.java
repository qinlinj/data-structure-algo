package org.qinlinj.algoframework._400_binary_tree_algo._440_binary_tree_algo_extension._442_complete_binary_tree_node_counting;

import java.util.*;

/**
 * Counting Nodes in a Complete Binary Tree
 * ---------------------------------------------------------
 * This class demonstrates efficient node counting in a complete binary tree
 * (LeetCode #222: Count Complete Tree Nodes).
 * <p>
 * Key points:
 * 1. A complete binary tree has all levels fully filled except possibly the last level,
 * which is filled from left to right.
 * 2. We can exploit this property to count nodes more efficiently than O(n).
 * 3. Three approaches are compared:
 * - Naive counting for any binary tree: O(n)
 * - Fast counting for perfect binary trees: O(log n)
 * - Optimized counting for complete binary trees: O(log n * log n)
 * 4. The key insight: In a complete binary tree, at least one of its subtrees must be a perfect binary tree.
 * 5. By identifying perfect subtrees, we can count large portions of nodes without traversing each node.
 * <p>
 * Time Complexity: O(log n * log n) instead of O(n)
 */
public class _442_CountNodesInCompleteBinaryTree {

    /**
     * Helper method to create a complete binary tree for testing
     */
    public static TreeNode createCompleteBinaryTree(int nodes) {
        if (nodes <= 0) {
            return null;
        }

        TreeNode root = new TreeNode(1);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        int count = 1;
        while (count < nodes) {
            TreeNode current = queue.poll();

            // Add left child
            if (count < nodes) {
                current.left = new TreeNode(++count);
                queue.offer(current.left);
            }

            // Add right child
            if (count < nodes) {
                current.right = new TreeNode(++count);
                queue.offer(current.right);
            }
        }

        return root;
    }

    /**
     * Main method to demonstrate the algorithms
     */
    public static void main(String[] args) {
        _442_CountNodesInCompleteBinaryTree solution = new _442_CountNodesInCompleteBinaryTree();

        System.out.println("Testing with trees of different sizes:");

        for (int i = 1; i <= 15; i++) {
            TreeNode tree = createCompleteBinaryTree(i);

            int naiveCount = solution.countNodesNaive(tree);
            int optimizedCount = solution.countNodes(tree);

            System.out.println("Tree with " + i + " nodes:");
            System.out.println("  - Naive count: " + naiveCount);
            System.out.println("  - Optimized count: " + optimizedCount);
            System.out.println("  - Results match: " + (naiveCount == optimizedCount));
        }

        // Demonstrate time complexity difference for larger trees
        System.out.println("\nDemonstrating performance difference for a larger tree:");
        int largeSize = 1023; // 2^10 - 1 (a perfect binary tree)
        TreeNode largeTree = createCompleteBinaryTree(largeSize);

        long startTime = System.nanoTime();
        int naiveResult = solution.countNodesNaive(largeTree);
        long naiveTime = System.nanoTime() - startTime;

        startTime = System.nanoTime();
        int optimizedResult = solution.countNodes(largeTree);
        long optimizedTime = System.nanoTime() - startTime;

        System.out.println("Tree with " + largeSize + " nodes:");
        System.out.println("  - Naive approach time: " + naiveTime + " ns");
        System.out.println("  - Optimized approach time: " + optimizedTime + " ns");
        System.out.println("  - Speedup factor: " + (double) naiveTime / optimizedTime);

        System.out.println("\nComparing different types of trees:");

        // Create a perfect binary tree (all levels filled)
        TreeNode perfectTree = createCompleteBinaryTree(15); // 2^4 - 1
        System.out.println("Perfect binary tree (15 nodes):");
        System.out.println("  - Is perfect: " + (solution.countNodesPerfect(perfectTree) == solution.countNodesNaive(perfectTree)));

        // Create a complete but not perfect binary tree
        TreeNode completeTree = createCompleteBinaryTree(10);
        System.out.println("Complete but not perfect binary tree (10 nodes):");
        int perfectCount = solution.countNodesPerfect(completeTree);
        int actualCount = solution.countNodesNaive(completeTree);
        System.out.println("  - Perfect count would give: " + perfectCount);
        System.out.println("  - Actual count: " + actualCount);
        System.out.println("  - This confirms we need the special algorithm for complete trees");
    }

    /**
     * Approach 1: Naive counting for any binary tree
     * Time Complexity: O(n) where n is the number of nodes
     */
    public int countNodesNaive(TreeNode root) {
        if (root == null) {
            return 0;
        }

        // Count current node + all nodes in left and right subtrees
        return 1 + countNodesNaive(root.left) + countNodesNaive(root.right);
    }

    /**
     * Approach 2: Fast counting for perfect binary trees
     * Time Complexity: O(log n) where n is the number of nodes
     * <p>
     * Note: A perfect binary tree has all levels completely filled.
     * The number of nodes in a perfect binary tree of height h is 2^h - 1.
     */
    public int countNodesPerfect(TreeNode root) {
        int height = 0;

        // Calculate height by following leftmost path
        TreeNode current = root;
        while (current != null) {
            current = current.left;
            height++;
        }

        // For a perfect binary tree: nodes = 2^height - 1
        return (int) Math.pow(2, height) - 1;
    }

    /**
     * Approach 3: Optimized counting for complete binary trees
     * Time Complexity: O(log n * log n)
     * <p>
     * Key insight: A complete binary tree of height h has between 2^(h-1) and 2^h - 1 nodes.
     * At least one of its subtrees must be a perfect binary tree.
     */
    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }

        // Check if tree is a perfect binary tree by comparing left height and right height
        TreeNode leftNode = root;
        TreeNode rightNode = root;
        int leftHeight = 0;
        int rightHeight = 0;

        // Compute height following leftmost path
        while (leftNode != null) {
            leftNode = leftNode.left;
            leftHeight++;
        }

        // Compute height following rightmost path
        while (rightNode != null) {
            rightNode = rightNode.right;
            rightHeight++;
        }

        // If heights are the same, it's a perfect binary tree
        if (leftHeight == rightHeight) {
            return (int) Math.pow(2, leftHeight) - 1;
        }

        // Otherwise, recursively count nodes in left and right subtrees
        // Note: Only one of these recursive calls will go deep,
        // the other will terminate quickly by finding a perfect subtree
        return 1 + countNodes(root.left) + countNodes(root.right);
    }

    // Definition for a binary tree node
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}