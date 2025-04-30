package org.qinlinj.algoframework._400_binary_tree_algo._410_binary_tree_algo_guiding_principle._413_binary_tree_postorder;

/**
 * BINARY TREE CONSTRUCTION FROM INORDER AND POSTORDER TRAVERSALS
 * =============================================================
 * <p>
 * This class implements the solution for LeetCode 106: Construct Binary Tree from Inorder and Postorder Traversal
 * <p>
 * Problem Description:
 * Given two integer arrays inorder and postorder where inorder is the inorder traversal of a binary tree
 * and postorder is the postorder traversal of the same tree, construct and return the binary tree.
 * <p>
 * Key insights:
 * <p>
 * 1. The last element in the postorder array is always the root of the tree or subtree.
 * This is the key difference from the preorder + inorder problem, where the first element of preorder is the root.
 * <p>
 * 2. The position of this root element in the inorder array divides the array into left and right subtrees:
 * - Elements to the left of the root in inorder form the left subtree
 * - Elements to the right of the root in inorder form the right subtree
 * <p>
 * 3. Algorithm steps:
 * - Identify the root from the last element of postorder array
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

public class _413_d_BuildTreeFromInorderPostorder {
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
     * Helper method to print the tree in postorder traversal (for verification)
     */
    private static void printPostorder(TreeNode root) {
        if (root == null) {
            return;
        }
        printPostorder(root.left);
        printPostorder(root.right);
        System.out.print(root.val + " ");
    }

    /**
     * Visual explanation of the array splitting process
     */
    private static void explainArraySplitting() {
        System.out.println("Visual explanation of array splitting:");
        System.out.println();
        System.out.println("inorder   = [9, 3, 15, 20, 7]");
        System.out.println("postorder = [9, 15, 7, 20, 3]");
        System.out.println();
        System.out.println("1. Root value (postorder[end]) is 3");
        System.out.println("2. Root index in inorder is 1");
        System.out.println("3. Left subtree has 1 node (elements before 3 in inorder)");
        System.out.println("4. Right subtree has 3 nodes (elements after 3 in inorder)");
        System.out.println();
        System.out.println("Recursion for left subtree:");
        System.out.println("inorder[0, 0] = [9]");
        System.out.println("postorder[0, 0] = [9]");
        System.out.println();
        System.out.println("Recursion for right subtree:");
        System.out.println("inorder[2, 4] = [15, 20, 7]");
        System.out.println("postorder[1, 3] = [15, 7, 20]");
        System.out.println();
    }

    public static void main(String[] args) {
        _413_d_BuildTreeFromInorderPostorder solution = new _413_d_BuildTreeFromInorderPostorder();

        int[] inorder = {9, 3, 15, 20, 7};
        int[] postorder = {9, 15, 7, 20, 3};

        TreeNode root = solution.buildTree(inorder, postorder);

        System.out.println("Tree constructed from:");
        System.out.println("Inorder: [9, 3, 15, 20, 7]");
        System.out.println("Postorder: [9, 15, 7, 20, 3]");
        System.out.println();

        System.out.println("Verification by traversals:");
        System.out.print("Inorder traversal: ");
        printInorder(root);
        System.out.println();

        System.out.print("Postorder traversal: ");
        printPostorder(root);
        System.out.println();

        System.out.println("\n---------------------------------\n");
        explainArraySplitting();
    }

    /**
     * Main method for constructing a binary tree from inorder and postorder traversal arrays
     */
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        // Create a map of value to index for inorder array for O(1) lookup
        HashMap<Integer, Integer> valToIndex = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            valToIndex.put(inorder[i], i);
        }

        // Call the recursive helper method
        return build(inorder, 0, inorder.length - 1,
                postorder, 0, postorder.length - 1,
                valToIndex);
    }

    /**
     * Helper method to recursively build the binary tree
     *
     * @param inorder The inorder traversal array
     * @param inStart Start index of current segment in inorder array
     * @param inEnd End index of current segment in inorder array
     * @param postorder The postorder traversal array
     * @param postStart Start index of current segment in postorder array
     * @param postEnd End index of current segment in postorder array
     * @param valToIndex Map of value to index in inorder array
     * @return The root of the constructed binary tree
     */
    private TreeNode build(int[] inorder, int inStart, int inEnd,
                           int[] postorder, int postStart, int postEnd,
                           HashMap<Integer, Integer> valToIndex) {
        // Base case: empty subtree
        if (inStart > inEnd) {
            return null;
        }

        // The last element in postorder is the root
        int rootVal = postorder[postEnd];
        TreeNode root = new TreeNode(rootVal);

        // Find position of root in inorder array
        int rootIndex = valToIndex.get(rootVal);

        // Calculate size of left subtree
        int leftSize = rootIndex - inStart;

        // Recursively build left subtree
        // Inorder: elements to the left of the root
        // Postorder: elements from the beginning, up to leftSize elements
        root.left = build(inorder, inStart, rootIndex - 1,
                postorder, postStart, postStart + leftSize - 1,
                valToIndex);

        // Recursively build right subtree
        // Inorder: elements to the right of the root
        // Postorder: remaining elements before the root
        root.right = build(inorder, rootIndex + 1, inEnd,
                postorder, postStart + leftSize, postEnd - 1,
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
