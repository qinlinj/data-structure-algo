package org.qinlinj.algoframework._400_binary_tree_algo._430_binary_tree_algo_practice_II._432_postorder_position_practice_II;

/**
 * Problem 606: Construct String from Binary Tree
 * <p>
 * Description:
 * Given the root of a binary tree, construct a string representation of the tree following these rules:
 * - The preorder traversal is used to construct the string representation.
 * - If a node has a left child, enclose the string representation of the left subtree in parentheses.
 * - If a node has a right child, enclose the string representation of the right subtree in parentheses.
 * - If a node has a right child but no left child, include empty parentheses "()" for the left subtree.
 * - If a node has no children, don't include any parentheses.
 * <p>
 * Key Concepts:
 * - Uses the post-order position to assemble the string representation
 * - Handles different cases of missing children according to the rules
 * - Demonstrates string construction from bottom-up tree traversal
 * - Shows how to implement preorder format using postorder processing
 * <p>
 * Time Complexity: O(n), where n is the number of nodes in the tree
 * Space Complexity: O(h), where h is the height of the tree (for recursion stack)
 * O(n) for the string representation
 */
public class _432_f_ConstructStringFromBinaryTree {
    /**
     * Main function to construct string representation of binary tree
     *
     * @param root Root of the binary tree
     * @return String representation of the binary tree
     */
    public String tree2str(TreeNode root) {
        // Base case: empty tree
        if (root == null) {
            return "";
        }

        // Base case: leaf node (no children)
        if (root.left == null && root.right == null) {
            return String.valueOf(root.val);
        }

        // Recursively get string representations of left and right subtrees
        String leftStr = tree2str(root.left);
        String rightStr = tree2str(root.right);

        // Post-order position: assemble the string representation

        // Case 1: Node has left child but no right child
        if (root.left != null && root.right == null) {
            return root.val + "(" + leftStr + ")";
        }

        // Case 2: Node has right child but no left child
        // Must include empty parentheses for missing left child
        if (root.left == null && root.right != null) {
            return root.val + "()" + "(" + rightStr + ")";
        }

        // Case 3: Node has both left and right children
        return root.val + "(" + leftStr + ")" + "(" + rightStr + ")";
    }

    /**
     * Alternative implementation using StringBuilder for better string manipulation
     */
    public String tree2strWithStringBuilder(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        buildString(root, sb);
        return sb.toString();
    }

    /**
     * Helper function for StringBuilder implementation
     */
    private void buildString(TreeNode node, StringBuilder sb) {
        if (node == null) {
            return;
        }

        // Append current node value
        sb.append(node.val);

        // Handle left subtree
        if (node.left != null || node.right != null) {
            // Include left subtree (or empty parentheses if null)
            sb.append("(");
            buildString(node.left, sb);
            sb.append(")");

            // Include right subtree (only if it exists)
            if (node.right != null) {
                sb.append("(");
                buildString(node.right, sb);
                sb.append(")");
            }
        }
    }

    /**
     * Implementation with more direct string concatenation
     */
    public String tree2strDirect(TreeNode root) {
        if (root == null) {
            return "";
        }

        String result = String.valueOf(root.val);

        // Both children are null - return just the value
        if (root.left == null && root.right == null) {
            return result;
        }

        // Add left subtree representation
        result += "(" + tree2strDirect(root.left) + ")";

        // Add right subtree representation (only if it exists)
        if (root.right != null) {
            result += "(" + tree2strDirect(root.right) + ")";
        }

        return result;
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