package org.qinlinj.algoframework._400_binary_tree_algo._420_binary_tree_algo_practice_I._424_problem_decomposition_thinking_practice_I;

import java.util.*;

/**
 * Problem 1110: Delete Nodes And Return Forest
 * <p>
 * Description:
 * Given the root of a binary tree, each node in the tree has a distinct value.
 * After deleting all nodes with a value in to_delete, we are left with a forest
 * (a disjoint union of trees).
 * <p>
 * Return the roots of the trees in the remaining forest. You may return the result in any order.
 * <p>
 * Key Concepts:
 * - Uses the "divide and conquer" approach for tree modification
 * - Performs post-order traversal to properly handle parent-child relationships
 * - Maintains a set of nodes to delete for efficient lookup
 * - Collects new root nodes when a node becomes the root of a separate tree
 * - Passes information about parent status through function parameters
 * <p>
 * Time Complexity: O(n), where n is the number of nodes in the tree
 * Space Complexity: O(n + d), where d is the size of to_delete array
 */
public class _424_g_DeleteNodesAndReturnForest {
    // Set to quickly check if a node should be deleted
    private Set<Integer> deleteSet = new HashSet<>();
    // List to store roots of the resulting forest
    private List<TreeNode> forestRoots = new LinkedList<>();

    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        // Handle empty tree
        if (root == null) {
            return new LinkedList<>();
        }

        // Initialize set of nodes to delete
        for (int val : to_delete) {
            deleteSet.add(val);
        }

        // Process the tree, starting with root as a potential forest root
        processTree(root, true);

        return forestRoots;
    }

    /**
     * Processes a binary tree, deleting nodes and collecting forest roots
     *
     * @param node   The current node being processed
     * @param isRoot Indicates if the current node is a potential root of a tree in the forest
     * @return The updated node after processing, or null if this node should be deleted
     */
    private TreeNode processTree(TreeNode node, boolean isRoot) {
        if (node == null) {
            return null;
        }

        // Check if current node should be deleted
        boolean shouldDelete = deleteSet.contains(node.val);

        // If this is a root node and it's not being deleted, add it to our forest
        if (isRoot && !shouldDelete) {
            forestRoots.add(node);
        }

        // Process left and right subtrees
        // If current node is being deleted, its children become new roots
        node.left = processTree(node.left, shouldDelete);
        node.right = processTree(node.right, shouldDelete);

        // If this node should be deleted, return null to remove it from the parent's reference
        return shouldDelete ? null : node;
    }

    /**
     * Alternative implementation using an explicitly defined parent parameter
     */
    public List<TreeNode> delNodesAlternative(TreeNode root, int[] to_delete) {
        List<TreeNode> result = new ArrayList<>();
        Set<Integer> toDeleteSet = new HashSet<>();

        for (int val : to_delete) {
            toDeleteSet.add(val);
        }

        // If root is not deleted, add it to result
        if (!toDeleteSet.contains(root.val)) {
            result.add(root);
        }

        processTreeWithParent(root, null, toDeleteSet, result);

        return result;
    }

    /**
     * Helper method that explicitly keeps track of the parent node
     */
    private void processTreeWithParent(TreeNode node, TreeNode parent,
                                       Set<Integer> toDeleteSet, List<TreeNode> result) {
        if (node == null) {
            return;
        }

        // Process left and right first (post-order traversal)
        processTreeWithParent(node.left, node, toDeleteSet, result);
        processTreeWithParent(node.right, node, toDeleteSet, result);

        // Check if current node should be deleted
        if (toDeleteSet.contains(node.val)) {
            // Add non-null children as new trees to the forest
            if (node.left != null) {
                result.add(node.left);
            }
            if (node.right != null) {
                result.add(node.right);
            }

            // Disconnect this node from its parent
            if (parent != null) {
                if (parent.left == node) {
                    parent.left = null;
                } else {
                    parent.right = null;
                }
            }
        }
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