package org.qinlinj.algoframework._400_binary_tree_algo._430_binary_tree_algo_practice_II._433_postorder_position_practice_III;

/**
 * Problem 1080: Insufficient Nodes in Root to Leaf Paths (Medium)
 * <p>
 * Problem Description:
 * Given the root of a binary tree and an integer limit, remove all "insufficient nodes" and return
 * the resulting binary tree's root. A node is considered "insufficient" if every possible root-to-leaf
 * path through this node has a sum less than the given limit.
 * <p>
 * Key Concepts:
 * - Combined pre-order and post-order traversal: This problem elegantly demonstrates both traversal positions:
 * 1. Pre-order position: Used to pass down accumulated path information (remaining limit) from parent to children
 * 2. Post-order position: Used to pass up deletion information from children to decide whether current node needs deletion
 * <p>
 * - Deletion strategy:
 * 1. For leaf nodes: Delete if the node's value is less than the remaining limit
 * 2. For non-leaf nodes: Delete if both children are deleted (which means all paths through this node are insufficient)
 * <p>
 * - Decision propagation: Deletion decisions propagate upward through the tree - if all paths through a node
 * are insufficient, the node itself becomes insufficient and should be deleted.
 * <p>
 * Time Complexity: O(n) where n is the number of nodes
 * Space Complexity: O(h) where h is the height of the tree (recursion stack)
 */

class _433_c_InsufficientNodes {
    // Main method for testing
    public static void main(String[] args) {
        _433_c_InsufficientNodes solution = new _433_c_InsufficientNodes();

        // Example 1: [1,2,3,4,-99,-99,7,8,9,-99,-99,12,13,-99,14], limit = 1
        TreeNode example1 = solution.createExample1Tree();

        System.out.print("Original tree: ");
        solution.printTree(example1);
        System.out.println();

        TreeNode result1 = solution.sufficientSubset(example1, 1);

        System.out.print("After removing insufficient nodes: ");
        solution.printTree(result1);
        System.out.println();

        // Example 2: [5,4,8,11,null,17,4,7,1,null,null,5,3], limit = 22
        TreeNode example2 = solution.createExample2Tree();

        System.out.print("Original tree: ");
        solution.printTree(example2);
        System.out.println();

        TreeNode result2 = solution.sufficientSubset(example2, 22);

        System.out.print("After removing insufficient nodes: ");
        solution.printTree(result2);
        System.out.println();
    }

    public TreeNode sufficientSubset(TreeNode root, int limit) {
        // Base case: empty tree
        if (root == null) {
            return null;
        }

        // Pre-order position:
        // For leaf nodes, we directly determine if they should be deleted
        if (root.left == null && root.right == null) {
            if (root.val < limit) {
                // Delete this leaf node if its value is less than the limit
                return null;
            }
            return root;  // Keep this leaf node
        }

        // Recursively process left and right subtrees
        // Pass down the remaining limit (limit - current node's value)
        TreeNode left = sufficientSubset(root.left, limit - root.val);
        TreeNode right = sufficientSubset(root.right, limit - root.val);

        // Post-order position:
        // Use children results to determine if current node should be deleted
        if (left == null && right == null) {
            // If both children are deleted, it means all paths through this node
            // have sums less than the limit, so this node is also insufficient
            return null;
        }

        // Update the tree structure
        root.left = left;
        root.right = right;

        return root;
    }

    // Helper method to print the tree for testing
    private void printTree(TreeNode root) {
        if (root == null) {
            System.out.print("null ");
            return;
        }

        System.out.print(root.val + " ");
        printTree(root.left);
        printTree(root.right);
    }

    // Helper methods to create example trees
    private TreeNode createExample1Tree() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(-99);
        root.right.left = new TreeNode(-99);
        root.right.right = new TreeNode(7);
        root.left.left.left = new TreeNode(8);
        root.left.left.right = new TreeNode(9);
        root.left.right.left = new TreeNode(-99);
        root.left.right.right = new TreeNode(-99);
        root.right.right.left = new TreeNode(12);
        root.right.right.right = new TreeNode(13);
        root.right.right.right.right = new TreeNode(14);
        return root;
    }

    private TreeNode createExample2Tree() {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(11);
        root.right.left = new TreeNode(17);
        root.right.right = new TreeNode(4);
        root.left.left.left = new TreeNode(7);
        root.left.left.right = new TreeNode(1);
        root.right.right.left = new TreeNode(5);
        root.right.right.right = new TreeNode(3);
        return root;
    }

    // TreeNode definition
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