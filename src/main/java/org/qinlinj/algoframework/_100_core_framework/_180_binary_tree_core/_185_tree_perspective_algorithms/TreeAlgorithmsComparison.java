package org.qinlinj.algoframework._100_core_framework._180_binary_tree_core._185_tree_perspective_algorithms;

import java.util.*;

/**
 * UNDERSTANDING DYNAMIC PROGRAMMING, BACKTRACKING AND DFS FROM A TREE PERSPECTIVE
 * ===============================================================================
 * <p>
 * This class demonstrates how dynamic programming, backtracking, and DFS algorithms
 * can all be viewed as extensions of binary tree algorithms, with each focusing on
 * different aspects:
 * <p>
 * 1. DYNAMIC PROGRAMMING:
 * - Uses a "problem decomposition" approach (divide and conquer)
 * - Focus is on entire "subtrees"
 * - Builds solutions by combining results from subproblems
 * - Often uses return values to propagate solutions upward
 * <p>
 * 2. BACKTRACKING:
 * - Uses a "traversal" approach
 * - Focus is on the "branches" between nodes
 * - Makes choices along paths and backtracks when needed
 * - "Choose" and "unchoose" operations happen inside for-loops
 * <p>
 * 3. DEPTH-FIRST SEARCH (DFS):
 * - Uses a "traversal" approach
 * - Focus is on individual "nodes"
 * - Performs operations on each node during traversal
 * - "Choose" and "unchoose" operations happen outside for-loops
 * <p>
 * Understanding these distinctions helps choose the right approach for different problems
 * and clarifies why implementation details vary between these related algorithms.
 */

public class TreeAlgorithmsComparison {
    // =====================================================
    // BINARY TREE NODE DEFINITION
    // =====================================================

    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    // =====================================================
    // N-ARY TREE NODE DEFINITION (FOR MULTI-WAY BRANCHING)
    // =====================================================

    public static class Node {
        int val;
        List<Node> children;

        Node(int x) {
            val = x;
            children = new ArrayList<>();
        }
    }
}
