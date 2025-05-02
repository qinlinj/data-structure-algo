package org.qinlinj.algoframework._400_binary_tree_algo._420_binary_tree_algo_practice_I._421_traversal_thinking_practice_I;

/**
 * Binary Tree Path Problems: Summary and Patterns
 * <p>
 * This file summarizes the common patterns and techniques used in binary tree path problems.
 * All the problems we've examined use the "traversal" thinking pattern rather than the
 * "divide and conquer" pattern.
 * <p>
 * Common Pattern Overview:
 * 1. Maintain a state variable to track the current path during traversal
 * 2. Update this state at the preorder position (before visiting children)
 * 3. Process the path when reaching leaf nodes
 * 4. Restore the state at the postorder position (after visiting children)
 * <p>
 * Key Techniques:
 * <p>
 * 1. Path Tracking:
 * - Use a list, StringBuilder, or integer to track the current path
 * - Add to the path at preorder position (before visiting children)
 * - Remove from the path at postorder position (after visiting children)
 * <p>
 * 2. Leaf Node Processing:
 * - Most path problems require special handling at leaf nodes
 * - Identify leaf nodes with: root.left == null && root.right == null
 * - Process the complete path (convert to string, calculate sum, etc.)
 * <p>
 * 3. Binary Tree Traversal Framework:
 * - Preorder position: Process current node before children
 * - Inorder position: Process current node between children
 * - Postorder position: Process current node after children
 * <p>
 * 4. Specialized Techniques:
 * - BFS vs DFS: Level-order traversal vs depth-first traversal
 * - Bit manipulation: Efficiently track node values using bitwise operations
 * - String processing: Path joining, comparison, and manipulation
 * <p>
 * Problems Covered:
 * 1. Binary Tree Paths (#257): Find all root-to-leaf paths
 * 2. Sum Root to Leaf Numbers (#129): Convert paths to numbers and sum them
 * 3. Binary Tree Right Side View (#199): View tree from the right side
 * 4. Smallest String Starting from Leaf (#988): Find lexicographically smallest leaf-to-root path
 * 5. Sum of Root to Leaf Binary Numbers (#1022): Sum binary numbers formed by paths
 * 6. Pseudo-Palindromic Paths (#1457): Count paths that can form palindromes
 */
public class _421_g_BinaryTreeTraversalSummary {
    /**
     * Generic binary tree traversal framework that demonstrates the key positions
     * for processing nodes during traversal.
     */
    public void traverse(TreeNode root) {
        if (root == null) {
            return;
        }

        // Preorder position - Process BEFORE visiting children
        // This is where we typically add the current node to our path
        System.out.println("Preorder: " + root.val);

        // Traverse left subtree
        traverse(root.left);

        // Inorder position - Process BETWEEN visiting children
        // Less commonly used for path problems
        System.out.println("Inorder: " + root.val);

        // Traverse right subtree
        traverse(root.right);

        // Postorder position - Process AFTER visiting children
        // This is where we typically remove the current node from our path
        System.out.println("Postorder: " + root.val);
    }

    /**
     * Example of a typical pattern for path tracking and processing
     */
    public void pathTrackingExample(TreeNode root) {
        // Initialize path tracking structure
        StringBuilder path = new StringBuilder();
        // Initialize result container
        int result = 0;

        // Start traversal
        traverse(root, path, result);
    }

    private void traverse(TreeNode root, StringBuilder path, int result) {
        if (root == null) {
            return;
        }

        // Preorder: Add current node to path
        path.append(root.val);

        // Check if leaf node
        if (root.left == null && root.right == null) {
            // Process complete path at leaf nodes
            // For example: convert path to string, calculate sum, etc.
            System.out.println("Complete path: " + path.toString());
        }

        // Recurse on children
        traverse(root.left, path, result);
        traverse(root.right, path, result);

        // Postorder: Remove current node from path
        path.deleteCharAt(path.length() - result);  // Important backtracking step
    }

    // Definition for a binary tree node
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
    }
}