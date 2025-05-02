package org.qinlinj.algoframework._400_binary_tree_algo._420_binary_tree_algo_practice_I._426_combined_thinking_practice;

/**
 * Binary Tree Traversal Applications: Summary
 * <p>
 * This file summarizes different problems that can be solved using binary tree traversal
 * techniques, comparing "divide and conquer" vs "traversal" approaches.
 * <p>
 * Common Pattern Overview:
 * 1. Many binary tree problems can be solved using either approach
 * 2. The traversal approach typically uses global variables or parameters
 * 3. The divide and conquer approach focuses on defining a clear return value
 * 4. Understanding the trade-offs helps select the most appropriate technique
 * <p>
 * Key Techniques:
 * <p>
 * 1. Path-Related Problems:
 * - Traversal with path accumulation
 * - Backtracking to explore all possible paths
 * - Sum tracking along paths
 * - Path collection and filtering
 * <p>
 * 2. Tree Transformation:
 * - In-place modification
 * - Creating new structures
 * - Merging multiple trees
 * - Rearranging nodes while preserving values
 * <p>
 * 3. Tree Search:
 * - Finding specific nodes or values
 * - Range queries and filtering
 * - Leveraging BST properties
 * - Handling node references vs values
 * <p>
 * 4. Special Tree Types:
 * - N-ary trees - generalizing binary tree concepts
 * - Binary Search Trees - utilizing ordering properties
 * - Complete Binary Trees - exploiting structural properties
 * <p>
 * Comparison of Approaches:
 * <p>
 * 1. Traversal Approach
 * - Typically uses global variables or parameters
 * - Often uses pre-order traversal with state tracking
 * - Good for problems requiring path tracking
 * - May be more intuitive for some developers
 * <p>
 * 2. Divide and Conquer Approach
 * - Focuses on defining return values clearly
 * - Often uses post-order traversal (solve subtrees before parent)
 * - Good for problems with clear subproblem structure
 * - May lead to more elegant, functional-style code
 */
public class _426_h_BinaryTreeTraversalApplicationSummary {
    // Definition for a binary tree node
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
    }

    /**
     * Example of traversal approach for path sum
     * Uses global variables and pre-order traversal with backtracking
     */
    private class TraversalExample {
        private int targetSum;
        private boolean foundPath = false;
        private int currentSum = 0;

        public boolean hasPathSum(TreeNode root, int targetSum) {
            this.targetSum = targetSum;
            traverse(root);
            return foundPath;
        }

        private void traverse(TreeNode node) {
            if (node == null || foundPath) {
                return;
            }

            // Pre-order position: add to path
            currentSum += node.val;

            // Check if leaf node and sum matches target
            if (node.left == null && node.right == null && currentSum == targetSum) {
                foundPath = true;
            }

            // Traverse children
            traverse(node.left);
            traverse(node.right);

            // Post-order position: backtrack
            currentSum -= node.val;
        }
    }

    /**
     * Example of divide and conquer approach for path sum
     * Clearly defines return value and uses recursive composition
     */
    private class DivideAndConquerExample {
        public boolean hasPathSum(TreeNode root, int targetSum) {
            if (root == null) {
                return false;
            }

            // If leaf node, check if value matches target
            if (root.left == null && root.right == null) {
                return root.val == targetSum;
            }

            // Check if either subtree has a path with remaining sum
            return hasPathSum(root.left, targetSum - root.val) ||
                    hasPathSum(root.right, targetSum - root.val);
        }
    }

    /**
     * Example of a tree transformation problem solved with divide and conquer
     * Creates a flattened tree structure
     */
    private class TreeTransformationExample {
        public TreeNode increasingBST(TreeNode root) {
            if (root == null) {
                return null;
            }

            // First, flatten left and right subtrees
            TreeNode leftFlattened = increasingBST(root.left);
            TreeNode rightFlattened = increasingBST(root.right);

            // Disconnect left child
            root.left = null;
            // Connect right subtree
            root.right = rightFlattened;

            // If no left subtree, return current root as the head
            if (leftFlattened == null) {
                return root;
            }

            // Find the rightmost node in the left flattened tree
            TreeNode current = leftFlattened;
            while (current.right != null) {
                current = current.right;
            }

            // Connect current root to the end of left flattened tree
            current.right = root;

            // Return the head of the left flattened tree
            return leftFlattened;
        }
    }

    /**
     * Example of a search problem using BST properties
     * Efficiently sums values in a range
     */
    private class TreeSearchExample {
        public int rangeSumBST(TreeNode root, int low, int high) {
            if (root == null) {
                return 0;
            }

            // If current value is below range, only search right subtree
            if (root.val < low) {
                return rangeSumBST(root.right, low, high);
            }

            // If current value is above range, only search left subtree
            if (root.val > high) {
                return rangeSumBST(root.left, low, high);
            }

            // If current value is in range, include it and search both subtrees
            return root.val +
                    rangeSumBST(root.left, low, high) +
                    rangeSumBST(root.right, low, high);
        }
    }

    /**
     * Guidelines for choosing between approaches:
     *
     * 1. Use traversal approach when:
     *    - You need to maintain complex state during traversal
     *    - The problem involves tracking paths
     *    - Backtracking is required
     *    - You're comfortable with using side effects
     *
     * 2. Use divide and conquer approach when:
     *    - The problem can be clearly broken into subproblems
     *    - The return value can be composed from subtree results
     *    - You prefer functional style with minimal side effects
     *    - The problem fits a post-order traversal pattern
     *
     * 3. Sometimes a hybrid approach works best:
     *    - Divide and conquer structure with some global tracking
     *    - Traversal structure that returns important values
     */
}