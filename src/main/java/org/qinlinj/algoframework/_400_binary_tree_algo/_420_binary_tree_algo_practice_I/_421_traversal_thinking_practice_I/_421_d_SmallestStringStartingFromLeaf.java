package org.qinlinj.algoframework._400_binary_tree_algo._420_binary_tree_algo_practice_I._421_traversal_thinking_practice_I;

/**
 * Problem 988: Smallest String Starting from Leaf
 * <p>
 * Description:
 * Given a binary tree where each node has a value from 0 to 25 (representing 'a' to 'z'),
 * return the lexicographically smallest string that starts at a leaf and ends at the root.
 * Note: A leaf is a node with no children.
 * <p>
 * Key Concepts:
 * - Uses the "traversal" thinking pattern for binary trees
 * - Tracks the path during traversal and compares strings at leaf nodes
 * - The path is from root to leaf, but the desired string is from leaf to root
 * - Need to reverse the path when comparing strings
 * - Carefully maintains the path state using both preorder and postorder operations
 * <p>
 * Time Complexity: O(N), where N is the number of nodes in the tree
 * Space Complexity: O(H), where H is the height of the tree
 */
public class _421_d_SmallestStringStartingFromLeaf {
    // Tracks the current path during traversal
    private StringBuilder path = new StringBuilder();
    // Stores the lexicographically smallest string
    private String res = null;

    public String smallestFromLeaf(TreeNode root) {
        traverse(root);
        return res;
    }

    private void traverse(TreeNode root) {
        if (root == null) {
            return;
        }

        // Check if current node is a leaf node
        if (root.left == null && root.right == null) {
            // Add the current character to the path
            path.append((char) ('a' + root.val));

            // Reverse the path to get leaf-to-root order
            path.reverse();
            String currentString = path.toString();

            // Compare with the current smallest string
            if (res == null || currentString.compareTo(res) < 0) {
                res = currentString;
            }

            // Restore the path for backtracking
            path.reverse();
            path.deleteCharAt(path.length() - 1);
            return;
        }

        // Preorder traversal position - add current character to path
        path.append((char) ('a' + root.val));

        // Recursively traverse left and right subtrees
        traverse(root.left);
        traverse(root.right);

        // Postorder traversal position - remove current character from path
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
