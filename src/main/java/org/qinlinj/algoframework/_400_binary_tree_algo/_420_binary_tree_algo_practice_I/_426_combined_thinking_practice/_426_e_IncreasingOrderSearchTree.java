package org.qinlinj.algoframework._400_binary_tree_algo._420_binary_tree_algo_practice_I._426_combined_thinking_practice;

/**
 * Problem 897: Increasing Order Search Tree
 * <p>
 * Description:
 * Given the root of a binary search tree, rearrange the tree in in-order so that the leftmost node
 * in the tree is now the root of the tree, and every node has no left child and only one right child.
 * <p>
 * Key Concepts:
 * - Tree transformation through in-order traversal
 * - Binary Search Tree (BST) properties
 * - Building a new tree structure with the same nodes
 * - Can be solved using both "divide and conquer" and "traversal" approaches
 * <p>
 * Time Complexity: O(n), where n is the number of nodes in the tree
 * Space Complexity: O(h), where h is the height of the tree
 */
public class _426_e_IncreasingOrderSearchTree {
    // Using a class field to track current node position
    private TreeNode current;

    /**
     * Solution 1: Divide and Conquer Approach
     * <p>
     * In this approach, we:
     * - Recursively flatten left and right subtrees
     * - Create a single right-leaning tree from these subtrees
     *
     * @param root Root of the binary search tree
     * @return Root of the increasing-order tree
     */
    public TreeNode increasingBST(TreeNode root) {
        if (root == null) {
            return null;
        }

        // First, flatten left and right subtrees
        TreeNode flattenedLeft = increasingBST(root.left);
        root.left = null;  // Disconnect the left child
        TreeNode flattenedRight = increasingBST(root.right);
        root.right = flattenedRight;  // Connect flattened right subtree

        // If there's no flattened left subtree, root is the head
        if (flattenedLeft == null) {
            return root;
        }

        // Otherwise, attach root to the end of flattened left subtree
        TreeNode current = flattenedLeft;
        while (current.right != null) {
            current = current.right;
        }
        current.right = root;

        // The head of the flattened tree is the head of the flattened left subtree
        return flattenedLeft;
    }

    /**
     * Solution 2: In-order Traversal Approach
     * <p>
     * In this approach, we:
     * - Perform an in-order traversal of the tree
     * - Build the new right-leaning tree as we traverse
     *
     * @param root Root of the binary search tree
     * @return Root of the increasing-order tree
     */
    public TreeNode increasingBSTTraversal(TreeNode root) {
        // Create a dummy node to serve as the parent of the new tree
        TreeNode dummy = new TreeNode(0);
        // Reference to the current node in the new tree
        TreeNode current = dummy;

        // Start in-order traversal
        inOrderTraversal(root, current);

        // Return the root of the new tree (first right child of dummy)
        return dummy.right;
    }

    /**
     * Helper method for in-order traversal
     * Due to Java's pass-by-value, we need to return the current pointer
     *
     * @param node    Current node being visited
     * @param current Current node in the new tree
     * @return Updated current node in the new tree
     */
    private TreeNode inOrderTraversal(TreeNode node, TreeNode current) {
        if (node == null) {
            return current;
        }

        // Process left subtree first (in-order)
        current = inOrderTraversal(node.left, current);

        // Process current node: add to new tree
        current.right = new TreeNode(node.val);
        current = current.right;

        // Process right subtree last (in-order)
        current = inOrderTraversal(node.right, current);

        return current;
    }

    /**
     * Alternative solution using a class field to track the current node
     */
    public TreeNode increasingBSTAlternative(TreeNode root) {
        TreeNode dummy = new TreeNode(0);
        current = dummy;
        inOrder(root);
        return dummy.right;
    }

    private void inOrder(TreeNode node) {
        if (node == null) {
            return;
        }

        // In-order traversal: left -> node -> right
        inOrder(node.left);

        // Process current node
        current.right = new TreeNode(node.val);
        current = current.right;

        inOrder(node.right);
    }

    /**
     * Alternative solution with in-place transformation
     * This minimizes memory usage by reusing existing nodes
     */
    public TreeNode increasingBSTInPlace(TreeNode root) {
        return increasingBSTInPlace(root, null);
    }

    private TreeNode increasingBSTInPlace(TreeNode root, TreeNode tail) {
        if (root == null) {
            return tail;
        }

        // Recursively process the left subtree and make it point to current node
        TreeNode result = increasingBSTInPlace(root.left, root);

        // Disconnect left subtree
        root.left = null;

        // Right child points to processed right subtree
        root.right = increasingBSTInPlace(root.right, tail);

        return result;
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