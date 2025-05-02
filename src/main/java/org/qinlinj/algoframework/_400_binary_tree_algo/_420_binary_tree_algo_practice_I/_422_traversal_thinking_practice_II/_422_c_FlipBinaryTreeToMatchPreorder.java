package org.qinlinj.algoframework._400_binary_tree_algo._420_binary_tree_algo_practice_I._422_traversal_thinking_practice_II;

import java.util.*;

/**
 * Problem 971: Flip Binary Tree to Match Preorder Traversal
 * <p>
 * Description:
 * Given a binary tree with n nodes and a sequence voyage of n values,
 * the task is to flip the least number of nodes in the tree to make
 * the preorder traversal match the voyage sequence.
 * Flipping a node means swapping its left and right children.
 * Return a list of nodes that need to be flipped, or [-1] if it's impossible.
 * <p>
 * Key Concepts:
 * - Uses the "traversal" thinking pattern for binary trees
 * - Follows preorder traversal (root, left, right)
 * - Maintains an index to track position in the voyage array
 * - Checks if the current node matches the expected value in voyage
 * - If the left child doesn't match the next expected value, tries flipping
 * <p>
 * Time Complexity: O(N), where N is the number of nodes in the tree
 * Space Complexity: O(H), where H is the height of the tree (for recursion stack)
 * O(N) for the result in the worst case
 */
public class _422_c_FlipBinaryTreeToMatchPreorder {
    // Store nodes that need to be flipped
    private List<Integer> res = new LinkedList<>();
    // Current index in the voyage array
    private int i = 0;
    // The target preorder traversal sequence
    private int[] voyage;
    // Flag to indicate if flipping can produce the target sequence
    private boolean canFlip = true;

    public List<Integer> flipMatchVoyage(TreeNode root, int[] voyage) {
        this.voyage = voyage;

        // Start traversal from root
        traverse(root);

        // If flipping is possible, return the list of flipped nodes
        // Otherwise, return [-1]
        if (canFlip) {
            return res;
        }
        return Arrays.asList(-1);
    }

    private void traverse(TreeNode root) {
        // Base cases
        if (root == null || !canFlip) {
            return;
        }

        // Check if current node matches the expected value in voyage
        if (root.val != voyage[i++]) {
            // If not, it's impossible to match by flipping
            canFlip = false;
            return;
        }

        // If left child exists but doesn't match the next expected value in voyage
        if (root.left != null && i < voyage.length && root.left.val != voyage[i]) {
            // Try flipping this node (swap left and right children)
            TreeNode temp = root.left;
            root.left = root.right;
            root.right = temp;

            // Record this node as flipped
            res.add(root.val);
        }

        // Continue traversal on left and right subtrees
        traverse(root.left);
        traverse(root.right);
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
