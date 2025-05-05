package org.qinlinj.algoframework._500_data_structure_design._510_cache_algorithm_design._511_lru_cache_implementation;

/**
 * LRU Algorithm Design
 * <p>
 * To implement an LRU Cache with O(1) time complexity for both get and put operations,
 * we need a data structure that satisfies these requirements:
 * <p>
 * 1. Maintains elements in time order to identify least/most recently used items
 * 2. Supports fast lookup to determine if a key exists and to get its value
 * 3. Supports fast insertion/deletion at any position to update access order
 * <p>
 * Solution: Combine two data structures
 * - Hash Table (HashMap): For O(1) key lookup
 * - Doubly Linked List: For O(1) insertion/deletion and maintaining usage order
 * <p>
 * This combination is known as a "HashLinkedList" or "LinkedHashMap" in Java
 * <p>
 * Design decisions:
 * - Most recently used items will be at the tail of the linked list
 * - Least recently used items will be at the head of the linked list
 * - Doubly linked list required (not singly linked) for O(1) node removal
 * - Nodes must store both key and value (key needed for hash map removal)
 */
public class _511_b_LRUAlgorithmDesign {

    // Main method to demonstrate the LRU design
    public static void main(String[] args) {
        System.out.println("LRU Algorithm Design Demonstration");
        System.out.println("----------------------------------");

        // Create a diagram of the data structure
        System.out.println("Data Structure Overview:");
        System.out.println("HashMap:");
        System.out.println("  key1 → Node1");
        System.out.println("  key2 → Node2");
        System.out.println("  key3 → Node3");
        System.out.println();
        System.out.println("Doubly Linked List:");
        System.out.println("  head ↔ Node1(key1,val1) ↔ Node2(key2,val2) ↔ Node3(key3,val3) ↔ tail");
        System.out.println("  |___________ Least Recent ______________|___ Most Recent ___|");
        System.out.println();

        // Demonstrate operations
        System.out.println("When we access key2:");
        System.out.println("1. Find Node2 using HashMap lookup: O(1)");
        System.out.println("2. Remove Node2 from its current position: O(1)");
        System.out.println("3. Move Node2 to the tail (most recent): O(1)");
        System.out.println("Updated list: head ↔ Node1 ↔ Node3 ↔ Node2 ↔ tail");
        System.out.println();

        System.out.println("When cache is full and we add a new key4:");
        System.out.println("1. Remove Node1 (from head/least recent): O(1)");
        System.out.println("2. Remove key1 from HashMap: O(1)");
        System.out.println("3. Add new Node4 at tail: O(1)");
        System.out.println("4. Add key4→Node4 mapping to HashMap: O(1)");
        System.out.println("Updated list: head ↔ Node3 ↔ Node2 ↔ Node4 ↔ tail");
    }

    // Class to represent a node in our doubly linked list
    static class Node {
        int key;
        int value;
        Node prev;
        Node next;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    // Why we need to store both key and value in the Node:
    // When removing the least recently used element (from the head),
    // we need its key to remove the corresponding entry from the HashMap.
    // Without the key in the Node, we wouldn't know which HashMap entry to remove.
}