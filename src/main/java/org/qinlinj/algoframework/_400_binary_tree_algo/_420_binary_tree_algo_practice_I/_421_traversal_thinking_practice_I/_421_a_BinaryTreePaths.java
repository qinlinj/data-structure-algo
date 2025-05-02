package org.qinlinj.algoframework._400_binary_tree_algo._420_binary_tree_algo_practice_I._421_traversal_thinking_practice_I;

import java.util.*;

/**
 * Problem 257: Binary Tree Paths
 * <p>
 * Description:
 * Given the root of a binary tree, return all paths from the root to leaf nodes in any order.
 * A leaf node is defined as a node with no children.
 * <p>
 * Key Concepts:
 * - This problem demonstrates the "traversal" thinking pattern for binary trees
 * - We need to track the path during traversal and record it when reaching leaf nodes
 * - Uses preorder traversal (process current node before children)
 * - The solution maintains a path list that is updated during traversal
 * - When a leaf node is reached, the current path is added to the result
 * <p>
 * Time Complexity: O(N), where N is the number of nodes in the tree
 * Space Complexity: O(H), where H is the height of the tree (for recursion stack)
 * O(N) for storing the result
 */
public class _421_a_BinaryTreePaths {
    // List to store all paths from root to leaf nodes
    private LinkedList<String> res = new LinkedList<>();
    // List to track current path during traversal
    private LinkedList<String> path = new LinkedList<>();

    public List<String> binaryTreePaths(TreeNode root) {
        // Start traversal from root
        traverse(root);
        return res;
    }

    private void traverse(TreeNode root) {
        if (root == null) {
            return;
        }

        // Check if current node is a leaf node
        if (root.left == null && root.right == null) {
            // Add current node value to path
            path.addLast(root.val + "");
            // Join path elements with "->" and add to result
            res.addLast(String.join("->", path));
            // Remove current node value from path before returning
            path.removeLast();
            return;
        }

        // Preorder traversal position - process current node
        path.addLast(root.val + "");

        // Recursively traverse left and right subtrees
        traverse(root.left);
        traverse(root.right);

        // Postorder traversal position - remove current node from path
        path.removeLast();
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
