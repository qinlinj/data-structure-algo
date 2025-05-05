package org.qinlinj.algoframework._500_data_structure_design._510_cache_algorithm_design._511_lru_cache_implementation;

/**
 * LRU (Least Recently Used) Cache Algorithm Summary
 * <p>
 * This class provides a summary of the LRU algorithm and its key concepts.
 * <p>
 * Key Points:
 * <p>
 * 1. Purpose:
 * - Manage limited cache space efficiently
 * - Evict items based on recency of access
 * - Optimize for frequently accessed items
 * <p>
 * 2. Implementation Approaches:
 * - Custom implementation with HashMap + DoublyLinkedList
 * - Using Java's built-in LinkedHashMap
 * <p>
 * 3. Time Complexity Requirements:
 * - get(key): O(1)
 * - put(key, value): O(1)
 * <p>
 * 4. Key Data Structure Components:
 * - Fast lookup: HashMap
 * - Order tracking: Doubly linked list
 * - Combined: HashLinkedList / LinkedHashMap
 * <p>
 * 5. Implementation Challenges:
 * - Maintaining synchronized state between HashMap and linked list
 * - Handling edge cases (empty cache, existing keys, etc.)
 * - Ensuring each operation maintains O(1) complexity
 * <p>
 * 6. Applications:
 * - Web caches
 * - Database query caches
 * - Operating system page replacement
 * - Mobile app background management
 * <p>
 * 7. Variations:
 * - LFU (Least Frequently Used)
 * - FIFO (First In First Out)
 * - Time-based expiration
 */
public class _511_f_LRUSummary {

    public static void main(String[] args) {
        System.out.println("Summary of LRU Cache Algorithm");
        System.out.println("=============================");

        // Comparison of implementation approaches
        System.out.println("Implementation Comparison:");

        System.out.println("\n1. Custom HashMap + DoublyLinkedList:");
        System.out.println("   Advantages:");
        System.out.println("   - Full control over implementation details");
        System.out.println("   - Educational value in understanding the algorithm");
        System.out.println("   - Can be customized for specific requirements");
        System.out.println("   Disadvantages:");
        System.out.println("   - More complex code to maintain");
        System.out.println("   - Potential for bugs in edge cases");
        System.out.println("   - Requires careful synchronization of data structures");

        System.out.println("\n2. Java's LinkedHashMap:");
        System.out.println("   Advantages:");
        System.out.println("   - Much simpler implementation");
        System.out.println("   - Built-in, well-tested functionality");
        System.out.println("   - Less prone to bugs");
        System.out.println("   Disadvantages:");
        System.out.println("   - Less flexibility for customization");
        System.out.println("   - Slightly higher memory overhead");
        System.out.println("   - Less educational value");

        // Code examples for quick reference
        System.out.println("\nKey Implementation Components:");

        System.out.println("\n1. Node class for Doubly Linked List:");
        System.out.println("```java");
        System.out.println("class Node {");
        System.out.println("    int key, val;");
        System.out.println("    Node prev, next;");
        System.out.println("    Node(int key, int val) { this.key = key; this.val = val; }");
        System.out.println("}");
        System.out.println("```");

        System.out.println("\n2. Core operations:");
        System.out.println("```java");
        System.out.println("// Make recently used");
        System.out.println("private void makeRecently(int key) {");
        System.out.println("    Node x = map.get(key);");
        System.out.println("    cache.remove(x);     // Remove from current position");
        System.out.println("    cache.addLast(x);    // Add to tail (most recent)");
        System.out.println("}");

        System.out.println("\n// Add recently used");
        System.out.println("private void addRecently(int key, int val) {");
        System.out.println("    Node x = new Node(key, val);");
        System.out.println("    cache.addLast(x);    // Add to tail (most recent)");
        System.out.println("    map.put(key, x);     // Add to map");
        System.out.println("}");

        System.out.println("\n// Remove least recently used");
        System.out.println("private void removeLeastRecently() {");
        System.out.println("    Node oldest = cache.removeFirst();   // From head (least recent)");
        System.out.println("    map.remove(oldest.key);             // Remove from map");
        System.out.println("}");
        System.out.println("```");

        System.out.println("\n3. Using LinkedHashMap:");
        System.out.println("```java");
        System.out.println("class LRUCache {");
        System.out.println("    private LinkedHashMap<Integer, Integer> cache;");
        System.out.println("    private int capacity;");
        System.out.println("    ");
        System.out.println("    public LRUCache(int capacity) {");
        System.out.println("        this.capacity = capacity;");
        System.out.println("        cache = new LinkedHashMap<>(capacity, 0.75f, true) {");
        System.out.println("            protected boolean removeEldestEntry(Map.Entry eldest) {");
        System.out.println("                return size() > capacity;");
        System.out.println("            }");
        System.out.println("        };");
        System.out.println("    }");
        System.out.println("    ");
        System.out.println("    public int get(int key) {");
        System.out.println("        return cache.getOrDefault(key, -1);");
        System.out.println("    }");
        System.out.println("    ");
        System.out.println("    public void put(int key, int val) {");
        System.out.println("        cache.put(key, val);");
        System.out.println("    }");
        System.out.println("}");
        System.out.println("```");

        // Common pitfalls and best practices
        System.out.println("\nCommon Pitfalls:");
        System.out.println("1. Forgetting to update the HashMap when modifying the linked list");
        System.out.println("2. Not handling existing keys properly in put operation");
        System.out.println("3. Incorrect linked list implementation (missing pointers)");
        System.out.println("4. Forgetting to store keys in linked list nodes");
        System.out.println("5. Not considering thread safety for concurrent access");

        System.out.println("\nBest Practices:");
        System.out.println("1. Abstract low-level operations into helper methods");
        System.out.println("2. Use dummy head/tail nodes to simplify edge cases");
        System.out.println("3. Test with edge cases (empty cache, repeated keys)");
        System.out.println("4. Consider using Java's LinkedHashMap for production code");
        System.out.println("5. Add logging for debugging complex operations");

        // Performance comparison
        performanceComparison();
    }

    // Performance comparison method
    private static void performanceComparison() {
        System.out.println("\nPerformance Comparison (Illustrative):");
        System.out.println("------------------------------------");

        // Create custom LRU cache
        System.out.println("Testing custom LRU implementation...");
        long start = System.nanoTime();
        CustomLRUCache customCache = new CustomLRUCache(1000);
        for (int i = 0; i < 10000; i++) {
            customCache.put(i, i);
            if (i % 10 == 0) {
                customCache.get(i / 10);
            }
        }
        long customTime = System.nanoTime() - start;

        // Create LinkedHashMap LRU cache
        System.out.println("Testing LinkedHashMap implementation...");
        start = System.nanoTime();
        LinkedHashMapLRUCache linkedCache = new LinkedHashMapLRUCache(1000);
        for (int i = 0; i < 10000; i++) {
            linkedCache.put(i, i);
            if (i % 10 == 0) {
                linkedCache.get(i / 10);
            }
        }
        long linkedTime = System.nanoTime() - start;

        System.out.println("Custom implementation time: " + customTime / 1_000_000 + " ms");
        System.out.println("LinkedHashMap time: " + linkedTime / 1_000_000 + " ms");
        System.out.println("Note: Actual performance may vary based on usage patterns and JVM optimizations");
    }

    // Simplified custom LRU implementation for testing
    static class CustomLRUCache {
        private java.util.HashMap<Integer, Node> map;
        private Node head, tail;
        private int capacity;
        public CustomLRUCache(int capacity) {
            this.capacity = capacity;
            map = new java.util.HashMap<>();
            head = new Node(0, 0);
            tail = new Node(0, 0);
            head.next = tail;
            tail.prev = head;
        }

        public int get(int key) {
            if (!map.containsKey(key)) return -1;
            Node node = map.get(key);
            remove(node);
            addToTail(node);
            return node.val;
        }

        public void put(int key, int val) {
            if (map.containsKey(key)) {
                Node node = map.get(key);
                node.val = val;
                remove(node);
                addToTail(node);
            } else {
                if (map.size() == capacity) {
                    Node toRemove = head.next;
                    remove(toRemove);
                    map.remove(toRemove.key);
                }
                Node newNode = new Node(key, val);
                addToTail(newNode);
                map.put(key, newNode);
            }
        }

        private void remove(Node node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }

        private void addToTail(Node node) {
            node.prev = tail.prev;
            node.next = tail;
            tail.prev.next = node;
            tail.prev = node;
        }

        private class Node {
            int key, val;
            Node prev, next;

            Node(int k, int v) {
                key = k;
                val = v;
            }
        }
    }

    // LinkedHashMap implementation for testing
    static class LinkedHashMapLRUCache {
        private int capacity;
        private java.util.LinkedHashMap<Integer, Integer> cache;

        public LinkedHashMapLRUCache(int capacity) {
            this.capacity = capacity;
            this.cache = new java.util.LinkedHashMap<Integer, Integer>(capacity, 0.75f, true) {
                @Override
                protected boolean removeEldestEntry(java.util.Map.Entry<Integer, Integer> eldest) {
                    return size() > capacity;
                }
            };
        }

        public int get(int key) {
            return cache.getOrDefault(key, -1);
        }

        public void put(int key, int value) {
            cache.put(key, value);
        }
    }
}