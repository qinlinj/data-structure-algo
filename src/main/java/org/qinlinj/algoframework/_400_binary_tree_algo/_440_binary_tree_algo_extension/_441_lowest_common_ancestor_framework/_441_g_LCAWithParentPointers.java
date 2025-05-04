package org.qinlinj.algoframework._400_binary_tree_algo._440_binary_tree_algo_extension._441_lowest_common_ancestor_framework;

import java.util.*;

/**
 * Lowest Common Ancestor in Trees with Parent Pointers
 * ---------------------------------------------------------
 * This class demonstrates finding the LCA when nodes have parent pointers
 * (LeetCode #1650: Lowest Common Ancestor of a Binary Tree III).
 * <p>
 * Key points:
 * 1. In this variant, nodes have parent pointers and the tree root is not given
 * 2. This transforms the problem into finding the intersection of two linked lists
 * 3. The solution uses the classic two-pointer technique for finding list intersections
 * 4. Similar to detecting a cycle in a linked list or finding the intersection point
 * 5. Significantly different approach from standard LCA algorithms
 */
public class _441_g_LCAWithParentPointers {

    // Create a sample tree with parent pointers for demonstration
    public static Node createSampleTreeWithParents() {
        Node root = new Node(3);
        Node node5 = new Node(5);
        Node node1 = new Node(1);
        Node node6 = new Node(6);
        Node node2 = new Node(2);
        Node node0 = new Node(0);
        Node node8 = new Node(8);
        Node node7 = new Node(7);
        Node node4 = new Node(4);

        // Set children
        root.left = node5;
        root.right = node1;
        node5.left = node6;
        node5.right = node2;
        node1.left = node0;
        node1.right = node8;
        node2.left = node7;
        node2.right = node4;

        // Set parent pointers
        node5.parent = root;
        node1.parent = root;
        node6.parent = node5;
        node2.parent = node5;
        node0.parent = node1;
        node8.parent = node1;
        node7.parent = node2;
        node4.parent = node2;

        return root;
    }

    public static void main(String[] args) {
        Node root = createSampleTreeWithParents();
        _441_g_LCAWithParentPointers solution = new _441_g_LCAWithParentPointers();

        // Get references to two nodes
        Node p = root.left.left;  // Node with value 6
        Node q = root.right.right; // Node with value 8

        // Find LCA using two-pointer approach
        Node lca = solution.lowestCommonAncestor(p, q);
        System.out.println("LCA of " + p.val + " and " + q.val + " is: " + lca.val);
        // Should output: LCA of 6 and 8 is: 3

        // Try with nodes in the same subtree
        Node p2 = root.left.right.left;  // Node with value 7
        Node q2 = root.left;             // Node with value 5
        Node lca2 = solution.lowestCommonAncestor(p2, q2);
        System.out.println("LCA of " + p2.val + " and " + q2.val + " is: " + lca2.val);
        // Should output: LCA of 7 and 5 is: 5

        // Try the HashSet approach
        Node lcaHash = solution.lowestCommonAncestorWithHashSet(p, q);
        System.out.println("HashSet approach: LCA of " + p.val + " and " + q.val + " is: " + lcaHash.val);
        // Should output: HashSet approach: LCA of 6 and 8 is: 3
    }

    /**
     * Solution for LeetCode #1650: Lowest Common Ancestor of a Binary Tree III
     * Uses the two-pointer approach to find the intersection point
     */
    public Node lowestCommonAncestor(Node p, Node q) {
        // Approach: Treat the parent pointers as a linked list problem
        Node a = p, b = q;

        // Two pointers approach to find intersection
        while (a != b) {
            // Advance pointer a, if reaches null, move to q
            a = (a == null) ? q : a.parent;

            // Advance pointer b, if reaches null, move to p
            b = (b == null) ? p : b.parent;
        }

        // When a == b, we've found the intersection (LCA)
        return a;
    }

    /**
     * Alternative approach using a hash set
     */
    public Node lowestCommonAncestorWithHashSet(Node p, Node q) {
        // Create a set to store visited nodes
        HashSet<Node> visited = new HashSet<>();

        // Add all ancestors of p (including p) to the set
        Node current = p;
        while (current != null) {
            visited.add(current);
            current = current.parent;
        }

        // Check if q or any of its ancestors are in the set
        current = q;
        while (current != null) {
            if (visited.contains(current)) {
                return current; // This is the LCA
            }
            current = current.parent;
        }

        return null; // Should never reach here if p and q are in the same tree
    }

    // Definition for a binary tree node with parent pointer
    static class Node {
        int val;
        Node left;
        Node right;
        Node parent;

        Node(int x) {
            val = x;
        }
    }
}