package org.qinlinj.algoframework._700_brute_force_search_algo._750_bfs_algo._752_bfs_classic_practice_I; /**
 * Populating Next Right Pointers in Each Node II (LeetCode 117)
 * ------------------------------------------------------------
 * <p>
 * Summary:
 * This problem asks us to connect each node in a binary tree with its next right node on
 * the same level. Unlike the similar problem 116, this tree is not necessarily a perfect
 * binary tree, which means some nodes might not have both left and right children.
 * <p>
 * Key Concepts:
 * 1. Level-order traversal (BFS) to process nodes level by level
 * 2. Using a 'prev' pointer to track the previous node at each level
 * 3. Connecting each node to its next right neighbor on the same level
 * <p>
 * Approach:
 * - Use a standard BFS with a queue to traverse the tree level by level
 * - For each level, keep track of the previous node processed
 * - Connect the current node's next pointer to the previous node
 * - Continue this process for all levels
 * <p>
 * Time Complexity: O(n) where n is the number of nodes in the tree
 * Space Complexity: O(w) where w is the maximum width of the tree
 * <p>
 * Note: The problem states that using recursion is also acceptable as the
 * implicit stack space is not counted toward the space complexity requirement.
 */

import java.util.*;

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

public class _752_b_PopulatingNextRightPointers {

    /**
     * Example usage
     */
    public static void main(String[] args) {
        // Create a binary tree:
        //        1
        //       / \
        //      2   3
        //     / \   \
        //    4   5   7
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.right = new Node(7);

        _752_b_PopulatingNextRightPointers solution = new _752_b_PopulatingNextRightPointers();
        Node result = solution.connect(root);

        // Print the tree with next pointers
        printLevelOrderWithNext(result);
    }

    /**
     * Helper method to print the tree with next pointers
     */
    private static void printLevelOrderWithNext(Node root) {
        if (root == null) return;

        // Start from the root
        Node levelStart = root;

        // Traverse each level
        while (levelStart != null) {
            Node current = levelStart;
            System.out.print("Level: ");

            // Print nodes at this level using next pointers
            while (current != null) {
                System.out.print(current.val);
                if (current.next != null) {
                    System.out.print(" -> ");
                } else {
                    System.out.print(" -> #"); // # indicates end of level
                }
                current = current.next;
            }
            System.out.println();

            // Move to the start of the next level (first node with a child)
            levelStart = getNextLevelStart(levelStart);
        }
    }

    /**
     * Helper method to find the first node of the next level
     */
    private static Node getNextLevelStart(Node levelStart) {
        Node current = levelStart;
        while (current != null) {
            if (current.left != null) return current.left;
            if (current.right != null) return current.right;
            current = current.next;
        }
        return null; // No more levels
    }

    /**
     * Connects all nodes with their next right neighbors
     * @param root Root of the binary tree
     * @return Root of the modified tree with next pointers set
     */
    public Node connect(Node root) {
        if (root == null) {
            return null;
        }

        // Use a queue for level-order traversal
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            Node prev = null;

            // Process all nodes at the current level
            for (int i = 0; i < levelSize; i++) {
                Node current = queue.poll();

                // Connect the current node to the previous node
                if (prev != null) {
                    prev.next = current;
                }
                prev = current;

                // Add children to the queue for the next level
                if (current.left != null) {
                    queue.offer(current.left);
                }
                if (current.right != null) {
                    queue.offer(current.right);
                }
            }
            // The last node in each level will have next = null (default)
        }

        return root;
    }
}