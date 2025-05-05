package org.qinlinj.algoframework._500_data_structure_design._510_cache_algorithm_design._511_lru_cache_implementation;

/**
 * Doubly Linked List Implementation for LRU Cache
 * <p>
 * This class implements a specialized doubly linked list for the LRU Cache algorithm,
 * with the following characteristics:
 * <p>
 * 1. Uses dummy head and tail nodes for simplified edge case handling
 * 2. Maintains a size counter for O(1) size operations
 * 3. Provides operations needed for LRU Cache:
 * - addLast: Add node at the end (most recently used)
 * - remove: Remove a specific node
 * - removeFirst: Remove from the front (least recently used)
 * - size: Get current size
 * <p>
 * All operations are O(1) time complexity, which is essential for the
 * LRU Cache implementation.
 */
public class _511_c_DoublyLinkedList {

    // Demonstration
    public static void main(String[] args) {
        System.out.println("Doubly Linked List for LRU Cache");
        System.out.println("--------------------------------");

        DoubleList list = new DoubleList();
        System.out.println("Initial state: " + list);

        // Add nodes
        Node node1 = new Node(1, 100);
        list.addLast(node1);
        System.out.println("After adding (1,100): " + list);

        Node node2 = new Node(2, 200);
        list.addLast(node2);
        System.out.println("After adding (2,200): " + list);

        Node node3 = new Node(3, 300);
        list.addLast(node3);
        System.out.println("After adding (3,300): " + list);

        // Remove a specific node
        list.remove(node2);
        System.out.println("After removing (2,200): " + list);

        // Remove the least recently used (first) node
        Node removed = list.removeFirst();
        System.out.println("Removed first node: " + removed);
        System.out.println("After removing first: " + list);

        // Size operation
        System.out.println("Current size: " + list.size());
    }

    // Node class for doubly linked list
    static class Node {
        public int key;
        public int val;
        public Node next;
        public Node prev;

        public Node(int key, int val) {
            this.key = key;
            this.val = val;
        }

        @Override
        public String toString() {
            return "(" + key + "," + val + ")";
        }
    }

    // Doubly linked list implementation
    static class DoubleList {
        // Dummy head and tail nodes
        private Node head;
        private Node tail;
        // Track size for O(1) size operations
        private int size;

        public DoubleList() {
            // Initialize with dummy nodes
            head = new Node(0, 0);
            tail = new Node(0, 0);
            head.next = tail;
            tail.prev = head;
            size = 0;
        }

        // Add a node at the end (most recently used)
        public void addLast(Node node) {
            // Connect node to the list
            node.prev = tail.prev;
            node.next = tail;
            // Connect list to the node
            tail.prev.next = node;
            tail.prev = node;
            size++;
        }

        // Remove a specific node (requires O(1) with doubly linked list)
        public void remove(Node node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
            // Clean references to help garbage collection
            node.prev = null;
            node.next = null;
            size--;
        }

        // Remove the first node (least recently used)
        public Node removeFirst() {
            if (head.next == tail) {
                return null; // List is empty
            }
            Node first = head.next;
            remove(first);
            return first;
        }

        // Get current size
        public int size() {
            return size;
        }

        // Utility method to print the list
        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("head ↔ ");

            Node current = head.next;
            while (current != tail) {
                sb.append(current).append(" ↔ ");
                current = current.next;
            }

            sb.append("tail (size=").append(size).append(")");
            return sb.toString();
        }
    }
}