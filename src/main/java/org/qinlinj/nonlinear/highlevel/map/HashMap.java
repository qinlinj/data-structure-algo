package org.qinlinj.nonlinear.highlevel.map;

import java.util.NoSuchElementException;

public class HashMap<K, V> implements Map<K, V> {
    private Node<K, V>[] data;
    private int size;
    private double loadFactor;

    public HashMap(int initCapacity, double loadFactor) {
        this.data = new Node[initCapacity];
        this.size = 0;
        this.loadFactor = loadFactor;
    }

    public HashMap(int initCapacity) {
        this(initCapacity, 0.75);
    }

    public HashMap(double loadFactor) {
        this(10, loadFactor);
    }

    public HashMap() {
        this(10, 0.75);
    }

    private int hash(K key, int length) {
        return Math.abs(key.hashCode()) % length;
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
    public void add(K key, V value) {
        int index = hash(key, data.length);
        Node<K, V> curr = getNode(key, index);
        if (curr == null) {
            data[index] = new Node(key, value, data[index]);
            size++;
            if (size >= data.length * loadFactor) {
                resize(2 * data.length);
            }
        } else {
            curr.value = value;
        }
    }

    private Node<K, V> getNode(K key, int index) {
        Node<K, V> curr = data[index];
        while (curr != null) {
            if (curr.key.equals(key)) {
                break;
            }
            curr = curr.next;
        }
        return curr;
    }

    private void resize(int newCapacity) {
        Node[] newData = new Node[newCapacity];
        for (int i = 0; i < data.length; i++) {
            Node<K, V> curr = data[i];
            while (curr != null) {
                K key = curr.key;
                int newIndex = hash(key, newCapacity);
                Node head = newData[newIndex];
                newData[newIndex] = new Node(key, curr.value);
                if (head != null) {
                    newData[newIndex].next = head;
                }
                curr = curr.next;
            }
        }
        data = newData;
    }

    /**
     * Remove the key-value pair associated with the specified key.
     *
     * @param key the key to be removed
     * @return the value previously associated with the key, or null if not found
     */
    @Override
    public V remove(K key) {
        int index = hash(key, data.length);
        if (data[index] == null) {
            return null;
        }
        Node<K, V> prev = null;
        Node<K, V> curr = data[index];
        while (curr != null) {
            if (key.equals(curr.key)) {
                if (prev == null) {
                    data[index] = curr.next;
                } else {
                    prev.next = curr.next;
                }
                curr.next = null;
                size--;
                return curr.value;
            }
            prev = curr;
            curr = curr.next;
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
    public void set(K key, V newValue) {
        int index = hash(key, data.length);
        Node<K, V> node = getNode(key, index);
        if (node == null) {
            throw new NoSuchElementException("There is no corresponding key: " + key);
        }
        node.value = newValue;
    }

    /**
     * Retrieve the value associated with the specified key.
     *
     * @param key the key to look up
     * @return the value associated with the key, or null if the key is not found
     */
    @Override
    public V get(K key) {
        int index = hash(key, data.length);
        Node<K, V> node = getNode(key, index);
        return node == null ? null : node.value;
    }

    /**
     * Check if the map contains the specified key.
     *
     * @param key the key to check for existence
     * @return true if the key exists in the map, false otherwise
     */
    @Override
    public boolean containsKey(K key) {
        int index = hash(key, data.length);
        Node<K, V> node = getNode(key, index);
        return node != null;
    }

    private class Node<K, V> {
        K key;
        V value;
        Node<K, V> next;

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
