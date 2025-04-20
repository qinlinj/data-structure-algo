package org.qinlinj.algoframework._100_core_framework._180_binary_tree_core._183_two_solution_approaches;


import java.util.*;

/**
 * TWO APPROACHES TO SOLVING BINARY TREE PROBLEMS
 * ==============================================
 * <p>
 * As mentioned in "My Algorithm Learning Experience," binary tree recursion solutions can be divided into two approaches:
 * <p>
 * 1. TRAVERSAL APPROACH:
 * - Traverse the entire tree once to calculate the answer
 * - Similar to the backtracking algorithm framework
 * - Function signature typically uses `void traverse(...)` with no return value
 * - Updates external variables to accumulate results
 * <p>
 * 2. PROBLEM DECOMPOSITION APPROACH:
 * - Calculate the answer by breaking down the problem into subproblems (subtrees)
 * - Similar to the dynamic programming framework
 * - Function is named according to its specific purpose and generally has a return value
 * - Return value represents the calculation result of the subproblem
 * <p>
 * The function naming convention highlights the solution's thought pattern:
 * - Functions without return values (void) indicate a traversal approach
 * - Functions with return values indicate a problem decomposition approach
 * <p>
 * For any binary tree problem, consider:
 * 1. Can you solve it by traversing the tree once? If yes, use a traverse function with external variables.
 * 2. Can you define a recursive function that solves the original problem using solutions to subproblems?
 * If yes, clearly define this function and use its return values effectively.
 * 3. Regardless of which approach you use, understand what each node needs to do and when (pre/in/post order).
 */
public class BinaryTreeProblemSolving {
    // =====================================================
    // EXAMPLE 1: MAXIMUM DEPTH OF BINARY TREE (LEETCODE 104)
    // =====================================================

    // APPROACH 1: TRAVERSAL METHOD
    public int maxDepthTraversal(TreeNode root) {
        // External variables to track state during traversal
        int[] depth = new int[1]; // Current depth
        int[] maxDepth = new int[1]; // Maximum depth found

        traverse(root, depth, maxDepth);
        return maxDepth[0];
    }

    private void traverse(TreeNode root, int[] depth, int[] maxDepth) {
        if (root == null) {
            return;
        }

        // Pre-order position - entering node, increase depth
        depth[0]++;

        // If we're at a leaf node, update the max depth if needed
        if (root.left == null && root.right == null) {
            maxDepth[0] = Math.max(maxDepth[0], depth[0]);
        }

        // Continue traversing
        traverse(root.left, depth, maxDepth);
        traverse(root.right, depth, maxDepth);

        // Post-order position - leaving node, decrease depth
        depth[0]--;
    }

    // APPROACH 2: PROBLEM DECOMPOSITION METHOD
    public int maxDepthDecomposition(TreeNode root) {
        // Base case
        if (root == null) {
            return 0;
        }

        // Calculate max depth of left and right subtrees
        int leftMax = maxDepthDecomposition(root.left);
        int rightMax = maxDepthDecomposition(root.right);

        // Post-order position - combine results from subtrees
        // The tree's max depth = max(left subtree depth, right subtree depth) + 1
        return 1 + Math.max(leftMax, rightMax);
    }

    // =====================================================
    // EXAMPLE 2: BINARY TREE PREORDER TRAVERSAL (LEETCODE 144)
    // =====================================================

    // APPROACH 1: TRAVERSAL METHOD
    public List<Integer> preorderTraversalMethod1(TreeNode root) {
        List<Integer> result = new LinkedList<>();
        preorderTraverse(root, result);
        return result;
    }

    private void preorderTraverse(TreeNode root, List<Integer> result) {
        if (root == null) {
            return;
        }

        // Pre-order position - add current node's value
        result.add(root.val);

        // Continue traversing
        preorderTraverse(root.left, result);
        preorderTraverse(root.right, result);
    }

    // APPROACH 2: PROBLEM DECOMPOSITION METHOD
    public List<Integer> preorderTraversalMethod2(TreeNode root) {
        List<Integer> result = new LinkedList<>();

        // Base case
        if (root == null) {
            return result;
        }

        // Preorder traversal: root node first
        result.add(root.val);

        // Then append left subtree's preorder traversal result
        result.addAll(preorderTraversalMethod2(root.left));

        // Finally append right subtree's preorder traversal result
        result.addAll(preorderTraversalMethod2(root.right));

        return result;
    }

    // Similarly, we can implement inorder and postorder traversals
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new LinkedList<>();

        if (root == null) {
            return result;
        }

        // First append left subtree's inorder traversal
        result.addAll(inorderTraversal(root.left));

        // Then add root's value (in-order position)
        result.add(root.val);

        // Finally append right subtree's inorder traversal
        result.addAll(inorderTraversal(root.right));

        return result;
    }

    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new LinkedList<>();

        if (root == null) {
            return result;
        }

        // First append left subtree's postorder traversal
        result.addAll(postorderTraversal(root.left));

        // Then append right subtree's postorder traversal
        result.addAll(postorderTraversal(root.right));

        // Finally add root's value (post-order position)
        result.add(root.val);

        return result;
    }

    // =====================================================
    // EXAMPLE 3: BINARY TREE PATH SUM (LEETCODE 112)
    // =====================================================

    // APPROACH 1: TRAVERSAL METHOD
    public boolean hasPathSumTraversal(TreeNode root, int targetSum) {
        // External variable to store the result
        boolean[] found = new boolean[1];
        // Current path sum during traversal
        int[] pathSum = new int[1];

        traverseForPathSum(root, targetSum, pathSum, found);
        return found[0];
    }

    private void traverseForPathSum(TreeNode root, int targetSum, int[] pathSum, boolean[] found) {
        // Base case or early return if already found
        if (root == null || found[0]) {
            return;
        }

        // Pre-order position - add current node value to path sum
        pathSum[0] += root.val;

        // Check if it's a leaf node and sum equals target
        if (root.left == null && root.right == null && pathSum[0] == targetSum) {
            found[0] = true;
            return;
        }

        // Continue traversing
        traverseForPathSum(root.left, targetSum, pathSum, found);
        traverseForPathSum(root.right, targetSum, pathSum, found);

        // Post-order position - backtrack by removing current node's value
        pathSum[0] -= root.val;
    }

    // APPROACH 2: PROBLEM DECOMPOSITION METHOD
    public boolean hasPathSumDecomposition(TreeNode root, int targetSum) {
        // Base case
        if (root == null) {
            return false;
        }

        // If leaf node, check if current value equals remaining target
        if (root.left == null && root.right == null) {
            return root.val == targetSum;
        }

        // Reduce the target by current node's value for the subtrees
        int remainingSum = targetSum - root.val;

        // The tree has a path with the sum if either left or right subtree has it
        return hasPathSumDecomposition(root.left, remainingSum) ||
                hasPathSumDecomposition(root.right, remainingSum);
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
