package org.qinlinj.algoframework._400_binary_tree_algo._420_binary_tree_algo_practice_I._423_traversal_thinking_practice_III;

import java.util.*;

/**
 * Advanced Binary Tree Techniques: Summary and Patterns
 * <p>
 * This file summarizes advanced techniques for solving binary tree problems
 * that combine tree traversal with other algorithmic approaches.
 * <p>
 * Common Pattern Overview:
 * 1. Binary tree problems often combine tree traversal with other techniques
 * 2. The core "traversal" pattern remains the foundation for these problems
 * 3. Additional data structures and algorithms are used to enhance tree operations
 * 4. Problems may require nested traversals or multi-phase approaches
 * <p>
 * Key Advanced Techniques:
 * <p>
 * 1. Prefix Sum in Binary Trees:
 * - Track path sums using a HashMap to find paths with target sum
 * - Maintain state during traversal with preorder/postorder operations
 * - Example: Path Sum III (#437)
 * <p>
 * 2. Depth/Level Tracking:
 * - Keep track of current depth during traversal
 * - Use depth information for decision-making
 * - Examples: Find Bottom Left Tree Value (#513)
 * <p>
 * 3. Tree Restoration and Transformation:
 * - Restore or transform a tree based on specific rules
 * - Use traversal to systematically apply transformations
 * - Example: Find Elements in a Contaminated Binary Tree (#1261)
 * <p>
 * 4. Tree Representation as Other Structures:
 * - View a tree as another data structure (e.g., multi-way tree, heap)
 * - Apply techniques specific to that structure
 * - Examples: Lexicographical Numbers (#386), Path in Zigzag Labeled Binary Tree (#1104)
 * <p>
 * 5. Path Finding in Trees:
 * - Find paths between specific nodes
 * - Track paths and convert to required format
 * - Example: Step-by-Step Directions from Binary Tree Node to Another (#2096)
 * <p>
 * 6. Tree Comparison and Pattern Matching:
 * - Compare subtrees for equality or pattern matching
 * - Use nested traversals to check all possible starting points
 * - Examples: Subtree of Another Tree (#572), Linked List in Binary Tree (#1367)
 * <p>
 * 7. Game Theory on Trees:
 * - Apply strategic thinking to tree structures
 * - Calculate optimal moves using tree properties
 * - Example: Binary Tree Coloring Game (#1145)
 * <p>
 * Cross-Cutting Implementation Strategies:
 * <p>
 * 1. Helper Methods:
 * - Break down complex logic into simpler helper methods
 * - Use helper methods for specialized traversals or checks
 * <p>
 * 2. State Maintenance:
 * - Use fields to track global state
 * - Carefully manage state changes during traversal
 * <p>
 * 3. Efficient Data Structures:
 * - Choose appropriate data structures for specific operations
 * - HashMaps for lookup, StringBuilders for path construction, etc.
 * <p>
 * 4. Algorithm Combinations:
 * - Combine multiple algorithms to solve complex problems
 * - Integrate search, dynamic programming, or other techniques with tree traversal
 */
public class _423_j_AdvancedBinaryTreeTechniquesSummary {
    /**
     * Example of combining prefix sum technique with tree traversal
     * Similar to Problem 437: Path Sum III
     */
    public int pathSumExample(TreeNode root, int targetSum) {
        HashMap<Long, Integer> prefixSumCount = new HashMap<>();
        prefixSumCount.put(0L, 1); // Initialize with sum 0 having count 1

        return pathSumHelper(root, 0, targetSum, prefixSumCount);
    }

    private int pathSumHelper(TreeNode node, long currentSum, int targetSum,
                              HashMap<Long, Integer> prefixSumCount) {
        if (node == null) {
            return 0;
        }

        // Update current sum with this node's value
        currentSum += node.val;

        // Check how many paths end at current node with sum = targetSum
        int count = prefixSumCount.getOrDefault(currentSum - targetSum, 0);

        // Update prefix sum map
        prefixSumCount.put(currentSum, prefixSumCount.getOrDefault(currentSum, 0) + 1);

        // Recurse on children
        count += pathSumHelper(node.left, currentSum, targetSum, prefixSumCount);
        count += pathSumHelper(node.right, currentSum, targetSum, prefixSumCount);

        // Backtrack - remove current sum from map to avoid affecting parallel paths
        prefixSumCount.put(currentSum, prefixSumCount.get(currentSum) - 1);

        return count;
    }

    /**
     * Example of finding a path between two nodes
     * Similar to Problem 2096: Step-by-Step Directions
     */
    public String findPathExample(TreeNode root, int start, int end) {
        StringBuilder startPath = new StringBuilder();
        StringBuilder endPath = new StringBuilder();

        // Find paths from root to both nodes
        findNodePath(root, start, new StringBuilder(), startPath);
        findNodePath(root, end, new StringBuilder(), endPath);

        // Find common prefix
        int i = 0;
        while (i < startPath.length() && i < endPath.length() &&
                startPath.charAt(i) == endPath.charAt(i)) {
            i++;
        }

        // Build the final path
        StringBuilder result = new StringBuilder();

        // Go up from start node to common ancestor
        for (int j = i; j < startPath.length(); j++) {
            result.append('U');
        }

        // Go down from common ancestor to end node
        result.append(endPath.substring(i));

        return result.toString();
    }

    private boolean findNodePath(TreeNode node, int target,
                                 StringBuilder currentPath, StringBuilder resultPath) {
        if (node == null) {
            return false;
        }

        if (node.val == target) {
            resultPath.append(currentPath);
            return true;
        }

        // Try left path
        currentPath.append('L');
        if (findNodePath(node.left, target, currentPath, resultPath)) {
            return true;
        }
        currentPath.deleteCharAt(currentPath.length() - 1);

        // Try right path
        currentPath.append('R');
        if (findNodePath(node.right, target, currentPath, resultPath)) {
            return true;
        }
        currentPath.deleteCharAt(currentPath.length() - 1);

        return false;
    }

    /**
     * Example of tree comparison for subtree checking
     * Similar to Problem 572: Subtree of Another Tree
     */
    public boolean isSubtreeExample(TreeNode root, TreeNode subRoot) {
        if (root == null) {
            return subRoot == null;
        }

        // Check if current tree matches
        if (isSameTree(root, subRoot)) {
            return true;
        }

        // Check subtrees
        return isSubtreeExample(root.left, subRoot) ||
                isSubtreeExample(root.right, subRoot);
    }

    private boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        if (p == null || q == null) return false;
        if (p.val != q.val) return false;

        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    // Definition for a binary tree node
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
    }
}
