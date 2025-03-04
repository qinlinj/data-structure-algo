package org.qinlinj.nonlinear.highlevel.map;

import java.util.NoSuchElementException;

public class LinkedListMap<K, V> implements Map<K, V> {

    private Node dummyHead;
    private int size;

    public LinkedListMap() {
        this.dummyHead = new Node();
        this.size = 0;
    }

    /**
     * Get the number of key-value pairs stored in the map.
     *
     * @return the size of the map (number of entries)
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Check if the map is empty.
     *
     * @return true if the map contains no key-value pairs, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Add a new key-value pair to the map.
     * If the key already exists, it will be updated with the new value.
     *
     * @param key   the key to be added
     * @param value the value associated with the key
     */
    @Override
    public void add(K key, V value) { // O(n)
        Node curr = getNode(key);
        if (curr == null) {
            dummyHead.next = new Node(key, value, dummyHead.next);
            size++;
        } else {
            curr.value = value;
        }
    }

    private Node getNode(K key) { // O(n)
        Node curr = dummyHead.next;
        while (curr != null) {
            if (key.equals(curr.key)) {
                break;
            }
            curr = curr.next;
        }
        return curr;
    }

    /**
     * Remove the key-value pair associated with the specified key.
     *
     * @param key the key to be removed
     * @return the value previously associated with the key, or null if not found
     */
    @Override
    public V remove(K key) { // O(n)
        Node prev = dummyHead;
        Node curr = dummyHead.next;
        while (curr != null) {
            if (curr.key.equals(key)) {
                break;
            }
            prev = curr;
            curr = curr.next;
        }

        if (curr != null) {
            prev.next = curr.next;
            curr.next = null;
            size--;
            return curr.value;
        }
        return null;
    }

    /**
     * Update the value associated with the specified key.
     *
     * @param key      the key whose value is to be updated
     * @param newValue the new value to be associated with the key
     */
    @Override
    public void set(K key, V newValue) { // O(n)
        Node curr = getNode(key);

        if (curr != null) curr.value = newValue;
        else throw new NoSuchElementException("no such key ：" + key);
    }

    /**
     * Retrieve the value associated with the specified key.
     *
     * @param key the key to look up
     * @return the value associated with the key, or null if the key is not found
     */
    @Override
    public V get(K key) { // O(n)
        Node node = getNode(key);
        return node == null ? null : node.value;
    }

    /**
     * Check if the map contains the specified key.
     *
     * @param key the key to check for existence
     * @return true if the key exists in the map, false otherwise
     */
    @Override
    public boolean containsKey(K key) { // O(n)
        Node node = getNode(key);
        return node != null;
    }

    private class Node {
        K key;
        V value;
        Node next;

        public Node(K key, V value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public Node(K key, V value) {
            this(key, value, null);
        }

        public Node() {
            this(null, null, null);
        }

        @Override
        public String toString() {
            return key.toString() + "->" + value.toString();
        }
    }
}
