package org.qinlinj.algoframework._500_data_structure_design._520_advanced_array_and_hash._522_hash_table_practice; /**
 * _522_e_CopyRandomList
 * <p>
 * LeetCode #138: Copy List with Random Pointer
 * <p>
 * Problem:
 * A linked list is given such that each node contains an additional random pointer
 * which could point to any node in the list or null. Return a deep copy of the list.
 * <p>
 * Approach:
 * The challenge here is handling the random pointers, which could point anywhere.
 * We solve this using a two-pass algorithm with a HashMap:
 * <p>
 * 1. First pass: Create clones of all nodes and store the mapping from original to clone
 * 2. Second pass: Connect the next and random pointers for all cloned nodes
 * <p>
 * This approach allows us to:
 * - Create all nodes before establishing connections
 * - Use the map to directly reference the corresponding clone for any original node
 * <p>
 * Time Complexity: O(n) where n is the number of nodes
 * Space Complexity: O(n) for the HashMap
 */

import java.util.*;

public class _522_e_CopyRandomList {

    // Map to track visited nodes (original -> clone)
    private HashMap<Node, Node> visited = new HashMap<>();

    // Utility method to create a test list
    private static Node createTestList(int[] vals, int[][] randoms) {
        if (vals.length == 0) {
            return null;
        }

        // Create nodes
        Node[] nodes = new Node[vals.length];
        for (int i = 0; i < vals.length; i++) {
            nodes[i] = new Node(vals[i]);
        }

        // Connect next pointers
        for (int i = 0; i < vals.length - 1; i++) {
            nodes[i].next = nodes[i + 1];
        }

        // Connect random pointers
        for (int i = 0; i < randoms.length; i++) {
            if (randoms[i][1] != -1) {
                nodes[randoms[i][0]].random = nodes[randoms[i][1]];
            }
        }

        return nodes[0];
    }

    // Utility method to print a list
    private static void printList(Node head) {
        Node current = head;
        while (current != null) {
            int randomVal = (current.random != null) ? current.random.val : -1;
            System.out.print("[" + current.val + "," + randomVal + "] -> ");
            current = current.next;
        }
        System.out.println("null");
    }

    public static void main(String[] args) {
        _522_e_CopyRandomList solution = new _522_e_CopyRandomList();

        // Test case: [[7,null],[13,0],[11,4],[10,2],[1,0]]
        int[] vals = {7, 13, 11, 10, 1};
        int[][] randoms = {{0, -1}, {1, 0}, {2, 4}, {3, 2}, {4, 0}};

        Node head = createTestList(vals, randoms);
        System.out.println("Original list:");
        printList(head);

        Node copy = solution.copyRandomList(head);
        System.out.println("Copied list:");
        printList(copy);

        // Verify deep copy
        System.out.println("Is deep copy? " + (head != copy));
    }

    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }

        // Map from original node to its clone
        HashMap<Node, Node> originalToClone = new HashMap<>();

        // First pass: Create all nodes without connections
        Node current = head;
        while (current != null) {
            originalToClone.put(current, new Node(current.val));
            current = current.next;
        }

        // Second pass: Connect next and random pointers
        current = head;
        while (current != null) {
            Node clone = originalToClone.get(current);

            // Connect next pointer
            if (current.next != null) {
                clone.next = originalToClone.get(current.next);
            }

            // Connect random pointer
            if (current.random != null) {
                clone.random = originalToClone.get(current.random);
            }

            current = current.next;
        }

        // Return the head of the cloned list
        return originalToClone.get(head);
    }

    /**
     * Alternative implementation using DFS traversal
     */
    public Node copyRandomListDFS(Node head) {
        // Initialize structures for tracking
        visited = new HashMap<>();

        // Start DFS traversal from head
        Node clone = dfs(head);

        return clone;
    }

    private Node dfs(Node node) {
        // Base case
        if (node == null) {
            return null;
        }

        // If already visited, return the clone
        if (visited.containsKey(node)) {
            return visited.get(node);
        }

        // Create a new clone node
        Node clone = new Node(node.val);

        // Add to visited map before recursion to avoid cycles
        visited.put(node, clone);

        // Recursively construct next and random pointers
        clone.next = dfs(node.next);
        clone.random = dfs(node.random);

        return clone;
    }

    // Definition for a Node in the linked list
    static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }
}