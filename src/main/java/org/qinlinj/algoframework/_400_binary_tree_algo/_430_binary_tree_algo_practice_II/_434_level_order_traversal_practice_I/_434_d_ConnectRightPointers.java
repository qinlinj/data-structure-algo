package org.qinlinj.algoframework._400_binary_tree_algo._430_binary_tree_algo_practice_II._434_level_order_traversal_practice_I; /**
 * Problem 117: Populating Next Right Pointers in Each Node II (Medium)
 * <p>
 * Problem Description:
 * Given a binary tree, populate each next pointer to point to its next right node.
 * If there is no next right node, the next pointer should be set to NULL.
 * The tree is not necessarily a perfect binary tree.
 * <p>
 * Key Concepts:
 * - Level Order Traversal for Next Pointers: Uses BFS to connect nodes at the same level
 * - Pointer Tracking: Maintains a reference to the previous node at each level to establish connections
 * - Non-Perfect Binary Tree Handling: Handles arbitrary binary trees where nodes might be missing
 * <p>
 * Time Complexity: O(n) where n is the number of nodes in the tree
 * Space Complexity: O(w) where w is the maximum width of the tree (optimal implementation)
 */

import java.util.*;

class _434_d_ConnectRightPointers {
    // Main method for testing
    public static void main(String[] args) {
        _434_d_ConnectRightPointers solution = new _434_d_ConnectRightPointers();

        // Create example tree: [1,2,3,4,5,null,7]
        Node root = solution.new Node(1);
        root.left = solution.new Node(2);
        root.right = solution.new Node(3);
        root.left.left = solution.new Node(4);
        root.left.right = solution.new Node(5);
        root.right.right = solution.new Node(7);

        Node connectedRoot = solution.connect(root);

        // Display the connected tree
        System.out.println("Connected tree next pointers:");
        solution.displayConnectedTree(connectedRoot);
        // Expected output should show each level with next pointers
    }

    public Node connect(Node root) {
        // Handle empty tree case
        if (root == null) {
            return null;
        }

        // Queue for level order traversal
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);

        // While loop controls traversal from top to bottom
        while (!queue.isEmpty()) {
            int levelSize = queue.size();

            // Reference to the previous node at current level
            Node prev = null;

            // For loop controls traversal from left to right at current level
            for (int i = 0; i < levelSize; i++) {
                Node currentNode = queue.poll();

                // Connect current node to previous node
                if (prev != null) {
                    prev.next = currentNode;
                }

                // Update prev to current node for next iteration
                prev = currentNode;

                // Add child nodes to queue for next level processing
                if (currentNode.left != null) {
                    queue.offer(currentNode.left);
                }
                if (currentNode.right != null) {
                    queue.offer(currentNode.right);
                }
            }

            // The last node's next pointer will remain null (default)
        }

        return root;
    }

    // Helper method to display the connected tree for testing
    private void displayConnectedTree(Node root) {
        while (root != null) {
            Node current = root;
            System.out.print("Level: ");

            // Traverse the current level using next pointers
            while (current != null) {
                System.out.print(current.val + " → ");
                current = current.next;
            }

            System.out.println("null");

            // Move to the leftmost node of the next level
            root = getNextLevelStart(root);
        }
    }

    // Helper method to find the start of the next level
    private Node getNextLevelStart(Node node) {
        while (node != null) {
            if (node.left != null) return node.left;
            if (node.right != null) return node.right;
            node = node.next;
        }
        return null;
    }

    // Definition for a Node with next pointer
    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }
}