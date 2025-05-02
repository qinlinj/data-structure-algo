package org.qinlinj.algoframework._400_binary_tree_algo._420_binary_tree_algo_practice_I._424_problem_decomposition_thinking_practice_I;

import java.util.*;

/**
 * Problem 106: Construct Binary Tree from Inorder and Postorder Traversal
 * <p>
 * Description:
 * Given two integer arrays inorder and postorder where inorder is the inorder traversal
 * of a binary tree and postorder is the postorder traversal of the same tree,
 * construct and return the binary tree.
 * <p>
 * Key Concepts:
 * - Uses the "divide and conquer" approach for binary tree construction
 * - Postorder traversal gives the root node as the last element
 * - Inorder traversal helps identify left and right subtrees
 * - Recursively builds the tree by identifying the root and constructing subtrees
 * - Uses a HashMap to quickly find indices in the inorder array
 * <p>
 * Time Complexity: O(n), where n is the number of nodes in the tree
 * Space Complexity: O(n) for the HashMap and recursion stack
 */
public class _424_b_ConstructBinaryTreeFromInorderPostorder {
    // HashMap to store inorder indices for O(1) lookup
    private HashMap<Integer, Integer> valToIndex = new HashMap<>();

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        // Map each value in inorder to its index for O(1) lookup
        for (int i = 0; i < inorder.length; i++) {
            valToIndex.put(inorder[i], i);
        }

        // Build the tree using the full arrays
        return build(inorder, 0, inorder.length - 1,
                postorder, 0, postorder.length - 1);
    }

    /**
     * Recursively builds a binary tree from inorder and postorder traversal arrays
     *
     * @param inorder   Inorder traversal array
     * @param inStart   Start index of current subtree in inorder
     * @param inEnd     End index of current subtree in inorder
     * @param postorder Postorder traversal array
     * @param postStart Start index of current subtree in postorder
     * @param postEnd   End index of current subtree in postorder
     * @return Root of the constructed binary tree
     */
    private TreeNode build(int[] inorder, int inStart, int inEnd,
                           int[] postorder, int postStart, int postEnd) {
        // Base case: empty subtree
        if (inStart > inEnd) {
            return null;
        }

        // The last element in postorder is the root of the current subtree
        int rootVal = postorder[postEnd];

        // Find the root's position in inorder
        int inRootIndex = valToIndex.get(rootVal);

        // Calculate the size of the left subtree
        int leftSubtreeSize = inRootIndex - inStart;

        // Create the current root node
        TreeNode root = new TreeNode(rootVal);

        // Recursively build left subtree
        // Left subtree inorder range: [inStart, inRootIndex-1]
        // Left subtree postorder range: [postStart, postStart+leftSubtreeSize-1]
        root.left = build(inorder, inStart, inRootIndex - 1,
                postorder, postStart, postStart + leftSubtreeSize - 1);

        // Recursively build right subtree
        // Right subtree inorder range: [inRootIndex+1, inEnd]
        // Right subtree postorder range: [postStart+leftSubtreeSize, postEnd-1]
        root.right = build(inorder, inRootIndex + 1, inEnd,
                postorder, postStart + leftSubtreeSize, postEnd - 1);

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
