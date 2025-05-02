package org.qinlinj.algoframework._400_binary_tree_algo._420_binary_tree_algo_practice_I._422_traversal_thinking_practice_II;

/**
 * Problem 1315: Sum of Nodes with Even-Valued Grandparent
 * <p>
 * Description:
 * Given the root of a binary tree, return the sum of values of all nodes with an even-valued grandparent.
 * A grandparent of a node is the parent of its parent, if it exists.
 * If there are no nodes with an even-valued grandparent, return 0.
 * <p>
 * Key Concepts:
 * - Uses the "traversal" thinking pattern for binary trees
 * - Identifies nodes with even-valued grandparents
 * - For each even-valued node, adds the values of all grandchildren to the sum
 * - Uses careful null checking to handle all cases
 * <p>
 * Time Complexity: O(N), where N is the number of nodes in the tree
 * Space Complexity: O(H), where H is the height of the tree (for recursion stack)
 */
public class _422_f_SumOfNodesWithEvenGrandparent {
    // Accumulator for the sum
    private int sum = 0;

    public int sumEvenGrandparent(TreeNode root) {
        traverse(root);
        return sum;
    }

    private void traverse(TreeNode root) {
        if (root == null) {
            return;
        }

        // Check if current node has an even value
        if (root.val % 2 == 0) {
            // Add values of all grandchildren to the sum

            // Check left child's children (grandchildren from left)
            if (root.left != null) {
                if (root.left.left != null) {
                    sum += root.left.left.val;
                }
                if (root.left.right != null) {
                    sum += root.left.right.val;
                }
            }

            // Check right child's children (grandchildren from right)
            if (root.right != null) {
                if (root.right.left != null) {
                    sum += root.right.left.val;
                }
                if (root.right.right != null) {
                    sum += root.right.right.val;
                }
            }
        }

        // Continue traversal
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
