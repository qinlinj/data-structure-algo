package org.qinlinj.algoframework._400_binary_tree_algo._420_binary_tree_algo_practice_I._421_traversal_thinking_practice_I;

/**
 * Problem 129: Sum Root to Leaf Numbers
 * <p>
 * Description:
 * Given a binary tree where each node contains a digit from 0-9, each root-to-leaf path
 * represents a number. Find the total sum of all root-to-leaf numbers.
 * For example, the path 1->2->3 represents the number 123.
 * <p>
 * Key Concepts:
 * - Uses the "traversal" thinking pattern for binary trees
 * - Records digits along the path during traversal
 * - Converts the path to an integer at each leaf node and adds to result
 * - Uses a StringBuilder to build the number as we traverse
 * - Carefully maintains the path state using postorder operations
 * <p>
 * Time Complexity: O(N), where N is the number of nodes in the tree
 * Space Complexity: O(H), where H is the height of the tree
 */
public class _421_b_SumRootToLeafNumbers {
    // Tracks the current path during traversal
    private StringBuilder path = new StringBuilder();
    // Accumulates the sum of all path numbers
    private int res = 0;

    public int sumNumbers(TreeNode root) {
        // Start traversal from root
        traverse(root);
        return res;
    }

    private void traverse(TreeNode root) {
        if (root == null) {
            return;
        }

        // Preorder traversal position - add current digit to path
        path.append(root.val);

        // Check if current node is a leaf node
        if (root.left == null && root.right == null) {
            // Convert path to integer and add to result
            res += Integer.parseInt(path.toString());
        }

        // Recursively traverse left and right subtrees
        traverse(root.left);
        traverse(root.right);

        // Postorder traversal position - remove current digit from path
        path.deleteCharAt(path.length() - 1);
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
