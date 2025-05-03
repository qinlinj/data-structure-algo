package org.qinlinj.algoframework._400_binary_tree_algo._430_binary_tree_algo_practice_II._435_level_order_traversal_practice_II; /**
 * Problem 919: Complete Binary Tree Inserter (Medium)
 * <p>
 * Problem Description:
 * A complete binary tree is a binary tree in which every level, except possibly the last,
 * is completely filled, and all nodes are as far left as possible.
 * <p>
 * Design a data structure CBTInserter that supports the following operations:
 * 1. CBTInserter(TreeNode root) - Initialize with a complete binary tree
 * 2. insert(int val) - Insert a new node with the given value into the tree and return the parent's value
 * 3. get_root() - Return the root node of the tree
 * <p>
 * Key Concepts:
 * - Complete Binary Tree Properties: Every level (except possibly the last) is filled,
 * and nodes are as far left as possible
 * - Queue-based Node Tracking: Maintains a queue of nodes that can accept new children
 * - Level Order Traversal: Uses BFS to identify insertable positions in the tree
 * - Efficient Insertion: O(1) time complexity for each insertion operation
 * <p>
 * Time Complexity:
 * - Initialization: O(n) where n is the number of nodes
 * - Insertion: O(1) amortized
 * - get_root(): O(1)
 * <p>
 * Space Complexity: O(n) for storing the queue of insertable nodes
 */

import java.util.*;

class _435_a_CompleteBinaryTreeInserter {
    // Main method for testing
    public static void main(String[] args) {
        _435_a_CompleteBinaryTreeInserter solution = new _435_a_CompleteBinaryTreeInserter();

        // Create example tree [1, 2]
        TreeNode root = solution.new TreeNode(1);
        root.left = solution.new TreeNode(2);

        // Create CBTInserter
        CBTInserter cbtInserter = solution.new CBTInserter(root);

        System.out.println("Initial tree:");
        solution.printLevelOrder(cbtInserter.get_root());

        // Insert 3
        int parentVal1 = cbtInserter.insert(3);
        System.out.println("After inserting 3, parent value: " + parentVal1);
        solution.printLevelOrder(cbtInserter.get_root());

        // Insert 4
        int parentVal2 = cbtInserter.insert(4);
        System.out.println("After inserting 4, parent value: " + parentVal2);
        solution.printLevelOrder(cbtInserter.get_root());

        // Get the final tree
        System.out.println("Final tree:");
        solution.printLevelOrder(cbtInserter.get_root());
        // Expected output: [1, 2, 3, 4]
    }

    // Helper method to print the tree in level order
    public void printLevelOrder(TreeNode root) {
        if (root == null) {
            System.out.println("[]");
            return;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        List<Integer> values = new ArrayList<>();

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();

            if (node != null) {
                values.add(node.val);
                queue.offer(node.left);
                queue.offer(node.right);
            } else {
                values.add(null);
            }
        }

        // Trim trailing nulls for cleaner output
        while (!values.isEmpty() && values.get(values.size() - 1) == null) {
            values.remove(values.size() - 1);
        }

        System.out.println(values);
    }

    // Definition for a binary tree node
    public class TreeNode {
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

    // Implementation of the CBTInserter class
    class CBTInserter {
        // Queue to store nodes that can accept new children
        private Queue<TreeNode> insertableNodes;
        // Reference to the root node
        private TreeNode root;

        /**
         * Initialize the CBTInserter with the root of a complete binary tree
         * @param root The root of a complete binary tree
         */
        public CBTInserter(TreeNode root) {
            this.root = root;
            insertableNodes = new LinkedList<>();

            // Use BFS to find all nodes that can accept new children
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);

            while (!queue.isEmpty()) {
                TreeNode node = queue.poll();

                // Add child nodes to the traversal queue
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }

                // If node has room for children, add to insertableNodes queue
                if (node.left == null || node.right == null) {
                    insertableNodes.offer(node);
                }
            }
        }

        /**
         * Insert a new node with the given value into the tree
         * @param val The value to insert
         * @return The value of the parent node
         */
        public int insert(int val) {
            TreeNode newNode = new TreeNode(val);

            // Get the first node that can accept a child (but don't remove it yet)
            TreeNode parent = insertableNodes.peek();

            // Insert as left child if left is null
            if (parent.left == null) {
                parent.left = newNode;
            }
            // Insert as right child if left is not null (meaning right must be null)
            else {
                parent.right = newNode;
                // Only remove the parent from the queue after both children are filled
                insertableNodes.poll();
            }

            // Add the new node to the queue (it can accept children in the future)
            insertableNodes.offer(newNode);

            return parent.val;
        }

        /**
         * Return the root of the tree
         * @return The root node
         */
        public TreeNode get_root() {
            return root;
        }
    }
}