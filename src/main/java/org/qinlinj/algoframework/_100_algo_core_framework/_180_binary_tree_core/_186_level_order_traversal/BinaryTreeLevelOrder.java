package org.qinlinj.algoframework._100_algo_core_framework._180_binary_tree_core._186_level_order_traversal;

import java.util.*;

/**
 * BINARY TREE LEVEL-ORDER TRAVERSAL
 * ================================
 * <p>
 * While most binary tree algorithms employ recursion (DFS), level-order traversal uses iteration
 * with a queue (BFS). This approach is vital for many graph algorithms and has unique advantages:
 * <p>
 * - Processes nodes level by level, from top to bottom
 * - Within each level, processes nodes from left to right
 * - Ideal for finding shortest paths in unweighted graphs
 * - Provides a breadth-first view of the tree structure
 * <p>
 * This class demonstrates three approaches to level-order traversal:
 * 1. Standard iterative BFS (most common and efficient)
 * 2. Recursive DFS that simulates level-order traversal using depth tracking
 * 3. Recursive BFS implementation that processes levels recursively
 * <p>
 * The fundamental BFS framework seen in level-order traversal extends to many graph algorithms,
 * particularly those requiring shortest path calculations.
 */
public class BinaryTreeLevelOrder {

    // =====================================================
    // MAIN METHOD FOR TESTING
    // =====================================================

    public static void main(String[] args) {
        BinaryTreeLevelOrder solution = new BinaryTreeLevelOrder();

        // Create a sample binary tree
        //      1
        //     / \
        //    2   3
        //   / \   \
        //  4   5   6
        //     /
        //    7
        TreeNode root = new TreeNode(1,
                new TreeNode(2,
                        new TreeNode(4),
                        new TreeNode(5,
                                new TreeNode(7), null)),
                new TreeNode(3,
                        null, new TreeNode(6)));

        System.out.println("=== STANDARD ITERATIVE LEVEL ORDER ===");
        System.out.println(solution.levelOrderIterative(root));

        System.out.println("\n=== LEVEL TRAVERSAL WITH PRINTING ===");
        solution.levelTraverse(root);

        System.out.println("\n=== RECURSIVE DFS SIMULATING LEVEL ORDER ===");
        System.out.println(solution.levelOrderRecursiveDFS(root));

        System.out.println("\n=== RECURSIVE BFS IMPLEMENTATION ===");
        System.out.println(solution.levelOrderRecursiveBFS(root));

        System.out.println("\n=== RIGHT SIDE VIEW ===");
        System.out.println(solution.rightSideView(root));

        System.out.println("\n=== ZIGZAG LEVEL ORDER ===");
        System.out.println(solution.zigzagLevelOrder(root));

        System.out.println("\n=== MINIMUM DEPTH ===");
        System.out.println("Minimum depth: " + solution.minDepth(root));

        System.out.println("\n=== PERFORMANCE COMPARISON ===");
        solution.comparePerformance(root);

        // Create a larger tree for better performance comparison
        TreeNode largeRoot = createLargeTree(5); // 31 nodes
        System.out.println("\n=== PERFORMANCE COMPARISON (LARGER TREE) ===");
        solution.comparePerformance(largeRoot);
    }

    // =====================================================
    // APPROACH 1: STANDARD ITERATIVE LEVEL-ORDER TRAVERSAL
    // =====================================================

    // Helper to create a large balanced binary tree of given height
    private static TreeNode createLargeTree(int height) {
        if (height <= 0) return null;
        TreeNode root = new TreeNode(1);

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int count = 2;

        for (int h = 1; h < height; h++) {
            int levelSize = queue.size();
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                node.left = new TreeNode(count++);
                node.right = new TreeNode(count++);
                queue.offer(node.left);
                queue.offer(node.right);
            }
        }

        return root;
    }

    /**
     * Standard iterative level-order traversal using a queue
     * - Time Complexity: O(n) where n is the number of nodes
     * - Space Complexity: O(w) where w is the max width of the tree
     */
    public List<List<Integer>> levelOrderIterative(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        // Process level by level
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            List<Integer> currentLevel = new ArrayList<>();

            // Process all nodes at current level
            for (int i = 0; i < levelSize; i++) {
                TreeNode current = queue.poll();
                currentLevel.add(current.val);

                // Add children to queue for next level processing
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

    // BFS framework for traversal without collecting results
    public void levelTraverse(TreeNode root) {
        if (root == null) return;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        // From top to bottom
        while (!queue.isEmpty()) {
            int levelSize = queue.size();

            // From left to right within each level
            for (int i = 0; i < levelSize; i++) {
                TreeNode current = queue.poll();

                // Process the current node
                System.out.print(current.val + " ");

                // Add children to queue
                if (current.left != null) {
                    queue.offer(current.left);
                }
                if (current.right != null) {
                    queue.offer(current.right);
                }
            }
            System.out.println(); // Line break after each level
        }
    }

    // =====================================================
    // APPROACH 2: RECURSIVE DFS SIMULATING LEVEL-ORDER
    // =====================================================

    // Modified BFS for finding minimum depth
    public int minDepth(TreeNode root) {
        if (root == null) return 0;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int depth = 1; // Start with depth 1 for root

        while (!queue.isEmpty()) {
            int levelSize = queue.size();

            for (int i = 0; i < levelSize; i++) {
                TreeNode current = queue.poll();

                // Check if this is a leaf node
                if (current.left == null && current.right == null) {
                    return depth; // Found the first leaf node
                }

                // Add children to queue
                if (current.left != null) {
                    queue.offer(current.left);
                }
                if (current.right != null) {
                    queue.offer(current.right);
                }
            }

            depth++; // Increment depth after processing each level
        }

        return depth;
    }

    /**
     * Uses DFS with depth tracking to simulate level-order traversal
     * - Not truly BFS, more like "column-order" traversal
     * - Good demonstration of how pre-order traversal can be adapted
     */
    public List<List<Integer>> levelOrderRecursiveDFS(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        dfsLevelOrder(root, 0, result);
        return result;
    }

    // =====================================================
    // APPROACH 3: RECURSIVE BFS IMPLEMENTATION
    // =====================================================

    private void dfsLevelOrder(TreeNode root, int depth, List<List<Integer>> result) {
        if (root == null) return;

        // Ensure the list for this depth exists
        if (result.size() <= depth) {
            result.add(new ArrayList<>());
        }

        // Pre-order position: add current node's value to its level's list
        result.get(depth).add(root.val);

        // Recurse on left and right with incremented depth
        dfsLevelOrder(root.left, depth + 1, result);
        dfsLevelOrder(root.right, depth + 1, result);
    }

    /**
     * True recursive implementation of BFS
     * - Processes each level as a whole recursively
     * - Conceptually closer to iterative BFS
     */
    public List<List<Integer>> levelOrderRecursiveBFS(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;

        List<TreeNode> currentLevel = new ArrayList<>();
        currentLevel.add(root);

        recursiveLevelOrder(currentLevel, result);
        return result;
    }

    // =====================================================
    // ADDITIONAL LEVEL-ORDER APPLICATIONS
    // =====================================================

    private void recursiveLevelOrder(List<TreeNode> currentLevel, List<List<Integer>> result) {
        // Base case: no more nodes at this level
        if (currentLevel.isEmpty()) return;

        // Process current level
        List<Integer> values = new ArrayList<>();
        List<TreeNode> nextLevel = new ArrayList<>();

        for (TreeNode node : currentLevel) {
            // Collect values
            values.add(node.val);

            // Prepare next level
            if (node.left != null) nextLevel.add(node.left);
            if (node.right != null) nextLevel.add(node.right);
        }

        // Add current level's values to result
        result.add(values);

        // Process next level recursively
        recursiveLevelOrder(nextLevel, result);

        // Note: If we add to result here instead, we get bottom-up level order
        // result.add(values);
    }

    /**
     * Right side view of the binary tree
     * Returns values visible from the right side
     */
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();

            for (int i = 0; i < levelSize; i++) {
                TreeNode current = queue.poll();

                // If this is the last node in the level, add to result
                if (i == levelSize - 1) {
                    result.add(current.val);
                }

                // Add children to queue
                if (current.left != null) queue.offer(current.left);
                if (current.right != null) queue.offer(current.right);
            }
        }

        return result;
    }

    /**
     * Zigzag level order traversal
     * Alternates between left-to-right and right-to-left ordering
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean leftToRight = true;

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            LinkedList<Integer> currentLevel = new LinkedList<>();

            for (int i = 0; i < levelSize; i++) {
                TreeNode current = queue.poll();

                // Add to current level based on direction
                if (leftToRight) {
                    currentLevel.addLast(current.val);
                } else {
                    currentLevel.addFirst(current.val);
                }

                // Add children to queue
                if (current.left != null) queue.offer(current.left);
                if (current.right != null) queue.offer(current.right);
            }

            result.add(currentLevel);
            leftToRight = !leftToRight; // Toggle direction for next level
        }

        return result;
    }

    // =====================================================
    // PERFORMANCE COMPARISON
    // =====================================================

    public void comparePerformance(TreeNode root) {
        long startTime, endTime;

        // Test iterative approach
        startTime = System.nanoTime();
        List<List<Integer>> iterativeResult = levelOrderIterative(root);
        endTime = System.nanoTime();
        System.out.println("Iterative BFS: " + (endTime - startTime) + " ns");

        // Test recursive DFS approach
        startTime = System.nanoTime();
        List<List<Integer>> dfsResult = levelOrderRecursiveDFS(root);
        endTime = System.nanoTime();
        System.out.println("Recursive DFS: " + (endTime - startTime) + " ns");

        // Test recursive BFS approach
        startTime = System.nanoTime();
        List<List<Integer>> bfsResult = levelOrderRecursiveBFS(root);
        endTime = System.nanoTime();
        System.out.println("Recursive BFS: " + (endTime - startTime) + " ns");
    }

    // Definition for a binary tree node
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }

        // Helper constructor for easier tree creation
        TreeNode(int x, TreeNode left, TreeNode right) {
            this.val = x;
            this.left = left;
            this.right = right;
        }
    }
}
