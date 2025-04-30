package org.qinlinj.algoframework._400_binary_tree_algo._410_binary_tree_algo_guiding_principle._411_binary_tree_guiding_principle;

/**
 * BINARY TREE POST-ORDER POSITION - POWER AND APPLICATIONS
 * =======================================================
 * <p>
 * This class focuses on the special importance of the post-order position in binary tree algorithms.
 * Post-order traversal offers unique capabilities that make it especially powerful for certain problems.
 * <p>
 * Key insights:
 * <p>
 * 1. The Post-Order Position Advantage:
 * - Pre-order can only access information from parent nodes (parameters)
 * - In-order can access parent nodes and left subtree information
 * - Post-order can access parent nodes, left subtree, AND right subtree information
 * <p>
 * 2. Information Flow in Recursion:
 * - Post-order is the only position where you have complete information about both subtrees
 * - This makes it ideal for problems where you need to combine results from subtrees
 * <p>
 * 3. When to Use Post-Order:
 * - When the problem involves computations on subtrees
 * - When you need to make decisions based on properties of both left and right subtrees
 * <p>
 * 4. Examples where Post-Order Excels:
 * - Calculating tree diameter (needs information from both subtrees)
 * - Counting nodes in subtrees
 * - Determining if a tree is balanced
 * <p>
 * Post-order traversal is particularly important for "subtree problems" where solutions
 * to subproblems contribute to the solution of the original problem.
 */

public class _411_d_BinaryTreePostOrder {
    /**
     * Example 2: Calculating tree diameter (maximum distance between any two nodes)
     * This demonstrates why post-order is essential for certain problems
     */
    private int maxDiameter = 0;

    public static void main(String[] args) {
        _411_d_BinaryTreePostOrder solution = new _411_d_BinaryTreePostOrder();

        // Create a sample tree
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        System.out.println("Node count:");
        int count = solution.countNodes(root);
        System.out.println("Total count: " + count);

        System.out.println("\nTree diameter: " + solution.diameterOfBinaryTree(root));

        System.out.println("\nIs tree balanced: " + solution.isBalanced(root));
    }

    /**
     * Example 1: Counting nodes in a binary tree using post-order
     * Definition: Count and return the number of nodes in the tree rooted at this node
     */
    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }

        // First get counts from left and right subtrees
        int leftCount = countNodes(root.left);
        int rightCount = countNodes(root.right);

        // Post-order position - combining results
        // Print the counts for demonstration
        System.out.println("Node " + root.val + " has " + leftCount +
                " nodes in left subtree and " + rightCount +
                " nodes in right subtree");

        // Return total count (left + right + 1 for current node)
        return leftCount + rightCount + 1;
    }

    public int diameterOfBinaryTree(TreeNode root) {
        maxDepth(root);
        return maxDiameter;
    }

    // Helper function that calculates depth and updates diameter
    private int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        // Calculate depths of left and right subtrees
        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);

        // Post-order position - Update diameter
        // The path through current node has length leftDepth + rightDepth
        int currentDiameter = leftDepth + rightDepth;
        maxDiameter = Math.max(maxDiameter, currentDiameter);

        // Return the max depth from this node
        return 1 + Math.max(leftDepth, rightDepth);
    }

    /**
     * Example 3: Inefficient approach to diameter calculation without using post-order
     * Shows recursive redundancy without post-order optimization
     */
    public int diameterOfBinaryTreeInefficient(TreeNode root) {
        if (root == null) {
            return 0;
        }

        // Calculate diameter through current node
        int diameterThroughRoot = maxDepthSimple(root.left) + maxDepthSimple(root.right);

        // Calculate diameter in left subtree
        int diameterLeftSubtree = diameterOfBinaryTreeInefficient(root.left);

        // Calculate diameter in right subtree
        int diameterRightSubtree = diameterOfBinaryTreeInefficient(root.right);

        // Return maximum of the three diameters
        return Math.max(diameterThroughRoot,
                Math.max(diameterLeftSubtree, diameterRightSubtree));
    }

    // Simple helper function for depth calculation
    private int maxDepthSimple(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + Math.max(maxDepthSimple(root.left), maxDepthSimple(root.right));
    }

    /**
     * Example 4: Checking if a binary tree is balanced using post-order
     * A balanced tree has the height difference between left and right subtrees <= 1 for all nodes
     */
    public boolean isBalanced(TreeNode root) {
        return balancedHeight(root) != -1;
    }

    // Helper function that returns height of the tree if balanced, -1 if unbalanced
    private int balancedHeight(TreeNode root) {
        if (root == null) {
            return 0;
        }

        // Get heights of left and right subtrees
        int leftHeight = balancedHeight(root.left);
        int rightHeight = balancedHeight(root.right);

        // If either subtree is unbalanced, propagate the result up
        if (leftHeight == -1 || rightHeight == -1) {
            return -1;
        }

        // Post-order position - check balance at current node
        if (Math.abs(leftHeight - rightHeight) > 1) {
            return -1;  // Unbalanced
        }

        // Return height of current tree
        return 1 + Math.max(leftHeight, rightHeight);
    }

    // Definition for a binary tree node
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
