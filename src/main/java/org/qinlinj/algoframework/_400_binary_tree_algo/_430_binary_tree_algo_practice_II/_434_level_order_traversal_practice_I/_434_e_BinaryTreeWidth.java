package org.qinlinj.algoframework._400_binary_tree_algo._430_binary_tree_algo_practice_II._434_level_order_traversal_practice_I; /**
 * Problem 662: Maximum Width of Binary Tree (Medium)
 * <p>
 * Problem Description:
 * Given the root of a binary tree, return the maximum width of the tree.
 * The width of a tree is the maximum width among all levels, where the width of a level
 * is the length between the leftmost and rightmost non-null nodes (null nodes between
 * the ends are also counted).
 * <p>
 * Key Concepts:
 * - Binary Tree Indexing: Uses a numbering scheme where if parent has index i, left child
 * has index 2*i and right child has index 2*i+1 (similar to heap indexing)
 * - Level Order Traversal with Node Indexing: Combines BFS with the indexing scheme to track positions
 * - Width Calculation: Width at a level is (rightmost_index - leftmost_index + 1)
 * - Integer Overflow Handling: Careful handling of indices to prevent overflow
 * <p>
 * Time Complexity: O(n) where n is the number of nodes in the tree
 * Space Complexity: O(w) where w is the maximum width of any level
 */

import java.util.*;

class _434_e_BinaryTreeWidth {
    // Main method for testing
    public static void main(String[] args) {
        _434_e_BinaryTreeWidth solution = new _434_e_BinaryTreeWidth();

        // Create example tree: [1,3,2,5,3,null,9]
        TreeNode root = solution.new TreeNode(1);
        root.left = solution.new TreeNode(3);
        root.right = solution.new TreeNode(2);
        root.left.left = solution.new TreeNode(5);
        root.left.right = solution.new TreeNode(3);
        root.right.right = solution.new TreeNode(9);

        int result = solution.widthOfBinaryTree(root);

        System.out.println("Maximum width of the binary tree: " + result);
        // Expected output: 4

        // Test the DFS solution as well
        int resultDFS = solution.widthOfBinaryTreeDFS(root);
        System.out.println("Maximum width (DFS method): " + resultDFS);
        // Expected output: 4
    }

    public int widthOfBinaryTree(TreeNode root) {
        // Handle empty tree case
        if (root == null) {
            return 0;
        }

        // Track maximum width seen so far
        int maxWidth = 0;

        // Queue for level order traversal, storing nodes with their position indices
        Queue<Pair> queue = new LinkedList<>();
        queue.offer(new Pair(root, 1)); // Start indexing at 1

        // While loop controls traversal from top to bottom
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            int leftmostIndex = 0;
            int rightmostIndex = 0;

            // For loop controls traversal from left to right at current level
            for (int i = 0; i < levelSize; i++) {
                Pair current = queue.poll();
                TreeNode currentNode = current.node;
                int position = current.index;

                // Record the position of first and last nodes at this level
                if (i == 0) {
                    leftmostIndex = position;
                }
                if (i == levelSize - 1) {
                    rightmostIndex = position;
                }

                // Enqueue children with their calculated positions
                // For left child: 2 * position
                // For right child: 2 * position + 1
                if (currentNode.left != null) {
                    queue.offer(new Pair(currentNode.left, position * 2));
                }
                if (currentNode.right != null) {
                    queue.offer(new Pair(currentNode.right, position * 2 + 1));
                }
            }

            // Update maximum width seen so far
            int currentWidth = rightmostIndex - leftmostIndex + 1;
            maxWidth = Math.max(maxWidth, currentWidth);
        }

        return maxWidth;
    }

    // Alternative DFS recursive solution
    public int widthOfBinaryTreeDFS(TreeNode root) {
        if (root == null) {
            return 0;
        }

        // List to store the leftmost node position at each depth
        List<Integer> leftmostPositions = new ArrayList<>();

        // Start DFS traversal
        return dfs(root, 1, 0, leftmostPositions);
    }

    private int dfs(TreeNode node, int position, int depth, List<Integer> leftmost) {
        if (node == null) {
            return 0;
        }

        // If we're seeing this depth for the first time, record leftmost position
        if (depth == leftmost.size()) {
            leftmost.add(position);
        }

        // Calculate width at current level
        int currentWidth = position - leftmost.get(depth) + 1;

        // Recursively find maximum width in left and right subtrees
        int leftMaxWidth = dfs(node.left, position * 2, depth + 1, leftmost);
        int rightMaxWidth = dfs(node.right, position * 2 + 1, depth + 1, leftmost);

        // Return the maximum width found
        return Math.max(currentWidth, Math.max(leftMaxWidth, rightMaxWidth));
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

    // Pair class to store node with its position index
    class Pair {
        TreeNode node;
        int index;

        public Pair(TreeNode node, int index) {
            this.node = node;
            this.index = index;
        }
    }
}