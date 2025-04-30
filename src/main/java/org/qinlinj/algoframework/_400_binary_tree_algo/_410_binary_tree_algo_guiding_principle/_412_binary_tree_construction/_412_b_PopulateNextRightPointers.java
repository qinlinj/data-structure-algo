package org.qinlinj.algoframework._400_binary_tree_algo._410_binary_tree_algo_guiding_principle._412_binary_tree_construction;

/**
 * POPULATE NEXT RIGHT POINTERS IN BINARY TREE
 * ===========================================
 * <p>
 * This class demonstrates how to solve the "Populate Next Right Pointers" problem (LeetCode 116):
 * Given a perfect binary tree, connect all nodes at the same level using the next pointer.
 * <p>
 * Problem Description:
 * You are given a perfect binary tree where all leaves are on the same level, and every parent
 * has two children. Populate each next pointer to point to its next right node. If there is no
 * next right node, the next pointer should be set to NULL.
 * <p>
 * Key Insights:
 * <p>
 * 1. Traversal Approach with Modified "Three-ary Tree" Thinking:
 * - Traditional traversal won't work as we need to connect nodes with different parents
 * - We need to think of the problem as traversing a "three-node" structure:
 * - Each "three-node" consists of two adjacent nodes in the binary tree
 * - The traverse function takes two nodes as parameters instead of one
 * - This allows us to connect nodes that aren't siblings
 * <p>
 * 2. Problem Decomposition Approach:
 * - This problem is difficult to solve with the decomposition approach
 * - We need to handle connections that span across different subtrees
 * - The traversal approach is more natural for this problem
 * <p>
 * This problem shows that sometimes one approach is more suitable than the other,
 * and we need to be flexible in our thinking.
 */

public class _412_b_PopulateNextRightPointers {
    /**
     * Utility method to verify connections by level traversal
     */
    public static void printLevelConnections(Node root) {
        if (root == null) {
            return;
        }

        // Start with the root level
        Node levelStart = root;

        // Loop through each level
        while (levelStart != null) {
            // Start at the beginning of the current level
            Node current = levelStart;

            // Print the values in this level, following next pointers
            System.out.print("Level: ");
            while (current != null) {
                System.out.print(current.val);
                if (current.next != null) {
                    System.out.print(" -> ");
                } else {
                    System.out.print(" -> null");
                }
                current = current.next;
            }
            System.out.println();

            // Move to the next level (start with the leftmost node)
            levelStart = levelStart.left;
        }
    }

    /**
     * Main method for testing
     */
    public static void main(String[] args) {
        _412_b_PopulateNextRightPointers solution = new _412_b_PopulateNextRightPointers();

        // Create a perfect binary tree: [1,2,3,4,5,6,7]
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);

        // Connect the nodes
        solution.connect(root);

        // Print the connections to verify
        System.out.println("Level connections after connecting next pointers:");
        printLevelConnections(root);

        // Expected output:
        // Level: 1 -> null
        // Level: 2 -> 3 -> null
        // Level: 4 -> 5 -> 6 -> 7 -> null
    }

    /**
     * Approach using the "traversal" thinking pattern with a modified three-node approach
     * Time Complexity: O(n) where n is the number of nodes
     * Space Complexity: O(h) where h is the height of the tree (recursion stack)
     */
    public Node connect(Node root) {
        if (root == null) {
            return null;
        }

        // Start the traversal with the first "three-node" structure
        // consisting of left and right children of the root
        traverse(root.left, root.right);

        return root;
    }

    /**
     * Modified traversal function that takes two nodes as parameters
     * This allows us to connect nodes across different parent nodes
     */
    private void traverse(Node node1, Node node2) {
        // Base case: if either node is null, we can't make connections
        if (node1 == null || node2 == null) {
            return;
        }

        // Pre-order position: connect the two input nodes
        node1.next = node2;

        // Connect siblings that share the same parent
        traverse(node1.left, node1.right);
        traverse(node2.left, node2.right);

        // Connect cousins (nodes whose parents are siblings)
        traverse(node1.right, node2.left);
    }

    // Definition for a Node with next pointer
    public static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node(int _val) {
            val = _val;
        }

        @Override
        public String toString() {
            return String.valueOf(val);
        }
    }
}