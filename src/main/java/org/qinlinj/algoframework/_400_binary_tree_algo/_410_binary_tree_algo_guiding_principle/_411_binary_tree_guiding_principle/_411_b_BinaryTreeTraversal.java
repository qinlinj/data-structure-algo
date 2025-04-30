package org.qinlinj.algoframework._400_binary_tree_algo._410_binary_tree_algo_guiding_principle._411_binary_tree_guiding_principle;

/**
 * BINARY TREE TRAVERSALS - UNDERSTANDING PRE-ORDER, IN-ORDER, AND POST-ORDER
 * =========================================================================
 * <p>
 * This class explores the three traversal positions in binary trees:
 * pre-order, in-order, and post-order. Understanding these positions is
 * crucial for mastering binary tree algorithms.
 * <p>
 * Key insights:
 * <p>
 * 1. Traversal Positions Are Not Just Lists:
 * - Pre-order, in-order, and post-order are not merely different orderings of nodes
 * - They represent specific moments during the traversal process where we can inject logic
 * <p>
 * 2. The Three Positions:
 * - Pre-order: Code executes when first entering a node (before exploring children)
 * - In-order: Code executes after traversing the left subtree, before traversing the right subtree
 * - Post-order: Code executes when about to exit a node (after exploring all children)
 * <p>
 * 3. Unique Properties:
 * - Each node has a unique pre-order, in-order, and post-order position
 * - Multi-way trees don't have in-order positions because there's no single moment between subtrees
 * <p>
 * 4. Practical Application:
 * - Different positions give you different capabilities for processing node information
 * - The position you choose depends on what information you need to access
 */

public class _411_b_BinaryTreeTraversal {
    public static void main(String[] args) {
        _411_b_BinaryTreeTraversal solution = new _411_b_BinaryTreeTraversal();

        // Create a simple binary tree
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        System.out.println("Traversal with visualization:");
        solution.traverseWithVisualization(root, "");
    }

    /**
     * Generic binary tree traversal framework showing all three positions
     */
    public void traverse(TreeNode root) {
        if (root == null) {
            return;
        }

        // Pre-order position - when we first visit the node
        System.out.println("Pre-order: " + root.val);

        traverse(root.left);

        // In-order position - after left subtree, before right subtree
        System.out.println("In-order: " + root.val);

        traverse(root.right);

        // Post-order position - after we've visited both children
        System.out.println("Post-order: " + root.val);
    }

    /**
     * Example: Printing nodes in reverse order using post-order position
     */
    public void printReverse(TreeNode root) {
        if (root == null) {
            return;
        }

        printReverse(root.left);
        printReverse(root.right);

        // Post-order position
        System.out.println(root.val);
    }

    /**
     * Example: Printing which level each node is on (using pre-order position)
     */
    public void printLevels(TreeNode root, int level) {
        if (root == null) {
            return;
        }

        // Pre-order position
        System.out.println("Node " + root.val + " is at level " + level);

        printLevels(root.left, level + 1);
        printLevels(root.right, level + 1);
    }

    /**
     * Example: Visual representation of pre-order, in-order, and post-order positions
     * on a binary tree (method would be called on the root node)
     */
    public void traverseWithVisualization(TreeNode root, String prefix) {
        if (root == null) {
            return;
        }

        // Pre-order position
        System.out.println(prefix + "Pre-order at node: " + root.val);

        traverseWithVisualization(root.left, prefix + "  ");

        // In-order position
        System.out.println(prefix + "In-order at node: " + root.val);

        traverseWithVisualization(root.right, prefix + "  ");

        // Post-order position
        System.out.println(prefix + "Post-order at node: " + root.val);
    }

    // Definition for a binary tree node
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
