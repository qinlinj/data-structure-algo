package org.qinlinj.algoframework._400_binary_tree_algo._440_binary_tree_algo_extension._446_stack_simulation_for_binary_tree_traversal;

import java.util.*;

/**
 * Converting Recursive Binary Tree Traversal to Iterative
 * ---------------------------------------------------------
 * <p>
 * This class demonstrates how to convert recursive binary tree traversal algorithms
 * into their iterative counterparts using a stack-based approach.
 * <p>
 * Key insights:
 * 1. A universal framework for converting any recursive tree traversal to iterative
 * 2. Using a stack to simulate the function call stack
 * 3. Identifying pre-order, in-order, and post-order positions in the iterative framework
 * 4. Using a 'visited' pointer to track traversal state
 * 5. Implementation of all three traversal methods using the same framework
 * <p>
 * This approach can be used to convert any recursive binary tree algorithm to an
 * iterative one while preserving the logical flow of the original algorithm.
 */
public class _446_RecursiveToIterativeTreeTraversal {
    TreeNode lastVisited = null;

    /**
     * Demo method to compare recursive and iterative approaches
     */
    public static void main(String[] args) {
        _446_RecursiveToIterativeTreeTraversal solution = new _446_RecursiveToIterativeTreeTraversal();

        // Create a sample tree:
        //       1
        //      / \
        //     2   3
        //    / \   \
        //   4   5   6
        TreeNode root = new TreeNode(1,
                new TreeNode(2,
                        new TreeNode(4), new TreeNode(5)),
                new TreeNode(3,
                        null, new TreeNode(6))
        );

        // For storing traversal results
        List<Integer> recPreorder = new ArrayList<>();
        List<Integer> recInorder = new ArrayList<>();
        List<Integer> recPostorder = new ArrayList<>();

        List<Integer> iterPreorder = new ArrayList<>();
        List<Integer> iterInorder = new ArrayList<>();
        List<Integer> iterPostorder = new ArrayList<>();

        // Execute recursive traversal
        solution.traverseRecursive(root, recPreorder, recInorder, recPostorder);

        // Execute iterative universal framework
        solution.traverseIterative(root, iterPreorder, iterInorder, iterPostorder);

        // Execute specific traversal methods
        List<Integer> specificPreorder = solution.preorderTraversal(root);
        List<Integer> specificInorder = solution.inorderTraversal(root);
        List<Integer> specificPostorder = solution.postorderTraversal(root);

        // Compare and print results
        System.out.println("Sample Tree: 1 -> (2 -> (4, 5), 3 -> (null, 6))");

        System.out.println("\nRecursive Pre-order: " + recPreorder);
        System.out.println("Iterative Pre-order (universal): " + iterPreorder);
        System.out.println("Iterative Pre-order (specific): " + specificPreorder);

        System.out.println("\nRecursive In-order: " + recInorder);
        System.out.println("Iterative In-order (universal): " + iterInorder);
        System.out.println("Iterative In-order (specific): " + specificInorder);

        System.out.println("\nRecursive Post-order: " + recPostorder);
        System.out.println("Iterative Post-order (universal): " + iterPostorder);
        System.out.println("Iterative Post-order (specific): " + specificPostorder);

        // Demonstrate applying the framework to a custom problem
        System.out.println("\nDemonstrating a custom problem: Sum of all node values");
        int recursiveSum = solution.sumValuesRecursive(root);
        int iterativeSum = solution.sumValuesIterative(root);
        System.out.println("Recursive sum: " + recursiveSum);
        System.out.println("Iterative sum: " + iterativeSum);
    }

    /**
     * The recursive approach for traversing binary trees
     * Shows the traditional recursive framework with pre-order, in-order, and post-order positions
     */
    public void traverseRecursive(TreeNode root, List<Integer> preorder, List<Integer> inorder, List<Integer> postorder) {
        if (root == null) return;

        // Pre-order position (before traversing left and right subtrees)
        preorder.add(root.val);

        // Traverse left subtree
        traverseRecursive(root.left, preorder, inorder, postorder);

        // In-order position (after left subtree, before right subtree)
        inorder.add(root.val);

        // Traverse right subtree
        traverseRecursive(root.right, preorder, inorder, postorder);

        // Post-order position (after traversing both subtrees)
        postorder.add(root.val);
    }

    /**
     * Universal iterative framework that can handle all three traversal orders
     * This is the primary contribution of the class - a unified approach to iterative traversal
     */
    public void traverseIterative(TreeNode root, List<Integer> preorder, List<Integer> inorder, List<Integer> postorder) {
        // Edge case: empty tree
        if (root == null) return;

        // Stack to simulate function call stack
        Stack<TreeNode> stack = new Stack<>();

        // Tracks the last fully processed node (for determining traversal state)
        TreeNode visited = new TreeNode(-1); // Special value to avoid conflicts

        // Start by pushing all left nodes onto the stack
        pushLeftBranch(root, stack, preorder);

        while (!stack.isEmpty()) {
            TreeNode p = stack.peek();

            // If left subtree is not processed yet
            if (p.left != null && lastVisited != p.left && lastVisited != p.right) {
                pushLeftBranch(p.left, stack, preorder);
            }
            // If left is done, but right is not
            else if (p.right != null && lastVisited != p.right) {
                inorder.add(p.val);
                pushLeftBranch(p.right, stack, preorder);
            }
            // If both subtrees are done
            else {
                postorder.add(p.val);
                lastVisited = p;
                stack.pop();

                // If this was a right child, also need to add inorder for parent
                if (stack.isEmpty() || p != stack.peek().right) {
                    inorder.add(p.val);
                }
            }
        }
    }

    /**
     * Helper method to push all nodes along the left branch onto the stack
     * This implements the initial downward traversal along left children
     */
    private void pushLeftBranch(TreeNode p, Stack<TreeNode> stack, List<Integer> preorder) {
        while (p != null) {
            // Pre-order position - as soon as we visit a node
            preorder.add(p.val);

            // Push current node to stack
            stack.push(p);

            // Move to left child
            p = p.left;
        }
    }

    /**
     * Specific implementation for pre-order traversal
     * Simplified from the universal framework
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();

            // Pre-order: process node first
            result.add(node.val);

            // Push right first so that left is processed first (stack is LIFO)
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }

        return result;
    }

    /**
     * Specific implementation for in-order traversal
     * Simplified from the universal framework
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;

        Stack<TreeNode> stack = new Stack<>();
        TreeNode current = root;

        while (current != null || !stack.isEmpty()) {
            // Go all the way to the left
            while (current != null) {
                stack.push(current);
                current = current.left;
            }

            // Process node when all left children are processed
            current = stack.pop();
            result.add(current.val);  // In-order position

            // Move to right subtree
            current = current.right;
        }

        return result;
    }

    /**
     * Specific implementation for post-order traversal
     * Directly using the universal framework
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;

        Stack<TreeNode> stack = new Stack<>();
        TreeNode visited = new TreeNode(-1);

        // Start by pushing all left nodes
        pushLeftBranchPostorder(root, stack);

        while (!stack.isEmpty()) {
            TreeNode p = stack.peek();

            // If right child exists and hasn't been visited yet
            if (p.right != null && p.right != visited) {
                pushLeftBranchPostorder(p.right, stack);
            } else {
                // Process node after both subtrees
                result.add(p.val);  // Post-order position
                visited = stack.pop();
            }
        }

        return result;
    }

    /**
     * Helper method for post-order traversal
     */
    private void pushLeftBranchPostorder(TreeNode p, Stack<TreeNode> stack) {
        while (p != null) {
            stack.push(p);
            p = p.left;
        }
    }

    /**
     * Example of a recursive tree algorithm: Calculate sum of all node values
     */
    public int sumValuesRecursive(TreeNode root) {
        if (root == null) return 0;

        // Pre-order position: Can perform operations before subtree traversal
        int currentVal = root.val;

        // Traverse left and right subtrees
        int leftSum = sumValuesRecursive(root.left);
        int rightSum = sumValuesRecursive(root.right);

        // Post-order position: Combine results after subtree traversal
        return currentVal + leftSum + rightSum;
    }

    /**
     * Iterative version of the sum algorithm using our universal framework
     */
    public int sumValuesIterative(TreeNode root) {
        if (root == null) return 0;

        int sum = 0;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode visited = new TreeNode(-1);

        // Push all left nodes
        TreeNode current = root;
        while (current != null) {
            // No operation in pre-order position for this algorithm
            stack.push(current);
            current = current.left;
        }

        while (!stack.isEmpty()) {
            TreeNode p = stack.peek();

            if ((p.left == null || p.left == visited) && p.right != visited) {
                // No operation in in-order position for this algorithm

                // Start traversing right subtree
                current = p.right;
                while (current != null) {
                    stack.push(current);
                    current = current.left;
                }
            } else if (p.right == null || p.right == visited) {
                // Post-order position: Add node value to sum
                sum += p.val;
                visited = stack.pop();
            }
        }

        return sum;
    }

    /**
     * Definition for a binary tree node
     */
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }

        // Utility constructor for creating test trees
        TreeNode(int x, TreeNode left, TreeNode right) {
            val = x;
            this.left = left;
            this.right = right;
        }
    }
}