package org.qinlinj.algoframework._400_binary_tree_algo._420_binary_tree_algo_practice_I._422_traversal_thinking_practice_II;

/**
 * Problem 623: Add One Row to Tree
 * <p>
 * Description:
 * Given the root of a binary tree, a value v, and a depth d, add a row of nodes with value v
 * at the given depth d. The root node is at depth 1.
 * The adding rule is:
 * - If d = 1, create a new root with value v, and the original tree is its left subtree.
 * - For each non-null node at depth d-1, add two children with value v as its left and right subtrees.
 * - The original left subtree should become the left subtree of the new left child at depth d.
 * - The original right subtree should become the right subtree of the new right child at depth d.
 * <p>
 * Key Concepts:
 * - Uses the "traversal" thinking pattern for binary trees
 * - Tracks depth during traversal to identify where to insert new nodes
 * - Special handling for the case where depth = 1
 * - Careful restructuring of connections between nodes
 * <p>
 * Time Complexity: O(N), where N is the number of nodes in the tree
 * Space Complexity: O(H), where H is the height of the tree (for recursion stack)
 */
public class _422_b_AddOneRowToTree {
    // Target value and depth for the new row
    private int targetVal;
    private int targetDepth;
    // Current depth during traversal
    private int curDepth = 0;

    public TreeNode addOneRow(TreeNode root, int val, int depth) {
        targetVal = val;
        targetDepth = depth;

        // Special case: If depth is 1, create a new root
        if (targetDepth == 1) {
            TreeNode newRoot = new TreeNode(targetVal);
            newRoot.left = root;
            return newRoot;
        }

        // Start traversal from root
        traverse(root);
        return root;
    }

    private void traverse(TreeNode root) {
        if (root == null) {
            return;
        }

        // Preorder traversal position - increment depth
        curDepth++;

        // Check if we are at depth d-1, where we need to add new nodes
        if (curDepth == targetDepth - 1) {
            // Create new nodes with the target value
            TreeNode newLeft = new TreeNode(targetVal);
            TreeNode newRight = new TreeNode(targetVal);

            // Connect original subtrees to the new nodes
            newLeft.left = root.left;
            newRight.right = root.right;

            // Connect new nodes to the current node
            root.left = newLeft;
            root.right = newRight;
        } else {
            // Continue traversal
            traverse(root.left);
            traverse(root.right);
        }

        // Postorder traversal position - decrement depth
        curDepth--;
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
