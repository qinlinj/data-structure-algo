package org.qinlinj.algoframework._400_binary_tree_algo._410_binary_tree_algo_guiding_principle._419_binary_search_tree_postorder;

/**
 * Binary Search Tree - Postorder Traversal Introduction
 * <p>
 * This class introduces the power of postorder traversal in Binary Search Tree algorithms.
 * <p>
 * Key points:
 * 1. Postorder traversal's unique advantage:
 * - Code in the postorder position can access data from both function parameters (passed down)
 * and return values from child nodes (passed up)
 * - This bidirectional data flow makes postorder traversal ideal for problems related to subtrees
 * <p>
 * 2. When to use postorder traversal:
 * - When the current node's operation depends on results from its left and right subtrees
 * - When bottom-up calculation is more efficient than top-down
 * - When you need to avoid redundant calculations in recursive functions
 * <p>
 * 3. The postorder traversal framework:
 * - Process left subtree
 * - Process right subtree
 * - Process current node (using results from left and right subtrees)
 * <p>
 * This approach is especially powerful for problems where properties of a node depend on
 * properties of its subtrees.
 */

public class _419_a_PostorderTraversalIntro {

    /**
     * Example 3: Find the diameter of a binary tree using postorder traversal
     * Diameter is the longest path between any two nodes
     */
    private int diameter = 0;

    public static void main(String[] args) {
        _419_a_PostorderTraversalIntro postorder = new _419_a_PostorderTraversalIntro();

        // Create a sample binary tree
        //      1
        //     / \
        //    2   3
        //   / \
        //  4   5
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        System.out.println("Postorder traversal of the tree:");
        postorder.postorderTraversal(root);

        System.out.println("\nHeight of the tree: " + postorder.height(root));

        System.out.println("Is the tree balanced? " + postorder.isBalanced(root));

        System.out.println("Diameter of the tree: " + postorder.diameterOfBinaryTree(root));

        System.out.println("\nKey advantages of postorder traversal:");
        System.out.println("1. Access to both top-down parameters and bottom-up return values");
        System.out.println("2. Efficient for problems where node properties depend on subtree properties");
        System.out.println("3. Reduces redundant calculations in recursive algorithms");
        System.out.println("4. Enables more elegant and efficient solutions for certain tree problems");
    }

    /**
     * Basic postorder traversal framework
     */
    public void postorderTraversal(TreeNode root) {
        if (root == null) return;

        // 1. Process left subtree
        postorderTraversal(root.left);

        // 2. Process right subtree
        postorderTraversal(root.right);

        // 3. Process current node (postorder position)
        System.out.println("Processing node: " + root.val);
        // Here we can use results from left and right subtrees
    }

    /**
     * Example 1: Calculate height of binary tree using postorder traversal
     * This demonstrates how postorder allows us to calculate properties that depend on subtrees
     */
    public int height(TreeNode root) {
        if (root == null) return 0;

        // Calculate height of left and right subtrees
        int leftHeight = height(root.left);
        int rightHeight = height(root.right);

        // Postorder position: use results from subtrees to determine current node's result
        return Math.max(leftHeight, rightHeight) + 1;
    }

    /**
     * Example 2: Check if a binary tree is balanced using postorder traversal
     * Shows how to use return values to avoid redundant calculations
     */
    public boolean isBalanced(TreeNode root) {
        return checkHeight(root) != -1;
    }

    // Helper function that returns the height if balanced, -1 if unbalanced
    private int checkHeight(TreeNode root) {
        if (root == null) return 0;

        // Calculate height of left and right subtrees
        int leftHeight = checkHeight(root.left);
        int rightHeight = checkHeight(root.right);

        // If either subtree is unbalanced, propagate the result up
        if (leftHeight == -1 || rightHeight == -1) {
            return -1;
        }

        // Check if current node maintains the balanced property
        if (Math.abs(leftHeight - rightHeight) > 1) {
            return -1;
        }

        // Return height of current tree
        return Math.max(leftHeight, rightHeight) + 1;
    }

    public int diameterOfBinaryTree(TreeNode root) {
        maxDepth(root);
        return diameter;
    }

    private int maxDepth(TreeNode root) {
        if (root == null) return 0;

        // Calculate depths of left and right subtrees
        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);

        // Postorder position: update diameter using subtree depths
        // The path through the current node has length leftDepth + rightDepth
        diameter = Math.max(diameter, leftDepth + rightDepth);

        // Return the max depth of this subtree
        return Math.max(leftDepth, rightDepth) + 1;
    }

    // Definition for a binary tree node
    public static class TreeNode {
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