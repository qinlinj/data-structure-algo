package org.qinlinj.algoframework._400_binary_tree_algo._430_binary_tree_algo_practice_II._431_postorder_position_practice_I;

import java.util.*;

/**
 * Post-Order Traversal Applications: Summary
 * <p>
 * This file summarizes various applications of post-order traversal in binary tree problems,
 * highlighting techniques and patterns for effectively using this traversal order.
 * <p>
 * Common Pattern Overview:
 * 1. Post-order traversal is particularly powerful for bottom-up operations
 * 2. The traversal order (left, right, root) allows child results to inform parent decisions
 * 3. Many tree modification, validation, and calculation problems can leverage post-order patterns
 * 4. Post-order position is key for determining node properties that depend on subtree properties
 * <p>
 * Key Techniques:
 * <p>
 * 1. Tree Property Validation:
 * - Checking balanced property (height differences)
 * - Validating BST properties
 * - Ensuring paths have certain characteristics
 * <p>
 * 2. Tree Calculations:
 * - Computing subtree sums, averages, or counts
 * - Calculating node-specific properties like tilt
 * - Finding frequency-based information
 * <p>
 * 3. Tree Modification:
 * - Pruning nodes based on subtree properties
 * - Removing specific types of nodes
 * - Restructuring tree connections
 * <p>
 * 4. Implementation Strategies:
 * - Using global variables vs. return values
 * - Combining multiple calculations in a single traversal
 * - Using helper classes to track multiple values
 */
public class _431_f_PostOrderTraversalApplicationsSummary {
    /**
     * Example 1: Validating a balanced tree
     * Shows how post-order traversal can efficiently check tree properties
     */
    public boolean isBalanced(TreeNode root) {
        return checkHeight(root) != -1;
    }

    /**
     * Helper function for balance validation
     * Returns height if balanced, -1 if unbalanced
     */
    private int checkHeight(TreeNode node) {
        if (node == null) {
            return 0;
        }

        // Get heights of left and right subtrees
        int leftHeight = checkHeight(node.left);
        if (leftHeight == -1) return -1; // Left subtree is unbalanced

        int rightHeight = checkHeight(node.right);
        if (rightHeight == -1) return -1; // Right subtree is unbalanced

        // Post-order position: check if current node is balanced
        if (Math.abs(leftHeight - rightHeight) > 1) {
            return -1; // Current node is unbalanced
        }

        // Return height of current subtree
        return 1 + Math.max(leftHeight, rightHeight);
    }

    /**
     * Example 2: Calculating subtree sum and tilt
     * Shows how to combine multiple calculations in one traversal
     */
    public int findTilt(TreeNode root) {
        int[] totalTilt = {0}; // Array to simulate pass by reference
        sumAndTilt(root, totalTilt);
        return totalTilt[0];
    }

    /**
     * Helper function for tilt calculation
     * Returns subtree sum while updating tilt
     */
    private int sumAndTilt(TreeNode node, int[] totalTilt) {
        if (node == null) {
            return 0;
        }

        // Get sums of left and right subtrees
        int leftSum = sumAndTilt(node.left, totalTilt);
        int rightSum = sumAndTilt(node.right, totalTilt);

        // Post-order position: calculate tilt and update total
        totalTilt[0] += Math.abs(leftSum - rightSum);

        // Return sum of current subtree
        return node.val + leftSum + rightSum;
    }

    /**
     * Example 3: Tree pruning
     * Shows how post-order traversal can modify tree structure
     */
    public TreeNode pruneTree(TreeNode root) {
        if (root == null) {
            return null;
        }

        // Process children first
        root.left = pruneTree(root.left);
        root.right = pruneTree(root.right);

        // Post-order position: decide if current node should be pruned
        if (root.val == 0 && root.left == null && root.right == null) {
            return null; // Prune this node
        }

        return root;
    }

    /**
     * Example of using NodeInfo to compute multiple properties at once
     */
    private NodeInfo calculateNodeInfo(TreeNode node, int target) {
        if (node == null) {
            return new NodeInfo(0, 0, Integer.MIN_VALUE, false);
        }

        // Process left and right subtrees
        NodeInfo leftInfo = calculateNodeInfo(node.left, target);
        NodeInfo rightInfo = calculateNodeInfo(node.right, target);

        // Post-order position: combine results
        int sum = node.val + leftInfo.sum + rightInfo.sum;
        int count = 1 + leftInfo.count + rightInfo.count;
        int max = Math.max(node.val, Math.max(leftInfo.max, rightInfo.max));
        boolean containsTarget = (node.val == target) ||
                leftInfo.containsTarget ||
                rightInfo.containsTarget;

        return new NodeInfo(sum, count, max, containsTarget);
    }

    /**
     * Example 5: Finding most frequent subtree sum
     * Shows how post-order traversal can track frequencies
     */
    public int[] findFrequentTreeSum(TreeNode root) {
        Map<Integer, Integer> sumFrequencies = new HashMap<>();
        calculateSubtreeSum(root, sumFrequencies);

        // Find maximum frequency
        int maxFreq = 0;
        for (int freq : sumFrequencies.values()) {
            maxFreq = Math.max(maxFreq, freq);
        }

        // Collect sums with maximum frequency
        List<Integer> result = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : sumFrequencies.entrySet()) {
            if (entry.getValue() == maxFreq) {
                result.add(entry.getKey());
            }
        }

        // Convert to array
        return result.stream().mapToInt(i -> i).toArray();
    }

    /**
     * Helper method for finding subtree sums
     */
    private int calculateSubtreeSum(TreeNode node, Map<Integer, Integer> sumFrequencies) {
        if (node == null) {
            return 0;
        }

        // Calculate subtree sums
        int leftSum = calculateSubtreeSum(node.left, sumFrequencies);
        int rightSum = calculateSubtreeSum(node.right, sumFrequencies);
        int totalSum = node.val + leftSum + rightSum;

        // Post-order position: update frequency map
        sumFrequencies.put(totalSum, sumFrequencies.getOrDefault(totalSum, 0) + 1);

        return totalSum;
    }

    // Definition for a binary tree node
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
    }

    /**
     * Example 4: Using a helper class for complex calculations
     * Shows how to track multiple values during traversal
     */
    private class NodeInfo {
        int sum;      // Sum of node values in subtree
        int count;    // Count of nodes in subtree
        int max;      // Maximum value in subtree
        boolean containsTarget; // Whether subtree contains a target value

        NodeInfo(int sum, int count, int max, boolean containsTarget) {
            this.sum = sum;
            this.count = count;
            this.max = max;
            this.containsTarget = containsTarget;
        }
    }

    /**
     * Key Insights for Post-Order Traversal Applications:
     *
     * 1. When to use post-order traversal:
     *    - When you need information from subtrees to make decisions about parent nodes
     *    - For bottom-up computations where child results inform parent calculations
     *    - For tree transformations where structure changes depend on subtree properties
     *
     * 2. Common post-order patterns:
     *    - Compute and return: Calculate properties, return to parent
     *    - Compute and modify: Calculate properties, modify tree structure
     *    - Compute and track: Calculate properties, track in global state
     *
     * 3. Benefits of post-order approach:
     *    - Efficient single-pass algorithms
     *    - Clean separation of concerns
     *    - Natural alignment with recursive tree structure
     *    - Effective for transformations that must process leaves first
     */
}
