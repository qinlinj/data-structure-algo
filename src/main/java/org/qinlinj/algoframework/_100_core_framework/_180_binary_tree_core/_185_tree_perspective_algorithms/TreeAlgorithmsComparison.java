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
    // MAIN METHOD FOR TESTING
    // =====================================================

    public static void main(String[] args) {
        TreeAlgorithmsComparison solution = new TreeAlgorithmsComparison();

        // Create a sample binary tree
        //      1
        //     / \
        //    2   3
        //   / \   \
        //  4   5   6
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.right = new TreeNode(6);

        // Create a sample N-ary tree
        //       1
        //     / | \
        //    2  3  4
        //   / \
        //  5   6
        Node nRoot = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(5);
        Node n6 = new Node(6);

        nRoot.children.add(n2);
        nRoot.children.add(n3);
        nRoot.children.add(n4);
        n2.children.add(n5);
        n2.children.add(n6);

        System.out.println("=== DYNAMIC PROGRAMMING EXAMPLES (FOCUS ON SUBTREES) ===");
        System.out.println("Total nodes in binary tree: " + solution.countNodes(root));
        System.out.println("Fibonacci(6): " + solution.fibonacci(6));

        System.out.println("\n=== BACKTRACKING EXAMPLES (FOCUS ON BRANCHES) ===");
        System.out.println("Binary tree branch tracking:");
        solution.traverseWithBranchTracking(root);

        System.out.println("\nPermutations of [1,2,3]:");
        int[] nums = {1, 2, 3};
        solution.permute(nums);

        System.out.println("\n=== DFS EXAMPLES (FOCUS ON NODES) ===");
        System.out.println("Before incrementing node values:");
        printTree(root);
        solution.incrementTreeValues(root);
        System.out.println("After incrementing node values:");
        printTree(root);

        System.out.println("\nIsland count in grid:");
        char[][] grid = {
                {'1', '1', '0', '0', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '1'}
        };
        int islands = solution.numIslands(grid);
        System.out.println("Number of islands: " + islands);

        System.out.println("\n=== COMPARING DFS AND BACKTRACKING ON N-ARY TREE ===");
        solution.compareTraversalStyles(nRoot);
    }

    // =====================================================
    // EXAMPLE 1: DYNAMIC PROGRAMMING - FOCUS ON "SUBTREES"
    // =====================================================

    // Helper method to print binary tree structure
    private static void printTree(TreeNode root) {
        if (root == null) return;

        System.out.print(root.val + " ");
        printTree(root.left);
        printTree(root.right);
    }

    // Example 1A: Count nodes in a binary tree
    public int countNodes(TreeNode root) {
        // Base case
        if (root == null) {
            return 0;
        }

        // Solve subproblems - get count from left and right subtrees
        int leftCount = countNodes(root.left);
        int rightCount = countNodes(root.right);

        // Combine subproblem results to solve original problem
        // Post-order position: Entire subtree count = left subtree + right subtree + 1
        return leftCount + rightCount + 1;
    }

    // Example 1B: Fibonacci sequence using dynamic programming approach
    public int fibonacci(int n) {
        // Base cases
        if (n <= 1) return n;

        // Solve subproblems
        int fib1 = fibonacci(n - 1);
        int fib2 = fibonacci(n - 2);

        // Combine subproblem results
        return fib1 + fib2;
    }

    // Example 1C: Maximum path sum in a binary tree
    public int maxPathSum(TreeNode root) {
        int[] maxSum = new int[1];
        maxSum[0] = Integer.MIN_VALUE;

        // Call helper function to do the calculation
        maxPathSumHelper(root, maxSum);
        return maxSum[0];
    }

    // =====================================================
    // EXAMPLE 2: BACKTRACKING - FOCUS ON "BRANCHES"
    // =====================================================

    private int maxPathSumHelper(TreeNode node, int[] maxSum) {
        if (node == null) return 0;

        // Calculate maximum path sum from left and right subtrees
        // Ignore negative sums by taking max with 0
        int leftGain = Math.max(maxPathSumHelper(node.left, maxSum), 0);
        int rightGain = Math.max(maxPathSumHelper(node.right, maxSum), 0);

        // Current node can be the highest point of the path
        int priceNewPath = node.val + leftGain + rightGain;

        // Update max sum if needed
        maxSum[0] = Math.max(maxSum[0], priceNewPath);

        // Return the max gain if continue the path through this node
        return node.val + Math.max(leftGain, rightGain);
    }

    // Example 2A: Print the paths taken during tree traversal
    public void traverseWithBranchTracking(TreeNode root) {
        traverseWithBranchTracking(root, "Root");
    }

    private void traverseWithBranchTracking(TreeNode root, String path) {
        if (root == null) return;

        // Process left branch
        if (root.left != null) {
            // Make choice - enter the branch to left child
            System.out.println("Going from node " + root.val + " to node " + root.left.val);
            traverseWithBranchTracking(root.left, path + " -> " + root.left.val);
            // Unmake choice - exit the branch
            System.out.println("Returning from node " + root.left.val + " to node " + root.val);
        }

        // Process right branch
        if (root.right != null) {
            // Make choice - enter the branch to right child
            System.out.println("Going from node " + root.val + " to node " + root.right.val);
            traverseWithBranchTracking(root.right, path + " -> " + root.right.val);
            // Unmake choice - exit the branch
            System.out.println("Returning from node " + root.right.val + " to node " + root.val);
        }
    }

    // Example 2B: N-ary tree traversal with branch tracking
    public void traverseNaryWithBranchTracking(Node root) {
        if (root == null) return;

        for (Node child : root.children) {
            // Make choice - enter this branch
            System.out.println("Going from node " + root.val + " to node " + child.val);
            traverseNaryWithBranchTracking(child);
            // Unmake choice - exit this branch
            System.out.println("Returning from node " + child.val + " to node " + root.val);
        }
    }

    // Example 2C: Classic backtracking - permutations
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        LinkedList<Integer> track = new LinkedList<>();
        boolean[] used = new boolean[nums.length];

        backtrack(nums, track, used, result);
        return result;
    }

    // =====================================================
    // EXAMPLE 3: DFS - FOCUS ON "NODES"
    // =====================================================

    private void backtrack(int[] nums, LinkedList<Integer> track, boolean[] used, List<List<Integer>> result) {
        // Base case: found a valid permutation
        if (track.size() == nums.length) {
            result.add(new ArrayList<>(track));
            return;
        }

        // Try all possible next elements
        for (int i = 0; i < nums.length; i++) {
            // Skip used elements
            if (used[i]) continue;

            // Make choice - add this element to permutation
            used[i] = true;
            track.add(nums[i]);
            System.out.println("Added " + nums[i] + " to permutation: " + track);

            // Explore further with this choice
            backtrack(nums, track, used, result);

            // Unmake choice - remove this element from permutation
            track.removeLast();
            used[i] = false;
            System.out.println("Removed " + nums[i] + " from permutation: " + track);
        }
    }

    // Example 3A: Increment every node value in a binary tree
    public void incrementTreeValues(TreeNode root) {
        if (root == null) return;

        // Process this node
        root.val++;
        System.out.println("Incremented node value to: " + root.val);

        // Continue DFS to children
        incrementTreeValues(root.left);
        incrementTreeValues(root.right);
    }

    // Example 3B: DFS on a grid (island problem)
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) return 0;

        int count = 0;
        int rows = grid.length;
        int cols = grid[0].length;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == '1') {
                    count++;
                    dfsGrid(grid, i, j);
                }
            }
        }

        return count;
    }

    private void dfsGrid(char[][] grid, int r, int c) {
        int rows = grid.length;
        int cols = grid[0].length;

        // Check boundaries and if current cell is land
        if (r < 0 || c < 0 || r >= rows || c >= cols || grid[r][c] == '0') {
            return;
        }

        // Mark as visited
        grid[r][c] = '0';
        System.out.println("Visited cell at: [" + r + "," + c + "]");

        // Visit all neighbors (4-directional)
        dfsGrid(grid, r + 1, c);
        dfsGrid(grid, r - 1, c);
        dfsGrid(grid, r, c + 1);
        dfsGrid(grid, r, c - 1);
    }

    // =====================================================
    // COMPARISON DEMONSTRATION
    // =====================================================

    // Example 3C: DFS with node entry/exit tracking
    public void dfsWithTracking(TreeNode root) {
        if (root == null) return;

        // Node entry - processing happens outside loop
        System.out.println("Entering node " + root.val);

        // Visit children
        if (root.left != null) dfsWithTracking(root.left);
        if (root.right != null) dfsWithTracking(root.right);

        // Node exit - processing happens outside loop
        System.out.println("Exiting node " + root.val);
    }

    // Compare DFS and backtracking approaches on same N-ary tree
    public void compareTraversalStyles(Node root) {
        System.out.println("\n--- DFS TRAVERSAL (FOCUS ON NODES) ---");
        dfsTraversal(root);

        System.out.println("\n--- BACKTRACKING TRAVERSAL (FOCUS ON BRANCHES) ---");
        backtrackingTraversal(root);
    }

    private void dfsTraversal(Node root) {
        if (root == null) return;

        // Processing node (outside the loop)
        System.out.println("Enter node " + root.val);

        // Visit all children
        for (Node child : root.children) {
            dfsTraversal(child);
        }

        // Post-processing node (outside the loop)
        System.out.println("Exit node " + root.val);
    }

    private void backtrackingTraversal(Node root) {
        if (root == null) return;

        // Visit all children with branch tracking
        for (Node child : root.children) {
            // Process branch (inside the loop)
            System.out.println("Enter branch: " + root.val + " -> " + child.val);

            backtrackingTraversal(child);

            // Post-process branch (inside the loop)
            System.out.println("Exit branch: " + child.val + " -> " + root.val);
        }
    }

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
        public int val;
        public List<Node> children;

        Node(int x) {
            val = x;
            children = new ArrayList<>();
        }
    }
}
