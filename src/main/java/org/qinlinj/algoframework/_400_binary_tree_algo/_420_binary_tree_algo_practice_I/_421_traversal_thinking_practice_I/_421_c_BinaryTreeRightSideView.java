package org.qinlinj.algoframework._400_binary_tree_algo._420_binary_tree_algo_practice_I._421_traversal_thinking_practice_I;

import java.util.*;

/**
 * Problem 199: Binary Tree Right Side View
 * <p>
 * Description:
 * Given the root of a binary tree, imagine standing on the right side of it.
 * Return the values of the nodes you can see ordered from top to bottom.
 * <p>
 * Key Concepts:
 * - This problem can be solved using two different approaches:
 * 1. BFS (Breadth-First Search) - Level order traversal
 * 2. DFS (Depth-First Search) - Preorder traversal
 * <p>
 * - BFS Approach:
 * - Process the tree level by level
 * - For each level, the rightmost node is part of the right side view
 * - Can be optimized by traversing each level from right to left
 * <p>
 * - DFS Approach:
 * - Track the depth during traversal
 * - Visit the right subtree first, then the left
 * - The first node visited at each depth level is the rightmost visible node
 * <p>
 * Time Complexity: O(N), where N is the number of nodes in the tree
 * Space Complexity: O(H), where H is the height of the tree
 */
public class _421_c_BinaryTreeRightSideView {
    // DFS Solution - Preorder Traversal
    private List<Integer> resDFS = new ArrayList<>();
    // Track the current depth during traversal
    private int depth = 0;

    // BFS Solution - Level Order Traversal
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        if (root == null) {
            return res;
        }

        // Queue for BFS level order traversal
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        // Process the tree level by level
        while (!q.isEmpty()) {
            int levelSize = q.size();
            // Get the first node of the current level (rightmost node)
            TreeNode rightmost = q.peek();

            for (int i = 0; i < levelSize; i++) {
                TreeNode current = q.poll();

                // Process right child first, then left child (right-to-left order)
                if (current.right != null) {
                    q.offer(current.right);
                }
                if (current.left != null) {
                    q.offer(current.left);
                }
            }

            // Add the rightmost node of this level to the result
            res.add(rightmost.val);
        }

        return res;
    }

    public List<Integer> rightSideView_DFS(TreeNode root) {
        traverseDFS(root);
        return resDFS;
    }

    private void traverseDFS(TreeNode root) {
        if (root == null) {
            return;
        }

        // Preorder traversal position
        depth++;

        // If this is the first node visited at this depth
        if (resDFS.size() < depth) {
            // Add it to the result (it's the rightmost visible node at this depth)
            resDFS.add(root.val);
        }

        // Visit right subtree first, then left subtree
        // This ensures we always process rightmost nodes first at each level
        traverseDFS(root.right);
        traverseDFS(root.left);

        // Postorder traversal position - decrease depth when backtracking
        depth--;
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
