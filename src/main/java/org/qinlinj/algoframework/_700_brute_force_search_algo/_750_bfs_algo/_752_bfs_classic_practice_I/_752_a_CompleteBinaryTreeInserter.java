package org.qinlinj.algoframework._700_brute_force_search_algo._750_bfs_algo._752_bfs_classic_practice_I; /**
 * Complete Binary Tree Inserter (LeetCode 919)
 * -------------------------------------------
 * <p>
 * Summary:
 * This class implements a data structure for inserting nodes into a complete binary tree.
 * A complete binary tree is a binary tree where every level except possibly the last is
 * completely filled, and all nodes are as far left as possible.
 * <p>
 * Key Concepts:
 * 1. Complete Binary Tree properties - all levels except possibly the last are filled
 * 2. Level-order traversal (BFS) to identify nodes that can accept children
 * 3. Queue-based insertion strategy for maintaining complete binary tree structure
 * <p>
 * The approach:
 * - During initialization, we perform a BFS to find all nodes that can accept children
 * (nodes with fewer than 2 children) and store them in a queue
 * - When inserting a node, we take the first node from our queue that has an available spot
 * - If the node now has two children, we remove it from the queue
 * - The new node is always added to the queue as it can accept future children
 * <p>
 * Time Complexity:
 * - Initialization: O(n) where n is the number of nodes in the tree
 * - Insertion: O(1) amortized time per insertion
 * - get_root: O(1)
 * <p>
 * Space Complexity: O(n) for storing nodes that can accept children
 */

import java.util.*;

// Definition for a binary tree node
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

public class _752_a_CompleteBinaryTreeInserter {
    // Queue to store nodes that can accept children (have fewer than 2 children)
    private Queue<TreeNode> insertionQueue = new LinkedList<>();
    private TreeNode root;

    /**
     * Initialize the CBTInserter with the root of a complete binary tree
     *
     * @param root The root of a complete binary tree
     */
    public _752_a_CompleteBinaryTreeInserter(TreeNode root) {
        this.root = root;

        // Perform BFS to find nodes that can accept children
        Queue<TreeNode> bfsQueue = new LinkedList<>();
        bfsQueue.offer(root);

        while (!bfsQueue.isEmpty()) {
            TreeNode current = bfsQueue.poll();

            // Add left child to BFS queue if it exists
            if (current.left != null) {
                bfsQueue.offer(current.left);
            }

            // Add right child to BFS queue if it exists
            if (current.right != null) {
                bfsQueue.offer(current.right);
            }

            // If this node has an available spot for a child, add it to the insertion queue
            if (current.left == null || current.right == null) {
                insertionQueue.offer(current);
            }
        }
    }

    /**
     * Example usage
     */
    public static void main(String[] args) {
        // Create a complete binary tree with nodes 1 and 2
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);

        _752_a_CompleteBinaryTreeInserter cbt = new _752_a_CompleteBinaryTreeInserter(root);

        // Insert node 3 and 4
        System.out.println("Insert 3, parent value: " + cbt.insert(3)); // Should return 1
        System.out.println("Insert 4, parent value: " + cbt.insert(4)); // Should return 2

        // Print the tree structure
        System.out.println("Final tree structure:");
        printTree(cbt.get_root(), 0);
    }

    // Helper method to print the tree structure
    private static void printTree(TreeNode node, int level) {
        if (node == null) return;

        // Print right subtree
        printTree(node.right, level + 1);

        // Print current node
        for (int i = 0; i < level; i++) {
            System.out.print("    ");
        }
        System.out.println(node.val);

        // Print left subtree
        printTree(node.left, level + 1);
    }

    /**
     * Insert a new node with the given value
     *
     * @param val Value of the new node to insert
     * @return The value of the parent node of the inserted node
     */
    public int insert(int val) {
        TreeNode newNode = new TreeNode(val);
        TreeNode parent = insertionQueue.peek();

        // Insert as left child if left is null
        if (parent.left == null) {
            parent.left = newNode;
        }
        // Insert as right child if right is null
        else if (parent.right == null) {
            parent.right = newNode;
            // Parent now has 2 children, so remove it from the queue
            insertionQueue.poll();
        }

        // The new node can accept children in the future
        insertionQueue.offer(newNode);

        return parent.val;
    }

    /**
     * Get the root of the tree
     *
     * @return The root node of the tree
     */
    public TreeNode get_root() {
        return root;
    }
}