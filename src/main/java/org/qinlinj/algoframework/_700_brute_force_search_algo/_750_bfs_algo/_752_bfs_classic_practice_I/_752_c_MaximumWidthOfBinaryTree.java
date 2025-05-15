package org.qinlinj.algoframework._700_brute_force_search_algo._750_bfs_algo._752_bfs_classic_practice_I; /**
 * Maximum Width of Binary Tree (LeetCode 662)
 * ------------------------------------------
 * <p>
 * Summary:
 * This problem asks us to find the maximum width of a binary tree. The width of a level
 * is defined as the distance between the leftmost and rightmost non-null nodes on that level.
 * Null nodes in between the leftmost and rightmost nodes are also counted in the width.
 * <p>
 * Key Concepts:
 * 1. Level-order traversal (BFS) to process nodes level by level
 * 2. Node indexing in a binary tree: if a parent node has index i,
 * then its left child has index 2*i and right child has index 2*i+1
 * 3. Tracking the index of each node to calculate width
 * <p>
 * Two approaches:
 * 1. BFS (Level-order traversal):
 * - Use a queue to process nodes level by level
 * - Store each node with its index
 * - Calculate the width as (end - start + 1) for each level
 * <p>
 * 2. DFS (Preorder traversal):
 * - Track the first node's index at each depth
 * - Calculate width as (currentNodeIndex - firstNodeIndex + 1)
 * <p>
 * Time Complexity: O(n) where n is the number of nodes in the tree
 * Space Complexity: O(w) where w is the maximum width of the tree (for BFS)
 * O(h) where h is the height of the tree (for DFS)
 * <p>
 * Note: The indices can get very large for deep trees, potentially causing integer overflow.
 * In a real implementation, we might need to handle this case.
 */

import java.util.*;

public class _752_c_MaximumWidthOfBinaryTree {

    /**
     * Example usage
     */
    public static void main(String[] args) {
        _752_c_MaximumWidthOfBinaryTree solution = new _752_c_MaximumWidthOfBinaryTree();

        // Example 1: Create a binary tree [1,3,2,5,3,null,9]
        //        1
        //       / \
        //      3   2
        //     / \   \
        //    5   3   9
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(3);
        root1.right = new TreeNode(2);
        root1.left.left = new TreeNode(5);
        root1.left.right = new TreeNode(3);
        root1.right.right = new TreeNode(9);

        System.out.println("Example 1 (BFS): " + solution.widthOfBinaryTree(root1)); // Should output 4
        System.out.println("Example 1 (DFS): " + solution.widthOfBinaryTreeDFS(root1)); // Should output 4

        // Example 2: Create a binary tree [1,3,2,5]
        //        1
        //       / \
        //      3   2
        //     /
        //    5
        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(3);
        root2.right = new TreeNode(2);
        root2.left.left = new TreeNode(5);

        System.out.println("Example 2 (BFS): " + solution.widthOfBinaryTree(root2)); // Should output 2
        System.out.println("Example 2 (DFS): " + solution.widthOfBinaryTreeDFS(root2)); // Should output 2
    }

    /**
     * Find the maximum width of a binary tree using BFS approach
     * @param root Root of the binary tree
     * @return Maximum width of the tree
     */
    public int widthOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }

        // Track maximum width found
        int maxWidth = 0;

        // Queue for BFS - store node and its index
        Queue<Pair> queue = new LinkedList<>();
        queue.offer(new Pair(root, 1)); // Start with root at index 1

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            int startIndex = 0, endIndex = 0;

            // Process all nodes at the current level
            for (int i = 0; i < levelSize; i++) {
                Pair current = queue.poll();
                TreeNode node = current.node;
                int index = current.index;

                // Record the index of the first and last nodes in this level
                if (i == 0) {
                    startIndex = index;
                }
                if (i == levelSize - 1) {
                    endIndex = index;
                }

                // Add children to the queue with their calculated indices
                if (node.left != null) {
                    queue.offer(new Pair(node.left, index * 2));
                }
                if (node.right != null) {
                    queue.offer(new Pair(node.right, index * 2 + 1));
                }
            }

            // Update maximum width
            maxWidth = Math.max(maxWidth, endIndex - startIndex + 1);
        }

        return maxWidth;
    }

    /**
     * Alternative implementation using DFS approach
     */
    public int widthOfBinaryTreeDFS(TreeNode root) {
        if (root == null) {
            return 0;
        }

        // List to store the first node's index at each depth
        List<Integer> firstIndices = new ArrayList<>();
        return dfsHelper(root, 0, 1, firstIndices);
    }

    /**
     * DFS helper method
     * @param node Current node
     * @param depth Current depth
     * @param index Current node's index
     * @param firstIndices List of first indices at each depth
     * @return Maximum width found
     */
    private int dfsHelper(TreeNode node, int depth, int index, List<Integer> firstIndices) {
        if (node == null) {
            return 0;
        }

        // If this is the first node we see at this depth
        if (firstIndices.size() == depth) {
            firstIndices.add(index);
        }

        // Calculate width at current depth
        int currentWidth = index - firstIndices.get(depth) + 1;

        // Recurse left and right, updating max width
        int leftWidth = dfsHelper(node.left, depth + 1, index * 2, firstIndices);
        int rightWidth = dfsHelper(node.right, depth + 1, index * 2 + 1, firstIndices);

        return Math.max(currentWidth, Math.max(leftWidth, rightWidth));
    }

    // Helper class to store a node with its position index
    class Pair {
        TreeNode node;
        int index;

        public Pair(TreeNode node, int index) {
            this.node = node;
            this.index = index;
        }
    }
}