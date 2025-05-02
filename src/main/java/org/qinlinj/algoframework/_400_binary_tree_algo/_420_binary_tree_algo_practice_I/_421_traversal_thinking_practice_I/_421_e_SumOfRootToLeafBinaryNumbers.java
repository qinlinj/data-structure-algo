package org.qinlinj.algoframework._400_binary_tree_algo._420_binary_tree_algo_practice_I._421_traversal_thinking_practice_I;

/**
 * Problem 1022: Sum of Root to Leaf Binary Numbers
 * <p>
 * Description:
 * Given a binary tree where each node has a value of 0 or 1,
 * each root-to-leaf path represents a binary number.
 * Return the sum of all binary numbers represented by all paths.
 * <p>
 * Key Concepts:
 * - Uses the "traversal" thinking pattern for binary trees
 * - Tracks the binary number formed during traversal
 * - Uses bit manipulation to efficiently build the binary number
 * - Uses path << 1 | root.val to add a new bit to the path
 * - Uses path >> 1 to remove the last bit when backtracking
 * <p>
 * Time Complexity: O(N), where N is the number of nodes in the tree
 * Space Complexity: O(H), where H is the height of the tree
 */
public class _421_e_SumOfRootToLeafBinaryNumbers {
    // Tracks the binary path during traversal
    private int path = 0;
    // Accumulates the sum of all path binary numbers
    private int res = 0;

    public int sumRootToLeaf(TreeNode root) {
        traverse(root);
        return res;
    }

    private void traverse(TreeNode root) {
        if (root == null) {
            return;
        }

        // Check if current node is a leaf node
        if (root.left == null && root.right == null) {
            // Add the current bit to path and add to result
            // path << 1 | root.val appends the current bit to the binary number
            res += path << 1 | root.val;
            return;
        }

        // Preorder traversal position
        // Add current bit to the path (equivalent to path = path * 2 + root.val)
        path = path << 1 | root.val;

        // Recursively traverse left and right subtrees
        traverse(root.left);
        traverse(root.right);

        // Postorder traversal position
        // Remove the last bit from path (equivalent to path = path / 2)
        path = path >> 1;
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
