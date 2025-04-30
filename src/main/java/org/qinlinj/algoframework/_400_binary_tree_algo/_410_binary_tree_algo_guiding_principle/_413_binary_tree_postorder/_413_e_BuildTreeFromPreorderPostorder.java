package org.qinlinj.algoframework._400_binary_tree_algo._410_binary_tree_algo_guiding_principle._413_binary_tree_postorder;

/**
 * BINARY TREE CONSTRUCTION FROM PREORDER AND POSTORDER TRAVERSALS
 * ==============================================================
 * <p>
 * This class implements the solution for LeetCode 889: Construct Binary Tree from Preorder and Postorder Traversal
 * <p>
 * Problem Description:
 * Given two integer arrays, preorder and postorder, where preorder is the preorder traversal of a binary tree
 * and postorder is the postorder traversal of the same tree, construct and return the binary tree.
 * If there exist multiple answers, you can return any of them.
 * <p>
 * Key insights:
 * <p>
 * 1. Unlike preorder+inorder or postorder+inorder combinations, the preorder+postorder combination
 * does not always uniquely identify a binary tree. Multiple valid trees may exist.
 * <p>
 * 2. Key observations:
 * - The first element in preorder is the root
 * - The last element in postorder is also the root
 * - The second element in preorder is the root of the left subtree (if there is a left subtree)
 * <p>
 * 3. Algorithm steps:
 * - Identify the root from the first element of preorder
 * - Use the second element of preorder (the root of left subtree) to find the division point in postorder
 * - Use this division to determine sizes of left and right subtrees
 * - Recursively build the left and right subtrees
 * <p>
 * 4. Special case: for a single node, preStart == preEnd, return a single node tree
 * <p>
 * 5. Time Complexity: O(n²) in worst case due to linear search in the postorder array
 * Can be optimized to O(n) using a HashMap
 * <p>
 * 6. Space Complexity: O(n) for the recursion stack
 */

import java.util.*;

public class _413_e_BuildTreeFromPreorderPostorder {
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
     * Visual explanation of why preorder+postorder may not uniquely identify a tree
     */
    private static void explainNonUniqueness() {
        System.out.println("Why preorder+postorder doesn't always uniquely identify a tree:");
        System.out.println();
        System.out.println("Consider these traversals:");
        System.out.println("preorder = [1, 2, 3], postorder = [3, 2, 1]");
        System.out.println();
        System.out.println("Two possible trees:");
        System.out.println("Tree 1:     1      Tree 2:     1");
        System.out.println("           /                    \\");
        System.out.println("          2                      2");
        System.out.println("         /                        \\");
        System.out.println("        3                          3");
        System.out.println();
        System.out.println("Both trees have the same preorder and postorder traversals!");
        System.out.println("This is because without inorder traversal, we can't determine");
        System.out.println("whether a node is a left child or right child in some cases.");
    }

    /**
     * Visual explanation of the array splitting process
     */
    private static void explainArraySplitting() {
        System.out.println("Visual explanation of array splitting:");
        System.out.println();
        System.out.println("preorder  = [1, 2, 4, 5, 3, 6, 7]");
        System.out.println("postorder = [4, 5, 2, 6, 7, 3, 1]");
        System.out.println();
        System.out.println("1. Root value (preorder[0]) is 1");
        System.out.println("2. Left subtree root (preorder[1]) is 2");
        System.out.println("3. Left subtree root's index in postorder is 2");
        System.out.println("4. Left subtree has 3 nodes ([2, 4, 5] in preorder)");
        System.out.println("5. Right subtree has 3 nodes ([3, 6, 7] in preorder)");
        System.out.println();
        System.out.println("Recursion for left subtree:");
        System.out.println("preorder[1, 3] = [2, 4, 5]");
        System.out.println("postorder[0, 2] = [4, 5, 2]");
        System.out.println();
        System.out.println("Recursion for right subtree:");
        System.out.println("preorder[4, 6] = [3, 6, 7]");
        System.out.println("postorder[3, 5] = [6, 7, 3]");
        System.out.println();
    }

    public static void main(String[] args) {
        _413_e_BuildTreeFromPreorderPostorder solution = new _413_e_BuildTreeFromPreorderPostorder();

        // Example 1
        int[] preorder1 = {1, 2, 4, 5, 3, 6, 7};
        int[] postorder1 = {4, 5, 2, 6, 7, 3, 1};

        TreeNode root1 = solution.constructFromPrePost(preorder1, postorder1);

        System.out.println("Tree 1 constructed from:");
        System.out.println("Preorder: [1, 2, 4, 5, 3, 6, 7]");
        System.out.println("Postorder: [4, 5, 2, 6, 7, 3, 1]");
        System.out.println();

        System.out.println("Verification by traversals:");
        System.out.print("Preorder traversal: ");
        printPreorder(root1);
        System.out.println();

        System.out.print("Postorder traversal: ");
        printPostorder(root1);
        System.out.println();

        // Example 2 showing non-uniqueness
        System.out.println("\n---------------------------------\n");
        int[] preorder2 = {1, 2, 3};
        int[] postorder2 = {3, 2, 1};

        TreeNode root2 = solution.constructFromPrePost(preorder2, postorder2);

        System.out.println("Tree 2 constructed from:");
        System.out.println("Preorder: [1, 2, 3]");
        System.out.println("Postorder: [3, 2, 1]");
        System.out.println();

        System.out.println("Verification by traversals:");
        System.out.print("Preorder traversal: ");
        printPreorder(root2);
        System.out.println();

        System.out.print("Postorder traversal: ");
        printPostorder(root2);
        System.out.println();

        System.out.println("\n---------------------------------\n");
        explainNonUniqueness();

        System.out.println("\n---------------------------------\n");
        explainArraySplitting();
    }

    /**
     * Main method for constructing a binary tree from preorder and postorder traversal arrays
     */
    public TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
        // Create a map of value to index for postorder array for O(1) lookup
        HashMap<Integer, Integer> valToIndex = new HashMap<>();
        for (int i = 0; i < postorder.length; i++) {
            valToIndex.put(postorder[i], i);
        }

        // Call the recursive helper method
        return build(preorder, 0, preorder.length - 1,
                postorder, 0, postorder.length - 1,
                valToIndex);
    }

    /**
     * Helper method to recursively build the binary tree
     *
     * @param preorder The preorder traversal array
     * @param preStart Start index of current segment in preorder array
     * @param preEnd End index of current segment in preorder array
     * @param postorder The postorder traversal array
     * @param postStart Start index of current segment in postorder array
     * @param postEnd End index of current segment in postorder array
     * @param valToIndex Map of value to index in postorder array
     * @return The root of the constructed binary tree
     */
    private TreeNode build(int[] preorder, int preStart, int preEnd,
                           int[] postorder, int postStart, int postEnd,
                           HashMap<Integer, Integer> valToIndex) {
        // Base case: empty subtree
        if (preStart > preEnd) {
            return null;
        }

        // Special case: leaf node (single node subtree)
        if (preStart == preEnd) {
            return new TreeNode(preorder[preStart]);
        }

        // The first element in preorder is the root
        int rootVal = preorder[preStart];
        TreeNode root = new TreeNode(rootVal);

        // The second element in preorder is the root of the left subtree (if it exists)
        int leftRootVal = preorder[preStart + 1];

        // Find the index of the left subtree root in postorder
        int leftRootIndex = valToIndex.get(leftRootVal);

        // Calculate the size of the left subtree
        // leftRootIndex is the position of left subtree root in postorder
        // postStart is the start of the current segment in postorder
        // Add 1 to include the left subtree root itself
        int leftSize = leftRootIndex - postStart + 1;

        // Recursively build left subtree
        // Preorder: from after the root to leftSize elements
        // Postorder: from start to the left subtree's root index
        root.left = build(preorder, preStart + 1, preStart + leftSize,
                postorder, postStart, leftRootIndex,
                valToIndex);

        // Recursively build right subtree
        // Preorder: remaining elements after the left subtree
        // Postorder: remaining elements before the root
        root.right = build(preorder, preStart + leftSize + 1, preEnd,
                postorder, leftRootIndex + 1, postEnd - 1,
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
