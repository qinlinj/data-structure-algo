package org.qinlinj.algoframework._400_binary_tree_algo._420_binary_tree_algo_practice_I._424_problem_decomposition_thinking_practice_I;

import java.util.*;

/**
 * Problem 889: Construct Binary Tree from Preorder and Postorder Traversal
 * <p>
 * Description:
 * Given two integer arrays, preorder and postorder, where preorder is the preorder traversal
 * of a binary tree and postorder is the postorder traversal of the same tree,
 * construct and return the binary tree.
 * <p>
 * Note: If there exist multiple answers, you can return any of them.
 * <p>
 * Key Concepts:
 * - Uses the "divide and conquer" approach for binary tree construction
 * - Unlike preorder+inorder or inorder+postorder, preorder+postorder can yield multiple valid trees
 * - Preorder traversal gives the root node as the first element
 * - Postorder traversal gives the root node as the last element
 * - Uses the left subtree's root (second element in preorder) to divide subtrees
 * - Recursively builds the tree by identifying roots and constructing subtrees
 * <p>
 * Time Complexity: O(n), where n is the number of nodes in the tree
 * Space Complexity: O(n) for the HashMap and recursion stack
 */
public class _424_c_ConstructBinaryTreeFromPreorderPostorder {
    // HashMap to store postorder indices for O(1) lookup
    private HashMap<Integer, Integer> valToIndex = new HashMap<>();

    public TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
        // Map each value in postorder to its index for O(1) lookup
        for (int i = 0; i < postorder.length; i++) {
            valToIndex.put(postorder[i], i);
        }

        // Build the tree using the full arrays
        return build(preorder, 0, preorder.length - 1,
                postorder, 0, postorder.length - 1);
    }

    /**
     * Recursively builds a binary tree from preorder and postorder traversal arrays
     *
     * @param preorder  Preorder traversal array
     * @param preStart  Start index of current subtree in preorder
     * @param preEnd    End index of current subtree in preorder
     * @param postorder Postorder traversal array
     * @param postStart Start index of current subtree in postorder
     * @param postEnd   End index of current subtree in postorder
     * @return Root of the constructed binary tree
     */
    private TreeNode build(int[] preorder, int preStart, int preEnd,
                           int[] postorder, int postStart, int postEnd) {
        // Base case: empty subtree
        if (preStart > preEnd) {
            return null;
        }

        // Handle single node case
        if (preStart == preEnd) {
            return new TreeNode(preorder[preStart]);
        }

        // The first element in preorder is the root of the current subtree
        int rootVal = preorder[preStart];

        // The second element in preorder is the root of the left subtree
        int leftRootVal = preorder[preStart + 1];

        // Find the left root's position in postorder to determine left subtree size
        int leftRootPostIndex = valToIndex.get(leftRootVal);

        // Calculate the size of the left subtree
        int leftSubtreeSize = leftRootPostIndex - postStart + 1;

        // Create the current root node
        TreeNode root = new TreeNode(rootVal);

        // Recursively build left subtree
        // Left subtree preorder range: [preStart+1, preStart+leftSubtreeSize]
        // Left subtree postorder range: [postStart, leftRootPostIndex]
        root.left = build(preorder, preStart + 1, preStart + leftSubtreeSize,
                postorder, postStart, leftRootPostIndex);

        // Recursively build right subtree
        // Right subtree preorder range: [preStart+leftSubtreeSize+1, preEnd]
        // Right subtree postorder range: [leftRootPostIndex+1, postEnd-1]
        root.right = build(preorder, preStart + leftSubtreeSize + 1, preEnd,
                postorder, leftRootPostIndex + 1, postEnd - 1);

        return root;
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
