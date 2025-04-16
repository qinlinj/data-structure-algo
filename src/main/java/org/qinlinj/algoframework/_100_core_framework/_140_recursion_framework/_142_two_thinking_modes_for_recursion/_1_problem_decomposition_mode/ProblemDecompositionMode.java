package org.qinlinj.algoframework._100_core_framework._140_recursion_framework._142_two_thinking_modes_for_recursion._1_problem_decomposition_mode;

// @formatter:off
public class ProblemDecompositionMode {
    public int maxDepth(TreeNode root) {
        // Base case: empty tree has depth 0
        if (root == null) {
            return 0;
        }

        // Decompose into subproblems:
        // Calculate the maximum depth of left and right subtrees
        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);

        // Combine solutions to subproblems:
        // The max depth is the greater of the two subtree depths plus 1 (for the root)
        return 1 + Math.max(leftDepth, rightDepth);
    }

    /**
     * Definition for a binary tree node
     */
    public static class TreeNode {
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
