package org.qinlinj.algoframework._400_binary_tree_algo._420_binary_tree_algo_practice_I._422_traversal_thinking_practice_II;

/**
 * Problem 404: Sum of Left Leaves
 * <p>
 * Description:
 * Given the root of a binary tree, return the sum of all left leaves.
 * A leaf is a node with no children. A left leaf is a leaf that is the left child of another node.
 * <p>
 * Key Concepts:
 * - Uses the "traversal" thinking pattern for binary trees
 * - Identifies left leaves during traversal
 * - A left leaf is a node that:
 * 1. Is a left child of its parent
 * 2. Has no children (both left and right are null)
 * - Accumulates the values of identified left leaves
 * <p>
 * Time Complexity: O(N), where N is the number of nodes in the tree
 * Space Complexity: O(H), where H is the height of the tree (for recursion stack)
 */
public class _422_a_SumOfLeftLeaves {
    // Accumulator for left leaf sum
    private int sum = 0;

    public int sumOfLeftLeaves(TreeNode root) {
        // Start traversal from root
        traverse(root);
        return sum;
    }

    private void traverse(TreeNode root) {
        if (root == null) {
            return;
        }

        // Check if the left child is a leaf node
        if (root.left != null &&
                root.left.left == null && root.left.right == null) {
            // Found a left leaf, add its value to the sum
            sum += root.left.val;
        }

        // Continue traversal on both subtrees
        traverse(root.left);
        traverse(root.right);

        // Note: This is a simple preorder traversal
        // No postorder processing is needed for this problem
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
