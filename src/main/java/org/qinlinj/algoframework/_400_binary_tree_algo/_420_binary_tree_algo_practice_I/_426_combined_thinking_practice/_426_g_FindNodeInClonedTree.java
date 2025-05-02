package org.qinlinj.algoframework._400_binary_tree_algo._420_binary_tree_algo_practice_I._426_combined_thinking_practice;

import java.util.*;

/**
 * Problem 1379: Find a Corresponding Node of a Binary Tree in a Clone of That Tree
 * <p>
 * Description:
 * Given two binary trees original and cloned, and a reference to a node target in the original tree,
 * return a reference to the same node in the cloned tree.
 * <p>
 * The cloned tree is a copy of the original tree. The original and cloned tree nodes have the same values.
 * <p>
 * Note that you are not allowed to change any of the trees or the target node and the answer must be
 * a reference to a node in the cloned tree.
 * <p>
 * Follow up: What if repeated values exist in the tree? How would your approach change?
 * <p>
 * Key Concepts:
 * - Comparing tree nodes by reference vs value
 * - Can be solved using both "divide and conquer" and "traversal" approaches
 * - Simultaneous traversal of two trees with identical structure
 * <p>
 * Time Complexity: O(n), where n is the number of nodes in the tree
 * Space Complexity: O(h), where h is the height of the tree
 */
public class _426_g_FindNodeInClonedTree {
    /**
     * Solution 1: Traversal Approach
     * <p>
     * In this approach, we:
     * - Simultaneously traverse both trees
     * - When we find the target node in the original tree, return the corresponding node in the cloned tree
     *
     * @param original Root of the original binary tree
     * @param cloned   Root of the cloned binary tree
     * @param target   Reference to a node in the original tree
     * @return Reference to the corresponding node in the cloned tree
     */
    public final TreeNode getTargetCopy(final TreeNode original, final TreeNode cloned, final TreeNode target) {
        // Use wrapper object to store result
        TreeNodeRef result = new TreeNodeRef();

        // Start traversal
        traverse(original, cloned, target, result);

        return result.node;
    }

    /**
     * Helper method for traversal approach
     *
     * @param original Current node in original tree
     * @param cloned   Corresponding node in cloned tree
     * @param target   Target node to find
     * @param result   Wrapper to store the result
     */
    private void traverse(TreeNode original, TreeNode cloned, TreeNode target, TreeNodeRef result) {
        // Base cases
        if (original == null || result.node != null) {
            return;
        }

        // Check if current node is the target node (by reference)
        if (original == target) {
            result.node = cloned;
            return;
        }

        // Recursively traverse left and right subtrees
        traverse(original.left, cloned.left, target, result);
        traverse(original.right, cloned.right, target, result);
    }

    /**
     * Solution 2: Divide and Conquer Approach
     * <p>
     * In this approach, we:
     * - Define a function that returns the corresponding node if found in the subtree
     * - Recursively check left and right subtrees
     *
     * @param original Root of the original binary tree
     * @param cloned   Root of the cloned binary tree
     * @param target   Reference to a node in the original tree
     * @return Reference to the corresponding node in the cloned tree
     */
    public final TreeNode getTargetCopyDivideConquer(final TreeNode original, final TreeNode cloned, final TreeNode target) {
        // Base case: empty tree
        if (original == null) {
            return null;
        }

        // Check if current node is the target
        if (original == target) {
            return cloned;
        }

        // Check left subtree first
        TreeNode leftResult = getTargetCopyDivideConquer(original.left, cloned.left, target);
        if (leftResult != null) {
            return leftResult;
        }

        // If not found in left subtree, check right subtree
        return getTargetCopyDivideConquer(original.right, cloned.right, target);
    }

    /**
     * Solution for follow-up: What if repeated values exist in the tree?
     * <p>
     * If the tree can have repeated values, we can't rely on values to identify the node.
     * We need to find the exact corresponding position in the cloned tree.
     * <p>
     * This solution uses a path encoding approach:
     * 1. First find the path from root to target in the original tree
     * 2. Then follow the same path in the cloned tree
     */
    public final TreeNode getTargetCopyWithRepeatedValues(final TreeNode original, final TreeNode cloned, final TreeNode target) {
        // Find path from root to target in original tree
        List<Character> path = new ArrayList<>();
        findPath(original, target, path);

        // Follow the path in cloned tree
        return followPath(cloned, path);
    }

    /**
     * Find path from root to target node
     * Encodes left child as 'L' and right child as 'R'
     *
     * @param node   Current node in original tree
     * @param target Target node to find
     * @param path   List to store the path
     * @return true if target is found, false otherwise
     */
    private boolean findPath(TreeNode node, TreeNode target, List<Character> path) {
        if (node == null) {
            return false;
        }

        if (node == target) {
            return true;
        }

        // Try left subtree
        path.add('L');
        if (findPath(node.left, target, path)) {
            return true;
        }
        path.remove(path.size() - 1);

        // Try right subtree
        path.add('R');
        if (findPath(node.right, target, path)) {
            return true;
        }
        path.remove(path.size() - 1);

        return false;
    }

    /**
     * Follow the path in cloned tree to find corresponding node
     *
     * @param node Current node in cloned tree
     * @param path Path to follow
     * @return Reference to the corresponding node
     */
    private TreeNode followPath(TreeNode node, List<Character> path) {
        TreeNode current = node;

        for (char direction : path) {
            if (direction == 'L') {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        return current;
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

    /**
     * Helper class to hold reference to a TreeNode
     * Used to pass result back from recursion
     */
    private class TreeNodeRef {
        TreeNode node = null;
    }
}