package org.qinlinj.algoframework._400_binary_tree_algo._430_binary_tree_algo_practice_II._436_binary_search_tree_classic_practice_I;

/**
 * Problem 1008: Construct Binary Search Tree from Preorder Traversal (Medium)
 * <p>
 * Problem Description:
 * Given an array of integers preorder, which represents the preorder traversal of a BST
 * (i.e., binary search tree), construct the tree and return its root.
 * It is guaranteed that there is always possible to find a binary search tree
 * with the given requirements for the given test cases.
 * <p>
 * Key Concepts:
 * - BST Property: Left subtree values < root value < right subtree values
 * - Preorder Traversal: Root node comes first, followed by left subtree, then right subtree
 * - Subtree Range Identification: Use BST property to identify left and right subtree elements
 * - Recursive Construction: Build tree by recursively constructing left and right subtrees
 * <p>
 * Time Complexity: O(n) where n is the number of nodes
 * Space Complexity: O(h) where h is the height of the tree (recursion stack)
 */

class _436_g_BSTFromPreorder {
    /**
     * Alternative approach using a global index and value bounds
     */
    private int index = 0;

    // Main method for testing
    public static void main(String[] args) {
        _436_g_BSTFromPreorder solution = new _436_g_BSTFromPreorder();

        // Example 1: [8,5,1,7,10,12]
        int[] preorder1 = {8, 5, 1, 7, 10, 12};

        System.out.println("Example 1 - Preorder traversal: ");
        for (int val : preorder1) {
            System.out.print(val + " ");
        }
        System.out.println();

        TreeNode root1 = solution.bstFromPreorder(preorder1);

        System.out.println("Inorder traversal of constructed BST (should be sorted):");
        solution.printInorder(root1);
        System.out.println();

        solution.printTreeStructure(root1);

        // Example 2: [1,3]
        int[] preorder2 = {1, 3};

        System.out.println("\nExample 2 - Preorder traversal: ");
        for (int val : preorder2) {
            System.out.print(val + " ");
        }
        System.out.println();

        TreeNode root2 = solution.bstFromPreorder(preorder2);

        System.out.println("Inorder traversal of constructed BST (should be sorted):");
        solution.printInorder(root2);
        System.out.println();

        solution.printTreeStructure(root2);

        // Also test the alternative approach
        System.out.println("\nTesting alternative approach with Example 1:");
        TreeNode root1Alt = solution.bstFromPreorderAlternative(preorder1);

        System.out.println("Inorder traversal of constructed BST (should be sorted):");
        solution.printInorder(root1Alt);
        System.out.println();

        solution.printTreeStructure(root1Alt);
    }

    /**
     * Main function to construct a BST from a preorder traversal array
     *
     * @param preorder Array representing the preorder traversal of the BST
     * @return The root of the constructed BST
     */
    public TreeNode bstFromPreorder(int[] preorder) {
        if (preorder == null || preorder.length == 0) {
            return null;
        }

        return buildBST(preorder, 0, preorder.length - 1);
    }

    /**
     * Helper method to recursively build the BST
     *
     * @param preorder The preorder traversal array
     * @param start    Start index of the current subtree in the preorder array
     * @param end      End index of the current subtree in the preorder array
     * @return The root of the constructed subtree
     */
    private TreeNode buildBST(int[] preorder, int start, int end) {
        // Base case: invalid range
        if (start > end) {
            return null;
        }

        // Create the root node using the first element in the current range
        TreeNode root = new TreeNode(preorder[start]);

        // Find the index where elements larger than the root start
        // This will be the boundary between left and right subtrees
        int rightSubtreeStart = start + 1;
        while (rightSubtreeStart <= end && preorder[rightSubtreeStart] < root.val) {
            rightSubtreeStart++;
        }

        // Recursively build left and right subtrees
        // Left subtree: elements from (start+1) to (rightSubtreeStart-1)
        root.left = buildBST(preorder, start + 1, rightSubtreeStart - 1);

        // Right subtree: elements from rightSubtreeStart to end
        root.right = buildBST(preorder, rightSubtreeStart, end);

        return root;
    }

    public TreeNode bstFromPreorderAlternative(int[] preorder) {
        if (preorder == null || preorder.length == 0) {
            return null;
        }

        // Reset the index
        index = 0;

        return buildBSTWithBounds(preorder, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    /**
     * Helper method for the alternative approach
     * Constructs BST using upper and lower bounds to determine valid node values
     */
    private TreeNode buildBSTWithBounds(int[] preorder, int lowerBound, int upperBound) {
        // If all nodes are used or current value is out of bounds
        if (index >= preorder.length || preorder[index] < lowerBound || preorder[index] > upperBound) {
            return null;
        }

        // Create node with current value
        TreeNode root = new TreeNode(preorder[index++]);

        // Build left subtree with upper bound as root's value
        root.left = buildBSTWithBounds(preorder, lowerBound, root.val);

        // Build right subtree with lower bound as root's value
        root.right = buildBSTWithBounds(preorder, root.val, upperBound);

        return root;
    }

    /**
     * Helper method to print the inorder traversal of the tree
     * Inorder traversal of a BST should produce sorted values
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