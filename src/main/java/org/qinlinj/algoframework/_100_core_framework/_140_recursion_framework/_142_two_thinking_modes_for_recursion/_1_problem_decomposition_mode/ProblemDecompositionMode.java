package org.qinlinj.algoframework._100_core_framework._140_recursion_framework._142_two_thinking_modes_for_recursion._1_problem_decomposition_mode;

// @formatter:off
/**
 * PROBLEM DECOMPOSITION MODE FOR RECURSION
 *
 * Key Concepts:
 * 1. Problem decomposition is a powerful mental model for recursive algorithms
 * 2. Each node in the recursion tree represents a subproblem
 * 3. The solution to the original problem is built from solutions to smaller subproblems
 * 4. This approach follows a "divide and conquer" strategy
 *
 * Critical Requirements for Problem Decomposition:
 * 1. The recursive function must have a clear, precise definition
 * 2. The definition must specify:
 *    - What the function parameters represent
 *    - What result the function returns
 * 3. The recursive calls must use this definition to solve subproblems
 * 4. The original problem's solution is computed from subproblem solutions
 *
 * Example: Binary Tree Maximum Depth (LeetCode 104)
 * - Clear definition: Given a node, return the maximum depth of the tree rooted at this node
 * - Base case: An empty tree (null node) has depth 0
 * - Recursive formula: maxDepth(root) = max(maxDepth(root.left), maxDepth(root.right)) + 1
 */
public class ProblemDecompositionMode {
    /**
     * Visualization of the recursion tree for maxDepth calculation
     *
     * For input tree:
     *        3
     *       / \
     *      9  20
     *        /  \
     *       15   7
     *
     * Recursion tree (values represent the returned maxDepth at each node):
     *        3 (3)
     *       /   \
     *   9 (1)   20 (2)
     *          /  \
     *      15 (1)  7 (1)
     *      /  \    /  \
     *  null(0) null(0) null(0) null(0)
     *
     * Calculation process:
     * 1. maxDepth(3) calls maxDepth(9) and maxDepth(20)
     * 2. maxDepth(9) calls maxDepth(null) twice, both return 0, so maxDepth(9) = max(0,0) + 1 = 1
     * 3. maxDepth(20) calls maxDepth(15) and maxDepth(7)
     * 4. Both maxDepth(15) and maxDepth(7) return 1 (same logic as for node 9)
     * 5. So maxDepth(20) = max(1,1) + 1 = 2
     * 6. Finally, maxDepth(3) = max(1,2) + 1 = 3
     */

    /**
     * Comparison with Fibonacci to highlight problem decomposition pattern:
     *
     * Fibonacci recursion:
     * - Clear definition: fib(n) returns the nth Fibonacci number
     * - Base case: fib(0) = 0, fib(1) = 1
     * - Decomposition: fib(n) = fib(n-1) + fib(n-2)
     *
     * maxDepth recursion:
     * - Clear definition: maxDepth(root) returns max depth of tree rooted at 'root'
     * - Base case: maxDepth(null) = 0
     * - Decomposition: maxDepth(root) = max(maxDepth(root.left), maxDepth(root.right)) + 1
     *
     * Both follow the same pattern:
     * 1. Decompose problem into smaller subproblems
     * 2. Solve subproblems recursively
     * 3. Combine solutions to subproblems to get solution to original problem
     */

    /**
     * Demonstration of the maxDepth algorithm
     */
    public static void main(String[] args) {
        ProblemDecompositionMode solution = new ProblemDecompositionMode();

        // Create the example tree from LeetCode problem 104
        //        3
        //       / \
        //      9  20
        //        /  \
        //       15   7
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        int depth = solution.maxDepth(root);
        System.out.println("Maximum depth of the tree: " + depth); // Should output 3

        // Create the second example from LeetCode problem 104
        //      1
        //       \
        //        2
        TreeNode root2 = new TreeNode(1);
        root2.right = new TreeNode(2);

        int depth2 = solution.maxDepth(root2);
        System.out.println("Maximum depth of the second tree: " + depth2); // Should output 2
    }

    /**
     * Problem: Calculate the maximum depth of a binary tree
     *
     * Approach using problem decomposition:
     * 1. Define the function clearly: maxDepth returns the maximum depth of the tree rooted at node 'root'
     * 2. Base case: If root is null, depth is 0
     * 3. Recursive case: Calculate max depth of left subtree and right subtree
     * 4. Combine results: Overall max depth = max(left depth, right depth) + 1
     *
     * Time Complexity: O(n) where n is the number of nodes in the tree
     * Space Complexity: O(h) where h is the height of the tree (recursion stack)
     */
    public int maxDepth(TreeNode root) {
        // Base case: empty tree has depth 0
        if (root == null) {
            return 0;
        }

        // Decompose into subproblems:
        // Calculate the maximum depth of left and right subtrees
        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);

        // Combine solutions to subproblems:
        // The max depth is the greater of the two subtree depths plus 1 (for the root)
        return 1 + Math.max(leftDepth, rightDepth);
    }

    /**
     * Definition for a binary tree node
     */
    public static class TreeNode {
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
