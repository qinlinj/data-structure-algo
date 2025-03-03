package org.qinlinj.nonlinear.highlevel.set;

/**
 * Open Addressing Hash Set implementation using linear probing.
 * This implementation handles hash collisions by finding the next available slot.
 * <p>
 * Key Concepts:
 * - Uses linear probing for collision resolution
 * - Supports dynamic resizing based on load factor
 * - Handles deleted elements with a soft delete mechanism
 *
 * @param <E> the type of elements in the set
 */
public class HashSetOpenAddressImplementation<E> implements Set<E> {
    // Internal array to store hash set items
    private Item<E>[] items;

    // Number of active elements in the set
    private int size;

    // Count of deleted elements (used for optimization)
    private int deleteCount;

    // Threshold for resizing the hash set
    private double loadFactor = 0.75;

    /**
     * Default constructor initializes the hash set with a default capacity.
     */
    public HashSetOpenAddressImplementation() {
        // Initial capacity of 10, can grow dynamically
        this.items = new Item[10];
        this.size = 0;
        this.deleteCount = 0;
    }

    /**
     * Constructor that allows custom load factor.
     *
     * @param loadFactor threshold for resizing the hash set
     */
    public HashSetOpenAddressImplementation(int loadFactor) {
        this();
        this.loadFactor = loadFactor;
    }

    /**
     * Main method to demonstrate basic set operations.
     */
    public static void main(String[] args) {
        // Create a new set and test its functionality
        Set<Integer> set = new HashSetOpenAddressImplementation<>();
        set.add(11);
        set.add(21);

        // Check element existence
        System.out.println(set.contains(21));
        System.out.println(set.contains(11));
    }

    /**
     * Returns the number of elements in the set.
     *
     * @return current size of the set
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Checks if the set is empty.
     *
     * @return true if set contains no elements, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Generates a hash index for an element.
     *
     * @param e      element to hash
     * @param length current array length
     * @return hash index within array bounds
     */
    public int hash(E e, int length) {
        // Use absolute value to handle negative hash codes
        // Ensure index is within array bounds
        return Math.abs(e.hashCode()) % length;
    }

    /**
     * Adds an element to the set.
     * <p>
     * Uses linear probing to handle collisions:
     * - If slot is empty, insert element
     * - If element already exists, do nothing
     * - If slot is occupied, move to next slot
     *
     * @param e element to add
     */
    @Override
    public void add(E e) {
        // Calculate initial hash index
        int index = hash(e, items.length);

        // Linear probing to find an empty slot
        while (items[index] != null) {
            // If element already exists and is not deleted, skip
            if (!items[index].isDeleted && e.equals(items[index].data)) return;

            // Move to next slot (wrap around if needed)
            index++;
            index = index % items.length;
        }

        // Insert new element
        items[index] = new Item(e);
        size++;

        // Resize if load factor exceeded
        if (size >= items.length * loadFactor) {
            resize(2 * items.length);
        }
    }

    /**
     * Resizes the internal array and rehashes all elements.
     *
     * @param newCapacity new size of the internal array
     */
    private void resize(int newCapacity) {
        // Create new array with increased capacity
        Item<E>[] newData = new Item[newCapacity];

        // Rehash existing non-deleted elements
        for (int i = 0; i < items.length; i++) {
            if (items[i] != null && !items[i].isDeleted) {
                // Recalculate hash for new array size
                int newIndex = hash(items[i].data, newCapacity);
                newData[newIndex] = items[i];
            }
        }

        // Reset delete count and update array
        deleteCount = 0;
        items = newData;
    }

    /**
     * Removes an element from the set.
     * <p>
     * Uses soft delete (mark as deleted instead of removing)
     *
     * @param e element to remove
     */
    @Override
    public void remove(E e) {
        // Calculate initial hash index
        int index = hash(e, items.length);

        // Linear probing to find the element
        while (items[index] != null && (!e.equals(items[index].data) || !items[index].isDeleted)) {
            index++;
            index = index % items.length;
        }

        // Mark element as deleted if found
        if (items[index] != null && !items[index].isDeleted) {
            items[index].isDeleted = true;
            deleteCount++;
            size--;
        }

        // Resize if too many deleted elements
        if (deleteCount + size >= items.length * loadFactor) {
            resize(items.length);
        }
    }

    /**
     * Checks if an element exists in the set.
     *
     * @param e element to search for
     * @return true if element is in the set, false otherwise
     */
    @Override
    public boolean contains(E e) {
        // Calculate initial hash index
        int index = hash(e, items.length);

        // Linear probing to find the element
        while (items[index] != null &&
                (!e.equals(items[index].data) || items[index].isDeleted)) {
            index++;
            index = index % items.length;
        }

        // Element found and not deleted
        return items[index] != null && !items[index].isDeleted;
    }

    /**
     * Internal class to store set items with deletion flag.
     * <p>
     * Soft delete approach allows efficient handling of removed elements
     */
    private class Item<E> {
        // Stored element
        E data;
        // Flag to mark soft-deleted elements
        boolean isDeleted;

        /**
         * Constructor for an item.
         *
         * @param data element to store
         */
        public Item(E data) {
            this.data = data;
            this.isDeleted = false;
        }

        /**
         * Generates hash code based on stored element.
         *
         * @return hash code of the stored element
         */
        @Override
        public int hashCode() {
            return data.hashCode();
        }
    }
}