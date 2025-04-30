package org.qinlinj.algoframework._400_binary_tree_algo._410_binary_tree_algo_guiding_principle._411_binary_tree_guiding_principle;

/**
 * BINARY TREE LEVEL-ORDER TRAVERSAL - BFS APPROACH
 * ================================================
 * <p>
 * This class focuses on level-order traversal of binary trees, also known as
 * breadth-first search (BFS). Unlike DFS-based traversals (pre/in/post-order),
 * level-order traversal explores the tree level by level from top to bottom.
 * <p>
 * Key concepts:
 * <p>
 * 1. Level-Order Traversal Structure:
 * - Uses a queue data structure (FIFO) instead of recursion/stack
 * - Processes nodes level by level, from top to bottom
 * - Within each level, processes nodes from left to right
 * <p>
 * 2. Implementation Components:
 * - Outer loop manages traversing from top to bottom (levels)
 * - Inner loop manages traversing from left to right (within each level)
 * <p>
 * 3. Applications:
 * - Finding shortest paths in unweighted graphs
 * - Level-based tree operations (getting nodes at a specific level)
 * - Serializing/deserializing binary trees
 * <p>
 * 4. Alternative Implementations:
 * - Iterative (using queue) - most common and intuitive approach
 * - Recursive (passing level information) - less common but sometimes useful
 * <p>
 * Level-order traversal forms the foundation for BFS algorithm in graphs, which
 * is commonly used for shortest path problems in unweighted graphs.
 */

import java.util.*;

public class _411_e_BinaryTreeLevelOrder {
    public static void main(String[] args) {
        _411_e_BinaryTreeLevelOrder solution = new _411_e_BinaryTreeLevelOrder();

        // Create a sample tree
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        System.out.println("Level Order Traversal: " + solution.levelOrderTraversal(root));
        System.out.println("Level Order By Level: " + solution.levelOrderByLevel(root));
        System.out.println("Level Order Recursive: " + solution.levelOrderRecursive(root));
        System.out.println("Average of Levels: " + solution.averageOfLevels(root));
        System.out.println("Zigzag Level Order: " + solution.zigzagLevelOrder(root));

        System.out.println("\nStandard Level-Order Traversal (printed by level):");
        solution.levelTraverse(root);
    }

    /**
     * Standard level-order traversal (BFS) implementation
     * Returns a list of values in level-order
     */
    public List<Integer> levelOrderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();
            result.add(current.val);

            // Add children to the queue
            if (current.left != null) {
                queue.offer(current.left);
            }
            if (current.right != null) {
                queue.offer(current.right);
            }
        }

        return result;
    }

    /**
     * Level-order traversal that groups nodes by level
     * Returns a list of lists, where each inner list contains values at one level
     */
    public List<List<Integer>> levelOrderByLevel(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();  // Number of nodes at current level
            List<Integer> currentLevel = new ArrayList<>();

            // Process all nodes at the current level
            for (int i = 0; i < levelSize; i++) {
                TreeNode current = queue.poll();
                currentLevel.add(current.val);

                // Add children to the queue (for the next level)
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
     * Alternative: Recursive approach to level-order traversal
     */
    public List<List<Integer>> levelOrderRecursive(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        levelHelper(root, 0, result);
        return result;
    }

    private void levelHelper(TreeNode node, int level, List<List<Integer>> result) {
        if (node == null) {
            return;
        }

        // If this is the first node at this level, create a new list
        if (result.size() <= level) {
            result.add(new ArrayList<>());
        }

        // Add the current node's value to the appropriate level list
        result.get(level).add(node.val);

        // Process children at the next level
        levelHelper(node.left, level + 1, result);
        levelHelper(node.right, level + 1, result);
    }

    /**
     * Example: Finding the average value at each level of a binary tree
     */
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            double levelSum = 0;

            // Process all nodes at current level
            for (int i = 0; i < levelSize; i++) {
                TreeNode current = queue.poll();
                levelSum += current.val;

                if (current.left != null) {
                    queue.offer(current.left);
                }
                if (current.right != null) {
                    queue.offer(current.right);
                }
            }

            // Calculate and add the average for this level
            result.add(levelSum / levelSize);
        }

        return result;
    }

    /**
     * Example: Zigzag level order traversal
     * Alternates between left-to-right and right-to-left order at each level
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean leftToRight = true;

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            List<Integer> currentLevel = new ArrayList<>();

            // Process all nodes at the current level
            for (int i = 0; i < levelSize; i++) {
                TreeNode current = queue.poll();

                // Add node based on current direction
                if (leftToRight) {
                    currentLevel.add(current.val);
                } else {
                    currentLevel.add(0, current.val);  // Add at beginning for right-to-left
                }

                if (current.left != null) {
                    queue.offer(current.left);
                }
                if (current.right != null) {
                    queue.offer(current.right);
                }
            }

            result.add(currentLevel);
            leftToRight = !leftToRight;  // Toggle direction for next level
        }

        return result;
    }

    /**
     * Standard BFS traversal function that showcases the framework
     * described in the original text
     */
    public void levelTraverse(TreeNode root) {
        if (root == null) return;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        // From top to bottom traversal of each level of the binary tree
        while (!q.isEmpty()) {
            int sz = q.size();
            // From left to right traversal of each node in the current level
            for (int i = 0; i < sz; i++) {
                TreeNode cur = q.poll();
                System.out.print(cur.val + " ");

                // Add next level nodes to the queue
                if (cur.left != null) {
                    q.offer(cur.left);
                }
                if (cur.right != null) {
                    q.offer(cur.right);
                }
            }
            System.out.println();  // New line after each level
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
}
