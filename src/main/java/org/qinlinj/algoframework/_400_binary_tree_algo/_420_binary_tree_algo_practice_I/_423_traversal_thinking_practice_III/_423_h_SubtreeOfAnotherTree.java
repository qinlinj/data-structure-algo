package org.qinlinj.algoframework._400_binary_tree_algo._420_binary_tree_algo_practice_I._423_traversal_thinking_practice_III;

/**
 * Problem 572: Subtree of Another Tree
 * <p>
 * Description:
 * Given the roots of two binary trees, root and subRoot,
 * return true if there is a subtree of root with the same structure and node values as subRoot.
 * A subtree of a binary tree tree is a tree consisting of a node in tree and all of this node's descendants.
 * <p>
 * Key Concepts:
 * - Uses the "traversal" thinking pattern for binary trees
 * - Requires comparing two trees for equivalence
 * - Leverages a helper function to check if trees are identical
 * - Time complexity is higher (O(N*M)) due to nested traversals
 * <p>
 * Time Complexity: O(N*M), where N is the number of nodes in root and M in subRoot
 * Space Complexity: O(H), where H is the height of the tree (for recursion stack)
 */
public class _423_h_SubtreeOfAnotherTree {
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        // Base cases
        if (root == null) {
            return subRoot == null;
        }

        // Check if current tree matches the subRoot
        if (isSameTree(root, subRoot)) {
            return true;
        }

        // Recursively check left and right subtrees
        return isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
    }

    /**
     * Helper method to check if two trees are identical
     *
     * @param p First tree root
     * @param q Second tree root
     * @return true if trees are identical, false otherwise
     */
    private boolean isSameTree(TreeNode p, TreeNode q) {
        // Both null - they're identical
        if (p == null && q == null) {
            return true;
        }

        // One null, one not - they're different
        if (p == null || q == null) {
            return false;
        }

        // Values don't match - they're different
        if (p.val != q.val) {
            return false;
        }

        // Recursively check if left and right subtrees are identical
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    /**
     * Alternative implementation using string serialization
     * This approach converts trees to strings and uses string matching
     * Time complexity: O(N+M), but with higher constant factors for string operations
     */
    public boolean isSubtreeWithSerialization(TreeNode root, TreeNode subRoot) {
        // Serialize both trees
        String rootSerialized = serialize(root);
        String subRootSerialized = serialize(subRoot);

        // Check if subRoot's serialization is a substring of root's serialization
        return rootSerialized.contains(subRootSerialized);
    }

    /**
     * Serialize a binary tree to a string
     * Uses a specific format to avoid false positives
     */
    private String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        serializeHelper(root, sb);
        return sb.toString();
    }

    private void serializeHelper(TreeNode node, StringBuilder sb) {
        // Use a special marker for null nodes
        if (node == null) {
            sb.append("#,");
            return;
        }

        // Append node value with a delimiter to avoid false matches
        // E.g., trees [12] and [2] would be distinguished
        sb.append(",").append(node.val).append(",");

        // Recursively serialize left and right subtrees
        serializeHelper(node.left, sb);
        serializeHelper(node.right, sb);
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
