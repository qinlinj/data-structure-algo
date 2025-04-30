package org.qinlinj.algoframework._400_binary_tree_algo._410_binary_tree_algo_guiding_principle._411_binary_tree_guiding_principle;

/**
 * BINARY TREE ALGORITHM COMPARISONS - DYNAMIC PROGRAMMING, BACKTRACKING, AND DFS
 * =============================================================================
 * <p>
 * This class explores the connections between binary tree algorithms and other
 * common algorithm paradigms: dynamic programming, backtracking, and DFS.
 * Understanding these connections helps in applying a unified framework to various problems.
 * <p>
 * Key insights:
 * <p>
 * 1. Binary Trees as a Foundation:
 * - Dynamic programming, backtracking, and DFS can all be viewed as extensions of binary tree algorithms
 * - They differ in what aspects of the tree they focus on
 * <p>
 * 2. Focus Points in Different Algorithms:
 * - Dynamic Programming: Focuses on entire subtrees (problem decomposition)
 * - Backtracking: Focuses on edges between nodes (tree branches)
 * - DFS: Focuses on individual nodes
 * <p>
 * 3. Implementation Differences:
 * - In backtracking, decisions/choices are made and undone within the loop
 * - In DFS, operations are typically performed outside the loop
 * - Dynamic programming uses return values from subproblems to build solutions
 * <p>
 * 4. Practical Applications:
 * - DP: When solutions to subproblems can be reused (e.g., Fibonacci, tree max depth)
 * - Backtracking: When exploring combinations of choices (e.g., permutations, subsets)
 * - DFS: When processing individual elements (e.g., island problems, node transformations)
 * <p>
 * Understanding these connections helps in recognizing which approach is most suitable
 * for a given problem and how to implement it effectively.
 */

import java.util.*;

public class _411_f_BinaryTreeAlgorithmComparisons {
    public static void main(String[] args) {
        _411_f_BinaryTreeAlgorithmComparisons solution = new _411_f_BinaryTreeAlgorithmComparisons();

        // Create a sample binary tree
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        System.out.println("DP Style - Count Nodes: " + solution.countNodes(root));

        System.out.println("\nBacktracking Style Traversal:");
        solution.traverseBacktrackingStyle(root);

        // Create a multi-way tree
        Node multiRoot = new Node(1);
        Node child1 = new Node(2);
        Node child2 = new Node(3);
        Node child3 = new Node(4);
        multiRoot.children.add(child1);
        multiRoot.children.add(child2);
        multiRoot.children.add(child3);
        child1.children.add(new Node(5));
        child1.children.add(new Node(6));

        System.out.println("\nMulti-way Tree Backtracking Style:");
        solution.traverseMultiWayTree(multiRoot);

        // Demonstrate DFS vs Backtracking difference
        System.out.println("\nDFS Style vs Backtracking Style:");
        System.out.println("DFS Style:");
        solution.dfsStyle(multiRoot);

        System.out.println("\nBacktracking Style:");
        solution.backtrackingStyle(multiRoot);
    }

    /**
     * EXAMPLE 1: Dynamic Programming Style
     * Focus: entire subtrees (counting nodes in a binary tree)
     */
    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }

        // Compute results for left and right subtrees
        int leftCount = countNodes(root.left);
        int rightCount = countNodes(root.right);

        // Combine results to solve the current problem
        return leftCount + rightCount + 1;
    }

    /**
     * Dynamic Programming application: Fibonacci sequence
     * Shows how DP uses solutions to subproblems
     */
    public int fibonacci(int n) {
        if (n <= 1) {
            return n;
        }

        // Compute results for subproblems
        int fib1 = fibonacci(n - 1);
        int fib2 = fibonacci(n - 2);

        // Combine results
        return fib1 + fib2;
    }

    /**
     * EXAMPLE 2: Backtracking Style
     * Focus: edges between nodes (printing path between nodes)
     */
    public void traverseBacktrackingStyle(TreeNode root) {
        if (root == null) {
            return;
        }

        // Process the node (can be done in pre-order position)
        System.out.println("Visiting node: " + root.val);

        // For each child (branch), make a choice, traverse, then undo the choice
        if (root.left != null) {
            System.out.println("Moving from " + root.val + " to " + root.left.val);
            traverseBacktrackingStyle(root.left);
            System.out.println("Moving back from " + root.left.val + " to " + root.val);
        }

        if (root.right != null) {
            System.out.println("Moving from " + root.val + " to " + root.right.val);
            traverseBacktrackingStyle(root.right);
            System.out.println("Moving back from " + root.right.val + " to " + root.val);
        }
    }

    /**
     * Backtracking style applied to a multi-way tree
     * Shows the classical backtracking pattern
     */
    public void traverseMultiWayTree(Node root) {
        if (root == null) {
            return;
        }

        // For each child (branch)
        for (Node child : root.children) {
            // Make a choice
            System.out.println("From node " + root.val + " enter node " + child.val);

            // Explore this path
            traverseMultiWayTree(child);

            // Undo the choice (backtrack)
            System.out.println("From node " + child.val + " back to node " + root.val);
        }
    }

    /**
     * EXAMPLE 3: DFS Style
     * Focus: individual nodes (incrementing each node's value)
     */
    public void incrementTreeNodesDFS(TreeNode root) {
        if (root == null) {
            return;
        }

        // Process the current node
        root.val++;

        // Recurse on children
        incrementTreeNodesDFS(root.left);
        incrementTreeNodesDFS(root.right);
    }

    /**
     * DFS style applied to a grid (island problem)
     * Shows how DFS focuses on individual cells/nodes
     */
    public void dfs(int[][] grid, int i, int j) {
        int m = grid.length;
        int n = grid[0].length;

        // Check boundaries and valid cells
        if (i < 0 || j < 0 || i >= m || j >= n || grid[i][j] == 0) {
            return;
        }

        // Mark current cell as visited
        grid[i][j] = 0;

        // Explore in all four directions
        dfs(grid, i + 1, j);
        dfs(grid, i - 1, j);
        dfs(grid, i, j + 1);
        dfs(grid, i, j - 1);
    }

    /**
     * Illustrates the key difference between DFS and backtracking
     * In DFS, actions are outside the loop; in backtracking, they're inside
     */

    // DFS style - actions outside the loop
    public void dfsStyle(Node root) {
        if (root == null) return;

        // Do something with the node (outside the loop)
        System.out.println("Enter node " + root.val);

        // Recurse on children
        for (Node child : root.children) {
            dfsStyle(child);
        }

        // Do something when leaving the node (outside the loop)
        System.out.println("Leave node " + root.val);
    }

    // Backtracking style - actions inside the loop
    public void backtrackingStyle(Node root) {
        if (root == null) return;

        // For each child, make a choice, recurse, then undo the choice
        for (Node child : root.children) {
            // Make choice (inside the loop)
            System.out.println("From node " + root.val + " to node " + child.val);

            // Recurse
            backtrackingStyle(child);

            // Undo choice (inside the loop)
            System.out.println("From node " + child.val + " back to node " + root.val);
        }
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

    // Definition for a multi-way tree node (for comparison examples)
    public static class Node {
        int val;
        List<Node> children;

        Node(int x) {
            val = x;
            children = new ArrayList<>();
        }
    }
}
