package org.qinlinj.algoframework._400_binary_tree_algo._420_binary_tree_algo_practice_I._424_problem_decomposition_thinking_practice_I;

import java.util.*;

/**
 * Binary Tree Construction and Modification: Summary and Patterns
 * <p>
 * This file summarizes common patterns and techniques for constructing and modifying
 * binary trees in various algorithmic problems.
 * <p>
 * Common Pattern Overview:
 * 1. Binary tree construction often uses the "divide and conquer" approach
 * 2. Tree traversal results (preorder, inorder, postorder) can be used to reconstruct trees
 * 3. Modification problems require careful handling of parent-child relationships
 * 4. Tree validation often uses properties of the tree structure or traversal patterns
 * <p>
 * Key Techniques:
 * <p>
 * 1. Tree Construction from Traversals:
 * - Using preorder + inorder traversals
 * - Using inorder + postorder traversals
 * - Using preorder + postorder traversals (may have multiple valid solutions)
 * - Key insight: Root identification and subtree partition
 * <p>
 * 2. Tree Property-Based Construction:
 * - Maximum trees (node values greater than all children)
 * - Full binary trees (nodes have 0 or 2 children)
 * - Using mathematical properties to guide construction
 * <p>
 * 3. Tree Modification:
 * - Deletion of nodes (handling the resulting forest)
 * - Insertion of nodes (maintaining tree properties)
 * - Using post-order traversal for deletion to handle dependencies properly
 * <p>
 * 4. Tree Validation:
 * - Checking serialization validity
 * - Verifying tree properties
 * - Using counting techniques (e.g., nodes vs. edges)
 * <p>
 * Common Implementation Strategies:
 * <p>
 * 1. Using HashMaps for O(1) lookups:
 * - Mapping values to indices in traversal arrays
 * - Tracking nodes to delete or modify
 * <p>
 * 2. Recursive Helpers:
 * - Using well-defined recursive helper functions
 * - Clearly defining the base cases and recursive cases
 * <p>
 * 3. Memoization:
 * - Caching results for problems with overlapping subproblems
 * - Efficient for generating all possible trees of a certain type
 * <p>
 * 4. Parent-Child Information Passing:
 * - Passing information about parent nodes via parameters
 * - Using boolean flags to track node status
 */
public class _424_h_BinaryTreeConstructionSummary {
    /**
     * Example of tree construction from traversal arrays
     */
    public TreeNode constructTreeExample(int[] preorder, int[] inorder) {
        // Create a map for quick lookup of inorder indices
        Map<Integer, Integer> inorderMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }

        return buildTree(preorder, 0, preorder.length - 1,
                inorder, 0, inorder.length - 1,
                inorderMap);
    }

    private TreeNode buildTree(int[] preorder, int preStart, int preEnd,
                               int[] inorder, int inStart, int inEnd,
                               Map<Integer, Integer> inorderMap) {
        // Base case
        if (preStart > preEnd) {
            return null;
        }

        // The first element in preorder array is the root
        int rootVal = preorder[preStart];
        TreeNode root = new TreeNode();
        root.val = rootVal;

        // Find the position of root in inorder array
        int rootIndex = inorderMap.get(rootVal);

        // Calculate the size of the left subtree
        int leftSubtreeSize = rootIndex - inStart;

        // Recursively build left and right subtrees
        root.left = buildTree(preorder, preStart + 1, preStart + leftSubtreeSize,
                inorder, inStart, rootIndex - 1,
                inorderMap);

        root.right = buildTree(preorder, preStart + leftSubtreeSize + 1, preEnd,
                inorder, rootIndex + 1, inEnd,
                inorderMap);

        return root;
    }

    /**
     * Example of tree modification (node deletion)
     */
    public List<TreeNode> deleteNodesExample(TreeNode root, int[] toDelete) {
        List<TreeNode> result = new ArrayList<>();
        Set<Integer> deleteSet = new HashSet<>();

        // Convert array to set for O(1) lookup
        for (int val : toDelete) {
            deleteSet.add(val);
        }

        // Process the tree starting from root
        TreeNode processedRoot = processTree(root, true, deleteSet, result);

        // Add the root if it wasn't deleted and hasn't been added yet
        if (processedRoot != null && !deleteSet.contains(root.val)) {
            result.add(processedRoot);
        }

        return result;
    }

    private TreeNode processTree(TreeNode node, boolean isRoot,
                                 Set<Integer> deleteSet, List<TreeNode> result) {
        if (node == null) {
            return null;
        }

        boolean shouldDelete = deleteSet.contains(node.val);

        // Process children recursively
        node.left = processTree(node.left, shouldDelete, deleteSet, result);
        node.right = processTree(node.right, shouldDelete, deleteSet, result);

        // If this node should be deleted, its children become new roots
        if (shouldDelete) {
            // Add non-null children to result
            if (node.left != null) {
                result.add(node.left);
            }
            if (node.right != null) {
                result.add(node.right);
            }

            // Return null to remove this node
            return null;
        }

        // If this is a new root and hasn't been processed yet, add it to result
        if (isRoot) {
            result.add(node);
        }

        return node;
    }

    /**
     * Example of validation using node/edge relationship
     */
    public boolean validateSerializationExample(String preorder) {
        // Count available edges (start with 1 for the edge to root)
        int edges = 1;

        // Process each node
        for (String node : preorder.split(",")) {
            // Before processing a node, we need an available edge
            edges--;

            // If at any point we run out of edges, the serialization is invalid
            if (edges < 0) {
                return false;
            }

            // If this is a non-null node, it provides two new edges
            if (!node.equals("#")) {
                edges += 2;
            }
        }

        // Valid serialization should use exactly all available edges
        return edges == 0;
    }

    // Definition for a binary tree node
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
    }
}