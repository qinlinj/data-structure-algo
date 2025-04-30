package org.qinlinj.algoframework._400_binary_tree_algo._410_binary_tree_algo_guiding_principle._413_binary_tree_postorder;

/**
 * BINARY TREE CONSTRUCTION FROM PREORDER AND INORDER TRAVERSALS
 * =============================================================
 * <p>
 * This class implements the solution for LeetCode 105: Construct Binary Tree from Preorder and Inorder Traversal
 * <p>
 * Problem Description:
 * Given two integer arrays preorder and inorder where preorder is the preorder traversal of a binary tree
 * and inorder is the inorder traversal of the same tree, construct and return the binary tree.
 * <p>
 * Key insights:
 * <p>
 * 1. The first element in the preorder array is always the root of the tree or subtree.
 * <p>
 * 2. The position of this root element in the inorder array divides the array into left and right subtrees:
 * - Elements to the left of the root in inorder form the left subtree
 * - Elements to the right of the root in inorder form the right subtree
 * <p>
 * 3. Algorithm steps:
 * - Identify the root from the first element of preorder array
 * - Find the position of this root in the inorder array
 * - Calculate the size of the left subtree
 * - Recursively build the left and right subtrees using corresponding subarrays
 * <p>
 * 4. Optimization: Use a HashMap to store the index of each value in the inorder array for O(1) lookup
 * <p>
 * 5. Time Complexity: O(n) where n is the number of nodes in the tree
 * <p>
 * 6. Space Complexity: O(n) for the recursion stack and the HashMap
 */

import java.util.*;

public class _413_c_BuildTreeFromPreorderInorder {
    /**
     * Helper method to print the tree in preorder traversal (for verification)
     */
    private static void printPreorder(TreeNode root) {
        if (root == null) {
            return;
        }
        System.out.print(root.val + " ");
        printPreorder(root.left);
        printPreorder(root.right);
    }

    /**
     * Helper method to print the tree in inorder traversal (for verification)
     */
    private static void printInorder(TreeNode root) {
        if (root == null) {
            return;
        }
        printInorder(root.left);
        System.out.print(root.val + " ");
        printInorder(root.right);
    }

    /**
     * Visual explanation of the array splitting process
     */
    private static void explainArraySplitting() {
        System.out.println("Visual explanation of array splitting:");
        System.out.println();
        System.out.println("preorder = [3, 9, 20, 15, 7]");
        System.out.println("inorder  = [9, 3, 15, 20, 7]");
        System.out.println();
        System.out.println("1. Root value (preorder[0]) is 3");
        System.out.println("2. Root index in inorder is 1");
        System.out.println("3. Left subtree has 1 node (elements before 3 in inorder)");
        System.out.println("4. Right subtree has 3 nodes (elements after 3 in inorder)");
        System.out.println();
        System.out.println("Recursion for left subtree:");
        System.out.println("preorder[1, 1] = [9]");
        System.out.println("inorder[0, 0] = [9]");
        System.out.println();
        System.out.println("Recursion for right subtree:");
        System.out.println("preorder[2, 4] = [20, 15, 7]");
        System.out.println("inorder[2, 4] = [15, 20, 7]");
        System.out.println();
    }

    public static void main(String[] args) {
        _413_c_BuildTreeFromPreorderInorder solution = new _413_c_BuildTreeFromPreorderInorder();

        int[] preorder = {3, 9, 20, 15, 7};
        int[] inorder = {9, 3, 15, 20, 7};

        TreeNode root = solution.buildTree(preorder, inorder);

        System.out.println("Tree constructed from:");
        System.out.println("Preorder: [3, 9, 20, 15, 7]");
        System.out.println("Inorder: [9, 3, 15, 20, 7]");
        System.out.println();

        System.out.println("Verification by traversals:");
        System.out.print("Preorder traversal: ");
        printPreorder(root);
        System.out.println();

        System.out.print("Inorder traversal: ");
        printInorder(root);
        System.out.println();

        System.out.println("\n---------------------------------\n");
        explainArraySplitting();
    }

    /**
     * Main method for constructing a binary tree from preorder and inorder traversal arrays
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        // Create a map of value to index for inorder array for O(1) lookup
        HashMap<Integer, Integer> valToIndex = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            valToIndex.put(inorder[i], i);
        }

        // Call the recursive helper method
        return build(preorder, 0, preorder.length - 1,
                inorder, 0, inorder.length - 1,
                valToIndex);
    }

    /**
     * Helper method to recursively build the binary tree
     *
     * @param preorder The preorder traversal array
     * @param preStart Start index of current segment in preorder array
     * @param preEnd End index of current segment in preorder array
     * @param inorder The inorder traversal array
     * @param inStart Start index of current segment in inorder array
     * @param inEnd End index of current segment in inorder array
     * @param valToIndex Map of value to index in inorder array
     * @return The root of the constructed binary tree
     */
    private TreeNode build(int[] preorder, int preStart, int preEnd,
                           int[] inorder, int inStart, int inEnd,
                           HashMap<Integer, Integer> valToIndex) {
        // Base case: empty subtree
        if (preStart > preEnd) {
            return null;
        }

        // The first element in preorder is the root
        int rootVal = preorder[preStart];
        TreeNode root = new TreeNode(rootVal);

        // Find position of root in inorder array
        int rootIndex = valToIndex.get(rootVal);

        // Calculate size of left subtree
        int leftSize = rootIndex - inStart;

        // Recursively build left subtree
        // Preorder: elements after the root, up to leftSize elements
        // Inorder: elements to the left of the root
        root.left = build(preorder, preStart + 1, preStart + leftSize,
                inorder, inStart, rootIndex - 1,
                valToIndex);

        // Recursively build right subtree
        // Preorder: remaining elements after the left subtree
        // Inorder: elements to the right of the root
        root.right = build(preorder, preStart + leftSize + 1, preEnd,
                inorder, rootIndex + 1, inEnd,
                valToIndex);

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
    }
}
