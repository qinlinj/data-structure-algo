package org.qinlinj.algoframework._500_data_structure_design._510_cache_algorithm_design._511_lru_cache_implementation;

/**
 * LRU Cache Full Implementation
 * <p>
 * This class implements a complete LRU (Least Recently Used) Cache with O(1) operations
 * following the requirements of LeetCode Problem 146.
 * <p>
 * Key features:
 * 1. Constant time O(1) for both get and put operations
 * 2. Fixed capacity with automatic eviction of least recently used items
 * 3. Uses a HashMap + Doubly Linked List combination
 * <p>
 * Design pattern:
 * - Abstract low-level operations into helper methods to avoid errors
 * - Maintain synchronized state between hash map and linked list
 * - Use dummy head/tail nodes to simplify edge cases
 * <p>
 * The implementation supports:
 * - get(key): Retrieve value and mark as recently used
 * - put(key, value): Insert or update value, mark as recently used, evict if needed
 */
public class _511_d_LRUCacheImplementation {

    public static void main(String[] args) {
        System.out.println("Complete LRU Cache Implementation");
        System.out.println("--------------------------------");

        LRUCache cache = new LRUCache(2);

        // Example operations
        cache.put(1, 1);
        System.out.println("After put(1, 1): " + cache);

        cache.put(2, 2);
        System.out.println("After put(2, 2): " + cache);

        int val1 = cache.get(1);
        System.out.println("get(1): " + val1);
        System.out.println("After get(1): " + cache + " (1 is now most recent)");

        cache.put(3, 3);
        System.out.println("After put(3, 3): " + cache + " (2 was evicted)");

        int val2 = cache.get(2);
        System.out.println("get(2): " + val2 + " (not found)");

        cache.put(4, 4);
        System.out.println("After put(4, 4): " + cache + " (1 was evicted)");

        int val3 = cache.get(1);
        System.out.println("get(1): " + val3 + " (not found)");

        int val4 = cache.get(3);
        System.out.println("get(3): " + val4);
        System.out.println("After get(3): " + cache + " (3 is now most recent)");

        int val5 = cache.get(4);
        System.out.println("get(4): " + val5);
        System.out.println("After get(4): " + cache + " (4 is now most recent)");
    }

    static class Node {
        public int key, val;
        public Node next, prev;

        public Node(int k, int v) {
            this.key = k;
            this.val = v;
        }

        @Override
        public String toString() {
            return "(" + key + "," + val + ")";
        }
    }

    static class DoubleList {
        // Head and tail dummy nodes
        private Node head, tail;
        // Size tracker
        private int size;

        public DoubleList() {
            head = new Node(0, 0);
            tail = new Node(0, 0);
            head.next = tail;
            tail.prev = head;
            size = 0;
        }

        // Add node at the end (most recent)
        public void addLast(Node x) {
            x.prev = tail.prev;
            x.next = tail;
            tail.prev.next = x;
            tail.prev = x;
            size++;
        }

        // Remove a specific node
        public void remove(Node x) {
            x.prev.next = x.next;
            x.next.prev = x.prev;
            size--;
        }

        // Remove first node (least recent)
        public Node removeFirst() {
            if (head.next == tail) {
                return null;
            }
            Node first = head.next;
            remove(first);
            return first;
        }

        public int size() {
            return size;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder("[");
            Node current = head.next;
            while (current != tail) {
                sb.append(current);
                current = current.next;
                if (current != tail) {
                    sb.append(" -> ");
                }
            }
            return sb.append("]").toString();
        }
    }

    static class LRUCache {
        // Key to Node mapping for O(1) lookup
        private java.util.HashMap<Integer, Node> map;
        // Linked list for ordering
        private DoubleList cache;
        // Maximum capacity
        private int capacity;

        public LRUCache(int capacity) {
            this.capacity = capacity;
            map = new java.util.HashMap<>();
            cache = new DoubleList();
        }

        // Get value and mark as recently used
        public int get(int key) {
            if (!map.containsKey(key)) {
                return -1;
            }

            // Key exists, make it recently used
            makeRecently(key);
            return map.get(key).val;
        }

        // Add or update value, evict if needed
        public void put(int key, int val) {
            // If key exists, update it
            if (map.containsKey(key)) {
                deleteKey(key);
                addRecently(key, val);
                return;
            }

            // If at capacity, remove least recently used
            if (cache.size() == capacity) {
                removeLeastRecently();
            }

            // Add new key-value
            addRecently(key, val);
        }

        // Helper: Move node to most recently used position
        private void makeRecently(int key) {
            Node x = map.get(key);
            // Remove from current position
            cache.remove(x);
            // Add to end (most recent)
            cache.addLast(x);
        }

        // Helper: Add as most recently used
        private void addRecently(int key, int val) {
            Node x = new Node(key, val);
            // Add to end of list (most recent)
            cache.addLast(x);
            // Add to map
            map.put(key, x);
        }

        // Helper: Delete a key completely
        private void deleteKey(int key) {
            Node x = map.get(key);
            // Remove from list
            cache.remove(x);
            // Remove from map
            map.remove(key);
        }

        // Helper: Remove least recently used item
        private void removeLeastRecently() {
            // Get least recently used node
            Node oldest = cache.removeFirst();
            // Also remove from map using the key
            // This is why we need to store the key in the Node
            map.remove(oldest.key);
        }

        @Override
        public String toString() {
            return "LRUCache(capacity=" + capacity + ", size=" + cache.size() + "): " + cache.toString();
        }
    }
}