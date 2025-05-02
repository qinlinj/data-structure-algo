package org.qinlinj.algoframework._400_binary_tree_algo._420_binary_tree_algo_practice_I._423_traversal_thinking_practice_III;

/**
 * Problem 2096: Step-by-Step Directions From a Binary Tree Node to Another
 * <p>
 * Description:
 * Given the root of a binary tree and two values startValue and destValue, return the
 * shortest path as a string of L, R, and U directions, where:
 * - L means to go from a node to its left child
 * - R means to go from a node to its right child
 * - U means to go from a node to its parent
 * <p>
 * Key Concepts:
 * - Finding paths from root to specific nodes
 * - Removing common prefix from two paths
 * - Converting paths to directions
 * - Handling upward and downward movements in the tree
 * <p>
 * Time Complexity: O(N), where N is the number of nodes in the tree
 * Space Complexity: O(H), where H is the height of the tree
 */
public class _423_g_StepByStepDirectionsBinaryTree {
    // Path from root to start node (as directions)
    private String startPath;
    // Path from root to destination node (as directions)
    private String destPath;
    // Target node values
    private int startValue;
    private int destValue;

    public String getDirections(TreeNode root, int startValue, int destValue) {
        this.startValue = startValue;
        this.destValue = destValue;

        // StringBuilder to track the path during traversal
        StringBuilder path = new StringBuilder();

        // Find paths from root to both nodes
        traverse(root, path);

        // Find the common prefix length of the two paths
        int commonPrefixLength = findCommonPrefixLength(startPath, destPath);

        // Remove common prefix
        String uniqueStartPath = startPath.substring(commonPrefixLength);
        String uniqueDestPath = destPath.substring(commonPrefixLength);

        // Convert start path to "U"s (all upward movements)
        String upwardPath = "U".repeat(uniqueStartPath.length());

        // Combine upward and downward paths
        return upwardPath + uniqueDestPath;
    }

    /**
     * Traverses the tree to find paths to start and destination nodes
     *
     * @param root Current node in traversal
     * @param path Current path from root to this node
     */
    private void traverse(TreeNode root, StringBuilder path) {
        if (root == null) {
            return;
        }

        // Check if we found either target node
        if (root.val == startValue) {
            startPath = path.toString();
        } else if (root.val == destValue) {
            destPath = path.toString();
        }

        // Continue traversal on left subtree
        path.append('L');
        traverse(root.left, path);
        path.deleteCharAt(path.length() - 1);

        // Continue traversal on right subtree
        path.append('R');
        traverse(root.right, path);
        path.deleteCharAt(path.length() - 1);
    }

    /**
     * Find the length of the common prefix between two strings
     *
     * @param s1 First string
     * @param s2 Second string
     * @return Length of the common prefix
     */
    private int findCommonPrefixLength(String s1, String s2) {
        int i = 0;
        int minLength = Math.min(s1.length(), s2.length());

        while (i < minLength && s1.charAt(i) == s2.charAt(i)) {
            i++;
        }

        return i;
    }

    /**
     * Alternative implementation using lowest common ancestor (LCA)
     */
    public String getDirectionsWithLCA(TreeNode root, int startValue, int destValue) {
        // Find the lowest common ancestor
        TreeNode lca = findLCA(root, startValue, destValue);

        // Find paths from LCA to start and destination
        StringBuilder startPathBuilder = new StringBuilder();
        StringBuilder destPathBuilder = new StringBuilder();

        findPath(lca, startValue, startPathBuilder);
        findPath(lca, destValue, destPathBuilder);

        // Convert start path to all "U"s since we need to go up to the LCA
        String upwardPath = "U".repeat(startPathBuilder.length());

        // Return the combined path
        return upwardPath + destPathBuilder.toString();
    }

    /**
     * Find the lowest common ancestor of two nodes
     */
    private TreeNode findLCA(TreeNode root, int p, int q) {
        if (root == null || root.val == p || root.val == q) {
            return root;
        }

        TreeNode left = findLCA(root.left, p, q);
        TreeNode right = findLCA(root.right, p, q);

        if (left != null && right != null) {
            return root;
        }

        return left != null ? left : right;
    }

    /**
     * Find path from a node to a target value
     */
    private boolean findPath(TreeNode root, int target, StringBuilder path) {
        if (root == null) {
            return false;
        }

        if (root.val == target) {
            return true;
        }

        // Try left subtree
        path.append('L');
        if (findPath(root.left, target, path)) {
            return true;
        }
        path.deleteCharAt(path.length() - 1);

        // Try right subtree
        path.append('R');
        if (findPath(root.right, target, path)) {
            return true;
        }
        path.deleteCharAt(path.length() - 1);

        return false;
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