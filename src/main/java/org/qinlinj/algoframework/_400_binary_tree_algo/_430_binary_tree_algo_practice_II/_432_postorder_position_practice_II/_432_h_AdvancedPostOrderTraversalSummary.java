package org.qinlinj.algoframework._400_binary_tree_algo._430_binary_tree_algo_practice_II._432_postorder_position_practice_II;

import java.util.*;

/**
 * Advanced Post-Order Traversal Applications: Summary
 * <p>
 * This file summarizes advanced techniques for using post-order traversal in binary trees,
 * focusing on complex path finding and advanced tree transformations.
 * <p>
 * Common Pattern Overview:
 * 1. Post-order traversal is powerful for path calculation and tree transformation
 * 2. The bottom-up computation allows efficient property calculation and modification
 * 3. Complex problems often require special result types to track multiple values
 * 4. Optimal solutions typically involve subtree information propagation
 * <p>
 * Key Techniques:
 * <p>
 * 1. Path Optimization:
 * - Finding paths with special properties (zigzag, same value, etc.)
 * - Tracking directional information along paths
 * - Combining path segments to form complete paths
 * - Computing optimal path metrics
 * <p>
 * 2. Lowest Common Ancestor (LCA) Variants:
 * - Finding LCA of nodes with specific properties
 * - Computing LCA with additional information
 * - Handling specialized LCA requirements
 * <p>
 * 3. Tree Metrics Computation:
 * - Computing value ranges in subtrees
 * - Finding maximum differences between ancestors and descendants
 * - Calculating optimal split points in trees
 * <p>
 * 4. Advanced Tree Transformations:
 * - Converting trees to different representations
 * - Selective node removal and tree splitting
 * - Reconstructing trees from partial information
 * <p>
 * 5. Multi-Tree Processing:
 * - Working with forests and collections of trees
 * - Converting between tree and graph representations
 * - Handling trees with special connectivity requirements
 */
public class _432_h_AdvancedPostOrderTraversalSummary {
    /**
     * Example: Longest Path with Same Values
     * Demonstrates tracking path information in post-order traversal
     */
    private int longestUnivaluePathExample(TreeNode root) {
        int[] maxLength = {0};
        maxPathLength(root, -1, maxLength);
        return maxLength[0];
    }

    private int maxPathLength(TreeNode node, int parentVal, int[] maxLength) {
        if (node == null) return 0;

        // Process left and right subtrees
        int leftLength = maxPathLength(node.left, node.val, maxLength);
        int rightLength = maxPathLength(node.right, node.val, maxLength);

        // Post-order position: calculate and update maximum path
        maxLength[0] = Math.max(maxLength[0], leftLength + rightLength);

        // Return path length that can extend from this node
        return node.val == parentVal ? Math.max(leftLength, rightLength) + 1 : 0;
    }

    private TreeNode lcaDeepestLeavesExample(TreeNode root) {
        return lcaHelper(root).node;
    }

    private Result lcaHelper(TreeNode node) {
        if (node == null) return new Result(null, 0);

        // Process left and right subtrees
        Result left = lcaHelper(node.left);
        Result right = lcaHelper(node.right);

        // Post-order position: determine result based on subtree depths
        if (left.depth > right.depth) {
            return new Result(left.node, left.depth + 1);
        } else if (right.depth > left.depth) {
            return new Result(right.node, right.depth + 1);
        } else {
            // Equal depths, this node is the LCA
            return new Result(node, left.depth + 1);
        }
    }

    /**
     * Example: Maximum Difference Between Node and Ancestor
     * Demonstrates tracking min/max values in post-order traversal
     */
    private int maxAncestorDiffExample(TreeNode root) {
        return maxDiffHelper(root, root.val, root.val);
    }

    private int maxDiffHelper(TreeNode node, int min, int max) {
        if (node == null) {
            return max - min;  // Return current range
        }

        // Update min and max with current node
        min = Math.min(min, node.val);
        max = Math.max(max, node.val);

        // Process left and right subtrees
        int leftDiff = maxDiffHelper(node.left, min, max);
        int rightDiff = maxDiffHelper(node.right, min, max);

        // Post-order position: return maximum difference
        return Math.max(leftDiff, rightDiff);
    }

    /**
     * Example: Maximum Product After Splitting Tree
     * Demonstrates calculating optimal split points
     */
    private int maxProductExample(TreeNode root) {
        int[] total = {getSum(root)};
        int[] maxProduct = {0};

        findOptimalSplit(root, total[0], maxProduct);

        return maxProduct[0];
    }

    private int getSum(TreeNode node) {
        if (node == null) return 0;
        return node.val + getSum(node.left) + getSum(node.right);
    }

    private int findOptimalSplit(TreeNode node, int total, int[] maxProduct) {
        if (node == null) return 0;

        // Process left and right subtrees
        int leftSum = findOptimalSplit(node.left, total, maxProduct);
        int rightSum = findOptimalSplit(node.right, total, maxProduct);

        // Calculate current subtree sum
        int subtreeSum = node.val + leftSum + rightSum;

        // Post-order position: update maximum product
        maxProduct[0] = Math.max(maxProduct[0],
                (total - subtreeSum) * subtreeSum);

        return subtreeSum;
    }

    /**
     * Example: Zigzag Path Calculation
     * Demonstrates directional path tracking
     */
    private int longestZigzagExample(TreeNode root) {
        int[] result = zigzagHelper(root);
        return result[2];
    }

    // Returns [left path length, right path length, max zigzag length]
    private int[] zigzagHelper(TreeNode node) {
        if (node == null) {
            return new int[]{-1, -1, -1};
        }

        // Process left and right subtrees
        int[] leftResult = zigzagHelper(node.left);
        int[] rightResult = zigzagHelper(node.right);

        // Calculate zigzag paths from current node
        int leftZigzag = rightResult[0] + 1;  // Right then left
        int rightZigzag = leftResult[1] + 1;  // Left then right

        // Calculate maximum zigzag path in this subtree
        int maxZigzag = Math.max(
                Math.max(leftZigzag, rightZigzag),
                Math.max(leftResult[2], rightResult[2])
        );

        // Return all three values
        return new int[]{leftZigzag, rightZigzag, maxZigzag};
    }

    /**
     * Example: Tree to String Conversion
     * Demonstrates string construction in post-order traversal
     */
    private String tree2strExample(TreeNode root) {
        if (root == null) return "";

        // Process left and right subtrees
        String leftStr = tree2strExample(root.left);
        String rightStr = tree2strExample(root.right);

        StringBuilder result = new StringBuilder();
        result.append(root.val);

        // Handle different cases according to rules
        if (root.left != null || root.right != null) {
            result.append("(").append(leftStr).append(")");

            if (root.right != null) {
                result.append("(").append(rightStr).append(")");
            }
        }

        return result.toString();
    }

    /**
     * Example: Collecting Apples in a Tree
     * Demonstrates working with graph representations of trees
     */
    private int minTimeExample(int n, int[][] edges, List<Boolean> hasApple) {
        // Convert edge list to adjacency list
        Map<Integer, List<Integer>> graph = buildGraph(n, edges);

        // Start DFS from node 0
        Set<Integer> visited = new HashSet<>();
        return collectApples(0, graph, hasApple, visited);
    }

    private Map<Integer, List<Integer>> buildGraph(int n, int[][] edges) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i < n; i++) {
            graph.put(i, new ArrayList<>());
        }

        for (int[] edge : edges) {
            int a = edge[0], b = edge[1];
            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        return graph;
    }

    private int collectApples(int node, Map<Integer, List<Integer>> graph,
                              List<Boolean> hasApple, Set<Integer> visited) {
        visited.add(node);
        int totalTime = 0;

        // Visit all unvisited neighbors
        for (int neighbor : graph.get(node)) {
            if (!visited.contains(neighbor)) {
                int childTime = collectApples(neighbor, graph, hasApple, visited);

                // If subtree has apples, add time to visit it
                if (childTime > 0 || hasApple.get(neighbor)) {
                    totalTime += childTime + 2;  // 2 seconds for round trip
                }
            }
        }

        return totalTime;
    }

    // Definition for a binary tree node
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
    }

    /**
     * Example: Finding LCA of Deepest Nodes
     * Demonstrates returning custom result types in post-order traversal
     */
    private class Result {
        TreeNode node;  // LCA node
        int depth;      // Depth of the subtree

        Result(TreeNode node, int depth) {
            this.node = node;
            this.depth = depth;
        }
    }

    /**
     * Key Post-Order Traversal Patterns:
     *
     * 1. Bottom-up Information Gathering:
     *    - Collect information from subtrees
     *    - Combine information in post-order position
     *    - Return processed information to parent
     *
     * 2. Two-Phase Processing:
     *    - First phase: calculate global property (e.g., total sum)
     *    - Second phase: use global property for local calculations
     *
     * 3. Multi-Value Return Types:
     *    - Custom classes to return multiple values
     *    - Arrays with defined meaning for each element
     *    - Combined with state tracking for global optimization
     *
     * 4. State Propagation Techniques:
     *    - Passing state down the tree (top-down)
     *    - Building state up the tree (bottom-up)
     *    - Combining both directions for complex problems
     *
     * 5. Edge Cases Handling:
     *    - Null nodes (leaf boundaries)
     *    - Single node trees
     *    - Trees with specific structures (e.g., linear chains)
     */
}