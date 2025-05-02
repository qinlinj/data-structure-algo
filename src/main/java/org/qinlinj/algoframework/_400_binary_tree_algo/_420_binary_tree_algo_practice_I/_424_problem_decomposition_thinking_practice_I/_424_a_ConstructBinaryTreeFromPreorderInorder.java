package org.qinlinj.algoframework._400_binary_tree_algo._420_binary_tree_algo_practice_I._424_problem_decomposition_thinking_practice_I;

import java.util.*;

/**
 * Problem 105: Construct Binary Tree from Preorder and Inorder Traversal
 * <p>
 * Description:
 * Given two integer arrays preorder and inorder where preorder is the preorder traversal
 * of a binary tree and inorder is the inorder traversal of the same tree,
 * construct and return the binary tree.
 * <p>
 * Key Concepts:
 * - Uses the "divide and conquer" approach for binary tree construction
 * - Preorder traversal gives the root node as the first element
 * - Inorder traversal helps identify left and right subtrees
 * - Recursively builds the tree by identifying the root and constructing subtrees
 * - Uses a HashMap to quickly find indices in the inorder array
 * <p>
 * Time Complexity: O(n), where n is the number of nodes in the tree
 * Space Complexity: O(n) for the HashMap and recursion stack
 */
public class _424_a_ConstructBinaryTreeFromPreorderInorder {
    // HashMap to store inorder indices for O(1) lookup
    private HashMap<Integer, Integer> valToIndex = new HashMap<>();

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        // Map each value in inorder to its index for O(1) lookup
        for (int i = 0; i < inorder.length; i++) {
            valToIndex.put(inorder[i], i);
        }

        // Build the tree using the full arrays
        return build(preorder, 0, preorder.length - 1,
                inorder, 0, inorder.length - 1);
    }

    /**
     * Recursively builds a binary tree from preorder and inorder traversal arrays
     *
     * @param preorder Preorder traversal array
     * @param preStart Start index of current subtree in preorder
     * @param preEnd   End index of current subtree in preorder
     * @param inorder  Inorder traversal array
     * @param inStart  Start index of current subtree in inorder
     * @param inEnd    End index of current subtree in inorder
     * @return Root of the constructed binary tree
     */
    private TreeNode build(int[] preorder, int preStart, int preEnd,
                           int[] inorder, int inStart, int inEnd) {
        // Base case: empty subtree
        if (preStart > preEnd) {
            return null;
        }

        // The first element in preorder is the root of the current subtree
        int rootVal = preorder[preStart];

        // Find the root's position in inorder
        int inRootIndex = valToIndex.get(rootVal);

        // Calculate the size of the left subtree
        int leftSubtreeSize = inRootIndex - inStart;

        // Create the current root node
        TreeNode root = new TreeNode(rootVal);

        // Recursively build left subtree
        // Left subtree preorder range: [preStart+1, preStart+leftSubtreeSize]
        // Left subtree inorder range: [inStart, inRootIndex-1]
        root.left = build(preorder, preStart + 1, preStart + leftSubtreeSize,
                inorder, inStart, inRootIndex - 1);

        // Recursively build right subtree
        // Right subtree preorder range: [preStart+leftSubtreeSize+1, preEnd]
        // Right subtree inorder range: [inRootIndex+1, inEnd]
        root.right = build(preorder, preStart + leftSubtreeSize + 1, preEnd,
                inorder, inRootIndex + 1, inEnd);

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
