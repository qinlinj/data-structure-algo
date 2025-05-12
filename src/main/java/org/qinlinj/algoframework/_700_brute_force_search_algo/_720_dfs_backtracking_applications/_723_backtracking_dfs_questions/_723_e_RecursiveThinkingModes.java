package org.qinlinj.algoframework._700_brute_force_search_algo._720_dfs_backtracking_applications._723_backtracking_dfs_questions;

/**
 * RECURSIVE THINKING MODES
 * <p>
 * This class summarizes the two main thinking modes for recursive algorithms:
 * 1. "Traversal" thinking mode
 * 2. "Divide and Conquer" thinking mode
 * <p>
 * Understanding these two modes helps clarify when to use return values
 * and how to structure recursive functions effectively.
 */

public class _723_e_RecursiveThinkingModes {

    /**
     * Example 1: "Traversal" thinking mode
     * - Focus on visiting every node
     * - Often uses external variables to collect results
     * - Typically void return type
     * <p>
     * Problem: Find maximum value in a binary tree
     */
    public static int findMaxTraversal(TreeNode root) {
        // Use a holder class to track the max value
        MaxValueHolder holder = new MaxValueHolder();
        // Initialize with the minimum possible value
        holder.maxValue = Integer.MIN_VALUE;

        // Traverse the tree to find max value
        traverseForMax(root, holder);

        return holder.maxValue;
    }

    // Traversal approach - void return type
    private static void traverseForMax(TreeNode root, MaxValueHolder holder) {
        // Base case
        if (root == null) {
            return;
        }

        // Update max at current node
        holder.maxValue = Math.max(holder.maxValue, root.val);

        // Continue traversal
        traverseForMax(root.left, holder);
        traverseForMax(root.right, holder);
    }

    /**
     * Example 2: "Divide and Conquer" thinking mode
     * - Break problem into subproblems
     * - Solve subproblems recursively
     * - Combine results to solve original problem
     * - Typically has meaningful return value
     * <p>
     * Same problem: Find maximum value in a binary tree
     */
    public static int findMaxDivideConquer(TreeNode root) {
        // Base case
        if (root == null) {
            return Integer.MIN_VALUE;
        }

        // Solve left subtree subproblem
        int leftMax = findMaxDivideConquer(root.left);

        // Solve right subtree subproblem
        int rightMax = findMaxDivideConquer(root.right);

        // Combine results (return max of current node, left max, and right max)
        return Math.max(root.val, Math.max(leftMax, rightMax));
    }

    /**
     * Example 3: Finding a target node in a binary tree
     * <p>
     * Traversal thinking mode approach
     */
    public static TreeNode findNodeTraversal(TreeNode root, int targetVal) {
        // Holder for result
        TreeNodeHolder holder = new TreeNodeHolder();

        // Traverse to find the node
        traverseForNode(root, targetVal, holder);

        return holder.result;
    }

    private static void traverseForNode(TreeNode root, int targetVal, TreeNodeHolder holder) {
        // Base cases
        if (root == null) {
            return;
        }

        // Already found the node, terminate recursion
        if (holder.result != null) {
            return;
        }

        // Check current node
        if (root.val == targetVal) {
            holder.result = root;
            return;
        }

        // Continue traversal
        traverseForNode(root.left, targetVal, holder);
        traverseForNode(root.right, targetVal, holder);
    }

    /**
     * Divide and conquer approach for finding a node
     */
    public static TreeNode findNodeDivideConquer(TreeNode root, int targetVal) {
        // Base case
        if (root == null) {
            return null;
        }

        // Check if target is at current node
        if (root.val == targetVal) {
            return root;
        }

        // Check left subtree
        TreeNode leftResult = findNodeDivideConquer(root.left, targetVal);
        if (leftResult != null) {
            return leftResult;
        }

        // Check right subtree
        return findNodeDivideConquer(root.right, targetVal);
    }

    /**
     * Main method to demonstrate the two thinking modes
     */
    public static void main(String[] args) {
        System.out.println("RECURSIVE THINKING MODES");
        System.out.println("=======================");

        // Create a sample binary tree
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(15);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(8);
        root.right.left = new TreeNode(12);
        root.right.right = new TreeNode(20);

        // Test max value finding
        int maxTraversal = findMaxTraversal(root);
        int maxDivideConquer = findMaxDivideConquer(root);

        System.out.println("\nFinding maximum value in a binary tree:");
        System.out.println("Using traversal approach: " + maxTraversal);
        System.out.println("Using divide and conquer approach: " + maxDivideConquer);

        // Test node finding
        int targetVal = 12;
        TreeNode nodeTraversal = findNodeTraversal(root, targetVal);
        TreeNode nodeDivideConquer = findNodeDivideConquer(root, targetVal);

        System.out.println("\nFinding node with value " + targetVal + ":");
        System.out.println("Using traversal approach: " + nodeTraversal);
        System.out.println("Using divide and conquer approach: " + nodeDivideConquer);

        System.out.println("\nComparison of Thinking Modes:");

        System.out.println("\n1. Traversal Thinking Mode:");
        System.out.println("- Focus on visiting every node in the tree");
        System.out.println("- Often uses external variables to collect results");
        System.out.println("- Functions are typically void (no return value)");
        System.out.println("- Good for problems requiring processing all nodes");
        System.out.println("- Example use cases: printing all nodes, collecting all paths");

        System.out.println("\n2. Divide and Conquer Thinking Mode:");
        System.out.println("- Break problem into subproblems");
        System.out.println("- Solve subproblems recursively");
        System.out.println("- Combine results to solve original problem");
        System.out.println("- Functions typically have meaningful return values");
        System.out.println("- Good for problems with clear subproblem structure");
        System.out.println("- Example use cases: finding max/min, computing tree properties");

        System.out.println("\nRecommendations:");
        System.out.println("1. For backtracking and DFS, prefer the traversal mode");
        System.out.println("2. Keep function names meaningful:");
        System.out.println("   - Use 'traverse/dfs/backtrack' for traversal mode functions");
        System.out.println("   - Use descriptive names for divide-and-conquer functions");
        System.out.println("3. Be consistent in your approach for better code clarity");
        System.out.println("4. Choose the mode that best fits the problem structure");
    }

    /**
     * TreeNode class for examples
     */
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }

        @Override
        public String toString() {
            return "TreeNode(" + val + ")";
        }
    }

    private static class MaxValueHolder {
        int maxValue;
    }

    private static class TreeNodeHolder {
        TreeNode result = null;
    }
}