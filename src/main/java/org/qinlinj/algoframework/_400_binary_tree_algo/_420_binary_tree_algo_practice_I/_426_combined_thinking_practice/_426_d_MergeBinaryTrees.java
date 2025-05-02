package org.qinlinj.algoframework._400_binary_tree_algo._420_binary_tree_algo_practice_I._426_combined_thinking_practice;

/**
 * Problem 617: Merge Binary Trees
 * <p>
 * Description:
 * You are given two binary trees root1 and root2. Imagine that when you put one of them
 * to cover the other, some nodes of the two trees are overlapped while the others are not.
 * You need to merge the two trees into a new binary tree. The merge rule is that if two
 * nodes overlap, then sum node values up as the new value of the merged node. Otherwise,
 * the NOT null node will be used as the node of the new tree.
 * <p>
 * Key Concepts:
 * - Tree transformation through recursion
 * - Can be solved using both "divide and conquer" and "traversal" approaches
 * - Demonstrates handling of two trees simultaneously
 * <p>
 * Time Complexity: O(min(n, m)), where n and m are the number of nodes in the two trees
 * Space Complexity: O(min(h1, h2)), where h1 and h2 are the heights of the trees
 */
public class _426_d_MergeBinaryTrees {
    /**
     * Solution 1: Divide and Conquer Approach
     * <p>
     * In this approach, we:
     * - Recursively merge the two trees
     * - Return one tree if the other is null
     * - Create a new merged tree if both are present
     *
     * @param root1 Root of the first binary tree
     * @param root2 Root of the second binary tree
     * @return Root of the merged binary tree
     */
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        // Base cases: if one tree is empty, return the other
        if (root1 == null) {
            return root2;
        }
        if (root2 == null) {
            return root1;
        }

        // Both trees have nodes at this position, so merge them
        // Add values from both trees
        root1.val += root2.val;

        // Recursively merge left and right subtrees
        root1.left = mergeTrees(root1.left, root2.left);
        root1.right = mergeTrees(root1.right, root2.right);

        // Return the merged tree (using root1 as the base)
        return root1;
    }

    /**
     * Solution 2: Traversal Approach
     * <p>
     * In this approach, we:
     * - Traverse one tree (root1) and modify it directly
     * - Add values from the second tree (root2)
     * - Attach subtrees from root2 when root1 doesn't have them
     *
     * @param root1 Root of the first binary tree (will be modified)
     * @param root2 Root of the second binary tree
     * @return Root of the merged binary tree
     */
    public TreeNode mergeTreesTraversal(TreeNode root1, TreeNode root2) {
        // If first tree is empty, return the second
        if (root1 == null) {
            return root2;
        }

        // Traverse and merge
        traverse(root1, root2);

        return root1;
    }

    /**
     * Helper method for traversal approach
     *
     * @param root1 Current node in first tree (being modified)
     * @param root2 Current node in second tree
     */
    private void traverse(TreeNode root1, TreeNode root2) {
        // If second tree node is null, nothing to merge
        if (root2 == null) {
            return;
        }

        // Add values from both trees
        root1.val += root2.val;

        // If root1 is missing a left child but root2 has one,
        // attach root2's left subtree to root1
        if (root1.left == null && root2.left != null) {
            root1.left = root2.left;
            // Mark as processed to avoid double-counting
            root2.left = null;
        }
        // Same for right subtree
        if (root1.right == null && root2.right != null) {
            root1.right = root2.right;
            // Mark as processed to avoid double-counting
            root2.right = null;
        }

        // Continue traversal to children
        traverse(root1.left, root2.left);
        traverse(root1.right, root2.right);
    }

    /**
     * Alternative solution that creates a completely new tree
     * instead of modifying one of the existing trees
     */
    public TreeNode mergeTreesNewTree(TreeNode root1, TreeNode root2) {
        // Base cases
        if (root1 == null && root2 == null) {
            return null;
        }
        if (root1 == null) {
            return new TreeNode(root2.val,
                    mergeTreesNewTree(null, root2.left),
                    mergeTreesNewTree(null, root2.right));
        }
        if (root2 == null) {
            return new TreeNode(root1.val,
                    mergeTreesNewTree(root1.left, null),
                    mergeTreesNewTree(root1.right, null));
        }

        // Both nodes exist, create a new node with combined value
        return new TreeNode(root1.val + root2.val,
                mergeTreesNewTree(root1.left, root2.left),
                mergeTreesNewTree(root1.right, root2.right));
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