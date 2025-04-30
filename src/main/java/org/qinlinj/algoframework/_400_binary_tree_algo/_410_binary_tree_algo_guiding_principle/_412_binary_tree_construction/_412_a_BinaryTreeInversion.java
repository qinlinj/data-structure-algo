package org.qinlinj.algoframework._400_binary_tree_algo._410_binary_tree_algo_guiding_principle._412_binary_tree_construction;

/**
 * BINARY TREE INVERSION
 * ====================
 * <p>
 * This class demonstrates two different approaches to solve the binary tree inversion problem (LeetCode 226):
 * 1. Using the "traversal" thinking pattern
 * 2. Using the "problem decomposition" thinking pattern
 * <p>
 * Problem Description:
 * Given the root of a binary tree, invert the tree, and return its root.
 * Inverting a binary tree means swapping every left node with its right node.
 * <p>
 * Key Insights:
 * <p>
 * 1. Traversal Approach:
 * - Define a traverse function that visits each node
 * - At each node, swap its left and right children
 * - The swap operation can be performed at pre-order or post-order position
 * - This approach uses external state to perform the transformation
 * <p>
 * 2. Problem Decomposition Approach:
 * - Define the recursive function's purpose: "invert the tree rooted at this node and return its root"
 * - Use the function's return value to build the solution
 * - Recursively invert left and right subtrees first
 * - Then swap the inverted subtrees
 * - This approach leverages the function's return value
 * <p>
 * Both approaches are valid solutions to this problem, demonstrating the flexibility
 * of the binary tree problem-solving framework.
 */

public class _412_a_BinaryTreeInversion {
    /**
     * Utility method to print tree in pre-order (for demonstration)
     */
    public static void printPreOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        System.out.print(root.val + " ");
        printPreOrder(root.left);
        printPreOrder(root.right);
    }

    /**
     * Main method for testing
     */
    public static void main(String[] args) {
        _412_a_BinaryTreeInversion solution = new _412_a_BinaryTreeInversion();

        // Create a sample tree: [4,2,7,1,3,6,9]
        TreeNode root1 = new TreeNode(4);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(7);
        root1.left.left = new TreeNode(1);
        root1.left.right = new TreeNode(3);
        root1.right.left = new TreeNode(6);
        root1.right.right = new TreeNode(9);

        System.out.println("Original Tree (Pre-order):");
        printPreOrder(root1);

        // Invert using traversal approach
        solution.invertTreeTraversal(root1);

        System.out.println("\nInverted Tree (Pre-order) using traversal approach:");
        printPreOrder(root1);

        // Create another identical tree for the second approach
        TreeNode root2 = new TreeNode(4);
        root2.left = new TreeNode(2);
        root2.right = new TreeNode(7);
        root2.left.left = new TreeNode(1);
        root2.left.right = new TreeNode(3);
        root2.right.left = new TreeNode(6);
        root2.right.right = new TreeNode(9);

        // Invert using problem decomposition approach
        TreeNode inverted2 = solution.invertTreeDecomposition(root2);

        System.out.println("\nInverted Tree (Pre-order) using problem decomposition approach:");
        printPreOrder(inverted2);
    }

    /**
     * APPROACH 1: Using the "traversal" thinking pattern
     * Time Complexity: O(n) where n is the number of nodes
     * Space Complexity: O(h) where h is the height of the tree (recursion stack)
     */
    public TreeNode invertTreeTraversal(TreeNode root) {
        // Start the traversal
        traverse(root);
        return root;
    }

    // Traversal function that visits each node and swaps its children
    private void traverse(TreeNode root) {
        if (root == null) {
            return;
        }

        // Pre-order position: swap left and right children
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        // Continue traversal on both subtrees
        traverse(root.left);
        traverse(root.right);

        // Note: The swapping could also be done in post-order position
        // In that case, it would happen after both recursive calls
    }

    /**
     * APPROACH 2: Using the "problem decomposition" thinking pattern
     * Time Complexity: O(n) where n is the number of nodes
     * Space Complexity: O(h) where h is the height of the tree (recursion stack)
     * <p>
     * Function definition: Invert the tree rooted at this node and return its root
     */
    public TreeNode invertTreeDecomposition(TreeNode root) {
        // Base case
        if (root == null) {
            return null;
        }

        // Recursively invert left and right subtrees
        TreeNode invertedLeft = invertTreeDecomposition(root.left);
        TreeNode invertedRight = invertTreeDecomposition(root.right);

        // Swap the inverted subtrees
        root.left = invertedRight;
        root.right = invertedLeft;

        // Return the root of the inverted tree
        return root;
    }

    // Definition for a binary tree node
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }

        @Override
        public String toString() {
            return String.valueOf(val);
        }
    }
}
