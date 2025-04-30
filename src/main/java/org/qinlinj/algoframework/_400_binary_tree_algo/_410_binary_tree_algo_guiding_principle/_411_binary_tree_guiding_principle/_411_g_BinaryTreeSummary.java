package org.qinlinj.algoframework._400_binary_tree_algo._410_binary_tree_algo_guiding_principle._411_binary_tree_guiding_principle;

/**
 * BINARY TREE ALGORITHMS - COMPREHENSIVE SUMMARY
 * =============================================
 * <p>
 * This class provides a comprehensive summary of all the key concepts covered in
 * binary tree algorithms, bringing together the insights from previous classes.
 * <p>
 * Key takeaways:
 * <p>
 * 1. Two Primary Approaches to Binary Tree Problems:
 * - Traversal approach: Using external variables to collect information during traversal
 * - Problem decomposition: Using return values to build solutions from subproblems
 * <p>
 * 2. Traversal Positions and Their Capabilities:
 * - Pre-order position: Code executes when first entering a node
 * - In-order position: Code executes between left and right subtree traversals
 * - Post-order position: Code executes after processing both subtrees
 * - Position choice depends on what information you need access to
 * <p>
 * 3. Post-Order Position Special Importance:
 * - Only position with access to complete information from both subtrees
 * - Essential for problems involving subtree relationships
 * - Used for "bottom-up" computations in binary trees
 * <p>
 * 4. Level-Order Traversal (BFS) for Breadth-First Processing:
 * - Processes tree level by level, using a queue
 * - Useful for shortest path problems and level-based operations
 * <p>
 * 5. Connections to Other Algorithm Paradigms:
 * - Dynamic Programming: Focus on subtrees (problem decomposition)
 * - Backtracking: Focus on tree branches (making/undoing choices)
 * - DFS: Focus on individual nodes (node-specific operations)
 * <p>
 * The framework presented here applies to virtually all binary tree problems,
 * providing a structured approach to solving them efficiently.
 */

import java.util.*;

public class _411_g_BinaryTreeSummary {
    /**
     * SECTION 2: TRAVERSAL APPROACH EXAMPLES
     * Using traversal with external variables to solve problems
     */

    // Example 2.1: Maximum depth using traversal approach
    private int maxDepth = 0;
    private int currentDepth = 0;
    // Example 2.2: Inorder traversal using traversal approach
    private List<Integer> inorderResult = new ArrayList<>();
    // Example 3.2: Tree diameter (maximum path length between any two nodes)
    private int diameter = 0;

    /**
     * Utility method to create a binary tree for demonstration
     */
    public static TreeNode createSampleTree() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
        return root;
    }

    public static void main(String[] args) {
        _411_g_BinaryTreeSummary solution = new _411_g_BinaryTreeSummary();
        TreeNode root = createSampleTree();

        System.out.println("1. Basic Traversal Demonstration:");
        solution.basicTraversalFramework(root);

        System.out.println("\n2. Traversal Approach Examples:");
        System.out.println("Max Depth (Traversal): " + solution.findMaxDepthTraversal(root));
        System.out.println("Inorder Traversal: " + solution.inorderTraversal(root));

        System.out.println("\n3. Problem Decomposition Examples:");
        System.out.println("Max Depth (Decomposition): " + solution.findMaxDepthDecomposition(root));
        System.out.println("Tree Diameter: " + solution.diameterOfBinaryTree(root));

        System.out.println("\n4. Level-Order Traversal:");
        System.out.println(solution.levelOrder(root));

        System.out.println("\n5. Algorithm Paradigm Connections:");
        System.out.println("Sum of Nodes (DP Pattern): " + solution.sumOfNodes(root));

        System.out.println("\nAll Paths (Backtracking Pattern):");
        solution.printAllPaths(root);

        System.out.println("\nIncrement All Nodes (DFS Pattern):");
        solution.incrementAllNodes(root);
        System.out.println("After incrementing, Inorder Traversal: " + solution.inorderTraversal(root));
    }

    /**
     * SECTION 1: BASIC TRAVERSAL FRAMEWORK
     * Shows the foundation of binary tree algorithms - traversal with three positions
     */
    public void basicTraversalFramework(TreeNode root) {
        if (root == null) {
            return;
        }

        // Pre-order position
        System.out.println("Pre-order: " + root.val);

        basicTraversalFramework(root.left);

        // In-order position
        System.out.println("In-order: " + root.val);

        basicTraversalFramework(root.right);

        // Post-order position
        System.out.println("Post-order: " + root.val);
    }

    public int findMaxDepthTraversal(TreeNode root) {
        maxDepth = 0;
        currentDepth = 0;
        maxDepthTraverse(root);
        return maxDepth;
    }

    private void maxDepthTraverse(TreeNode root) {
        if (root == null) {
            return;
        }

        // Pre-order: entering node, increase depth
        currentDepth++;

        // Update max depth at leaf nodes
        if (root.left == null && root.right == null) {
            maxDepth = Math.max(maxDepth, currentDepth);
        }

        maxDepthTraverse(root.left);
        maxDepthTraverse(root.right);

        // Post-order: leaving node, decrease depth
        currentDepth--;
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        inorderResult.clear();
        inorderTraverse(root);
        return inorderResult;
    }

    private void inorderTraverse(TreeNode root) {
        if (root == null) {
            return;
        }

        inorderTraverse(root.left);
        // In-order position
        inorderResult.add(root.val);
        inorderTraverse(root.right);
    }

    /**
     * SECTION 3: PROBLEM DECOMPOSITION EXAMPLES
     * Using return values to build solutions from subproblems
     */

    // Example 3.1: Maximum depth using problem decomposition
    public int findMaxDepthDecomposition(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int leftDepth = findMaxDepthDecomposition(root.left);
        int rightDepth = findMaxDepthDecomposition(root.right);

        // Post-order: combine results from subtrees
        return 1 + Math.max(leftDepth, rightDepth);
    }

    public int diameterOfBinaryTree(TreeNode root) {
        diameter = 0;
        calculateHeight(root);
        return diameter;
    }

    // Helper function that calculates height and updates diameter in post-order
    private int calculateHeight(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int leftHeight = calculateHeight(root.left);
        int rightHeight = calculateHeight(root.right);

        // Post-order: update diameter using information from both subtrees
        diameter = Math.max(diameter, leftHeight + rightHeight);

        return 1 + Math.max(leftHeight, rightHeight);
    }

    /**
     * SECTION 4: LEVEL-ORDER TRAVERSAL (BFS)
     * Demonstrates breadth-first traversal of a binary tree
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            List<Integer> currentLevel = new ArrayList<>();

            // Process all nodes at the current level
            for (int i = 0; i < levelSize; i++) {
                TreeNode current = queue.poll();
                currentLevel.add(current.val);

                if (current.left != null) {
                    queue.offer(current.left);
                }
                if (current.right != null) {
                    queue.offer(current.right);
                }
            }

            result.add(currentLevel);
        }

        return result;
    }

    /**
     * SECTION 5: ALGORITHM PARADIGM CONNECTIONS
     * Shows how binary tree algorithms connect to other algorithm types
     */

    // Example 5.1: Dynamic Programming Pattern (focusing on subtrees)
    public int sumOfNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }

        // Get results from subtrees
        int leftSum = sumOfNodes(root.left);
        int rightSum = sumOfNodes(root.right);

        // Post-order: combine results
        return leftSum + rightSum + root.val;
    }

    // Example 5.2: DFS Pattern (focusing on individual nodes)
    public void incrementAllNodes(TreeNode root) {
        if (root == null) {
            return;
        }

        // Process current node
        root.val += 1;

        // Process children
        incrementAllNodes(root.left);
        incrementAllNodes(root.right);
    }

    // Example 5.3: Backtracking Pattern (focusing on tree branches)
    public void printAllPaths(TreeNode root) {
        List<Integer> path = new ArrayList<>();
        printPaths(root, path);
    }

    private void printPaths(TreeNode root, List<Integer> path) {
        if (root == null) {
            return;
        }

        // Make choice: add current node to path
        path.add(root.val);

        // At leaf node, print the path
        if (root.left == null && root.right == null) {
            System.out.println(path);
        }

        // Explore left and right paths
        printPaths(root.left, path);
        printPaths(root.right, path);

        // Undo choice: remove current node from path before returning
        path.remove(path.size() - 1);
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
