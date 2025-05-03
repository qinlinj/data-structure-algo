package org.qinlinj.algoframework._400_binary_tree_algo._430_binary_tree_algo_practice_II._436_binary_search_tree_classic_practice_I;

/**
 * Problem 669: Trim a Binary Search Tree (Medium)
 * <p>
 * Problem Description:
 * Given the root of a binary search tree and the lowest and highest boundaries as low and high,
 * trim the tree so that all its elements lie in [low, high].
 * Trimming the tree should not change the relative structure of the elements that will remain in the tree
 * (i.e., any node's descendant should remain a descendant). It can be proven that there is a unique answer.
 * <p>
 * Key Concepts:
 * - BST Property Exploitation: Using the BST property to efficiently trim subtrees
 * - Recursive Tree Modification: Using post-order recursion to build the trimmed tree bottom-up
 * - Early Pruning: Skipping entire subtrees based on BST property (all values in left/right subtree too small/large)
 * - Structure Preservation: Maintaining the relative positions of remaining nodes
 * <p>
 * Time Complexity: O(n) where n is the number of nodes
 * Space Complexity: O(h) where h is the height of the tree (recursion stack)
 */

class _436_b_TrimBST {
    // Main method for testing
    public static void main(String[] args) {
        _436_b_TrimBST solution = new _436_b_TrimBST();

        // Example 1: [1,0,2], low = 1, high = 2
        TreeNode root1 = solution.new TreeNode(1);
        root1.left = solution.new TreeNode(0);
        root1.right = solution.new TreeNode(2);

        System.out.println("Example 1 - Before trimming:");
        solution.printInorder(root1);
        System.out.println();
        solution.printTreeStructure(root1);

        TreeNode trimmed1 = solution.trimBST(root1, 1, 2);

        System.out.println("\nAfter trimming (low=1, high=2):");
        solution.printInorder(trimmed1);
        System.out.println();
        solution.printTreeStructure(trimmed1);

        // Example 2: [3,0,4,null,2,null,null,1], low = 1, high = 3
        TreeNode root2 = solution.new TreeNode(3);
        root2.left = solution.new TreeNode(0);
        root2.right = solution.new TreeNode(4);
        root2.left.right = solution.new TreeNode(2);
        root2.left.right.left = solution.new TreeNode(1);

        System.out.println("\nExample 2 - Before trimming:");
        solution.printInorder(root2);
        System.out.println();
        solution.printTreeStructure(root2);

        TreeNode trimmed2 = solution.trimBST(root2, 1, 3);

        System.out.println("\nAfter trimming (low=1, high=3):");
        solution.printInorder(trimmed2);
        System.out.println();
        solution.printTreeStructure(trimmed2);
    }

    /**
     * Main function to trim the BST to contain only values in range [low, high]
     *
     * @param root The root of the binary search tree
     * @param low  The lower bound (inclusive)
     * @param high The upper bound (inclusive)
     * @return The root of the trimmed tree
     */
    public TreeNode trimBST(TreeNode root, int low, int high) {
        if (root == null) {
            return null;
        }

        // Case 1: Root value is less than low
        // The entire left subtree and root are too small, focus on right subtree
        if (root.val < low) {
            return trimBST(root.right, low, high);
        }

        // Case 2: Root value is greater than high
        // The entire right subtree and root are too large, focus on left subtree
        if (root.val > high) {
            return trimBST(root.left, low, high);
        }

        // Case 3: Root value is within range [low, high]
        // Keep the root, but trim its left and right subtrees
        root.left = trimBST(root.left, low, high);
        root.right = trimBST(root.right, low, high);

        return root;
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

    /**
     * Helper method to print the tree structure
     */
    private void printTree(TreeNode root, String prefix, boolean isLeft) {
        if (root == null) {
            System.out.println(prefix + (isLeft ? "├── " : "└── ") + "null");
            return;
        }

        System.out.println(prefix + (isLeft ? "├── " : "└── ") + root.val);

        // Print children with appropriate prefix
        printTree(root.left, prefix + (isLeft ? "│   " : "    "), true);
        printTree(root.right, prefix + (isLeft ? "│   " : "    "), false);
    }

    /**
     * Simplified tree printing method
     */
    private void printTreeStructure(TreeNode root) {
        System.out.println("Tree structure:");
        printTree(root, "", false);
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