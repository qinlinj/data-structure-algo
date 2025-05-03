package org.qinlinj.algoframework._400_binary_tree_algo._430_binary_tree_algo_practice_II._436_binary_search_tree_classic_practice_I;

/**
 * Problem 99: Recover Binary Search Tree (Medium)
 * <p>
 * Problem Description:
 * You are given the root of a binary search tree (BST), where exactly two nodes were swapped by mistake.
 * Recover the tree without changing its structure.
 * <p>
 * Key Concepts:
 * - BST Inorder Traversal Property: In a valid BST, inorder traversal produces a sorted sequence
 * - Identifying Swapped Nodes: When two nodes are swapped, the sorted property is violated at two positions
 * - Traversal with State Tracking: Use prev node reference to detect violations in order
 * - Constant Space Solution: Use Morris Traversal for O(1) space complexity (not implemented here)
 * <p>
 * Time Complexity: O(n) where n is the number of nodes
 * Space Complexity: O(h) where h is the height of the tree (recursion stack)
 */

class _436_a_RecoverBST {
    // Nodes that need to be swapped
    private TreeNode first = null;  // First node that violates BST property
    private TreeNode second = null; // Second node that violates BST property
    // Reference to the previous node in inorder traversal
    private TreeNode prev = null;

    // Main method for testing
    public static void main(String[] args) {
        _436_a_RecoverBST solution = new _436_a_RecoverBST();

        // Example 1: [1,3,null,null,2]
        TreeNode root1 = solution.new TreeNode(1);
        root1.left = solution.new TreeNode(3);
        root1.left.right = solution.new TreeNode(2);

        System.out.println("Example 1 - Before recovery:");
        solution.printInorder(root1);
        System.out.println();

        solution.recoverTree(root1);

        System.out.println("After recovery:");
        solution.printInorder(root1);
        System.out.println();

        // Reset solution state for next example
        solution.first = null;
        solution.second = null;
        solution.prev = null;

        // Example 2: [3,1,4,null,null,2]
        TreeNode root2 = solution.new TreeNode(3);
        root2.left = solution.new TreeNode(1);
        root2.right = solution.new TreeNode(4);
        root2.right.left = solution.new TreeNode(2);

        System.out.println("\nExample 2 - Before recovery:");
        solution.printInorder(root2);
        System.out.println();

        solution.recoverTree(root2);

        System.out.println("After recovery:");
        solution.printInorder(root2);
        System.out.println();
    }

    /**
     * Main function to recover the BST by finding and swapping the two incorrect nodes
     */
    public void recoverTree(TreeNode root) {
        // Initialize prev with a minimum value to handle the case when the first node is the root
        prev = new TreeNode(Integer.MIN_VALUE);

        // Find the two nodes through inorder traversal
        inorderTraversal(root);

        // Swap the values of the two nodes
        if (first != null && second != null) {
            int temp = first.val;
            first.val = second.val;
            second.val = temp;
        }
    }

    /**
     * Inorder traversal to find nodes that violate BST property
     */
    private void inorderTraversal(TreeNode root) {
        if (root == null) {
            return;
        }

        // Visit left subtree
        inorderTraversal(root.left);

        // Process current node (inorder position)
        // In a valid BST, prev.val should always be less than root.val
        if (prev != null && root.val < prev.val) {
            // We found a violation
            if (first == null) {
                // First violation: prev is out of place
                first = prev;
            }
            // Second violation (or potentially the other node in the pair)
            second = root;
        }

        // Update prev to current node for next comparison
        prev = root;

        // Visit right subtree
        inorderTraversal(root.right);
    }

    /**
     * Helper method to print inorder traversal of the tree
     */
    private void printInorder(TreeNode root) {
        if (root == null) return;

        printInorder(root.left);
        System.out.print(root.val + " ");
        printInorder(root.right);
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